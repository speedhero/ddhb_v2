package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class UserCompareHistory extends BaseEntity {
	private static final long serialVersionUID = 5901915056039285597L;
	private String historyId;
	private String secondHandHouse;
	private String rentHouse;
	private String community;
	private Date lastModifiedDate;

	public String getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getSecondHandHouse() {
		return this.secondHandHouse;
	}

	public void setSecondHandHouse(String secondHandHouse) {
		this.secondHandHouse = secondHandHouse;
	}

	public String getRentHouse() {
		return this.rentHouse;
	}

	public void setRentHouse(String rentHouse) {
		this.rentHouse = rentHouse;
	}

	public String getCommunity() {
		return this.community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
