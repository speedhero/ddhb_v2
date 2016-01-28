package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.City;
import java.util.Date;

public class Area extends BaseEntity {
	private static final long serialVersionUID = -8114505163259085798L;
	private String countyName;
	private City city;
	private int deleteFlag;
	private String erpId;
	private Date lastModified;
	private int sortFlag;
	private Date lastSync;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public int getSortFlag() {
		return this.sortFlag;
	}

	public void setSortFlag(int sortFlag) {
		this.sortFlag = sortFlag;
	}
}
