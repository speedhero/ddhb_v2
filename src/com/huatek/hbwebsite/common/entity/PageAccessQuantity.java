package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;

public class PageAccessQuantity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1285152949245150500L;
	
	private String pageInformationId;	//页面信息 如 经纪人Id，房源Id等，都归为一类
	private int accessQuantity;		//页面访问量
	private String description;		//页面描述
	public String getPageInformationId() {
		return pageInformationId;
	}
	public void setPageInformationId(String pageInformationId) {
		this.pageInformationId = pageInformationId;
	}
	public int getAccessQuantity() {
		return accessQuantity;
	}
	public void setAccessQuantity(int accessQuantity) {
		this.accessQuantity = accessQuantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
