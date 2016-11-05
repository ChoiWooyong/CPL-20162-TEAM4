package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import common.Car;
import common.Environment;
import common.Packet;
import common.Point;
import common.SwitchTimer;

public class MyCar extends Car implements Runnable {

	/**
	 * 서버 소켓 
	 */
	private ServerSocket serv_sock;

	/**
	 * 모든 차량 
	 */
	private ArrayList<Socket> socks;

	/**
	 * CCH에서 걸러진 차량
	 */
	private ArrayList<Socket> selList;

	/**
	 * CCH, SCH에 시간 할당
	 */
	private Thread timer;

	public MyCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
		selList = new ArrayList<Socket>();
	}

	public void startConnectedCar_Server() throws InterruptedException {
		// 주변의 차량을 계속 탐색하기 위한 무한 루프 쓰레드
		new Thread(this).start();

		// Car num만큼 찾을 때까지 기다림
		System.out.println("Waiting for detecting car...");
		while (socks.size() < Environment.CAR_NUM) ;

		// 통신 시작
		System.out.println("Starting to communication with other cars...");
		while (true) {
			CCHPeriod();
			SCHPeriod();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				socks.add(serv_sock.accept());
				System.out.println("A car is detected.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// Method for WAVE communication
	@Override
	protected void CCHPeriod() throws InterruptedException {
		timer.start();

		// Broadcasting my first leg
		for (Socket sock : socks) {
			// 읽고
			// 쓴다
		}

		// Getting response from other cars
		for (Socket sock : socks) {
			// 읽고
			// 쓴다
		}

		timer.join();
	}

	@Override
	protected void SCHPeriod() throws InterruptedException {
		timer.start();

		Socket sock = socks.get(selectionAlg());

		// 읽고
		// 쓴다

		timer.join();
	}
	
	public int selectionAlg() {
		return 0;
	}


	@Override
	protected void readPacket(Socket sock, Point p) throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
		Packet pk = (Packet) in.readObject();
		p = (Point) pk.getMessage();
		in.close();
	}

	@Override
	protected void readPacket(Socket sock, ArrayList<Point> plist) throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
		Packet pk = (Packet) in.readObject();
		plist = (ArrayList<Point>) pk.getMessage();
		in.close();
	}

	@Override
	protected void writePacket(Socket sock, Point p) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		Packet pk = new Packet("CCH", p);
		out.writeObject(pk);
		out.close();
	}

	@Override
	protected void writePacket(Socket sock, ArrayList<Point> plist) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		Packet pk = new Packet("SCH", plist);
		out.writeObject(pk);
		out.close();
	}


	// Getter & Setter
	public ArrayList<Socket> getSocks() {
		return socks;
	}
}
