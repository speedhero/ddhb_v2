package com.huatek.hbwebsite.member.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.basemanagement.entity.PlatImage;
import com.huatek.hbwebsite.member.entity.IntegralTable;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PlatMemberInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 8116089431017781686L;
	private String nickName;
	private String passWd;
	private String realName;
	private String accName;
	private String mobilephone;
	private int mobilephoneValidatedFlag;
	private Integer imageId;
	private Integer sex;
	private String status;
	private String picPath;
	private String emailAddress;
	private int emailValidationFlag;
	private String description;
	private String identityCard;
	private String weburl;
	private String regIp;
	private Date lastLogin;
	private Date lastTime;
	private String lastIp;
	private Integer loginNum;
	private String uuid;
	private Date regTime;
	private Date createdTime;
	private Set<PlatImage> platImages = new HashSet(0);
	private String confirmPassWd;
	private String verifyCode;
	private String ifRemember;
	private String checkbox;
	private String userOrPasswd;
	private String oldPasswd;
	private String newPasswd;
	private String confirmNewPasswd;
	private String accountNoActive;
	private String acctNameInactive;
	private long wrongLoginTime;
	private int wrongCount;
	private int isFirst;
	private IntegralTable score;

	private Integer exported;	//是否已导出
	
	public int getIsFirst() {
		return this.isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getWeburl() {
		return this.weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getRegIp() {
		return this.regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getLastIp() {
		return this.lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Integer getLoginNum() {
		return this.loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Set<PlatImage> getPlatImages() {
		return this.platImages;
	}

	public void setPlatImages(Set<PlatImage> platImages) {
		this.platImages = platImages;
	}

	public String getConfirmPassWd() {
		return this.confirmPassWd;
	}

	public void setConfirmPassWd(String confirmPassWd) {
		this.confirmPassWd = confirmPassWd;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getIfRemember() {
		return this.ifRemember;
	}

	public void setIfRemember(String ifRemember) {
		this.ifRemember = ifRemember;
	}

	public String getUserOrPasswd() {
		return this.userOrPasswd;
	}

	public void setUserOrPasswd(String userOrPasswd) {
		this.userOrPasswd = userOrPasswd;
	}

	public String getOldPasswd() {
		return this.oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return this.newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getConfirmNewPasswd() {
		return this.confirmNewPasswd;
	}

	public void setConfirmNewPasswd(String confirmNewPasswd) {
		this.confirmNewPasswd = confirmNewPasswd;
	}

	public String getAccountNoActive() {
		return this.accountNoActive;
	}

	public void setAccountNoActive(String accountNoActive) {
		this.accountNoActive = accountNoActive;
	}

	public String getAcctNameInactive() {
		return this.acctNameInactive;
	}

	public void setAcctNameInactive(String acctNameInactive) {
		this.acctNameInactive = acctNameInactive;
	}

	public String getPassWd() {
		return this.passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public int getMobilephoneValidatedFlag() {
		return this.mobilephoneValidatedFlag;
	}

	public void setMobilephoneValidatedFlag(int mobilephoneValidatedFlag) {
		this.mobilephoneValidatedFlag = mobilephoneValidatedFlag;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getEmailValidationFlag() {
		return this.emailValidationFlag;
	}

	public void setEmailValidationFlag(int emailValidationFlag) {
		this.emailValidationFlag = emailValidationFlag;
	}

	public String getAccName() {
		return this.accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IntegralTable getScore() {
		return this.score;
	}

	public void setScore(IntegralTable score) {
		this.score = score;
	}

	public long getWrongLoginTime() {
		return this.wrongLoginTime;
	}

	public void setWrongLoginTime(long wrongLoginTime) {
		this.wrongLoginTime = wrongLoginTime;
	}

	public int getWrongCount() {
		return this.wrongCount;
	}

	public void setWrongCount(int wrongCount) {
		this.wrongCount = wrongCount;
	}

	public Integer getExported() {
		return exported;
	}

	public void setExported(Integer exported) {
		this.exported = exported;
	}
}
