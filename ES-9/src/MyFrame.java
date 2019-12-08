import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener {
	Connect c = null;
	Start printer = null;
	protected Socket s;
	protected PrintWriter out;
	protected Scanner in;
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
		c = new Connect(cmd, ip, porta, s);
		Thread t1 = new Thread(c);
		t1.start();
		cmd = null;
	}
	
	public void onStart() {
//		System.out.println("start");
		printer = new Start(this.c.getS(), out, in);
		Thread t2 = new Thread(printer);
		t2.start();
		cmd = null;
	}
	
	public void onStop() {
//		System.out.println("stop");
		Thread t3 = new Thread(new Stop(this.c.getS(), this.printer.getPrint()));
		t3.start();
		cmd = null;
	}
	
	public void onDisconnect() {
//		System.out.println("disconnect");
		Thread t4 = new Thread(new Disconnect(this.c.getS(), this.printer.getPrint()));
		t4.start();
		cmd = null;
	}

}
