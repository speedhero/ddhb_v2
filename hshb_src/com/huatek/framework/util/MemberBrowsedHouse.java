package com.huatek.framework.util;

public class MemberBrowsedHouse {
	private Integer shi;
	private Integer ting;
	private Long communityId;

	public MemberBrowsedHouse() {
	}

	public MemberBrowsedHouse(Integer shi, Integer ting, Long communityId) {
		this.shi = shi;
		this.ting = ting;
		this.communityId = communityId;
	}

	public Integer getShi() {
		return this.shi;
	}

	public void setShi(Integer shi) {
		this.shi = shi;
	}

	public Integer getTing() {
		return this.ting;
	}

	public void setTing(Integer ting) {
		this.ting = ting;
	}

	public Long getCommunityId() {
		return this.communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
}
