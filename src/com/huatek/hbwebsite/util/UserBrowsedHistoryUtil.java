package com.huatek.hbwebsite.util;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.member.entity.HistoryItem;
import com.huatek.hbwebsite.member.entity.UserBrowsedHistory;
import com.huatek.hbwebsite.member.service.UserBrowsedHistoryService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UserBrowsedHistoryUtil {
	private static Map<String, HistoryItem> historyMap = new HashMap();
	private static UserBrowsedHistoryUtil userBrowseredUtil = new UserBrowsedHistoryUtil();
	public static final String SH = "secondHouseHistory";
	public static final String RH = "rentHouseHistory";
	public static final String COMMUNITY = "communityHistory";
	private UserBrowsedHistoryService userBrowsedHistoryService = null;

	public static UserBrowsedHistoryUtil getInstance() {
		return userBrowseredUtil;
	}

	public synchronized String getHistoryString(String clientIP, String type) {
		HistoryItem historyItem = null;
		historyItem = (HistoryItem) historyMap.get(clientIP);
		if (historyItem == null) {
			if (this.userBrowsedHistoryService == null) {
				this.userBrowsedHistoryService = (UserBrowsedHistoryService) SpringContext.getBean("userBrowsedHistoryService");
			}

			UserBrowsedHistory userBrowsedHistory = this.userBrowsedHistoryService.getUserCompareHistory(clientIP);
			if (userBrowsedHistory != null) {
				historyItem = new HistoryItem();
				historyItem.setCommunity(userBrowsedHistory.getCommunity());
				historyItem.setHouseRent(userBrowsedHistory.getRentHouse());
				historyItem.setHouseSecond(userBrowsedHistory.getSecondHandHouse());
				historyItem.setLastBrowsedDate(userBrowsedHistory.getLastBrowsedDate());
				historyMap.put(clientIP, historyItem);
			}
		}

		if (historyItem != null) {
			historyItem.setLastBrowsedDate(new Date());
			if (type.equals("secondHouseHistory")) { return historyItem.getHouseSecond(); }

			if (type.equals("rentHouseHistory")) { return historyItem.getHouseRent(); }

			if (type.equals("communityHistory")) { return historyItem.getCommunity(); }
		}

		return null;
	}

	public synchronized void updateHistoryString(String clientIP, String type, String content) {
		HistoryItem historyItem = null;
		historyItem = (HistoryItem) historyMap.get(clientIP);
		if (historyItem == null) {
			historyItem = new HistoryItem();
		}

		if (type.equals("secondHouseHistory")) {
			historyItem.setHouseSecond(content);
		} else if (type.equals("rentHouseHistory")) {
			historyItem.setHouseRent(content);
		} else if (type.equals("communityHistory")) {
			historyItem.setCommunity(content);
		}

		historyItem.setLastBrowsedDate(new Date());
		historyMap.put(clientIP, historyItem);
	}

	public void saveToDatabase() {
		Set keySet = historyMap.keySet();
		if (keySet != null) {
			Date currentDate = new Date();
			HistoryItem historyItem = null;
			Iterator var5 = keySet.iterator();

			while (var5.hasNext()) {
				String key = (String) var5.next();
				historyItem = (HistoryItem) historyMap.get(key);
				if (historyItem != null && currentDate.getTime() - historyItem.getLastBrowsedDate().getTime() >= 720000L) {
					if (this.userBrowsedHistoryService == null) {
						this.userBrowsedHistoryService = (UserBrowsedHistoryService) SpringContext
								.getBean("userBrowsedHistoryService");
					}

					this.userBrowsedHistoryService.saveOrUpdateHistory(key, historyItem.getHouseSecond(),
							historyItem.getHouseRent(), historyItem.getCommunity(), historyItem.getLastBrowsedDate());
					historyMap.remove(key);
				}
			}

		}
	}
}
