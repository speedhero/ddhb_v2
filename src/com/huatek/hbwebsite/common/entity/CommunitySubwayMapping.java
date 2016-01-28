package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.SubwayLine;

public class CommunitySubwayMapping extends BaseEntity {
	private static final long serialVersionUID = -2569450281874272126L;
	private Community community;
	private SubwayLine subway;
	private Float distance;
	private Integer deleteFlag;
	private String erpId;

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

	public SubwayLine getSubway() {
		return this.subway;
	}

	public void setSubway(SubwayLine subway) {
		this.subway = subway;
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
}
