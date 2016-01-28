package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.util.Date;

public class MemberQuestion extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private PlatMemberInfo platMemberInfo;
	private String userName;
	private int commentScore;
	private String question;
	private Broker platBroker;
	private String applyContent;
	private String clientIp;
	private int isDisplay;
	private int isTop;
	private int isSystem;
	private Date createTime;
	private Date modifiedTime;

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCommentScore() {
		return this.commentScore;
	}

	public void setCommentScore(int commentScore) {
		this.commentScore = commentScore;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Broker getPlatBroker() {
		return this.platBroker;
	}

	public void setPlatBroker(Broker platBroker) {
		this.platBroker = platBroker;
	}

	public String getApplyContent() {
		return this.applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public int getIsDisplay() {
		return this.isDisplay;
	}

	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}

	public int getIsTop() {
		return this.isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
