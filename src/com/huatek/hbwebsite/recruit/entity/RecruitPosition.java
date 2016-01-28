package com.huatek.hbwebsite.recruit.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.recruit.entity.RecruitDepartment;
import com.huatek.hbwebsite.recruit.entity.RecruitPositionType;
import java.sql.Date;
import java.sql.Timestamp;

public class RecruitPosition extends BaseEntity {
	private static final long serialVersionUID = -9204083702045503890L;
	public static final int IS_PUBLISHED = 1;
	public static final int NOT_PUBLISHED = 0;
	public static final int JOB_TYPE = 0;
	public static final int JOB = 1;
	private String positionName;
	private int entityFlag;
	private String workPlace;
	private RecruitPositionType positionType;
	private RecruitDepartment department;
	private int needed;
	private String description;
	private String requirement;
	private String otherinformation;
	private Timestamp createTime;
	private Date publishedTime;
	private int isPublished;
	private int publisher;
	private int emergencyFlag;
	private int deleteFlag;

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getEntityFlag() {
		return this.entityFlag;
	}

	public void setEntityFlag(int entityFlag) {
		this.entityFlag = entityFlag;
	}

	public int getNeeded() {
		return this.needed;
	}

	public void setNeeded(int needed) {
		this.needed = needed;
	}

	public String getWorkPlace() {
		return this.workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return this.requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getOtherinformation() {
		return this.otherinformation;
	}

	public void setOtherinformation(String otherinformation) {
		this.otherinformation = otherinformation;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Date getPublishedTime() {
		return this.publishedTime;
	}

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public int getIsPublished() {
		return this.isPublished;
	}

	public void setIsPublished(int isPublished) {
		this.isPublished = isPublished;
	}

	public int getPublisher() {
		return this.publisher;
	}

	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}

	public int getEmergencyFlag() {
		return this.emergencyFlag;
	}

	public void setEmergencyFlag(int emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public RecruitPositionType getPositionType() {
		return this.positionType;
	}

	public void setPositionType(RecruitPositionType positionType) {
		this.positionType = positionType;
	}

	public RecruitDepartment getDepartment() {
		return this.department;
	}

	public void setDepartment(RecruitDepartment department) {
		this.department = department;
	}
}
