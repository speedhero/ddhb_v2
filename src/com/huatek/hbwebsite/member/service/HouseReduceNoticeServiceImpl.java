package com.huatek.hbwebsite.member.service;

import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.common.entity.HouseReduceNotice;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.service.HouseReduceNoticeService;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;

public class HouseReduceNoticeServiceImpl extends BaseServiceToImpl implements HouseReduceNoticeService {
	public int ifHouseNotice(String searchNo, long memberid) {
		String queryString = "from HouseReduceNotice t where t.platMemberInfo.id= ? and t.houseNo = ? and t.deleteFlag = 0";
		Object[] paramArray = new Object[] { Long.valueOf(memberid), searchNo };
		List reduceNoticeList = this.hibernateTemplate.find(queryString, paramArray);
		return reduceNoticeList.size() != 0 && reduceNoticeList != null ? 1 : 0;
	}

	public List<HouseReduceNotice> getHouseNotice(String searchno, long memberid) {
		String queryString = "from HouseReduceNotice t where t.platMemberInfo.id= ? and t.houseNo = ? and t.deleteFlag = 0";
		Object[] paramArray = new Object[] { Long.valueOf(memberid), searchno };
		List houseReduceList = this.hibernateTemplate.find(queryString, paramArray);
		return houseReduceList;
	}

	public CutPageBean getHouseReduceNotice(CutPageBean cutpageBean, Long memberId) {
		String totalRowsHsql = "select count(t) from HouseReduceNotice t where t.platMemberInfo.id = " + memberId
				+ " and t.deleteFlag = 0";
		String resultSql = "from HouseReduceNotice t where t.platMemberInfo.id = " + memberId
				+ " and t.deleteFlag = 0 order by createTime desc";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				cutpageBean, new ArrayList());
		if (cutPageBean.getDataList() != null && cutPageBean.getDataList().size() > 0) {
			for (int i = 0; i < cutPageBean.getDataList().size(); ++i) {
				HouseReduceNotice houseReduceNotice = (HouseReduceNotice) cutPageBean.getDataList().get(i);
				String historySql;
				List tmp;
				if (houseReduceNotice.getHouseType() == 1) {
					HouseSecond houseRent = (HouseSecond) this.getHibernateTemplate().get(HouseSecond.class,
							houseReduceNotice.getHouseId());
					if (houseRent != null && houseRent.getErpId() != null) {
						historySql = "from PriceChangeHistory p where p.houseId = ? and p.houseType = 1";
						tmp = this.getHibernateTemplate().find(historySql, houseRent.getErpId());
						houseRent.setPriceChangeHistory(tmp);
						houseReduceNotice.setHouseSecond(houseRent);
						if (houseReduceNotice.getHouseSecond().getPrice().floatValue() > houseReduceNotice.getCurrentPrice()) {
							houseReduceNotice.setPriceFlag(-1);
						} else if (houseReduceNotice.getHouseSecond().getPrice().floatValue() == houseReduceNotice
								.getCurrentPrice()) {
							houseReduceNotice.setPriceFlag(0);
						} else {
							houseReduceNotice.setPriceFlag(1);
						}
					}
				} else if (houseReduceNotice.getHouseType() == 2) {
					HouseRent var11 = (HouseRent) this.getHibernateTemplate()
							.get(HouseRent.class, houseReduceNotice.getHouseId());
					historySql = "from PriceChangeHistory p where p.houseId = ? and p.houseType = 2";
					if (var11 != null && var11.getErpId() != null) {
						tmp = this.getHibernateTemplate().find(historySql, var11.getErpId());
						var11.setPriceChangeHistory(tmp);
						houseReduceNotice.setHouseRent(var11);
						if (houseReduceNotice.getHouseRent().getRentPrice() > houseReduceNotice.getCurrentPrice()) {
							houseReduceNotice.setPriceFlag(-1);
						} else if (houseReduceNotice.getHouseRent().getRentPrice() == houseReduceNotice.getCurrentPrice()) {
							houseReduceNotice.setPriceFlag(0);
						} else {
							houseReduceNotice.setPriceFlag(-1);
						}
					}
				}
			}
		}

		return cutPageBean;
	}

	public int getNoticeCount(Long memberId) {
		String sqlString = "SELECT COUNT(*) FROM ( SELECT hrn.current_price AS current_price, shh.price AS price FROM house_reduce_notice hrn JOIN house_second_hand_house shh ON shh.erp_id = hrn.erp_id AND hrn.current_price <> shh.price WHERE hrn.house_type = \'1\' and hrn.deleteflag = \'0\' UNION ALL SELECT hrn.current_price AS current_price, hrh.rent_price AS price FROM house_reduce_notice hrn JOIN house_rent_house hrh ON hrh.erp_id = hrn.erp_id AND hrn.current_price <> hrh.rent_price WHERE hrn.house_type = \'2\' and hrn.deleteflag = \'0\' ) X";
		SQLQuery query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sqlString);
		List objects = query.list();
		BigInteger num = (BigInteger) objects.get(0);
		int count = num.intValue();
		return count;
	}

	public boolean deletePriceNotice(String Id, Long memberId) {
		String delHsql = "update HouseReduceNotice t set t.deleteFlag = 1 where t.erpId = " + Id
				+ "and t.platMemberInfo.id = " + memberId;
		int count = this.hibernateTemplate.bulkUpdate(delHsql);
		return count > 0;
	}

	public boolean validEmail(String emailAddress) {
		String sql = "from HouseReduceNotice t where t.deleteFlag = 0 and t.noticeEmail = ? and t.emailFlag = 1";
		List list = this.getHibernateTemplate().find(sql, emailAddress);
		if (list.size() == 0) {
			return false;
		} else {
			HouseReduceNotice tmp = (HouseReduceNotice) list.get(0);
			return tmp.getEmailFlag() != 0;
		}
	}

	public boolean validPhone(String phoneNum) {
		String sql = "from HouseReduceNotice t where t.deleteFlag = 0 and t.noticePhone = ? and t.phoneFlag = 1";
		List list = this.getHibernateTemplate().find(sql, phoneNum);
		return list.size() != 0;
	}

	public boolean acticeEmail(String erpId) {
		String delHsql = "update HouseReduceNotice t set t.emailFlag = 1 where t.erpId = ? ";
		int count = this.hibernateTemplate.bulkUpdate(delHsql, erpId);
		return count > 0;
	}

	public boolean valiDatas(Long id, Integer houseType, String houseId) {
		String sql = "from HouseReduceNotice t where t.platMemberInfo.id = ? and t.houseType = ? and t.houseId = ? and t.deleteFlag = 0";
		Object[] paramArray = new Object[] { id, houseType, houseId };
		List list = this.getHibernateTemplate().find(sql, paramArray);
		return list.size() != 0;
	}
}
