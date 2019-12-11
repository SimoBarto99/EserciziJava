import java.io.PrintWriter;

public class Stark implements Runnable{
	PrintWriter p;
	
	public Stark(PrintWriter p) {
		this.p = p;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		p.println("stark");
		p.flush();
	}

}
