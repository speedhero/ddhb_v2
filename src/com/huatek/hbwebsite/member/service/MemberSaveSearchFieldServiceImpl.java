package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.member.entity.MemberSavedSearchContent;
import com.huatek.hbwebsite.member.service.MemberSaveSearchFieldService;
import java.util.ArrayList;
import java.util.List;

public class MemberSaveSearchFieldServiceImpl extends BaseServiceImpl implements MemberSaveSearchFieldService {
	private static final String IS_EXIST = " from MemberSavedSearchContent mssc where mssc.url = ? and mssc.memberId = ? and mssc.type = ? and mssc.deleteFlag = 0";
	private static final String LIST_FIELD = " from MemberSavedSearchContent mssc where where mssc.memberId = ? mssc.deleteFlag = 0 order by mssc.createDate desc";
	private static final String DELETE_FIELD = "update MemberSavedSearchContent mssc set mssc.deleteFlag=1 where mssc.no = ?";

	public boolean isUrlExist(String url, Long memberId, int type) {
		Object[] paramArray = new Object[] { url, memberId, Integer.valueOf(type) };
		List list = this.hibernateTemplate
				.find(
						" from MemberSavedSearchContent mssc where mssc.url = ? and mssc.memberId = ? and mssc.type = ? and mssc.deleteFlag = 0",
						paramArray);
		return list.size() > 0;
	}

	public List<MemberSavedSearchContent> getAllSavedFieldList(Long memberId) {
		List list = this.hibernateTemplate
				.find(
						" from MemberSavedSearchContent mssc where where mssc.memberId = ? mssc.deleteFlag = 0 order by mssc.createDate desc",
						memberId);
		return list;
	}

	public boolean deleteSavedFieldList(String id) {
		this.hibernateTemplate.bulkUpdate("update MemberSavedSearchContent mssc set mssc.deleteFlag=1 where mssc.no = ?",
				id);
		return true;
	}

	public CutPageBean getCutPageBean(CutPageBean pageBean, Long userId) {
		pageBean.setPageSize(5);
		String totalRowsHsql = "select count(mssc) from MemberSavedSearchContent mssc where mssc.memberId = " + userId
				+ " and mssc.deleteFlag = 0 order by mssc.createDate desc";
		String resultSql = " from MemberSavedSearchContent mssc where mssc.memberId = " + userId
				+ " and mssc.deleteFlag = 0 order by mssc.createDate desc";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		return cutPageBean;
	}
}
