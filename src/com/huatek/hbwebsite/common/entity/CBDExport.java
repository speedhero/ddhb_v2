package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.CBD;
import java.util.Date;

public class CBDExport extends BaseEntity {
	private static final long serialVersionUID = 3452626125195378916L;
	private String erpId;
	private CBD cbd;
	private Broker broker;
	private Date lastModified;
	private Date lastSync;
	private int deleteFlag;

	public CBD getCbd() {
		return this.cbd;
	}

	public void setCbd(CBD cbd) {
		this.cbd = cbd;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
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

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
}
