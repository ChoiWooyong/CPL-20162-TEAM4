package Common;

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
	private Channel channel;
	
	/**
	 * CCH/SCH ���ο� ���� ĳ������ �޸��ؾ� ��
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
