package Common;

import java.util.function.LongToIntFunction;

public class Point {
	public double latitude;
	public double longitude;
	
	public Point (double la, double lg) {
		latitude = la;
		longitude = lg;
	}
	
	public String toString() {
		return latitude + "," + longitude;
	}
}
