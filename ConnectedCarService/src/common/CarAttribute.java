package common;

import java.io.Serializable;

public class CarAttribute implements Serializable{

	/**
	 * �� ��ȣ
	 */
	private String num;

	
	
	public CarAttribute(String num) {
		this.num = num;
	}
	
	
	
	// Getter & Setter
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	
}
