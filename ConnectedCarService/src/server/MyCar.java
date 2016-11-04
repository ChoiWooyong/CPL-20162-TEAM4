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

	
	public MyCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
	}

	public void startConnectedCar_Server() throws InterruptedException {
		// 주변의 차량을 계속 탐색하기 위한 무한 루프 쓰레드
		new Thread(this).start();
		
		// Car num만큼 찾을 때까지 기다림
		System.out.println("Waiting for detecting car...");
		while (socks.size() < Environment.CAR_NUM) ;

		System.out.println("Starting to communication with other cars...");
		selList = new ArrayList<Socket>();
		while (true) {
			CCHPeriod();
			SCHPeriod();
		}
	}

	public int selectionAlg() {
		return 0;
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

	public void CCHPeriod() throws InterruptedException {
		// Broadcasting my first leg
		ObjectOutputStream out;
		for (Socket sock : socks) {
			try {
				out = new ObjectOutputStream(sock.getOutputStream());
				Packet pk = new Packet("CCH", "CCH 줄꺼");
				out.writeObject(pk);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Getting response from other cars
		ObjectInputStream in;
		for (Socket sock : socks) {
			try {
				in = new ObjectInputStream(sock.getInputStream());
				Packet pk = (Packet) in.readObject();
				Object msg = (Object) (pk.getMessage());  // CCH 읽은 거

				if (!selList.contains(socks)) {
					selList.add(sock);
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Thread.sleep(Environment.CHANNEL_SWITCHING_TIME);
	}

	public void SCHPeriod() throws InterruptedException {
		Socket sock = socks.get(selectionAlg());
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			Packet pk = new Packet("SCH", "SCH 구간 주는거!");
			out.writeObject(pk);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			Packet pk = (Packet) in.readObject();
			Object msg = (Object) (pk.getMessage());  // SCH 읽은 거
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(Environment.CHANNEL_SWITCHING_TIME);
	}

	public ArrayList<Socket> getSocks() {
		return socks;
	}
}
