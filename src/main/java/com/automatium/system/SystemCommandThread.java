package com.automatium.system;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sgurusharan on 12/10/16.
 */
public class SystemCommandThread extends Thread {
    private String command;
    private CommandOutput output = null;

    private static final String BASH_PROFILE = System.getenv("HOME") + "/.bash_profile";

    public SystemCommandThread(String command) {
        this.command = command;
    }

    public CommandOutput getOutput() {
        return output;
    }

    @Override
    public void run() {
        try {

            String cmdPrefix = "sh ";
            if (new File(BASH_PROFILE).exists()) {
                command = String.format("source %s\n%s", BASH_PROFILE, command);
            }

            if (System.getProperty("os.name").equals("Windows")) {
                cmdPrefix = "cmd /c ";
            }

            File tempShFile = File.createTempFile("syscmd", ".sh");
            FileWriter fileWriter = new FileWriter(tempShFile);
            fileWriter.write(command);
            fileWriter.close();

            Process process = Runtime.getRuntime().exec(cmdPrefix + tempShFile.getAbsolutePath());
            BufferedReader cmdOutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader cmdErrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            ExecutorService executor = Executors.newFixedThreadPool(2);
            StreamThread cmdOutThread = new StreamThread(cmdOutReader);
            StreamThread cmdErrThread = new StreamThread(cmdErrReader);
            executor.execute(cmdOutThread);
            executor.execute(cmdErrThread);

            executor.shutdown();

            while (!executor.isTerminated()) {
                // Wait for process termination
                Thread.sleep(1000L);
            }

            tempShFile.delete();

            output = new CommandOutput(cmdOutThread.content, cmdErrThread.content, process.exitValue());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (output == null) {
            output = new CommandOutput("", "", -255);
        }
    }

    static class StreamThread extends Thread {
        private BufferedReader bufferedReader;
        private String content = "";

        public StreamThread(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
        }

        String getContent() {
            return content;
        }

        @Override
        public void run() {
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    content += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
