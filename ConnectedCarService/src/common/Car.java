package common;

import java.util.ArrayList;

public class Car {

	/**
	 * 차의 이름
	 */
	private String name;
	
	/**
	 * 차 번호
	 */
	private String num;
	
	/**
	 * 경로 정보
	 */
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
	
	

	// Getter & Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setRoute(ArrayList<Point> route) {
		this.route = route;
	}

	public ArrayList<Point> getRoute() {
		return route;
	}
}
