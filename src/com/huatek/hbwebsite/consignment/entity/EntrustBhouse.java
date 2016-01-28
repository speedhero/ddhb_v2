package com.huatek.hbwebsite.consignment.entity;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.hbwebsite.consignment.entity.Entrust;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.dongdao.axis2.entity.toERP.EntrustBuyHouseSyn;

public class EntrustBhouse extends Entrust {
	private static final long serialVersionUID = 5737869438436626574L;
	private Date lastModified;
	private Timestamp synchronizedTime;

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Timestamp getSynchronizedTime() {
		return this.synchronizedTime;
	}

	public void setSynchronizedTime(Timestamp synchronizedTime) {
		this.synchronizedTime = synchronizedTime;
	}

	public EntrustBuyHouseSyn convertToSyncObj(String operationId) {
		EntrustBuyHouseSyn buyHouseSyn = new EntrustBuyHouseSyn();
		buyHouseSyn.setItemID(String.valueOf(getId()));
		buyHouseSyn.setRequestID(UUID.randomUUID().toString());
		if (operationId == null) {
			buyHouseSyn.setOperationId("1");
		} else {
			buyHouseSyn.setOperationId(operationId);
		}

		if (getLocFlag() == null || getLocFlag() == 1) {
			buyHouseSyn.setRequirementType("不明确");
			buyHouseSyn.setAreaID("");
			buyHouseSyn.setSubdistrictName("");
			buyHouseSyn.setSubdistrictID("");
		} else if (getLocFlag() == 2) {
			buyHouseSyn.setRequirementType("商圈");
			buyHouseSyn.setAreaID(String.valueOf(getCbdNo()));
			buyHouseSyn.setSubdistrictName("");
			buyHouseSyn.setSubdistrictID("");
		} else if (getLocFlag() == 3) {
			buyHouseSyn.setAreaID("");
			buyHouseSyn.setRequirementType("小区");
			buyHouseSyn.setSubdistrictName(getCommunityName());
			buyHouseSyn.setSubdistrictID("");
		}

		buyHouseSyn.setMaxRoom(String.valueOf(getMaxShi()));
		buyHouseSyn.setMinRoom(String.valueOf(getMinShi()));
		buyHouseSyn.setMaxPrice(String.valueOf(getMaxPrice()));
		buyHouseSyn.setMinPrice(String.valueOf(getMinPrice()));
		buyHouseSyn.setLivingRoomCount(String.valueOf(getTing()));
		buyHouseSyn.setOtherRequirement(StringUtil.trimToEmpty(getOther()));
		buyHouseSyn.setClientName(getName());
		if (getGender() ==null || getGender() == 0) {
			buyHouseSyn.setAppellation("先生");
			buyHouseSyn.setGender("男");
		} else {
			buyHouseSyn.setAppellation("女士");
			buyHouseSyn.setGender("女");
		}

		buyHouseSyn.setMobilePhone(getPhone());
		buyHouseSyn.setOtherContactInfo(StringUtil.trimToEmpty(getOtherContect()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = getLastModified()==null? "" : sdf.format(getLastModified());
		buyHouseSyn.setCreateTime(timeStr);
		return buyHouseSyn;
	}
}
