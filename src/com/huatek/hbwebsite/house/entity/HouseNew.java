package com.huatek.hbwebsite.house.entity;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.house.entity.CityForNewHouse;
import com.huatek.hbwebsite.house.entity.CountyForNewHouse;
import com.huatek.hbwebsite.house.entity.RemainTime;

import java.util.Calendar;
import java.util.Date;

public class HouseNew extends BaseEntity {
	private static final long serialVersionUID = 251169676740694880L;
	private String projectNo;
	private Integer averagePrice;
	private Date publishDate;
	private String adv;
	private Integer minPrice;
	private Date createDate;
	private Date endDate;
	private String pictureUrl;
	private String hotline;
	private String buildingName;
	private String buildingAddress;
	private String developer;
	private String recommandContent;
	private String sellingPoint;
	private String salesPromotion;
	private String buildingIntroduce;
	private CityForNewHouse city;
	private CountyForNewHouse area;
	private Decorations decoration;
	private Integer homepageRecommandFlag;
	private Date homepageRecommandTime;
	private String facilityName;
	private boolean enableFlag;
	private Integer deleteFlag;
	private RemainTime remainDays;
	private long entrants;


	private Integer housePriority;
	private Integer dishPriority;
	
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

	public Date getHomepageRecommandTime() {
		return this.homepageRecommandTime;
	}

	public void setHomepageRecommandTime(Date homepageRecommandTime) {
		this.homepageRecommandTime = homepageRecommandTime;
	}

	public long getEntrants() {
		return this.entrants;
	}

	public void setEntrants(long entrants) {
		this.entrants = entrants;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public Integer getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public String getAdv() {
		return this.adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getFacilityName() {
		return this.facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Integer getAveragePrice() {
		return this.averagePrice;
	}

	public void setAveragePrice(Integer averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getHotline() {
		return this.hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAddress() {
		return this.buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getRecommandContent() {
		return this.recommandContent;
	}

	public void setRecommandContent(String recommandContent) {
		this.recommandContent = recommandContent;
	}

	public String getSellingPoint() {
		return this.sellingPoint;
	}

	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}

	public String getSalesPromotion() {
		return this.salesPromotion;
	}

	public void setSalesPromotion(String salesPromotion) {
		this.salesPromotion = salesPromotion;
	}

	public String getBuildingIntroduce() {
		return this.buildingIntroduce;
	}

	public void setBuildingIntroduce(String buildingIntroduce) {
		this.buildingIntroduce = buildingIntroduce;
	}

	public CityForNewHouse getCity() {
		return this.city;
	}

	public void setCity(CityForNewHouse city) {
		this.city = city;
	}

	public CountyForNewHouse getArea() {
		return this.area;
	}

	public void setArea(CountyForNewHouse area) {
		this.area = area;
	}

	public Decorations getDecoration() {
		return this.decoration;
	}

	public void setDecoration(Decorations decoration) {
		this.decoration = decoration;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getHomepageRecommandFlag() {
		return this.homepageRecommandFlag;
	}

	public void setHomepageRecommandFlag(Integer homepageRecommandFlag) {
		this.homepageRecommandFlag = homepageRecommandFlag;
	}

	public boolean getEnableFlag() {
		RemainTime remainTime = this.getRemainDays();
		return ((remainTime.getDay() + remainTime.getHour() 
						+ remainTime.getMin() + remainTime.getSec())>0 && this.deleteFlag==0);
	}

	public RemainTime getRemainDays() {
		RemainTime tmp = new RemainTime();
		if (this.getEndDate() == null) {
			return tmp;
		} else {
			int endDate = (int) (this.getEndDate().getTime() / 1000L / 60L / 60L / 24L);
			Calendar calendar = Calendar.getInstance();
			int dateNow = (int) (calendar.getTime().getTime() / 1000L / 60L / 60L / 24L);
			int time = endDate - dateNow;
			if (time < 0) {
				return tmp;
			} else {
				Long endDateTmp = Long.valueOf(this.getEndDate().getTime());
				Long dateNowTmp = Long.valueOf(calendar.getTime().getTime());
				Long tempSec = Long.valueOf(endDateTmp.longValue() - dateNowTmp.longValue());
				long days = tempSec.longValue() / 86400000L;
				long hours = tempSec.longValue() % 86400000L / 3600000L;
				long minutes = tempSec.longValue() % 3600000L / 60000L;
				long seconds = tempSec.longValue() % 60000L / 1000L;
				tmp.setDay(Integer.valueOf((int) days));
				tmp.setHour(Integer.valueOf((int) hours));
				tmp.setMin(Integer.valueOf((int) minutes));
				tmp.setSec(Integer.valueOf((int) seconds));
				return tmp;
			}
		}
	}

	public void setRemainDays(RemainTime remainDays) {
		this.remainDays = remainDays;
	}
}
