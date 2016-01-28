/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuy;

/**
 * @author ShengYoufu
 *
 */
public interface HouseNewhouseGroupbuyService {
	/**
	 * 查询指定日期后(含指定日期)的团购人数
	 */
	public List<HouseNewhouseGroupbuy> getNewHouseGroupBuyAfterDate(Date endDate);
	

	/**
	 * 查询团购人数
	 */
	public Integer getCountGroupBuy();
	
	/**
	 * 根据楼盘ID统计团购参与人数
	 */
	public List<Object> getHouseEntrants(Integer[] houseIds);

	/**
	 * 根据楼盘ID统计团购参与人数
	 */
	public Integer getHouseNewEntrantsById(Integer houseId);

	/**
	 * 根据客户电话和新楼盘编号查询团购信息
	 * @param telephone	客户电话
	 * @param houseId	新楼盘ID
	 * @return
	 */
	public Boolean isExistsGroupbuy(String telephone, Integer houseId);
	
	/**
	 * 根据楼盘ID统计团购参与人数
	 * @param houseIds
	 * @return
	 */
	public List<Map<String, Integer>> getHouseGroupbuyCount(List<Integer> houseIds);
	
	/**
	 * 根据新楼盘ID查询团购记录
	 * @param houseIds
	 * @return
	 */
	public List<HouseNewhouseGroupbuy> getHouseGroupbuys(List<Integer> houseIds);

	/**
	 * 保存新房团购记录
	 * @param record
	 * @return
	 */
	public Boolean save(HouseNewhouseGroupbuy record);
}
