import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Downloader implements Runnable {
	private Scanner scan;
	private boolean running;
	private JTextArea starkArea;
	private JTextArea targaryenArea;
	private JTextArea lannisterArea;
	private ClientListener cl;
	private SortedSet<Character> starkSortedSet;
	private SortedSet<Character> targaryenSortedSet;
	private SortedSet<Character> lannisterSortedSet;

	public Downloader(Scanner scan, JTextArea starkArea, JTextArea targaryenArea, JTextArea lannisterArea, ClientListener cl) {
		this.starkArea = starkArea;
		this.targaryenArea = targaryenArea;
		this.lannisterArea = lannisterArea;
		this.cl = cl;
		this.scan = scan;
		running = false;
		starkSortedSet = new TreeSet<Character>();
		targaryenSortedSet = new TreeSet<Character>();
		lannisterSortedSet = new TreeSet<Character>();
	}

	@Override
	public void run() {
		if (!running) {
			running = true;
			String header = "";
			while (running) {
				try {
					String cmd = scan.nextLine();
					System.out.println(cmd);
					if (cmd.equals("end")) {
						running = false;
						continue;
					}
					String[] elems = cmd.split(",");
					Character p = new Character(elems[0], elems[1], elems[2], elems[3]);
					JTextArea tArea = null;
					SortedSet<Character> sortedSet = null;
					if (p.getCasata().equals("STARK")) {
						tArea = starkArea;
						sortedSet = starkSortedSet;
					} else if(p.getCasata().equals("TARGARYEN")){
						tArea = targaryenArea;
						sortedSet = targaryenSortedSet;
					} else {
						tArea = lannisterArea;
						sortedSet = lannisterSortedSet;
					}
					String text = header;
					if (!sortedSet.add(p)) {
						text = tArea.getText() + "\n DOPPIONE RICEVUTO";
					} else {
						for (Character character : sortedSet) {
							text = text + character + "\n";
						}
					}
					tArea.setText(text);
				} catch (NoSuchElementException e) {
					starkArea.setText("");
					targaryenArea.setText("");
					lannisterArea.setText("");
					JOptionPane.showMessageDialog(starkArea, "Comunicazione interrotta dal server", "Errore Server",
							JOptionPane.WARNING_MESSAGE);
					cl.setFrameButtons(false, false);
					running = false;
					return;
				}
			}
			cl.setFrameButtons(true, false);
		}
	}

	public boolean isRunning() {
		return running;
	}

}
