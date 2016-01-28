package com.huatek.hbwebsite.search.entity;

import com.google.gson.annotations.Expose;
import com.huatek.base.entity.BaseEntity;
import java.util.List;
import java.util.Map;

public class SearchField extends BaseEntity {
	private static final long serialVersionUID = -1672605241737210630L;
	@Expose
	private String fieldName;
	@Expose
	private String fieldValue;
	@Expose
	private String maxFieldValue;
	@Expose
	private String minFieldValue;
	@Expose
	private int hasSubValue;
	@Expose
	private String fieldColumnName;
	private long searchItem;
	@Expose
	private Map<String, List<SearchField>> subFiledsMap;
	private int deleteFlag;
	@Expose
	private String bgColor;
	@Expose 
	private String schoolCountryId;
	
	public SearchField() {
	}

	public SearchField(String fieldName, String fieldValue) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getMaxFieldValue() {
		return this.maxFieldValue;
	}

	public void setMaxFieldValue(String maxFieldValue) {
		this.maxFieldValue = maxFieldValue;
	}

	public String getMinFieldValue() {
		return this.minFieldValue;
	}

	public void setMinFieldValue(String minFieldValue) {
		this.minFieldValue = minFieldValue;
	}

	public int getHasSubValue() {
		return this.hasSubValue;
	}

	public void setHasSubValue(int hasSubValue) {
		this.hasSubValue = hasSubValue;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public long getSearchItem() {
		return this.searchItem;
	}

	public void setSearchItem(long searchItem) {
		this.searchItem = searchItem;
	}

	public Map<String, List<SearchField>> getSubFiledsMap() {
		return this.subFiledsMap;
	}

	public void setSubFiledsMap(Map<String, List<SearchField>> subFiledsMap) {
		this.subFiledsMap = subFiledsMap;
	}

	public String getFieldColumnName() {
		return this.fieldColumnName;
	}

	public void setFieldColumnName(String fieldColumnName) {
		this.fieldColumnName = fieldColumnName;
	}

	public String getBgColor() {
		return this.bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getSchoolCountryId() {
		return schoolCountryId;
	}

	public void setSchoolCountryId(String schoolCountryId) {
		this.schoolCountryId = schoolCountryId;
	}
	
}
