package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import common.Environment;
import common.GeocodeFetcher;
import common.Point;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		System.out.println("Starting to connected car service for client.");
		
		System.out.println("My IP Address : " + InetAddress.getLocalHost().getHostAddress());
		
		Scanner in = new Scanner(System.in);
		System.out.print("Input the name of car : ");
		String name = in.nextLine();
		System.out.print("Input the number of car : ");
		String num = in.nextLine();
		
		OtherCar car = new OtherCar(name, num, new Point(0, 0), new Point(0, 0));
		System.out.println("Start Connected Car Provider");
		car.startConnectedCar_Client();
	}
	
	
}
