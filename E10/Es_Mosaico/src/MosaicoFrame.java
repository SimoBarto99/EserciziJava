import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

import javax.swing.*;

public class MosaicoFrame extends JFrame implements ActionListener {
	Connect c = null;
	public static final String titolo = "Simone Bartolini 1842781"; 
	protected static String cmd = null;
	protected Socket s;
	protected static final String CONNECT = "Connect";
	protected static final String START = "Start";
	protected static final String STOP = "Stop";
	protected static final String DISCONNECT = "Disconnect";
	protected static final String CLEAR = "Claear";
	protected static enum stato {CONNECT, START, STOP, DISCONNECT, CLEAR};
	stato currentstate = null;
	public MosaicoFrame() {
		super(titolo);
		JFrame tot  = new JFrame();
		JPanel data = new JPanel(new GridLayout(1,4));
		JPanel mosaico = new JPanel(new GridLayout(16, 16));
		JPanel bottoni = new JPanel();
		JTextField ipAddres = new JTextField();
		JTextField porta = new JTextField();
		JButton start = new JButton("Start");
		start.setActionCommand(START);
		start.addActionListener(this);
		
		JButton stop = new JButton("Stop");
		stop.setActionCommand(STOP);
		stop.addActionListener(this);
		
		JButton connect = new JButton("Connect");
		connect.setActionCommand(CONNECT);
		connect.addActionListener(this);
		
		JButton disconnect = new JButton("Disconnect");
		disconnect.setActionCommand(DISCONNECT);
		disconnect.addActionListener(this);
		
		JButton clear = new JButton("Clear");
		clear.setActionCommand(CLEAR);
		clear.addActionListener(this);
		
		for(int i = 0; i < 16; i++) {
			mosaico.add(new JPanel());
		}
		
		data.add(start);
		data.add(ipAddres);
		data.add(porta);
		data.add(stop);
		
		bottoni.add(connect);
		bottoni.add(clear);
		bottoni.add(disconnect);
		
		Container nord = tot.getContentPane();
		nord.add(data, BorderLayout.NORTH);
		nord.add(mosaico, BorderLayout.CENTER);
		nord.add(bottoni, BorderLayout.SOUTH);
		
		Container fin = this.getContentPane();
		fin.add(nord);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
		case(CLEAR):
			cmd = CLEAR;
			onClear();
			break;
		}
	}	
	public void onConnect() {
		//		System.out.println(cmd);
		c = new Connect(cmd, s);
		Thread t1 = new Thread(c);
		t1.start();
	}
	public void onStart() {
//		System.out.println(cmd);
		Thread t2 = new Thread(new Start(cmd, this.c.getS()));
		t2.start();
	}
	public void onStop() {
//		System.out.println(cmd);
		Thread t3 = new Thread(new Stop(cmd, this.c.getS()));
		t3.start();
	}
	public void onDisconnect() {
//		System.out.println(cmd);
		Thread t4 = new Thread(new Disconnect(cmd, this.c.getS()));
		t4.start();
	}
	public void onClear() {
		System.out.println(cmd);
	}
}
