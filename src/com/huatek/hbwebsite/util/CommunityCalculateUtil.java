package com.huatek.hbwebsite.util;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.community.service.CommunityService;

public class CommunityCalculateUtil {
	private CommunityService communityService = null;

	public synchronized void doCalculate() {
		if (this.communityService == null) {
			this.communityService = (CommunityService) SpringContext.getBean("communityService");
		}

		this.communityService.calculateCurrentMonthPrice();
	}
}
