package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import common.Car;
import common.Environment;
import common.Packet;
import common.Point;

public class OtherCar extends Car {

	/**
	 * 서버와의 소켓
	 */
	private Socket sock;

	public OtherCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
	}

	public void startConnectedCar_Client(String serv_ip)  throws InterruptedException, IOException {
		sock = new Socket(serv_ip, Environment.PORT_NUM);

		while (true) {
			CCHPeriod();
			SCHPeriod();
		}
	}

	@Override
	protected void CCHPeriod() throws InterruptedException {
		timer.start();

		// 할 사람? 읽고 판단
		// 본인 첫번쨰 leg 씀

		timer.join();
	}

	@Override
	protected void SCHPeriod() throws InterruptedException {
		timer.start();

		// 풀 path 내놔 읽음
		// 풀 path 씀

		timer.join();
	}


	@Override
	protected void readPacket(Socket sock, Point p) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
		Packet pk = (Packet) in.readObject();
		p = (Point) pk.getMessage();
		in.close();
	}

	@Override
	protected void readPacket(Socket sock, ArrayList<Point> plist) throws IOException, ClassNotFoundException {
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
}
