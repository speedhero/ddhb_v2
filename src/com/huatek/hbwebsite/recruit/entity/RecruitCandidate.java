package com.huatek.hbwebsite.recruit.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.recruit.entity.RecruitPosition;
import java.util.Date;

public class RecruitCandidate extends BaseEntity {
	private static final long serialVersionUID = 2267223257110731919L;
	public static final int MAN = 1;
	public static final int WOMAN = 0;
	private String name;
	private Integer gender;
	private Date birthday;
	private String yearworking;
	private String degree;
	private String email;
	private String telephone;
	private RecruitPosition appliedPosition;
	private String resume;
	private Date applyTime;
	private String workHistory;
	private Integer deleteFlag;
	private String date;
	private String year;
	private String month;
	private String day;

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getYearworking() {
		return this.yearworking;
	}

	public void setYearworking(String yearworking) {
		this.yearworking = yearworking;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public RecruitPosition getAppliedPosition() {
		return this.appliedPosition;
	}

	public void setAppliedPosition(RecruitPosition appliedPosition) {
		this.appliedPosition = appliedPosition;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getWorkHistory() {
		return this.workHistory;
	}

	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}
}
