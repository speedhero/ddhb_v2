package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class Tag extends BaseEntity {
	private static final long serialVersionUID = 1731030983692363869L;
	private String tagName;
	private Integer moduleId;
	private String iconUrl;
	private int deleteFlag;
	private String tagColor;
	private String fontColor;
	private String erpId;
	private Integer isSearchIndividual;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getFontColor() {
		return this.fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getTagColor() {
		return this.tagColor;
	}

	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getIsSearchIndividual() {
		return this.isSearchIndividual;
	}

	public void setIsSearchIndividual(Integer isSearchIndividual) {
		this.isSearchIndividual = isSearchIndividual;
	}
}
