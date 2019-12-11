import java.io.IOException;
import java.net.Socket;

public class Connect implements Runnable {
	Socket s;
	String ip;
	int po;
	
	public Connect(Socket s, String ip, int po) {
		this.s = s;
		this.ip = ip;
		this.po = po;
	}
	
	@Override
	public void run() {
		try {
			s = new Socket(ip, po);
			System.out.println("connesso");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getS() {
		return s;
	}

}
