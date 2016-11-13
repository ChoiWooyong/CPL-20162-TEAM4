package client;

import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JPanel;

import common.CarAttribute;
import common.Point;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientPanel extends JPanel {

	public ClientPanel() throws Exception {
		setBounds(0, 0, 1008, 730);
		setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(350, 261, 97, 23);
		add(btnNewButton);
		
		setVisible(true);
		
		
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
