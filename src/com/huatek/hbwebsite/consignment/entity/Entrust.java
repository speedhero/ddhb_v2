package com.huatek.hbwebsite.consignment.entity;

import com.huatek.base.entity.BaseEntity;

public class Entrust extends BaseEntity {
	private static final long serialVersionUID = -5635281999579771458L;
	private Integer locFlag;
	private String cbdNo;
	private String communityId;
	private String communityName;
	private Integer maxShi;
	private Integer minShi;
	private Integer ting;
	private Integer minPrice;
	private Integer maxPrice;
	private String other;
	private Long userNo;
	private String name;
	private Integer gender;
	private String phone;
	private String otherContect;
	private String verifyCode;
	private Integer deleteFlag;
	private Integer synchronizedFlag;

	public Integer getLocFlag() {
		return this.locFlag;
	}

	public void setLocFlag(Integer locFlag) {
		this.locFlag = locFlag;
	}

	public String getCbdNo() {
		return this.cbdNo;
	}

	public Long getUserNo() {
		return this.userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public void setCbdNo(String cbdNo) {
		this.cbdNo = cbdNo;
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

	public Integer getTing() {
		return this.ting;
	}

	public void setTing(Integer ting) {
		this.ting = ting;
	}

	public Integer getMaxShi() {
		return this.maxShi;
	}

	public void setMaxShi(Integer maxShi) {
		this.maxShi = maxShi;
	}

	public Integer getMinShi() {
		return this.minShi;
	}

	public void setMinShi(Integer minShi) {
		this.minShi = minShi;
	}

	public Integer getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOtherContect() {
		return this.otherContect;
	}

	public void setOtherContect(String otherContect) {
		this.otherContect = otherContect;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getSynchronizedFlag() {
		return this.synchronizedFlag;
	}

	public void setSynchronizedFlag(Integer synchronizedFlag) {
		this.synchronizedFlag = synchronizedFlag;
	}
}
