import java.io.PrintWriter;

public class Targaryen implements Runnable {
	PrintWriter p;
	
	public Targaryen(PrintWriter p) {
		this.p = p;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		p.println("stark");
		p.flush();
	}

}
