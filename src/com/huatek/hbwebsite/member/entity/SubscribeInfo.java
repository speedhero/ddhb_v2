package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

public class SubscribeInfo extends BaseEntity {
	private static final long serialVersionUID = 7339458581200151377L;
	private PlatMemberInfo platMemberInfo;
	private String email;
	private String mobailphone;
	private HouseSecond houseSecond;
	private String subscribeType;
	private String subscribeContent;

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubscribeType() {
		return this.subscribeType;
	}

	public void setSubscribeType(String subscribeType) {
		this.subscribeType = subscribeType;
	}

	public String getSubscribeContent() {
		return this.subscribeContent;
	}

	public void setSubscribeContent(String subscribeContent) {
		this.subscribeContent = subscribeContent;
	}

	public String getMobailphone() {
		return this.mobailphone;
	}

	public void setMobailphone(String mobailphone) {
		this.mobailphone = mobailphone;
	}

	public HouseSecond getHouseSecond() {
		return this.houseSecond;
	}

	public void setHouseSecond(HouseSecond houseSecond) {
		this.houseSecond = houseSecond;
	}
}
