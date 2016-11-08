package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import common.Point;

public class CarInfo {
	
	private ArrayList<Point> fullpath;
	private int score;
	private int sockIdx;
	private ArrayList<Socket> selList;

	
	public CarInfo(int sockIdx)
	{
		score = -1;
	}
	
	
	public ArrayList<Point> getFullpath() {
		return fullpath;
	}

	public void setFullpath(ArrayList<Point> fullpath) {
		this.fullpath = fullpath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSockIdx() {
		return sockIdx;
	}

	public void setSock(int sockIdx) {
		this.sockIdx = sockIdx;
	}

	public ArrayList<Socket> getSelList() {
		return selList;
	}

	public void setSelList(ArrayList<Socket> selList) {
		this.selList = selList;
	}

	
}
