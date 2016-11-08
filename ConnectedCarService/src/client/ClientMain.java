package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import common.Environment;
import common.GeocodeFetcher;
import common.Point;

public class ClientMain {

	public static void main(String[] args) throws Exception{
		
		System.out.println("My IP Address : " + InetAddress.getLocalHost().getHostAddress());
		
		Scanner in = new Scanner(System.in);
		System.out.print("NAME : ");
		String name = in.nextLine();
		System.out.print("NUMBER : ");
		String num = in.nextLine();
		System.out.print("SERVER IP : ");
		String serv_ip = in.nextLine();
		
		//OtherCar car = new OtherCar(name, num, new Point(0, 0), new Point(0, 0));
		OtherCar car = new OtherCar(name, num, new Point(35.885122, 128.614304), new Point(35.899157, 128.638201));
		
		System.out.println("Starting Connected Car Service for client...");
		car.startConnectedCar_Client(serv_ip);
	}
	
	
}
