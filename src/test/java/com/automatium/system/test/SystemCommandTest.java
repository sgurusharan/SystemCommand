package com.automatium.system.test;

import com.automatium.system.CommandOutput;
import com.automatium.system.SystemCommand;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sgurusharan on 12/10/16.
 */
public class SystemCommandTest {
    @Test
    public void testUnixEnvCommand() {

        if (System.getProperty("os.name").equals("Windows")) {
            return;
        }

        String unixCommand = "echo $HOME";

        CommandOutput output = SystemCommand.execute(unixCommand);
        Assert.assertEquals(output.getExitCode(), 0);
        Assert.assertTrue(output.getOut().equals(System.getenv("HOME") + "\n"));
        Assert.assertTrue(output.getOut().equals(System.getenv("HOME") + "\n"));
    }

    @Test
    public void testWindowsEnvCommand() {
        if (!System.getProperty("os.name").equals("Windows")) {
            return;
        }

        String unixCommand = "echo %HOME%";

        CommandOutput output = SystemCommand.execute(unixCommand);
        Assert.assertEquals(output.getExitCode(), 0);
        Assert.assertTrue(output.getOut().equals(System.getenv("HOME") + "\n"));
        Assert.assertTrue(output.getOut().equals(System.getenv("HOME") + "\n"));
    }
}
