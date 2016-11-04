package server;

import java.io.IOException;
import java.util.Scanner;

import common.Environment;
import common.Point;

public class ServerMain {
	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);
		System.out.print("NAME : ");
		String name = in.nextLine();
		System.out.print("NUM : ");
		String num = in.nextLine();

		System.out.println("Starting to Connected Car Service for server.");
		MyCar myCar = new MyCar(name, num, new Point(0, 0), new Point(0, 0));
		
		try {
			myCar.startConnectedCar_Server();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
