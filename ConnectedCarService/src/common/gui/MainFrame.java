package common.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.ClientPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements ActionListener {
	
	ClientPanel clientPanel;
	InitPanel initPanel;

	public MainFrame() throws Exception {
		initPanel = new InitPanel();
		clientPanel = new ClientPanel();
		
		setSize(1024, 768);
		getContentPane().setLayout(null);
		getContentPane().add(initPanel);
		getContentPane().add(clientPanel);
		
		initPanel.btnClient.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		allVisibleFalse();
		
		if(e.getSource() == initPanel.btnClient) {
			clientPanel.setVisible(true);
		}
	}
	
	public void allVisibleFalse() {
		clientPanel.setVisible(false);
		initPanel.setVisible(false);
	}
	
	public static void main(String[] args) throws Exception {
		new MainFrame();
	}
}
