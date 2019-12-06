import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Starting extends Frame implements Runnable {
	JFrame frame;
	String cmd;
	Socket s;
	PrintWriter outWriter;
	Scanner bir;
	JButton sta;
	JButton disc;
	
	public Starting(JFrame frame, String cmd, Socket s, PrintWriter outWriter, Scanner bir) {
		this.frame = frame;
		this.cmd = cmd;
		this.s = s;
		this.bir = bir;
		this.outWriter = outWriter;
	}
	@Override
	public synchronized void run() {
		while(cmd != START) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("inizio la ricezione");
		outWriter.println("start");
		outWriter.flush();
		Start.setEnabled(false);
		while(cmd != STOP) {
			if(bir.hasNextLine()) {
			data = bir.nextLine();
			System.out.println(data);
			}
		}
		bir.close();
		notifyAll();
	}
	
}
