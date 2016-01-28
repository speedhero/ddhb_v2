package com.huatek.hbwebsite.member.service;

import com.huatek.authority.factory.DataAuthority;
import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.MemberBespeak;
import com.huatek.hbwebsite.member.service.MemberBespeakService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

public class MemberBespeakServiceImpl extends BaseServiceImpl implements MemberBespeakService {
	private static final String GET_MEMBERBESPEAK_ITEM_SQL = " from MemberBespeak bm where bm.platMemberInfo.id = ?";
	private static final String GET_UNSYNCRENTBESPEAK = " from MemberBespeak mb where mb.deleteFlag = 0 and mb.synchronizedFlag = 0";
	private static final int PAGE_SIZE = 5;

	public List<MemberBespeak> getMemberBespeakItem(long id) {
		return super.getHibernateTemplate().find(" from MemberBespeak bm where bm.platMemberInfo.id = ?", Long.valueOf(id));
	}

	public CutPageBean getAppointmentHouse(CutPageBean pageBean, Long userId) {
		pageBean.setPageSize(5);
		String totalRowsHsql = "select count(t) from MemberBespeak t where t.deleteFlag = 0 and t.platMemberInfo.id = "
				+ userId + " and t.bespeakTimeEnd > CURRENT_DATE()";
		String resultSql = "from MemberBespeak t where t.deleteFlag = 0 and t.platMemberInfo.id = " + userId
				+ " and t.bespeakTimeEnd > CURRENT_DATE() order by houseType desc";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		if (cutPageBean.getDataList() != null && cutPageBean.getDataList().size() > 0) {
			for (int i = 0; i < cutPageBean.getDataList().size(); ++i) {
				MemberBespeak bespeak = (MemberBespeak) cutPageBean.getDataList().get(i);
				if (bespeak.getHouseType().equals(Long.valueOf(1L))) {
					HouseSecond houseRent = (HouseSecond) this.getHibernateTemplate().load(HouseSecond.class,
							bespeak.getHouseId());
					bespeak.setSecondHouse(houseRent);
				} else if (bespeak.getHouseType().equals(Long.valueOf(2L))) {
					HouseRent var9 = (HouseRent) this.getHibernateTemplate().load(HouseRent.class, bespeak.getHouseId());
					bespeak.setRentHouse(var9);
				}
			}
		}

		return cutPageBean;
	}

	public Long getSecondHouseCount(Long userId) {
		String totalRowsHsql = "select count(t) from MemberBespeak t where t.platMemberInfo.id = " + userId
				+ " and t.bespeakTimeEnd > CURRENT_DATE() and t.houseType = 1 and t.deleteFlag = 0";
		List resultList = this.hibernateTemplate.find(totalRowsHsql);
		Long count = (Long) resultList.get(0);
		return count;
	}

	public Long getRentHouseCount(Long userId) {
		String totalRowsHsql = "select count(t) from MemberBespeak t where t.platMemberInfo.id = " + userId
				+ " and t.bespeakTimeEnd > CURRENT_DATE() and t.houseType = 2 and t.deleteFlag = 0";
		List resultList = this.hibernateTemplate.find(totalRowsHsql);
		Long count = (Long) resultList.get(0);
		return count;
	}

	public boolean deleteAppointment(Long mid, Long id) {
		String delHsql = "update MemberBespeak t set t.deleteFlag = 1 where t.id = " + mid + "and t.platMemberInfo.id = "
				+ id;
		int count = this.hibernateTemplate.bulkUpdate(delHsql);
		return count > 0;
	}

	public boolean deleteMemberCollect(Long mid, Long id) {
		String delHsql = "update PlatCollection t set t.deleteFlag = 1 where t.id = " + mid + "and t.platMemberInfo.id = "
				+ id;
		int count = this.hibernateTemplate.bulkUpdate(delHsql);
		return count > 0;
	}

	public List<MemberBespeak> getUnsyncBespeak() {
		String authorityTotalSQL = DataAuthority.getAuthorityString(
				" from MemberBespeak mb where mb.deleteFlag = 0 and mb.synchronizedFlag = 0", this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public void updateSyncFlag(String idStr) {
		StringBuilder updateSql = new StringBuilder("update MemberBespeak mb");
		updateSql.append(" set mb.synchronizedFlag = 1, mb.synchronizedTime = ? where mb.id in (");
		updateSql.append(idStr);
		updateSql.append(")");
		this.hibernateTemplate.bulkUpdate(updateSql.toString(), new Timestamp((new Date()).getTime()));
	}
}
