package common;

/**
 * 위도, 경도 정보를 담을 Information class
 * @author 최우용
 *
 */
public class Point {
	private double latitude;
	private double longitude;
	
	public Point (double la, double lg) {
		latitude = la;
		longitude = lg;
	}
	
	public boolean isEqual(Point p) {
		if (p.latitude != latitude || p.longitude != longitude)
			return false;
		
		return true;
	}
	
	public String toString() {
		return latitude + "," + longitude;
	}
}