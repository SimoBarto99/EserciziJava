import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MyFrame extends JFrame implements ActionListener {
	Connect c;
	protected Socket s;
	protected JFrame f;
	protected String cmd = null;
	protected final static String CONNECT = "connect";
	protected final static String START = "start";
	protected final static String STOP = "stop";
	protected final static String DISCONNECT = "disconnect";
	protected JPanel data, bottoni;
	protected JButton connect, start, stop, disconnect;
	protected JTextField ip, porta, matricola;
	
	public MyFrame() {
		f = new JFrame();
		data = new JPanel(new GridLayout(2,2));
		bottoni = new JPanel();
		ip = new JTextField();
		porta = new JTextField();
		matricola = new JTextField();
		
		connect = new JButton("connect");
		connect.setActionCommand(CONNECT);
		connect.addActionListener(this);
		
		start = new JButton("start");
		start.setActionCommand(START);
		start.addActionListener(this);
		
		stop = new JButton("stop");
		stop.setActionCommand(STOP);
		stop.addActionListener(this);
		
		disconnect = new JButton("disconnect");
		disconnect.setActionCommand(DISCONNECT);
		disconnect.addActionListener(this);
		
		data.add(ip);
		data.add(porta);
		data.add(matricola);
		
		bottoni.add(connect);
		bottoni.add(start);
		bottoni.add(stop);
		bottoni.add(disconnect);
		
		Container frame = this.getContentPane();
		frame.add(data, BorderLayout.NORTH);
		frame.add(bottoni, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(cmd == null) {
			switch(e.getActionCommand()) {
			case(CONNECT):
				cmd = CONNECT;
				onConnect();
				break;
			case(START):
				cmd = START;
				onStart();
				break;
			case(STOP):
				cmd = STOP;
				onStop();
				break;
			case(DISCONNECT):
				cmd = DISCONNECT;
				onDisconnect();
				break;
			}
		}
	}
	
	public void onConnect() {
//		System.out.println("connect");
		Thread t1 = new Thread(new Connect(cmd, ip, porta, s));
		t1.start();
		cmd = null;
	}
	
	public void onStart() {
		System.out.println("start");
		cmd = null;
	}
	
	public void onStop() {
		System.out.println("stop");
		cmd = null;
	}
	
	public void onDisconnect() {
		System.out.println("disconnect");
		cmd = null;
	}

}
