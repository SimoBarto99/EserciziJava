import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

public class Stopping extends Frame implements Runnable {
	JFrame frame;
	String cmd;
	Socket s;
	PrintWriter outWriter;
	Scanner bir;
	
	public Stopping(JFrame frame, String cmd, Socket s, PrintWriter outWriter, Scanner bir) {
		this.frame = frame;
		this.cmd = cmd;
		this.s = s;
		this.outWriter = outWriter;
		this.bir = bir;
	}

	@Override
	public synchronized void run() {
		outWriter.println("stop");
		outWriter.flush();
//		notifyAll();
	}
}
