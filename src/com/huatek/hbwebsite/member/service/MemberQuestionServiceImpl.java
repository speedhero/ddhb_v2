package com.huatek.hbwebsite.member.service;

import com.huatek.authority.factory.DataAuthority;
import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import com.huatek.hbwebsite.member.service.MemberQuestionService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

public class MemberQuestionServiceImpl extends BaseServiceImpl implements MemberQuestionService {
	private static final String GET_UNSYNCRENTQUESTION = " from QuestionStategy qs where qs.deleteFlag = 0 and qs.synchronizedFlag = 0";
	private static final int PAGE_SIZE = 5;

	public Long getAllQuestionCount(Long id) {
		String totalRowsHsql = "select count(t) from QuestionStategy t where t.user.id = " + id + "and t.deleteFlag = 0";
		List resultList = this.hibernateTemplate.find(totalRowsHsql);
		Long count = (Long) resultList.get(0);
		return count;
	}

	public CutPageBean getQuestionListByType(CutPageBean pageBean, Long id, int type) {
		String totalRowsHsql = "select count(t) from QuestionStategy t where t.user.id = " + id + " and t.answeredFlag = "
				+ type + " and t.deleteFlag = 0";
		String resultSql = "from QuestionStategy t where t.user.id = " + id + " and t.answeredFlag = " + type
				+ " and t.deleteFlag = 0";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		return cutPageBean;
	}

	public CutPageBean getQuestionList(CutPageBean pageBean, Long id) {
		String totalRowsHsql = "select count(t) from QuestionStategy t where t.user.id = " + id + " and t.deleteFlag = 0";
		String resultSql = "from QuestionStategy t where t.user.id = " + id + " and t.deleteFlag = 0";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		return cutPageBean;
	}

	public Long getQuestionCountByType(Long id, int type) {
		String totalRowsHsql = "select count(t) from QuestionStategy t where t.user.id = " + id + "and t.answeredFlag = "
				+ type + " and t.deleteFlag = 0";
		List resultList = this.hibernateTemplate.find(totalRowsHsql);
		Long count = (Long) resultList.get(0);
		return count;
	}

	public List<QuestionStategy> getUnsyncQuestion() {
		String authorityTotalSQL = DataAuthority.getAuthorityString(
				" from QuestionStategy qs where qs.deleteFlag = 0 and qs.synchronizedFlag = 0", this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public void updateSyncFlag(String idStr) {
		StringBuilder updateSql = new StringBuilder("update QuestionStategy qs");
		updateSql.append(" set qs.synchronizedFlag = 1, qs.synchronizedTime = ? where qs.erpId in (");
		updateSql.append(idStr);
		updateSql.append(")");
		this.hibernateTemplate.bulkUpdate(updateSql.toString(), new Timestamp((new Date()).getTime()));
	}
}
