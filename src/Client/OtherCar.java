package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Common.Car;
import Common.Environment;
import Common.Point;

public class OtherCar extends Car {
	
	private Socket sock;
	
	public OtherCar(String name, String num, Point departure, Point destination) {
		super(name, num, departure, destination);
	}
	
	public void startConnectedCar() {
		try {
			sock = new Socket("127.0.0.1", Environment.PORT_NUM);
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			out.writeObject(null);  // legs로 바꾸자
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
