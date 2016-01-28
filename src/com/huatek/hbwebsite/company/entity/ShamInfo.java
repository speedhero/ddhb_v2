package com.huatek.hbwebsite.company.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class ShamInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String reportName;
	private String emailAddr;
	private String telephone;
	private String content;
	private int houseType;
	private String houseNo;
	private int statu;
	private Date createDate;
	private Date operationDate;
	private int deleteFlag;
	private String brokerId;
	private String verifyCode;

	public String getBrokerId() {
		return this.brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHouseType() {
		return this.houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public int getStatu() {
		return this.statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFalg) {
		this.deleteFlag = deleteFalg;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
