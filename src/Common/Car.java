package Common;

import java.util.ArrayList;

public class Car {

	private String name;
	private String num;
	
	private ArrayList<Point> route;
	
	public Car(String name, String num, Point departure, Point destination) {
		this.name = name;
		this.num = num;
		
		try {
			route = new GeocodeFetcher(departure, destination).getGeocode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Point> getRoute() {
		return route;
	}
}
