package server;

import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.CarAttribute;
import common.Point;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;


public class ServerPanel extends JPanel {
	
	public ServerPanel() 
	{
		setBounds(0, 0, 1008, 730);
		setLayout(null);
		
		JLabel label = new JLabel("\uC131\uBCC4");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label.setBounds(431, 208, 46, 23);
		add(label);
		
		JRadioButton radioButton = new JRadioButton("\uB0A8");
		radioButton.setBounds(485, 210, 37, 23);
		add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\uC5EC");
		radioButton_1.setBounds(526, 210, 37, 23);
		add(radioButton_1);
		
		setVisible(true);
		
		/*
		System.out.println("My IP Address : " + InetAddress.getLocalHost().getHostAddress()); 

		Scanner in = new Scanner(System.in);
		System.out.print("Car Number : ");
		String num = in.next();
		
		// Attribute
		System.out.print("Target Driving Career : ");
		short career = in.nextShort();
		System.out.print("Target Gender : ");
		short gender = in.nextShort();
		System.out.print("Target Age : ");
		short age = in.nextShort();
		System.out.print("Target Type : ");
		short type = in.nextShort();

		System.out.println("Starting to Connected Car Service for server.");
		//MyCar myCar = new MyCar(num, new Point(0, 0), new Point(0, 0));
		MyCar myCar = new MyCar(
				new CarAttribute(num, career, gender, age, type),
				new Point(35.899157, 128.638201)
		);

		myCar.startConnectedCar_Server();
		*/
	}
}
