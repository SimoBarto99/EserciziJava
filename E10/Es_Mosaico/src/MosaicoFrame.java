import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

import javax.swing.*;

public class MosaicoFrame extends JFrame implements ActionListener {
	Connect c = null;
	public static final String titolo = "Simone Bartolini 1842781"; 
	protected static String cmd = null;
	protected Socket s;
	protected JButton connect, start, stop, disconnect, clear;
	protected JTextField ipAddres, porta;
	protected JPanel[]  griglia = new JPanel[256];
	protected static final String CONNECT = "Connect";
	protected static final String START = "Start";
	protected static final String STOP = "Stop";
	protected static final String DISCONNECT = "Disconnect";
	protected static final String CLEAR = "Claear";
	public MosaicoFrame() {
		super(titolo);
		JFrame tot  = new JFrame();
		JPanel data = new JPanel(new GridLayout(1,4));
		JPanel mosaico = new JPanel(new GridLayout(16, 16));
		JPanel bottoni = new JPanel();
		ipAddres = new JTextField();
		porta = new JTextField();
		
		JButton start = new JButton("Start");
//		start.setEnabled(false);
		start.setActionCommand(START);
		start.addActionListener(this);
		
		stop = new JButton("Stop");
//		stop.setEnabled(false);
		stop.setActionCommand(STOP);
		stop.addActionListener(this);
		
		connect = new JButton("Connect");
		connect.setActionCommand(CONNECT);
		connect.addActionListener(this);
		
		disconnect = new JButton("Disconnect");
//		disconnect.setEnabled(false);
		disconnect.setActionCommand(DISCONNECT);
		disconnect.addActionListener(this);
		
		clear = new JButton("Clear");
//		clear.setEnabled(false);
		clear.setActionCommand(CLEAR);
		clear.addActionListener(this);
		
		for(int i = 0; i < 256; i++) {
			JPanel a = new JPanel();
			griglia[i] = a;
			mosaico.add(griglia[i]);
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
	public synchronized void onConnect() {
		cmd = CONNECT;
		notify();
		Thread t = new Thread(new GUIcontroller(this, cmd, connect, start, stop, clear, disconnect));
		t.start();
		c = new Connect(this, cmd, s, ipAddres, porta);
		Thread t1 = new Thread(c);
		t1.start();
	}
	public synchronized void onStart() {
		cmd = START;
		notify();
		Thread t2 = new Thread(new Start(cmd, this.c.getS(), griglia));
		t2.start();
	}
	public synchronized void onStop() {
		cmd = STOP;
		notify();
		Thread t3 = new Thread(new Stop(cmd, this.c.getS()));
		t3.start();
	}
	public synchronized void onDisconnect() {
		cmd = DISCONNECT;
		notify();
		Thread t4 = new Thread(new Disconnect(cmd, this.c.getS()));
		t4.start();
	}
	public synchronized void onClear() {
		cmd = CLEAR;
		notify();
		Thread t4 = new Thread(new Clear(cmd, s, griglia));
		t4.start();
		System.out.println(cmd);
	}
}
