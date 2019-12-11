import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Disconnect implements Runnable {
	Socket s;
	PrintWriter p;
	public Disconnect(Socket s, PrintWriter p) {
		this.s = s;
		this.p = p;
	}
	@Override
	public void run() {
		try {
			p.println("disconnetti");
			p.flush();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

}
