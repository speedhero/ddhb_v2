package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class PriceChangeHistory extends BaseEntity {
	private static final long serialVersionUID = -8777715977205582178L;
	private Integer historyNo;
	private String houseId;
	private Float lastPrice;
	private int deleteFlag;
	private Float newPrice;
	private Integer houseType;
	private Date updateTime;

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getHistoryNo() {
		return this.historyNo;
	}

	public void setHistoryNo(Integer historyNo) {
		this.historyNo = historyNo;
	}

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Float getLastPrice() {
		return this.lastPrice;
	}

	public void setLastPrice(Float lastPrice) {
		this.lastPrice = lastPrice;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Float getNewPrice() {
		return this.newPrice;
	}

	public void setNewPrice(Float newPrice) {
		this.newPrice = newPrice;
	}

	public Integer getHouseType() {
		return this.houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
}
