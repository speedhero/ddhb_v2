package com.huatek.hbwebsite.company.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class CompanyCustomerServiceType extends BaseEntity {
	private static final long serialVersionUID = -2392790354528199833L;
	private String typeName;
	private Date createTime;
	private int deleteFlag;

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
