package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import common.Car;
import common.CarAttribute;
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
	 * CCH, SCH에 시간 할당
	 */
	private Thread timer;

	/**
	 * 소켓과 풀 패쓰 등 차량 정보를 가지고 있음
	 */
	private ArrayList<CarInfo> carInfo;

	/**
	 * 이미 CCH에서 통신한 차량 패스
	 */
	private ArrayList<Integer> mark;

	/**
	 * 다음 SCH할 index 및 SCH 통신 한 개수
	 */
	private int schCnt = 0;


	public MyCar(String num, Point departure, Point destination) throws IOException {
		super(num, departure, destination);
		
		serv_sock = new ServerSocket(Environment._PORT_NUM);
		carInfo = new ArrayList<CarInfo>();
	}
	
	public MyCar(CarAttribute attr, Point departure, Point destination) throws IOException {
		super(attr, departure, destination);
		
		serv_sock = new ServerSocket(Environment._PORT_NUM);
		carInfo = new ArrayList<CarInfo>();
	}

	public void startConnectedCar_Server() throws Exception {
		// 주변의 차량을 계속 탐색하기 위한 무한 루프 쓰레드
		new Thread(this).start();

		// Car num만큼 찾을 때까지 기다림
		System.out.println("Waiting for detecting car...");

		// 통신 시작
		System.out.println("Starting to communication with other cars...");
		while (true) {
			if (schCnt == Environment._CAR_NUM)
				break;
			//System.out.print("CCC");
			CCHPeriod();
			//System.out.print("SSS");
			SCHPeriod();
		}
		
		System.out.println(selectionAlg() + "번째 차량이 셀렉트됨.");
	}

	
	@Override
	public void run() {
		try {
			for (int i = 0; ; i++) {
				Socket sock = serv_sock.accept();
				carInfo.add(new CarInfo(sock));
				writePacket(i, Environment._RQ_INFO);
				carInfo.get(i).setAttr((CarAttribute) readMsg(i));
			}
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// Method for WAVE communication
	protected void CCHPeriod() throws Exception {
		System.out.println("CCH Period");
		timer = new Thread(new SwitchTimer());
		timer.start();

		Point firstLeg = route.get(0);

		//System.out.println("Broadcast send");
		// Broadcasting my first leg
		for (int i = 0; i < carInfo.size(); i++) {
			if (carInfo.get(i).getState() >= 1)
				continue;
			writePacket(i, Environment._RQ_FIRST_LEG);
		}

		//System.out.println("Broadcast receive");
		// Getting response from other cars
		for (int i = 0; i < carInfo.size(); i++) {
			if (carInfo.get(i).getState() >= 1)
				continue;
			Point p = (Point) readMsg(i);
			carInfo.get(i).goNextState();  // CCH 했다 표시
			if (!firstLeg.isEqual(p)) {
				carInfo.remove(i);
			}
		}

		timer.join();
	}

	protected void SCHPeriod() throws Exception {
		System.out.println("SCH Period");
		timer = new Thread(new SwitchTimer());
		timer.start();

		// Mutex 써야되나..?
		if (schCnt < carInfo.size() && carInfo.get(schCnt).getState() == 1) {
			int idx = schCnt++;

			//System.out.println("request Full legs");
			// Request Full legs
			writePacket(idx, Environment._RQ_FULL_LEGS);

			//System.out.println("read Full legs");
			// Read Full legs 
			CarInfo curCar = carInfo.get(idx);
			Object obj = readMsg(idx);
			if (obj instanceof ArrayList<?>) {
				curCar.setFullPath((ArrayList<Point>) obj);
				carInfo.get(idx).goNextState();
			
				calScore(curCar);
			}
		}

		timer.join();
	}

	
	
	// Selection Algorithm 관련
	/**
	 * 점수계산해서 넣는 것
	 */
	public void calScore(CarInfo car) {
		float score = 0;
		ArrayList<Point> fullPath = car.getFullPath();

		// Route 비교하면서 기본 점수 계산
		int minSize = Math.min(fullPath.size(), route.size());
		for (int i = 0; i < minSize; i++) {
			Point curPath = fullPath.get(i);
			Point curRoute = route.get(i);
			if(curPath.isEqual(curRoute))  // 위도와 경도가 서로 허용오차범위 이내이면 1점 추가
				score += 1;
		}
		
		// Attribute에 따라서 점수 가감
		CarAttribute curAttr = car.getAttr();

		int suit = 0;
		if (curAttr.getCareer() == attr.getCareer())
			suit++;
		if (curAttr.getGender() == attr.getGender())
			suit++;
		if (curAttr.getAge() == attr.getAge())
			suit++;
		if (curAttr.getType() == attr.getType())
			suit++;
		
		// 1개 같을 때 마다 10% 가산점
		score = (float) (score * (1.0 + (suit / 100.0)));

		car.setScore(score);
	}

	/**
	 * CarInfo에서 Score 중 최고 값을 찾음
	 * @return max 값을 가진 index
	 */
	public int selectionAlg() {
		float maxscore = 0;  // score의 max 값
		int maxindex = 0;  // max score을 가진 carInfo의 index

		for (int index = 0; index < carInfo.size(); index++)
		{
			float score = carInfo.get(index).getScore();
			if(maxscore < score) {
				maxscore = score;
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
		ObjectInputStream in = new ObjectInputStream(carInfo.get(idx).getSock().getInputStream());
		Packet pk = (Packet) in.readObject();
		return pk.getMessage();
	}

	/**
	 * Packet을 보낸다
	 * @param idx CarInfo의 index
	 * @param requestCode 무엇을 요청할 것 인가
	 * @throws Exception
	 */
	private void writePacket(int idx, int requestCode) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(carInfo.get(idx).getSock().getOutputStream());
		
		int chnType = 0;
		if (requestCode == Environment._RQ_FIRST_LEG || requestCode == Environment._RQ_INFO) {
			chnType = Environment._CCH;
			
		} else if (requestCode == Environment._RQ_FULL_LEGS) {
			chnType = Environment._SCH;
		}
		
		Packet pk = new Packet(chnType, requestCode, null);
		out.writeObject(pk);
	}
}
