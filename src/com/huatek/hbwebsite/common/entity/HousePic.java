package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class HousePic extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String pictureName;
	private String houseId;
	private Integer houseType;
	private Integer picType;
	private String picComment;
	private String picUrl;
	private int sortIndex;
	private String pictureFormat;
	private int layoutFlag;
	private Date lastModified;
	private Date lastSync;
	private Integer deleteFlag;
	private String erpId;
	private String shelvingID;

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Integer getHouseType() {
		return this.houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Integer getPicType() {
		return this.picType;
	}

	public void setPicType(Integer picType) {
		this.picType = picType;
	}

	public String getPicComment() {
		return this.picComment;
	}

	public void setPicComment(String picComment) {
		this.picComment = picComment;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

	public String getPictureFormat() {
		return this.pictureFormat;
	}

	public void setPictureFormat(String pictureFormat) {
		this.pictureFormat = pictureFormat;
	}

	public int getLayoutFlag() {
		return this.layoutFlag;
	}

	public void setLayoutFlag(int layoutFlag) {
		this.layoutFlag = layoutFlag;
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

	public String getPictureName() {
		return this.pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getShelvingID() {
		return this.shelvingID;
	}

	public void setShelvingID(String shelvingID) {
		this.shelvingID = shelvingID;
	}
}
