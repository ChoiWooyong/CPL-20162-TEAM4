package common.gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InitPanel extends MyPanel {
	JButton btnClient;
	JButton btnServer;
	
	
	public InitPanel() throws IOException {
		super();

		ImagePanel imagePanel = new ImagePanel(new File("ConnectedCar.png"));
		imagePanel.setBounds(222, 100, 540, 340);
		add(imagePanel);
		
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
