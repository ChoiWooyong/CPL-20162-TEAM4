package common;

import java.io.IOException;
import java.util.ArrayList;

public class Car implements Runnable {

	String[] cmd = {"sudo", "gpsd", "/dev/ttyS0", "-F", "/var/run/gpsd.sock"};

	protected CarAttribute attr;

	protected Point curPos;
	
	protected ArrayList<Point> route;


	protected Car(CarAttribute attr) {
		this.attr = attr;
		curPos = updateCurPosistion();
		
		// Repeat to get current position
		new Thread(this).start();
	}
	
	public void makeRoute(Point dst) {
		route = MapDataFetcher.getGeocode(curPos, dst);
	}
	
	private Point updateCurPosistion() {
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

		return new Point(infoArray[0], infoArray[1]);

	}

	@Override
	public void run() {
		curPos = updateCurPosistion();
		
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
