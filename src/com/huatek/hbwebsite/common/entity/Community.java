package com.huatek.hbwebsite.common.entity;

import com.google.gson.annotations.Expose;
import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CBD;
import com.huatek.hbwebsite.common.entity.Store;
import com.huatek.hbwebsite.common.entity.Usage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.FetchType;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Formula;

public class Community extends BaseEntity {
	private static final long serialVersionUID = -2728416751337980162L;
	private String communityNo;
	@Expose
	private String communityName;
	private float floorArea;
	private String pinyin;
	private Date startSaleDate;
	private float landScaping;
//	private Store nearStore;	//改成只保存门店ID，允许多个ID用分号分隔
	private String title;
	private String communityAddress;
	private Date buildYear;
	private String developer;
	private String propertyManagement;
	private String propertyExpense;
	private Usage propertyType;
	private String location;
	private Integer communityArea;
	private Float plotRatio;
	private Integer shCount;
	private Integer rentCount;
	private Date lastModified;
	private Date lastSync;
	private int tents;
	private String structType;
	private float currentSHAvePrice;
	private float currentRHAvePrice;
	private String introduction;
	private String parkingCount;
	private Integer houseCount;
	private Area region;
	private String inital;
	private List<Broker> borderList;
	private CBD cbd;
	private Integer deleteFlag;
	private String erpId;
	private int pictureCount;
	private int sortIndex;
	private Long collectId;
	private String communityUrl;
	private int communityPicCount;
	private double aroundLastYear;
	private double compareWithLastMonth;
	private double aroundLastYearrh;
	private double compareWithLastMonthrh;
	private String pictureUrl;
	private String nearStoreID;    //最近门店ID，允许多个
	private List<Store> nearStoreList = new ArrayList<Store>(); //最近门店列表
	private Set<HousePic> pictures = new HashSet<HousePic>();	//小区照片数	
	//向百度推送数据
	//周边设施
	private List<String> communityFacilities;
	//推送百度房源数据记录
	private BaiDuCommunity baiDuCommuntiy;
	
	public String getCommunityUrl() {
		return this.communityUrl;
	}

	public void setCommunityUrl(String communityUrl) {
		this.communityUrl = communityUrl;
	}

	public Long getCollectId() {
		return this.collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public float getLandScaping() {
		return this.landScaping;
	}

	public void setLandScaping(float landScaping) {
		this.landScaping = landScaping;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommunityAddress() {
		return this.communityAddress;
	}

	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress;
	}

	public Date getBuildYear() {
		return this.buildYear;
	}

	public void setBuildYear(Date buildYear) {
		this.buildYear = buildYear;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getPropertyManagement() {
		return this.propertyManagement;
	}

	public void setPropertyManagement(String propertyManagement) {
		this.propertyManagement = propertyManagement;
	}

	public String getPropertyExpense() {
		return this.propertyExpense;
	}

	public void setPropertyExpense(String propertyExpense) {
		this.propertyExpense = propertyExpense;
	}

	public Usage getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(Usage propertyType) {
		this.propertyType = propertyType;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCommunityArea() {
		return this.communityArea;
	}

	public void setCommunityArea(Integer communityArea) {
		this.communityArea = communityArea;
	}

	public Float getPlotRatio() {
		return this.plotRatio;
	}

	public void setPlotRatio(Float plotRatio) {
		this.plotRatio = plotRatio;
	}

	public Integer getShCount() {
		return this.shCount;
	}

	public void setShCount(Integer shCount) {
		this.shCount = shCount;
	}

	public Integer getRentCount() {
		return this.rentCount;
	}

	public void setRentCount(Integer rentCount) {
		this.rentCount = rentCount;
	}

	public String getParkingCount() {
		return this.parkingCount;
	}

	public void setParkingCount(String parkingCount) {
		this.parkingCount = parkingCount;
	}

	public Integer getHouseCount() {
		return this.houseCount;
	}

	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}

	public Area getRegion() {
		return this.region;
	}

	public void setRegion(Area region) {
		this.region = region;
	}

	public String getInital() {
		return this.inital;
	}

	public void setInital(String inital) {
		this.inital = inital;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getStartSaleDate() {
		return this.startSaleDate;
	}

	public void setStartSaleDate(Date startSaleDate) {
		this.startSaleDate = startSaleDate;
	}

	public CBD getCbd() {
		return this.cbd;
	}

	public void setCbd(CBD cbd) {
		this.cbd = cbd;
	}

	public List<Broker> getBorderList() {
		return this.borderList;
	}

	public void setBorderList(List<Broker> borderList) {
		this.borderList = borderList;
	}

	public int getPictureCount() {
		return this.pictureCount;
	}

	public void setPictureCount(int pictureCount) {
		this.pictureCount = pictureCount;
	}

	public int getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public float getFloorArea() {
		return this.floorArea;
	}

	public void setFloorArea(float floorArea) {
		this.floorArea = floorArea;
	}

	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastSync() {
		return this.lastSync;
	}

	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	public int getTents() {
		return this.tents;
	}

	public void setTents(int tents) {
		this.tents = tents;
	}

	public String getStructType() {
		return this.structType;
	}

	public void setStructType(String structType) {
		this.structType = structType;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getCommunityNo() {
		return this.communityNo;
	}

	public void setCommunityNo(String communityNo) {
		this.communityNo = communityNo;
	}

	public float getCurrentSHAvePrice() {
		return this.currentSHAvePrice;
	}

	public void setCurrentSHAvePrice(float currentSHAvePrice) {
		this.currentSHAvePrice = currentSHAvePrice;
	}

	public int getCommunityPicCount() {
		return this.communityPicCount;
	}

	public void setCommunityPicCount(int communityPicCount) {
		this.communityPicCount = communityPicCount;
	}

	public float getCurrentRHAvePrice() {
		return this.currentRHAvePrice;
	}

	public void setCurrentRHAvePrice(float currentRHAvePrice) {
		this.currentRHAvePrice = currentRHAvePrice;
	}

	public double getAroundLastYearrh() {
		return this.aroundLastYearrh;
	}

	public void setAroundLastYearrh(double aroundLastYearrh) {
		this.aroundLastYearrh = aroundLastYearrh;
	}

	public double getCompareWithLastMonthrh() {
		return this.compareWithLastMonthrh;
	}

	public void setCompareWithLastMonthrh(double compareWithLastMonthrh) {
		this.compareWithLastMonthrh = compareWithLastMonthrh;
	}

	public double getAroundLastYear() {
		return this.aroundLastYear;
	}

	public void setAroundLastYear(double aroundLastYear) {
		this.aroundLastYear = aroundLastYear;
	}

	public double getCompareWithLastMonth() {
		return this.compareWithLastMonth;
	}

	public void setCompareWithLastMonth(double compareWithLastMonth) {
		this.compareWithLastMonth = compareWithLastMonth;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

//public Store getNearStore() {
//return this.nearStore;
//}
//
//public void setNearStore(Store nearStore) {
//this.nearStore = nearStore;
//}

	public String getNearStoreID() {
		return nearStoreID;
	}

	public void setNearStoreID(String nearStoreID) {
		this.nearStoreID = nearStoreID;
	}

	public List<Store> getNearStoreList() {
		return nearStoreList;
	}

	public void setNearStoreList(List<Store> nearStoreList) {
		this.nearStoreList = nearStoreList;
	}

	public BaiDuCommunity getBaiDuCommuntiy() {
		return baiDuCommuntiy;
	}

	public void setBaiDuCommuntiy(BaiDuCommunity baiDuCommuntiy) {
		this.baiDuCommuntiy = baiDuCommuntiy;
	}

	public List<String> getCommunityFacilities() {
		return communityFacilities;
	}

	public void setCommunityFacilities(List<String> communityFacilities) {
		this.communityFacilities = communityFacilities;
	}	
	
}
