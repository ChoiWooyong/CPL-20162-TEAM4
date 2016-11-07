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
	
	private ArrayList<ArrayList<Point>> fullPathList;
	

	public MyCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
		selList = new ArrayList<Socket>();
		fullPathList = new ArrayList<ArrayList<Point>>();
		timer = new Thread(new SwitchTimer());
	}

	public void startConnectedCar_Server() throws Exception {
		// �ֺ��� ������ ��� Ž���ϱ� ���� ���� ���� ������
		new Thread(this).start();

		// Car num��ŭ ã�� ������ ��ٸ�
		System.out.println("Waiting for detecting car...");
		while (socks.size() < Environment._CAR_NUM) ;

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
	protected void CCHPeriod() throws Exception {
		timer.start();

		Point firstLeg = route.get(0);
		// Broadcasting my first leg
		for (int i = 0; i < socks.size(); i++){
			writePacket(i, Environment._RQ_FIRST_LEG);
		}

		// Getting response from other cars
		for (int i = 0; i < socks.size(); i++){
			Point p = (Point) readMsg(i);
			if (firstLeg.isEqual(p)) {
				// CarInfo add
			}
		}
		
		

		timer.join();
	}

	protected void SCHPeriod() throws Exception {
		timer.start();

		int idx = selectionAlg();

		writePacket(idx, Environment._RQ_FULL_LEGS);
		// CarInfo�� �߰�
		ArrayList<Point> temp = (ArrayList<Point>) readMsg(idx);

		timer.join();
	}
		
	public int selectionAlg() {
		return 0;
	}


	// Read & Write Packet
	/**
	 * Packet���� Message�� �о� ��
	 * @param idx
	 * @return Packet�� Message ��ü�� �ѱ�
	 * @throws Exception
	 */
	private Object readMsg(int idx) throws Exception {
		ObjectInputStream in = new ObjectInputStream(socks.get(idx).getInputStream());
		Packet pk = (Packet) in.readObject();
		in.close();
		return pk.getMessage();
	}

	/**
	 * Packet�� ������
	 * @param idx sock�� index
	 * @param requestCode ������ request�� �� �ΰ�
	 * @throws Exception
	 */
	private void writePacket(int idx, int requestCode) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(socks.get(idx).getOutputStream());

		int chnType = 0;
		if (requestCode == Environment._RQ_FIRST_LEG) {
			chnType = Environment._CCH;
			
		} else if (requestCode == Environment._RQ_FULL_LEGS) {
			chnType = Environment._SCH;
		}
		Packet pk = new Packet(chnType, requestCode, null);
		out.writeObject(pk);
		out.close();
	}


	// Getter & Setter
	public ArrayList<Socket> getSocks() {
		return socks;
	}
}
