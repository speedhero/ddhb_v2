package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.util.Date;

public class IntegralHistory extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private PlatMemberInfo platMemberInfo;
	private int integral;
	private int getflag;
	private Date gettedTime;
	private String comment;
	private int deleteFlag;
	private PlatMemberInfo user;

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public int getIntegral() {
		return this.integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getGetflag() {
		return this.getflag;
	}

	public void setGetflag(int getflag) {
		this.getflag = getflag;
	}

	public Date getGettedTime() {
		return this.gettedTime;
	}

	public void setGettedTime(Date gettedTime) {
		this.gettedTime = gettedTime;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public PlatMemberInfo getUser() {
		return this.user;
	}

	public void setUser(PlatMemberInfo user) {
		this.user = user;
	}
}
