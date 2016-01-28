package com.huatek.hbwebsite.house.entity;

import java.sql.Timestamp;

import com.huatek.base.entity.BaseEntity;

public class BaiDuHouseSecond  extends BaseEntity{

private static final long serialVersionUID = 1L;
	
	private String houseId;				// 房源编号
	private	String title;				// 房源标题	
	private Timestamp lastmod;			// 最后更新时间
	private Timestamp publishTime;		// 发布时间
	private Boolean isSync;				// 是否已同步
	private Integer state;				//房源状态：1：新增	2：修改	3：删除	4：房源有问题
	private HouseSecond houseSecond;	//关联关系
	private String brokerName;
	private String telephone;
	private Integer price;
	private String brokerErpId;
	
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getLastmod() {
		return lastmod;
	}
	public void setLastmod(Timestamp lastmod) {
		this.lastmod = lastmod;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public Boolean getIsSync() {
		return isSync;
	}
	public void setIsSync(Boolean isSync) {
		this.isSync = isSync;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public HouseSecond getHouseSecond() {
		return houseSecond;
	}
	public void setHouseSecond(HouseSecond houseSecond) {
		this.houseSecond = houseSecond;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getBrokerErpId() {
		return brokerErpId;
	}
	public void setBrokerErpId(String brokerErpId) {
		this.brokerErpId = brokerErpId;
	}
	
}
