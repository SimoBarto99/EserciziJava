import java.awt.Frame;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;

public class Connect implements Runnable {
	Frame f;
	Socket s;
	String cmd;
	public Connect(Frame f, String cmd, Socket s) {
		this.f = f;
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
