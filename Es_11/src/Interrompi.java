import java.io.PrintWriter;

public class Interrompi implements Runnable {
	PrintWriter p;
	
	public Interrompi(PrintWriter p) {
		this.p = p;
	}
	@Override
	public void run() {
		p.println("interrompi");
		p.flush();
		// TODO Auto-generated method stub
		
	}

}
