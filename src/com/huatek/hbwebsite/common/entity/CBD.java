package com.huatek.hbwebsite.common.entity;

import com.google.gson.annotations.Expose;
import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CBDWebsite;
import java.util.Date;

public class CBD extends BaseEntity {
	private static final long serialVersionUID = -1944849013068791039L;
	@Expose
	private String cbdName;
	@Expose
	private String erpId;
	private String gps;
	private Area county;
	@Expose
	private int isMapping;
	private String initial;
	private int deleteFlag;
	private Date lastModified;
	private Date lastSync;
	private String comment;
	@Expose
	private CBDWebsite parentCBD;
	private String belongTo;
	private String path;

	public String getCbdName() {
		return this.cbdName;
	}

	public void setCbdName(String cbdName) {
		this.cbdName = cbdName;
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

	public Area getCounty() {
		return this.county;
	}

	public void setCounty(Area county) {
		this.county = county;
	}

	public int getIsMapping() {
		return this.isMapping;
	}

	public void setIsMapping(int isMapping) {
		this.isMapping = isMapping;
	}

	public String getInitial() {
		return this.initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CBDWebsite getParentCBD() {
		return this.parentCBD;
	}

	public void setParentCBD(CBDWebsite parentCBD) {
		this.parentCBD = parentCBD;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBelongTo() {
		return this.belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
}
