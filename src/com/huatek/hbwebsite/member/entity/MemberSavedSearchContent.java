package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class MemberSavedSearchContent extends BaseEntity {
	private static final long serialVersionUID = 5233263150456461434L;
	private String no;
	private Long memberId;
	private int type;
	private String url;
	private String savedField;
	private int deleteFlag;
	private Date createDate;

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSavedField() {
		return this.savedField;
	}

	public void setSavedField(String savedField) {
		this.savedField = savedField;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
