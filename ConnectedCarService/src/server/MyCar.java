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
	 * 모든 socks
	 */
	private ArrayList<Socket> socks;

	/**
	 * CCH, SCH에 시간 할당
	 */
	private Thread timer;

	private ArrayList<CarInfo> carInfo;
	
	/**
	 * 이미 CCH에서 통신한 차량 패스
	 */
	private ArrayList<Integer> mark;
	
	private int cnt = 0;

	//car info (socket정보 , full path list, score ) selList에있는것만


	public MyCar(String name, String num, Point departure, Point destination) throws IOException {
		super(name, num, departure, destination);
		serv_sock = new ServerSocket(Environment._PORT_NUM);
		serv_sock.setSoTimeout(0);
		socks = new ArrayList<Socket>();
		carInfo = new ArrayList<CarInfo>();
		timer = new Thread(new SwitchTimer());
	}

	public void startConnectedCar_Server() throws Exception {
		// 주변의 차량을 계속 탐색하기 위한 무한 루프 쓰레드
		new Thread(this).start();

		// Car num만큼 찾을 때까지 기다림
		System.out.println("Waiting for detecting car...");
		while (socks.size() < Environment._CAR_NUM) ;

		// 통신 시작
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
				carInfo.add(new CarInfo(i));
				// 중복 거르기
			}
		}

		timer.join();
	}

	protected void SCHPeriod() throws Exception {
		timer.start();

		if (cnt < carInfo.size()) {
			int idx = cnt++;  // 멀리가서 통신안되는거 배제

			// Request Full legs
			writePacket(idx, Environment._RQ_FULL_LEGS);

			// Read Full legs 
			CarInfo selInfo = carInfo.get(idx);
			selInfo.setFullpath((ArrayList<Point>) readMsg(idx));
			selectScore(selInfo);
		}

		timer.join();
	}

	/**
	 * 점수계산해서 넣는 것
	 */
	public void selectScore(CarInfo car) {

		int score=0;
		ArrayList<Point> temppoint = car.getFullpath();

		for(int j=0 ; j<temppoint.size() ; j++)
		{
			if(Math.abs(temppoint.get(j).getLatitude()-route.get(j).getLatitude()) < Environment.ERRORRANGE)
				score += 1;
			if(Math.abs(temppoint.get(j).getLongitude()-route.get(j).getLatitude()) < Environment.ERRORRANGE)
				score += 1;
		}

		car.setScore(score);
	}

	/**
	 * maxscore max값비교하는거
	 * maxindex score가 max인 값의 carInfolist에서 index
	 * @return
	 */
	public int selectionAlg(){

		int score=0;
		int maxscore=0;
		int maxindex = 0;
		ArrayList<Point> temppoint;

		for(int index=0 ; index<carInfo.size() ; index++)
		{
			CarInfo car = carInfo.get(index);
			score = car.getScore();
			if(maxscore<score)
			{
				maxscore=score;
				maxindex = index;
			}
		}

		return maxindex;
	}


	// Read & Write Packet
	/**
	 * Packet에서 Message를 읽어 옴
	 * @param idx
	 * @return Packet의 Message 객체를 넘김
	 * @throws Exception
	 */
	private Object readMsg(int idx) throws Exception {
		ObjectInputStream in = new ObjectInputStream(socks.get(idx).getInputStream());
		Packet pk = (Packet) in.readObject();
		in.close();
		return pk.getMessage();
	}

	/**
	 * Packet을 보낸다
	 * @param idx sock의 index
	 * @param requestCode 무엇을 request할 것 인가
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
