package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CBD;
import java.util.Date;
import java.util.List;

public class CBDWebsite extends BaseEntity {
	private static final long serialVersionUID = -1494883938417675206L;
	private String name;
	private Integer websiteId;
	private Area area;
	private Date lastModified;
	private Integer deleteFlag;
	private List<CBD> cbdList;
	private String cbdSearch;
	private String initial;
	
	private String lngQQ;
	private String latQQ;
	private String lngBaidu;
	private String latBaidu;
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWebsiteId() {
		return this.websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<CBD> getCbdList() {
		return this.cbdList;
	}

	public void setCbdList(List<CBD> cbdList) {
		this.cbdList = cbdList;
	}

	public String getCbdSearch() {
		return this.cbdSearch;
	}

	public void setCbdSearch(String cbdSearch) {
		this.cbdSearch = cbdSearch;
	}

	public String getInitial() {
		return this.initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getLngQQ() {
		return lngQQ;
	}

	public void setLngQQ(String lngQQ) {
		this.lngQQ = lngQQ;
	}

	public String getLatQQ() {
		return latQQ;
	}

	public void setLatQQ(String latQQ) {
		this.latQQ = latQQ;
	}

	public String getLngBaidu() {
		return lngBaidu;
	}

	public void setLngBaidu(String lngBaidu) {
		this.lngBaidu = lngBaidu;
	}

	public String getLatBaidu() {
		return latBaidu;
	}

	public void setLatBaidu(String latBaidu) {
		this.latBaidu = latBaidu;
	}
}
