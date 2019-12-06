import java.awt.Frame;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;

public class Connect implements Runnable {
	Frame f;
	Socket s;
	String cmd;
	JTextField ip;
	JTextField porta;
	public Connect(Frame f, String cmd, Socket s, JTextField ip, JTextField porta) {
		this.f = f;
		this.s = s;
		this.cmd = cmd;
		this.ip = ip;
		this.porta = porta;
	}
	
	@Override
	public synchronized void run() {
		try {
			while(cmd == "Connect") {
			s = new Socket(ip.getText(), Integer.parseInt(porta.getText()));
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
