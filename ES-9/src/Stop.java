import java.io.PrintWriter;
import java.net.Socket;

public class Stop implements Runnable {
	Socket s;
	PrintWriter in;
	
	public Stop(Socket s, PrintWriter in) {
		this.s = s;
		this.in = in;
	}
	@Override
	public void run() {
		in.println("stop");
		in.flush();
	}

}
