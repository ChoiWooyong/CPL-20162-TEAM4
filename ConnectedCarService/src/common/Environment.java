package common;

public class Environment {
	public static final int _PORT_NUM = 8888;
	public static final int _CAR_NUM = 1;
	public static final int _CHANNEL_SWITCHING_TIME = 2000;
	
	public static final String BING_ROUTE_URL="http://dev.virtualearth.net/REST/v1/Routes?";
	public static final String KEY = "AmHs8uhFO0ODZi-ng9hzHXCbCAR-ehsfpWyenSZqvNuT8cp7VeCeEVsy7Hf-F-4U";
	
	public static final double ERRORRANGE = 0.001;//위치범위가 10m안으로 들어오면  

	public static final String _BING_ROUTE_URL="http://dev.virtualearth.net/REST/v1/Routes?";
	public static final String _KEY = "AmHs8uhFO0ODZi-ng9hzHXCbCAR-ehsfpWyenSZqvNuT8cp7VeCeEVsy7Hf-F-4U";
	
	// Packet에 싣을 메시지 정보
	public static final int _CCH = 1;
	public static final int _SCH = 2;

	// requestCode, 통신에서 요청할 때 사용
	public static final int _RQ_NONE = 0;
	public static final int _RQ_INFO = 1;
	public static final int _RQ_FIRST_LEG = 2;
	public static final int _RQ_FULL_LEGS = 3;
}