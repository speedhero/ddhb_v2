package com.huatek.hbwebsite.common.entity;

import com.huatek.base.entity.BaseEntity;
import java.util.Date;

public class HouseRate extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private int rateType;
	private int deleteFlag;
	private double rateVal;
	private Date rateDate;

	public int getRateType() {
		return this.rateType;
	}

	public void setRateType(int rateType) {
		this.rateType = rateType;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public double getRateVal() {
		return this.rateVal;
	}

	public void setRateVal(double rateVal) {
		this.rateVal = rateVal;
	}

	public Date getRateDate() {
		return this.rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}
}
