package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class RentType extends BaseEntity {
	private static final long serialVersionUID = -5979384000508059848L;
	private String rentTypeName;
	private int rentValue;
	private int deleteFlag;

	public String getRentTypeName() {
		return this.rentTypeName;
	}

	public void setRentTypeName(String rentTypeName) {
		this.rentTypeName = rentTypeName;
	}

	public int getRentValue() {
		return this.rentValue;
	}

	public void setRentValue(int rentValue) {
		this.rentValue = rentValue;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
