package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.SubwayStation;
import java.util.Date;

public class CommunitySubwayStationMapping extends BaseEntity {
	private static final long serialVersionUID = -167686265292020324L;
	private Community community;
	private SubwayStation subwayStation;
	private Float distance;
	private int deleteFlag;
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

	public SubwayStation getSubwayStation() {
		return this.subwayStation;
	}

	public void setSubwayStation(SubwayStation subwayStation) {
		this.subwayStation = subwayStation;
	}

	public Float getDistance() {
		return this.distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
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
}
