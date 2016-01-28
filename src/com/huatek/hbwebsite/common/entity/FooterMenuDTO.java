package com.huatek.hbwebsite.common.entity;

import com.huatek.hbwebsite.common.entity.FooterLinkDTO;
import java.io.Serializable;
import java.util.List;

public class FooterMenuDTO implements Serializable {
	private static final long serialVersionUID = -3821096324694657734L;
	private Long id;
	private String menuName;
	private List<FooterLinkDTO> linkDTOList;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<FooterLinkDTO> getLinkDTOList() {
		return this.linkDTOList;
	}

	public void setLinkDTOList(List<FooterLinkDTO> linkDTOList) {
		this.linkDTOList = linkDTOList;
	}
}
