package com.huatek.hbwebsite.news.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class NewsNotice extends BaseEntity {
	private static final long serialVersionUID = -8998385330172682291L;
	private String emailAddress;
	private Date createTime;
	private Integer deleteFlag;
	private Integer emailFlag;
	private String verifyCode;

	public Integer getEmailFlag() {
		return this.emailFlag;
	}

	public void setEmailFlag(Integer emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
