package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Common.Car;
import Common.Environment;
import Common.Packet;
import Common.Point;

public class MyCar extends Car {
	
	private ServerSocket serv_sock;
	private Socket[] socks;
	
	private ArrayList<Socket> selList;
	
	
	public MyCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
	}
	
	public void findNearCars() {
		try {
			serv_sock = new ServerSocket(Environment.PORT_NUM);
			socks = new Socket[Environment.CAR_NUM];
			for (int i = 0; i < Environment.CAR_NUM; i++) {  // 일단은 3대 다 첨부터 같이 가는 걸로
				socks[i] = serv_sock.accept();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startConnectedCar() throws ClassNotFoundException, IOException {
		selList = new ArrayList<Socket>();
		while(true) {
			try {
				CCHPeriod();
				SCHPeriod();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int selectionAlg() {
		return 0;
	}
	
	public void CCHPeriod() throws InterruptedException, IOException, ClassNotFoundException {
		ObjectInputStream in;
		ObjectOutputStream out;
		
		for (Socket sock : socks) {
			try {
				out = new ObjectOutputStream(sock.getOutputStream());
				Packet pk = new Packet("CCH", getRoute().get(0));
				out.writeObject(pk);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (Socket sock : socks) {
			in = new ObjectInputStream(sock.getInputStream());
			Packet pk = (Packet) in.readObject();
			int yn = (int) (pk.getMessage());
			
			if (!selList.contains(socks)) {
				selList.add(sock);
			}
		}

		Thread.sleep(Environment.CHANNEL_SWITCHING_TIME);
	}
	
	public void SCHPeriod() throws InterruptedException  {
		Socket sock = socks[selectionAlg()];
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			out.writeObject("전체 패스 정보");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(Environment.CHANNEL_SWITCHING_TIME);
	}
}
