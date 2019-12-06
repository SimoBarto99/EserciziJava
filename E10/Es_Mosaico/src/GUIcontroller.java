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
		try {
			wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(cmd == "Connect") {
			connect.setEnabled(false);
			start.setEnabled(true);
			disconnect.setEnabled(true);
			cmd = null;
		}
		while(cmd == "Start") {
			stop.setEnabled(true);
			start.setEnabled(false);
			clear.setEnabled(true);
			cmd = null;
		}
		while(cmd == "Stop") {
			stop.setEnabled(false);
			start.setEnabled(true);
			cmd = null;
		}
		while(cmd == "Clear") {
			cmd = null;
		}
		while(cmd == "Disconnect") {
			disconnect.setEnabled(false);
			connect.setEnabled(true);
			cmd = null;
		}
		while(cmd == null)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
}
