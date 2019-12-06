import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JPanel;

public class Start implements Runnable {
	Socket s;
	String cmd;
	JPanel[] griglia;
	public Start(String cmd, Socket s, JPanel griglia[]) {
		this.cmd = cmd;
		this.s = s;
		this.griglia = griglia;
	}
	
	
	@Override
	public void run() {
		try {
			PrintWriter bir = new PrintWriter(s.getOutputStream());
			Scanner scan = new Scanner(s.getInputStream());
			bir.println("start");
			bir.flush();
			String data;
			int c = 0;
			int pos = 0;
			
			for(int i = 0; i < griglia.length; i++) {
				griglia[i].setBackground(Color.lightGray);
			}
			
			while(scan.hasNextLine()) {
				data = scan.nextLine();
				String boh[] = data.split(";");
				pos = Integer.parseInt(boh[0]);
				c = Integer.parseInt(boh[1]);
				Color col = new Color(c);
				if(c != -1 && pos !=-1)
				griglia[pos].setBackground(col);
				System.out.println("colore" + c + "posizione" + pos);
				if(c == -1 && pos == -1)
					break;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
