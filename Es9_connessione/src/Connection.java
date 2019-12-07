import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connection extends Frame implements Runnable{
	JFrame frame;
	String cmd;
	Socket s;
	public Connection(JFrame frame, String cmd, Socket s){
		this.frame = frame;
		this.cmd = cmd;
		this.s = s;
	}
	
	public synchronized void run() {
//		while(cmd != CONNECT) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		try {
			s = new Socket("127.0.0.1", 8080);
			outWriter = new PrintWriter(s.getOutputStream());
			bir = new Scanner(new InputStreamReader(s.getInputStream()));
			System.out.println("AHO connesso");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
