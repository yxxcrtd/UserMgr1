package cn.edustar.usermgr;

import cn.edustar.usermgr.service.UserService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 11, 2008 5:32:08 PM
 */
public final class TimeOutClearEngine {
	private static long TIMEOUT = 0;
	private static threadClass thread = null;
	private static UserService user_svc = null;

	public static void InitEngine(UserService user_svc, long iOutSec) {
		TimeOutClearEngine.user_svc = user_svc;
		TIMEOUT = iOutSec * 1000;
		thread = new threadClass(129);
		thread.start();
	}

	private static class threadClass extends Thread {
		@SuppressWarnings("unused")
		long minPrime;

		threadClass(long minPrime) {
			this.minPrime = minPrime;
		}

		public void run() {
			while (true) {
				user_svc.timeoutClear(TIMEOUT);
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void CloseEngine() {
		try {
			thread.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
