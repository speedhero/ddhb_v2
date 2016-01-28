package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.SubwayLine;
import java.util.Date;

public class SubwayStation extends BaseEntity {
	private static final long serialVersionUID = -7657531585349986576L;
	private String stationName;
	private SubwayLine subwayLine;
	private String erpId;
	private Integer deleteFlag;
	private Date lastModified;
	private Date lastSync;

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public SubwayLine getSubwayLine() {
		return this.subwayLine;
	}

	public void setSubwayLine(SubwayLine subwayLine) {
		this.subwayLine = subwayLine;
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
