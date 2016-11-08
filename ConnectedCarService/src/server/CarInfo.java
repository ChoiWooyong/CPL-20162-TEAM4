package server;

import java.net.Socket;
import java.util.ArrayList;

import common.CarAttribute;
import common.Point;

public class CarInfo {
	
	private Socket sock;
	private CarAttribute attr;
	private ArrayList<Point> fullPath;
	private int score;
	
	/**
	 * SCH에서 고려 할 가치가 있는가
	 */
	private boolean isWorth;

	
	public CarInfo(Socket sock) {
		this.sock = sock;
		score = -1;
		isWorth = false;
	}


	public ArrayList<Point> getFullPath() {
		return fullPath;
	}

	public void setFullPath(ArrayList<Point> fullpath) {
		this.fullPath = fullpath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public CarAttribute getAttr() {
		return attr;
	}

	public void setAttr(CarAttribute attr) {
		this.attr = attr;
	}

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public boolean isWorth() {
		return isWorth;
	}

	public void setWorth(boolean isWorth) {
		this.isWorth = isWorth;
	}
}
