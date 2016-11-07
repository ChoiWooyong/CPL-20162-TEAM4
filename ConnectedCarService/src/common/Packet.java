package common;

import java.io.Serializable;


/**
 * ���� �� ��� ������ ���� Packet�̴�.
 * @author �ֿ��
 *
 */
public class Packet implements Serializable {
	
	/**
	 * CCH/SCH ���п� Enumeration
	 */
	private int channel;
	
	/**
	 * � ������ ��û�� �� ���, default = 0;
	 */
	private int requestCode;
	
	/**
	 * CCH/SCH ���ο� ���� ĳ������ �޸��ؾ� ��
	 */
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
