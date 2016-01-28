package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

/**
 * 
 * 官网首页价格走势图 实体类
 * @author 何剑波
 * @since 2015-06
 * @version 1.0 
 * http://www.hshb.cn
 *
 */
public class PriceChart  extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2481917674948468393L;
	
	private Integer defaultId;				//默认Id
	private double medianListing;			//挂牌均价
	private double averageTransationPrice;	//成交均价
	private double volume;					//成交量
	private String currentDate;				//当前月份
	private int    houseType;				//房源类别 1:二手房,2:租赁,3:小区
	public Integer getDefaultId() {
		return defaultId;
	}
	public void setDefaultId(Integer defaultId) {
		this.defaultId = defaultId;
	}
	public double getMedianListing() {
		return medianListing;
	}
	public void setMedianListing(double medianListing) {
		this.medianListing = medianListing;
	}
	public double getAverageTransationPrice() {
		return averageTransationPrice;
	}
	public void setAverageTransationPrice(double averageTransationPrice) {
		this.averageTransationPrice = averageTransationPrice;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public int getHouseType() {
		return houseType;
	}
	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}
	
}
