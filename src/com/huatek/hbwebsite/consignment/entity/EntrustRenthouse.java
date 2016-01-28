package com.huatek.hbwebsite.consignment.entity;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.hbwebsite.consignment.entity.Entrust;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.dongdao.axis2.entity.toERP.EntrustRentHouseSyn;

public class EntrustRenthouse extends Entrust {
	private static final long serialVersionUID = -9078824447642631263L;
	private String rentType;
	private String facility;
	private String otherContact;
	private Date lastModified;
	private Timestamp synchronizedTime;

	public String getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
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

	public EntrustRentHouseSyn convertToSyncObj(String operationId) {
		EntrustRentHouseSyn entrustRentHouseSyn = new EntrustRentHouseSyn();
		entrustRentHouseSyn.setItemID(String.valueOf(getId()));
		if (operationId == null) {
			entrustRentHouseSyn.setOperationId("1");
		} else {
			entrustRentHouseSyn.setOperationId(operationId);
		}

		entrustRentHouseSyn.setRequestID(UUID.randomUUID().toString());
		if (getLocFlag()==null || getLocFlag() == 1) {
			entrustRentHouseSyn.setRequirementType("不明确");
			entrustRentHouseSyn.setAreaID("");
			entrustRentHouseSyn.setSubdistrictName("");
			entrustRentHouseSyn.setSubdistrictID("");
		} else if (getLocFlag() == 2) {
			entrustRentHouseSyn.setRequirementType("商圈");
			entrustRentHouseSyn.setAreaID(String.valueOf(getCbdNo()));
			entrustRentHouseSyn.setSubdistrictName("");
			entrustRentHouseSyn.setSubdistrictID("");
		} else if (getLocFlag() == 3) {
			entrustRentHouseSyn.setAreaID("");
			entrustRentHouseSyn.setRequirementType("小区");
			entrustRentHouseSyn.setSubdistrictName(StringUtil.trimToEmpty(getCommunityName()));
			entrustRentHouseSyn.setSubdistrictID("");
		}

		entrustRentHouseSyn.setMaxRoom(String.valueOf(getMaxShi()));
		entrustRentHouseSyn.setMinRoom(String.valueOf(getMinShi()));
		entrustRentHouseSyn.setMaxPrice(String.valueOf(getMaxPrice()));
		entrustRentHouseSyn.setMinPrice(String.valueOf(getMinPrice()));
		entrustRentHouseSyn.setLivingRoomCount(String.valueOf(getTing()));
		entrustRentHouseSyn.setOtherRequirement(StringUtil.trimToEmpty(getOther()));
		entrustRentHouseSyn.setClientName(getName());
		if (getGender()==null || getGender() == 0) {
			entrustRentHouseSyn.setAppellation("先生");
			entrustRentHouseSyn.setGender("男");
		} else {
			entrustRentHouseSyn.setAppellation("女士");
			entrustRentHouseSyn.setGender("女");
		}

		entrustRentHouseSyn.setMobilePhone(getPhone());
		entrustRentHouseSyn.setOtherContactInfo(StringUtil.trimToEmpty(getOtherContact()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = getLastModified()==null ? "" : sdf.format(getLastModified());
		entrustRentHouseSyn.setCreateTime(timeStr);
		return entrustRentHouseSyn;
	}

}
