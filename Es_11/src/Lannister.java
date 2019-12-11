import java.io.PrintWriter;

public class Lannister implements Runnable {
	PrintWriter p;
	
	public Lannister(PrintWriter p) {
		this.p = p;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		p.println("lannister");
		p.flush();
	}

}
