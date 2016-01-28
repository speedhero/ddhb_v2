package com.huatek.hbwebsite.common.entity;

import java.io.Serializable;

public class FooterLinkDTO implements Serializable {
	private static final long serialVersionUID = -7771350844334630625L;
	private Long id;
	private String linkName;
	private String linkUrl;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinkName() {
		return this.linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
