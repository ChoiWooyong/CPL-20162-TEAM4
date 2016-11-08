package common;

import java.util.ArrayList;

public class Car {

	protected CarAttribute attr;

	/**
	 * Bing Map REST Service 사용을 위한 객체
	 */
	protected GeocodeFetcher geoFetcher;
	
	/**
	 * 경로 정보
	 */
	protected ArrayList<Point> route;
	

	protected Car(String num, Point departure, Point destination) {
		attr = new CarAttribute(num);
		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
	}


	// Getter & Setter
	public CarAttribute getAttr() {
		return attr;
	}

	public void setAttr(CarAttribute attr) {
		this.attr = attr;
	}

	public ArrayList<Point> getRoute() {
		return route;
	}
	
	public void setRoute(ArrayList<Point> route) {
		this.route = route;
	}



}
