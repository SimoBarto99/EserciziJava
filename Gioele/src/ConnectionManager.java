public class ConnectionManager implements UIActionListener{
	CommandDispatcher sc;
	Thread worker;
	
	private void Log(String msg) {
		System.out.println("[CONNECTION MANAGER] " + msg);
	}
	
	public ConnectionManager() {
		UI GUI = new UI(this);
		GUI.show();
		
		sc = new CommandDispatcher();
		worker = new Thread(new SocketWorker(sc));
		this.Log("Avvio il thread");
		worker.start();
	}

	@Override
	public void onConnect() {
		this.Log("Ricevuto comando connect, inoltro al worker");
		sc.sendCommand(CommandDispatcher.Command.CONNECT);
	}

	@Override
	public void onStart() {
		this.Log("Ricevuto comando start, inoltro al worker");
		sc.sendCommand(CommandDispatcher.Command.START);
		
	}

	@Override
	public void onStop() {
		this.Log("Ricevuto comando stop, inoltro al worker");
		sc.sendCommand(CommandDispatcher.Command.STOP);
		
	}

	@Override
	public void onDisconnect() {
		this.Log("Ricevuto comando disconnect, inoltro al worker");
		sc.sendCommand(CommandDispatcher.Command.DISCONNECT);
		
	}
	

}