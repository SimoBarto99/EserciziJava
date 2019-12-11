import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientThread implements Runnable {

	private Socket sock;
	private boolean fired = false;
	private SenderThread st = null;
	private boolean running = false;
	Server parent;

	public ClientThread(Socket s, Server parent) {
		sock = s;
		this.parent = parent;
	}

	@Override
	public void run() {
		if (fired)
			return;
		fired = true;
		running = true;
		Scanner in = null;
		PrintWriter pw = null;
		try {
			in = new Scanner(sock.getInputStream());
			pw = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			parent.removeFromCTList(this);
			e.printStackTrace();
		}
		try {
			while (running) {
				String cmd = in.nextLine();
				System.out.println("Ricevuto: " + cmd);
				if (cmd.equals("download")) {
					st = new SenderThread(pw, parent);
					Thread t = new Thread(st);
					t.start();
				} else if (cmd.equals("interrompi")) {
					st.stop();
				} else if (cmd.equals("stark") || cmd.equals("targaryen") || cmd.equals("lannister")) {
					st.setFile(cmd);
				} else {
					parent.removeFromCTList(this);
					running = false;
				}
			}
			sock.close();
			pw.close();
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			parent.removeFromCTList(this);
		} catch (NoSuchElementException e2) {
			e2.printStackTrace();
			parent.removeFromCTList(this);
		}
	}

	public void stop() throws IOException {
		if (st != null)
			st.stop();
		if (sock != null)
			sock.close();
		running = false;
	}

	public boolean isRunning() {
		return running;
	}
}