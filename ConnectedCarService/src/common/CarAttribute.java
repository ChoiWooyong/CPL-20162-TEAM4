package common;

import java.io.Serializable;

public class CarAttribute implements Serializable, Runnable {

	/**
	 * 차 번호
	 */
	private String num;
	
	/**
	 * career = 1 : 운전 경력 2년 이하 
	 * career = 2 : 운전 경력 2년 ~ 5년 
	 * career = 3 : 운전 경력 5년 이상
	 */
	private short career;
	
	/**
	 * gender = 1 : 남성
	 * gender = 2 : 여성
	 */
	private short gender;
	
	private short age;
	
	/**
	 * type = 1 : 소형
	 * type = 2 : 중형
	 * type = 3 : 대형
	 */
	private short type;
	
	private double maxSpeed;
	
	// 운전 경력, 운전 평균 속도, 성별, 차종, 나이

	public CarAttribute(String num) {
		this.num = num;
		this.career = -1;
		this.gender = -1;
		this.age = -1;
		this.type = -1;

		new Thread(this).start();
	}
	
	public CarAttribute(String num, short career, short gender, short age, short type) {
		this.num = num;
		this.career = career;
		this.gender = gender;
		this.age = age;
		this.type = type;
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		//while(true) {
		//	if maxSpeed 
		//	sleep(2000);
		//}
	}
	
	// Getter & Setter
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public short getCareer() {
		return career;
	}

	public void setCareer(short career) {
		this.career = career;
	}

	public short getGender() {
		return gender;
	}

	public void setGender(short gender) {
		this.gender = gender;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
