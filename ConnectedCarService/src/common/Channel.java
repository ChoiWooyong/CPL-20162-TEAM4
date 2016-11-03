package common;

/**
 * CCH/SCH를 가지는 Enum 타입이다
 * @author 최우용
 *   
 */
public enum Channel {
	CCH, SCH;

	/**
	 * Enum -> String으로 변환
	 * @param chn
	 * @return String str
	 */
	public static String toString(Channel chn) {
		switch(chn) {
		case CCH:
			return "CCH";
		case SCH:
			return "SCH";
		}
		
		return null;
	}
	
	/**
	 * String -> Enum으로 변환
	 * @param str
	 * @return Enum Channel
	 */
	public static Channel toEnum(String str) {
		if (str == "CCH")
			return CCH;
		else if (str == "SCH")
			return SCH;
		
		return null;
	}
}
