package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.util.Date;

public class MemberBrowseHistory extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private PlatMemberInfo platMemberInfo;
	private int objectType;
	private String objectId;
	private Date createTime;
	private Date modifiedTime;
	private Integer deleteFlag = Integer.valueOf(0);
	private HouseSecond secondHouse;
	private HouseRent rentHouse;
	private Community community;

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public int getObjectType() {
		return this.objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public HouseSecond getSecondHouse() {
		return this.secondHouse;
	}

	public void setSecondHouse(HouseSecond secondHouse) {
		this.secondHouse = secondHouse;
	}

	public HouseRent getRentHouse() {
		return this.rentHouse;
	}

	public void setRentHouse(HouseRent rentHouse) {
		this.rentHouse = rentHouse;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
}
