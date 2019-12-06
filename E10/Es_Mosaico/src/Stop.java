import java.io.IOException;
import java.io.PrintWriter;
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
		try {
			PrintWriter bir = new PrintWriter(s.getOutputStream());
			bir.println("stop");
			bir.flush();
//			bir.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
