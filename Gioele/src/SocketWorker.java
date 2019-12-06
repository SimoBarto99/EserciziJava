import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SocketWorker implements Runnable{
	private CommandDispatcher sc;
	
	private static final String SERVER_IP = "127.0.0.1"; //Nella realt� questo dato andrebbe passato insieme al comando di connesione
	private static final int SERVER_PORT = 8080;
	
	public SocketWorker(CommandDispatcher sc) {
		this.sc = sc;
	}
	
	private void log(String msg) {
		System.out.println("[Worker] " + msg);
	}
	
	private CommandDispatcher.Command getPendingCommand() {
		synchronized(sc) {
			while(!sc.isPendingCommand()) {
				try {
					sc.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return CommandDispatcher.Command.NULL;
					
				}
			}
		}
		return sc.getPendingCommand();
	}
	
	@Override
	public void run() {
		this.log("Worker thread inizializzato, attendo il comando di connesione");
		while(this.getPendingCommand() != CommandDispatcher.Command.CONNECT);
		this.log("Comando ricevuto, apro la connesione con il server");
		try {
			Socket socket = new Socket(SERVER_IP,SERVER_PORT);
			socket.setSoTimeout(500);
			/*Scanner inputScanner = new Scanner(socket.getInputStream());
			PrintWriter outWriter = new PrintWriter(socket.getOutputStream());*/
			BufferedReader bir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter  outWriter = new PrintWriter(socket.getOutputStream(),true);
			
			this.log("Connesione stabilita, attendo per il comando di inizio");
			sc.comply();
			while(true) {
				CommandDispatcher.Command cmd = this.getPendingCommand();
				if(cmd == CommandDispatcher.Command.DISCONNECT) {
					this.log("Ricevuto comando disconnect, mi disconnetto dal server");
					break;
				}
				if(cmd == CommandDispatcher.Command.START) {
					this.log("Comando ricevuto, inizio l'input");
					outWriter.println("start");
					sc.comply();
					while(!sc.isPendingCommand() && sc.getPendingCommand() != CommandDispatcher.Command.STOP) {
						try {
							String in = bir.readLine();
							this.log("Dati ricevuti: " + in);
						}catch(SocketTimeoutException ex) {
							this.log("Socket event handler");
						}
						
					}
					sc.comply();
					this.log("Ricevuto comando stop, inoltro al server");
					outWriter.println("stop");
				}else {
					this.log("Ricevuto comando non consentito!");
				}
			}
			
			bir.close();
			outWriter.close();
			socket.close();
			sc.comply();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}