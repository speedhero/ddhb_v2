package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.util.Date;

public class HouseReduceNotice extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private PlatMemberInfo platMemberInfo;
	private Date createTime;
	private float currentPrice;
	private int deleteFlag;
	private String noticePhone;
	private String noticeEmail;
	private int houseType;
	private String houseNo;
	private HouseSecond houseSecond;
	private HouseRent houseRent;
	private Broker broker;
	private int priceFlag;
	private String erpId;
	private String houseId;
	private int emailFlag;
	private int phoneFlag;
	private String houseTitle;
	private int emailDone;
	private int smsDone;

	public int getEmailDone() {
		return this.emailDone;
	}

	public void setEmailDone(int emailDone) {
		this.emailDone = emailDone;
	}

	public int getSmsDone() {
		return this.smsDone;
	}

	public void setSmsDone(int smsDone) {
		this.smsDone = smsDone;
	}

	public String getHouseTitle() {
		return this.houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}

	public int getEmailFlag() {
		return this.emailFlag;
	}

	public void setEmailFlag(int emailFlag) {
		this.emailFlag = emailFlag;
	}

	public int getPhoneFlag() {
		return this.phoneFlag;
	}

	public void setPhoneFlag(int phoneFlag) {
		this.phoneFlag = phoneFlag;
	}

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public int getHouseType() {
		return this.houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public float getCurrentPrice() {
		return this.currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getPriceFlag() {
		return this.priceFlag;
	}

	public void setPriceFlag(int priceFlag) {
		this.priceFlag = priceFlag;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getNoticePhone() {
		return this.noticePhone;
	}

	public void setNoticePhone(String noticePhone) {
		this.noticePhone = noticePhone;
	}

	public String getNoticeEmail() {
		return this.noticeEmail;
	}

	public void setNoticeEmail(String noticeEmail) {
		this.noticeEmail = noticeEmail;
	}

	public HouseSecond getHouseSecond() {
		return this.houseSecond;
	}

	public void setHouseSecond(HouseSecond houseSecond) {
		this.houseSecond = houseSecond;
	}

	public HouseRent getHouseRent() {
		return this.houseRent;
	}

	public void setHouseRent(HouseRent houseRent) {
		this.houseRent = houseRent;
	}
}
