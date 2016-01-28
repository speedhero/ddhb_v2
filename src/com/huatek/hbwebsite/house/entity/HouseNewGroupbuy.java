package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.house.entity.HouseNew;
import java.util.Date;

public class HouseNewGroupbuy extends BaseEntity {
	private static final long serialVersionUID = 4664452724815575066L;
	private HouseNew houseNew;
	private String clientName;
	private String clientTelephone;
	private Date applyDate;
	private Integer deleteFlag;
	private String clientEmail;
	private String clientproperties;
	private String verifyCode;

	public String getClientEmail() {
		return this.clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientproperties() {
		return this.clientproperties;
	}

	public void setClientproperties(String clientproperties) {
		this.clientproperties = clientproperties;
	}

	public HouseNew getHouseNew() {
		return this.houseNew;
	}

	public void setHouseNew(HouseNew houseNew) {
		this.houseNew = houseNew;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientTelephone() {
		return this.clientTelephone;
	}

	public void setClientTelephone(String clientTelephone) {
		this.clientTelephone = clientTelephone;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
}
