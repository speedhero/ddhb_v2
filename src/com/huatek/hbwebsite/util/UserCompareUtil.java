package com.huatek.hbwebsite.util;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.member.entity.CompareItem;
import com.huatek.hbwebsite.member.entity.UserCompareHistory;
import com.huatek.hbwebsite.member.service.UserCompareService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UserCompareUtil {
	private static Map<String, CompareItem> compareMap = new HashMap<String, CompareItem>();
	private static UserCompareUtil userCompareUtil = new UserCompareUtil();
	private UserCompareService userCompareService = null;
	public static final String SH = "secondHouseCompare";
	public static final String RH = "rentHouseCompare";
	public static final String COMMUNITY = "communityCompare";

	public static UserCompareUtil getInstance() {
		return userCompareUtil;
	}

	/**
	 * 
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param type
	 * @return
	 * @see 
	 */
	public synchronized String getCompareString(String clientFlag, String type) {
		CompareItem compareItem = null;
		compareItem = (CompareItem) compareMap.get(clientFlag);
		if (compareItem == null) {
			if (this.userCompareService == null) {
				this.userCompareService = (UserCompareService) SpringContext.getBean("userCompareService");
			}

			UserCompareHistory compareHistory = this.userCompareService.getUserCompareHistory(clientFlag);
			if (compareHistory != null) {
				compareItem = new CompareItem();
				compareItem.setCommunity(compareHistory.getCommunity());
				compareItem.setHouseRent(compareHistory.getRentHouse());
				compareItem.setHouseSecond(compareHistory.getSecondHandHouse());
				compareItem.setLastModifiedDate(compareHistory.getLastModifiedDate());
				compareMap.put(clientFlag, compareItem);
			}
		}

		if (compareItem != null) {
			compareItem.setLastModifiedDate(new Date());
			if (type.equals("secondHouseCompare")) { return compareItem.getHouseSecond(); }
			if (type.equals("rentHouseCompare")) { return compareItem.getHouseRent(); }
			if (type.equals("communityCompare")) { return compareItem.getCommunity(); }
		}

		return null;
	}

	public void clearCompareString(String clientIP, String type) {
		this.saveOrUpdateCompareString(clientIP, type, (String) null);
	}

	public synchronized void saveOrUpdateCompareString(String clientIP, String type, String content) {
		CompareItem compareItem = null;
		compareItem = (CompareItem) compareMap.get(clientIP);
		if (compareItem == null) {
			compareItem = new CompareItem();
		}

		if (type.equals("secondHouseCompare")) {
			compareItem.setHouseSecond(content);
		} else if (type.equals("rentHouseCompare")) {
			compareItem.setHouseRent(content);
		} else if (type.equals("communityCompare")) {
			compareItem.setCommunity(content);
		}

		compareItem.setLastModifiedDate(new Date());
		compareMap.put(clientIP, compareItem);
	}

	public void saveToDatabase() {
		Set keySet = compareMap.keySet();
		if (keySet != null) {
			Date currentDate = new Date();
			CompareItem compareItem = null;
			Iterator var5 = keySet.iterator();

			while (var5.hasNext()) {
				String key = (String) var5.next();
				compareItem = (CompareItem) compareMap.get(key);
				if (compareItem != null && currentDate.getTime() - compareItem.getLastModifiedDate().getTime() >= 10000L) {
					if (this.userCompareService == null) {
						this.userCompareService = (UserCompareService) SpringContext.getBean("userCompareService");
					}

					this.userCompareService.saveOrUpdateHistory(key, compareItem.getHouseSecond(), compareItem.getHouseRent(),
							compareItem.getCommunity(), compareItem.getLastModifiedDate());
					compareMap.remove(key);
				}
			}

		}
	}
}
