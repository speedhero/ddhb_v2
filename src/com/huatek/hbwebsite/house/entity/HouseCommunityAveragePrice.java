package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class HouseCommunityAveragePrice extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String communityNo;
	private Date calculateDate;
	private Float priceAnnounced;
	private Float priceDealed;
	private Float rentAveragePrice;
	private Integer deleteFlag;

	public Float getRentAveragePrice() {
		return this.rentAveragePrice;
	}

	public void setRentAveragePrice(Float rentAveragePrice) {
		this.rentAveragePrice = rentAveragePrice;
	}

	public String getCommunityNo() {
		return this.communityNo;
	}

	public void setCommunityNo(String communityNo) {
		this.communityNo = communityNo;
	}

	public Date getCalculateDate() {
		return this.calculateDate;
	}

	public void setCalculateDate(Date calculateDate) {
		this.calculateDate = calculateDate;
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
