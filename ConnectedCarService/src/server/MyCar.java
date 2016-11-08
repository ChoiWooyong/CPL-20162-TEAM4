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
	 * ��� socks
	 */
	private ArrayList<Socket> socks;

	/**
	 * CCH, SCH�� �ð� �Ҵ�
	 */
	private Thread timer;

	private ArrayList<CarInfo> carInfo;

	/**
	 * �̹� CCH���� ����� ���� �н�
	 */
	private ArrayList<Integer> mark;

	private int cnt = 0;

	//car info (socket���� , full path list, score ) selList���ִ°͸�


	public MyCar(String name, String num, Point departure, Point destination) throws IOException {
		super(name, num, departure, destination);
		serv_sock = new ServerSocket(Environment._PORT_NUM);
		serv_sock.setSoTimeout(0);
		socks = new ArrayList<Socket>();
		carInfo = new ArrayList<CarInfo>();
		timer = new Thread(new SwitchTimer());
	}

	public void startConnectedCar_Server() throws Exception {
		// �ֺ��� ������ ��� Ž���ϱ� ���� ���� ���� ������
		new Thread(this).start();

		// Car num��ŭ ã�� ������ ��ٸ�
		System.out.println("Waiting for detecting car...");
		while (socks.size() < Environment._CAR_NUM)
			Thread.sleep(500);

		// ��� ����
		System.out.println("Starting to communication with other cars...");
		//while (true) {
		System.out.println("CCH");
		CCHPeriod();
		System.out.println("SCH");
		SCHPeriod();
		//}
	}

	@Override
	public void run() {
		try {
			while (true) {
				socks.add(serv_sock.accept());
				System.out.println("A car is detected. " + socks.size());
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
				carInfo.add(new CarInfo(i));
				// �ߺ� �Ÿ���
			}
		}

		timer.join();
	}

	protected void SCHPeriod() throws Exception {
		timer.start();

		if (cnt < carInfo.size()) {
			int idx = cnt++;  // �ָ����� ��žȵǴ°� ����

			// Request Full legs
			writePacket(idx, Environment._RQ_FULL_LEGS);

			// Read Full legs 
			CarInfo selInfo = carInfo.get(idx);
			selInfo.setFullPath((ArrayList<Point>) readMsg(idx));
			calScore(selInfo);
		}

		timer.join();
	}

	
	
	// Selection Algorithm ����
	/**
	 * ��������ؼ� �ִ� ��
	 */
	public void calScore(CarInfo car) {
		int score = 0;
		ArrayList<Point> fullPath = car.getFullPath();

		for (int i = 0; i < fullPath.size(); i++) {
			Point curPath = fullPath.get(i);
			Point curRoute = route.get(i);
			if(curPath.isEqual(curRoute))  // ������ �浵�� ���� ���������� �̳��̸� 1�� �߰�
				score += 1;
		}

		car.setScore(score);
	}

	/**
	 * CarInfo���� Score �� �ְ� ���� ã��
	 * @return max ���� ���� index
	 */
	public int selectionAlg() {
		int maxscore = 0;  // score�� max��
		int maxindex = 0;  // max score�� ���� carInfo�� index

		for (int index = 0; index < carInfo.size(); index++)
		{
			int score = carInfo.get(index).getScore();
			if(maxscore < score) {
				maxscore = score;
				maxindex = index;
			}
		}

		return maxindex;
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
