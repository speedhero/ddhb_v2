package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.house.entity.CityForNewHouse;

public class CountyForNewHouse extends BaseEntity {
	private static final long serialVersionUID = -8857784952256033497L;
	private String countyName;
	private CityForNewHouse cityForNewHouse;
	private int deleteFlag;

	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public CityForNewHouse getCityForNewHouse() {
		return this.cityForNewHouse;
	}

	public void setCityForNewHouse(CityForNewHouse cityForNewHouse) {
		this.cityForNewHouse = cityForNewHouse;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
