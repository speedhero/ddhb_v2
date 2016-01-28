package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class Decorations extends BaseEntity {
	private static final long serialVersionUID = -7012065281045584104L;
	private String decorationName;
	private Integer deleteFlag;
	private String erpId;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getDecorationName() {
		return this.decorationName;
	}

	public void setDecorationName(String decorationName) {
		this.decorationName = decorationName;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
