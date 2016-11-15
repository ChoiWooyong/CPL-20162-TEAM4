package common.gui;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class ImagePanel extends JPanel {

	public ImagePanel() {
		setLayout(null);	

		JLabel lblImage = new JLabel();
		lblImage.setIcon(new ImageIcon(ImagePanel.class.getResource("/common/gui/ConnectedCar.png")));
		lblImage.setBounds(0, 0, getWidth(), getHeight());
		add(lblImage);	
		setVisible(true);
	}
	
	public void view(String imageName) {
		JLabel lblImage = new JLabel();
		lblImage.setBounds(0, 0, getWidth(), getHeight());
		add(lblImage);	
		setVisible(true);
	}
}
