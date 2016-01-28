package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import java.util.Date;

public class HouseAppraise extends BaseEntity {
	private static final long serialVersionUID = 7728355115768610708L;
	private String houseNo;
	private String erpId;
	private String searchno;
	private String houseId;
	private Integer houseType;
	private Broker broker;
	private String title;
	private String content;
	private Date time;
	private int sortIndex;
	private int deleteFlag;
	private Date lastModified;
	private Date lastSync;
	private String shelvingID;

	public String getSearchno() {
		return this.searchno;
	}

	public void setSearchno(String searchno) {
		this.searchno = searchno;
	}

	public Integer getHouseType() {
		return this.houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
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

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
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

	public String getShelvingID() {
		return this.shelvingID;
	}

	public void setShelvingID(String shelvingID) {
		this.shelvingID = shelvingID;
	}
}
