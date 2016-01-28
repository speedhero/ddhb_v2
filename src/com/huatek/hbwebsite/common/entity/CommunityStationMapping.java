package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Station;
import java.util.Date;

public class CommunityStationMapping extends BaseEntity {
	private static final long serialVersionUID = -7055354574782293432L;
	private Community community;
	private Station station;
	private Float distance;
	private Integer deleteFlag;
	private String erpId;
	private Date lastModified;
	private Date lastSync;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Float getDistance() {
		return this.distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
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
}
