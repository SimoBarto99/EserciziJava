import java.net.Socket;

public class Clear implements Runnable {
	Socket s;
	String cmd;
	public Clear(String cmd, Socket s) {
		this.s = s;
		this.cmd = cmd;
	}
	@Override
	public void run() {
				
	}

}
