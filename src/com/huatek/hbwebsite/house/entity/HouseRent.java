package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.framework.util.MemberBrowsedHouse;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.Orientations;
import com.huatek.hbwebsite.common.entity.PriceChangeHistory;
import com.huatek.hbwebsite.common.entity.RentType;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.house.entity.ERPHousePicture;
import com.huatek.hbwebsite.house.entity.Evaluation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HouseRent extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String showTags;
	private String showFunitures;
	private String hourseNo;
	private String shelvingID;
	private String title;
	private String content;
	private float rentPrice;
	private float area;
	private Usage usage;
	private RentType rentType;
	private float previousRentPrice;
	private String ratioToCommunity;
	private String lastedThirtyPriceRatio;
	private int shi;
	private int ting;
	private int wei;
	private int yangtai;
	private Orientations orientations;
	private Integer vervicalLocation;
	private Integer storyCount;
	private String tags;
	private String payforWay;
	private Integer storey;
	private Decorations decoration;
	private String decorationTime;
	private String timeToSee;
	private String useageStatus;
	private int browsed;
	private int daikan;
	private int sortIndex;
	private Community community;
	private Broker broker;
	private String furniture;
	private int haslift;
	private int deleteFlag;
	private String erpId;
	private Long collectId;
	private Integer primaryFlag;
	private Integer exclusiveFlag; // 托管房标志
	private Integer focusFlag;
	private Integer qualityFlag;
	private Integer hasKey;
	private Integer schoolRegionFlag;
	private Integer subwayRegionFlag;
	private String lastupdatecontent;
	private String property;
	private Date lastModified;
	private Date lastSync;
	private String objectId;
	private String pictureUrl;
	private List<String> tagIdList;
	private Integer appCount;
	private List<String> furIdList;
	private String houseUrl;
	private int toErpSynFlag;
	private Integer homepageRecommandFlag;
	private Date homepageRecommandTime;
	private List<PriceChangeHistory> priceChangeHistory;
	private List<ERPHousePicture> erpHousePicList;
	private List<Evaluation> evaluationList;
	// 房源在首页的排序序号
	private Integer housePriority;

	private Set<HouseAppraise> appraises = new HashSet<HouseAppraise>(0); // 房源评价列表
	private List<Furniture> furnitureList; // 房源配套设施列表

	// 推送百度房源数据记录
	private BaiDuHouseRent baiDuHouseRentList;
	//租赁图片数量
	private Integer housePicSize;
	public String getUseageStatus() {
		return useageStatus;
	}

	public void setUseageStatus(String useageStatus) {
		this.useageStatus = useageStatus;
	}

	public List<Furniture> getFurnitureList() {
		return furnitureList;
	}

	public void setFurnitureList(List<Furniture> furnitureList) {
		this.furnitureList = furnitureList;
	}

	public Set<HouseAppraise> getAppraises() {
		return appraises;
	}

	public void setAppraises(Set<HouseAppraise> appraises) {
		this.appraises = appraises;
	}

	public void setHousePriority(int housePriority) {
		this.housePriority = housePriority;
	}

	public HouseRent() {
	}

	public HouseRent(String shelvingID, int browsed, Date lastModified) {
		this.shelvingID = shelvingID;
		this.browsed = browsed;
		this.lastModified = lastModified;
	}

	public Integer getExclusiveFlag() {
		return exclusiveFlag;
	}

	public void setExclusiveFlag(Integer exclusiveFlag) {
		this.exclusiveFlag = exclusiveFlag;
	}

	public int getToErpSynFlag() {
		return this.toErpSynFlag;
	}

	public void setToErpSynFlag(int toErpSynFlag) {
		this.toErpSynFlag = toErpSynFlag;
	}

	public Date getHomepageRecommandTime() {
		return this.homepageRecommandTime;
	}

	public void setHomepageRecommandTime(Date homepageRecommandTime) {
		this.homepageRecommandTime = homepageRecommandTime;
	}

	public List<Evaluation> getEvaluationList() {
		return this.evaluationList;
	}

	public void setEvaluationList(List<Evaluation> evaluationList) {
		this.evaluationList = evaluationList;
	}

	public List<ERPHousePicture> getErpHousePicList() {
		return this.erpHousePicList;
	}

	public void setErpHousePicList(List<ERPHousePicture> erpHousePicList) {
		this.erpHousePicList = erpHousePicList;
	}

	public int getYangtai() {
		return this.yangtai;
	}

	public void setYangtai(int yangtai) {
		this.yangtai = yangtai;
	}

	public void setRatioToCommunity(String ratioToCommunity) {
		this.ratioToCommunity = ratioToCommunity;
	}

	public void setLastedThirtyPriceRatio(String lastedThirtyPriceRatio) {
		this.lastedThirtyPriceRatio = lastedThirtyPriceRatio;
	}

	public List<PriceChangeHistory> getPriceChangeHistory() {
		return this.priceChangeHistory;
	}

	public void setPriceChangeHistory(List<PriceChangeHistory> priceChangeHistory) {
		this.priceChangeHistory = priceChangeHistory;
	}

	public String getHouseUrl() {
		return this.houseUrl;
	}

	public void setHouseUrl(String houseUrl) {
		this.houseUrl = houseUrl;
	}

	public int getHaslift() {
		return this.haslift;
	}

	public void setHaslift(int haslift) {
		this.haslift = haslift;
	}

	public String getHourseNo() {
		return this.hourseNo;
	}

	public void setHourseNo(String hourseNo) {
		this.hourseNo = hourseNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		String finalContent = "";
		if (this.content != null && this.content.startsWith("<br>")) {
			finalContent = this.content.replaceAll("<br>", "");
		} else {
			finalContent = this.content;
		}

		return finalContent;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRentPrice() {
		return this.rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public float getArea() {
		return this.area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public Usage getUsage() {
		return this.usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public RentType getRentType() {
		return this.rentType;
	}

	public void setRentType(RentType rentType) {
		this.rentType = rentType;
	}

	public int getShi() {
		return this.shi;
	}

	public void setShi(int shi) {
		this.shi = shi;
	}

	public int getTing() {
		return this.ting;
	}

	public void setTing(int ting) {
		this.ting = ting;
	}

	public int getWei() {
		return this.wei;
	}

	public void setWei(int wei) {
		this.wei = wei;
	}

	public Orientations getOrientations() {
		return this.orientations;
	}

	public void setOrientations(Orientations orientations) {
		this.orientations = orientations;
	}

	public Integer getVervicalLocation() {
		return this.vervicalLocation;
	}

	public void setVervicalLocation(Integer vervicalLocation) {
		this.vervicalLocation = vervicalLocation;
	}

	public Integer getStoryCount() {
		return this.storyCount;
	}

	public void setStoryCount(Integer storyCount) {
		this.storyCount = storyCount;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPayforWay() {
		return this.payforWay;
	}

	public void setPayforWay(String payforWay) {
		this.payforWay = payforWay;
	}

	public Integer getStorey() {
		return this.storey;
	}

	public void setStorey(Integer storey) {
		this.storey = storey;
	}

	public Decorations getDecoration() {
		return this.decoration;
	}

	public void setDecoration(Decorations decoration) {
		this.decoration = decoration;
	}

	public String getDecorationTime() {
		return this.decorationTime;
	}

	public void setDecorationTime(String decorationTime) {
		this.decorationTime = decorationTime;
	}

	public String getTimeToSee() {
		return this.timeToSee;
	}

	public void setTimeToSee(String timeToSee) {
		this.timeToSee = timeToSee;
	}

	public int getBrowsed() {
		return this.browsed;
	}

	public void setBrowsed(int browsed) {
		this.browsed = browsed;
	}

	public int getDaikan() {
		return this.daikan;
	}

	public void setDaikan(int daikan) {
		this.daikan = daikan;
	}

	public int getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public String getFurniture() {
		return this.furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	public int getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Long getCollectId() {
		return this.collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public List<String> getTagIdList() {
		return this.tagIdList;
	}

	public void setTagIdList(List<String> tagIdList) {
		this.tagIdList = tagIdList;
	}

	public Integer getAppCount() {
		return this.appCount;
	}

	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	public List<String> getFurIdList() {
		return this.furIdList;
	}

	public void setFurIdList(List<String> furIdList) {
		this.furIdList = furIdList;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public Integer getPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(Integer primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public Integer getFocusFlag() {
		return this.focusFlag;
	}

	public void setFocusFlag(Integer focusFlag) {
		this.focusFlag = focusFlag;
	}

	public Integer getQualityFlag() {
		return this.qualityFlag;
	}

	public void setQualityFlag(Integer qualityFlag) {
		this.qualityFlag = qualityFlag;
	}

	public Integer getHasKey() {
		return this.hasKey;
	}

	public void setHasKey(Integer hasKey) {
		this.hasKey = hasKey;
	}

	public Integer getSchoolRegionFlag() {
		return this.schoolRegionFlag;
	}

	public void setSchoolRegionFlag(Integer schoolRegionFlag) {
		this.schoolRegionFlag = schoolRegionFlag;
	}

	public Integer getSubwayRegionFlag() {
		return this.subwayRegionFlag;
	}

	public void setSubwayRegionFlag(Integer subwayRegionFlag) {
		this.subwayRegionFlag = subwayRegionFlag;
	}

	public String getLastupdatecontent() {
		return this.lastupdatecontent;
	}

	public void setLastupdatecontent(String lastupdatecontent) {
		this.lastupdatecontent = lastupdatecontent;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
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

	public Integer getHomepageRecommandFlag() {
		return this.homepageRecommandFlag;
	}

	public void setHomepageRecommandFlag(Integer homepageRecommandFlag) {
		this.homepageRecommandFlag = homepageRecommandFlag;
	}

	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	public String getShowTags() {
		return this.tags != null ? this.tags.replaceAll(",,", ",") : this.showTags;
	}

	public void setShowTags(String showTags) {
		this.showTags = showTags;
	}

	public String getShowFunitures() {
		return this.furniture != null ? this.furniture.replaceAll(",,", ",") : this.showFunitures;
	}

	public void setShowFunitures(String showFunitures) {
		this.showFunitures = showFunitures;
	}

	public MemberBrowsedHouse convertToMemberBrowseredHouse(HouseRent rentHouse) {
		return new MemberBrowsedHouse(Integer.valueOf(rentHouse.getShi()), Integer.valueOf(rentHouse.getTing()),
				rentHouse.getCommunity().getId());
	}

	public float getPreviousRentPrice() {
		return this.previousRentPrice;
	}

	public void setPreviousRentPrice(float previousRentPrice) {
		this.previousRentPrice = previousRentPrice;
	}

	public float getRatioToCommunity() {
		if (this.community == null) {
			return 0.0F;
		} else if (this.community.getCurrentSHAvePrice() == 0.0F) {
			return 0.0F;
		} else {
			float f = 0.0F;
			if (this.community.getCurrentRHAvePrice() != 0.0F) {
				f = (this.rentPrice - this.community.getCurrentRHAvePrice()) / this.community.getCurrentRHAvePrice()
						* 100.0F;
			}

			BigDecimal b = new BigDecimal((double) f);
			return b.setScale(1, 4).floatValue();
		}
	}

	public float getLastedThirtyPriceRatio() {
		if (this.previousRentPrice == 0.0F) {
			return 0.0F;
		} else {
			float f = (this.rentPrice - this.previousRentPrice) / this.previousRentPrice * 100.0F;
			BigDecimal b = new BigDecimal((double) f);
			return b.setScale(1, 4).floatValue();
		}
	}

	public String getShelvingID() {
		return this.shelvingID;
	}

	public void setShelvingID(String shelvingID) {
		this.shelvingID = shelvingID;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Integer getHousePriority() {
		return housePriority;
	}

	public void setHousePriority(Integer housePriority) {
		this.housePriority = housePriority;
	}

	public BaiDuHouseRent getBaiDuHouseRentList() {
		return baiDuHouseRentList;
	}

	public void setBaiDuHouseRentList(BaiDuHouseRent baiDuHouseRentList) {
		this.baiDuHouseRentList = baiDuHouseRentList;
	}

	public Integer getHousePicSize() {
		return housePicSize == null ? 0 : housePicSize;
	}

	public void setHousePicSize(Integer housePicSize) {
		this.housePicSize = housePicSize;
	}

}
