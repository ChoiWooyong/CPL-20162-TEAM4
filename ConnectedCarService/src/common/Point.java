package common;

public class Point {
	private double latitude;
	private double longitude;
	
	public Point (double la, double lg) {
		latitude = la;
		longitude = lg;
	}
	
	public String toString() {
		return latitude + "," + longitude;
	}
}