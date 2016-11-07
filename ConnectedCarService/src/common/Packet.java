package common;

import java.io.Serializable;


/**
 * 차량 간 통신 구현을 위한 Packet이다.
 * @author 최우용
 *
 */
public class Packet implements Serializable {
	
	/**
	 * CCH/SCH 구분용 Enumeration
	 */
	private int channel;
	
	/**
	 * 어떤 정보를 요청할 때 사용, default = 0;
	 */
	private int requestCode;
	
	/**
	 * CCH/SCH 여부에 따라 캐스팅을 달리해야 함
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
