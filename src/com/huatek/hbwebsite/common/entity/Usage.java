package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class Usage extends BaseEntity {
	private static final long serialVersionUID = -7301921152940521030L;
	private String usageName;
	private Integer module;
	private Integer deleteFlag;
	private String erpId;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getUsageName() {
		return this.usageName;
	}

	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}

	public Integer getModule() {
		return this.module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
