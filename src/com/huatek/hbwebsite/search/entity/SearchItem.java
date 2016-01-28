package com.huatek.hbwebsite.search.entity;

import com.google.gson.annotations.Expose;
import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.search.entity.SearchField;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SearchItem extends BaseEntity {
	private static final long serialVersionUID = -6036865353055304652L;
	public static final int IS_PARENT = 1;
	public static final int NOT_PARENT = 0;
	@Expose
	private String searchLabel;
	@Expose
	private int isParent;
	@Expose
	private long parent;
	@Expose
	private int onlyShowPrivate;
	private String modulename;
	private Timestamp modifyTime;
	private String subEntityName;
	private int deleteFlag;
	@Expose
	private int isRange;
	@Expose
	private String colunmName;
	@Expose
	private int isHidden;
	@Expose
	private int isNeedInput;
	@Expose
	private String unitName;
	private int isFromTable;
	private String entityName;
	@Expose
	private int isPublic;
	@Expose
	private int hasSubValue;
	@Expose
	private String subValueLabel;
	private int sortindex;
	@Expose
	private List<SearchItem> subItems = new ArrayList<SearchItem>();
	@Expose
	private List<SearchField> searchFileds = new ArrayList<SearchField>();
	@Expose
	private int ismulty;

	public String getSearchLabel() {
		return this.searchLabel;
	}

	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
	}

	public int getIsParent() {
		return this.isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}

	public long getParent() {
		return this.parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public String getModulename() {
		return this.modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getIsRange() {
		return this.isRange;
	}

	public void setIsRange(int isRange) {
		this.isRange = isRange;
	}

	public String getColunmName() {
		return this.colunmName;
	}

	public void setColunmName(String colunmName) {
		this.colunmName = colunmName;
	}

	public int getIsHidden() {
		return this.isHidden;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public int getIsNeedInput() {
		return this.isNeedInput;
	}

	public void setIsNeedInput(int isNeedInput) {
		this.isNeedInput = isNeedInput;
	}

	public int getIsFromTable() {
		return this.isFromTable;
	}

	public void setIsFromTable(int isFromTable) {
		this.isFromTable = isFromTable;
	}

	public int getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	public int getHasSubValue() {
		return this.hasSubValue;
	}

	public void setHasSubValue(int hasSubValue) {
		this.hasSubValue = hasSubValue;
	}

	public List<SearchItem> getSubItems() {
		return this.subItems;
	}

	public void setSubItems(List<SearchItem> subItems) {
		this.subItems = subItems;
	}

	public List<SearchField> getSearchFileds() {
		return this.searchFileds;
	}

	public void setSearchFileds(List<SearchField> searchFileds) {
		this.searchFileds = searchFileds;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getSubEntityName() {
		return this.subEntityName;
	}

	public void setSubEntityName(String subEntityName) {
		this.subEntityName = subEntityName;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getSortindex() {
		return this.sortindex;
	}

	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}

	public int getIsmulty() {
		return this.ismulty;
	}

	public void setIsmulty(int ismulty) {
		this.ismulty = ismulty;
	}

	public int getOnlyShowPrivate() {
		return this.onlyShowPrivate;
	}

	public void setOnlyShowPrivate(int onlyShowPrivate) {
		this.onlyShowPrivate = onlyShowPrivate;
	}

	public String getSubValueLabel() {
		return this.subValueLabel;
	}

	public void setSubValueLabel(String subValueLabel) {
		this.subValueLabel = subValueLabel;
	}
}
