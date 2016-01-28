package com.huatek.hbwebsite.house.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.house.entity.HouseNew;
import com.huatek.hbwebsite.house.entity.HouseNewGroupbuy;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HouseNewService extends BaseService {
	
	/**
	 * 查询分页记录
	 * @param cutPageBean
	 * @param commonList
	 * @return
	 */
	CutPageBean getPageBean(CutPageBean cutPageBean, List<CommonBean> commonList);

	/**
	 * 查询新楼盘列表
	 * @param count 指定返回的记录数
	 * @return
	 */
	List<HouseNew> loadHouseNewListWithCount(int count);

	/**
	 * 查询新楼盘数据
	 * @param pageBean 分页信息
	 * @param oneMap	 第一部分参数
	 * @param twoMap	第二部分参数
	 * @param orderStr	排序规则
	 * @param sortfield	排序字段
	 * @return
	 * @throws ParseException
	 */
	CutPageBean getSearchFiledList(CutPageBean pageBean, Map<String, String> oneMap, Map<String, List> twoMap, String orderStr, String sortfield)
			throws ParseException;

	/**
	 * 查询指定日期后（含指定日期）的团购人数
	 * @param expirationDate
	 * @return
	 */
	List<HouseNewGroupbuy> getNewHouseGroupBuy(Date expirationDate);

	/**
	 * 查询团购人数
	 * @return
	 */
	Long getCountGroupBuy();

	/**
	 * 查询指定日期之后（含指定日期）的新楼盘数
	 * @param oneMap
	 * @param expirationDate
	 * @return
	 */
	Long getCountHouseNew(Map<String, String> oneMap, Date expirationDate);

	/**
	 * 查询指定日期之前的新楼盘数
	 * @param oneMap
	 * @param expirationDate
	 * @return
	 */
	Long getCountHouseNewEnd(Map<String, String> oneMap, Date expirationDate);

	/**
	 * 根据楼盘ID查询楼盘
	 * @param houseIds
	 * @return
	 */
	List<Object> getHouseEntrants(long[] houseIds);

	/**
	 * 根据楼盘ID统计团购参与人数
	 * @param houseId
	 * @return
	 */
//	List<Object> getHouseNewEntrantsById(long houseId);
	Long getHouseNewEntrantsById(long houseId);

	/**
	 * 根据联系电话和楼盘ID查询楼盘
	 * @param telephone
	 * @param houseId
	 * @return
	 */
	int findHouseNewByItems(String telephone, long houseId);
	

}
