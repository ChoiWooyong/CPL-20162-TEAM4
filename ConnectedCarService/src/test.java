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

public class test extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	JPanel innerPanel;
	
	public test() {

		setLayout(null);
		setSize(1008, 638);
		
		JButton btnFinded = new JButton("Allow to be finded");
		btnFinded.setBounds(706, 177, 139, 23);
		add(btnFinded);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(706, 389, 139, 23);
		add(btnNewButton_1);
		
		
	}
}
