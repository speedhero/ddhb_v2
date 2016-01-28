package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;

public class CityForNewHouse extends BaseEntity {
	private static final long serialVersionUID = -8857784952256033497L;
	private String cityName;
	private int deleteFlag;

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
