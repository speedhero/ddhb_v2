package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

public class IntegralTable extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private PlatMemberInfo platMemberInfo;
	private int integral;
	private int changedIntegral;
	private int changedCount;
	private int receivedCount;
	private int deleteFlag;

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

	public int getChangedIntegral() {
		return this.changedIntegral;
	}

	public void setChangedIntegral(int changedIntegral) {
		this.changedIntegral = changedIntegral;
	}

	public int getChangedCount() {
		return this.changedCount;
	}

	public void setChangedCount(int changedCount) {
		this.changedCount = changedCount;
	}

	public int getReceivedCount() {
		return this.receivedCount;
	}

	public void setReceivedCount(int receivedCount) {
		this.receivedCount = receivedCount;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
