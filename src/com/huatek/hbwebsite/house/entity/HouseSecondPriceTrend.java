package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class HouseSecondPriceTrend extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String houseId;
	private Float price;
	private Float unitPrice;
	private Date dateAndMonth;
	private Integer deleteFlag;

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getDateAndMonth() {
		return this.dateAndMonth;
	}

	public void setDateAndMonth(Date dateAndMonth) {
		this.dateAndMonth = dateAndMonth;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
