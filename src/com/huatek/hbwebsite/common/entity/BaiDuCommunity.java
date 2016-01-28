package com.huatek.hbwebsite.common.entity;

import java.sql.Timestamp;

import com.huatek.base.entity.BaseEntity;

public class BaiDuCommunity extends BaseEntity{

	private String communityId;			// 小区编号
	private	String title;				// 房源标题	
	private Timestamp lastmod;			// 最后更新时间
	private Timestamp publishTime;		// 发布时间
	private Boolean isSync;				// 是否已同步
	private Integer state;				//房源状态：1：新增	2：修改	3：删除	4:小区有问题
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getLastmod() {
		return lastmod;
	}
	public void setLastmod(Timestamp lastmod) {
		this.lastmod = lastmod;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public Boolean getIsSync() {
		return isSync;
	}
	public void setIsSync(Boolean isSync) {
		this.isSync = isSync;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
