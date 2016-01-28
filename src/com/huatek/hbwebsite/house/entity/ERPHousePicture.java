package com.huatek.hbwebsite.house.entity;

public class ERPHousePicture {
	int orderNum;
	String picUrl;
	int layoutFlag;
	String picFormat;

	public int getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getLayoutFlag() {
		return this.layoutFlag;
	}

	public void setLayoutFlag(int layoutFlag) {
		this.layoutFlag = layoutFlag;
	}

	public String getPicFormat() {
		return this.picFormat;
	}

	public void setPicFormat(String picFormat) {
		this.picFormat = picFormat;
	}
}
