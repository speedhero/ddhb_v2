package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.hbwebsite.member.entity.UserBrowsedHistory;
import com.huatek.hbwebsite.member.service.UserBrowsedHistoryService;
import java.util.Date;

public class UserBrowsedHistoryServiceImpl extends BaseServiceImpl implements UserBrowsedHistoryService {
	public void saveOrUpdateHistory(String clientFlag, String SH, String RH, String community, Date lastBrowsedDate) {
		UserBrowsedHistory userBrowsedHistory = this.getUserCompareHistory(clientFlag);
		if (userBrowsedHistory == null) {
			userBrowsedHistory = new UserBrowsedHistory();
			userBrowsedHistory.setHistoryId(clientFlag);
			userBrowsedHistory.setSecondHandHouse(SH);
			userBrowsedHistory.setRentHouse(RH);
			userBrowsedHistory.setCommunity(community);
			userBrowsedHistory.setLastBrowsedDate(lastBrowsedDate);
			this.getHibernateTemplate().save(userBrowsedHistory);
		} else {
			userBrowsedHistory.setSecondHandHouse(SH);
			userBrowsedHistory.setRentHouse(RH);
			userBrowsedHistory.setCommunity(community);
			userBrowsedHistory.setLastBrowsedDate(lastBrowsedDate);
			this.getHibernateTemplate().merge(userBrowsedHistory);
		}

	}

	public UserBrowsedHistory getUserCompareHistory(String clientFlag) {
		if (clientFlag == null) {
			return null;
		} else {
			UserBrowsedHistory userBrowsedHistory = (UserBrowsedHistory) this.getHibernateTemplate().get(
					UserBrowsedHistory.class, clientFlag);
			return userBrowsedHistory;
		}
	}
}
