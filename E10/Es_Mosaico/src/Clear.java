import java.awt.Color;
import java.net.Socket;

import javax.swing.JPanel;

public class Clear implements Runnable {
	Socket s;
	String cmd;
	JPanel[] griglia;
	public Clear(String cmd, Socket s, JPanel[] griglia) {
		this.griglia = griglia;
		this.s = s;
		this.cmd = cmd;
	}
	@Override
	public void run() {
			for(int i = 0; i < griglia.length; i++) {
				griglia[i].setBackground(Color.lightGray);
			}
	}

}
