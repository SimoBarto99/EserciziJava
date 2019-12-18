import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {
	Download d = null;
	Connect c = null;
	protected Socket s;
	protected PrintWriter p;
	protected Scanner scan;
	JFrame f;
	JPanel dati, casate, tabella, dow_inter, tot;
	JTextArea sta, tar, lan;
	JTextField ipaddres, porta;
	JButton connect, disconnect, download, interrompi, stark, lannister, targaryen;
	JLabel ip, por;
	static final String CONNECT = "connect";
	static final String DISCONNECT = "disconnect";
	static final String DOWNLOAD = "download";
	static final String INTERRUPT = "interrompi";
	static final String STARK = "stark";
	static final String LANNISTER = "lannister";
	static final String TARGARYEN = "targaryen";
	
	public MyFrame() {
		f = new JFrame();
		tot = new JPanel(new GridLayout(2,1));
		dati = new JPanel(new GridLayout(1, 6));
		tabella = new JPanel(new GridLayout(1, 3));
		casate = new JPanel(new GridLayout(1,3));
		dow_inter = new JPanel();
		sta = new JTextArea(20, 10);
		tar = new JTextArea(20, 10);
		lan = new JTextArea(20, 10);
		
		connect = new JButton("Connect");
		connect.setActionCommand(CONNECT);
		connect.addActionListener(this);
		
		disconnect = new JButton("Disconnect");
		disconnect.setActionCommand(DISCONNECT);
		disconnect.addActionListener(this);
		disconnect.setEnabled(false);
		
		download = new JButton("Download");
		download.setActionCommand(DOWNLOAD);
		download.addActionListener(this);
		download.setEnabled(false);
		
		interrompi = new JButton("Interrompi");
		interrompi.setActionCommand(INTERRUPT);
		interrompi.addActionListener(this);
		interrompi.setEnabled(false);
		
		stark = new JButton("Stark");
		stark.setActionCommand(STARK);
		stark.addActionListener(this);
		stark.setEnabled(false);
		
		lannister = new JButton("Lannister");
		lannister.setActionCommand(LANNISTER);
		lannister.addActionListener(this);
		lannister.setEnabled(false);
		
		targaryen = new JButton("Targaryen");
		targaryen.setActionCommand(TARGARYEN);
		targaryen.addActionListener(this);
		targaryen.setEnabled(false);
		
		ip = new JLabel("IPaddres");
		por = new JLabel("Porta");
		ipaddres = new JTextField();
		porta = new JTextField();
		
		dati.add(ip);		
		dati.add(ipaddres);
		dati.add(por);
		dati.add(porta);
		dati.add(connect);
		dati.add(disconnect);
		
		casate.add(stark);
		casate.add(targaryen);
		casate.add(lannister);
		tabella.add(sta);
		tabella.add(tar);
		tabella.add(lan);
		tot.add(casate);
		tot.add(tabella);
		
		dow_inter.add(download);
		dow_inter.add(interrompi);
		
		Container fin = this.getContentPane();
		fin.add(dati, BorderLayout.NORTH);
		fin.add(tot, BorderLayout.CENTER);
		fin.add(dow_inter, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case(CONNECT):
			download.setEnabled(true);
			stark.setEnabled(true);
			lannister.setEnabled(true);
			targaryen.setEnabled(true);
			onConnect();
			break;
		case(DISCONNECT):
			connect.setEnabled(true);
			disconnect.setEnabled(false);
			download.setEnabled(false);
			interrompi.setEnabled(false);
			onDisconnect();
			break;
		case(DOWNLOAD):
			download.setEnabled(false);
			interrompi.setEnabled(true);
			disconnect.setEnabled(false);
			onDownload();
			break;
		case(INTERRUPT):
			interrompi.setEnabled(false);
			disconnect.setEnabled(true);
			stark.setEnabled(false);
			lannister.setEnabled(false);
			targaryen.setEnabled(false);
			onInterrompi();
			break;
		case(STARK):
			onStark();
			break;
		case(LANNISTER):
			onLannister();
			break;
		case(TARGARYEN):
			onTargaryen();
			break;
		}
	}
	
	public void onConnect() {
		c = new Connect(s, ipaddres.getText(), Integer.parseInt(porta.getText()));
		Thread t = new Thread(c);
		t.start();
	}
	
	public void onDownload() {
		d = new Download(this.c.getS(), p, scan, sta, tar, lan);
		
		Thread t = new Thread(d);
		t.start();
	}
	
	public void onInterrompi() {
		Thread t = new Thread(new Interrompi(this.d.getD()));
		t.start();
	}
	
	public void onDisconnect() {
		Thread t = new Thread(new Disconnect(this.c.getS(), this.d.getD()));
		t.start();
	}
	
	public void onStark() {
		Thread t = new Thread(new Stark(this.d.getD()));
		t.start();
	}
	
	public void onLannister() {
		
	}
	
	public void onTargaryen() {
		
	}
}
