import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;

import common.gui.ImagePanel;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

public class test extends JPanel {
	public test() {
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon(test.class.getResource("/common/gui/ConnectedCar.png")));
		btnNewButton.setForeground(UIManager.getColor("Button.darkShadow"));
		btnNewButton.setBackground(Color.WHITE);
		add(btnNewButton);
	}
	private JTextField textField;

}
