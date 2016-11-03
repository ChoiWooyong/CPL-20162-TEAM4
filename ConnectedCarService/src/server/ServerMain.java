package server;

import java.io.IOException;
import java.util.Scanner;

import common.Environment;
import common.Point;

public class ServerMain {
	public static void main(String[] args)
	{
		System.out.println("Starting to connected car service for server.");

		Scanner in = new Scanner(System.in);
		System.out.print("Input the name of car : ");
		String name = in.nextLine();
		System.out.print("Input the number of car : ");
		String num = in.nextLine();
		
		MyCar myCar = new MyCar(name, num, new Point(0, 0), new Point(0, 0));
		
		try {
			myCar.startConnectedCar_Server();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
