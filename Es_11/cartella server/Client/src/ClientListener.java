import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientListener implements ActionListener {

	public static final String START = "download", STOP = "interrompi", CONNECT = "connect", DISCONNECT = "disconnect",
			STARK = "stark", TARGARYEN = "targaryen", LANNISTER = "lannister";

	private JTextField ipAddressField;
	private JTextField portaField;

	private boolean connected = false, transmitting = false;
	private Downloader downloader = null;

	private PrintWriter netPw;
	private Scanner scan;
	private Socket sock;
	private StatusDownloaderFrame frame;
	private JTextArea starkArea;
	private JTextArea targaryenArea;
	private JTextArea lannisterArea;

	public ClientListener(JTextField ipAddr, JTextField porta, JTextArea starkArea, JTextArea targaryenArea, JTextArea lannisterArea) {
		this.ipAddressField = ipAddr;
		this.portaField = porta;
		this.starkArea = starkArea;
		this.targaryenArea = targaryenArea;
		this.lannisterArea = lannisterArea;

	}

	private void setupConnection() throws UnknownHostException, IOException {
		sock = new Socket(ipAddressField.getText(), Integer.parseInt(portaField.getText()));
		OutputStream os = sock.getOutputStream();
		netPw = new PrintWriter(new OutputStreamWriter(os));
		scan = new Scanner(sock.getInputStream());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (frame == null)
			frame = (StatusDownloaderFrame) SwingUtilities.getRoot((JButton) e.getSource());

		String cmd = e.getActionCommand();

		if (cmd.equals(ClientListener.CONNECT)) {
			try {
				setupConnection();
				connected = true;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile connettersi al server: \n" + e1.getMessage(),
						"Errore Server", JOptionPane.ERROR_MESSAGE);
				// e1.printStackTrace();
				return;
			}

			JOptionPane.showMessageDialog(null, "Connessione stabilita");
		} else if (cmd.equals(ClientListener.START)) {
			downloader = new Downloader(scan, starkArea, targaryenArea, lannisterArea, this);
			transmitting = true;
			netPw.println(cmd);
			netPw.flush();
			Thread t = new Thread(downloader);
			t.start();
			JOptionPane.showMessageDialog(null, "Download avviato");
		} else if (cmd.equals(ClientListener.STOP)) {
			netPw.println(cmd);
			netPw.flush();
			transmitting = false;
			JOptionPane.showMessageDialog(null, "Download fermato");
		} else if (cmd.equals(ClientListener.STARK) || cmd.equals(ClientListener.TARGARYEN) || cmd.equals(ClientListener.LANNISTER)) {
			netPw.println(cmd);
			netPw.flush();
		}
		else if (cmd.equals(ClientListener.DISCONNECT)) {
			netPw.println(cmd);
			netPw.flush();
			netPw.close();
			scan.close();
			starkArea.setText("");
			targaryenArea.setText("");
			lannisterArea.setText("");
			connected = false;
			try {
				sock.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Connessione chiusa");
		}
		frame.setButtons(connected, transmitting);
	}

	protected void setFrameButtons(boolean connected, boolean transmitting) {
		this.connected = connected;
		this.transmitting = transmitting;
		frame.setButtons(connected, transmitting);
	}
}
