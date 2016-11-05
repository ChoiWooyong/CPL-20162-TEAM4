package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Car {

	/**
	 * 차의 이름
	 */
	protected String name;

	/**
	 * 차 번호
	 */
	protected String num;

	/**
	 * Bing Map REST Service 사용을 위한 객체
	 */
	protected GeocodeFetcher geoFetcher;
	
	/**
	 * 경로 정보
	 */
	protected ArrayList<Point> route;

	/**
	 * CCH, SCH에 시간 할당
	 */
	protected Thread timer;
	

	protected Car(String name, String num, Point departure, Point destination) {
		this.name = name;
		this.num = num;

		geoFetcher = new GeocodeFetcher(departure, destination);
		route = geoFetcher.getGeocode();
		timer = new Thread(new SwitchTimer());
	}
	
	
	
	// WAVE communication
	protected void CCHPeriod() throws InterruptedException { }
	protected void SCHPeriod() throws InterruptedException { }
	
	
	// Read & Write Packet
	protected void readPacket(Socket sock, Point p) throws IOException, ClassNotFoundException { }
	protected void readPacket(Socket sock, ArrayList<Point> plist) throws IOException, ClassNotFoundException { }
	protected void writePacket(Socket sock, Point p) throws IOException { }
	protected void writePacket(Socket sock, ArrayList<Point> plist) throws IOException { }


	// Getter & Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setRoute(ArrayList<Point> route) {
		this.route = route;
	}

	public ArrayList<Point> getRoute() {
		return route;
	}
}
