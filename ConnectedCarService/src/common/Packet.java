package common;

import java.io.Serializable;


public class Packet implements Serializable {
	

	private int channel;

	private int requestCode;

	private Object message;
	
	public Packet(int chn, int reqCode, Object msg) {
		channel = chn;
		requestCode = reqCode;
		message = msg;
	}

	public int getChannel() {
		return channel;
	}
	
	public int getRequestCode() {
		return requestCode;
	}

	public Object getMessage() {
		return message;
	}	
}
