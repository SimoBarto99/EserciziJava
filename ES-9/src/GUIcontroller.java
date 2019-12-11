import javax.swing.JButton;
import javax.swing.JFrame;

public class GUIcontroller implements Runnable {
	JFrame f;
	String cmd;
	JButton connect;
	JButton start;
	JButton stop;
	JButton disconnect;
	
	public GUIcontroller(JFrame f, String cmd, JButton connect, JButton start, JButton stop, JButton disconnect) {
		this.f = f;
		this.cmd = cmd;
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
	}
	
	@Override
	public void run() {
		if(cmd == "Connect") {
			connect.setEnabled(false);
			start.setEnabled(true);
			disconnect.setEnabled(true);
			stop.setEnabled(false);
			cmd = null;
		}
		if (cmd == "Start") {
			connect.setEnabled(false);
			start.setEnabled(false);
			stop.setEnabled(true);
			disconnect.setEnabled(false);
			cmd = null;
		}
		if(cmd == "Stop") {
			connect.setEnabled(false);
			start.setEnabled(true);
			stop.setEnabled(false);
			disconnect.setEnabled(true);
			cmd = null;
		}
		if(cmd == "Disconnect") {
			connect.setEnabled(true);
			start.setEnabled(false);
			stop.setEnabled(false);
			disconnect.setEnabled(false);
			cmd = null;
		}
	}

}
