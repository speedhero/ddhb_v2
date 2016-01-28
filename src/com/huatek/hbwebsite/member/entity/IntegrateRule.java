package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;

public class IntegrateRule extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String ruleName;
	private int ruleValue;
	private String comment;
	private int deleteFlag;

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public int getRuleValue() {
		return this.ruleValue;
	}

	public void setRuleValue(int ruleValue) {
		this.ruleValue = ruleValue;
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
}
