package com.huatek.hbwebsite.recruit.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.recruit.entity.RecruitPosition;
import java.sql.Timestamp;
import java.util.List;

public class RecruitPositionType extends BaseEntity {
	private static final long serialVersionUID = 4172456213941061603L;
	private String typeName;
	private Timestamp createTime;
	private int deleteFlag;
	private List<RecruitPosition> recruitPositions;

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<RecruitPosition> getRecruitPositions() {
		return this.recruitPositions;
	}

	public void setRecruitPositions(List<RecruitPosition> recruitPositions) {
		this.recruitPositions = recruitPositions;
	}
}
