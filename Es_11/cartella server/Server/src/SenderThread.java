import java.io.PrintWriter;

public class SenderThread implements Runnable {

	private PrintWriter pw;
	private boolean flag;
	private String file;
	private Server serv;

	public SenderThread(PrintWriter pw, Server serv) {
		flag = false;
		this.pw = pw;
		this.serv = serv;
	}

	@Override
	public void run() {
		flag = true;
		double d = Math.random();
		int i = (int) (d * (3));
		if(i==0) 
			file = "stark";
		else if(i==1)
			file = "targaryen";
		else if(i==2)
			file = "lannister";
		else
			file = "stark";
		//System.out.println(i);
		while (flag) {
			
			String toSend = serv.extract(file);
			pw.println(toSend);
			pw.flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		flag = false;
		pw.println("end");
		pw.flush();

	}

	public void stop() {
		flag = false;
	}

	public void setFile(String cmd) {
		file = cmd;
	}

}
