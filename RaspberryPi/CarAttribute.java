
import java.io.Serializable;

public class CarAttribute implements Serializable, Runnable {

	private Point curPos;

	private String num;
	
	private short career;

	private short gender;
	
	private short age;
	
	private short type;
	
	private double maxSpeed;
	
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

	public Point getCurPos() {
		return curPos;
	}

	public void setCurPos(Point pos) {
		curPos = pos;
	}
}
