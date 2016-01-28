package com.huatek.hbwebsite.consignment.service;

import com.huatek.authority.factory.DataAuthority;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CBDWebsite;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.consignment.entity.EntrustBhouse;
import com.huatek.hbwebsite.consignment.entity.EntrustRenthouse;
import com.huatek.hbwebsite.consignment.entity.EntrustRentinghouse;
import com.huatek.hbwebsite.consignment.entity.EntrustSalehouse;
import com.huatek.hbwebsite.consignment.service.ConsignmentService;
import com.huatek.hbwebsite.house.entity.HouseType;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

public class ConsignmentServiceImpl extends BaseServiceToImpl implements ConsignmentService {
	private static final String GET_UNSYNCRENTHOUSE = " from EntrustRenthouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0";
	private static final String GET_UNSYNCRENTINGHOUSE = " from EntrustRentinghouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0";
	private static final String GET_UNSYNCSALEHOUSE = " from EntrustSalehouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0";
	private static final String GET_UNSYNCBUYHOUSE = " from EntrustBhouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0";
	private static final int PAGE_SIZE = 5;

	public List<Area> findAllCounty() {
		String sqlRequest = "from Area t";
		List areas = this.hibernateTemplate.find(sqlRequest);
		return areas;
	}

	public List<CBDWebsite> findCbd(String cId) {
		String splRequest = "from CBDWebsite t where t.area.erpId = ? and t.deleteFlag=?";
		Object[] objs = new Object[] { cId, Integer.valueOf(0) };
		List cbds = this.hibernateTemplate.find(splRequest, objs);
		return cbds;
	}

	public List<Community> getAllCommunity() {
		String queryString = "from Community c where c.deleteFlag = 0";
		return this.hibernateTemplate.find(queryString);
	}

	public List<HouseType> getAllHouseType() {
		String queryString = "from HouseType t where t.deleteFlag = 0";
		return this.hibernateTemplate.find(queryString);
	}

	public List<Community> getCommunityIdByCommName(String communityName) {
		String sqlString = "from Community t where t.communityName = ? and t.deleteFlag = ?";
		Object[] objs = new Object[] { communityName, Integer.valueOf(0) };
		List commList = this.hibernateTemplate.find(sqlString, objs);
		return commList;
	}

	public List<Furniture> getFurList() {
		String resultSql = "from Furniture f";
		List furList = this.hibernateTemplate.find(resultSql);
		return furList;
	}

	public List<EntrustRenthouse> getUnsyncRentHouse() {
		String authorityTotalSQL = DataAuthority.getAuthorityString(
				" from EntrustRenthouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0", this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public List<EntrustRentinghouse> getUnsyncRentingHouse() {
		String authorityTotalSQL = DataAuthority.getAuthorityString(
				" from EntrustRentinghouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0", this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public List<EntrustSalehouse> getUnsyncSaleHouse() {
		String authorityTotalSQL = DataAuthority.getAuthorityString(
				" from EntrustSalehouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0", this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public List<EntrustBhouse> getUnsyncBuyHouse() {
		String authorityTotalSQL = DataAuthority.getAuthorityString(
				" from EntrustBhouse er where er.deleteFlag = 0 and er.synchronizedFlag = 0", this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public void updateSyncFlag(String type, String idStr) {
		StringBuilder updateSql = new StringBuilder("update ");
		if (type.equals("rent")) {
			updateSql.append(" EntrustRenthouse ");
		} else if (type.equals("renting")) {
			updateSql.append(" EntrustRentinghouse ");
		} else if (type.equals("sale")) {
			updateSql.append(" EntrustSalehouse ");
		} else {
			updateSql.append(" EntrustBhouse ");
		}

		updateSql.append(" er set er.synchronizedFlag = 1, er.synchronizedTime = ? where er.id in (");
		updateSql.append(idStr);
		updateSql.append(")");
		this.hibernateTemplate.bulkUpdate(updateSql.toString(), new Timestamp((new Date()).getTime()));
	}
}
