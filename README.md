# SystemCommand
A quick and easy way to run native system commands (a wrapper to Process).

## Importing Latest Version : 1.0
You can import this library into a gradle project by following the below steps:

- Since this project has not made into maven repo yet - I am currently using JitPack for distribution. So add the JitPack repo to your build's repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```
- Now you can add the dependency:
```
dependencies {
    compile 'com.github.sgurusharan:SystemCommand:1.0-SNAPSHOT'
}
```
## Usage
There are two ways to execute native system commands using this library:

### 1. Blocking Execution
This would block your program until the execution of the given native command(s) is complete and returns the command(s)' result wrapped inside a `CommandOutput` object:
```
com.automatium.system.CommandOutput commandOutput = com.automatium.system.SystemCommand.execute("dir");
System.out.println("Output: " + commandOutput.getOut());
System.out.println("Error: " + commandOutput.getErr());
System.out.println("Exit Code: " + commandOutput.getExitCode());
```
### 2. Threaded Execution
This is similar to using the Process API in Java, except for hooking into output and error streams. You create a new thread and run it - once the thread completes, you can access the output.
```
com.automatium.system.SystemCommandThread systemCommandThread = new com.automatium.system.SystemCommandThread("dir");
systemCommandThread.start();
try {
  systemCommandThread.join();
} catch (InterruptedException e) {
  e.printStackTrace();
}
System.out.println("Output: " + systemCommandThread.getOutput().getOut());
System.out.println("Error: " + systemCommandThread.getOutput().getErr());
System.out.println("Exit Code: " + systemCommandThread.getOutput().getExitCode());
```
