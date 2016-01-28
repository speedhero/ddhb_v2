package com.huatek.hbwebsite.search.entity;

import com.huatek.base.entity.BaseEntity;

public class SharedSearch extends BaseEntity {
	private static final long serialVersionUID = 1411895073985300671L;
	private String searchID;
	private String searchItemFinger;
	private String searchContent;
	private int deleteFlag;

	public String getSearchID() {
		return this.searchID;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchItemFinger() {
		return this.searchItemFinger;
	}

	public void setSearchItemFinger(String searchItemFinger) {
		this.searchItemFinger = searchItemFinger;
	}

	public String getSearchContent() {
		return this.searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
