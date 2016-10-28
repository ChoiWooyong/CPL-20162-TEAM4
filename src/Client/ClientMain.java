package Client;

import java.io.IOException;
import java.util.Scanner;

import Common.GeocodeFetcher;
import Common.Point;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
		//System.out.print("Input name of car : ");
		//String name = in.nextLine();
		//System.out.print("Input number of car : ");
		//String num = in.nextLine();
		
		GeocodeFetcher g = new GeocodeFetcher(new Point(36.397034, 128.593246), new Point(37.525041, 126.950790));
		System.out.println(g.getGeocode());
		//OtherCar car = new OtherCar(name, num, new Point(0, 0), new Point(0, 0));
		//System.out.println("Start Connected Car Provider");
		//car.startConnectedCar();
	}
}
