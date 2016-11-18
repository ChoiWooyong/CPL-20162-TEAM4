package common;

import java.io.IOException;
import java.util.ArrayList;

public class Car implements Runnable {

	String[] cmd = {"sudo", "gpsd", "/dev/ttyS0", "-F", "/var/run/gpsd.sock"};

	protected CarAttribute attr;

	protected int curSpeed = 0;

	protected ArrayList<Point> route;


	protected Car(CarAttribute attr, boolean isDebug) {
		this.attr = attr;
		if (isDebug) {
			attr.setCurPos(new Point(35.892441, 128.609169));
			
		} else {
			updateGPSInfo();
		}
	}

	public void makeRoute(Point dst) {
		route = MapDataFetcher.getGeocode(getAttr().getCurPos(), dst);
	}

	private void updateGPSInfo() {
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

		getAttr().setCurPos(new Point(infoArray[0], infoArray[1]));
		curSpeed = (int) (infoArray[3] * Environment.MILE_TO_KILO);
	}

	@Override
	public void run() {
		while(true) {
			updateGPSInfo();

			try {
				Thread.sleep(Environment._IMAGE_UPDATE_TIME / 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	public int getCurSpeed() {
		return curSpeed;
	}

	public void setCurSpeed(int curSpeed) {
		this.curSpeed = curSpeed;
	}
	
	
}
