package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

public class CleanCompareAndHistoryMapServletThread extends Thread {
	public void run() {
		try {
			Thread.sleep(480000L);
			UserCompareUtil.getInstance().saveToDatabase();
			UserBrowsedHistoryUtil.getInstance().saveToDatabase();

			while (true) {
				Thread.sleep(480000L);
				UserCompareUtil.getInstance().saveToDatabase();
				UserBrowsedHistoryUtil.getInstance().saveToDatabase();
			}
		} catch (InterruptedException var2) {
			super.run();
		}
	}
}
