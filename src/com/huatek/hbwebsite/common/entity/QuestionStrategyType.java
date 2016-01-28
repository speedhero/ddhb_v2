package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class QuestionStrategyType extends BaseEntity {
	private static final long serialVersionUID = -7091315780903730472L;
	private String typeName;
	private String iconUrl;
	private Date createTime;
	private int deleteFlag;
	private String hoverIconUrl;
	private String erpId;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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

	public String getHoverIconUrl() {
		return this.hoverIconUrl;
	}

	public void setHoverIconUrl(String hoverIconUrl) {
		this.hoverIconUrl = hoverIconUrl;
	}
}
