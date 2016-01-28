package com.huatek.hbwebsite.company.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.company.entity.CompanyCustomerServiceType;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.dongdao.axis2.entity.toERP.CompanyCustomerServiceSyn;

public class CompanyCustomerService extends BaseEntity {
	private static final long serialVersionUID = 1265571025838111005L;
	private CompanyCustomerServiceType serviceType;
	private String username;
	private String emailAddress;
	private String telephoneNo;
	private String content;
	private Date createTime;
	private int deleteFlag;
	private int statu;
	private int synchronizedFlag;
	private Timestamp synchronizedTime;
	private String verifyCode;
	private PlatMemberInfo member;

	public CompanyCustomerServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(CompanyCustomerServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephoneNo() {
		return this.telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getStatu() {
		return this.statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public int getSynchronizedFlag() {
		return this.synchronizedFlag;
	}

	public void setSynchronizedFlag(int synchronizedFlag) {
		this.synchronizedFlag = synchronizedFlag;
	}

	public Timestamp getSynchronizedTime() {
		return this.synchronizedTime;
	}

	public void setSynchronizedTime(Timestamp synchronizedTime) {
		this.synchronizedTime = synchronizedTime;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public PlatMemberInfo getMember() {
		return this.member;
	}

	public void setMember(PlatMemberInfo member) {
		this.member = member;
	}

	public CompanyCustomerServiceSyn convertToSyncObj(String operationId) {
		CompanyCustomerServiceSyn companyCustomerServiceSyn = new CompanyCustomerServiceSyn();
		companyCustomerServiceSyn.setItemID(String.valueOf(getId()));
		if (operationId == null) {
			companyCustomerServiceSyn.setOperationId("1");
		} else {
			companyCustomerServiceSyn.setOperationId(operationId);
		}

		companyCustomerServiceSyn.setAppellation("");
		companyCustomerServiceSyn.setClientName(getUsername());
		companyCustomerServiceSyn.setContent(getContent());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = getCreateTime()==null ? "" : df.format(getCreateTime());
		companyCustomerServiceSyn.setCreateTime(timeStr);
		companyCustomerServiceSyn.setEmail(getEmailAddress());
		companyCustomerServiceSyn.setMemberID("");
		companyCustomerServiceSyn.setMobilePhone(getTelephoneNo());
		companyCustomerServiceSyn.setSuggestionID(String.valueOf(getId()));
		if(getServiceType()!=null)
			companyCustomerServiceSyn.setSuggestionType(String.valueOf(getServiceType().getId()));
		else
			companyCustomerServiceSyn.setSuggestionType("");
		if (getMember() == null) {
			companyCustomerServiceSyn.setMemberID("");
			companyCustomerServiceSyn.setMemberName("");
		} else {
			if (getMember().getId() == null) {
				companyCustomerServiceSyn.setMemberID("");
			} else {
				companyCustomerServiceSyn.setMemberID(String.valueOf(getMember().getId()));
			}

			if (getMember().getAccName() == null) {
				companyCustomerServiceSyn.setMemberName("");
			} else {
				companyCustomerServiceSyn.setMemberName(getMember().getAccName());
			}
		}

		return companyCustomerServiceSyn;
	}
}
