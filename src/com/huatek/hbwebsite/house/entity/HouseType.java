package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;

public class HouseType extends BaseEntity {
	private static final long serialVersionUID = 2182082207013038416L;
	private Integer htCount;
	private String htName;
	private Integer deleteFlag;

	public Integer getHtCount() {
		return this.htCount;
	}

	public void setHtCount(Integer htCount) {
		this.htCount = htCount;
	}

	public String getHtName() {
		return this.htName;
	}

	public void setHtName(String htName) {
		this.htName = htName;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
