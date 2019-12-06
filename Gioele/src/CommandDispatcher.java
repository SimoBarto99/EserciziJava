import CommandDispatcher.Command;

public class CommandDispatcher {
	public enum Command {NULL,CONNECT,START,STOP,DISCONNECT};
	
	private Command pendingCommand = Command.NULL;

	public synchronized void sendCommand(Command c) {
		pendingCommand = c;
		this.notify();
	}
	public synchronized void comply() {
		pendingCommand = Command.NULL;
		this.notify();
	}
	
	public boolean isPendingCommand() {
		return pendingCommand != Command.NULL;
	}
	
	public Command getPendingCommand() {
		return pendingCommand;
	}
}