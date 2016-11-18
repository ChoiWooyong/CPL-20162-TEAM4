import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
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
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

public class test extends JFrame {
	public test() throws IOException, InterruptedException {

		setSize(500, 500);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new test();
	}

}
