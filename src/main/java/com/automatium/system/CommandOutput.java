package com.automatium.system;

/**
 * Created by sgurusharan on 12/10/16.
 *
 * A placeholder for system command outputs.
 */
public class CommandOutput {
    private String out, err;
    private int exitCode;

    /**
     * Creates a new object with the given output message, error message and process return code.
     *
     * @param out - OutputString from the executed command
     * @param err - Error String from the executed command
     * @param exitCode - Process return code from the executed command
     */
    public CommandOutput(String out, String err, int exitCode) {
        this.out = out;
        this.err = err;
        this.exitCode = exitCode;
    }

    /**
     * Get the output as a String
     *
     * @return output as a String
     */
    public String getOut() {
        return out;
    }


    /**
     * Get the error as a String
     *
     * @return error as a String
     */
    public String getErr() {
        return err;
    }

    /**
     * Get the exit code as an int.
     *
     * @return Exit code as int.
     */
    public int getExitCode() {
        return exitCode;
    }
}
