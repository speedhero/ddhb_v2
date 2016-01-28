package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.City;
import java.util.Date;

public class SubwayLine extends BaseEntity {
	private static final long serialVersionUID = 8865368977127317548L;
	private String subwayName;
	private City city;
	private String erpId;
	private Integer deleteFlag;
	private Date lastModified;
	private Date lastSync;

	public String getSubwayName() {
		return this.subwayName;
	}

	public void setSubwayName(String subwayName) {
		this.subwayName = subwayName;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
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
