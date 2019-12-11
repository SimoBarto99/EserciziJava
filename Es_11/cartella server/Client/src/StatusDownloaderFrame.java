import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class StatusDownloaderFrame extends JFrame {

	private JPanel north;
	private JTextField addressText;
	private JTextField portaText;
	private JLabel msgLabel;
	private JPanel addressPanel;
	private JPanel portPanel;
	private JPanel southpanel;
	private JPanel innerPanel;
	
	private JButton connectBtn;
	private JButton disconnectBtn;
	private JButton startBtn;
	private JScrollPane centralScrollPane;
	private JButton stopBtn;
	
	private JTextArea starkArea;
	private JTextArea targaryenArea;
	private JTextArea lannisterArea;
	private JButton file1Btn;
	private JButton file2Btn;
	private JButton file3Btn;
	
	private JPanel centralPanel;

	public StatusDownloaderFrame() {

		Container mainContainer = this.getContentPane();
				
		north = new JPanel();
		
		addressPanel = new JPanel(new FlowLayout());
		portPanel = new JPanel(new FlowLayout());

		msgLabel = new JLabel("STOPPED");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Font f =  msgLabel.getFont();
		msgLabel.setFont(new Font(f.getName(), Font.BOLD, 30));
		
		addressPanel.add(new JLabel("IP Address"), BorderLayout.CENTER);
		addressText = new JTextField(10);
		addressText.setText("127.0.0.1");
		addressPanel.add(addressText, BorderLayout.SOUTH);

		portPanel.add(new JLabel("Port"), BorderLayout.CENTER);
		portaText = new JTextField(10);
		portaText.setText("4400");
		portPanel.add(new JPanel().add(portaText), BorderLayout.SOUTH);
		
		southpanel = new JPanel();
		
		starkArea = new JTextArea();
		starkArea.setEditable(false);
		starkArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		starkArea.setForeground(Color.DARK_GRAY);
		
		targaryenArea = new JTextArea();
		targaryenArea.setEditable(false);
		targaryenArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		targaryenArea.setForeground(Color.RED);
		
		lannisterArea = new JTextArea();
		lannisterArea.setEditable(false);
		lannisterArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lannisterArea.setForeground(Color.ORANGE);

		ActionListener list = new ClientListener(addressText, portaText, starkArea, targaryenArea, lannisterArea);

		connectBtn = new JButton("Connect");
		connectBtn.setActionCommand(ClientListener.CONNECT);
		connectBtn.addActionListener(list);
		disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setActionCommand(ClientListener.DISCONNECT);
		disconnectBtn.addActionListener(list);
		startBtn = new JButton("Download");
		startBtn.setActionCommand(ClientListener.START);
		startBtn.addActionListener(list);
		
		
		stopBtn = new JButton("Interrompi");
		stopBtn.setActionCommand(ClientListener.STOP);
		stopBtn.addActionListener(list);
		
		file1Btn = new JButton("STARK");
		file1Btn.setActionCommand("stark");
		file1Btn.addActionListener(list);
		file2Btn = new JButton("TARGARYEN");
		file2Btn.setActionCommand("targaryen");
		file2Btn.addActionListener(list);
		file3Btn = new JButton("LANNISTER");
		file3Btn.setActionCommand("lannister");
		file3Btn.addActionListener(list);
		
		north.add(addressPanel);
		north.add(portPanel);
		north.add(connectBtn);
		north.add(disconnectBtn);
		
		southpanel.add(startBtn);
		southpanel.add(stopBtn);
		
		JPanel starkPanel = new JPanel(new BorderLayout());
		JPanel targaryenPanel = new JPanel(new BorderLayout());
		JPanel lannisterPanel = new JPanel(new BorderLayout());
		starkPanel.add(file1Btn,BorderLayout.NORTH);
		starkPanel.add(starkArea,BorderLayout.CENTER);
		targaryenPanel.add(file2Btn,BorderLayout.NORTH);
		targaryenPanel.add(targaryenArea,BorderLayout.CENTER);
		lannisterPanel.add(file3Btn,BorderLayout.NORTH);
		lannisterPanel.add(lannisterArea,BorderLayout.CENTER);
		
		centralPanel = new JPanel(new BorderLayout());
		innerPanel = new JPanel(new GridLayout(1, 3));
		innerPanel.add(starkPanel);
		innerPanel.add(targaryenPanel);
		innerPanel.add(lannisterPanel);

		centralScrollPane = new JScrollPane(innerPanel);
		centralPanel.add(centralScrollPane,BorderLayout.CENTER);
		centralPanel.add(southpanel, BorderLayout.SOUTH);
		
		mainContainer.add(north, BorderLayout.NORTH);
		mainContainer.add(centralPanel, BorderLayout.CENTER);

		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setButtons(false, false);
		
		setTitle("Nome Cognome 1234567");
		
		this.setVisible(true);
	}

	public void setButtons(boolean connected, boolean transmitting) {
		if(connected){
			connectBtn.setEnabled(false);
			//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			if(transmitting){
				disconnectBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				startBtn.setEnabled(false);
				file1Btn.setEnabled(true);
				file2Btn.setEnabled(true);
				file3Btn.setEnabled(true);
			}else{
				stopBtn.setEnabled(false);
				startBtn.setEnabled(true);
				disconnectBtn.setEnabled(true);
				file1Btn.setEnabled(false);
				file2Btn.setEnabled(false);
				file3Btn.setEnabled(false);
			}		
		}else{
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			connectBtn.setEnabled(true);
			disconnectBtn.setEnabled(false);
			startBtn.setEnabled(false);
			file1Btn.setEnabled(false);
			file2Btn.setEnabled(false);
			file3Btn.setEnabled(false);
			stopBtn.setEnabled(false);
		}
	}
}
