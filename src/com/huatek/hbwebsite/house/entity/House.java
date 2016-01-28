package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import java.io.Serializable;

public abstract class House extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String houseno;
	private String title;
	private String content;
	private String location;
	private float area;
	private String houseType;
	private String orientations;
	private String storey;
	private String decorationType;
	private int soritindex;
	private String communityId;
	private String communityName;
	private int publisherId;
	private String businessCircle;
	private String studyRegion;
	private int deleteflag;
	private String regionId;

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getHouseno() {
		return this.houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getArea() {
		return this.area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getHouseType() {
		return this.houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getOrientations() {
		return this.orientations;
	}

	public void setOrientations(String orientations) {
		this.orientations = orientations;
	}

	public String getStorey() {
		return this.storey;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}

	public String getDecorationType() {
		return this.decorationType;
	}

	public void setDecorationType(String decorationType) {
		this.decorationType = decorationType;
	}

	public int getSoritindex() {
		return this.soritindex;
	}

	public void setSoritindex(int soritindex) {
		this.soritindex = soritindex;
	}

	public String getCommunityId() {
		return this.communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public int getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getBusinessCircle() {
		return this.businessCircle;
	}

	public void setBusinessCircle(String businessCircle) {
		this.businessCircle = businessCircle;
	}

	public String getStudyRegion() {
		return this.studyRegion;
	}

	public void setStudyRegion(String studyRegion) {
		this.studyRegion = studyRegion;
	}

	public int getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}
}
