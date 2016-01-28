package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class Type extends BaseEntity {
	private static final long serialVersionUID = -7220325297416441015L;
	private String houseTypeName;
	private Integer deleteFlag;
	private String erpId;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getHouseTypeName() {
		return this.houseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
