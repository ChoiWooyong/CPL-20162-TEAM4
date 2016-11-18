package common;

import java.io.Serializable;


public class Point implements Serializable {
	private double latitude;
	private double longitude;
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Point (double la, double lg) {
		latitude = la;
		longitude = lg;
	}
	
	public boolean isEqual(Point p) {
		if (Math.abs(p.latitude - latitude) > Environment._ERRORRANGE || Math.abs(p.longitude - longitude) > Environment._ERRORRANGE)
			return false;
		
		return true;
	}
	
	public double calDistance(Point p) {
		return Math.sqrt(Math.pow(latitude - p.latitude, 2.0) + Math.pow(longitude - p.longitude, 2.0)) ;
	}
	
	public String toString() {
		return latitude + "," + longitude;
	}
}