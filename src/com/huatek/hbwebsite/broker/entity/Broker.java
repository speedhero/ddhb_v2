package com.huatek.hbwebsite.broker.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Store;
import java.util.Date;

public class Broker extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String erpId;
	private String bname;
	private String photograph;
	private String telephone;
	private String qq;
	private String wchat;
	private Integer deleteFlag;
	private Store store;
	private String introduction;
	private Date lastModified;
	private Date lastSync;
	private Integer daikan;
	private Integer houseCount;
	private String weCharUrl;

	//经纪人详情页背景图
	private String backgroundImage;
	
	public Integer getDaikan() {
		return daikan==null || daikan == 0 ? (int) Math.round(Math.random() * 15.0D + 5.0D) : daikan;
	}

	public void setDaikan(Integer daikan) {
		this.daikan = daikan;
	}

	public Integer getHouseCount() {
		return this.houseCount;
	}

	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getPhotograph() {
		return this.photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWchat() {
		return this.wchat;
	}

	public void setWchat(String wchat) {
		this.wchat = wchat;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getWeCharUrl() {
		return this.weCharUrl;
	}

	public void setWeCharUrl(String weCharUrl) {
		this.weCharUrl = weCharUrl;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
}
