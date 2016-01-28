package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class StudyZoneType extends BaseEntity {
	private static final long serialVersionUID = 1503355718369538307L;
	private String stutName;
	private int deleteFlag;
	private String erpId;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getStutName() {
		return this.stutName;
	}

	public void setStutName(String stutName) {
		this.stutName = stutName;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
