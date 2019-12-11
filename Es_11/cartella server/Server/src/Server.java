import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Server implements Runnable {

	private ServerSocket lis = null;
	private String port;
	private List<ClientThread> ctList;
	private List<String> l1;
	private List<String> l2;
	private List<String> l3;
	private JLabel clientsLabel;

	public Server() {
		port = "4400";
		ctList = new LinkedList<ClientThread>();
		try {
			l1 = leggiFile("STARK.csv");
			l2 = leggiFile("TARGARYEN.csv");
			l3 = leggiFile("LANNISTER.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Server(String text, JLabel clientsLabel) {
		port = text;
		ctList = new ArrayList<ClientThread>();
		try {
			l1 = leggiFile("STARK.csv");
			l2 = leggiFile("TARGARYEN.csv");
			l3 = leggiFile("LANNISTER.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.clientsLabel = clientsLabel;
	}

	public void run() {

		try {
			lis = new ServerSocket(Integer.parseInt(port));
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore nella creazione del ServerSocket, applicazione dismessa", null,
					0);
			System.exit(1);
		}
		System.out.println("Server Avviato");
		Socket sock = null;

		while (true) {
			try {
				sock = lis.accept();
			} catch (IOException e) {
				break;
			}
			System.out.println("Socket creata, connessione accettata");
			ClientThread cl = new ClientThread(sock, this);
			Thread tr = new Thread(cl);
			tr.start();
			ctList.add(cl);
			clientsLabel.setText("" + ctList.size());
		}
	}

	public void stop() throws IOException {
		lis.close();
		for (ClientThread clientThread : ctList) {
			clientThread.stop();
		}
		ctList = new LinkedList<ClientThread>();
	}

	private List<String> leggiFile(String fileName) throws IOException {
		File f = new File(fileName);
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(f));
		List<String> res = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			res.add(line);
		}
		br.close();
		//System.err.println(res.size());
		return res;
	}

	public void removeFromCTList(ClientThread ct) {
		synchronized (ctList) {
			ctList.remove(ct);
			clientsLabel.setText("" + ctList.size());
		}
	}

	public synchronized String extract(String dBid) {
		List<String> db = l1;
		if (dBid.equals("stark")) {
			db = l1;
		}else if (dBid.equals("targaryen")) {
			db = l2;
		}else
			db = l3;
		if (db.size() == 0)
			return "";
		double d = Math.random();
		int index = (int) (d * (db.size()));
		return db.get(index);
	}
}
