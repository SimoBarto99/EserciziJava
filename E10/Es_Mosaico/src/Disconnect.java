import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Disconnect implements Runnable {
	Socket s;
	String cmd;
	Disconnect(String cmd, Socket s)  {
		this.s = s;
		this.cmd = cmd;
	}
	@Override
	public synchronized void run() {
		try {
			//contatta il serever
			PrintWriter out = new PrintWriter(s.getOutputStream());
			out.println("disconnect");
			out.flush();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
