package com.huatek.hbwebsite.community.service;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.CommunityAveragePrice;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.house.entity.HouseCommunityAveragePrice;
import com.huatek.hbwebsite.service.BaseServiceTo;

import java.util.List;
import java.util.Map;

public interface CommunityService extends BaseServiceTo {
	CutPageBean getPageBean(CutPageBean var1, List<CommonBean> var2);

	List<HouseCommunityAveragePrice> getPriceTrendForSixMonth(String var1);

	List<Community> findCommunityListByCommunityNos(String var1);

	CutPageBean getSearchFiledList(CutPageBean var1, Map var2, Map<String, List> var3, String var4, String var5);

	String getCommunityJsonStringByName(String var1);

	CutPageBean getSecondByCommuId(CutPageBean var1, List<CommonBean> var2, String var3);

	CutPageBean getRentByCommuId(CutPageBean var1, List<CommonBean> var2, String var3);

	CutPageBean getSearchHouseList(CutPageBean var1, Map var2, Map<String, List> var3, Map<String, String> var4,
			String var5, String var6, String var7, String var8);

	CutPageBean getBrokerList(CutPageBean var1, String var2);

	List<HousePic> getCommunityPicByIdsAndPicType(String[] var1, int var2, int var3, int var4);

	List<Object> findContPicPerCommunity(String[] var1);

	List<CommunityStationMapping> getCommunityStationsById(String communityErpId);

	CommunityAveragePrice getCommunityAveragePriceLastMonth(String communityErpId);

	CommunityAveragePrice getCommunityAveragePriceLastYear(String communityErpId);

	CommunityAveragePrice getCommunityAveragePriceThisMonth(String communityErpId);

	CutPageBean searchCommunity(String key, CutPageBean pageBean, List<CommonBean> commonList);

	List<Community> getRecommandedCommunity(String cbdErpId);

	void calculateCurrentMonthPrice();

	void calculateLastYearSHAndRH();

	Community getCommunityListByErpId(String communityErpId);

	List<CommunitySubwayStationMapping> findCSubMapping(String communityErpId);

	boolean hasThisCommunity(String communityNo);
	
	/**
	 * 根据给定的小区对象载入该小区最近的门店列表，根据ERP同步过来的门店ID列表载入
	 * @param community 小区对象
	 */
	public void loadNearestStore(Community community);
	
	/**
	 * 查询推送到百度的房源
	 * 
	 * @return
	 */
	public List<Community> getHousesForBaidu(int count);
	
	/**
	 * 保存isSync是否同步状态
	 * @param CommunityList
	 */
	public void saveChangeIsSync(List<Community> CommunityList);
	
	/**
	 * 根据小区统计出租房源数
	 * @return
	 */
	public List getHouseCountByComunity();
}
