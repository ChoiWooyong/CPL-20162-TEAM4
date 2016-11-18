package common.gui;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InitPanel extends MyPanel {
	JButton btnClient;
	JButton btnServer;
	
	
	public InitPanel() throws IOException {
		super();

		ImagePanel imagePanel = new ImagePanel(new File("src/common/gui/ConnectedCar.png"));
		imagePanel.setBounds(getWidth()/2-691/2, 80, 691, 463);  // 691 463
		add(imagePanel);
		
		btnClient = new JButton("Client");
		btnClient.setBounds(getWidth()/2 - 200, 580, 150, 50);
		btnClient.setFont(new Font("±¼¸²", Font.BOLD, 25));
		add(btnClient);
		
		btnServer = new JButton("Server");
		btnServer.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnServer.setBounds(getWidth()/2 + 75, 580, 150, 50);
		add(btnServer);
		
		// It must be first panel to be viewed
		setVisible(true);
	}
}
