package com.automatium.system;

/**
 * Created by sgurusharan on 12/10/16.
 */
public class CommandOutput {
    private String out, err;
    private int exitCode;

    public CommandOutput(String out, String err, int exitCode) {
        this.out = out;
        this.err = err;
        this.exitCode = exitCode;
    }

    public String getOut() {
        return out;
    }

    public String getErr() {
        return err;
    }

    public int getExitCode() {
        return exitCode;
    }
}
