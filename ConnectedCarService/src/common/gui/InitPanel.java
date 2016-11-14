package common.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InitPanel extends MyPanel {
	JButton btnClient;
	JButton btnServer;
	
	public InitPanel() {
		super();
		
		btnClient = new JButton("Client");
		btnClient.setBounds(398, 611, 97, 37);
		add(btnClient);
		
		btnServer = new JButton("Server");
		btnServer.setBounds(577, 611, 97, 37);
		add(btnServer);
		
		// It must be first panel to be viewed
		setVisible(true);
	}
}
