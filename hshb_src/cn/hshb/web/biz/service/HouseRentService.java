package cn.hshb.web.biz.service;

import java.util.List;
import java.util.Map;

import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.CommonUsage;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.util.HouseRecommendUtil;

public interface HouseRentService {
	
	/**
	 * 根据条件查询房源数据
	 * @param conditions	查询参数
	 * @param orderFields	排序字段及策略
	 * @param pageNum		请求的页码
	 * @return
	 */
	List<HouseRentHouseExt> getHouseList(Map<String, String> conditions, Map<String, String> orderFields, Integer pageNum);
	
	/**
	 *根据条件查询房源数据 
	 * @param conditions	查询参数
	 * @param orderFields	排序字段及策略
	 * @param PageNum		请求的页面
	 * @param pageSize		每页显示的数量
	 * @return
	 */
	List<HouseRentHouseExt> getHouseList(Map<String, String> conditions, Map<String, String> orderFields, Integer PageNum, Integer pageSize);
	
	/**
	 * @param houseId	房源编号
	 * @return
	 */
	public HouseRentHouse loadHouseByHouseId(String houseId);
	
	/**
	 * 根据房源编号搜索房源
	 * @param houseNo
	 * @return
	 */
	public HouseRentHouse findHouseByHouseId(String houseNo);
	/**
	 * 根据房源Id和经纪人查询经纪人的房源评价
	 * @param house		
	 * @param brokerId
	 * @return
	 */
	public HouseAppraise findHouseAppraiseByBroker(HouseRentHouse house, String brokerId);

	/**
	 * 把查询条件放入list
	 * @param condition
	 * @param url
	 * @param searchField
	 * @return
	 */
	public List<SearchField> getSearchFieldList(Map<String, String> condition, String url, String searchField);
	/**
	 * 载入推荐到首页的房源
	 * @param count	要载入的记录数
	 * @return
	 */
	public List<HouseRentHouseExt> loadRecommandedHouse(Integer rows);
	
	/**
	 * 根据房源查询推荐的房源
	 * @param house			当前房源
	 * @param recommandUtil	推荐房源工具类缓存
	 * @return
	 */
	public List<HouseRentHouseExt> findHouseRentRecommend(HouseRentHouse house, HouseRecommendUtil recommandUtil);
	
	/**
	 * 买卖房源同步XML解析成对象
	 * @param strXml
	 * @return
	 */
	public HouseRentHouse parseHouseRentHouseXmlToObject(String strXml);
	
	/**
	 * 解析房源中的房源标签
	 * @param tagStr 房源标签字符串，可能是逗号分隔的出租屋内设施ID字符串，也可能是整型的按位存储的出租屋内设施组合整型值
	 * @return
	 */
	public List<CommonLiveFacility> parseHouseFurnitures(String furnitureStr);	
}
