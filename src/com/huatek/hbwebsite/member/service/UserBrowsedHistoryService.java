package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.hbwebsite.member.entity.UserBrowsedHistory;
import java.util.Date;

public interface UserBrowsedHistoryService extends BaseService {
	void saveOrUpdateHistory(String var1, String var2, String var3, String var4, Date var5);

	UserBrowsedHistory getUserCompareHistory(String var1);
}
