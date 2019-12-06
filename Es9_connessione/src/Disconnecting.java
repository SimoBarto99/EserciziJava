import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Disconnecting extends Frame implements Runnable{
	JFrame frame;
	String cmd;
	Socket s;
	PrintWriter outWriter;
	JButton c, sto;
	public Disconnecting(JFrame frame, String cmd, Socket s, PrintWriter outWriter){
		this.frame = frame;
		this.cmd = cmd;
		this.s = s;
		this.outWriter = outWriter;
		
	}
	@Override
	public void run() {
		outWriter.println("disconnect");
		outWriter.flush();
		outWriter.close();
	}
}
