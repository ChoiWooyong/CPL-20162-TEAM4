
import java.util.ArrayList;

public class Car {

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
		geoFetcher = new GeocodeFetcher(getCurPosistion(), destination);
		route = geoFetcher.getGeocode();
	}


	public Point getCurPosistion() {
		 getInfo = new getGpsInfo();
         double[] infoArray;
         infoArray = getInfo.makeArray();
         getInfo.getGps(infoArray);

         return new Point(infoArray[0], infoArray[1]);

	}
}
