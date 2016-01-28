package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.hbwebsite.member.entity.UserCompareHistory;
import java.util.Date;

public interface UserCompareService extends BaseService {
	void saveOrUpdateHistory(String var1, String var2, String var3, String var4, Date var5);

	UserCompareHistory getUserCompareHistory(String var1);
}
