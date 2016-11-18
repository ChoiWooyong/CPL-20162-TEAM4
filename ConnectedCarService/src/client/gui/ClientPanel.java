package client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import common.CarAttribute;
import common.gui.ImagePanel;
import common.gui.InitPanel;
import common.gui.MyPanel;

public class ClientPanel extends MyPanel implements ActionListener {
	
	// DEBUG
	private boolean isDebug = false;
	
	// Server IP
	private String serv_ip;
	
	// InnerPanel to switch ClientMainPanel
	private JPanel innerPanel;

	// For Car Attribute
	private String num;
	private JTextField ageField;
	private ButtonGroup genderGroup;
	private ButtonGroup careerGroup;
	private ButtonGroup typeGroup;

	public ClientPanel(String[] args) throws Exception {

		// Set variables
		isDebug = args[1] == "--DEBUG" ? true : false;
		
		if (isDebug) {
			num = "TEST";
			serv_ip = "127.0.0.1";
			
		} else {
			num = args[1];
			serv_ip = args[2];
		}
			

		// Set default UI of Client
		JLabel lblClientMode = new JLabel("Client Mode");
		lblClientMode.setFont(new Font("±¼¸²", Font.BOLD, 22));
		lblClientMode.setBounds(12, 10, 155, 30);
		add(lblClientMode);
		
		JLabel lblTeamWave = new JLabel("Team WAVE");
		lblTeamWave.setFont(new Font("±¼¸²", Font.BOLD, 22));
		lblTeamWave.setBounds(655, 10, 155, 30);
		add(lblTeamWave);
		
		
		// InnerPanel for Client
		innerPanel = new JPanel();
		innerPanel.setBounds(0, 20, 800, 470);
		innerPanel.setBackground(Color.WHITE);
		add(innerPanel);
		innerPanel.setLayout(null);
		
		
		// ImagePanel
		ImagePanel imagePanel = new ImagePanel(new File("src/common/gui/ConnectedCar.png"));
		imagePanel.setBounds(getWidth()/2-493/2, 10, 493, 298);
		imagePanel.setBackground(Color.WHITE);
		innerPanel.add(imagePanel);
		
		
		// Construct ButtonGroup for RadioButton
		genderGroup = new ButtonGroup();
		careerGroup = new ButtonGroup();
		typeGroup = new ButtonGroup();
		
		// Description of Car Attribute
		Box labelBox = Box.createVerticalBox();
		labelBox.setBounds(90, 320, 150, 96);
		innerPanel.add(labelBox);
		
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
		
		
		// TextField & RadioButton for Car Attribute
		Box infoBox = Box.createVerticalBox();
		infoBox.setBounds(250, 320, 231, 96);
		innerPanel.add(infoBox);
		
		ageField = new JTextField();
		infoBox.add(ageField);
		ageField.setHorizontalAlignment(JTextField.CENTER);
		ageField.setColumns(10);
		
		// Box for Radiobuttons of the gender
		Box GenderBox = Box.createHorizontalBox();
		infoBox.add(GenderBox);
		
		JRadioButton rdbtnMale = new JRadioButton("Male    ");
		rdbtnMale.setActionCommand("1");
		rdbtnMale.setBackground(Color.WHITE);
		GenderBox.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setActionCommand("2");
		rdbtnFemale.setBackground(Color.WHITE);
		GenderBox.add(rdbtnFemale);
		
		genderGroup.add(rdbtnMale);
		genderGroup.add(rdbtnFemale);

		// Box for Radiobuttons of the career
		Box CareerBox = Box.createHorizontalBox();
		infoBox.add(CareerBox);
		
		JRadioButton rdbtnBeginner = new JRadioButton("Beginner");
		rdbtnBeginner.setActionCommand("1");
		rdbtnBeginner.setBackground(Color.WHITE);
		CareerBox.add(rdbtnBeginner);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Intermediate");
		rdbtnIntermediate.setActionCommand("2");
		rdbtnIntermediate.setBackground(Color.WHITE);
		CareerBox.add(rdbtnIntermediate);
		
		JRadioButton rdbtnExpert = new JRadioButton("Expert");
		rdbtnExpert.setActionCommand("3");
		rdbtnExpert.setBackground(Color.WHITE);
		CareerBox.add(rdbtnExpert);
		
		careerGroup.add(rdbtnBeginner);
		careerGroup.add(rdbtnIntermediate);
		careerGroup.add(rdbtnExpert);
		
		// Box for Radiobuttons of the car type
		Box TypeBox = Box.createHorizontalBox();
		infoBox.add(TypeBox);
		
		JRadioButton rdbtnSmall = new JRadioButton("Small  ");
		rdbtnSmall.setActionCommand("1");
		rdbtnSmall.setBackground(Color.WHITE);
		TypeBox.add(rdbtnSmall);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium  ");
		rdbtnMedium.setActionCommand("2");
		rdbtnMedium.setBackground(Color.WHITE);
		TypeBox.add(rdbtnMedium);
		
		JRadioButton rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.setActionCommand("3");
		rdbtnLarge.setBackground(Color.WHITE);
		TypeBox.add(rdbtnLarge);
		
		typeGroup.add(rdbtnSmall);
		typeGroup.add(rdbtnMedium);
		typeGroup.add(rdbtnLarge);
		

		// Commit button
		JButton btnCommit = new JButton("");
		btnCommit.setBounds(510, 340, 200, 60);
		btnCommit.setIcon(new ImageIcon(InitPanel.class.getResource("/common/gui/Commit.png")));
		innerPanel.add(btnCommit);
		btnCommit.setFont(new Font("±¼¸²", Font.BOLD, 20));	
		btnCommit.addActionListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		short age = (short) (Short.parseShort(ageField.getText()) % 10);
		short career = Short.parseShort(careerGroup.getSelection().getActionCommand());
		short gender = Short.parseShort(genderGroup.getSelection().getActionCommand());
		short type = Short.parseShort(typeGroup.getSelection().getActionCommand());
		
		remove(innerPanel);
		revalidate();
		repaint();
		try {
			innerPanel = new ClientMainPanel(new CarAttribute(num, career, gender, age, type), serv_ip, isDebug);
			innerPanel.setBounds(0, 46, 1020, 722);
			add(innerPanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
