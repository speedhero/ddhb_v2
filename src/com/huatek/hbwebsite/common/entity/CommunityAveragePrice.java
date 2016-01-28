package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Community;
import java.util.Date;

public class CommunityAveragePrice extends BaseEntity {
	private static final long serialVersionUID = -6326708025530611784L;
	private Date calculateDate;
	private Community community;
	private Float priceAnnounced;
	private Float priceDealed;
	private Integer deleteFlag;
	private String erpId;
	private Float rentPrice;

	public Float getRentPrice() {
		return this.rentPrice;
	}

	public void setRentPrice(Float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public Date getCalculateDate() {
		return this.calculateDate;
	}

	public void setCalculateDate(Date calculateDate) {
		this.calculateDate = calculateDate;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Float getPriceAnnounced() {
		return this.priceAnnounced;
	}

	public void setPriceAnnounced(Float priceAnnounced) {
		this.priceAnnounced = priceAnnounced;
	}

	public Float getPriceDealed() {
		return this.priceDealed;
	}

	public void setPriceDealed(Float priceDealed) {
		this.priceDealed = priceDealed;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
