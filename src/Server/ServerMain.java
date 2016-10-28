package Server;

import java.io.IOException;

import Common.Environment;
import Common.Point;

public class ServerMain {
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{		
		// CUI implement
		System.out.println("Starting to connected car service");
		
		MyCar myCar = new MyCar("MyCar", "1111", new Point(0, 0), new Point(0, 0));
		myCar.findNearCars();
		
		System.out.println(Environment.CAR_NUM + " cars are detected.");
		
		myCar.startConnectedCar();
	}
}
