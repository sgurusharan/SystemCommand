package com.automatium.system;

/**
 * Created by sgurusharan on 12/10/16.
 *
 */
public class SystemCommand {

    /**
     * Executes the given command, waits until the process exits, bundles the output,
     * error and the exit code of the execution into a {@link CommandOutput}.
     *
     * @param command - a Shell/Windows command to execute.
     * @return the results of execution as a {@link CommandOutput} object.
     * @see CommandOutput
     */
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
