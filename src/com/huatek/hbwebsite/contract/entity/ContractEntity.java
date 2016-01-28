package com.huatek.hbwebsite.contract.entity;

import com.huatek.hbwebsite.contract.entity.ContractFlowEntity;
import java.util.List;

public class ContractEntity {
	private String itemID;
	private String contractID;
	private String IDCardNo;
	private String phoneNum;		//手机号码
	private String resultCode;		//查询结果代码
	private String resultMessage;	//查询结果描述
	List<ContractFlowEntity> flows;

	public String getItemID() {
		return this.itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getContractID() {
		return this.contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public String getIDCardNo() {
		return this.IDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		this.IDCardNo = iDCardNo;
	}

	public List<ContractFlowEntity> getFlows() {
		return this.flows;
	}

	public void setFlows(List<ContractFlowEntity> flows) {
		this.flows = flows;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
