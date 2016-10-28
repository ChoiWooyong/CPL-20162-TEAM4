package Common;

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
	private Channel channel;
	
	/**
	 * CCH/SCH 여부에 따라 캐스팅을 달리해야 함
	 */
	private Object message;
	
	public Packet(String chnStr, Object msg) {
		channel = Channel.toEnum(chnStr);
		message = msg;
	}

	public Channel getChannel() {
		return channel;
	}

	public Object getMessage() {
		return message;
	}	
}
