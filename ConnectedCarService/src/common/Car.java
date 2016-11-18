package common;

import java.io.IOException;
import java.util.ArrayList;

public class Car implements Runnable {

	String[] cmd = {"sudo", "gpsd", "/dev/ttyS0", "-F", "/var/run/gpsd.sock"};

	protected CarAttribute attr;

	protected Point curPos;
	
	protected int curSpeed;
	
	protected ArrayList<Point> route;


	protected Car(CarAttribute attr) {
		this.attr = attr;
	}
	
	public void makeRoute(Point dst) {
		route = MapDataFetcher.getGeocode(curPos, dst);
	}
	
	private void getGPSInfo() {
		Process p = null;

		try{
			p = Runtime.getRuntime().exec(cmd);
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor();
		} catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("Getting GPS Information...");
		
		getGpsInfo getInfo = new getGpsInfo();
		double[] infoArray = getInfo.makeArray();
		getInfo.getGps(infoArray);

		curPos = new Point(infoArray[0], infoArray[1]);
		curSpeed = (int) infoArray[3];
	}

	@Override
	public void run() {
		getGPSInfo();
		
		try {
			Thread.sleep(Environment._IMAGE_UPDATE_TIME / 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Point getCurPos() {
		return curPos;
	}

	public void setCurPos(Point pos) {
		curPos = pos;
	}

	public CarAttribute getAttr() {
		return attr;
	}

	public void setAttr(CarAttribute attr) {
		this.attr = attr;
	}

	public ArrayList<Point> getRoute() {
		return route;
	}
}
