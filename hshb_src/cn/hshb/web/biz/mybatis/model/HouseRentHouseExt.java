package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class HouseRentHouseExt extends HouseRentHouse {
	private String communityName;	//小区名字
	private String countyName;		//城区名字
	private String cbdWsiteName;	//网站商圈名字
	private Integer pictureCount;	//图片数量
	private String cbdWebsiteId;	//网站商圈Id
	private String countyId;		//城区Id
	private String agentName;		//经纪人姓名
	private String telephone;		//经纪人电话
	
	private String photographPath;		//经纪人照片
	private String communityAddress;//小区所在地址	
	private Date BaiduHouseLastmod;	 //百度房源最后更新时间
	private Date BaiduHousePublishTime;//发布时间
	
	private String decorationName;	//装修
	private String orientationName; //朝向
	/**
	 * 房源类别 如:商用\公寓
	 */
	private String PropertyTypeName;	
	
	public String getOrientationName() {
		return orientationName;
	}
	public void setOrientationName(String orientationName) {
		this.orientationName = orientationName;
	}
	public String getDecorationName() {
		return decorationName;
	}
	public void setDecorationName(String decorationName) {
		this.decorationName = decorationName;
	}
	public String getPhotographPath() {
		return photographPath;
	}
	public void setPhotographPath(String photographPath) {
		this.photographPath = photographPath;
	}
	public String getCommunityAddress() {
		return communityAddress;
	}
	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress;
	}
	public Date getBaiduHouseLastmod() {
		return BaiduHouseLastmod;
	}
	public void setBaiduHouseLastmod(Date baiduHouseLastmod) {
		BaiduHouseLastmod = baiduHouseLastmod;
	}
	public Date getBaiduHousePublishTime() {
		return BaiduHousePublishTime;
	}
	public void setBaiduHousePublishTime(Date baiduHousePublishTime) {
		BaiduHousePublishTime = baiduHousePublishTime;
	}
	public String getPropertyTypeName() {
		return PropertyTypeName;
	}
	public void setPropertyTypeName(String propertyTypeName) {
		PropertyTypeName = propertyTypeName;
	}
	public Integer getPictureCount() {
		return pictureCount;
	}
	public void setPictureCount(Integer pictureCount) {
		this.pictureCount = pictureCount;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getCbdWebsiteId() {
		return cbdWebsiteId;
	}
	public void setCbdWebsiteId(String cbdWebsiteId) {
		this.cbdWebsiteId = cbdWebsiteId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getCbdWsiteName() {
		return cbdWsiteName;
	}
	public void setCbdWsiteName(String cbdWsiteName) {
		this.cbdWsiteName = cbdWsiteName;
	}

}
