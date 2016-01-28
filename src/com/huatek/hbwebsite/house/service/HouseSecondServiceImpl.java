package com.huatek.hbwebsite.house.service;

import java.io.StringReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.huatek.authority.factory.DataAuthority;
import com.huatek.ddhb.manage.footerManage.entity.FooterLink;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.CommunityHospitalMapping;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.HouseRate;
import com.huatek.hbwebsite.common.entity.HouseReduceNotice;
import com.huatek.hbwebsite.common.entity.PriceChangeHistory;
import com.huatek.hbwebsite.common.entity.PriceChart;
import com.huatek.hbwebsite.common.entity.School;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.house.entity.BaiDuHouseSecond;
import com.huatek.hbwebsite.house.entity.ERPHousePicture;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseCommunityAveragePrice;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.entity.HouseSecondPriceTrend;
import com.huatek.hbwebsite.mailer.EmailAbstract;
import com.huatek.hbwebsite.member.entity.MemberBespeak;
import com.huatek.hbwebsite.service.BaseServiceToImpl;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.EmailUrlUtil;
import com.huatek.hbwebsite.util.HouseSortRuleCacheUtil;
import com.huatek.hbwebsite.util.ReduceNoticeUtil;
import com.huatek.mail.service.MailSendService;

import cn.hshb.web.biz.util.HouseTagUtil;
import cn.hshb.web.common.exception.ExceptionUtil;
import cn.hshb.web.common.exception.HshbException;
import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.house.enums.EnumHouseTag;
import cn.hshb.web.partner.baidu.service.DocumentXML;

/**
 * 二手房源服务实现类
 * 
 * @author Administrator
 * @since 2015-01 
 * @version 1.0 http://www.hshb.cn
 */
public class HouseSecondServiceImpl extends BaseServiceToImpl implements HouseSecondService {
	private static final Log log = LogFactory.getLog(HouseSecondServiceImpl.class);

	@Autowired
	MailSendService mailSendService;
	@Autowired
	private EmailAbstract sendEmailService;
//	@Autowired
//	private PlatMemberInfoService platMemberInfoService;
	private static final Logger LOGGER = Logger.getLogger(ReduceNoticeUtil.class);

//	private static final String  LAST_COMMIT_XML_SAVE_PATH = String.valueOf( Parameter.getInstance().getProperty("last.commit.xml.save.path"));
	private static String  LAST_COMMIT_XML_SAVE_PATH = "./";
	/**
	 * 根据条件获取房源
	 */
	public CutPageBean getPageBean(CutPageBean cutPageBean, List<CommonBean> commonList, String sortSql) {
		String totalRowsHsql = "select count(t) from HouseSecond t where t.deleteFlag = 0 ";
		String resultSql = "from HouseSecond t where t.deleteFlag = 0";
		if (sortSql == null || "".equals(sortSql.trim())) {
			sortSql = "order by t.sortIndex asc";
		}

		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql,
				sortSql, cutPageBean, commonList);
		return pageBean;
	}

	/**
	 * 获取城区
	 */
	public List<Area> getAreaList() {
		String resultSql = "from Area t";
		@SuppressWarnings("unchecked")
		List<Area> areaList = this.hibernateTemplate.find(resultSql);
		return areaList;
	}

	/**
	 * 获取房源标签
	 */
	public List<Tag> getTagList() {
		String resultSql = "from Tag t";
		List<Tag> tagList = this.hibernateTemplate.find(resultSql);
		return tagList;
	}

	/**
	 * 解析房源中的房源标签
	 * @param tagStr 房源标签字符串，可能是逗号分隔的标签ID字符串，也可能是整型的按位存储的标签组合整型值
	 * @return
	 */
	@Override
	public List<Tag> parseHouseTag(String tagStr){
		List<Tag> list = new ArrayList<Tag>();
		if(tagStr!=null){
			if(StringUtil.isNumeric(tagStr)){
				Integer tagVal = StringUtil.parseInt(tagStr, 0);
				List<EnumHouseTag> tagList = HouseTagUtil.getTagsByValue(tagVal);
				for(EnumHouseTag t: tagList){
					Tag tag = new Tag();
					tag.setErpId(String.valueOf(t.getValue()));
					tag.setTagName(t.getTitle());
					tag.setTagColor(t.getColor());
					tag.setFontColor("#FFFFFF");
					list.add(tag);
				}
			}else{
				
				List<Tag> allTagList = getTagList();
				String[] tagIds = tagStr.split(",");
				for(String s: tagIds){
					for(Tag t: allTagList){
						if(t.getErpId().equalsIgnoreCase(s)){
							list.add(t);
							break;
						}
					}
				}
			}
		}
		return list;
	}
		
	/**
	 * 根据房源编号获取房源评价信息
	 */
	public List<HouseAppraise> findAppraiseListByHouseNo(String houseNo) {
		String resultSql = "from HouseAppraise t where t.houseNo = ? order by t.sortIndex asc";
		List<HouseAppraise> houseAppraiseList = this.hibernateTemplate.find(resultSql, houseNo);
		return houseAppraiseList;
	}

	/**
	 * 根据房源ID获取房源
	 */
	public HouseSecond findHouseSecondByHouseId(String houseId) {
		String resultSql = "from HouseSecond t where t.erpId = \'" + houseId + "\'";
		List<HouseSecond> houseSecondList = this.hibernateTemplate.find(resultSql);
		if(houseSecondList.size() == 0){
			return null;
		}
		return houseSecondList.get(0);
	}

	/**
	 * 计算租赁经纪人房源持有量
	 */
	public long findHouseRentCountByBrokerId(String brokerId) {
		/**
		 * 原程序逻辑错误，计算经纪人持有的房源量不要统计该经纪人评价的房源，只统计该经纪人发布的房源即可 Modified by syf at
		 * 2015.01.30 String totalRowsHsql =
		 * "select count(t) from HouseRent t where t.deleteFlag = 0 and t.broker.erpId = \'"
		 * + brokerId +
		 * "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 2 and ha.broker.erpId = \'"
		 * + brokerId + "\')";
		 */

		String totalRowsHsql = "select count(t) from HouseRent t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId + "\'";
		return ((Long) this.hibernateTemplate.find(totalRowsHsql).get(0)).longValue();
	}

	/**
	 * 计算每个经纪人持有的房源量
	 */
	public long findHouseSecondCountByBrokerId(String brokerId) {
		/**
		 * 原程序逻辑错误，计算经纪人持有的房源量不要统计该经纪人评价的房源，只统计该经纪人发布的房源即可 Modified by syf at
		 * 2015.01.30 String totalRowsHsql =
		 * "select count(distinct t.shelvingID) from HouseSecond t where t.deleteFlag = 0 and t.broker.erpId = \'"
		 * + brokerId +
		 * "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 1 and ha.broker.erpId = \'"
		 * + brokerId + "\')";
		 */
		String totalRowsHsql = "select count(distinct t.shelvingID) from HouseSecond t "
				+ "where t.deleteFlag = 0 and t.broker.erpId = \'" + brokerId + "\'";
		return ((Long) this.hibernateTemplate.find(totalRowsHsql).get(0));
	}

	/**
	 * 根据房源编号和经纪人ID获取房源评价
	 */
	public HouseAppraise findAppraiseByBrokerAndHouseNo(String houseNo, String brokerId) {
		HouseAppraise appraise = null;
		String resultSql = "from HouseAppraise t where t.houseNo =? and t.broker.erpId =? and t.deleteFlag = ?";
		Object[] obs = new Object[] { houseNo, brokerId, Integer.valueOf(0) };
		@SuppressWarnings("unchecked")
		List<HouseAppraise> appraiseList = this.hibernateTemplate.find(resultSql, obs);
		if (appraiseList.size() > 0) {
			// appraise = (HouseAppraise) this.hibernateTemplate.find(resultSql,
			// obs).get(0);
			appraise = appraiseList.get(0);
		}

		return appraise;
	}

	/**
	 * 根据会员ID和经纪人ID获取会员预约信息 TODO: 这个函数名称可能弄错了！！！
	 */
	public MemberBespeak findAppraiseByBrokerAndMember(Long memberId, String brokerId) {
		MemberBespeak memberBespeak = null;
		String resultSql = "from MemberBespeak t where t.platMemberInfo.id=? and t.platBroker.erpId=? and t.deleteFlag = ?";
		Object[] obs = new Object[] { memberId, brokerId, Integer.valueOf(0) };
		@SuppressWarnings("unchecked")
		List<MemberBespeak> list = this.hibernateTemplate.find(resultSql, obs);
		if (list != null && list.size() > 0) {
			memberBespeak = list.get(0);
		}
		return memberBespeak;
	}

	/**
	 * 根据房源代码查询买卖房源
	 */
	public List<HouseSecond> findSecondHouseListByHouseNos(String houseNos) {
		// String[] split = houseNos.split(",");
		List<String> houseNoList = Arrays.asList(houseNos.split(","));
		String sqlConditionStr = "";

		/*
		 * String str; String resultSql; for (Iterator houseSecondList =
		 * houseNoList.iterator(); houseSecondList.hasNext(); sqlConditionStr =
		 * sqlConditionStr + str + ",") { resultSql = (String)
		 * houseSecondList.next(); str = "\'" + resultSql + "\'"; }
		 */
		Iterator<String> itHouseNo = houseNoList.iterator();
		while (itHouseNo.hasNext()) {
			String houseNo = itHouseNo.next();
			sqlConditionStr = sqlConditionStr + "\'" + houseNo + "\'" + ",";
		}

		sqlConditionStr = sqlConditionStr.substring(0, sqlConditionStr.lastIndexOf(","));
		String resultSql = "from HouseSecond t where t.houseNo in ( " + sqlConditionStr + ")";
		List<HouseSecond> houseSecondList = this.hibernateTemplate.find(resultSql);
		return houseSecondList;
	}

	/**
	 * 根据房源ID获取房源图片
	 */
	public List<HousePic> finSecondHousePicListByHouseId(String[] ids, int picType) {
		StringBuilder sb = new StringBuilder("");

		for (String id : ids) {
			sb.append(id).append("'");
		}
		sb.deleteCharAt(sb.length() - 1);

		String sqlStr = "from HousePic t where t.houseId in ( " + sb.toString() + ") and t.picType = " + picType;
		List<HousePic> housePicList = this.hibernateTemplate.find(sqlStr);
		return housePicList;
	}

	/**
	 * 计算房源平均面积和平均价格
	 */
	public float[] findAverageValueForPriceAndArea(List<HouseSecond> houseSecondList) {
		float a = 0.0F;
		float b = 0.0F;

		for (int i = 0; i < houseSecondList.size(); ++i) {
			a += houseSecondList.get(i).getPrice();
			b += houseSecondList.get(i).getArea();
		}

		return new float[] { a / (float) houseSecondList.size(), b / (float) houseSecondList.size() };
	}

	/**
	 * 根据SQL获取房源
	 */
	public List<HouseSecond> getHouseSecondBySQL(String sql, HouseSecond houseSecond) {
		List<HouseSecond> houseSecondList = this.getQueryWithMaxCount(sql, 5);
		if (houseSecondList.contains(houseSecond)) {
			houseSecondList.remove(houseSecond);
		} else if (houseSecondList.size() > 4) {
			houseSecondList = houseSecondList.subList(0, 4);
		}
		return houseSecondList;
	}

	/**
	 * 根据房源类型获取房源评价
	 */
	public List<HouseAppraise> getHouseAppraisesByHouseType(Integer type) {
		String sqlResult = "from HouseAppraise t where t.houseType =? and t.deleteFlag=?";
		Object[] objs = new Object[] { type, Integer.valueOf(0) };
		List<HouseAppraise> list = this.hibernateTemplate.find(sqlResult, objs);
		return list;
	}
	
	/**
	 * 根据房源编号查询房源评价信息
	 * @param houseNoList	房源编号列表
	 * @return
	 */
	public List<HouseAppraise> getHouseAppraisesByHouseNo(List<String> houseNoList) {
		String sqlResult = "from HouseAppraise t where t.deleteFlag=0 AND t.houseNo in (?)";
		StringBuilder sbHouseNos = new StringBuilder("");
		for(String houseNo: houseNoList){
			sbHouseNos.append(",'").append(houseNo).append("'");
		}
		sbHouseNos.deleteCharAt(0);
		Object[] objs = new Object[] { sbHouseNos.toString() };
		List<HouseAppraise> list = this.hibernateTemplate.find(sqlResult, objs);
		return list;
	}
	

	/**
	 * 根据房源ID获取房源最近6个月价格走势
	 */
	public List<HouseSecondPriceTrend> getSecondHouseTrendForSixMonth(String houseId) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		String resultSql = "from HouseSecondPriceTrend t where t.houseId = \'" + houseId + "\' and dateandmonth <= \'"
				+ dateStr + "\' and deleteFlag = 0 order by t.dateAndMonth desc";
		List<HouseSecondPriceTrend> list = this.getQueryWithMaxCount(resultSql, 6);
		return list;
	}

	/**
	 * 根据房源ID获取房源最近一月总价
	 */
	public float getSecondHouseLastMonthPrice(String houseId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		// Date date1 = new Date();
		// date1.setMonth(date1.getMonth() + 1);
		// String dateStr1 = dateFormat.format(date1);
		// Date date2 = new Date();
		// date2.setMonth(date2.getMonth() - 1);
		// String dateStr2 = dateFormat.format(date2);

		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, -1);

		String dateStr1 = dateFormat.format(cal1.getTime());
		String dateStr2 = dateFormat.format(cal2.getTime());

		String resultSql = "from HouseSecondPriceTrend t where t.houseId = \'" + houseId + "\' and dateandmonth < \'"
				+ dateStr1 + "\' and dateandmonth > \'" + dateStr2 + "\' and deleteFlag = 0";
		List<HouseSecondPriceTrend> list = this.hibernateTemplate.find(resultSql);
		return list != null && list.size() > 0 ? list.get(0).getPrice() : 0.0F;
	}

	/**
	 * 根据房源ID获取房源最近一个月单价
	 */
	public float getSecondHouseLastMonthUnitPrice(String houseId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		/*
		 * Date date1 = new Date(); date1.setMonth(date1.getMonth() + 1); String
		 * dateStr1 = dateFormat.format(date1); Date date2 = new Date();
		 * date2.setMonth(date2.getMonth() - 1); String dateStr2 =
		 * dateFormat.format(date2);
		 */
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, -1);

		String dateStr1 = dateFormat.format(cal1.getTime());
		String dateStr2 = dateFormat.format(cal2.getTime());

		String resultSql = "from HouseSecondPriceTrend t where t.houseId = " + houseId + " and dateandmonth < \'"
				+ dateStr1 + "\' and dateandmonth > \'" + dateStr2 + "\' and deleteFlag = 0";
		List<HouseSecondPriceTrend> list = this.hibernateTemplate.find(resultSql);
		return list != null && list.size() > 0 ? list.get(0).getUnitPrice() : 0.0F;
	}

	/**
	 * 根据小区ID获取小区与医院关联关系
	 */
	public List<CommunityHospitalMapping> findCHMapping(String id) {
		String sqlResult = "from CommunityHospitalMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] params = new Object[] { id, Integer.valueOf(0) };
		List<CommunityHospitalMapping> cHMapping = this.hibernateTemplate.find(sqlResult, params);
		return cHMapping;
	}

	/**
	 * 根据小区ID获取小区与学校关联关系
	 */
	public List<CommunitySchoolMapping> findCSMapping(String id) {
		String sqlResult = "from CommunitySchoolMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] params = new Object[] { id, Integer.valueOf(0) };
		List<CommunitySchoolMapping> cSMapping = this.hibernateTemplate.find(sqlResult, params);
		return cSMapping;
	}

	/**
	 * 根据小区ID获取小区房源本月均价
	 */
	public float getSecondHouseCommunityThisMonthPrice(String id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		// Date date1 = new Date();
		// date1.setMonth(date1.getMonth() + 1);
		// String dateStr1 = dateFormat.format(date1);
		// Date date2 = new Date();
		// date2.setMonth(date2.getMonth() - 1);
		// String dateStr2 = dateFormat.format(date2);

		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, -1);

		String dateStr1 = dateFormat.format(cal1.getTime());
		String dateStr2 = dateFormat.format(cal2.getTime());

		String resultSql = "from HouseCommunityAveragePrice t where t.communityNo = \'" + id
				+ "\' and calculateDate < \'" + dateStr1 + "\' and calculateDate > \'" + dateStr2
				+ "\' and deleteFlag = 0";
		List<HouseCommunityAveragePrice> list = this.hibernateTemplate.find(resultSql);
		return list != null && list.size() > 0 ? list.get(0).getPriceDealed() : 0.0F;
	}

	/**
	 * 根据小区ID获取小区最近一个月房源均价
	 */
	public float getSecondHouseCommunityLastMonthPrice(String id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		/*
		 * Date date1 = new Date(); date1.setMonth(date1.getMonth()); String
		 * dateStr1 = dateFormat.format(date1); Date date2 = new Date();
		 * date2.setMonth(date2.getMonth() - 2); String dateStr2 =
		 * dateFormat.format(date2);
		 */
		Calendar cal1 = Calendar.getInstance();
		// cal1.add(Calendar.MONTH, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, -2);

		String dateStr1 = dateFormat.format(cal1.getTime());
		String dateStr2 = dateFormat.format(cal2.getTime());

		String resultSql = "from HouseCommunityAveragePrice t where t.communityNo = \'" + id
				+ "\' and calculateDate < \'" + dateStr1 + "\' and calculateDate > \'" + dateStr2
				+ "\' and deleteFlag = 0";
		List<HouseCommunityAveragePrice> list = this.hibernateTemplate.find(resultSql);
		return list != null && list.size() > 0 ? list.get(0).getPriceDealed() : 0.0F;
	}

	/**
	 * 根据小区ID获取小区与车站对应关系
	 */
	public List<CommunityStationMapping> findCStaMapping(String id) {
		String sqlResult = "from CommunityStationMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] params = new Object[] { id, Integer.valueOf(0) };
		List<CommunityStationMapping> cStaMapping = this.hibernateTemplate.find(sqlResult, params);
		return cStaMapping;
	}

	/**
	 * 根据小区ID获取小区与地铁站对应关系
	 */
	public List<CommunitySubwayStationMapping> findCSubMapping(String id) {
		String sqlResult = "from CommunitySubwayStationMapping t where t.community.erpId =? and t.deleteFlag=?";
		Object[] params = new Object[] { id, Integer.valueOf(0) };
		List<CommunitySubwayStationMapping> cSubMapping = this.hibernateTemplate.find(sqlResult, params);
		return cSubMapping;
	}

	/**
	 * 根据价格区间查询小区信息
	 */
	public List<HouseSecond> getListByBetween(Float min, Float max) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(HouseSecond.class);
		criteria.add(Restrictions.between("price", min, max));
		List<HouseSecond> houseList = criteria.list();
		return houseList;
	}

	/**
	 * 根据地铁站ID获取小区信息
	 * 
	 * @param id
	 * @param distance
	 * @return
	 */
	public List<CommunitySubwayStationMapping> getCommunityListBySubwayId(String id, List<Float> distance) {
		List<CommunitySubwayStationMapping> list = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = distance.get(1).floatValue();
			float objs2 = distance.get(0).floatValue();
			String sqlStr = "from CommunitySubwayStationMapping t where t.subwayStation.subwayLine.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] params = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlStr, params);
		} else {
			String sqlStr = "from CommunitySubwayStationMapping t where t.subwayStation.subwayLine.erpId = ? and t.deleteFlag =?";
			Object[] params = new Object[] { id, Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlStr, params);
		}
		return list;
	}

	/**
	 * 根据车站ID获取小区信息
	 * 
	 * @param id
	 * @param distance
	 * @return
	 */
	public List<CommunitySubwayStationMapping> getCommunityListByStationId(String id, List<Float> distance) {
		List<CommunitySubwayStationMapping> list = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = distance.get(1).floatValue();
			float objs2 = distance.get(0).floatValue();
			String sqlStr = "from CommunitySubwayStationMapping t where t.subwayStation.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] params = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlStr, params);
		} else {
			String sqlStr = "from CommunitySubwayStationMapping t where t.subwayStation.erpId = ? and t.deleteFlag =?";
			Object[] params = new Object[] { id, Integer.valueOf(0) };
			list = this.hibernateTemplate.find(sqlStr, params);
		}

		return list;
	}

	/**
	 * 根据学校ID获取小区信息
	 * 
	 * @param id
	 * @param distance
	 * @return
	 */
	public List<CommunitySchoolMapping> getCommunityListBySchoolId(String id, List<Float> distance) {
		List<CommunitySchoolMapping> mappingList = null;
		if (distance != null && distance.size() == 2) {
			float sqlResult2 = distance.get(1).floatValue();
			float objs2 = distance.get(0).floatValue();
			String sqlStr = "from CommunitySchoolMapping t where t.school.erpId = ? and t.distance between ? and ? and t.deleteFlag =?";
			Object[] params = new Object[] { id, Float.valueOf(objs2), Float.valueOf(sqlResult2), Integer.valueOf(0) };
			mappingList = this.hibernateTemplate.find(sqlStr, params);
		} else {
			String sqlStr = "from CommunitySchoolMapping t where t.school.erpId = ? and t.deleteFlag =?";
			Object[] params = new Object[] { id, Integer.valueOf(0) };
			mappingList = this.hibernateTemplate.find(sqlStr, params);
		}

		return mappingList;
	}

	public List<CommunitySchoolMapping> getCommunityListByszids(List<String> ids) {
		String[] idlongs = ids.toArray(new String[ids.size()]);
		StringBuffer param = new StringBuffer("");

		for (int i = 0; i < idlongs.length; ++i) {
			if (i > 0) {
				param.append(",");
			}

			param.append("?");
		}

		if (!param.toString().trim().equals("")) {
			return this.hibernateTemplate.find("from CommunitySchoolMapping t where t.deleteFlag = 0 and t.erpId in ("
					+ param + ")", idlongs);
		} else {
			return null;
		}
	}

	public List<School> getStudyListBySztypeid(String id) {
		String sqlResult = "from School t where t.studyZoneType.erpId = ? and t.deleteFlag =?";
		Object[] objs = new Object[] { id, Integer.valueOf(0) };
		List list = this.hibernateTemplate.find(sqlResult, objs);
		return list;
	}

	/**
	 * 查询二手房数据
	 */
	public CutPageBean getSearchFiledList(CutPageBean pageBean, Map<String, Object> oneMap, Map<String, List> twoMap,
			Map<String, String> tagsMap, String orderStr, String sortfield) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(HouseSecond.class, "h");
		criteria.createAlias("h.community", "community");
		for (String key : oneMap.keySet()) {
			if (key.equals("inputSearch")) {
				criteria.createAlias("community.region", "region");
				criteria.createAlias("community.cbd", "cbd");
				criteria.createCriteria("cbd.parentCBD", "parentCBD", 1);
				criteria.add(Restrictions.or(Restrictions.like("community.communityName", "%" + oneMap.get(key) + "%"),
						Restrictions.or(Restrictions.like("region.countyName", "%" + oneMap.get(key) + "%"),
								Restrictions.like("parentCBD.name", "%" + oneMap.get(key) + "%"))));
			} else if (key.equals("subwayline.erpId")) {
				List<CommunitySubwayStationMapping> list = null;
				List<Float> distanceValueList = null;
				if (twoMap.containsKey("distance")) {
					distanceValueList = twoMap.get("distance");
				}
				list = getCommunityListBySubwayId((String) oneMap.get(key), distanceValueList);
				List<String> idList = new ArrayList<String>();
				if ((list != null) && (list.size() > 0)) {
					for (CommunitySubwayStationMapping com : list) {
						if (com.getCommunity() != null) {
							idList.add(com.getCommunity().getErpId());
						}
					}
				}
				if (idList.size() > 0) {
					if (idList.size() == 1) {
						criteria.add(Restrictions.eq("community.erpId", idList.get(0)));
					} else {
						criteria.add(Restrictions.in("community.erpId", idList));
					}
				} else {
					criteria.add(Restrictions.eq("community.erpId", "0"));
				}

			} else if ("hideNoPic".equalsIgnoreCase(key)) {

			} else {
				if (key.equals("school.erpId")) {
					List<Float> distanceValueList = null;
					if (twoMap.containsKey("distance")) {
						distanceValueList = twoMap.get("distance");
					}
					List<CommunitySchoolMapping> list = getCommunityListBySchoolId((String) oneMap.get(key),
							distanceValueList);

					List<String> idList = new ArrayList<String>();
					if ((list != null) && (list.size() > 0)) {
						Iterator<CommunitySchoolMapping> it = list.iterator();
						while (it.hasNext()) {
							CommunitySchoolMapping com = (CommunitySchoolMapping) it.next();
							if (com.getCommunity() != null) {
								idList.add(com.getCommunity().getErpId());
							}
						}
					}
					if (idList.size() > 0) {
						if (idList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", idList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", idList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "0"));
					}
				} else if (key.equals("sztype.erpId")) {
					List<School> list = getStudyListBySztypeid((String) oneMap.get(key));
					List<String> szids = new ArrayList<String>();
					if ((list != null) && (list.size() > 0)) {
						for (School sz : list) {
							szids.add(sz.getErpId());
						}
					}
					List<CommunitySchoolMapping> csmlist = getCommunityListByszids(szids);
					List<String> cids = new ArrayList<String>();
					if ((csmlist != null) && (csmlist.size() > 0)) {
						Iterator<CommunitySchoolMapping> itCSM = csmlist.iterator();
						while (itCSM.hasNext()) {
							CommunitySchoolMapping com = itCSM.next();
							if (com.getCommunity() != null) {
								cids.add(com.getCommunity().getErpId());
							}
						}
					}
					if (cids.size() > 0) {
						if (cids.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", cids.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", cids));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "0"));
					}
				} else if (key.equals("subwaystation.erpId")) {
					List<Float> distanceValueList = null;
					if (twoMap.containsKey("distance")) {
						distanceValueList = twoMap.get("distance");
					}
					List<CommunitySubwayStationMapping> list = getCommunityListByStationId((String) oneMap.get(key),
							distanceValueList);
					List<String> commIdList = new ArrayList<String>();
					if ((list != null) && (list.size() > 0)) {
						Iterator<CommunitySubwayStationMapping> itCSSM = list.iterator();
						while (itCSSM.hasNext()) {
							CommunitySubwayStationMapping com = itCSSM.next();
							if (com.getCommunity() != null) {
								commIdList.add(com.getCommunity().getErpId());
							}
						}
					}
					if (commIdList.size() > 0) {
						if (commIdList.size() == 1) {
							criteria.add(Restrictions.eq("community.erpId", commIdList.get(0)));
						} else {
							criteria.add(Restrictions.in("community.erpId", commIdList));
						}
					} else {
						criteria.add(Restrictions.eq("community.erpId", "0"));
					}
				} else if (key.equals("community.cbd.parentCBD.websiteId")) {
					criteria.createAlias("community.cbd", "cbd");
					criteria.createAlias("cbd.parentCBD", "parentCBD");
					criteria.add(Restrictions.eq("parentCBD.websiteId",
							Integer.valueOf(String.valueOf(oneMap.get(key)))));
				} else {
					criteria.add(Restrictions.eq(key, oneMap.get(key)));
				}
			}
		}
		for (String key : twoMap.keySet()) {
			if (key.trim().equals("price")) {
				criteria.add(Restrictions.between(key, ((Float) twoMap.get(key).get(0)) * 10000.0F, ((Float) twoMap
						.get(key).get(1)) * 10000.0F));
			}else if("previousUnitPrice".equals(key)){                                                                                                   
				Object[] p = {twoMap.get(key).get(0), twoMap.get(key).get(1) };                                                                     
				Type[] t = {Hibernate.FLOAT, Hibernate.FLOAT};                                                                                      
				criteria.add(Restrictions.sqlRestriction("(previous_unit_price - unit_price)>=? and (previous_unit_price - unit_price)<=?", p, t ));
			}  else if (!key.trim().equals("distance")) {
				criteria.add(Restrictions.between(key, twoMap.get(key).get(0), twoMap.get(key).get(1)));
			}
		}

		for (String key : tagsMap.keySet()) {
			if (tagsMap.get(key).indexOf(",") > 0) {
				String[] tags = tagsMap.get(key).split(",");
				StringBuilder sb = new StringBuilder("");
				for (int ii = 0; ii < tags.length; ii++) {
					sb.append("%,").append(tags[ii]).append(",");
				}
				sb.append("%");
				criteria.add(Restrictions.like(key, sb.toString()));
			} else {
//				criteria.add(Restrictions.like(key, "%," + tagsMap.get(key) + ",%"));
				criteria.add(Restrictions.like(key, "%" + tagsMap.get(key) + "%"));
			}
		}
		if ((orderStr != null) && (!"".equals(orderStr.trim()))) {
			if (sortfield.equals("sortIndex")) {
				List<String> sortFieldsList = HouseSortRuleCacheUtil.getInstance().getSortFields();
				for (String field : sortFieldsList) {
					criteria.addOrder(Order.desc(field));
				}
			} else if ("Asc".equalsIgnoreCase(orderStr)) {
				criteria.addOrder(Order.asc(sortfield));
			} else {
				criteria.addOrder(Order.desc(sortfield));
			}
		}

		// 查询所有房源数，含没有图片的
		// Modified by Sheng Youfu at 2015.03.02
		Long totalCount = getTotalRowCount(criteria);

		/*
		 * 如果查询条件中含有“showNoPic”标志，或者showNoPic=false 或 showNoPic=f 或
		 * showNoPic=0，则增加过滤掉没有图片的房源 
		 * Added by sheng Youfu at 2015.03.02
		 */
		if (!oneMap.containsKey("showNoPic") || "false".equalsIgnoreCase((String) oneMap.get("showNoPic"))
				|| "f".equalsIgnoreCase((String) oneMap.get("showNoPic"))
				|| "0".equals((String) oneMap.get("showNoPic"))) {
			DetachedCriteria picCriteria = DetachedCriteria.forClass(HousePic.class, "pic")
					.setProjection(Property.forName("shelvingID"))
					.add(Property.forName("pic.shelvingID").eqProperty("h.shelvingID"));
			//追加exists查询
			criteria.add(Subqueries.exists(picCriteria));
		}
		/************************************/

		CutPageBean cutpageBean = getCutPage(pageBean, criteria);
		if (cutpageBean.getTotalRows() > 0)
			cutpageBean.setTotalRows(totalCount.intValue()); // 页面显示总记录数，包括可显示和不显示的

		return cutpageBean;
	}

	/**
	 * 查询当前页的房源数据
	 * 
	 * @param pageBean
	 * @param criteria
	 * @param totalRow
	 * @return
	 */
	private CutPageBean getCutPage(CutPageBean pageBean, Criteria criteria) {
		// 查询可显示的房源数
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

	/**
	 * 获取推荐房源
	 */
	public List<HouseSecond> loadRecommandedHouse(int count) {
		String sqlStr = " from HouseSecond hs inner join fetch hs.community order by hs.homepageRecommandFlag desc,hs.homepageRecommandTime desc";

		@SuppressWarnings("unchecked")
		List<HouseSecond> houseSecondList = (List<HouseSecond>) this.getQueryWithMaxCount(sqlStr, count);

		return houseSecondList;
	}

	/**
	 * 获取指定房源所有图片
	 */
	public List<HousePic> getAllHousePicList(String id) {
		Integer id1 = Integer.valueOf(Integer.parseInt(id));
		String sqlResult = "from HousePic t where t.houseId = ? and t.houseType = ? and t.deleteFlag =?";
		Object[] objs = new Object[] { id1, Integer.valueOf(1), Integer.valueOf(0) };

		@SuppressWarnings("unchecked")
		List<HousePic> picList = this.hibernateTemplate.find(sqlResult, objs);

		return picList;
	}

	public List<HousePic> getHeadHouseList(String id) {
		Integer id1 = Integer.valueOf(Integer.parseInt(id));
		String sqlResult = "from HousePic t where t.houseId = ? and t.houseType = ? and t.picType <= ?  and t.deleteFlag =?";
		Object[] objs = new Object[] { id1, Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0) };
		@SuppressWarnings("unchecked")
		List<HousePic> picList = this.hibernateTemplate.find(sqlResult, objs);
		return picList;
	}

	/**
	 * 根据房源ID和图片类型获取房源图片
	 * 
	 * @param houseIds
	 *            房源ID
	 * @param houseType
	 *            房源类型
	 * @param layoutFlag
	 *            图片位置标志，1：封面，2：全部，其他：非封面
	 * @param count
	 *            数量
	 */
	public List<HousePic> getHousePicByIdsAndPicType(String[] houseIds, int houseType, int layoutFlag, int count) {
		if (houseIds.length == 0) {
			return new ArrayList<HousePic>();
		} else {
			StringBuffer param = new StringBuffer("");

			for (int ii = 0; ii < houseIds.length; ++ii) {
				if (ii > 0) {
					param.append(",");
				}
				param.append("\'" + houseIds[ii] + "\'");
			}

			String sql = "from HousePic t where t.shelvingID in (" + param + ") and t.houseType = " + houseType;
			if (layoutFlag == 1) {
				sql = sql + " and t.layoutFlag = 1 and t.deleteFlag = 0";
			} else if (layoutFlag == 2) {
				sql = sql + " and t.deleteFlag = 0";
			} else {
				sql = sql + " and t.layoutFlag <> 1 and t.deleteFlag = 0";
			}

			String authorityTotalSQL = DataAuthority.getAuthorityString(sql, this.getSessionFactory());
			Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
			List objlist = query.list();
			return objlist;
		}
	}

	public List<HouseRate> getRate() {
		String sqlString = "from HouseRate t order by t.rateDate desc";
		return this.hibernateTemplate.find(sqlString);
	}

	public void updateBrowsed(String houseNo, int browsed) {
		String sqlString = "update HouseSecond t set t.browsed = ? where t.houseNo = ?";
		Object[] objs = new Object[] { Integer.valueOf(browsed), houseNo };
		this.hibernateTemplate.bulkUpdate(sqlString, objs);
	}

	public List<MemberBespeak> findAppraiseByMember(Long memberId) {
		String resultSql = "from MemberBespeak t where t.platMemberInfo.id=? and  t.deleteFlag = ?";
		Object[] obs = new Object[] { memberId, Integer.valueOf(0) };
		List list = this.hibernateTemplate.find(resultSql, obs);
		return list;
	}

	public HouseSecondPriceTrend getHousePriceLastMonth(String houseId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1);
		int month = now.get(2);
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseSecondPriceTrend.class);

		try {
			cri.add(Restrictions.eq("dateAndMonth", format.parse(today)));
			cri.add(Restrictions.eq("houseId", houseId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (HouseSecondPriceTrend) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public HouseSecondPriceTrend getHousePriceLastYear(String houseId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1) - 1;
		int month = now.get(2);
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseSecondPriceTrend.class);

		try {
			cri.add(Restrictions.eq("dateAndMonth", format.parse(today)));
			cri.add(Restrictions.eq("houseId", houseId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (HouseSecondPriceTrend) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public HouseSecondPriceTrend getHousePriceThisMonth(String houseId) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = now.get(1);
		int month = now.get(2) + 1;
		String today = year + "-" + (month > 10 ? Integer.valueOf(month) : "0" + month) + "-" + "01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseSecondPriceTrend.class);

		try {
			cri.add(Restrictions.eq("dateAndMonth", format.parse(today)));
			cri.add(Restrictions.eq("houseId", houseId));
			List list = cri.list();
			return list != null && list.size() > 0 ? (HouseSecondPriceTrend) list.get(0) : null;
		} catch (ParseException var9) {
			return null;
		}
	}

	public CutPageBean searchHouseSecond(String key, CutPageBean pageBean, List<CommonBean> commonList) {
		key = "%" + key + "%";
		String totalRowsHsql = "select count(t) from HouseSecond t where t.community.cbd.cbdName like ? or t.community.communityName like ?";
		String hql = " from HouseSecond hr where hr.community.cbd.cbdName like ? or hr.community.communityName like ?";
		Object[] obj = new Object[] { key, key };
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, hql, "", obj,
				pageBean, commonList);
		return cutPageBean;
	}

	public void addCurrentMonthPrice() {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery queryRH = session.createSQLQuery("CALL rhp_trend()");
		queryRH.executeUpdate();
		SQLQuery querySH = session.createSQLQuery("CALL shp_trend()");
		querySH.executeUpdate();
	}

	public List<PriceChangeHistory> getPriceChangeHistory() {
		String sqlString = "from PriceChangeHistory p where p.deleteFlag = 0";
		List list = this.hibernateTemplate.find(sqlString);
		return list;
	}

	public void doReduceNotice(List<PriceChangeHistory> priceChangeHistoryList) throws HshbException {
		for (int i = 0; i < priceChangeHistoryList.size(); ++i) {
			PriceChangeHistory temp = (PriceChangeHistory) priceChangeHistoryList.get(i);
			Object[] obj = new Object[] { temp.getHouseId(), temp.getHouseType() };
			String sqlString = "from HouseReduceNotice h where h.houseId = ? and h.houseType = ?  and  h.deleteFlag = 0 and (h.emailFlag = 1 or h.phoneFlag = 1)";
			List houseReduceNoticeList = this.hibernateTemplate.find(sqlString, obj);
			if (houseReduceNoticeList.size() > 0) {
				HouseReduceNotice houseReduceNotice = (HouseReduceNotice) houseReduceNoticeList.get(0);
				Object[] obj2 = new Object[] { houseReduceNotice.getHouseId() };
				String hql = "from HouseSecond h where h.erpId = ? and h.deleteFlag = 0";
				List tmpList = this.hibernateTemplate.find(hql, obj2);
				if (tmpList.size() > 0) {
					HouseSecond houseSecondTmp = (HouseSecond) tmpList.get(0);
					if (temp.getLastPrice().floatValue() - temp.getNewPrice().floatValue() >= 10000.0F) {
						final String erpId = houseReduceNotice.getErpId();

						if (houseReduceNotice.getEmailFlag() == 1 && houseReduceNotice.getEmailDone() == 0) {
							final HashMap<String, Object> mailDataMap = new HashMap<String, Object>();
							mailDataMap.put("AccountName", houseReduceNotice.getPlatMemberInfo().getAccName());
							mailDataMap.put("HouseTitle", houseSecondTmp.getTitle());
							mailDataMap.put("NewPrice", String.valueOf(temp.getNewPrice()));
							String baseUrl = EmailUrlUtil.getInstance().getEmailUrl();
							if (houseReduceNotice.getBroker() != null) {
								mailDataMap.put("BrokerName", houseReduceNotice.getBroker().getBname());
								mailDataMap.put("BrokerPhone", houseReduceNotice.getBroker().getTelephone());
								mailDataMap.put("HouseURL",
										baseUrl + "houseSecond.show?actionMethod=houseSecondDetail&houseNo="
												+ houseSecondTmp.getHouseNo() + "&brokerId="
												+ houseSecondTmp.getBroker().getErpId());
							} else {
								mailDataMap.put("BrokerName", "");
								mailDataMap.put("BrokerPhone", "");
								mailDataMap.put("HouseURL",
										baseUrl + "houseSecond.show?actionMethod=houseSecondDetail&houseNo="
												+ houseSecondTmp.getHouseNo() + "&brokerId=");
							}
							final String noticeEmail = houseReduceNotice.getNoticeEmail();
							// 返回邮件名称、邮件内容、邮件ID的集合
							final Map<String, String> emailMap = this.mailSendService.saveMailSendInfo(noticeEmail,
									EmailAbstract.TEMPL_ID_REDUCE_NOTICE, mailDataMap);
							(new Thread() {
								public void run() {
									String errorMsg = "";
									try {
										// 修改邮件发送方式，邮件模板统一改为从数据库取，邮件发送功能统一到EmailServiceImpl类
										// Modified by Sheng Youfu at 2015.03.03
										// //
										// HouseSecondServiceImpl.this.sendEmailService.sendReduceNoticeEmail(mailData,
										// noticeEmail);
										// String mailTempl =
										// HouseSecondServiceImpl.this.mailSendService.getTemplateContentStr(EmailServiceImpl.TEMPL_ID_REDUCE_NOTICE);
										// HouseSecondServiceImpl.this.sendEmailService.sendReduceNoticeEmail(mailTempl,
										// mailDataMap, noticeEmail);
										// 上面写的没有生成邮件后没有保存数据库
										// Modified by He JianBo at 2015_03_05
										HouseSecondServiceImpl.this.sendEmailService.sendHtmlEmail(
												emailMap.get(MailSendService.KEY_MAIL_RESULT_CONTENT),
												emailMap.get(MailSendService.KEY_MAIL_RESULT_NAME), noticeEmail, null);

										String hql = "update HouseReduceNotice h set h.emailDone = 1 where h.erpId = ?";
										Object[] objs = new Object[] { erpId };
										HouseSecondServiceImpl.this.hibernateTemplate.bulkUpdate(hql, objs);
										HouseSecondServiceImpl.this.hibernateTemplate.flush();
									} catch (Exception ex) {
										errorMsg = "房源降价通知邮件发送失败！";
										throw new BusinessRuntimeException("房源降价通知邮件发送失败！"
												+ ExceptionUtil.getStackTraceAsString(ex));
									} finally {
										HouseSecondServiceImpl.this.mailSendService.saveErrorMsg(
												Long.valueOf(emailMap.get(MailSendService.KEY_MAIL_RESULT_MAIL_ID)),
												errorMsg);
									}
								}
							}).start();
						}

						if (houseReduceNotice.getPhoneFlag() == 1 && houseReduceNotice.getSmsDone() == 0) {
							String phoneNumber = houseReduceNotice.getNoticePhone();
							String requestXML = this.createXml(UUID.randomUUID().toString(), phoneNumber,
									houseSecondTmp.getHouseNo());
							String returnedXml = "";

							try {
								returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
								SAXReader e = new SAXReader();
								Document document = e.read(new StringReader(returnedXml));
								List resultList = document.selectNodes("/BasicDataSyncResult/Results/Item/ResultCode");
								if (resultList.size() > 0) {
									Node node = (Node) resultList.get(0);
									if (!"0".equals(node.getText())) {
										LOGGER.error("短信发送失败");
									} else {
										String sqlStringH1 = "update HouseReduceNotice h set h.smsDone = 1 where h.erpId = ?";
										Object[] objs1 = new Object[] { erpId };
										this.hibernateTemplate.bulkUpdate(sqlStringH1, objs1);
										this.hibernateTemplate.flush();
									}
								}
							} catch (DocumentException ex) {
								LOGGER.error(ex.getMessage());
							}
						}

						hql = "update HouseReduceNotice h set h.deleteFlag = 1 where h.erpId = ?";
						Object[] params = new Object[] { erpId };
						this.hibernateTemplate.bulkUpdate(hql, params);
						this.hibernateTemplate.flush();
					}
				}
			}
		}
	}

	/**
	 * 根据邮件模板ID生成邮件并保存到数据库
	 * 
	 * @param houseReduceNotice
	 *            房源降价通知
	 * @param requestURL
	 *            请求URL
	 * @param mailTemplateId
	 *            邮件模板ID
	 * @param title
	 *            邮件
	 * @param newPrice
	 *            房源最新价格
	 * @return
	 * @throws HshbException
	 */
	public Long saveMailSendInfoByTemplate(HouseReduceNotice houseReduceNotice, String requestURL, Long mailTemplateId,
			String title, String newPrice) throws HshbException {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("userName", houseReduceNotice.getHouseNo());
		infoMap.put("url", requestURL);
		infoMap.put("activeKey", Integer.valueOf(houseReduceNotice.getHouseType()));
		infoMap.put("newPrice", newPrice);

		// MailTemplate template = (MailTemplate)
		// this.getObjectById(MailTemplate.class, mailTemplateId);
		// Long mailSendInfoId =
		// mailSendService.saveMailSendInfo(template.getTemplateName(),
		// houseReduceNotice.getNoticeEmail(), mailTemplateId, infoMap);
		// return mailSendInfoId;
		Map<String, String> retMap = mailSendService.saveMailSendInfo(houseReduceNotice.getNoticeEmail(),
				mailTemplateId, infoMap);
		return Long.valueOf(retMap.get(MailSendService.KEY_MAIL_RESULT_MAIL_ID));
	}

	public HouseSecond getHouseSecondByHouseNo(String houseNo) {
		HouseSecond houseSecond = null;
		String resultSql = "from HouseSecond t where t.houseNo = ? and t.deleteFlag = ?";
		Object[] objs = new Object[] { houseNo, Integer.valueOf(0) };
		List houseSecondList = this.hibernateTemplate.find(resultSql, objs);
		if (houseSecondList != null && houseSecondList.size() > 0) {
			houseSecond = (HouseSecond) houseSecondList.get(0);
		}

		return houseSecond;
	}

	public List<HouseSecond> getRecommandedHouseSecond(String price, String area, String shi, String erpId) {
		String[] priceRanges = price.split(",");
		Float priceMin = Float.valueOf(priceRanges[0]);
		Float priceMax = Float.valueOf(priceRanges[1]);
		String[] areaRanges = area.split(",");
		Float areaMin = Float.valueOf(areaRanges[0]);
		Float areaMax = Float.valueOf(areaRanges[1]);
		Integer shiInteger = Integer.valueOf(shi);
		String resultSql = "from HouseSecond t where t.price between " + priceMin + " and " + priceMax
				+ " and t.area between " + areaMin + " and " + areaMax + " and t.shi = " + shiInteger
				+ " and t.erpId <> \'" + erpId + "\' and t.deleteFlag = 0";
		List houseSecondList = this.getQueryWithMaxCount(resultSql, 4);
		return houseSecondList;
	}

	/**
	 * 生成房源变动通知同步
	 * 
	 * @param itemId
	 *            同步数据的ItemId
	 * @param phoneNumber
	 *            接收人的手机号码
	 * @param message
	 *            房源编号
	 * @return
	 */
	private String createXml(String itemId, String phoneNumber, String message) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sbXML = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("<BasicData>")
				.append("<DataHeader>").append("<DataSetID>").append(UUID.randomUUID()).append("</DataSetID>")
				.append("<DataType>SMS</DataType>").append("</DataHeader>").append("<DataBody>").append("<Item>")
				.append("<ItemID>").append(itemId).append("</ItemID>").append("<OperationID>1</OperationID>")
				.append("<SMSID>1</SMSID>").append("<MemberID></MemberID>").append("<SMSType>1</SMSType>")
				.append("<Content>您订阅的内部编号为").append(message).append("的房 源有了新的价格变动，请在我们的官方网站上查看。</Content>")
				.append("<MobilePhone>").append(phoneNumber).append("</MobilePhone>").append("<CreateTime>")
				.append(df.format(date)).append("</CreateTime>").append("</Item>").append("</DataBody>")
				.append("</BasicData>");
		return sbXML.toString();
	}

	/**
	 * 检查房源降价通知
	 * 
	 * @param priceChangeHistoryList
	 */
	public void checkReduceNotice(List<PriceChangeHistory> priceChangeHistoryList) {
		for (int i = 0; i < priceChangeHistoryList.size(); ++i) {
			PriceChangeHistory temp = (PriceChangeHistory) priceChangeHistoryList.get(i);
			Object[] obj = new Object[] { temp.getHouseId(), temp.getHouseType() };
			String sqlString = "from HouseReduceNotice h where h.houseId = ? and h.houseType = ? and  h.deleteFlag = 0";
			List houseReduceNoticeList = this.hibernateTemplate.find(sqlString, obj);
			if (houseReduceNoticeList.size() == 0) {
				String sqlStringH = "update PriceChangeHistory p set p.deleteFlag = 1 where p.historyNo = ?";
				Object[] objs = new Object[] { temp.getHistoryNo() };
				this.hibernateTemplate.bulkUpdate(sqlStringH, objs);
				this.hibernateTemplate.flush();
			}
		}
	}

	/**
	 * 根据上架ID和经纪人ID获取房源
	 * 
	 * @param shelvingId
	 *            上架ID
	 * @param brokerId
	 *            经纪人ID
	 * @param houseType
	 *            房源类型，1：买卖，其他：租赁
	 */
	public List getHouseByShelvIngIdAndBrokerId(String shelvingId, String brokerId, int houseType) {
		String houseEntity = houseType == 1 ? "HouseSecond" : "HouseRent";

		String resultSql = "from " + houseEntity
				+ " t where t.shelvingID = ? and t.deleteFlag = 0 and t.broker.erpId = ?";
		Object[] objs = new Object[] { shelvingId, brokerId };
		List houseList = this.hibernateTemplate.find(resultSql, objs);
		return houseList;
	}

	/**
	 * 判断是否有指定的二手房源
	 * 
	 * @param houseNos
	 *            房源编号
	 */
	public boolean hasThisHouse(String houseNos) {
		String[] split = houseNos.split(",");
		List<String> list = Arrays.asList(split);

		String sqlConditionStr = "";
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			sqlConditionStr = sqlConditionStr + "\'" + it.next() + "\'" + ",";
		}

		sqlConditionStr = sqlConditionStr.substring(0, sqlConditionStr.lastIndexOf(","));
		String resultSql = "select count(t.houseNo) from HouseSecond t where t.houseNo in ( " + sqlConditionStr + ")";
		Query query1 = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(resultSql);
		int num1 = ((Number) query1.iterate().next()).intValue();
		return num1 > 0;
	}

	/**
	 * 查询推送到百度的房源
	 * 
	 * @return
	 */

	public List<HouseSecond> getHousesForBaidu(int count) {
		List<HouseSecond> houseList = new ArrayList<HouseSecond>();
//		List<BaiDuHouseSecond> bdHouseList = new ArrayList<BaiDuHouseSecond>();

//		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BaiDuHouseSecond.class, "b");
//		criteria.add(Restrictions.eq("isSync", false));
//		criteria.add(Restrictions.eq("state", 1));
//		if (count > 0) {
//			criteria.setMaxResults(count);
//			criteria.setFirstResult(0);
//		}
//		bdHouseList = criteria.list();
//		Criteria cr = null;
//		List<HouseSecond> list = null;
//		for (BaiDuHouseSecond bd : bdHouseList) {
//			cr = getSessionFactory().getCurrentSession().createCriteria(HouseSecond.class, "h");
//			cr.add(Restrictions.eq("houseNo", bd.getHouseId()));
//			criteria.add(Restrictions.eq("state",1));
//			
//			list = cr.list();
//			if(list == null || list.size() < 1){
//				//在房源中找不到对应的Id说明该房源已删除
//				bd.setState(3);
//				this.hibernateTemplate.save(bd);
//				continue;
//			}
		//处理不匹配的数据
		modifyHouseData();
		final int counts = count;
	 	@SuppressWarnings("unchecked")
		List<HouseSecond> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
				String hql = " from HouseSecond hs where hs.houseNo in (select houseId from BaiDuHouseSecond bd where bd.isSync = false and bd.state = 1  )  ";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);  
                query.setMaxResults(counts); 
				return query.list();
			}
			
		});
			for (HouseSecond house : list) {
				//先记录.方便于状态存储
				BaiDuHouseSecond bd = new BaiDuHouseSecond();
				bd.setHouseId(house.getHouseNo());
				bd.setTitle(house.getTitle());
				bd.setState(1);
				bd.setIsSync(false);
				
				String[] erpIds = {house.getErpId()};
				List<HouseAppraise> appraiseList = this.findAppraiseListByHouseNo(house.getHouseNo());
				Set<HouseAppraise> appSet = new HashSet<HouseAppraise>(appraiseList);
				house.setAppraises(appSet);
				//获取封页,没有封页图片就跳下一条
				List<HousePic> housePic = this.getHousePicByIdsAndPicType(erpIds, 1, 1, 0);
				if(housePic == null || housePic.size() < 1){
					System.out.println("mei_you_feng_mian_tu_pian  second_house_id:" + bd.getHouseId() );
					bd.setState(4);
//					bd.setIsSync(false);
					house.setBaiDuHouseSecond(bd);
					house.setPictureUrl(null);
					houseList.add(house);
					log.error("mei_you_feng_mian_tu_pian  second_house_id:" + bd.getHouseId() );
//					this.hibernateTemplate.update(bd);
					continue;
				}
				//获取该房源全部图片
				List<HousePic> housePics = this.getHousePicByIdsAndPicType(erpIds,1,2,0);
				List<ERPHousePicture> housePicList = new ArrayList<ERPHousePicture>();
				for(HousePic hp : housePics){
					ERPHousePicture erpH = new ERPHousePicture();
					if(hp.getErpId().equals(housePic.get(0).getErpId())) 
						continue;
					erpH.setPicUrl(hp.getPicUrl());
					housePicList.add(erpH);
				}
				// 发布的时间
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				bd.setPublishTime(ts);
				if(house.getLastModified()!=null){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bd.setLastmod(Timestamp.valueOf(df.format(house.getLastModified())));
				}else
					bd.setLastmod(ts);
//				bd.setIsSync(true);
				house.setBaiDuHouseSecond(bd);
				if(housePics.size() > 0)
					house.setPictureUrl(housePics.get(0).getPicUrl());
				house.setErpHousePicList(housePicList);
				houseList.add(house);
			}
//			this.hibernateTemplate.save(bd);
//		}
		return houseList;
	}


	/**
	 * 查询推送到百度需要删除的房源
	 * 
	 * @param count
	 * @return
	 */
	@Override
	public List<BaiDuHouseSecond> getDelHousesForBaidu(int count) {
		
		this.modifyHouseData();
		List<HouseSecond> houseList = new ArrayList<HouseSecond>();
		List<BaiDuHouseSecond> bdHouseList = new ArrayList<BaiDuHouseSecond>();

		//这里没有简化查询语句，是因为 删除需要获取BaiDuHouseSecond 里的更新时间，还是要进行对BaiDuHouseSecond查询
		
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(BaiDuHouseSecond.class, "b");
		criteria.add(Restrictions.eq("isSync", true));
		criteria.add(Restrictions.eq("state",3));
		if (count > 0) {
			criteria.setMaxResults(count);
			criteria.setFirstResult(0);
		}
		bdHouseList = criteria.list();
//		List<HouseSecond> list = new ArrayList<HouseSecond>();
		for(BaiDuHouseSecond bd : bdHouseList){
//			Criteria cr = getSessionFactory().getCurrentSession().createCriteria(HouseSecond.class, "h");
//			cr.add(Restrictions.eq("houseNo", bd.getHouseId()));
//			list = cr.list();
//			for(HouseSecond hs : list){
////				bd.setIsSync(false);
//				hs.setBaiDuHouseSecond(bd);
//				houseList.add(hs);
//			}
//			this.hibernateTemplate.save(bd);
			//处理XML里的数据
			try{
			new DocumentXML (bd.getHouseId(), LAST_COMMIT_XML_SAVE_PATH + "sale.xml", "delete");
			}catch(Exception ex){
				log.error(ex);
			}
		}
		return bdHouseList;
	}
	
	@Override
	public void saveChangeIsSyncDel(List<BaiDuHouseSecond> list) {
		if(list != null || list.size() > 0){
			for(BaiDuHouseSecond bd : list){
				this.hibernateTemplate.update(bd);
			}
		}
	}
	/**
	 * 保存isSync是否同步状态
	 * @param houseSecondList
	 */
	@Override
	public void saveChangeIsSync(List<HouseSecond> houseSecondList){
//		String resultSql = "from HouseAppraise t where t.houseNo =? and t.broker.erpId =? and t.deleteFlag = ?";
//		Object[] obs = new Object[] { houseNo, brokerId, Integer.valueOf(0) };
		System.out.println("保存houseSecond的状态");
		if(houseSecondList != null || houseSecondList.size() > 0){
			for(HouseSecond hs : houseSecondList){
				BaiDuHouseSecond bd = hs.getBaiDuHouseSecond();
//				System.out.println("最后出现一次的是有问题的:" + bd.getHouseId());
//				this.hibernateTemplate.update(bd);
				String hql = null;
				Object[] obs = null;
				if(bd.getState() != 1){
					hql = "replace into partner_baidu_house_second(house_id, title, is_sync, status ) values (?,?,?,?)";
					obs = new Object[]{bd.getHouseId(), bd.getTitle(), bd.getIsSync(), bd.getState()};
				}else{
//					hql = "replace into BaiDuHouseSecond(houseId, title, lastmod, publishTime, isSync, state, brokerName, telephone, price, brokerErpId) values (?,?,?,?,?,?,?,?,?,?)";
					hql = "replace into partner_baidu_house_second(house_id, title, lastmod, publish_time, is_sync, status, broker_name, telephone, price, broker_erp_id) values (?,?,?,?,?,?,?,?,?,?)";
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
		List<BaiDuHouseSecond> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
//				String hql = " from HouseSecond hs where hs.houseNo in (select houseId from BaiDuHouseSecond bd where bd.isSync = false and bd.state = 1  )  ";
				
//				String hql = " FROM  BaiDuHouseSecond p WHERE p.isSync = 0 and p.state = 1 and  "
//						+ "NOT EXISTS (select h.houseNo FROM HouseSecond h WHERE h.houseNo = p.houseId	)  ";
				String hql = " FROM  BaiDuHouseSecond p WHERE  p.state = 1 and  "
						+ "NOT EXISTS (select h.houseNo FROM HouseSecond h WHERE h.houseNo = p.houseId )  ";
				
				Query query = session.createQuery(hql);
				return query.list();
			}
			
		});
		System.out.println("list.size(): " + list.size());
		if(list.size() > 0){
		for(BaiDuHouseSecond bd : list){
//			bd.setIsSync(false);
			bd.setState(3);
			this.hibernateTemplate.update(bd);
		}
		}
	}
//	/**
//	 * 处理没有对应小区的二手房
//	 */
//	private void getCommunityHouseSecond(){
//		
//			
//			String sql = " select bd from BaiDuHouseSecond bd , HouseSecond hs where bd.houseId and hs.houseNo and "
//				+ "hs.community.erpId in (SELECT hc.erpId FROM BaiDuCommunity pd , Community hc WHERE hc.communityNo = pd.communityId and pd.isSync = 1 and pd.state = 1) ";
//		
//		this.hibernateTemplate.update(sql);
//		
//	}
//	 public List<HouseSecond> getHousesForBaidu(int count){
//	 List<HouseSecond> houseList = new ArrayList<HouseSecond>();

	

	/**
	 * 获取所有二手房源前12个月平均挂牌价
	 * @return
	 */
	public List<Object[]> getHouseAvgUnitPriceTrendLastYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		now.add(Calendar.YEAR, -1); //取上年同日
		String ld = sdf.format(now.getTime());
		
		Criteria cri = this.getSessionFactory().getCurrentSession().createCriteria(HouseSecondPriceTrend.class);
	  cri.setProjection(
	  		Projections.projectionList()
	  		.add(Projections.avg("unitPrice").as("avgPrice"))
	  		.add(Projections.groupProperty("dateAndMonth")));	
		try {
			cri.add(Restrictions.ge("dateAndMonth", sdf.parse(ld)));
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
	 * 给热点链接换上最新的URL地址
	 */
	@Override
	public List<FooterLink> getFooterLinkUrl(List<FooterLink> list, String webUrl) {
		
		if(list.size() > 0){
			String hql = " from Community c ";
		List<Community> cbdList = this.hibernateTemplate.find(hql);
			for(FooterLink fl : list){
				String linkName = (fl.getLinkName()).replace("二手房", "");
//				http://www.hshb.cn:80//houseSecond.show?actionMethod=dimquery&ispage=0&tabIndex=0&type=0&ddhb_one_community.region.erpId=15&ddhb_one_community.cbd.parentCBD.websiteId=17
				String linkUrl = webUrl + "houseSecond.show?actionMethod=dimquery&ispage=0&tabIndex=0&type=0";
				if("".equals(linkName.trim()))
					continue;
				for(Community c : cbdList){
					
//					商圈不为空    
					if(c.getCbd() != null){ 
//						网站商圈不为空
						if(c.getCbd().getParentCBD() != null)
							if(linkName.equals(c.getCbd().getParentCBD().getName().trim())){
//								所在的城区
								if(c.getRegion() != null){
						 			linkUrl += "&ddhb_one_community.region.erpId=" + c.getRegion().getErpId();
								}
								linkUrl += "&ddhb_one_community.cbd.parentCBD.websiteId=" + c.getCbd().getParentCBD().getWebsiteId();
								break;
							}
					}
				}
				fl.setLinkUrl(linkUrl);
			}
		}
		return list;
	}

	@Override
	public List<List<Object[]>> getPriceChart( int houseType) {
//		"2014-05-01", 2545
		List<List<Object[]>> list = null;
		
		String hql = "From PriceChart p where p.houseType = " + houseType;
		List<PriceChart> priceChartList = this.hibernateTemplate.find(hql);
		if(priceChartList.size() > 0 ){
			list = new ArrayList<List<Object[]>>();
			//存放挂牌均价数据
			Object[] medianListing = new Object[2];
			List<Object[]> mList = new ArrayList<Object[]>();
			//存放成交均价数据
			Object[] averageTransactionPrice = new Object[2];
			List<Object[]> aList = new ArrayList<Object[]>();
			//成交数量		
			Object[] volume = new Object[2];
			List<Object[]> vList = new ArrayList<Object[]>();
			for(PriceChart pc : priceChartList){
				medianListing = new Object[2];
				averageTransactionPrice = new Object[2];
				volume = new Object[2];
				medianListing[0] = pc.getCurrentDate();
				medianListing[1] = pc.getMedianListing();
				mList.add(medianListing);
				
				averageTransactionPrice[0] = pc.getCurrentDate();
				averageTransactionPrice[1] = pc.getAverageTransationPrice();
				aList.add(averageTransactionPrice);
				
				volume[0] = pc.getCurrentDate();
				volume[1] = pc.getVolume();
				vList.add(volume);
			}
			list.add(mList);
			list.add(aList);
			list.add(vList);
		}
		return list;
	}	
	/**
	 * 根据城区汇总二手房源数
	 * @return
	 */
	public List getHouseCountByArea(){
		StringBuilder hql = new StringBuilder("select area.erpId, area.countyName, area.location, count(*) ")
		.append(" from HouseSecond h ")
		.append(" inner join Community hc on h.communityId=hc.erpId ")
		.append(" inner join CBD cbd on hc.businessCircleId=cbd.erpId ")
		.append(" inner join Area area on cbd.countyId=area.erpId ")
		.append(" where h.deleteflag<>1 ")
		.append("group by area.erpId, area.countyName, area.location ");
		
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
