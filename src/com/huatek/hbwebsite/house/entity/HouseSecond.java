package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.framework.util.MemberBrowsedHouse;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Orientations;
import com.huatek.hbwebsite.common.entity.PriceChangeHistory;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.house.entity.ERPHousePicture;
import com.huatek.hbwebsite.house.entity.Evaluation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HouseSecond extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String showTags;
	private String erpId;
	private String houseNo;
	private String shelvingID;
	private String title;
	private String content;
	private Float price;
	private Float unitPrice;
	private Float area;
	private Integer shi;
	private Integer ting;
	private Integer wei;
	private Integer balcony;
	private Orientations orientations;
	private Integer vervicalLocation;
	private Integer storyCount;
	private String tags;
	private Integer storey;
	private String property;
	private Decorations decoration;
	private Integer sortIndex;
	private Usage usage;
	private String timeToSee;
	private String useageStatus;
	private Broker broker;
	private Community community;
	private Integer browsed;
	private Integer daikan;
	private Date decorateYear;
	private Integer primaryFlag;
	private Integer exclusiveFlag; // 独家房源标志
	private Integer focusFlag;
	private Integer qualityFlag;
	private Integer hasKey;
	private Integer schoolRegionFlag;
	private Integer subwayRegionFlag;
	private String lastupdatecontent;
	private Date lastModified;
	private Date lastSync;
	private String pictureUrl;
	private Integer haslift;
	private Integer deleteFlag;
	private double loanAssets; // 贷款月还款额
	private Integer loanRatios; // 贷款成数
	private Integer loanMonths; // 贷款月数
	private List<String> tagIdList;
	private Long collectId;
	private Integer appCount;
	private String ratioToCommunity;
	private String lastedThirtyPriceRatio;
	private String objectId;
	private float previousUnitPrice;
	private String houseUrl;				//额外加入的房源照片URL
	private Integer homepageRecommandFlag;
	private Date homepageRecommandTime;
	private String desc;
	private int toErpSynFlag;
	private List<PriceChangeHistory> priceChangeHistory;
	private List<ERPHousePicture> erpHousePicList;
	private List<Evaluation> evaluationList;
	// 房源在首页的排序序号
	private Integer housePriority;
	private Integer dishPriority;

	private Set<HouseAppraise> appraises = new HashSet<HouseAppraise>(0);; // 房源评价列表

	//推送百度房源数据记录
	private BaiDuHouseSecond baiDuHouseSecond;
	//二手房图片数量
	private Integer housePicSize;
	
	private List<Tag> houseTags = new ArrayList<Tag>();	//房源标签
	
	public Set<HouseAppraise> getAppraises() {
		return appraises;
	}

	public void setAppraises(Set<HouseAppraise> appraises) {
		this.appraises = appraises;
	}

	public HouseSecond() {
	}

	public HouseSecond(String shelvingID, Integer browsed, Date lastModified) {
		this.shelvingID = shelvingID;
		this.browsed = browsed;
		this.lastModified = lastModified;
	}

	public Date getHomepageRecommandTime() {
		return this.homepageRecommandTime;
	}

	public void setHomepageRecommandTime(Date homepageRecommandTime) {
		this.homepageRecommandTime = homepageRecommandTime;
	}

	public List<ERPHousePicture> getErpHousePicList() {
		return this.erpHousePicList;
	}

	public void setErpHousePicList(List<ERPHousePicture> erpHousePicList) {
		this.erpHousePicList = erpHousePicList;
	}

	public double getLoanAssets() {
		return this.loanAssets;
	}

	public void setLoanAssets(double loanAssets) {
		this.loanAssets = loanAssets;
	}

	public Integer getLoanRatios() {
		return loanRatios;
	}

	public void setLoanRatios(Integer loanRatios) {
		this.loanRatios = loanRatios;
	}

	public Integer getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}

	public List<Evaluation> getEvaluationList() {
		if(evaluationList==null)
			evaluationList = new ArrayList<Evaluation>();
		return this.evaluationList;
	}

	public void setEvaluationList(List<Evaluation> evaluationList) {
		this.evaluationList = evaluationList;
	}

	public List<PriceChangeHistory> getPriceChangeHistory() {
	if(priceChangeHistory==null)
			priceChangeHistory = new ArrayList<PriceChangeHistory>();		return this.priceChangeHistory;
	}

	public void setPriceChangeHistory(List<PriceChangeHistory> priceChangeHistory) {
		this.priceChangeHistory = priceChangeHistory;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setRatioToCommunity(String ratioToCommunity) {
		this.ratioToCommunity = ratioToCommunity;
	}

	public void setLastedThirtyPriceRatio(String lastedThirtyPriceRatio) {
		this.lastedThirtyPriceRatio = lastedThirtyPriceRatio;
	}

	public String getHouseUrl() {
		return this.houseUrl;
	}

	public void setHouseUrl(String houseUrl) {
		this.houseUrl = houseUrl;
	}

	public Integer getHaslift() {
		return this.haslift;
	}

	public void setHaslift(Integer haslift) {
		this.haslift = haslift;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
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
			finalContent = this.content.replaceFirst("<br>", "");
		} else {
			finalContent = this.content;
		}

		return finalContent;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getArea() {
		return this.area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Integer getShi() {
		return this.shi;
	}

	public void setShi(Integer shi) {
		this.shi = shi;
	}

	public Integer getTing() {
		return this.ting;
	}

	public void setTing(Integer ting) {
		this.ting = ting;
	}

	public Integer getWei() {
		return this.wei;
	}

	public void setWei(Integer wei) {
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

	public Integer getStorey() {
		return this.storey;
	}

	public void setStorey(Integer storey) {
		this.storey = storey;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Decorations getDecoration() {
		return this.decoration;
	}

	public void setDecoration(Decorations decoration) {
		this.decoration = decoration;
	}

	public Usage getUsage() {
		return this.usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public String getTimeToSee() {
		return this.timeToSee;
	}

	public void setTimeToSee(String timeToSee) {
		this.timeToSee = timeToSee;
	}

	public String getUseageStatus() {
		return this.useageStatus;
	}

	public void setUseageStatus(String useageStatus) {
		this.useageStatus = useageStatus;
	}

	public Broker getBroker() {
		return this.broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Integer getBrowsed() {
		return this.browsed;
	}

	public void setBrowsed(Integer browsed) {
		this.browsed = browsed;
	}

	public Integer getDaikan() {
		return this.daikan;
	}

	public void setDaikan(Integer daikan) {
		this.daikan = daikan;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<String> getTagIdList() {
		return this.tagIdList;
	}

	public void setTagIdList(List<String> tagIdList) {
		this.tagIdList = tagIdList;
	}

	public Long getCollectId() {
		return this.collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public Integer getAppCount() {
		return this.appCount;
	}

	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getErpId() {
		return this.erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public Date getDecorateYear() {
		return this.decorateYear;
	}

	public void setDecorateYear(Date decorateYear) {
		this.decorateYear = decorateYear;
	}

	public Integer getPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(Integer primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public Integer getExclusiveFlag() {
		return this.exclusiveFlag;
	}

	public void setExclusiveFlag(Integer exclusiveFlag) {
		this.exclusiveFlag = exclusiveFlag;
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

	public String getLastupdatecontent() {
		return this.lastupdatecontent;
	}

	public void setLastupdatecontent(String lastupdatecontent) {
		this.lastupdatecontent = lastupdatecontent;
	}

	public Integer getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	public Integer getHomepageRecommandFlag() {
		return this.homepageRecommandFlag;
	}

	public void setHomepageRecommandFlag(Integer homepageRecommandFlag) {
		this.homepageRecommandFlag = homepageRecommandFlag;
	}

	public MemberBrowsedHouse convertToMemberBrowseredHouse(HouseSecond secondHouse) {
		return new MemberBrowsedHouse(secondHouse.getShi(), secondHouse.getTing(), secondHouse.getCommunity().getId());
	}

	public String getShowTags() {
		return this.tags != null ? this.tags.replaceAll(",,", ",") : this.showTags;
	}

	public void setShowTags(String showTags) {
		this.showTags = showTags;
	}

	public Integer getBalcony() {
		return this.balcony;
	}

	public void setBalcony(Integer balcony) {
		this.balcony = balcony;
	}

	public float getRatioToCommunity() {
		if (this.community == null) {
			return 0.0F;
		} else if (this.community.getCurrentSHAvePrice() == 0.0F) {
			return 0.0F;
		} else {
			float f = (this.unitPrice.floatValue() - this.community.getCurrentSHAvePrice())
					/ this.community.getCurrentSHAvePrice() * 100.0F;
			BigDecimal b = new BigDecimal((double) f);
			return b.setScale(1, 4).floatValue();
		}
	}

	public float getPreviousUnitPrice() {
		return this.previousUnitPrice;
	}

	public void setPreviousUnitPrice(float previousUnitPrice) {
		this.previousUnitPrice = previousUnitPrice;
	}

	public float getLastedThirtyPriceRatio() {
		if (this.previousUnitPrice == 0.0F) {
			return 0.0F;
		} else {
			float f = (this.unitPrice.floatValue() - this.previousUnitPrice) / this.previousUnitPrice * 100.0F;
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

	public int getToErpSynFlag() {
		return this.toErpSynFlag;
	}

	public void setToErpSynFlag(int toErpSynFlag) {
		this.toErpSynFlag = toErpSynFlag;
	}

	public Integer getHousePriority() {
		return housePriority;
	}

	public void setHousePriority(Integer housePriority) {
		this.housePriority = housePriority;
	}

	public Integer getDishPriority() {
		return dishPriority;
	}

	public void setDishPriority(Integer dishPriority) {
		this.dishPriority = dishPriority;
	}

	public BaiDuHouseSecond getBaiDuHouseSecond() {
		return baiDuHouseSecond;
	}

	public void setBaiDuHouseSecond(BaiDuHouseSecond baiDuHouseSecond) {
		this.baiDuHouseSecond = baiDuHouseSecond;
	}

	public Integer getHousePicSize() {
		return housePicSize == null ? 0 : housePicSize;
	}

	public void setHousePicSize(Integer housePicSize) {
		this.housePicSize = housePicSize;
	}

	public List<Tag> getHouseTags() {
		return houseTags;
	}

	public void setHouseTags(List<Tag> houseTags) {
		this.houseTags = houseTags;
	}

}
