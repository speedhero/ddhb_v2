package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.house.entity.HouseNew;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import java.util.Date;

public class PlatCollection extends BaseEntity {
	private static final long serialVersionUID = -5263406102251547945L;
	private PlatMemberInfo platMemberInfo;
	private int collectType;
	private String objectId;
	private String brokerId;
	private Date createDate;
	private float priceCc;
	private int deleteFlag;
	private HouseSecond houseSecond;
	private HouseRent houseRent;
	private Community community;
	private HouseNew houseNew;

	public HouseNew getHouseNew() {
		return houseNew;
	}

	public void setHouseNew(HouseNew houseNew) {
		this.houseNew = houseNew;
	}

	public HouseSecond getHouseSecond() {
		return this.houseSecond;
	}

	public void setHouseSecond(HouseSecond houseSecond) {
		this.houseSecond = houseSecond;
	}

	public HouseRent getHouseRent() {
		return this.houseRent;
	}

	public void setHouseRent(HouseRent houseRent) {
		this.houseRent = houseRent;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public float getPriceCc() {
		return this.priceCc;
	}

	public void setPriceCc(float priceCc) {
		this.priceCc = priceCc;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public int getCollectType() {
		return this.collectType;
	}

	public void setCollectType(int collectType) {
		this.collectType = collectType;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBrokerId() {
		return this.brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
}
