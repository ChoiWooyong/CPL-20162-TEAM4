package server;

import java.net.Socket;
import java.util.ArrayList;

import common.Point;

public class CarInfo {
	
	private ArrayList<Point> fullPath;
	private int score;
	private int sockIdx;

	
	public CarInfo(int sockIdx)
	{
		score = -1;
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

	public void setSockIdx(int sockIdx) {
		this.sockIdx = sockIdx;
	}

	public int getSockIdx() {
		return sockIdx;
	}
}
