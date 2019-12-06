import java.net.Socket;

public class Stop implements Runnable {
	String cmd;
	Socket s;
	public Stop(String cmd, Socket s) {
		this.cmd = cmd;
		this.s = s;
	}
	@Override
	public void run() {
				
	}

}
