package com.huatek.hbwebsite.company.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class CompanyIntroduce extends BaseEntity {
	private static final long serialVersionUID = -2280062013444362503L;
	private String introduceContent;
	private Date createTime;
	private Integer deleteFlag;

	public String getIntroduceContent() {
		return this.introduceContent;
	}

	public void setIntroduceContent(String introduceContent) {
		this.introduceContent = introduceContent;
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
