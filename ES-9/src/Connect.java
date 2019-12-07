import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextField;

public class Connect implements Runnable {
	Socket s;
	String cmd;
	JTextField ip, porta;
	
	public Connect(String cmd, JTextField ip, JTextField porta, Socket s) {
		this.cmd = cmd;
		this.ip = ip;
		this.porta = porta;
		this.s = s;
	}
	@Override
	public void run() {
		try {
			s = new Socket(ip.getText(), Integer.parseInt(porta.getText()));
			System.out.println("connesso");
		} catch (NumberFormatException | IOException e) {
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
