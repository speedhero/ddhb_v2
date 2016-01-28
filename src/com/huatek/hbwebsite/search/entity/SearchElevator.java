package com.huatek.hbwebsite.search.entity;

import com.google.gson.annotations.Expose;
import com.huatek.base.entity.BaseEntity;

public class SearchElevator extends BaseEntity {
	private static final long serialVersionUID = -4201960935853631025L;
	@Expose
	private String title;
	@Expose
	private int value;
	private int deleteFlag;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
