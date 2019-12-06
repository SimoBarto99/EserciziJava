import javax.swing.*;

public class GUIcontroller implements Runnable {
	JFrame f;
	JButton connect, start, stop, clear, disconnect;
	String cmd;
	public GUIcontroller(JFrame f, String cmd, JButton connect, JButton start, JButton stop, JButton clear, JButton disconnect) {
		this.f = f;
		this.cmd = cmd;
		this.connect = connect;
		this.start = start;
		this.stop = stop;
		this.clear = clear;
		this.disconnect = disconnect;
	}
	@Override
	public synchronized void run() {
		while(cmd == null) {
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
		}
		if(cmd == "Connect") {
			connect.setEnabled(false);
			start.setEnabled(true);
			disconnect.setEnabled(true);
			cmd = null;
		}
		if(cmd == "Start") {
			stop.setEnabled(true);
			start.setEnabled(false);
			clear.setEnabled(false);
			disconnect.setEnabled(false);
			cmd = null;
		}
		if(cmd == "Stop") {
			stop.setEnabled(false);
			start.setEnabled(true);
			disconnect.setEnabled(true);
			clear.setEnabled(true);
			cmd = null;
		}
		if(cmd == "Clear") {
			cmd = null;
		}
		if(cmd == "Disconnect") {
			disconnect.setEnabled(false);
			connect.setEnabled(true);
			start.setEnabled(false);
			stop.setEnabled(false);
			clear.setEnabled(false);
			cmd = null;
		}
	}
	
}
