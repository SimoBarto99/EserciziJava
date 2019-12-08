import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Disconnect implements Runnable {
	Socket s;
	PrintWriter out;
	
	public Disconnect(Socket s, PrintWriter out) {
		this.out = out;
		this.s = s;
	}
	@Override
	public void run() {
		try {
			out.println("disconnect");
			out.flush();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
