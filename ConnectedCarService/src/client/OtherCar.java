package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Car;
import common.Environment;
import common.Point;

public class OtherCar extends Car {
	
	/**
	 * �������� ����
	 */
	private Socket sock;
	
	public OtherCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
	}

	public void startConnectedCar_Client(String serv_ip) {
		try {
			sock = new Socket(serv_ip, Environment.PORT_NUM);
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			out.writeObject(null);  // legs�� �ٲ���
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
