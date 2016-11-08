package common;

import java.util.ArrayList;

public class Car {

	protected CarAttribute attr;

	/**
	 * Bing Map REST Service ����� ���� ��ü
	 */
	protected GeocodeFetcher geoFetcher;
	
	/**
	 * ��� ����
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
