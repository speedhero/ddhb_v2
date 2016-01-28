package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

public interface SubscribeInfoService extends BaseService {
	Boolean checkEmailIfExist(String var1);

	void saveSubScribeInfo(String var1, PlatMemberInfo var2);

	HouseAppraise getAppraiseBySearchno(String var1);

	HouseAppraise getAppraiseByHouseNo(String var1);
}
