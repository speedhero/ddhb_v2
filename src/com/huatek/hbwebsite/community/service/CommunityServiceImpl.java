package com.huatek.hbwebsite.community.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.framework.util.GsonUtil;
import com.huatek.hbwebsite.common.entity.BaiDuCommunity;import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.CommunityAveragePrice;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.School;
import com.huatek.hbwebsite.house.entity.HouseCommunityAveragePrice;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.service.BaseServiceToImpl;

public class CommunityServiceImpl extends BaseServiceToImpl implements CommunityService {
	private static final String GET_COMMUNITY_NAME_SQL = " from Community comm where comm.deleteFlag = 0 and comm.communityName like ?";

	public CutPageBean getPageBean(CutPageBean pageBean, List<CommonBean> queryParams) {
		String totalRowsHsql = "select count(t) from Community t where t.deleteFlag = 0";
		String resultSql = "from Community t where t.deleteFlag = 0";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, queryParams);
		return cutPageBean;
	}

	public List<HouseCommunityAveragePrice> getPriceTrendForSixMonth(String id) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		String resultSql = "from HouseCommunityAveragePrice t where t.communityNo = " + id + " and calculateDate <= \'"
				+ dateStr + "\' and deleteFlag = 0 order by calculateDate desc";
		List<HouseCommunityAveragePrice> list = (List<HouseCommunityAveragePrice>)getQueryWithMaxCount(resultSql, 12);
		
		//因为有些数据日期重复，这里移除重复日期数据
		for(int ii=0; ii<list.size(); ii++){
			for(int jj=ii+1; jj<list.size(); jj++){
				if(list.get(ii).getCalculateDate().equals(list.get(jj).getCalculateDate())){
					list.remove(jj--);
				}
			}
		}
		return list;
	}

	public List<Community> findCommunityListByCommunityNos(String communityNos) {
		String[] split = communityNos.split(",");
		List list = Arrays.asList(split);
		String sqlConditionStr = "";

		String resultSql;
		String str;
		for (Iterator communityList = list.iterator(); communityList.hasNext(); sqlConditionStr = sqlConditionStr + str
				+ ",") {
			resultSql = (String) communityList.next();
			str = "\'" + resultSql + "\'";
		}

		sqlConditionStr = sqlConditionStr.substring(0, sqlConditionStr.lastIndexOf(","));
		resultSql = "from Community t where t.erpId in ( " + sqlConditionStr + ")";
		List communityList1 = this.hibernateTemplate.find(resultSql);
		return communityList1;
	}

	public List<CommunitySubwayStationMapping> getCommunityListBySubwayId(String id) {
		String sqlResult = "from CommunitySubwayStationMapping t where t.subwayStation.subwayLine.erpId = ? and t.deleteFlag =?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List list = this.hibernateTemplate.find(sqlResult, objs);
		return list;
	}

	public List<CommunitySchoolMapping> getCommunityListBySchoolId(String id, List<Float> distance) {
		List mappingList = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = ((Float) distance.get(1)).floatValue();
			float objs2 = ((Float) distance.get(0)).floatValue();
			String sqlResult1 = "from CommunitySchoolMapping t where t.school.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] objs1 = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			mappingList = this.hibernateTemplate.find(sqlResult1, objs1);
		} else {
			String sqlResult = "from CommunitySchoolMapping t where t.school.erpId = ? and t.deleteFlag =?";
			Object[] objs = new Object[] { id, Integer.valueOf(0) };
			mappingList = this.hibernateTemplate.find(sqlResult, objs);
		}

		return mappingList;
	}

	public List<School> getStudyListBySztypeid(String id) {
		String sqlResult = "from School t where t.studyZoneType.erpId = ? and t.deleteFlag =?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List list = this.hibernateTemplate.find(sqlResult, objs);
		return list;
	}

	public List<CommunitySchoolMapping> getCommunityListByszids(List<String> ids) {
		StringBuilder parame = new StringBuilder();

		for (int i = 0; i < ids.size(); ++i) {
			parame.append("\'" + (String) ids.get(i) + "\'");
			if (i < ids.size() - 1) {
				parame.append(",");
			}
		}

		if (ids.size() > 0) {
			return this.hibernateTemplate.find("from CommunitySchoolMapping t where t.deleteFlag = 0 and t.school.erpId in ("
					+ parame.toString() + ")");
		} else {
			return null;
		}
	}

	public List<CommunitySubwayStationMapping> getCommunityListByStationId(String id, List<Float> distance) {
		List list = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = ((Float) distance.get(1)).floatValue();
			float minLength = ((Float) distance.get(0)).floatValue();
			String sqlResult1 = "from CommunitySubwayStationMapping t where t.subwayStation.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] objs = new Object[] { id, Float.valueOf(minLength), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult1, objs);
		} else {
			String sqlResult = "from CommunitySubwayStationMapping t where t.subwayStation.erpId = \'" + id	+ "\' and t.deleteFlag =0";
			list = this.hibernateTemplate.find(sqlResult);
		}

		return list;
	}

	/**
	 * 构造智能搜索条件
	 * @param criteria	搜索条件对象
	 * @param searchKey	智能搜索条件key
	 * @param searchValue 智能搜索条件Value
	 */
	private void makeSamrtSearchCondition(Criteria criteria, String searchKey, String searchValue){
//		if (!oneMap.get(searchKey).equals("")) {
			criteria.createAlias("com.region", "region");
			criteria.createAlias("com.cbd", "cbd");
			criteria.createCriteria("cbd.parentCBD", "parentCBD", 1);
			criteria.add(Restrictions.or(
					Restrictions.like("com.communityName", "%" + searchValue + "%"),
					Restrictions.or(Restrictions.like("region.countyName", "%" + searchValue + "%"),
							Restrictions.like("parentCBD.name", "%" + searchValue + "%"))));
//		}
	}
	

	/**
	 * 构造按地铁搜索条件
	 * @param criteria
	 * @param key			搜索条件key
	 * @param oneMap	查询参数map
	 * @param twoMap	查询参数map
	 * @param tagsMap	房源标签map
	 */
	private void makeSubwaylineSearchCondition(Criteria criteria, String key, Map<String, Object> oneMap,
			Map<String, List> twoMap) {
		List<CommunitySubwayStationMapping> commSubwayList = this.getCommunityListBySubwayId((String) oneMap.get(key));
		List<String> commIdList = new ArrayList();
		if (commSubwayList != null && commSubwayList.size() > 0) {
			Iterator<CommunitySubwayStationMapping> itCom = commSubwayList.iterator();

			while (itCom.hasNext()) {
				CommunitySubwayStationMapping cssMapping = itCom.next();
				if (cssMapping.getCommunity() != null) {
					commIdList.add(cssMapping.getCommunity().getErpId());
				}
			}
		}

		if (commIdList.size() > 0) {
			if (commIdList.size() == 1) {
				criteria.add(Restrictions.eq("erpId", commIdList.get(0)));
			} else {
				criteria.add(Restrictions.in("erpId", commIdList));
			}
		} else {
			criteria.add(Restrictions.eq("erpId", "-1"));
		}
	}
	

	/**
	 * 构造按学校搜索条件
	 * @param criteria
	 * @param key			搜索条件key
	 * @param oneMap	查询参数map
	 * @param twoMap	查询参数map
	 * @param tagsMap 房源标签map
	 */
	private void makeSchoolSearchCondition(Criteria criteria, String key, Map<String, Object> oneMap, Map<String, List> twoMap) {
		List<Float> disList = null;
		if (twoMap.containsKey("distance")) {
			disList = (List<Float>) twoMap.get("distance");
		}

		List<CommunitySchoolMapping> csMapList = this.getCommunityListBySchoolId((String) oneMap.get(key), disList);
		List<String> commIdList = new ArrayList<String>();
		if (csMapList != null && csMapList.size() > 0) {
			Iterator<CommunitySchoolMapping> itCSMap = csMapList.iterator();

			while (itCSMap.hasNext()) {
				CommunitySchoolMapping csMapping = itCSMap.next();
				if (csMapping.getCommunity() != null) {
					commIdList.add(csMapping.getCommunity().getErpId());
				}
			}
		}

		if (commIdList.size() > 0) {
			if (commIdList.size() == 1) {
				criteria.add(Restrictions.eq("erpId", commIdList.get(0)));
			} else {
				criteria.add(Restrictions.in("erpId", commIdList));
			}
		} else {
			criteria.add(Restrictions.eq("erpId", "-1"));
		}
	}
	

	/**
	 * 构造根据学区搜索条件
	 * @param criteria
	 * @param key
	 * @param oneMap
	 * @param twoMap
	 * @param tagsMap
	 */
	private void makeSztypeSearchCondition(Criteria criteria, String key, Map<String, Object> oneMap, Map<String, List> twoMap) {
		List<School> schoolList = this.getStudyListBySztypeid((String) oneMap.get(key));
		List<String> schoolIdList = new ArrayList();
		if (schoolList != null && schoolList.size() > 0) {
			Iterator<School> itSchool = schoolList.iterator();

			while (itSchool.hasNext()) {
				schoolIdList.add(itSchool.next().getErpId());
			}
		}

		List<CommunitySchoolMapping> csMapList = this.getCommunityListByszids(schoolIdList);
		List<String> comIdList = new ArrayList<String>();
		if (csMapList != null && csMapList.size() > 0) {
			Iterator<CommunitySchoolMapping> itCSMap = csMapList.iterator();

			while (itCSMap.hasNext()) {
				CommunitySchoolMapping csMapping = itCSMap.next();
				if (csMapping.getCommunity() != null) {
					comIdList.add(csMapping.getCommunity().getErpId());
				}
			}
		}

		if (comIdList.size() > 0) {
			if (comIdList.size() == 1) {
				criteria.add(Restrictions.eq("erpId", comIdList.get(0)));
			} else {
				criteria.add(Restrictions.in("erpId", comIdList));
			}
		} else {
			criteria.add(Restrictions.eq("erpId", Long.valueOf(-1L)));
		}
	}
	

	/**
	 * 构造根据地铁站搜索条件
	 * 
	 * @param criteria
	 * @param key
	 * @param oneMap
	 * @param twoMap
	 * @param tagsMap
	 */
	private void makeSubwaystationSearchCondition(Criteria criteria, String key, Map<String, Object> oneMap,
			Map<String, List> twoMap) {
		List<Float> disList = null;
		if (twoMap.containsKey("distance")) {
			disList = (List<Float>) twoMap.get("distance");
		}

		List<CommunitySubwayStationMapping> cssMapList = this
				.getCommunityListByStationId((String) oneMap.get(key), disList);
		List<String> commIdList = new ArrayList<String>();
		if (cssMapList != null && cssMapList.size() > 0) {
			Iterator<CommunitySubwayStationMapping> itCSSMap = cssMapList.iterator();

			while (itCSSMap.hasNext()) {
				CommunitySubwayStationMapping com = itCSSMap.next();
				if (com.getCommunity() != null) {
					commIdList.add(com.getCommunity().getErpId());
				}
			}
		}

		if (commIdList.size() > 0) {
			if (commIdList.size() == 1) {
				criteria.add(Restrictions.eq("erpId", commIdList.get(0)));
			} else {
				criteria.add(Restrictions.in("erpId", commIdList));
			}
		} else {
			criteria.add(Restrictions.eq("erpId", "-1"));
		}
	}
	
	/**
	 * 构造根据上级商圈搜索条件
	 * @param criteria
	 * @param key
	 * @param oneMap
	 * @param twoMap
	 * @param tagsMap
	 */
	private void makeParentCBDSearchCondition(Criteria criteria, String key, Map<String, Object> oneMap,
			Map<String, List> twoMap) {
		criteria.createAlias("com.cbd", "cbd");
		criteria.createAlias("cbd.parentCBD", "parentCBD");
		criteria.add(Restrictions.eq("parentCBD.websiteId", Integer.valueOf(String.valueOf(oneMap.get(key)))));

	}
		
	/**
	 * 根据条件搜索小区
	 */
	public CutPageBean getSearchFiledList(CutPageBean pageBean, Map oneMap, Map<String, List> twoMap, String orderStr, String sortfield) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Community.class, "com");
		
		Iterator itKey = oneMap.keySet().iterator();
		while (itKey.hasNext()) {
			String key = (String) itKey.next();
			if (key.equals("inputSearch")) {
				// 智能搜索
				String searchValue = (String) oneMap.get(key);
				makeSamrtSearchCondition(criteria, key, searchValue);
				
			} else {
				if (key.equals("subwayline.erpId")) {
					//根据地铁线路搜索
					makeSubwaylineSearchCondition( criteria, key, oneMap, twoMap);
					
				} else {
					if (key.equals("school.erpId")) {
						//根据学校搜索
						makeSchoolSearchCondition(criteria, key, oneMap, twoMap);
						
					} else if (key.equals("sztype.erpId")) {
						//根据学区搜索
						makeSztypeSearchCondition(criteria, key, oneMap, twoMap);
						
					} else if (key.equals("subwaystation.erpId")) {
						//根据地铁站搜索
						makeSubwaystationSearchCondition(criteria, key, oneMap, twoMap);
								
					} else if (key.equals("cbd.parentCBD.websiteId")) {
						//根据上级商圈搜索
						makeParentCBDSearchCondition(criteria, key, oneMap, twoMap);
					} else {
						criteria.add(Restrictions.eq(key, oneMap.get(key)));
					}
				}
			}
		}

		Iterator<String> itKey1 = twoMap.keySet().iterator();
		while (itKey1.hasNext()) {
			String twoMapKey = itKey1.next();
			if (!twoMapKey.trim().equals("distance")) {
				if ("currentSHAvePrice".equals(twoMapKey.trim())) {
					float minVal = ((Float) ((List) twoMap.get(twoMapKey)).get(0));
					float maxVal = ((Float) ((List) twoMap.get(twoMapKey)).get(1));
					criteria.add(Restrictions.between(twoMapKey.trim(), Float.valueOf(minVal), Float.valueOf(maxVal)));
				} else {
					criteria.add(Restrictions.between(twoMapKey.trim(), ((List) twoMap.get(twoMapKey)).get(0), ((List) twoMap.get(twoMapKey)).get(1)));
				}
			}
		}

		if (orderStr != null && !"".equals(orderStr.trim())) {
			if ("Asc".equalsIgnoreCase(orderStr)) {
				criteria.addOrder(Order.asc(sortfield));
			} else {
				criteria.addOrder(Order.desc(sortfield));
			}
		}

		CutPageBean resultBean = this.getCutPage(pageBean, criteria);
		return resultBean;
	}

	private CutPageBean getCutPage(CutPageBean pageBean, Criteria criteria){
		return getCutPage( pageBean,  criteria, 0);
	}
	/**
	 * 
	 * @param pageBean
	 * @param criteria
	 * @param houseType 区分是否是小区 或者房源 因为 小区是根据“pictureCount”这个字段排序，但是二手房里没有这个字段，在小区详情页里搜索二手房时，会报错
	 * @return
	 */
	private CutPageBean getCutPage(CutPageBean pageBean, Criteria criteria, Integer houseType) {
		CutPageBean newPage = new CutPageBean();
		newPage.setExport(pageBean.isExport());
		newPage.setPageSize(pageBean.getPageSize());
		criteria.setProjection(Projections.rowCount());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
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
			//按图片数量排序
			//Addes by ShengYoufu at 2015.03.30
			//二手房，租赁没有自带的图片数量
			if(houseType == 0)
				criteria.addOrder(Order.desc("pictureCount"));

			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			newPage.setDataList(criteria.list());

			return newPage;
		}
	}

	public String getCommunityJsonStringByName(String name) {
		List communityList = this.hibernateTemplate.find(
				" from Community comm where comm.deleteFlag = 0 and comm.communityName like ?", "%" + name + "%");
		return GsonUtil.getGsonInstanceWithExpose().toJson(communityList);
	}

	public CutPageBean getSecondByCommuId(CutPageBean pageBean, List<CommonBean> params, String comId) {
		String totalRowsHsql = "select count(t) from HouseSecond t where t.deleteFlag = 0 and t.community.erpId = \'" + comId + "\'";
		String resultSql = "from HouseSecond t where t.deleteFlag = 0 and t.community.erpId = \'" + comId + "\'";
		String sortSql = "order by t.sortIndex asc";
		CutPageBean cutBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, sortSql, pageBean, params);
		return cutBean;
	}

	public CutPageBean getRentByCommuId(CutPageBean pageBean, List<CommonBean> params, String comId) {
		String totalRowsHsql = "select count(t) from HouseRent t where t.deleteFlag = 0 and t.community.erpId = \'" + comId
				+ "\'";
		String resultSql = "from HouseRent t where t.deleteFlag = 0 and t.community.erpId = \'" + comId + "\'";
		String sortSql = "order by t.sortIndex asc";
		CutPageBean cutBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, sortSql,
				pageBean, params);
		return cutBean;
	}

	public CutPageBean getSearchHouseList(CutPageBean pageBean, Map oneMap, Map<String, List> twoMap,
			Map<String, String> tagsMap, String orderStr, String sortfield, String housetype, String comId) {
		Criteria criteria;
		if ("1".equals(housetype.trim())) {
			criteria = this.getSessionFactory().getCurrentSession().createCriteria(HouseSecond.class, "h");
		} else {
			criteria = this.getSessionFactory().getCurrentSession().createCriteria(HouseRent.class, "h");
		}

		criteria.createAlias("h.community", "community");
		Iterator var11 = oneMap.keySet().iterator();

		String cutpageBean;
		while (var11.hasNext()) {
			cutpageBean = (String) var11.next();
			criteria.add(Restrictions.eq(cutpageBean, oneMap.get(cutpageBean)));
		}

		var11 = twoMap.keySet().iterator();

		while (var11.hasNext()) {
			cutpageBean = (String) var11.next();
			criteria.add(Restrictions.between(cutpageBean, ((List) twoMap.get(cutpageBean)).get(0),
					((List) twoMap.get(cutpageBean)).get(1)));
		}

		var11 = tagsMap.keySet().iterator();

		while (var11.hasNext()) {
			cutpageBean = (String) var11.next();
			criteria.add(Restrictions.like(cutpageBean, "%," + (String) tagsMap.get(cutpageBean) + ",%"));
		}

		if (orderStr != null && !"".equals(orderStr.trim())) {
			if ("Asc".equalsIgnoreCase(orderStr)) {
				criteria.addOrder(Order.asc(sortfield));
			} else {
				criteria.addOrder(Order.desc(sortfield));
			}
		}

		criteria.add(Restrictions.eq("community.erpId", comId));
		CutPageBean cutpageBean1 = this.getCutPage(pageBean, criteria, 1);
		return cutpageBean1;
	}

	public CutPageBean getBrokerList(CutPageBean cutPageBean, String communityId) {
		String totalRowsHsql = "select count(t) from CommunityExpert t where t.deleteFlag = 0 and t.community.erpId = \'"
				+ communityId + "\' and exists(from Broker b where b.erpId = t.broker.erpId)";
		String resultSql = "from CommunityExpert t where t.deleteFlag = 0 and t.community.erpId = \'" + communityId
				+ "\' and exists(from Broker b where b.erpId = t.broker.erpId)";
		return QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, cutPageBean);
	}

	public List<HousePic> getCommunityPicByIdsAndPicType(String[] community, int houseType, int layoutFlag, int count) {
		StringBuffer param = new StringBuffer("");

		for (int sqlString = 0; sqlString < community.length; ++sqlString) {
			if (sqlString > 0) {
				param.append(",");
			}

			param.append(community[sqlString]);
		}

		param.toString();
		String hql = "from HousePic t where t.houseId in (" + param + ") and t.houseType = " + houseType
				+ " and t.layoutFlag = " + layoutFlag + " and t.deleteFlag = 0";
		if (count == 0) {
			return this.hibernateTemplate.find(hql);
		} else {
			return this.getQueryWithMaxCount(hql, count);
		}
	}

	/**
	 * 根据房源ID列表查询房源图片数
	 */
	public List<Object> findContPicPerCommunity(String[] houseIds) {
		StringBuilder sb = new StringBuilder();
		for(String s: houseIds)
			sb.append(", '").append(s).append("'");
		sb.deleteCharAt(0);
		String hql = "select t.houseId, count(t.houseId) from HousePic t where t.deleteFlag = 0 and t.houseType = 3 and t.houseId in ( "
				+ sb.toString() + " ) group by t.houseId order by t.houseId";
		return this.hibernateTemplate.find(hql);
	}

	public List<CommunityStationMapping> getCommunityStationsById(String id) {
		String queryString = "from CommunityStationMapping t where t.community.erpId = ?";
		return this.hibernateTemplate.find(queryString, id);
	}

	public CommunityAveragePrice getCommunityAveragePriceLastMonth(String communityId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1);
		int month = now.get(2);
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(CommunityAveragePrice.class);

		try {
			cri.add(Restrictions.eq("calculateDate", format.parse(today)));
			cri.add(Restrictions.eq("community.erpId", communityId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (CommunityAveragePrice) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public CommunityAveragePrice getCommunityAveragePriceLastYear(String communityId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1) - 1;
		int month = now.get(2);
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(CommunityAveragePrice.class);

		try {
			cri.add(Restrictions.eq("calculateDate", format.parse(today)));
			cri.add(Restrictions.eq("community.erpId", communityId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (CommunityAveragePrice) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public CommunityAveragePrice getCommunityAveragePriceThisMonth(String communityId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1);
		int month = now.get(2) + 1;
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(CommunityAveragePrice.class);

		try {
			cri.add(Restrictions.eq("calculateDate", format.parse(today)));
			cri.add(Restrictions.eq("community.erpId", communityId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (CommunityAveragePrice) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public CutPageBean searchCommunity(String key, CutPageBean pageBean, List<CommonBean> commonList) {
		key = "%" + key + "%";
		String totalRowsHsql = "select count(hr) from Community hr where hr.cbd.cbdName like ? or hr.communityName like ?";
		String hql = " from Community hr where hr.cbd.cbdName like ? or hr.communityName like ?";
		Object[] obj = new Object[] { key, key };
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, hql, "", obj,
				pageBean, commonList);
		return cutPageBean;
	}

	public List<Community> getRecommandedCommunity(String erpId) {
		String hql = "from Community c where c.cbd.erpId = \'" + erpId + "\' order by c.shCount desc";
		List recommandedCommunity = this.getQueryWithMaxCount(hql, 4);
		return recommandedCommunity;
	}

	public void calculateCurrentMonthPrice() {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery queryRH = session.createSQLQuery("CALL calculate_community_sh()");
		queryRH.executeUpdate();
		SQLQuery querySH = session.createSQLQuery("CALL calculate_community_rh()");
		querySH.executeUpdate();
	}

	public void calculateLastYearSHAndRH() {
	}

	public Community getCommunityListByErpId(String erpId) {
		String sqlResult = "from Community t where t.erpId = ? and t.deleteFlag =0";
		Object[] objs = new Object[] { erpId };
		List list = this.hibernateTemplate.find(sqlResult, objs);
		return list.size() > 0 ? (Community) list.get(0) : null;
	}

	public List<CommunitySubwayStationMapping> findCSubMapping(String id) {
		String sqlResult = "from CommunitySubwayStationMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List cSubMapping = this.hibernateTemplate.find(sqlResult, objs);
		return cSubMapping;
	}

	public boolean hasThisCommunity(String communityNo) {
		String sql = "select count(t.communityNo) from Community t where t.communityNo=:communityNO and t.deleteFlag = 0";
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(sql);
		query.setString("communityNO", communityNo);
		int count = ((Number) query.iterate().next()).intValue();
		return count > 0;
	}
	
	/**
	 * 载入小区最近的门店列表，根据ERP同步过来的门店ID列表载入
	 * @param community
	 */
	@Override
	public void loadNearestStore(Community community){
		String storeIDStr = community.getNearStoreID();
		if(StringUtils.isBlank(storeIDStr)) return;
		
		String[] storeIDArr = storeIDStr.split(";|,");
		StringBuilder sb = new StringBuilder("");
		for(String id: storeIDArr){
			sb.append("'").append(id).append("'").append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		
		String sql = "from Store t where t.erpId in ("+sb.toString()+") and t.deleteFlag = 0";
		List storeList = this.hibernateTemplate.find(sql);
		if(storeList!=null)
			community.setNearStoreList(storeList);
	}
	/**
	 * 查询推送到百度的小区
	 * 
	 * @return
	 */
	@Override
	public List<Community> getHousesForBaidu(int count) {
		List<Community> communityList = new ArrayList<Community>();
		List<BaiDuCommunity> bdCommunityList = new ArrayList<BaiDuCommunity>();
		
//		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BaiDuCommunity.class,"b");
//		criteria.add(Restrictions.eq("isSync", false));
//		criteria.add(Restrictions.eq("state",1));
//		if(count > 0 ){
//			criteria.setMaxResults(count);
//			criteria.setFirstResult(0);
//		}
//		bdCommunityList = criteria.list();
//		Criteria c = null;
//		List<Community> list = null;
//		for(BaiDuCommunity bd : bdCommunityList){
//			c = getSessionFactory().getCurrentSession().createCriteria(Community.class,"b");
//			c.add(Restrictions.eq("communityNo", bd.getCommunityId()));
//			list = c.list(); 
//			if(list.size() < 1){
//				bd.setState(3);
//				this.hibernateTemplate.save(bd);
//				continue;
//			}
		//处理不匹配的数据
		modifyHouseData();
		final int counts = count;
		@SuppressWarnings("unchecked")
		List<Community> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
				String hql = " from Community hs where hs.communityNo in (select communityId from BaiDuCommunity bd where bd.isSync = false and bd.state = 1  )  ";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(counts);
				return query.list();
			}

		});
			for(Community community : list){
				//先记录.方便于状态存储
				BaiDuCommunity bd = new BaiDuCommunity();
				bd.setCommunityId(community.getCommunityNo());
				bd.setTitle(community.getTitle());
				bd.setState(1);
				bd.setIsSync(false);
				//发布的时间
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				bd.setPublishTime(ts);
				if(community.getLastModified() != null){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bd.setLastmod(Timestamp.valueOf(df.format(community.getLastModified())));
				}else{
					bd.setLastmod(ts);
				}
//				bd.setIsSync(true);
				//周边设施
				List<String> cFacilities = new ArrayList<String>();
				//周边学校
				String sqlResult = "from CommunitySchoolMapping t where t.community.erpId = ? and t.deleteFlag =0";
				List<CommunitySchoolMapping> cSchool = this.hibernateTemplate.find(sqlResult, community.getErpId());
				for(CommunitySchoolMapping school : cSchool){
					if(school.getSchool() == null) continue;
					cFacilities.add(school.getSchool().getStuName());
				}
				//周边地铁
				List<CommunitySubwayStationMapping> cSubway = this.findCSubMapping(community.getErpId());
				for(CommunitySubwayStationMapping subway : cSubway){
					if(subway.getSubwayStation() == null) continue;
					cFacilities.add(subway.getSubwayStation().getStationName());
				}
				//周边车站
				List<CommunityStationMapping> cStation = this.getCommunityStationsById(community.getErpId());
				for(CommunityStationMapping station : cStation){
					if(station.getStation() == null) continue;
					cFacilities.add(station.getStation().getStationName());
				}
				community.setCommunityFacilities(cFacilities);
				communityList.add(community);
				//记录
				community.setBaiDuCommuntiy(bd);
			}			
//			this.hibernateTemplate.save(bd);
//		}
		
		return communityList;
	}

	/**
	 * 保存isSync是否同步状态
	 * @param CommunityList
	 */
	@Override
	public void saveChangeIsSync(List<Community> CommunityList) {
		if(CommunityList != null || CommunityList.size() > 0){
			for(Community c : CommunityList){
				BaiDuCommunity bd = c.getBaiDuCommuntiy();
				this.hibernateTemplate.update(bd);
			}
		}
	}
	/**
	 * 先把不匹配的数据给标记
	 */
	private void modifyHouseData(){
		@SuppressWarnings("unchecked")
		List<BaiDuCommunity> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
//				String hql = " from HouseSecond hs where hs.houseNo in (select houseId from BaiDuHouseSecond bd where bd.isSync = false and bd.state = 1  )  ";
				
				String hql = " FROM  BaiDuCommunity p WHERE p.isSync = 0 and p.state = 1 and  "
						+ "NOT EXISTS (select h.communityNo FROM Community h WHERE h.communityNo = p.communityId	)  ";
				
				Query query = session.createQuery(hql);
				return query.list();
			}
			
		});
		if(list.size() > 0){
		for(BaiDuCommunity bd : list){
//			bd.setIsSync(false);
			bd.setState(3);
			this.hibernateTemplate.update(bd);
		}
		}
	}

	/**
	 * 根据小区统计出租房源数
	 * @return
	 */
	public List getHouseCountByComunity(){
		StringBuilder hql = new StringBuilder("select hc.erpId, hc.communityName, hc.communityAddress, hc.communityNo, ")
		.append(" hc.currentMonthShAvgPrice, hc.currentMonthRhAvgPrice, ")
		.append(" hc.rentCount, hc.shCount, hc.rentCount + hc.shCount as totalCount, ")
		.append(" pic.pictureUri, hc.location, count(*) ")
		.append(" from HouseRent h ")
		.append(" inner join Community hc on h.communityId=hc.erpId ")
		.append(" inner join housePicture pic on hc.erpId=pic.houseId ")
		.append(" where h.deleteflag<>1 ")
		.append(" group by hc.erpId, hc.communityName");
		
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		return query.list();
	}	
}
