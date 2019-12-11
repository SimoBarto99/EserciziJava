import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Download implements Runnable{
	Socket s;
	String data;
	PrintWriter p;
	Scanner scan;
	JTextArea lannister, stark, targaryen;
	public Download(Socket s, PrintWriter p, Scanner scan, JTextArea stark, JTextArea targaryen, JTextArea lannister) {
		this.s = s;
		this.p = p;
		this.scan = scan;
		this.lannister = lannister;
		this.stark = stark;
		this.targaryen = targaryen;
	}
	@Override
	public void run() {
		try {
			String[] nomi; 
			String nome, cognome, casata, grado;
			System.out.println("inizio il download: ");
			p = new PrintWriter(s.getOutputStream());
			p.println("download");
			p.flush();
			scan = new Scanner(s.getInputStream());
			while(scan.hasNextLine()) {
					data = scan.nextLine();
					System.out.println(data);
					if(!data.equals("end")){
						nomi = data.split(",");
						nome = nomi[0];
						cognome = nomi[1];
						casata = nomi[2];
						grado = nomi[3];
						if(casata.equals("STARK"))
							stark.setText(data);
						if(casata.equals("LANNISTER"))
							lannister.setText(data);
						if(casata.equals("TARGARYEN"))
							targaryen.setText(data);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PrintWriter getD() {
		return p;
	}

}
