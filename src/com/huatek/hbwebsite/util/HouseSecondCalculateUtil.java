package com.huatek.hbwebsite.util;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.house.service.HouseSecondService;

public class HouseSecondCalculateUtil {
	private HouseSecondService houseSecondService = null;

	public synchronized void houseSecondCalculate() {
		if (this.houseSecondService == null) {
			this.houseSecondService = (HouseSecondService) SpringContext.getBean("houseSecondService");
		}

		this.houseSecondService.addCurrentMonthPrice();
	}
}
