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

	private ServerSocket serv_sock;

	private Thread timer;

	private ArrayList<CarInfo> carInfo;

	private ArrayList<Integer> mark;

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

	public MyCar(CarAttribute attr, Point destination) throws IOException {
		super(attr, destination);
		
		serv_sock = new ServerSocket(Environment._PORT_NUM);
		carInfo = new ArrayList<CarInfo>();
	}
	
	public MyCar(CarAttribute attr) throws IOException {
		super(attr);
		
		serv_sock = new ServerSocket(Environment._PORT_NUM);
		carInfo = new ArrayList<CarInfo>();
	}


	public void startConnectedCar_Server() throws Exception {

		Thread accepter = new Thread(this);
		accepter.start();

		System.out.println("Waiting for detecting car...");


		System.out.println("Starting to communication with other cars...");
		while (true) {
			if (schCnt == Environment._CAR_NUM)
				break;
			CCHPeriod();
			SCHPeriod();
		}
		
		System.out.println(selectionAlg() + "is selected");
		accepter.stop();
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

		// Broadcasting my first leg
		for (int i = 0; i < carInfo.size(); i++) {
			if (carInfo.get(i).getState() >= 1)
				continue;
			writePacket(i, Environment._RQ_FIRST_LEG);
		}

		// Getting response from other cars
		for (int i = 0; i < carInfo.size(); i++) {
			if (carInfo.get(i).getState() >= 1)
				continue;
			Point p = (Point) readMsg(i);
			carInfo.get(i).goNextState(); 
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

		if (schCnt < carInfo.size() && carInfo.get(schCnt).getState() == 1) {
			int idx = schCnt++;

			// Request Full legs
			writePacket(idx, Environment._RQ_FULL_LEGS);

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

	
	

	public void calScore(CarInfo car) {
		float score = 0;
		ArrayList<Point> fullPath = car.getFullPath();


		int minSize = Math.min(fullPath.size(), route.size());
		for (int i = 0; i < minSize; i++) {
			Point curPath = fullPath.get(i);
			Point curRoute = route.get(i);
			if(curPath.isEqual(curRoute)) 
				score += 1;
		}
		

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
		
		score = (float) (score * (1.0 + (suit / 100.0)));

		car.setScore(score);
	}


	public int selectionAlg() {
		float maxscore = 0;  
		int maxindex = 0; 

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



	private Object readMsg(int idx) throws Exception {
		ObjectInputStream in = new ObjectInputStream(carInfo.get(idx).getSock().getInputStream());
		Packet pk = (Packet) in.readObject();
		return pk.getMessage();
	}


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
