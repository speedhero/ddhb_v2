package com.huatek.hbwebsite.member.entity;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dongdao.axis2.entity.toERP.MemberBespeakSyn;

public class MemberBespeak extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private PlatMemberInfo platMemberInfo;
	private HouseSecond secondHouse;
	private HouseRent rentHouse;
	private String houseId;
	private Long houseType;
	private Broker platBroker;
	private Date bespeakTimeStart;
	private Date bespeakTimeEnd;
	private String contacts;
	private int gender;
	private String contactsPhone;
	private String emails;
	private Date createdTime;
	private Date modifiedTime;
	private Integer deleteFlag;
	private String verifyCode;
	private String date;
	private String timeBespeak;
	private int synchronizedFlag;
	private String brokerId;
	private Timestamp synchronizedTime;

	public int getSynchronizedFlag() {
		return this.synchronizedFlag;
	}

	public void setSynchronizedFlag(int synchronizedFlag) {
		this.synchronizedFlag = synchronizedFlag;
	}

	public Timestamp getSynchronizedTime() {
		return this.synchronizedTime;
	}

	public void setSynchronizedTime(Timestamp synchronizedTime) {
		this.synchronizedTime = synchronizedTime;
	}

	public String getTimeBespeak() {
		return this.timeBespeak;
	}

	public void setTimeBespeak(String timeBespeak) {
		this.timeBespeak = timeBespeak;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public PlatMemberInfo getPlatMemberInfo() {
		return this.platMemberInfo;
	}

	public void setPlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.platMemberInfo = platMemberInfo;
	}

	public String getHouseId() {
		return this.houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Long getHouseType() {
		return this.houseType;
	}

	public void setHouseType(Long houseType) {
		this.houseType = houseType;
	}

	public Broker getPlatBroker() {
		return this.platBroker;
	}

	public void setPlatBroker(Broker platBroker) {
		this.platBroker = platBroker;
	}

	public Date getBespeakTimeStart() {
		return this.bespeakTimeStart;
	}

	public void setBespeakTimeStart(Date bespeakTimeStart) {
		this.bespeakTimeStart = bespeakTimeStart;
	}

	public Date getBespeakTimeEnd() {
		return this.bespeakTimeEnd;
	}

	public void setBespeakTimeEnd(Date bespeakTimeEnd) {
		this.bespeakTimeEnd = bespeakTimeEnd;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return this.contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public HouseSecond getSecondHouse() {
		return this.secondHouse;
	}

	public void setSecondHouse(HouseSecond secondHouse) {
		this.secondHouse = secondHouse;
	}

	public HouseRent getRentHouse() {
		return this.rentHouse;
	}

	public void setRentHouse(HouseRent rentHouse) {
		this.rentHouse = rentHouse;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBrokerId() {
		return this.brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public void setBeginEnd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = getTimeBespeak();
		String[] times = time.split(",");
		String dateBiginStr = getDate() + " " + times[0] + ":00:00";
		String dateEndStr = getDate() + " " + times[1] + ":00:00";

		try {
			Date dateBigin = format.parse(dateBiginStr);
			setBespeakTimeStart(dateBigin);
			Date dateEnd = format.parse(dateEndStr);
			setBespeakTimeEnd(dateEnd);
		} catch (ParseException ex) {
			;
		}

	}

	public MemberBespeakSyn convertToSyncObj(String operationId) {
		MemberBespeakSyn memberBespeakSyn = new MemberBespeakSyn();
		memberBespeakSyn.setItemID(String.valueOf(getId()));
		if (operationId == null) {
			memberBespeakSyn.setOperationId("1");
		} else {
			memberBespeakSyn.setOperationId(operationId);
		}

		memberBespeakSyn.setAgentID(StringUtil.trimToEmpty(getBrokerId()));
		if (getGender() == 0) {
			memberBespeakSyn.setAppellation("先生");
		} else {
			memberBespeakSyn.setAppellation("女士");
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = getBespeakTimeStart()==null? "" : df.format(getBespeakTimeStart());
		String dateStr2 = getBespeakTimeEnd()==null? "" : df.format(getBespeakTimeEnd());
		memberBespeakSyn.setBookingDate(dateStr.length()< 11? "" : dateStr.substring(0, 11));
		if(dateStr.length()>11 && dateStr.length()>11)
			memberBespeakSyn.setBookingTime(dateStr.substring(11) + "-" + dateStr2.substring(11));
		else
			memberBespeakSyn.setBookingTime("");
		memberBespeakSyn.setBookingID(String.valueOf(getId()));
		if (getHouseType()==null || getHouseType() == 1L) {
			memberBespeakSyn.setBusinessType("买房");
		} else {
			memberBespeakSyn.setBusinessType("租房");
		}

		String timeStr = getCreatedTime()==null? "" : df.format(getCreatedTime());
		memberBespeakSyn.setCreateTime(timeStr);
		memberBespeakSyn.setHouseID(getHouseId());
		memberBespeakSyn.setMobilePhone(getContactsPhone());
		memberBespeakSyn.setOtherContactInfo("");
		memberBespeakSyn.setUserName(getContacts());
		if (getPlatMemberInfo() == null) {
			memberBespeakSyn.setMemberID("");
			memberBespeakSyn.setMemberName("");
		} else {
			if (getPlatMemberInfo()==null || getPlatMemberInfo().getId() == null) {
				memberBespeakSyn.setMemberID("");
			} else {
				memberBespeakSyn.setMemberID(String.valueOf(getPlatMemberInfo().getId()));
			}

			if (getPlatMemberInfo()==null || getPlatMemberInfo().getAccName() == null) {
				memberBespeakSyn.setMemberName("");
			} else {
				memberBespeakSyn.setMemberName(getPlatMemberInfo().getAccName());
			}
		}

		return memberBespeakSyn;
	}

}
