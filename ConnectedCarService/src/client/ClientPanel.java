package client;

import java.awt.Color;
import java.awt.Font;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import common.CarAttribute;
import common.Point;
import common.gui.MyPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ClientPanel extends MyPanel {

	public ClientPanel() throws Exception {
		
		Box labelBox = Box.createVerticalBox();
		labelBox.setToolTipText("");
		labelBox.setBounds(320, 500, 133, 98);
		add(labelBox);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("±¼¸²", Font.BOLD, 20));
		labelBox.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("±¼¸²", Font.BOLD, 20));
		labelBox.add(lblGender);
		
		JLabel lblCareer = new JLabel("Driving Career");
		lblCareer.setFont(new Font("±¼¸²", Font.BOLD, 20));
		labelBox.add(lblCareer);
		
		JLabel lblType = new JLabel("Car Type");
		lblType.setFont(new Font("±¼¸²", Font.BOLD, 20));
		labelBox.add(lblType);
		
		
		Box infoBox = Box.createVerticalBox();
		infoBox.setBounds(453, 500, 243, 98);
		add(infoBox);
		
		JTextField ageField = new JTextField();
		infoBox.add(ageField);
		ageField.setHorizontalAlignment(JTextField.CENTER);
		ageField.setColumns(9);
		
		Box GenderBox = Box.createHorizontalBox();
		infoBox.add(GenderBox);
		
		JRadioButton rdbtnMale = new JRadioButton("Male    ");
		GenderBox.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		GenderBox.add(rdbtnFemale);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdbtnMale);
		genderGroup.add(rdbtnFemale);
		
		
		Box CareerBox = Box.createHorizontalBox();
		infoBox.add(CareerBox);
		
		JRadioButton rdbtnBeginner = new JRadioButton("Beginner");
		CareerBox.add(rdbtnBeginner);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Intermediate");
		CareerBox.add(rdbtnIntermediate);
		
		JRadioButton rdbtnExpert = new JRadioButton("Expert");
		CareerBox.add(rdbtnExpert);
		
		ButtonGroup careerGroup = new ButtonGroup();
		careerGroup.add(rdbtnBeginner);
		careerGroup.add(rdbtnIntermediate);
		careerGroup.add(rdbtnExpert);
		
		
		Box TypeBox = Box.createHorizontalBox();
		infoBox.add(TypeBox);
		
		JRadioButton rdbtnSmall = new JRadioButton("Small  ");
		TypeBox.add(rdbtnSmall);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium  ");
		TypeBox.add(rdbtnMedium);
		
		JRadioButton rdbtnLarge = new JRadioButton("Large");
		TypeBox.add(rdbtnLarge);

		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(rdbtnSmall);
		typeGroup.add(rdbtnMedium);
		typeGroup.add(rdbtnLarge);
		
		
		JButton btnCommit = new JButton("Commit");
		btnCommit.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnCommit.setBounds(444, 634, 121, 38);
		add(btnCommit);
		
		/*
		System.out.println("My IP Address : " + InetAddress.getLocalHost().getHostAddress());
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Car Number : ");
		String num = in.next();
		
		// Attribute
		System.out.print("Driving Career : ");
		short career = in.nextShort();
		System.out.print("Gender : ");
		short gender = in.nextShort();
		System.out.print("Age : ");
		short age = in.nextShort();
		System.out.print("Type : ");
		short type = in.nextShort();

		System.out.print("Server IP : ");
		String serv_ip = in.next();
		
		//OtherCar car = new OtherCar(name, num, new Point(0, 0), new Point(0, 0));  35.899157
		// Daegu Airport : 35.899500, 128.638377
		OtherCar car = new OtherCar(
				new CarAttribute(num, career, gender, age, type),
				new Point(35.899500, 128.638201)
		);
		
		System.out.println("Starting Connected Car Service for client...");
		car.startConnectedCar_Client(serv_ip);
		*/
	}
}
