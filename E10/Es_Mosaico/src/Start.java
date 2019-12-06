import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Start implements Runnable {
	Socket s;
	String cmd;
	public Start(String cmd, Socket s) {
		this.cmd = cmd;
		this.s = s;
	}
	
	
	@Override
	public void run() {
		try {
			System.out.println("ao ");
			PrintWriter bir = new PrintWriter(s.getOutputStream());
			Scanner scan = new Scanner(s.getInputStream());
			bir.println("start");
			bir.flush();
			String data;
			String c = null;
			String pos = null;
			while(c != "-1" && pos != "-1") {
			data = scan.nextLine();
			String boh[] = data.split(";");
			c = boh[0];
			pos = boh[1];
			System.out.println("colore" + c + "posizione" + pos);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
