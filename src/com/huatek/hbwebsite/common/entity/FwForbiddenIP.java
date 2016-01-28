package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class FwForbiddenIP extends BaseEntity {
	private static final long serialVersionUID = 6518766655568090576L;
	private String ipAddress;
	private Date createTime;
	private Integer deleteFlag;

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
