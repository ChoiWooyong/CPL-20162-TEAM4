package common;

public class SwitchTimer implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(Environment.CHANNEL_SWITCHING_TIME);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
