package com.automatium.system;

/**
 * Created by sgurusharan on 12/10/16.
 */
public class SystemCommand {

    public static CommandOutput execute(String command) {
        SystemCommandThread systemCommandThread = new SystemCommandThread(command);
        systemCommandThread.start();
        try {
            systemCommandThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return systemCommandThread.getOutput();
    }
}
