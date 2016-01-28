/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;
import java.util.Map;

import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.util.HouseRecommendUtil;

/**
 * @author Administrator
 *
 */
public interface HouseSecondhandHouseService {
	
	/**
	 * 根据条件查询房源数据
	 * @param conditionFields	查询参数
	 * @param orderFields	排序字段及策略
	 * @param pageNum		请求的页码
	 * @return
	 */
	List<HouseSecondHandHouse> getHouseList(Map<String, String> conditions, Map<String, String> orderFields, Integer pageNum);
	
	/**
	 *根据条件查询房源数量 
	 * @param conditions	查询参数
	 * @param orderFields	排序字段及策略
	 * @param pageNum		请求的页码
	 * @param pageSize		页面显示房源的数量
	 * @return
	 */
	List<HouseSecondHandHouse> getHouseList(Map<String, String> conditions, Map<String, String> orderFields, Integer pageNum, Integer pageSize);
	/**
	 * 根据房源编号查询房源
	 * @param houseId	房源编号
	 * @return
	 */
	public HouseSecondHandHouse findHouseByHouseId(String houseId);
	
	/**
	 * 根据房源ID和经纪人查询经纪人的房源评价
	 * @param house
	 * @param brokerId
	 * @return
	 */
	public HouseAppraise findHouseAppraiseByBroker(HouseSecondHandHouse house, String brokerId);
	
	/**
	 * 根据小区ID获取小区与医院关联关系
	 * @param communityId	小区ID
	 * @return
	 */
	public List<HouseCommunityHospitalMapping> findCHMapping(String communityId);
	
	
	/**
	 * 根据小区ID获取小区与学校关联关系
	 * @param communityId	小区ID
	 * @return
	 */
	public List<HouseCommunitySchoolMapping> findCSMapping(String communityId);	
	
	/**
	 * 根据小区ID获取小区与车站对应关系
	 * @param communityId	小区ID
	 * @return
	 */
	public List<HouseCommunityStationMapping> findCStaMapping(String communityId);
	
	/**
	 * 根据小区ID获取小区与地铁站对应关系
	 * @param communityId	小区ID
	 * @return
	 */
	public List<HouseCommunitySubwayStationMapping> findCSubMapping(String communityId);

	/**
	 * 根据房源查询推荐的房源* 
	 * @param house			当前房源
	 * @param recommandUtil	推荐房源工具类缓存 
	 * @return
	 */
	public List<HouseSecondHandHouse> findHouseSecondRecommend(HouseSecondHandHouse house, HouseRecommendUtil recommandUtil);
	
	/**
	 * 根据查询条件范围查询房源记录，主要用于查询推荐房源 
	 * @param priceRange	价格区间
	 * @param areaRange		面积区间
	 * @param shi			居室数
	 * @param houseErpId	房源ERPId
	 * @param rows			查询记录数
	 * @return
	 */
	public List<HouseSecondHandHouse> getHouseByConditionRange(String priceRange, String areaRange, String shi, String houseErpId, int rows);
	
	/**
	 * 载入推荐到首页的房源
	 * @param count	要载入的记录数
	 * @return
	 */
	public List<HouseSecondHandHouse> loadRecommandedHouse(Integer rows);
	
	/**
	 * 把查询条件放入list
	 * @param condition
	 * @param url
	 * @param searchField
	 * @return
	 */
	public List<SearchField> getSearchFieldList(Map<String, String> condition, String url, String searchField);
	
	/**
	 * 解析房源中的房源标签
	 * @param tagStr 房源标签字符串，可能是逗号分隔的标签ID字符串，也可能是整型的按位存储的标签组合整型值
	 * @return
	 */
	public List<CommonFlag> parseHouseTag(String tagStr);	
	
	/**
	 * 买卖房源同步XML解析成对象
	 * @param strXml
	 * @return
	 */
	public HouseSecondHandHouse parseHosueSecondHouseXmlToObject(String strXml);
}
