package com.huatek.hbwebsite.basemanagement.entity;

import com.huatek.base.entity.BaseEntity;

public class BaseRegion extends BaseEntity {
	private static final long serialVersionUID = -2483293840908361591L;
	private String selectedProvince;
	private String selectedCity;
	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;
	private String districtCode;
	private String districtName;
	private Integer regionType;
	private Integer districtType;

	public String getSelectedProvince() {
		return this.selectedProvince;
	}

	public void setSelectedProvince(String selectedProvince) {
		this.selectedProvince = selectedProvince;
	}

	public String getSelectedCity() {
		return this.selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Integer getRegionType() {
		return this.regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	public Integer getDistrictType() {
		return this.districtType;
	}

	public void setDistrictType(Integer districtType) {
		this.districtType = districtType;
	}

	public String getRegionFullName() {
		return this.provinceName + "/" + this.cityName + "/" + this.districtName;
	}
}
