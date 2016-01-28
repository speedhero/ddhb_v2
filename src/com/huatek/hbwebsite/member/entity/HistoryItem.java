package com.huatek.hbwebsite.member.entity;

import java.util.Date;

public class HistoryItem {
	private Date lastBrowsedDate;
	private String houseSecond;
	private String houseRent;
	private String community;

	public Date getLastBrowsedDate() {
		return this.lastBrowsedDate;
	}

	public void setLastBrowsedDate(Date lastBrowsedDate) {
		this.lastBrowsedDate = lastBrowsedDate;
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
