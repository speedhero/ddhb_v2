package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import java.util.Date;

public class BroderAnsered extends BaseEntity {
	private static final long serialVersionUID = 7955857862925696373L;
	private Broker broker;
	private String answeredContent;
	private QuestionStategy questionStategy;
	private int isAccepted;
	private int isShow;
	private int updateFlag;
	private int deleteFlag;
	private String erpId;
	private Date lastModified;
	private Date lastSync;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public String getAnsweredContent() {
		return this.answeredContent;
	}

	public void setAnsweredContent(String answeredContent) {
		this.answeredContent = answeredContent;
	}

	public QuestionStategy getQuestionStategy() {
		return this.questionStategy;
	}

	public void setQuestionStategy(QuestionStategy questionStategy) {
		this.questionStategy = questionStategy;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getIsAccepted() {
		return this.isAccepted;
	}

	public void setIsAccepted(int isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getIsShow() {
		return this.isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getUpdateFlag() {
		return this.updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastSync() {
		return this.lastSync;
	}

	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}
}
