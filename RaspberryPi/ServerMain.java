package server;

import java.net.InetAddress;
import java.util.Scanner;

import common.CarAttribute;
import common.Point;


public class ServerMain {
	public static void main(String[] args) throws Exception
	{
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
	}
}
