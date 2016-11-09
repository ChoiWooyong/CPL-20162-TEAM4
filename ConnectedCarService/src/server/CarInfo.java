package server;

import java.net.Socket;
import java.util.ArrayList;

import common.CarAttribute;
import common.Point;

public class CarInfo {
	
	private Socket sock;
	private CarAttribute attr;
	private ArrayList<Point> fullPath;
	private float score;
	
	/**
	 * state = 0 : Before CCH
	 * state = 1 : After CCH & Before SCH
	 * state = 2 : After SCH
	 */
	private short state;
	
	public CarInfo(Socket sock) {
		this.sock = sock;
		state = 0;
		score = -1;
	}


	public ArrayList<Point> getFullPath() {
		return fullPath;
	}

	public void setFullPath(ArrayList<Point> fullpath) {
		this.fullPath = fullpath;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
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

	public short getState() {
		return state;
	}

	public void goNextState() {
		state++;
	}
}
