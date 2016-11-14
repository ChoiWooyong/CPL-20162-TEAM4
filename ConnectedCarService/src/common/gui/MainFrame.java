package common.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.ClientPanel;
import common.Environment;
import server.ServerPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements ActionListener {
	
	InitPanel initPanel;
	ClientPanel clientPanel;
	ServerPanel serverPanel;
	
	MyPanel curPanel;

	public MainFrame() throws Exception {
		// Construct panels
		initPanel = new InitPanel();
		clientPanel = new ClientPanel();
		serverPanel = new ServerPanel();
		
		// Configure frame
		setSize(1024, 768);
		setTitle(Environment._TITLE);
		getContentPane().setLayout(null);
		setVisible(true);
		
		// Add panels to frame
		getContentPane().add(initPanel);
		getContentPane().add(clientPanel);
		getContentPane().add(serverPanel);
		
		// Add actionListener to button
		initPanel.btnClient.addActionListener(this);
		initPanel.btnServer.addActionListener(this);
		
		// InitPanel must be first panel to be viewed.
		curPanel = initPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		// Get What button is pressed
		JButton curBtn = (JButton) e.getSource();
		
		// Set current panel invisible
		curPanel.setVisible(false);
		
		// Set panel visible according to button that is pressed
		if(curBtn == initPanel.btnClient) {
			clientPanel.setVisible(true);
			curPanel = clientPanel;
			
		} else if(curBtn == initPanel.btnServer) {
			serverPanel.setVisible(true);
			curPanel = serverPanel;
		}
	}
	
	public void allVisibleFalse() {
		initPanel.setVisible(false);
		clientPanel.setVisible(false);
		serverPanel.setVisible(false);
	}
	
	public static void main(String[] args) throws Exception {
		new MainFrame();
	}
}
