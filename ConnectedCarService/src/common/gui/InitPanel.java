package common.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InitPanel extends JPanel {
	JButton btnClient;
	JButton btnServer;
	
	public InitPanel() {
		super();
		
		setBounds(0, 0, 1008, 730);
		setLayout(null);
		
		btnClient = new JButton("Client");
		btnClient.setBounds(398, 611, 97, 37);
		add(btnClient);
		
		btnServer = new JButton("Server");
		btnServer.setBounds(577, 611, 97, 37);
		add(btnServer);
	}
}
