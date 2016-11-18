package common.gui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InitPanel extends MyPanel {
	JButton btnClient;
	JButton btnServer;
	
	
	public InitPanel() throws IOException {
		super();

		ImagePanel imagePanel = new ImagePanel(new File("src/common/gui/ConnectedCar.png"));
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setBounds(getWidth()/2-493/2, 30, 493, 298);  // 493 298
		add(imagePanel);
		
		btnClient = new JButton("");
		btnClient.setBounds(getWidth()/2 - 220, 350, 200, 60);
		btnClient.setFont(new Font("±¼¸²", Font.BOLD, 25));
		//btnClient.setBackground(Color.BLACK);
		btnClient.setIcon(new ImageIcon(InitPanel.class.getResource("/common/gui/ManualMode.png")));
		add(btnClient);
		
		btnServer = new JButton("");
		btnServer.setFont(new Font("±¼¸²", Font.BOLD, 25));
		btnServer.setBounds(getWidth()/2 + 25, 350, 200, 60);
		btnServer.setIcon(new ImageIcon(InitPanel.class.getResource("/common/gui/AutoMode.png")));
		add(btnServer);
		
		// It must be first panel to be viewed
		setVisible(true);
	}
}
