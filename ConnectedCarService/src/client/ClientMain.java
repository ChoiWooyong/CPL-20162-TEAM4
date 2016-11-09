package client;

import java.net.InetAddress;
import java.util.Scanner;

import common.CarAttribute;
import common.Point;

public class ClientMain {

	public static void main(String[] args) throws Exception{
		
		System.out.println("My IP Address : " + InetAddress.getLocalHost().getHostAddress());
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Car Number : ");
		String num = in.nextLine();
		
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
		String serv_ip = in.nextLine();
		
		//OtherCar car = new OtherCar(name, num, new Point(0, 0), new Point(0, 0));  35.899157
		OtherCar car = new OtherCar(
				new CarAttribute(num, career, gender, age, type),
				new Point(35.885122, 128.614304),
				new Point(Double.parseDouble(num), 128.638201)
		);
		
		System.out.println("Starting Connected Car Service for client...");
		car.startConnectedCar_Client(serv_ip);
	}
	
	
}
