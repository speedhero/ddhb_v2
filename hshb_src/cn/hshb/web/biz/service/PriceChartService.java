/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author ShengYoufu
 *
 */
public interface PriceChartService {

	/**
	 * 获取首页房源价格走势图数据
	 * @param houseType
	 * @return
	 */
	public List<List<Object[]>> getPriceChart(EnumHouseType houseType);
}
