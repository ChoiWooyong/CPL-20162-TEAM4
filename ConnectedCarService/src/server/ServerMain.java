package server;

import java.util.Scanner;

import common.Point;

public class ServerMain {
	public static void main(String[] args) throws Exception
	{

		Scanner in = new Scanner(System.in);
		System.out.print("NUM : ");
		String num = in.nextLine();

		System.out.println("Starting to Connected Car Service for server.");
		//MyCar myCar = new MyCar(num, new Point(0, 0), new Point(0, 0));
		MyCar myCar = new MyCar(num, new Point(35.885122, 128.614304), new Point(35.899157, 128.638201));
		
		myCar.startConnectedCar_Server();
	}
}
