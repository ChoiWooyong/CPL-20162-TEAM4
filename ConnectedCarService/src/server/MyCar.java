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
	 * ���� ���� 
	 */
	private ServerSocket serv_sock;

	/**
	 * ��� ���� 
	 */
	private ArrayList<Socket> socks;

	/**
	 * CCH���� �ɷ��� ����
	 */
	private ArrayList<Socket> selList;

	/**
	 * CCH, SCH�� �ð� �Ҵ�
	 */
	private Thread timer;

	public MyCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
		selList = new ArrayList<Socket>();
	}

	public void startConnectedCar_Server() throws InterruptedException {
		// �ֺ��� ������ ��� Ž���ϱ� ���� ���� ���� ������
		new Thread(this).start();

		// Car num��ŭ ã�� ������ ��ٸ�
		System.out.println("Waiting for detecting car...");
		while (socks.size() < Environment.CAR_NUM) ;

		// ��� ����
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
			// �а�
			// ����
		}

		// Getting response from other cars
		for (Socket sock : socks) {
			// �а�
			// ����
		}

		timer.join();
	}

	@Override
	protected void SCHPeriod() throws InterruptedException {
		timer.start();

		Socket sock = socks.get(selectionAlg());

		// �а�
		// ����

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
