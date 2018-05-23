package com.zzr.util.common;

public class EntityUtil {

	private static final int RANDOM_COUNT = 1000;
	private static final long baseTime = 1420041600000L;
	private static long lastTime = System.currentTimeMillis();
	private static Integer sufint = 0;
	
	public static synchronized long getAutoId(){
			
		synchronized (sufint) {
			long currentTime = System.currentTimeMillis();
			if(lastTime!=currentTime) {
				lastTime = currentTime;
				sufint = 0;
			} else {
				sufint ++;
				if(sufint>=RANDOM_COUNT) {
					try {
						System.out.println("********************************************");
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lastTime ++;
					sufint = 0;
				}
			}
		}
		long result = (lastTime - baseTime) * 1000  + sufint;
		return result;
	}
}
