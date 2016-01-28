package com.huatek.hbwebsite.consignment.entity;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.base.entity.BaseEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.dongdao.axis2.entity.toERP.EntrustSaleHouseSyn;

public class EntrustSalehouse extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String communityId;
	private String communityName;
	private Integer area;
	private Integer buildingNo;
	private Integer unitNo;
	private Integer roomNo;
	private Integer shi;
	private Integer ting;
	private Integer price;
	private String otherRequirement;
	private Long userNo;
	private String userName;
	private Integer gender;
	private String telePhone;
	private String otherContact;
	private Integer synchronizedFlag;
	private String verifyCode;
	private Integer deleteFlag;
	private Date lastModified;
	private Timestamp synchronizedTime;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public Integer getTing() {
		return ting;
	}

	public void setTing(Integer ting) {
		this.ting = ting;
	}

	public Integer getPrice() {
		return price;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public Integer getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(Integer buildingNo) {
		this.buildingNo = buildingNo;
	}

	public Integer getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(Integer unitNo) {
		this.unitNo = unitNo;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getShi() {
		return shi;
	}

	public void setShi(Integer shi) {
		this.shi = shi;
	}

	public String getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getOtherRequirement() {
		return otherRequirement;
	}

	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public Integer getSynchronizedFlag() {
		return synchronizedFlag;
	}

	public void setSynchronizedFlag(Integer synchronizedFlag) {
		this.synchronizedFlag = synchronizedFlag;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Timestamp getSynchronizedTime() {
		return synchronizedTime;
	}

	public void setSynchronizedTime(Timestamp synchronizedTime) {
		this.synchronizedTime = synchronizedTime;
	}

	public EntrustSaleHouseSyn convertToSyncObj(String operationId) {
		EntrustSaleHouseSyn entrustSaleHouseSyn = new EntrustSaleHouseSyn();
		entrustSaleHouseSyn.setItemID(String.valueOf(getId()));
		if (operationId == null) {
			entrustSaleHouseSyn.setOperationId("1");
		} else {
			entrustSaleHouseSyn.setOperationId(operationId);
		}

		entrustSaleHouseSyn.setRequestID(UUID.randomUUID().toString());
		entrustSaleHouseSyn.setSubdistrictID(StringUtil.trimToEmpty(getCommunityId()));
		entrustSaleHouseSyn.setSubdistrictName(StringUtil.trimToEmpty(getCommunityName()));
		entrustSaleHouseSyn.setBuildingNo(String.valueOf(getBuildingNo()));
		entrustSaleHouseSyn.setUnitNo(String.valueOf(getUnitNo()));
		entrustSaleHouseSyn.setRoomNo(String.valueOf(getRoomNo()));
		entrustSaleHouseSyn.setArea(String.valueOf(getArea()));
		entrustSaleHouseSyn.setPrice(String.valueOf(getPrice()));
		entrustSaleHouseSyn.setRoomCount(String.valueOf(getShi()));
		entrustSaleHouseSyn.setLivingRoomCount(String.valueOf(getTing()));
		entrustSaleHouseSyn.setOtherRequirement(StringUtil.trimToEmpty(getOtherRequirement()));
		entrustSaleHouseSyn.setClientName(getUserName());
		if (getGender()==null || getGender() == 0) {
			entrustSaleHouseSyn.setAppellation("先生");
			entrustSaleHouseSyn.setGender("男");
		} else {
			entrustSaleHouseSyn.setAppellation("女士");
			entrustSaleHouseSyn.setGender("女");
		}

		entrustSaleHouseSyn.setMobilePhone(getTelePhone());
		entrustSaleHouseSyn.setOtherContactInfo(StringUtil.trimToEmpty(getOtherContact()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = getLastModified()==null ? "" : sdf.format(getLastModified());
		entrustSaleHouseSyn.setCreateTime(timeStr);
		return entrustSaleHouseSyn;
	}
}
