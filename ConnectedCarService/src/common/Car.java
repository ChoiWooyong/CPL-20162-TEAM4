package common;

import java.io.IOException;
import java.util.ArrayList;

public class Car {

	String[] cmd = {"sudo", "gpsd", "/dev/ttyS0", "-F", "/var/run/gpsd.sock"};

	protected CarAttribute attr;

	protected GeocodeFetcher geoFetcher;

	protected ArrayList<Point> route;

	protected getGpsInfo getInfo;


	protected Car(String num, Point departure, Point destination) {
		attr = new CarAttribute(num);
		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
	}


	protected Car(CarAttribute attr, Point departure, Point destination) {
		this.attr = attr;
		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
	}

	protected Car(CarAttribute attr, Point destination) {
		this.attr = attr;
		Point curPos = getCurPosistion();
		geoFetcher = new GeocodeFetcher(curPos, destination);
		route = geoFetcher.getGeocode();

		System.out.println(curPos + " -> " + destination);
		System.out.println("route.size = " + route.size());
	}

	protected Car(CarAttribute attr) {
		this.attr = attr;
		Point curPos = getCurPosistion();
		// ¤·¤·
	}


	public Point getCurPosistion() {
		Process p = null;

		try{
			p=Runtime.getRuntime().exec(cmd);
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor();
		} catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("Getting GPS Information...");	
		getInfo = new getGpsInfo();
		double[] infoArray;
		infoArray = getInfo.makeArray();
		getInfo.getGps(infoArray);

		return new Point(infoArray[0], infoArray[1]);

	}
}
