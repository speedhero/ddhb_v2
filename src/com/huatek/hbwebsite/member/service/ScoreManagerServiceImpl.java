package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.member.entity.IntegralTable;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.ScoreManagerService;
import java.util.ArrayList;
import java.util.List;

public class ScoreManagerServiceImpl extends BaseServiceImpl implements ScoreManagerService {
	public List<IntegralTable> getIntegralTableByUserId(Long id) {
		String sqlString = "from IntegralTable t where t.platMemberInfo.id = ? and t.deleteFlag = 0";
		return this.hibernateTemplate.find(sqlString, id);
	}

	public CutPageBean getAllHistory(CutPageBean pageBean, Long userId) {
		String totalRowsHsql = "select count(t) from IntegralHistory t where t.platMemberInfo.id = " + userId
				+ " and t.deleteFlag = 0";
		String resultSql = "from IntegralHistory t where t.platMemberInfo.id = " + userId + " and t.deleteFlag = 0";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		return cutPageBean;
	}

	public List<PlatMemberInfo> getPlatMemberInfoByAccName(String accName) {
		String sqlString = "from PlatMemberInfo p where p.accName = ?";
		return this.hibernateTemplate.find(sqlString, accName);
	}
}
