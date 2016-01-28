package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class HouseNewBrowseAnalyse extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String analyseId;
	private String projectNo;
	private String projectName;
	private Date browseTime;
	private String countyId;
	private String countyName;
	private Integer isMember;
	private String memberName;
	private Date registerTime;
	private Integer deleteFlag;
	private String clientFlag;

	public String getAnalyseId() {
		return this.analyseId;
	}

	public void setAnalyseId(String analyseId) {
		this.analyseId = analyseId;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getBrowseTime() {
		return this.browseTime;
	}

	public void setBrowseTime(Date browseTime) {
		this.browseTime = browseTime;
	}

	public String getCountyId() {
		return this.countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getIsMember() {
		return this.isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getClientFlag() {
		return this.clientFlag;
	}

	public void setClientFlag(String clientFlag) {
		this.clientFlag = clientFlag;
	}
}
