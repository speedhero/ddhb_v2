package com.huatek.hbwebsite.util;

import cn.hshb.web.common.exception.HshbException;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.house.service.HouseSecondService;

import java.util.List;

public class ReduceNoticeUtil {
	private HouseSecondService houseSecondService = null;

	public synchronized void reduceNotice() throws HshbException {
		if (this.houseSecondService == null) {
			this.houseSecondService = (HouseSecondService) SpringContext.getBean("houseSecondService");
		}

		List priceChangeHistoryList = this.houseSecondService.getPriceChangeHistory();
		if (priceChangeHistoryList.size() > 0) {
			this.houseSecondService.doReduceNotice(priceChangeHistoryList);
			this.houseSecondService.checkReduceNotice(priceChangeHistoryList);
		}

	}
}
