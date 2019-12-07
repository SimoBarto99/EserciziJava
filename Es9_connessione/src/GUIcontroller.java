import javax.swing.*;

public class GUIcontroller implements Runnable {
	Frame f;
	JButton connect, start, stop, disconnect;
	String cmd;
	
	public GUIcontroller(String cmd, Frame f, JButton connect, JButton start, JButton stop, JButton disconnect) {
		this.cmd = cmd;
		this.f = f;
		this.connect = connect;
		this.start = start;
		this.stop = stop;
		this.disconnect = disconnect;
	}
	
	@Override
	public synchronized void run() {
		while(cmd == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(cmd == "Connect") {
			connect.setEnabled(false);
			start.setEnabled(true);
			disconnect.setEnabled(true);
			stop.setEnabled(false);
			cmd = null;
		}
		if(cmd == "Start") {
			start.setEnabled(false);
			stop.setEnabled(true);
			connect.setEnabled(false);
			disconnect.setEnabled(false);
			cmd = null;
		}
		if(cmd == "Stop") {
			stop.setEnabled(false);
			start.setEnabled(true);
			disconnect.setEnabled(true);
			connect.setEnabled(false);
			cmd = null;
		}
		if(cmd == "Disconnect") {
			disconnect.setEnabled(false);
			connect.setEnabled(true);
			stop.setEnabled(false);
			start.setEnabled(false);
			cmd = null;
		}
	}
	
}
