package com.huatek.hbwebsite.house.entity;

public class RemainTime {
	private Integer day = Integer.valueOf(0);
	private Integer hour = Integer.valueOf(0);
	private Integer min = Integer.valueOf(0);
	private Integer sec = Integer.valueOf(0);

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		if (day.intValue() >= 0) {
			this.day = day;
		}

	}

	public Integer getHour() {
		return this.hour;
	}

	public void setHour(Integer hour) {
		if (hour.intValue() >= 0) {
			this.hour = hour;
		}

	}

	public Integer getMin() {
		return this.min;
	}

	public void setMin(Integer min) {
		if (min.intValue() >= 0) {
			this.min = min;
		}

	}

	public Integer getSec() {
		return this.sec;
	}

	public void setSec(Integer sec) {
		if (sec.intValue() >= 0) {
			this.sec = sec;
		}

	}
}
