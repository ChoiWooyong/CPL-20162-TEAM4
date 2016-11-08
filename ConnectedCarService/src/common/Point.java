package common;

/**
 * 위도, 경도 정보를 담을 Information class
 * @author 최우용
 *
 */
public class Point {
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
		if (Math.abs(p.latitude - latitude) > Environment.ERRORRANGE || Math.abs(p.longitude - longitude) > Environment.ERRORRANGE)
			return false;
		
		return true;
	}
	
	public String toString() {
		return latitude + "," + longitude;
	}
}