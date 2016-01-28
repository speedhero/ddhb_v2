package com.huatek.hbwebsite.rent.service;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.CommunityHospitalMapping;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.house.entity.BaiDuHouseRent;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseRentPriceTrend;
import com.huatek.hbwebsite.service.BaseServiceTo;

import java.util.List;
import java.util.Map;

public interface RentService extends BaseServiceTo {
	CutPageBean getRentInfoPageBean(CutPageBean var1, List<CommonBean> var2, String var3, String var4);

	List<Tag> getTagList();

	List<Furniture> getFurList();

	List<HouseAppraise> findAppraiseListByHouseNo(String var1);

	HouseRent findHouseSecondByHouseId(String var1);

	List<HouseRent> findRentHouseListByHouseNos(String var1);

	float[] findAverageValueForPriceAndArea(List<HouseRent> var1);

	List<HouseRent> getHouseRentBySQL(String var1, HouseRent var2);

	List<CommunityHospitalMapping> findCHMapping(String var1);

	List<CommunitySchoolMapping> findCSMapping(String var1);

	List<CommunityStationMapping> findCStaMapping(String var1);

	List<CommunitySubwayStationMapping> findCSubMapping(String var1);

	List<HouseRent> loadRecommandedHouse(int var1);

	List<HousePic> getAllHousePicList(String var1);

	List<HousePic> getHeadHouseList(String var1);

	CutPageBean getSearchFiledList(CutPageBean var1, Map var2, Map<String, List> var3, Map<String, String> var4,
			String var5, String var6);

	List<HousePic> getHousePicByIdAndPicType(String var1, int var2, int var3, int var4);

	List<HouseRentPriceTrend> getRentHouseTrendForSixMonth(String var1);

	float getRentHouseLastMonthPrice(String var1);

	void updateBrowsed(String var1, int var2);

	HouseRentPriceTrend getHousePriceLastMonth(String var1);

	HouseRentPriceTrend getHousePriceLastYear(String var1);

	HouseRentPriceTrend getHousePriceThisMonth(String var1);

	CutPageBean searchRentHouse(String var1, CutPageBean var2, List<CommonBean> var3);

	Furniture getFurListByName(String var1);

	Tag getTagListByName(String var1);

	Broker getBrokerById(String var1);

	Decorations getDecorationsById(String var1);

	Usage getUsageById(String var1);

	HouseRent getHouseRentByHouseNo(String var1);

	List<HouseRent> getRecommandedHouseRent(String var1, String var2, String var3, String var4);

	boolean hasThisHouse(String var1);

	/**
	 * 随机获取热门小区
	 * @param count 数量
	 * @return
	 */
	List<Community> getRandomHotCommunityForRent(Integer count);

	/**
	 * 获取出租房源前12个月平均租价
	 * @return
	 */
	List<Object[]> getHouseAvgRentPriceTrendLastYear();

	
	/**
	 * 查询推送到百度的房源
	 * 
	 * @return
	 */
	public List<HouseRent> getHousesForBaidu(int count);
	
	/**
	 * 查询推送百度需要删除的房源
	 * @param count
	 * @return
	 */
	public  List<BaiDuHouseRent> getDelHousesForBaidu(int count);
	
	/**
	 * 保存isSync是否同步状态
	 * @param houseRent
	 */
	public void saveChangeIsSync(List<HouseRent> houseRent);
	
	public void saveChangeIsSyncDel(List<BaiDuHouseRent> list);

	/**
	 * 根据城区统计出租房源数
	 * @return
	 */
	public List getHouseCountByArea();
}
