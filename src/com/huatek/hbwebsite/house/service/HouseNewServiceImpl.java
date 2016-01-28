package com.huatek.hbwebsite.house.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.house.entity.HouseNew;
import com.huatek.hbwebsite.house.entity.HouseNewGroupbuy;
import com.huatek.hbwebsite.house.service.HouseNewService;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class HouseNewServiceImpl extends BaseServiceImpl implements HouseNewService {
//	private static final String LOAD_NEW_HOUSE_WITH_COUNT_SQL = " from HouseNew t where t.deleteFlag = 0 order by t.homepageRecommandFlag desc,t.homepageRecommandTime desc";

	/**
	 * 
	 */
	public CutPageBean getPageBean(CutPageBean cutPageBean, List<CommonBean> commonList) {
		String totalRowsHsql = "select count(t) from HouseNew t where t.deleteFlag = 0";
		String resultSql = "from HouseNew t where t.deleteFlag = 0";
		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "", cutPageBean, commonList);
		return pageBean;
	}

	/**
	 * 查询新楼盘列表
	 */
	public List<HouseNew> loadHouseNewListWithCount(int count) {
		List houseList = this.getQueryWithMaxCount(
				" from HouseNew t where t.deleteFlag = 0 order by t.homepageRecommandFlag desc,t.homepageRecommandTime desc",
				count);
		return houseList;
	}

	/**
	 * 查询新楼盘记录
	 */
	public CutPageBean getSearchFiledList(CutPageBean pageBean, Map<String, String> oneMap, Map<String, List> twoMap, String orderStr, String sortfield) 
			throws ParseException {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(HouseNew.class, "newHouse");
		Iterator<String> itKey = oneMap.keySet().iterator();

		while (itKey.hasNext()) {
			String key = (String) itKey.next();
			if (!key.equals("decoration.erpId")) {
				String idStr = (String) oneMap.get(key);
				if (key.equals("area.cityForNewHouse.id")) {
					criteria.createAlias("newHouse.area", "area");
					criteria.createAlias("area.cityForNewHouse", "city");
					criteria.add(Restrictions.eq("city.id", Long.valueOf(idStr)));
				} else {
					criteria.add(Restrictions.eq("area.id", Long.valueOf(idStr)));
				}
			} else if (key.equals("decoration.erpId")) {
				criteria.add(Restrictions.eq(key, oneMap.get(key)));
			}
		}

		itKey = twoMap.keySet().iterator();

		while (itKey.hasNext()) {
			String key = (String) itKey.next();
			criteria.add(Restrictions.between(key.trim(), ((List) twoMap.get(key)).get(0), ((List) twoMap.get(key)).get(1)));
		}

		if (orderStr != null && !"".equals(orderStr.trim())) {
			if ("Asc".equalsIgnoreCase(orderStr)) {
				criteria.addOrder(Order.asc(sortfield));
			} else {
				String[] sfields = sortfield.split(",");
				for(String s: sfields)
					criteria.addOrder(Order.desc(s));
			}
		}

		criteria.add(Restrictions.eq("deleteFlag", Integer.valueOf(0)));
		CutPageBean resultBean = this.getCutPage(pageBean, criteria);
		return resultBean;
	}

	/**
	 * 执行查询
	 * @param pageBean
	 * @param criteria
	 * @return
	 */
	private CutPageBean getCutPage(CutPageBean pageBean, Criteria criteria) {
		CutPageBean newPage = new CutPageBean();
		newPage.setExport(pageBean.isExport());
		newPage.setPageSize(pageBean.getPageSize());
		criteria.setProjection(Projections.rowCount());
		List queryList = criteria.list();
		int totalRow = Integer.valueOf(queryList.size() == 0 ? "0" : queryList.get(0).toString()).intValue();
		newPage.setTotalRows(totalRow);
		if (totalRow == 0) {
			return newPage;
		} else {
			pageBean.setTotalRows(totalRow);
			newPage.setCurrentPage(pageBean.getCurrentPage());
			if (!newPage.isExport()) {
				criteria.setFirstResult(newPage.getPageSize() * (pageBean.getCurrentPage() - 1));
				criteria.setMaxResults(newPage.getPageSize());
			}

			criteria.setProjection((Projection) null);
			criteria.setResultTransformer(Criteria.ROOT_ENTITY);
			newPage.setDataList(criteria.list());
			return newPage;
		}
	}

	/**
	 * 查询指定日期后(含指定日期)的团购人数
	 */
	public List<HouseNewGroupbuy> getNewHouseGroupBuy(Date expirationDate) {
		String sqlString = "from HouseNewGroupbuy t where t.houseNew.endDate >= ? and t.houseNew.deleteFlag = 0 ";
		Object[] params = new Object[] { expirationDate };
		return this.hibernateTemplate.find(sqlString, params);
	}

	/**
	 * 查询团购人数
	 */
	public Long getCountGroupBuy() {
		String sqlString = "select count(*) from HouseNewGroupbuy t";
		List<Long> list = this.hibernateTemplate.find(sqlString);
		if(list.size()>0)
			return list.get(0);
		else
			return -1L;
	}

	/**
	 * 查询指定日期之后(含指定日期)的新楼盘记录数
	 */
	public Long getCountHouseNew(Map<String, String> oneMap, Date expirationDate) {
		return getNewHouseCount(oneMap, expirationDate, false);
	}

	/**
	 * 查询指定日期之前的新楼盘记录数
	 */
	public Long getCountHouseNewEnd(Map<String, String> oneMap, Date expirationDate) {
		return getNewHouseCount(oneMap, expirationDate, true);
	}
	
	private Long getNewHouseCount(Map<String, String> oneMap, Date expirationDate, Boolean isBeforeToday){
		String sqlString = "select count(*) from HouseNew t where t.endDate >= ?and t.deleteFlag = 0";
		if(isBeforeToday)
			sqlString = "select count(*) from HouseNew t where t.endDate < ? and t.deleteFlag = 0";
		
		if (oneMap.size() > 0) {
			Iterator<String> itKey = oneMap.keySet().iterator();
			while(itKey.hasNext()){
				String key = itKey.next();
				String value = oneMap.get(key);
				sqlString = sqlString + " and " + key + " = " + value;
			}
		}
		Object[] params = new Object[] { expirationDate };
		List<Long> list = (List<Long>)hibernateTemplate.find(sqlString, params);
		if(list.size()>0) 
			return list.get(0);
		else
			return -1L;
	}

	/**
	 * 根据楼盘ID统计团购参与人数
	 */
	public List<Object> getHouseEntrants(long[] houseIds) {
		StringBuilder sbSql = new StringBuilder("");

		for(Long id: houseIds)
			sbSql.append(id).append(",");

		sbSql.deleteCharAt(sbSql.length() - 1);

		String hql = "select t.houseNew.id, count(t.houseNew.id) from HouseNewGroupbuy t where t.houseNew.id in ( "  + sbSql.toString() + ") group by t.houseNew.id";
		List<Object> list = this.hibernateTemplate.find(hql);
		return list;
	}

	/**
	 * 根据楼盘ID统计团购参与人数
	 */
	public Long getHouseNewEntrantsById(long houseId) {
		String queryString = "select count(t.houseNew.id) from HouseNewGroupbuy t where t.houseNew.id = " + houseId;
		List list = this.hibernateTemplate.find(queryString);
		if(list.size()>0)
			return (Long)list.get(0);
		else
			return -1L;
	}

	public int findHouseNewByItems(String telephone, long houseId) {
		String queryString = "from HouseNewGroupbuy t where t.clientTelephone = ? and t.houseNew.id = ?";
		Object[] obj = new Object[] { telephone, Long.valueOf(houseId) };
		List housegroup = this.hibernateTemplate.find(queryString, obj);
		return housegroup.size() != 0 && housegroup != null ? 1 : 0;
	}
}
