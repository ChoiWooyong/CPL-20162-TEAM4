package common;

import java.util.ArrayList;

public class Car {

	/**
	 * 해당 차의 특성 (차번호 등)
	 */
	protected CarAttribute attr;

	/**
	 * Bing Map REST Service 사용을 위한 객체
	 */
	protected GeocodeFetcher geoFetcher;
	
	/**
	 * 경로 정보
	 */
	protected ArrayList<Point> route;
	

	// test용 생성자
	protected Car(String num, Point departure, Point destination) {
		attr = new CarAttribute(num);
		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
	}
	
	// test용 생성자
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
