/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;
import java.util.Map;

import cn.hshb.web.biz.mybatis.model.HouseAppraise;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityAveragePrice;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExt;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMapping;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.InputSearch;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.util.HouseRecommendUtil;

/**
 * @author Administrator
 *
 */
public interface HouseCommunityService {
	
	/**
	 * 给HouseCommunity注入依赖的属性，比如商圈和小区
	 * @param community
	 */
	void populateCommunity(HouseCommunity community);
	
	/**
	 * 查询小区最近门店列表
	 * @param community
	 */
	public void loadNearestStores(HouseCommunity community);
	
	/**
	 * 根据条件查询小区数据
	 * @param conditions	查询参数
	 * @param orderFields	排序字段及策略
	 * @param pageNum		请求的页码
	 * @return
	 */
	List<HouseCommunityExt> getCommunityList(Map<String, String> conditions, Map<String, String> orderFields, Integer pageNum);
	
	/**
	 * 根据小区编号查询小区信息 
	 * @param houseId
	 * @return
	 */
	public HouseCommunity loadCommunityByHouseId(String houseNo);

	/**
	 * 根据小区编号搜索小区
	 * @param communityId
	 * @return
	 */
	public HouseCommunity findCommunityByCommunityId(String communityId);
	/**
	 * 把查询条件放在list里面
	 * @param condition
	 * @param url
	 * @param searchField
	 * @return
	 */
	public List<SearchField> getSearchFieldList(Map<String, String> condition, String url, String searchField);
	
	/**
	 * 根据小区Id获取小区图片集
	 * @param CommunityId	小区Id
	 * @return
	 */
	public List<HousePicture> getHousePictureByCommunityId(String CommunityId);
	
	/**
	 * 根据小区Id获取小区近6个月的平均价格
	 * @param communityId
	 * @return
	 */
	public List<HouseCommunityAveragePrice> getPriceTrendSixMonth(String communityId);
	
	
	/**
	 * 根据小区Id获取小区与医院关联关系
	 * @param communityId 小区Id
	 * @return
	 */
	public List<HouseCommunityHospitalMapping> findCHMapping(String communityId);
	
	/**
	 * 根据小区Id获取小区与学校关联关系
	 * @param communityId
	 * @return
	 */
	public List<HouseCommunitySchoolMapping> findCSMapping(String communityId);
	
	/**
	 * 根据小区Id获取小区与车站对应关系
	 * @param communityId
	 * @return
	 */
	public List<HouseCommunityStationMapping> findCStaMapping(String communityId);
	
	/**
	 * 根据小区Id获取小区与地铁站对应关系
	 * @param comunityId
	 * @return
	 */
	public List<HouseCommunitySubwayStationMapping> findCSubMapping(String comunityId);
	
	
	/**
	 * 给指定房源查询小区信息
	 * @param houseList
	 */
	public void loadCommunityForHouse(List<? extends Object> houseList);
	
	/**
	 * 根据小区名称,检索小区
	 * @param searchField
	 * @return
	 */
	public List<HouseCommunity> getHouseCommunityByCommunityName(String searchField);
	
	/**
	 * 返回所有所有小区的数据
	 * @return 
	 */
	public List<HouseCommunity> getAllHouseCommunitys();
}
