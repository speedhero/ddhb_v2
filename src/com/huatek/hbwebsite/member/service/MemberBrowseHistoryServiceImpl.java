package com.huatek.hbwebsite.member.service;

import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.MemberBrowseHistory;
import com.huatek.hbwebsite.member.service.MemberBrowseHistoryService;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import java.util.ArrayList;
import java.util.List;

public class MemberBrowseHistoryServiceImpl extends BaseServiceToImpl implements MemberBrowseHistoryService {
	public List<MemberBrowseHistory> findMemberBrowseHistoryByMember(Long memberId, int objectType, String objectId) {
		String sqlString = "from MemberBrowseHistory t where t.platMemberInfo.id = ? and t.objectType = ? and t.objectId = ?";
		Object[] objs = new Object[] { memberId, Integer.valueOf(objectType), objectId };
		List list = this.hibernateTemplate.find(sqlString, objs);
		return list;
	}

	public CutPageBean getBrowseHistory(CutPageBean cutpageBean, Long memberId, String houseType) {
		String totalRowsHsql = "select count(t) from MemberBrowseHistory t where t.platMemberInfo.id = " + memberId;
		String resultSql = "from MemberBrowseHistory t where t.platMemberInfo.id = " + memberId;
		if (houseType != null && !"".equals(houseType)) {
			totalRowsHsql = totalRowsHsql + " and t.objectType = " + houseType;
			resultSql = resultSql + " and t.objectType = " + houseType;
		}

		totalRowsHsql = totalRowsHsql + "  and t.deleteFlag = 0";
		resultSql = resultSql + " and t.deleteFlag = 0 order by modifiedTime desc";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				cutpageBean, new ArrayList());
		if (cutPageBean.getDataList() != null && cutPageBean.getDataList().size() > 0) {
			for (int i = 0; i < cutPageBean.getDataList().size(); ++i) {
				MemberBrowseHistory browseHistory = (MemberBrowseHistory) cutPageBean.getDataList().get(i);
				if (browseHistory.getObjectType() == 0) {
					HouseSecond community = (HouseSecond) this.getHibernateTemplate().get(HouseSecond.class,
							browseHistory.getObjectId());
					browseHistory.setSecondHouse(community);
				} else if (browseHistory.getObjectType() == 1) {
					HouseRent var10 = (HouseRent) this.getHibernateTemplate().get(HouseRent.class, browseHistory.getObjectId());
					browseHistory.setRentHouse(var10);
				} else if (browseHistory.getObjectType() == 2) {
					Community var11 = (Community) this.getHibernateTemplate().get(Community.class, browseHistory.getObjectId());
					browseHistory.setCommunity(var11);
				}
			}
		}

		return cutPageBean;
	}

	public List<Object> getCountByObjectType(Long memberId) {
		String sqlString = "select t.objectType, count(t) from MemberBrowseHistory t where t.deleteFlag = 0 and t.platMemberInfo.id = "
				+ memberId + " group by t.objectType";
		return this.hibernateTemplate.find(sqlString);
	}
}
