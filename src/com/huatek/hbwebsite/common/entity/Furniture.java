package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class Furniture extends BaseEntity {
	private static final long serialVersionUID = 3327920662232976388L;
	private String furName;
	private String imgUrl;
	private String imgDisUrl;
	private int sortIndex;
	private Integer deleteFlag;
	private String erpId;
	private Date lastModified;
	private Date lastSync;
	private boolean flag;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getFurName() {
		return this.furName;
	}

	public void setFurName(String furName) {
		this.furName = furName;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgDisUrl() {
		return this.imgDisUrl;
	}

	public void setImgDisUrl(String imgDisUrl) {
		this.imgDisUrl = imgDisUrl;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
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

	public boolean isFlag() {
		return this.flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
