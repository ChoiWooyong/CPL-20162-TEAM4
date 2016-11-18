package common;

public class Environment {
	// For socket communication
	public static final int _PORT_NUM = 8888;
	public static final double MILE_TO_KILO = 1.61;
	
	// For GUI
	public static final String _TITLE = "Connected Car Service (Team WAVE)";
	public static final int _IMAGE_UPDATE_TIME = 1000;
	
	// For WAVE communication
	public static final int _CAR_NUM = 3;
	public static final int _CHANNEL_SWITCHING_TIME = 2000;
	public static final double _ERRORRANGE = 0.001;
	
	// (GeocodeFetcher) For Bing Map API
	public static final String _BING_ROUTE_DATA_URL = "http://dev.virtualearth.net/REST/v1/Routes?";
	public static final String _BING_CUR_IMAGE_URL = "http://dev.virtualearth.net/REST/v1/Imagery/Map/Road/";
	public static final String _KEY = "AmHs8uhFO0ODZi-ng9hzHXCbCAR-ehsfpWyenSZqvNuT8cp7VeCeEVsy7Hf-F-4U";
	
	// (Packet) Make a division for Channel
	public static final int _CCH = 1;
	public static final int _SCH = 2;

	// (Packet) What server want to request
	public static final int _RQ_NONE = 0;
	public static final int _RQ_INFO = 1;
	public static final int _RQ_FIRST_LEG = 2;
	public static final int _RQ_FULL_LEGS = 3;
	public static final int _RQ_SIGNAL = 4;
}
