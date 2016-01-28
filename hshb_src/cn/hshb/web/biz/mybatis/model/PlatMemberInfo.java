package cn.hshb.web.biz.mybatis.model;

import com.huatek.hbwebsite.basemanagement.entity.PlatImage;
import com.huatek.hbwebsite.member.entity.IntegralTable;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PlatMemberInfo extends MemberInfo implements Serializable {
	private static final long serialVersionUID = 8116089431017781686L;
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
	private Long wrongLoginTime;
	private IntegralTable score;

	private Integer exported;	//是否已导出

	public Set<PlatImage> getPlatImages() {
		return platImages;
	}

	public void setPlatImages(Set<PlatImage> platImages) {
		this.platImages = platImages;
	}

	public String getConfirmPassWd() {
		return confirmPassWd;
	}

	public void setConfirmPassWd(String confirmPassWd) {
		this.confirmPassWd = confirmPassWd;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getIfRemember() {
		return ifRemember;
	}

	public void setIfRemember(String ifRemember) {
		this.ifRemember = ifRemember;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getUserOrPasswd() {
		return userOrPasswd;
	}

	public void setUserOrPasswd(String userOrPasswd) {
		this.userOrPasswd = userOrPasswd;
	}

	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getConfirmNewPasswd() {
		return confirmNewPasswd;
	}

	public void setConfirmNewPasswd(String confirmNewPasswd) {
		this.confirmNewPasswd = confirmNewPasswd;
	}

	public String getAccountNoActive() {
		return accountNoActive;
	}

	public void setAccountNoActive(String accountNoActive) {
		this.accountNoActive = accountNoActive;
	}

	public String getAcctNameInactive() {
		return acctNameInactive;
	}

	public void setAcctNameInactive(String acctNameInactive) {
		this.acctNameInactive = acctNameInactive;
	}

	public Long getWrongLoginTime() {
		return wrongLoginTime;
	}

	public void setWrongLoginTime(Long l) {
		this.wrongLoginTime = l;
	}

	public IntegralTable getScore() {
		return score;
	}

	public void setScore(IntegralTable score) {
		this.score = score;
	}

	public Integer getExported() {
		return exported;
	}

	public void setExported(Integer exported) {
		this.exported = exported;
	}
	
	

}
