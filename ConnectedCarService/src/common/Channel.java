package common;

/**
 * CCH/SCH�� ������ Enum Ÿ���̴�
 * @author �ֿ��
 *   
 */
public enum Channel {
	CCH, SCH;

	/**
	 * Enum -> String���� ��ȯ
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
	 * String -> Enum���� ��ȯ
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
