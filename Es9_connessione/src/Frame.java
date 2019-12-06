import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	protected static Socket s;
	protected static Scanner bir;
	protected static PrintWriter  outWriter;
	protected static String data = null;
	
	private static JFrame frame;
	private static JLabel Matricola, IPaddres, porta;
	protected static JTextField matricola, ip, po; 
	private static JPanel dati_ippo, dati_matricola, bottoni;
	protected static JButton Connect, Start, Stop, Disconnect;
	protected static final String CONNECT = "Connect";
	protected static final String START = "Start";
	protected static final String STOP = "Stop";
	protected static final String DISCONNECT = "Disconnect";
	
	protected static String cmd = CONNECT;
	
	public Frame() {
		frame = new JFrame("AHO");
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dati_ippo = new JPanel(new GridLayout(1, 4)); 
		dati_matricola = new JPanel(new GridLayout(1, 2));
		bottoni = new JPanel();
		
		Matricola = new JLabel("Matricola");
		dati_matricola.add(Matricola);
		matricola = new JTextField();
		dati_matricola.add(matricola);
		IPaddres = new JLabel("IPaddres");
		dati_ippo.add(IPaddres);
		ip = new JTextField();
		dati_ippo.add(ip);
		porta = new JLabel("Porta");
		dati_ippo.add(porta);
		po = new JTextField();
		dati_ippo.add(po);
		
		Connect = new JButton("Connect");
		Connect.setActionCommand(CONNECT);
		Connect.addActionListener(this);
		
		Start = new JButton("Start");
		Start.setActionCommand(START);
		Start.addActionListener(this);
		
		Stop = new JButton("Stop");
		Stop.setActionCommand(STOP);
		Stop.addActionListener(this);
		
		Disconnect = new JButton("Disconnect");
		Disconnect.setActionCommand(DISCONNECT);
		Disconnect.addActionListener(this);
		
		bottoni.add(Connect);
		bottoni.add(Start);
		bottoni.add(Stop);
		bottoni.add(Disconnect);
		
		
		Container myframe = this.getContentPane();
		myframe.add(dati_ippo, BorderLayout.NORTH);
		myframe.add(dati_matricola, BorderLayout.CENTER);
		myframe.add(bottoni, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
		if(cmd != null) {
			switch(e.getActionCommand()) {
			case(CONNECT):
				onConnect();
				break;
			case(START):
				onStart();
				break;
			case(STOP):
				onStop();
				break;
			case(DISCONNECT):
				onDisconnect();
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}
	}
	public synchronized void onConnect() {
		cmd = CONNECT;
		Thread t1 = new Thread(new Connection(this, cmd, s));
		t1.start();
	}
	public synchronized void onStart() {
		cmd = START;
		Stop.setEnabled(true);
		Thread t2 = new Thread(new Starting(this, cmd, s, outWriter, bir));
		t2.start();
	}
	public synchronized void onStop() {
		cmd = STOP;
		Thread t3 = new Thread(new Stopping(this, cmd, s, outWriter, bir));
		t3.start();
	}
	public synchronized void onDisconnect() {
		cmd = DISCONNECT;
		Thread t4 = new Thread(new Disconnecting(this, cmd, s, outWriter));
		t4.start();
	}
}