package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.StudyZoneType;
import java.util.Date;

public class School extends BaseEntity {
	private static final long serialVersionUID = 6745327275528453376L;
	private String stuName;
	private StudyZoneType studyZoneType;
	private int isStudyZone;
	private String gps;
	private int deleteFlag;
	private String erpId;
	private Area region;
	private int sortinddex;
	private Date lastModified;
	private Date lastSync;
	private String associatedSchoolid;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public StudyZoneType getStudyZoneType() {
		return this.studyZoneType;
	}

	public void setStudyZoneType(StudyZoneType studyZoneType) {
		this.studyZoneType = studyZoneType;
	}

	public String getGps() {
		return this.gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getIsStudyZone() {
		return this.isStudyZone;
	}

	public void setIsStudyZone(int isStudyZone) {
		this.isStudyZone = isStudyZone;
	}

	public Area getRegion() {
		return this.region;
	}

	public void setRegion(Area region) {
		this.region = region;
	}

	public int getSortinddex() {
		return this.sortinddex;
	}

	public void setSortinddex(int sortinddex) {
		this.sortinddex = sortinddex;
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

	public String getAssociatedSchoolid() {
		return this.associatedSchoolid;
	}

	public void setAssociatedSchoolid(String associatedSchoolid) {
		this.associatedSchoolid = associatedSchoolid;
	}
}
