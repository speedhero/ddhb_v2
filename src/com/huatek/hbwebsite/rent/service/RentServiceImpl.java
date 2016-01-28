package com.huatek.hbwebsite.rent.service;

import cn.hshb.web.partner.baidu.service.DocumentXML;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.CommunityHospitalMapping;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.School;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.house.entity.BaiDuHouseRent;
import com.huatek.hbwebsite.house.entity.ERPHousePicture;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseRentPriceTrend;
import com.huatek.hbwebsite.house.entity.HouseSecondPriceTrend;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import com.huatek.hbwebsite.util.HouseSortRuleCacheUtil;
import com.huatek.hbwebsite.util.Parameter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;





import cn.hshb.web.common.helper.HouseHelper;

import com.huatek.ddhb.manage.footerManage.entity.FooterLink;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.AreaSecond;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.CommunityHospitalMapping;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.School;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseRentPriceTrend;
import com.huatek.hbwebsite.house.entity.HouseSecondPriceTrend;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import com.huatek.hbwebsite.util.HouseSortRuleCacheUtil;


public class RentServiceImpl extends BaseServiceToImpl implements RentService {

	private static final Log log = LogFactory.getLog(RentServiceImpl.class);
	
	private static final int EASTCITYID = 40;			//SEO 租赁底部菜单 区域最小Id
	private static final int BINJIANGCITYID = 45;		//SEO 租赁底部菜单 区域最大Id

//	private static final String  LAST_COMMIT_XML_SAVE_PATH = String.valueOf( Parameter.getInstance().getProperty("last.commit.xml.save.path"));
	private static String  LAST_COMMIT_XML_SAVE_PATH = "./";
	//	private final Long timeStamps =  0L;
	

	public CutPageBean getRentInfoPageBean(CutPageBean pageBean, List<CommonBean> paramterList, String queryCondition,
			String sortSql) {
		String totalRowsHsql = "select count(t) from HouseRent t where " + queryCondition;
		String resultSql = "from HouseRent t where " + queryCondition;
		if (sortSql == null || "".equals(sortSql.trim())) {
			sortSql = "order by t.sortIndex asc";
		}

		return QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, sortSql, pageBean,
				paramterList);
	}

	public List<Tag> getTagList() {
		String resultSql = "from Tag t";
		List tagList = this.hibernateTemplate.find(resultSql);
		return tagList;
	}

	public Tag getTagListByName(String tagName) {
		String resultSql = "from Tag t where t.tagName = ?";
		List tagList = this.hibernateTemplate.find(resultSql, tagName);
		return tagList.size() > 0 ? (Tag) tagList.get(0) : new Tag();
	}

	public List<HouseAppraise> findAppraiseListByHouseNo(String houseNo) {
		String resultSql = "from HouseAppraise t where t.houseNo = ? order by t.sortIndex asc";
		List houseAppraiseList = this.hibernateTemplate.find(resultSql, houseNo);
		return houseAppraiseList;
	}

	public HouseRent findHouseSecondByHouseId(String houseId) {
//		String resultSql = "from HouseRent t where t.erpId = " + houseId;
		String resultSql = "from HouseRent t where t.erpId = ? ";
		List houseRentList = this.hibernateTemplate.find(resultSql, houseId);
		return houseRentList.size() > 0 ? (HouseRent) houseRentList.get(0) : null;
	}

	public List<HouseRent> findRentHouseListByHouseNos(String houseNos) {
		String[] split = houseNos.split(",");
		List<String> list = Arrays.asList(split);
		StringBuilder sbSql = new StringBuilder();

		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String no = it.next();
			if(StringUtils.isEmpty(no)) continue;
			sbSql.append(",").append("\'").append(no).append("\'");
		}
		if(sbSql.length()>0) sbSql.deleteCharAt(0);
		String hql = "from HouseRent t where t.hourseNo in ( " + sbSql.toString() + ")";
		List<HouseRent> houseList = (List<HouseRent>)hibernateTemplate.find(hql);
		return houseList;
	}

	public float[] findAverageValueForPriceAndArea(List<HouseRent> houseRentList) {
		float a = 0.0F;
		float b = 0.0F;

		for (int i = 0; i < houseRentList.size(); ++i) {
			a += ((HouseRent) houseRentList.get(i)).getRentPrice();
			b += ((HouseRent) houseRentList.get(i)).getArea();
		}
		return new float[] { a / (float) houseRentList.size(), b / (float) houseRentList.size() };
	}

	public List<HouseRent> getHouseRentBySQL(String sql, HouseRent houseRent) {
		List houseRentList = this.getQueryWithMaxCount(sql, 5);
		if (houseRentList.contains(houseRent)) {
			houseRentList.remove(houseRent);
		} else if (houseRentList.size() > 4) {
			houseRentList = houseRentList.subList(0, 4);
		}

		return houseRentList;
	}

	public List<Furniture> getFurList() {
		String resultSql = "from Furniture f";
		List furList = this.hibernateTemplate.find(resultSql);
		return furList;
	}

	public Furniture getFurListByName(String furName) {
		String resultSql = "from Furniture f where f.furName = ?";
		List furList = this.hibernateTemplate.find(resultSql, furName);
		return furList.size() > 0 ? (Furniture) furList.get(0) : new Furniture();
	}

	public List<CommunityHospitalMapping> findCHMapping(String id) {
		String sqlResult = "from CommunityHospitalMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List cHMapping = this.hibernateTemplate.find(sqlResult, objs);
		return cHMapping;
	}

	public List<CommunitySchoolMapping> findCSMapping(String id) {
		String sqlResult = "from CommunitySchoolMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List cSMapping = this.hibernateTemplate.find(sqlResult, objs);
		return cSMapping;
	}

	public List<CommunityStationMapping> findCStaMapping(String id) {
		String sqlResult = "from CommunityStationMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List cStaMapping = this.hibernateTemplate.find(sqlResult, objs);
		return cStaMapping;
	}

	public List<CommunitySubwayStationMapping> findCSubMapping(String id) {
		String sqlResult = "from CommunitySubwayStationMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List cSubMapping = this.hibernateTemplate.find(sqlResult, objs);
		return cSubMapping;
	}

	public List<HouseRent> loadRecommandedHouse(int count) {
		List houseRentList = this
				.getQueryWithMaxCount(
						" from HouseRent hr inner join fetch hr.community order by hr.homepageRecommandFlag desc,hr.homepageRecommandTime desc",
						count);
		return houseRentList;
	}

	public List<HousePic> getAllHousePicList(String id) {
		String sqlResult = "from HousePic t where t.houseId = ? and t.houseType = ? and t.deleteFlag =?";
		Object[] objs = new Object[] { id, Integer.valueOf(2), Integer.valueOf(0) };
		List picList = this.hibernateTemplate.find(sqlResult, objs);
		return picList;
	}

	public List<HousePic> getHeadHouseList(String id) {
		String sqlResult = "from HousePic t where t.houseId = ? and t.houseType = ? and t.picType <= ?  and t.deleteFlag =?";
		Object[] objs = new Object[] { id, Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(0) };
		List picList = this.hibernateTemplate.find(sqlResult, objs);
		return picList;
	}

	public List<CommunitySubwayMapping> getCommunityListBySubwayId(String id) {
		String sqlResult = "from CommunitySubwayMapping t where t.subway.erpId = ? and t.deleteFlag =?";
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
		List<CommunitySchoolMapping> list = new ArrayList<CommunitySchoolMapping>();
		String[] idlongs = (String[]) ids.toArray(new String[ids.size()]);
		StringBuffer param = new StringBuffer("");

		for (int i = 0; i < idlongs.length; ++i) {
			if (i > 0) {
				param.append(",");
			}
			param.append(idlongs[i]);
		}

		if (param != null) {
			list = this.hibernateTemplate.find("from CommunitySchoolMapping t where t.deleteFlag = 0 and t.id in (" + param.toString() + ")");
		}

		return list;
	}

	public List<CommunitySubwayStationMapping> getCommunityListByStationId(String id, List<Float> distance) {
		List list = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = ((Float) distance.get(1)).floatValue();
			float objs2 = ((Float) distance.get(0)).floatValue();
			String sqlResult1 = "from CommunitySubwayStationMapping t where t.subwayStation.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] objs1 = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult1, objs1);
		} else {
			String sqlResult = "from CommunitySubwayStationMapping t where t.subwayStation.erpId = ? and t.deleteFlag =?";
			Object[] objs = new Object[] { id, Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult, objs);
		}

		return list;
	}

	public List<CommunityStationMapping> getCommunityListByBusStaId(String id, List<Float> distance) {
		List list = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = ((Float) distance.get(1)).floatValue();
			float objs2 = ((Float) distance.get(0)).floatValue();
			String sqlResult1 = "from CommunityStationMapping t where t.station.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] objs1 = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult1, objs1);
		} else {
			String sqlResult = "from CommunityStationMapping t where t.station.erpId = ? and t.deleteFlag =?";
			Object[] objs = new Object[] { id, Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult, objs);
		}

		return list;
	}

	public List<CommunityHospitalMapping> getCommunityListByHospitalId(String hospitalName, List<Float> distance) {
		List list = null;
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(CommunityHospitalMapping.class,"t");
		criteria.add(Restrictions.eq("t.deleteFlag", 0));
		criteria.createAlias("t.hospital", "hospital");
		criteria.add(Restrictions.like("hospital.hospitalName", "%"+hospitalName+"%"));
		
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = ((Float) distance.get(1)).floatValue();
			float objs2 = ((Float) distance.get(0)).floatValue();
			criteria.add(Restrictions.between("t.distance", Float.valueOf(objs2), Float.valueOf(sqlResult2)));
//			String sqlResult1 = "from CommunityHospitalMapping t where t.hospital.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
//			String sqlResult1 = "from CommunityHospitalMapping t where t.hospital.hospitalName like '% ? %' and t.distance between ? and ? and t.deleteFlag =?";
//			Object[] objs1 = new Object[] { hospitalName, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
//			list = this.hibernateTemplate.find(sqlResult1, objs1);
		}
//		else {
//			String sqlResult = "from CommunityHospitalMapping t where t.hospital.erpId = ? and t.deleteFlag =?";
//			String sqlResult = "from CommunityHospitalMapping t where t.hospital.hospitalName like '% ? %' and t.deleteFlag =?";
//			Object[] objs = new Object[] { hospitalName, Integer.valueOf(0) };
//			list = this.hibernateTemplate.find(sqlResult, objs);
//		}
		list = criteria.list();
		return list;
	}

	/**
	 * 查询当前页的房源数据
	 */
	public CutPageBean getSearchFiledList(CutPageBean pageBean, Map oneMap, Map<String, List> twoMap,
			Map<String, String> tagsMap, String orderStr, String sortfield) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(HouseRent.class, "h");
		criteria.createAlias("h.community", "community");
		criteria.createAlias("community.cbd", "cbd");
		criteria.createAlias("cbd.parentCBD", "parentCBD");
		String key;
		Iterator itKey = oneMap.keySet().iterator();
		while (itKey.hasNext()) {
			key = (String) itKey.next();
			if (key.equals("inputSearch")) {
				criteria.createAlias("community.region", "region");
				//criteria.createAlias("community.cbd", "cbd");
				//criteria.createCriteria("cbd.parentCBD", "parentCBD", 1);
				criteria.add(Restrictions.or(
						Restrictions.like("community.communityName", "%" + oneMap.get(key) + "%"),
						Restrictions.or(Restrictions.like("region.countyName", "%" + oneMap.get(key) + "%"),
								Restrictions.like("parentCBD.name", "%" + oneMap.get(key) + "%"))));
			} else {
				List disList = null;
				if (twoMap.containsKey("distance")) {
					disList = (List) twoMap.get("distance");
				}
				
				if (key.equals("subwayline.erpId")) {
//					List disList = null;
//					if (twoMap.containsKey("distance")) {
//						disList = (List) twoMap.get("distance");
//					}

					List<CommunitySubwayStationMapping> cssmList = this.getCommunityListBySubwayId((String) oneMap.get(key), disList);
					List<String> cErpIdList = new ArrayList<String>();
					if (cssmList != null && cssmList.size() > 0) {
						Iterator<CommunitySubwayStationMapping> it = cssmList.iterator();

						while (it.hasNext()) {
							CommunitySubwayStationMapping cssMapping = it.next();
							if (cssMapping.getCommunity() != null) {
								cErpIdList.add(cssMapping.getCommunity().getErpId());
							}
						}
					}

					if (cErpIdList.size() > 0) {
						if (cErpIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cErpIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cErpIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "0"));
					}
				} else if (key.equals("school.erpId")) {
//					List disList = null;
//					if (twoMap.containsKey("distance")) {
//						disList = (List) twoMap.get("distance");
//					}

					List<CommunitySchoolMapping> csmList = this.getCommunityListBySchoolId((String) oneMap.get(key), disList);
					List<String> cErpIdList = new ArrayList<String>();
					if (csmList != null && csmList.size() > 0) {
						Iterator<CommunitySchoolMapping> it = csmList.iterator();
						while (it.hasNext()) {
							CommunitySchoolMapping csMapping = it.next();
							if (csMapping.getCommunity() != null) {
								cErpIdList.add(csMapping.getCommunity().getErpId());
							}
						}
					}

					if (cErpIdList.size() > 0) {
						if (cErpIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cErpIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cErpIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "-1"));
					}
				} else if (key.equals("sztype.erpId")) {
					List<School> sList = this.getStudyListBySztypeid((String) oneMap.get(key));
					List<String> schoolIdList = new ArrayList<String>();
					if (sList != null && sList.size() > 0) {
						Iterator<School> it = sList.iterator();
						while (it.hasNext()) {
							schoolIdList.add(it.next().getErpId());
						}
					}

					List<CommunitySchoolMapping> csmList = this.getCommunityListByszids(schoolIdList);
					List<String> cErpIdList = new ArrayList<String>();
					if (csmList != null && csmList.size() > 0) {
						Iterator<CommunitySchoolMapping> it = csmList.iterator();
						while (it.hasNext()) {
							CommunitySchoolMapping csMapping = it.next();
							if (csMapping.getCommunity() != null) {
								cErpIdList.add(csMapping.getCommunity().getErpId());
							}
						}
					}

					if (cErpIdList.size() > 0) {
						if (cErpIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cErpIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cErpIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "-1"));
					}
				} else if (key.equals("subwaystation.erpId")) {
//					List disList = null;
//					if (twoMap.containsKey("distance")) {
//						disList = (List) twoMap.get("distance");
//					}

					List<CommunitySubwayStationMapping> cssmList = this.getCommunityListByStationId((String) oneMap.get(key), disList);
					List<String> cErpIdList = new ArrayList<String>();
					if (cssmList != null && cssmList.size() > 0) {
						Iterator<CommunitySubwayStationMapping> it = cssmList.iterator();
						while (it.hasNext()) {
							CommunitySubwayStationMapping cssMapping = it.next();
							if (cssMapping.getCommunity() != null) {
								cErpIdList.add(cssMapping.getCommunity().getErpId());
							}
						}
					}

					if (cErpIdList.size() > 0) {
						if (cErpIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cErpIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cErpIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "-1"));
					}
				} else if (key.equals("station.erpId")) {
//					List disList = null;
//					if (twoMap.containsKey("distance")) {
//						disList = (List) twoMap.get("distance");
//					}

					List<CommunityStationMapping> csmList = this.getCommunityListByBusStaId((String) oneMap.get(key), disList);
					List<String> cErpIdList = new ArrayList<String>();
					if (csmList != null && csmList.size() > 0) {
						Iterator<CommunityStationMapping> it = csmList.iterator();
						while (it.hasNext()) {
							CommunityStationMapping csMapping = it.next();
							if (csMapping.getCommunity() != null) {
								cErpIdList.add(csMapping.getCommunity().getErpId());
							}
						}
					}

					if (cErpIdList.size() > 0) {
						if (cErpIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cErpIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cErpIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "-1"));
					}
				} else if (key.equals("hospital.hospitalName")) {
//					List disList = null;
//					if (twoMap.containsKey("distance")) {
//						disList = (List) twoMap.get("distance");
//					}

					List<CommunityHospitalMapping> chmList = this.getCommunityListByHospitalId((String) oneMap.get(key), disList);
					List<String> cErpIdList = new ArrayList<String>();
					if (chmList != null && chmList.size() > 0) {
						Iterator<CommunityHospitalMapping> it = chmList.iterator();
						while (it.hasNext()) {
							CommunityHospitalMapping com = it.next();
							if (com.getCommunity() != null) {
								cErpIdList.add(com.getCommunity().getErpId());
							}
						}
					}

					if (cErpIdList.size() > 0) {
						if (cErpIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cErpIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cErpIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "-1"));
					}
				} else if (key.equals("community.cbd.parentCBD.websiteId")) {
					Integer integer = Integer.valueOf(String.valueOf(oneMap.get(key)));
					//SEO租赁分类  HeJB
					//因为SEO新建的区域表没有与对应商圈关联，所以做判断语句
					String value = (String) oneMap.get("community.region.erpId");
					if(value != null){
						//判断value的值是否是SEO表里Id的范围内40～45
						if(Integer.parseInt(value) >= EASTCITYID && Integer.parseInt(value) <= BINJIANGCITYID){
						Integer[] ids = this.getCommonCountySdcond(value);
						for(Integer id : ids){
							if(integer == id)
								criteria.add(Restrictions.eq("parentCBD.websiteId", id));
						}
					}else
						criteria.add(Restrictions.eq("parentCBD.websiteId", integer));
					}else
						criteria.add(Restrictions.eq("parentCBD.websiteId", integer));
				} else 	if (key.equals("community.region.erpId")) {
						//添加这个if判断 是为了SEO租赁底部菜单分类信息显示
						//因为SEO租赁底部菜单的区域是新建的表，在原有的数据结构是没有关联关系的 HeJB
						//新建的表为AreaSecond 六个区域 成东西南北中 滨江 Id取值为40 ～45
						Integer id = Integer.valueOf((String) oneMap.get(key));
						if(id >= EASTCITYID && id <= BINJIANGCITYID ){
							Integer[] ids = this.getCommonCountySdcond(id.toString());
							criteria.add(Restrictions.in("parentCBD.websiteId", ids ));
						}else{
							criteria.add(Restrictions.eq(key, oneMap.get(key)));
						}
				} else 
				{
					criteria.add(Restrictions.eq(key, oneMap.get(key)));
				}
			}
		}

		itKey = twoMap.keySet().iterator();
		while (itKey.hasNext()) {
			key = (String) itKey.next();
			if("previousRentPrice".equals(key.trim())){
				Object[] p = {twoMap.get(key).get(0), twoMap.get(key).get(1) };
				Type[] t = {Hibernate.FLOAT, Hibernate.FLOAT};
				criteria.add(Restrictions.sqlRestriction("(previous_rent_price - rent_price)>=? and (previous_rent_price - rent_price)<=?", p, t ));
			}else if (!key.trim().equals("distance")) {
				criteria.add(Restrictions.between(key, ((List) twoMap.get(key)).get(0), ((List) twoMap.get(key)).get(1)));
			}
		}

		itKey = tagsMap.keySet().iterator();
		while (itKey.hasNext()) {
			key = (String) itKey.next();
			if (((String) tagsMap.get(key)).indexOf(",") > 0) {
				String[] tags = ((String) tagsMap.get(key)).split(",");
				StringBuilder sb = new StringBuilder("");
				for (int ii = 0; ii < tags.length; ++ii) {
					sb.append("%,").append(tags[ii]).append(",");
				}
				sb.append("%");
				criteria.add(Restrictions.like(key, sb.toString()));
			} else {
				criteria.add(Restrictions.like(key, "%," + (String) tagsMap.get(key) + ",%"));
			}
		}

		if (orderStr != null && !"".equals(orderStr.trim())) {
			if (sortfield.equals("sortIndex")) {
				List<String> sortList = HouseSortRuleCacheUtil.getInstance().getSortFields();
				Iterator<String> it = sortList.iterator();
				while (it.hasNext()) {
					String sortField = it.next();
					if (!"exclusiveFlag".equals(sortField)) {
						criteria.addOrder(Order.desc(sortField));
					}
				}
			} else if ("Asc".equalsIgnoreCase(orderStr)) {
				criteria.addOrder(Order.asc(sortfield));
			} else {
				criteria.addOrder(Order.desc(sortfield));
			}
		}

		//查询所有房源数，含没有图片的
		//Modified by Sheng Youfu at 2015.03.02
		Long totalCount = getTotalRowCount(criteria);
		
		/*
		 * 如果查询条件中含有“showNoPic”标志，或者showNoPic=false 或 showNoPic=f 或 showNoPic=0，则增加过滤掉没有图片的房源
		 * Added by sheng Youfu at 2015.03.02
		 */
		if(!oneMap.containsKey("showNoPic") || "false".equalsIgnoreCase((String)oneMap.get("showNoPic")) 
				|| "f".equalsIgnoreCase((String)oneMap.get("showNoPic")) || "0".equals((String)oneMap.get("showNoPic"))){

			DetachedCriteria picCriteria = DetachedCriteria.forClass(HousePic.class, "pic")
					.setProjection(Property.forName("shelvingID"))
					.add(Property.forName("pic.shelvingID").eqProperty("h.shelvingID"));
			//追加exists查询
			criteria.add(Subqueries.exists(picCriteria));
			/************************************/
		}

		CutPageBean cutPageBean = this.getCutPage(pageBean, criteria);
		if(cutPageBean.getTotalRows()>0) 
			cutPageBean.setTotalRows(totalCount.intValue());		//页面显示总记录数，包括可显示和不显示的

		return cutPageBean;
	}

	
	/**
	 * 获取SEO租赁分类商圈的ID
	 * @param erpId
	 * @return
	 */
	private Integer[] getCommonCountySdcond(String erpId){
		String Hql = "from AreaSecond  where erpId = " + erpId + " and deleteFlag =0 ";
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(Hql);
		List<AreaSecond> list = query.list();
		String id = null;
		for(AreaSecond a : list){
			id = a.getCommonIds();
		}
		String[] ids = id.split(",");
		Integer[] intIds = new Integer[ids.length]; 
		for(int i=0; i<ids.length; i++ ){
			intIds[i] = Integer.valueOf(ids[i]);
		}
		return intIds;
	}

	/**
	 * 查询当前页的房源数据
	 * @param pageBean
	 * @param criteria
	 * @param totalRow
	 * @return
	 */
	private CutPageBean getCutPage(CutPageBean pageBean, Criteria criteria) {
		//查询可显示的房源数
		Long totalRow = getTotalRowCount(criteria);

		CutPageBean newPage = new CutPageBean();
		newPage.setExport(pageBean.isExport());
		newPage.setPageSize(pageBean.getPageSize());
		newPage.setTotalRows(totalRow.intValue());
		if (totalRow == 0) {
			return newPage;
		} else {
			pageBean.setTotalRows(totalRow.intValue());
			newPage.setCurrentPage(pageBean.getCurrentPage());
			if (!newPage.isExport()) {
				criteria.setFirstResult(newPage.getPageSize() * (pageBean.getCurrentPage() - 1));
				criteria.setMaxResults(newPage.getPageSize());
			}

			criteria.setProjection((Projection) null).setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			newPage.setDataList(criteria.list());
			return newPage;
		}
	}

	public List<CommunitySubwayStationMapping> getCommunityListBySubwayId(String id, List<Float> distance) {
		List list = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = ((Float) distance.get(1)).floatValue();
			float objs2 = ((Float) distance.get(0)).floatValue();
			String sqlResult1 = "from CommunitySubwayStationMapping t where t.subwayStation.subwayLine.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] objs1 = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult1, objs1);
		} else {
			String sqlResult = "from CommunitySubwayStationMapping t where t.subwayStation.subwayLine.erpId = ? and t.deleteFlag =?";
			Object[] objs = new Object[] { id, Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlResult, objs);
		}

		return list;
	}

	public List<HousePic> getHousePicByIdAndPicType(String houseId, int houseType, int picType, int count) {
		String sqlString = "from HousePic t where t.houseId = " + houseId + " and t.houseType = " + houseType
				+ " and t.picType = " + picType + " and t.deleteFlag = 0";
		return count == 0 ? this.hibernateTemplate.find(sqlString) : this.getQueryWithMaxCount(sqlString, count);
	}

	public List<HouseRentPriceTrend> getRentHouseTrendForSixMonth(String houseId) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		String resultSql = "from HouseRentPriceTrend t where t.houseId = \'" + houseId + "\' and dateandmonth <= \'"
				+ dateStr + "\' and deleteFlag = 0 order by dateandmonth desc";
		List list = this.getQueryWithMaxCount(resultSql, 6);
		return list;
	}

	public float getRentHouseLastMonthPrice(String houseId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date1 = new Date();
		date1.setMonth(date1.getMonth() + 1);
		String dateStr1 = dateFormat.format(date1);
		Date date2 = new Date();
		date2.setMonth(date2.getMonth() - 1);
		String dateStr2 = dateFormat.format(date2);
		String resultSql = "from HouseSecondPriceTrend t where t.houseId = \'" + houseId + "\' and dateandmonth < \'"
				+ dateStr1 + "\' and dateandmonth > \'" + dateStr2 + "\' and deleteFlag = 0";
		List list = this.hibernateTemplate.find(resultSql);
		return list != null && list.size() > 0 ? ((HouseSecondPriceTrend) list.get(0)).getPrice().floatValue() : 0.0F;
	}

	public void updateBrowsed(String houseNo, int browsed) {
		String sqlString = "update HouseRent t set t.browsed = ? where t.hourseNo = ?";
		Object[] objs = new Object[] { Integer.valueOf(browsed), houseNo };
		this.hibernateTemplate.bulkUpdate(sqlString, objs);
	}

	public HouseRentPriceTrend getHousePriceLastMonth(String houseId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1);
		int month = now.get(2);
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseRentPriceTrend.class);

		try {
			cri.add(Restrictions.eq("dateAndMonth", format.parse(today)));
			cri.add(Restrictions.eq("houseId", houseId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (HouseRentPriceTrend) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public HouseRentPriceTrend getHousePriceLastYear(String houseId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1) - 1;
		int month = now.get(2);
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseRentPriceTrend.class);

		try {
			cri.add(Restrictions.eq("dateAndMonth", format.parse(today)));
			cri.add(Restrictions.eq("houseId", houseId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (HouseRentPriceTrend) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public HouseRentPriceTrend getHousePriceThisMonth(String houseId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1);
		int month = now.get(2) + 1;
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseRentPriceTrend.class);

		try {
			cri.add(Restrictions.eq("dateAndMonth", format.parse(today)));
			cri.add(Restrictions.eq("houseId", houseId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (HouseRentPriceTrend) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public CutPageBean searchRentHouse(String key, CutPageBean pageBean, List<CommonBean> commonList) {
		key = "%" + key + "%";
		String totalRowsHsql = "select count(hr) from HouseRent hr where hr.community.cbd.cbdName like ? or hr.community.communityName like ?";
		String hql = " from HouseRent hr where hr.community.cbd.cbdName like ? or hr.community.communityName like ?";
		Object[] obj = new Object[] { key, key };
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, hql, "", obj,
				pageBean, commonList);
		return cutPageBean;
	}

	public Broker getBrokerById(String brokerId) {
		String resultSql = "from Broker b where b.erpId = ?";
		List broker = this.hibernateTemplate.find(resultSql, brokerId);
		return broker.size() > 0 ? (Broker) broker.get(0) : new Broker();
	}

	public Decorations getDecorationsById(String decorationsId) {
		String resultSql = "from Decorations b where b.erpId = ?";
		List decorations = this.hibernateTemplate.find(resultSql, decorationsId);
		return decorations.size() > 0 ? (Decorations) decorations.get(0) : new Decorations();
	}

	public Usage getUsageById(String usageId) {
		String resultSql = "from Usage b where b.erpId = ?";
		List usage = this.hibernateTemplate.find(resultSql, usageId);
		return usage.size() > 0 ? (Usage) usage.get(0) : new Usage();
	}

	public HouseRent getHouseRentByHouseNo(String houseNo) {
		HouseRent houseRent = null;
		String resultSql = "from HouseRent t where t.hourseNo = ? and t.deleteFlag = ?";
		List houseRentList = this.hibernateTemplate.find(resultSql, new Object[] { houseNo, Integer.valueOf(0) });
		if (houseRentList != null && houseRentList.size() > 0) {
			houseRent = (HouseRent) houseRentList.get(0);
		}

		return houseRent;
	}

	public List<HouseRent> getRecommandedHouseRent(String price, String area, String shi, String erpId) {
		String[] priceRanges = price.split(",");
		Float priceMin = Float.valueOf(priceRanges[0]);
		Float priceMax = Float.valueOf(priceRanges[1]);
		String[] areaRanges = area.split(",");
		Float areaMin = Float.valueOf(areaRanges[0]);
		Float areaMax = Float.valueOf(areaRanges[1]);
		Integer shiInteger = Integer.valueOf(shi);
		String resultSql = "from HouseRent t where t.rentPrice between " + priceMin + " and " + priceMax
				+ " and t.area between " + areaMin + " and " + areaMax + " and t.shi = " + shiInteger + " and t.erpId <> \'"
				+ erpId + "\' and t.deleteFlag = 0";
		List houseRentList = this.getQueryWithMaxCount(resultSql, 4);
		return houseRentList;
	}

	public boolean hasThisHouse(String houseNos) {
		String[] split = houseNos.split(",");
		List list = Arrays.asList(split);
		String sqlConditionStr = "";

		String resultSql;
		String count;
		for (Iterator query = list.iterator(); query.hasNext(); sqlConditionStr = sqlConditionStr + count + ",") {
			resultSql = (String) query.next();
			count = "\'" + resultSql + "\'";
		}

		sqlConditionStr = sqlConditionStr.substring(0, sqlConditionStr.lastIndexOf(","));
		resultSql = "select count(t.hourseNo) from HouseRent t where t.hourseNo in ( " + sqlConditionStr + ")";
		Query query1 = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(resultSql);
		int count1 = ((Number) query1.iterate().next()).intValue();
		return count1 > 0;
	}
	

	/**
	 * 查询推送到百度的房源
	 * 
	 * @return
	 */
	@Override
	public List<HouseRent> getHousesForBaidu(int count) {
		List<HouseRent> houseList = new ArrayList<HouseRent>();
//		List<BaiDuHouseRent> bdHouseList = new ArrayList<BaiDuHouseRent>();

//		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BaiDuHouseRent.class, "h");
//		// 获取没有同步的数据
//		criteria.add(Restrictions.eq("isSync", false));
//		criteria.add(Restrictions.eq("state",1));
//		if (count > 0) {
//			criteria.setMaxResults(count);
//			criteria.setFirstResult(0);
//		}
//		bdHouseList = criteria.list();
//		//当bdHouseList没有数据时，则更新状态2的数据
//		if(bdHouseList == null || bdHouseList.size() < 1){
//			
//		}
//		Criteria cr = null;
//		List<HouseRent> list = null;
//		for (BaiDuHouseRent bd : bdHouseList) {
//			cr = getSessionFactory().getCurrentSession().createCriteria(HouseRent.class, "h");
//			cr.add(Restrictions.eq("hourseNo", bd.getHouseId()));
//			list = cr.list();
//			if(list == null || list.size() < 1){
//				//说明该房源不存在，要删除
//				bd.setState(3);
//				this.hibernateTemplate.save(bd);
//				continue;
//			}
		//处理不匹配的数据
		modifyHouseData();
		//判断是否有更新的数据
//		insertAndUpdateHouseDate();
		//没有数据提交时，则循环提交
//		cycleSubmitBaiDUHouseRent();
		final int counts = count;
	 	@SuppressWarnings("unchecked")
		List<HouseRent> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
				String hql = " from HouseRent hs where hs.hourseNo in (select houseId from BaiDuHouseRent bd where bd.isSync = false and bd.state = 1 )";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);  
                query.setMaxResults(counts); 
				return query.list();
			}
			
		});
			// 只有一条记录
			for (HouseRent house : list) {

				//先记录.方便于状态存储
				BaiDuHouseRent bd = new BaiDuHouseRent();
				bd.setHouseId(house.getHourseNo());
				bd.setTitle(house.getTitle());
				bd.setState(1);
				bd.setIsSync(false);
				
				List<HouseAppraise> appraiseList = this.findAppraiseListByHouseNo(house.getHourseNo());
				Set<HouseAppraise> appSet = new HashSet<HouseAppraise>(appraiseList);
				house.setAppraises(appSet);

				// 获取封页图片
				Criteria c1 = getSessionFactory().getCurrentSession().createCriteria(HousePic.class, "p");
				c1.add(Restrictions.eq("houseId", house.getErpId()));
				c1.add(Restrictions.eq("houseType", 2));
				c1.add(Restrictions.eq("layoutFlag", 1));
				List<HousePic> housePic = c1.list();
				if (housePic.size() < 1){
					System.out.println("mei_you_feng_mian_tu_pian  rent_house_id:" + bd.getHouseId() );
					bd.setState(4);
					bd.setIsSync(false);
					house.setBaiDuHouseRentList(bd);
					house.setPictureUrl(null);
					houseList.add(house);
					log.error("mei_you_feng_mian_tu_pian  rent_house_id:" + bd.getHouseId() );
//					this.hibernateTemplate.update(bd);
					continue;
				}
				// 获取该房源全部图片
				Criteria c2 = getSessionFactory().getCurrentSession().createCriteria(HousePic.class, "u");
				c2.add(Restrictions.eq("houseId",house.getErpId()));
				c2.add(Restrictions.eq("houseType", 2));
				List<HousePic> housePics = c2.list();
				List<ERPHousePicture> housePicList = new ArrayList<ERPHousePicture>();
				for (HousePic hp : housePics) {
					if (hp.getErpId().equals(housePic.get(0).getErpId()))
						continue;
					ERPHousePicture erpH = new ERPHousePicture();
					erpH.setPicUrl(hp.getPicUrl());
					housePicList.add(erpH);
				}
				
				// 获取家具
				String furStr = house.getShowFunitures();
				// 取出家具Id
				String[] furArr = furStr.split(",");
				List<String> furIdList = new ArrayList<String>();
				List<Furniture> furnitureList = new ArrayList<Furniture>();

				for (int i = 0; i < furArr.length; i++) {
					if (org.apache.commons.lang.StringUtils.isBlank(furArr[i]))
						continue;
					furIdList.add(furArr[i]);
				}

				List<Furniture> furList = this.getFurList();
				if (furIdList != null && furIdList.size() > 0) {
					for (int i = 0; i < furList.size(); i++) {
						for (int j = 0; j < furIdList.size(); j++) {
							if ((furList.get(i).getErpId()).equals(furIdList.get(j)))
								furnitureList.add(furList.get(i));
						}
					}
				}
				house.setFurnitureList(furnitureList);

				// 发布的时间
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				if (house.getLastModified() != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bd.setLastmod(Timestamp.valueOf(df.format(house.getLastModified())));
				} else
					bd.setLastmod(ts);
//				bd.setIsSync(true);
				bd.setPublishTime(ts);
				if (housePic.size() > 0)
					house.setPictureUrl(housePics.get(0).getPicUrl());
				house.setErpHousePicList(housePicList);
				house.setBaiDuHouseRentList(bd);
				houseList.add(house);
			}
//			this.hibernateTemplate.save(bd);
//		}
		return houseList;
	}

	/**
	 * 查询推送百度需要删除的房源
	 * 
	 * @param count
	 * @return
	 */
	@Override
	public List<BaiDuHouseRent> getDelHousesForBaidu(int count) {
		
		this.modifyHouseData();
//		List<HouseRent> houseList = new ArrayList<HouseRent>();
		List<BaiDuHouseRent> bdHouseList = new ArrayList<BaiDuHouseRent>();

		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BaiDuHouseRent.class, "b");
		criteria.add(Restrictions.eq("isSync",true));
		criteria.add(Restrictions.eq("state",3));
		if (count > 0) {
			criteria.setMaxResults(count);
			criteria.setFirstResult(0);
		}
		bdHouseList = criteria.list();
//		System.out.println("bdHouseList: " + bdHouseList.get(0).getIsSync());
//		List<HouseRent> list = new ArrayList<HouseRent>();
		for (BaiDuHouseRent bd : bdHouseList) {
//			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HouseRent.class, "h");
//			cr.add(Restrictions.eq("hourseNo", bd.getHouseId()));
//			list = cr.list();
////			bd.setIsSync(false);
//			for (HouseRent hr : list) {
//				hr.setBaiDuHouseRentList(bd);
//				houseList.add(hr);
//			}
//			this.hibernateTemplate.save(bd);
			//处理XML里的数据
			new DocumentXML (bd.getHouseId(), LAST_COMMIT_XML_SAVE_PATH + "rent.xml", "delete");
		}
		return bdHouseList;
	}

	@Override
	public void saveChangeIsSyncDel(List<BaiDuHouseRent> list) {
		if(list != null || list.size() > 0){
			for(BaiDuHouseRent bd : list){
				this.hibernateTemplate.update(bd);
			}
		}
	}
	/**
	 * 保存isSync是否同步状态
	 * @param houseRent
	 */
	@Override
	public void saveChangeIsSync(List<HouseRent> houseRent){
		if(houseRent != null || houseRent.size() > 0){
			for(HouseRent hr : houseRent){
				BaiDuHouseRent bd = hr.getBaiDuHouseRentList();
//				this.hibernateTemplate.update(bd);
				String hql = null;
				Object[] obs = null;
				if(bd.getState() != 1){
					hql = "replace into partner_baidu_house_rent(house_id, title, is_sync, status ) values (?,?,?,?)";
					obs = new Object[]{bd.getHouseId(), bd.getTitle(), bd.getIsSync(), bd.getState()};
				}else{
//					hql = "replace into BaiDuHouseSecond(houseId, title, lastmod, publishTime, isSync, state, brokerName, telephone, price, brokerErpId) values (?,?,?,?,?,?,?,?,?,?)";
					hql = "replace into partner_baidu_house_rent(house_id, title, lastmod, publish_time, is_sync, status, broker_name, telephone, price, broker_erp_id) values (?,?,?,?,?,?,?,?,?,?)";
					obs = new Object[]{bd.getHouseId(), bd.getTitle(), bd.getLastmod(), bd.getPublishTime(), bd.getIsSync(), bd.getState(),
							bd.getBrokerName(), bd.getTelephone(), bd.getPrice(), bd.getBrokerErpId()};
				}
				final String resultHql = hql;
				final Object[] values = obs;
				this.hibernateTemplate.executeFind(new HibernateCallback(){

					@Override
					public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
						Query query = session.createSQLQuery(resultHql);
						for(int i = 0; i<values.length; i++){
							query.setParameter(i, values[i]);
						} 
						query.executeUpdate();
						Object o = null;
						return o;
					}
					
				});
			}
		}
	}
	

	/**
	 * 先把不匹配的数据给标记
	 */
	private void modifyHouseData(){
		@SuppressWarnings("unchecked")
		List<BaiDuHouseRent> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
//				String hql = " from HouseSecond hs where hs.houseNo in (select houseId from BaiDuHouseSecond bd where bd.isSync = false and bd.state = 1  )  ";
				
//				String hql = " FROM  BaiDuHouseRent p WHERE p.isSync = 0 and p.state = 1 and  "
//						+ "NOT EXISTS (select h.hourseNo FROM HouseRent h WHERE h.hourseNo = p.houseId	)  ";
				String hql = " FROM  BaiDuHouseRent p WHERE p.state = 1 and  "
						+ "NOT EXISTS (select h.hourseNo FROM HouseRent h WHERE h.hourseNo = p.houseId	)  ";
				
				Query query = session.createQuery(hql);
				return query.list();
			}
			
		});
		if(list.size() > 0){
		for(BaiDuHouseRent bd : list){
//			bd.setIsSync(false);
			bd.setState(3);
			this.hibernateTemplate.update(bd);
		}
		}
	} 
	
	/**
	 * 随机查询租赁房热门小区
	 * @param count 数量
	 * @return
	 */
	public List<Community> getRandomHotCommunityForRent(Integer count) {
		List<AreaSecond> areaSecondList = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(AreaSecond.class, "as")
				.add(Restrictions.eq("deleteFlag", 0))
				.list();
		StringBuilder sbIds = new StringBuilder("");
		for(AreaSecond as : areaSecondList){
			sbIds.append(",").append(as.getCommonIds());
		}
		sbIds.deleteCharAt(0);
		String ids = sbIds.toString();
		ids = ids.replaceAll(",+", ",");
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Community.class, "c");
		criteria.add(Restrictions.in("c.erpId", ids.split(",")));
		
		List<Community> linkList = (List<Community>)this.getRadomList(criteria, count);
		return linkList;
	}

	/**
	 * 获取所有出租房源前12个月平均挂牌价
	 * @return
	 */
	public List<Object[]> getHouseAvgRentPriceTrendLastYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		now.add(Calendar.YEAR, -1); //取上年同日
		String ld = sdf.format(now.getTime());
		
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseRentPriceTrend.class);
	  cri.setProjection(
	  		Projections.projectionList()
	  		.add(Projections.avg("price").as("avgPrice"))
	  		.add(Projections.groupProperty("dateAndMonth")));	
		try {
			cri.add(Restrictions.ge("dateAndMonth", sdf.parse(ld))).add(Restrictions.eq("deleteFlag", 0));
			List<Object[]> list = cri.list();
//			for(Object[] os: list){
//				Double val = (Double)os[0];
//				val = Math.floor(val * 100 + 0.5) / 100;
//				os[0] = val;
//			}
			return list;
		} catch (ParseException ex) {
			log.error(ex);
			return null;
		}
	}	
	
	
	/**
	 * 根据城区统计出租房源数
	 * @return
	 */
	public List getHouseCountByArea(){
		StringBuilder hql = new StringBuilder("select area.erpId, area.countyName, area.location, count(*) ")
		.append(" from HouseRent h ")
		.append(" inner join Community hc on h.communityId=hc.erpId ")
		.append(" inner join CBD cbd on hc.businessCircleId=cbd.erpId ")
		.append(" inner join Area area on cbd.countyId=area.erpId ")
		.append(" where h.deleteflag<>1 ")
		.append("group by area.erpId, area.countyName, area.location ");
		
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

}
