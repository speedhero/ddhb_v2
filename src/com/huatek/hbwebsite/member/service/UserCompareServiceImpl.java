package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.hbwebsite.member.entity.UserCompareHistory;
import com.huatek.hbwebsite.member.service.UserCompareService;
import java.util.Date;

public class UserCompareServiceImpl extends BaseServiceImpl implements UserCompareService {
	public void saveOrUpdateHistory(String clientFlag, String SH, String RH, String community, Date modifiedDate) {
		UserCompareHistory compareHistory = this.getUserCompareHistory(clientFlag);
		if (compareHistory == null) {
			compareHistory = new UserCompareHistory();
			compareHistory.setHistoryId(clientFlag);
			compareHistory.setSecondHandHouse(SH);
			compareHistory.setRentHouse(RH);
			compareHistory.setCommunity(community);
			compareHistory.setLastModifiedDate(modifiedDate);
			this.getHibernateTemplate().save(compareHistory);
		} else {
			compareHistory.setSecondHandHouse(SH);
			compareHistory.setRentHouse(RH);
			compareHistory.setCommunity(community);
			compareHistory.setLastModifiedDate(modifiedDate);
			this.getHibernateTemplate().merge(compareHistory);
		}

	}

	public UserCompareHistory getUserCompareHistory(String clientFlag) {
		if (clientFlag == null) {
			return null;
		} else {
			UserCompareHistory compareHistory = (UserCompareHistory) this.getHibernateTemplate().get(
					UserCompareHistory.class, clientFlag);
			return compareHistory;
		}
	}
}
