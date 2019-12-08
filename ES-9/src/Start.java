import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Start implements Runnable {
	Socket s;
	PrintWriter in;
	Scanner out;
	public Start(Socket s, PrintWriter in, Scanner out) {
		this.s = s;
		this.out = out;
		this.in = in;
	}
	@Override
	public void run() {
		try {
			String data;
			in = new PrintWriter(s.getOutputStream());
			in.println("start");
			in.flush();
			out = new Scanner(s.getInputStream());
			while(out.hasNextLine()) {
				data = out.nextLine();
				System.out.println(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public PrintWriter getPrint() {
		return in;
	}

}
