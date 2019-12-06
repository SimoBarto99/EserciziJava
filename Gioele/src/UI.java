import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class UI implements ActionListener{
	private JFrame frame;
	private JButton connect, start,stop,disconnect;
	private UIActionListener lst;
	
	private static final String CONNECT_CMD = "Connect";
	private static final String START_CMD = "Start";
	private static final String STOP_CMD = "Stop";
	private static final String DISCONNECT_CMD = "Disconnect";
	public UI() {
		frame = new JFrame("AHO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,500);
		frame.setLayout(new FlowLayout());
		
		connect = new JButton("Connect");
		connect.setActionCommand(CONNECT_CMD);
		connect.addActionListener(this);
		frame.add(connect);
		
		start = new JButton("Start");
		start.setActionCommand(START_CMD);
		start.addActionListener(this);
		frame.add(start);
		
		stop = new JButton("Stop");
		stop.setActionCommand(STOP_CMD);
		stop.addActionListener(this);
		frame.add(stop);
		
		disconnect = new JButton("Disconnect");
		disconnect.setActionCommand(DISCONNECT_CMD);
		disconnect.addActionListener(this);
		frame.add(disconnect);
	}
	public UI(UIActionListener el) {
		this();
		this.lst = el;
		
	}
	
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(lst != null) {
			switch(event.getActionCommand()) {
				case(CONNECT_CMD):
					lst.onConnect();
					break;
				case(START_CMD):
					lst.onStart();
					break;
				case(STOP_CMD):
					lst.onStop();
					break;
				case(DISCONNECT_CMD):
					lst.onDisconnect();
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		
	}
}