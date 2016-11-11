package common;

public class getGpsInfo {
	static {
		System.loadLibrary("gps"); //libgps.so (.so file)
	}
	public native double[] makeArray();
	public native double[] getGps(double[]arr);
}