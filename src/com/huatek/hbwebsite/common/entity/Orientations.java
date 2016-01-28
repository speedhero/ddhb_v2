package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class Orientations extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String orientationName;
	private Integer deleteFlag;

	public String getOrientationName() {
		return this.orientationName;
	}

	public void setOrientationName(String orientationName) {
		this.orientationName = orientationName;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
