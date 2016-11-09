package common;

import java.util.ArrayList;

public class Car {

	/**
	 * �ش� ���� Ư�� (����ȣ ��)
	 */
	protected CarAttribute attr;

	/**
	 * Bing Map REST Service ����� ���� ��ü
	 */
	protected GeocodeFetcher geoFetcher;
	
	/**
	 * ��� ����
	 */
	protected ArrayList<Point> route;
	

	// test�� ������
	protected Car(String num, Point departure, Point destination) {
		attr = new CarAttribute(num);
		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
	}
	
	// test�� ������
	protected Car(CarAttribute attr, Point departure, Point destination) {
		this.attr = attr;
		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
	}
	
	protected Car(String num, Point destination) {
		attr = new CarAttribute(num);
		geoFetcher = new GeocodeFetcher(getCurPosistion(), destination);
		route = geoFetcher.getGeocode();
	}
	
	public Point getCurPosistion() {
		
		
		return new Point(0.0, 0.0);
	}
}
