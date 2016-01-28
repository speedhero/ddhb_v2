package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class HouseSecondBrowseAnalyse extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String analyseId;
	private String houseId;
	private Date browseTime;
	private String countyId;
	private String countyName;
	private String cbdId;
	private String cbdName;
	private String communityId;
	private String communityName;
	private Float houseArea;
	private Float housePrice;
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

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
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

	public String getCbdId() {
		return this.cbdId;
	}

	public void setCbdId(String cbdId) {
		this.cbdId = cbdId;
	}

	public String getCbdName() {
		return this.cbdName;
	}

	public void setCbdName(String cbdName) {
		this.cbdName = cbdName;
	}

	public String getCommunityId() {
		return this.communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Float getHouseArea() {
		return this.houseArea;
	}

	public void setHouseArea(Float houseArea) {
		this.houseArea = houseArea;
	}

	public Float getHousePrice() {
		return this.housePrice;
	}

	public void setHousePrice(Float housePrice) {
		this.housePrice = housePrice;
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
