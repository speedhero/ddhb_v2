package com.huatek.hbwebsite.basemanagement.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.io.Serializable;
import java.util.Date;

public class PlatImage extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -6835974136870876243L;
	private PlatMemberInfo platMemberInfo;
	private String moduleFlag;
	private String picPath;
	private Date createdTime;
	private Integer isPublic;

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public String getModuleFlag() {
		return this.moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
}
