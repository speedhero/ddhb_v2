package com.huatek.hbwebsite.common.entity;

import com.google.gson.annotations.Expose;
import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.QuestionStrategyType;
import java.util.Date;

public class QuestionStrategySubtype extends BaseEntity {
	private static final long serialVersionUID = 4887529773415694416L;
	@Expose
	private String subTypeName;
	private QuestionStrategyType parentType;
	private Date createTime;
	private int deleteFlag;
	private long countQuestion = 0L;
	@Expose
	private String erpId;

	public long getCountQuestion() {
		return this.countQuestion;
	}

	public void setCountQuestion(long countQuestion) {
		this.countQuestion = countQuestion;
	}

	public String getSubTypeName() {
		return this.subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public QuestionStrategyType getParentType() {
		return this.parentType;
	}

	public void setParentType(QuestionStrategyType parentType) {
		this.parentType = parentType;
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

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
}
