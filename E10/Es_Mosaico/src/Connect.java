import java.io.IOException;
import java.net.Socket;

public class Connect implements Runnable {
	Socket s;
	String cmd;
	public Connect(String cmd, Socket s) {
		this.s = s;
		this.cmd = cmd;
	}
	
	@Override
	public synchronized void run() {
		try {
			while(cmd == "Connect") {
			s = new Socket("127.0.0.1", 4400);
			break;
			}
			System.out.println("Connesso");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
