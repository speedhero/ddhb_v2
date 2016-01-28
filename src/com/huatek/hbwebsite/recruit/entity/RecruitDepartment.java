package com.huatek.hbwebsite.recruit.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.recruit.entity.RecruitPosition;
import java.sql.Timestamp;
import java.util.List;

public class RecruitDepartment extends BaseEntity {
	private static final long serialVersionUID = -3674093333898652767L;
	private String departmentName;
	private String city;
	private String location;
	private String departmentComment;
	private Timestamp createTime;
	private int deleteFlag;
	List<RecruitPosition> positionList;

	public RecruitDepartment() {
	}

	public RecruitDepartment(String departmentName, String city, String location, String departmentComment,
			Timestamp createTime, int deleteFlag) {
		this.departmentName = departmentName;
		this.city = city;
		this.location = location;
		this.departmentComment = departmentComment;
		this.createTime = createTime;
		this.deleteFlag = deleteFlag;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartmentComment() {
		return this.departmentComment;
	}

	public void setDepartmentComment(String departmentComment) {
		this.departmentComment = departmentComment;
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

	public List<RecruitPosition> getPositionList() {
		return this.positionList;
	}

	public void setPositionList(List<RecruitPosition> positionList) {
		this.positionList = positionList;
	}
}
