package com.huatek.hbwebsite.member.entity;

import java.util.Date;

public class CompareItem {
	private Date lastModifiedDate;
	private String houseSecond;
	private String houseRent;
	private String community;

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getHouseSecond() {
		return this.houseSecond;
	}

	public void setHouseSecond(String houseSecond) {
		this.houseSecond = houseSecond;
	}

	public String getHouseRent() {
		return this.houseRent;
	}

	public void setHouseRent(String houseRent) {
		this.houseRent = houseRent;
	}

	public String getCommunity() {
		return this.community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public void cleanHouseSecondCompareItem() {
		this.setHouseRent((String) null);
	}

	public void cleanHouseRentCompareItem() {
		this.setHouseRent((String) null);
	}

	public void cleanCommunityItem() {
		this.setCommunity((String) null);
	}
}
