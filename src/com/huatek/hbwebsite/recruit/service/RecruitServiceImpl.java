package com.huatek.hbwebsite.recruit.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.recruit.entity.RecruitPosition;
import com.huatek.hbwebsite.recruit.entity.RecruitPositionType;
import com.huatek.hbwebsite.recruit.service.RecruitService;
import java.util.ArrayList;
import java.util.List;

public class RecruitServiceImpl extends BaseServiceImpl implements RecruitService {
	private static final String GET_ALL_POSITION_TYPE_SQL = " from RecruitPositionType rd where rd.deleteFlag = 0";

	public List<RecruitPositionType> getAllRecruitPositionType() {
		return super.getHibernateTemplate().find(" from RecruitPositionType rd where rd.deleteFlag = 0");
	}

	public CutPageBean getRecruitPositionByType(CutPageBean pageBean, Long type) {
		String totalRowsHsql = "select count(t) from RecruitPosition t where t.positionType.id = " + type
				+ "and t.isPublished = 1 and t.deleteFlag = 0";
		String resultSql = "from RecruitPosition t where t.positionType.id = " + type
				+ "and t.isPublished = 1 and t.deleteFlag = 0 order by t.createTime desc";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		return cutPageBean;
	}

	public RecruitPosition getPositionDetailByPositionId(Long positionId) {
		String resultSql = "from RecruitPosition t where t.id = " + positionId
				+ "and t.isPublished = 1 and t.deleteFlag = 0";
		return (RecruitPosition) super.getHibernateTemplate().find(resultSql).get(0);
	}

	public boolean getIsExist(long positionId, String name, String telephone) {
		String resultSql = "from RecruitCandidate t where t.appliedPosition.id = ? and t.name = ? and t.telephone = ? and t.deleteFlag = ?";
		Object[] objs = new Object[] { Long.valueOf(positionId), name, telephone, Integer.valueOf(0) };
		List reds = this.hibernateTemplate.find(resultSql, objs);
		return reds.size() <= 0;
	}
}
