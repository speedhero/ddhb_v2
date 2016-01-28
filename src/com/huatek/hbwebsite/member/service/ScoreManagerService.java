package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.member.entity.IntegralTable;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.util.List;

public interface ScoreManagerService extends BaseService {
	List<IntegralTable> getIntegralTableByUserId(Long var1);

	CutPageBean getAllHistory(CutPageBean var1, Long var2);

	List<PlatMemberInfo> getPlatMemberInfoByAccName(String var1);
}
