package com.huatek.hbwebsite.house.service;

import cn.hshb.web.common.exception.HshbException;

import com.huatek.ddhb.manage.footerManage.entity.FooterLink;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CommunityHospitalMapping;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.HouseRate;
import com.huatek.hbwebsite.common.entity.PriceChangeHistory;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.house.entity.BaiDuHouseSecond;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.entity.HouseSecondPriceTrend;
import com.huatek.hbwebsite.member.entity.MemberBespeak;
import com.huatek.hbwebsite.service.BaseServiceTo;

import java.util.List;
import java.util.Map;

public interface HouseSecondService extends BaseServiceTo {
	CutPageBean getPageBean(CutPageBean var1, List<CommonBean> var2, String var3);

	List<Area> getAreaList();

	List<Tag> getTagList();

	List<HouseAppraise> findAppraiseListByHouseNo(String var1);

	HouseSecond findHouseSecondByHouseId(String var1);

	HouseAppraise findAppraiseByBrokerAndHouseNo(String var1, String var2);

	MemberBespeak findAppraiseByBrokerAndMember(Long var1, String var2);

	List<HouseSecond> findSecondHouseListByHouseNos(String var1);

	float[] findAverageValueForPriceAndArea(List<HouseSecond> houseSecondList);

	List<HouseSecond> getHouseSecondBySQL(String sql, HouseSecond houseSecond);

	/**
	 * 根据房源类型查询房源评价
	 * @param houseType
	 * @return
	 */
	List<HouseAppraise> getHouseAppraisesByHouseType(Integer houseType);
	
	/**
	 * 根据房源编号查询房源评价信息
	 * @param houseNoList	房源编号列表
	 * @return
	 */
	public List<HouseAppraise> getHouseAppraisesByHouseNo(List<String> houseNoList); 
	
	List<HouseSecondPriceTrend> getSecondHouseTrendForSixMonth(String var1);

	float getSecondHouseLastMonthPrice(String var1);

	float getSecondHouseLastMonthUnitPrice(String var1);

	List<CommunityHospitalMapping> findCHMapping(String var1);

	List<CommunitySchoolMapping> findCSMapping(String var1);

	float getSecondHouseCommunityThisMonthPrice(String var1);

	float getSecondHouseCommunityLastMonthPrice(String var1);

	List<CommunityStationMapping> findCStaMapping(String var1);

	List<CommunitySubwayStationMapping> findCSubMapping(String var1);

	List<HouseSecond> getListByBetween(Float var1, Float var2);

	CutPageBean getSearchFiledList(CutPageBean pageBean, Map<String, Object> oneMap, Map<String, List> twoMap,
			Map<String, String> tagsMap, String orderStr, String sortfield);

	List<HouseSecond> loadRecommandedHouse(int var1);

	List<HousePic> getAllHousePicList(String var1);

	List<HousePic> getHeadHouseList(String var1);

	List<HousePic> getHousePicByIdsAndPicType(String[] houseIds, int houseType, int layoutFlag, int count);

	List<HouseRate> getRate();

	void updateBrowsed(String var1, int var2);

	List<HousePic> finSecondHousePicListByHouseId(String[] var1, int var2);

	List<MemberBespeak> findAppraiseByMember(Long var1);

	HouseSecondPriceTrend getHousePriceLastMonth(String var1);

	HouseSecondPriceTrend getHousePriceLastYear(String var1);

	HouseSecondPriceTrend getHousePriceThisMonth(String var1);

	CutPageBean searchHouseSecond(String var1, CutPageBean var2, List<CommonBean> var3);

	void addCurrentMonthPrice();

	List<PriceChangeHistory> getPriceChangeHistory();

	void doReduceNotice(List<PriceChangeHistory> priceChangeHistoryList) throws HshbException;

	void checkReduceNotice(List<PriceChangeHistory> priceChangeHistoryList);

	HouseSecond getHouseSecondByHouseNo(String houseNo);

	List<HouseSecond> getRecommandedHouseSecond(String price, String area, String shi, String erpId);

	long findHouseSecondCountByBrokerId(String var1);

	long findHouseRentCountByBrokerId(String var1);

	List getHouseByShelvIngIdAndBrokerId(String shelvingId, String brokerId, int houseType);

	boolean hasThisHouse(String var1);

	/**
	 * 查询推送到百度的房源
	 * 
	 * @return
	 */
	public List<HouseSecond> getHousesForBaidu(int count);

	
	/**
	 * 查询推送到百度需要删除的房源
	 * @param count
	 * @return
	 */
	public List<BaiDuHouseSecond> getDelHousesForBaidu(int count);
	
	/**
	 * 保存isSync是否同步状态
	 * @param houseSecondList
	 */
	public void saveChangeIsSync(List<HouseSecond> houseSecondList);
	
	/**
	 * 保存isSync是否同步状态
	 */
	public void saveChangeIsSyncDel(List<BaiDuHouseSecond> list);

	/**
	 * 查询所有二手房源前12个月平均挂牌价格
	 * @return
	 */
	public List<Object[]> getHouseAvgUnitPriceTrendLastYear();
	
	/**
	 * 给热点链接附上 最新链接
	 * @return
	 */
	public List<FooterLink> getFooterLinkUrl(List<FooterLink> list, String webUrl);
	
	/**
	 * 查询首页价格走势图所需的数据
	 * 返回值顺序为0：挂牌均价，1:成交均价，2:成交量
	 * @param houseType :  1:二手房,2:租赁,3:小区
	 * @return
	 */
	public List<List<Object[]>> getPriceChart(int houseType);

	/**
	 * 根据城区统计二手房源数
	 * @return
	 */
	public List getHouseCountByArea();
	
	/**
	 * 解析房源中的房源标签
	 * @param tagStr 房源标签字符串，可能是逗号分隔的标签ID字符串，也可能是整型的按位存储的标签组合整型值
	 * @return
	 */
	public List<Tag> parseHouseTag(String tagStr);	
}
