//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:46:01 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.community;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;
import cn.hshb.web.partner.baidu.entity.houseSecond.THouseType;

import com.huatek.hbwebsite.common.entity.Community;


/**
 * <p>tRealEstate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tRealEstate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="domain" type="{}tDomain"/&gt;
 *         &lt;element name="type" type="{}tType"/&gt;
 *         &lt;element name="name" type="{}tName"/&gt;
 *         &lt;element name="lastmod" type="{}tLastmod"/&gt;
 *         &lt;element name="publishTime" type="{}tPublishTime" minOccurs="0"/&gt;
 *         &lt;element name="changefreq" type="{}tChangefreq" minOccurs="0"/&gt;
 *         &lt;element name="ResidenceName" type="{}tResidenceName"/&gt;
 *         &lt;element name="Url" type="{}t1Url"/&gt;
 *         &lt;element name="PropertyCompany" type="{}tPropertyCompany"/&gt;
 *         &lt;element name="HouseType" type="{}tHouseType"/&gt;
 *         &lt;element name="HouseAge" type="{}tHouseAge"/&gt;
 *         &lt;element name="SchoolDistrict" type="{}tSchoolDistrict"/&gt;
 *         &lt;element name="Developers" type="{}tDevelopers"/&gt;
 *         &lt;element name="Introduce" type="{}tIntroduce"/&gt;
 *         &lt;element name="Price" type="{}tPrice"/&gt;
 *         &lt;element name="VolumeRate" type="{}tVolumeRate"/&gt;
 *         &lt;element name="TotalConArea" type="{}tTotalConArea"/&gt;
 *         &lt;element name="TotalHoushold" type="{}tTotalHoushold" minOccurs="0"/&gt;
 *         &lt;element name="GreenRate" type="{}tGreenRate"/&gt;
 *         &lt;element name="TotalBuilding" type="{}tTotalBuilding" minOccurs="0"/&gt;
 *         &lt;element name="SurroundFacility" type="{}tSurroundFacility" maxOccurs="unbounded"/&gt;
 *         &lt;element name="BadSurroundFacility" type="{}tBadSurroundFacility" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="onsale_num" type="{}tOnsale_num" minOccurs="0"/&gt;
 *         &lt;element name="onrent_num" type="{}tOnrent_num" minOccurs="0"/&gt;
 *         &lt;element name="Place" type="{}tPlace"/&gt;
 *         &lt;element name="SubwayInfo" type="{}tSubwayInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tRealEstate", propOrder = {
    "domain",
    "type",
    "name",
    "lastmod",
    "publishTime",
    "source",				//新增 来源
    "changefreq",
    "residenceName",
    "url",
    "propertyCompany",
    "houseType",
    "houseAge",
    "schoolDistrict",
    "developers",
    "introduce",
    "price",
    "volumeRate",
    "totalConArea",
    "totalHoushold",
    "greenRate",
    "totalBuilding",
    "surroundFacility",
    "badSurroundFacility",
    "onsaleNum",
    "onrentNum",
    "place",
    "subwayInfo",
    "image"			//小区图片
})
public class TRealEstate extends BaseEntity {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TDomain domain;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TType type;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String lastmod;
    @XmlSchemaType(name = "anySimpleType")
    protected String publishTime;
    @XmlSchemaType(name= "string")					//新增来源
    protected String source;
    @XmlSchemaType(name = "string")
    protected TChangefreq changefreq;
    @XmlElement(name = "ResidenceName", required = true)
    protected String residenceName;
    @XmlElement(name = "Url", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;
    @XmlElement(name = "PropertyCompany", required = true)
    protected String propertyCompany;
    @XmlElement(name = "HouseType", required = true)
    protected String houseType;
    @XmlElement(name = "HouseAge", required = true)
    protected String houseAge;
    @XmlElement(name = "SchoolDistrict", required = true)
    @XmlSchemaType(name = "string")
    protected TSchoolDistrict schoolDistrict;
    @XmlElement(name = "Developers", required = true)
    protected String developers;
    @XmlElement(name = "Introduce", required = true)
    protected String introduce;
    @XmlElement(name = "Price", required = true)
    protected BigInteger price;
    @XmlElement(name = "VolumeRate", required = true)
    protected BigDecimal volumeRate;
    @XmlElement(name = "TotalConArea", required = true)
    protected BigInteger totalConArea;
    @XmlElement(name = "TotalHoushold")
    protected BigInteger totalHoushold;
    @XmlElement(name = "GreenRate", required = true)
    protected BigInteger greenRate;
    @XmlElement(name = "TotalBuilding")
    protected BigInteger totalBuilding;
    @XmlElement(name = "SurroundFacility", required = true)
    protected List<String> surroundFacility;
    @XmlElement(name = "BadSurroundFacility")
    protected List<String> badSurroundFacility;
    @XmlElement(name = "onsale_num")
    protected BigInteger onsaleNum;
    @XmlElement(name = "onrent_num")
    protected BigInteger onrentNum;
    @XmlElement(name = "Place", required = true)
    protected TPlace place;
    @XmlElement(name = "SubwayInfo")
    protected List<TSubwayInfo> subwayInfo;
    @XmlElement(name = "Image")
    protected List<String> image;				//小区图片
    /**
     * 新增来源
     */
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getImage() {
		if(image == null)
			image = new ArrayList<String>();
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	/**
     * 获取domain属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TDomain }
     *     
     */
    public TDomain getDomain() {
        return domain;
    }

    /**
     * 设置domain属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TDomain }
     *     
     */
    public void setDomain(TDomain value) {
        this.domain = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TType }
     *     
     */
    public TType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TType }
     *     
     */
    public void setType(TType value) {
        this.type = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取lastmod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastmod() {
        return lastmod;
    }

    /**
     * 设置lastmod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastmod(String value) {
        this.lastmod = value;
    }

    /**
     * 获取publishTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublishTime() {
        return publishTime;
    }

    /**
     * 设置publishTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublishTime(String value) {
        this.publishTime = value;
    }

    /**
     * 获取changefreq属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TChangefreq }
     *     
     */
    public TChangefreq getChangefreq() {
        return changefreq;
    }

    /**
     * 设置changefreq属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TChangefreq }
     *     
     */
    public void setChangefreq(TChangefreq value) {
        this.changefreq = value;
    }

    /**
     * 获取residenceName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidenceName() {
        return residenceName;
    }

    /**
     * 设置residenceName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidenceName(String value) {
        this.residenceName = value;
    }

    /**
     * 获取url属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * 获取propertyCompany属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyCompany() {
        return propertyCompany;
    }

    /**
     * 设置propertyCompany属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyCompany(String value) {
        this.propertyCompany = value;
    }

    /**
     * 获取houseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * 设置houseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseType(String value) {
        this.houseType = value;
    }

    /**
     * 获取houseAge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseAge() {
        return houseAge;
    }

    /**
     * 设置houseAge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseAge(String value) {
        this.houseAge = value;
    }

    /**
     * 获取schoolDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TSchoolDistrict }
     *     
     */
    public TSchoolDistrict getSchoolDistrict() {
        return schoolDistrict;
    }

    /**
     * 设置schoolDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TSchoolDistrict }
     *     
     */
    public void setSchoolDistrict(TSchoolDistrict value) {
        this.schoolDistrict = value;
    }

    /**
     * 获取developers属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevelopers() {
        return developers;
    }

    /**
     * 设置developers属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevelopers(String value) {
        this.developers = value;
    }

    /**
     * 获取introduce属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置introduce属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntroduce(String value) {
        this.introduce = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPrice(BigInteger value) {
        this.price = value;
    }

    /**
     * 获取volumeRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVolumeRate() {
        return volumeRate;
    }

    /**
     * 设置volumeRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVolumeRate(BigDecimal value) {
        this.volumeRate = value;
    }

    /**
     * 获取totalConArea属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalConArea() {
        return totalConArea;
    }

    /**
     * 设置totalConArea属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalConArea(BigInteger value) {
        this.totalConArea = value;
    }

    /**
     * 获取totalHoushold属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalHoushold() {
        return totalHoushold;
    }

    /**
     * 设置totalHoushold属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalHoushold(BigInteger value) {
        this.totalHoushold = value;
    }

    /**
     * 获取greenRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGreenRate() {
        return greenRate;
    }

    /**
     * 设置greenRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGreenRate(BigInteger value) {
        this.greenRate = value;
    }

    /**
     * 获取totalBuilding属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalBuilding() {
        return totalBuilding;
    }

    /**
     * 设置totalBuilding属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalBuilding(BigInteger value) {
        this.totalBuilding = value;
    }

    /**
     * Gets the value of the surroundFacility property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surroundFacility property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurroundFacility().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSurroundFacility() {
        if (surroundFacility == null) {
            surroundFacility = new ArrayList<String>();
        }
        return this.surroundFacility;
    }

    /**
     * Gets the value of the badSurroundFacility property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the badSurroundFacility property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBadSurroundFacility().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBadSurroundFacility() {
        if (badSurroundFacility == null) {
            badSurroundFacility = new ArrayList<String>();
        }
        return this.badSurroundFacility;
    }

    /**
     * 获取onsaleNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOnsaleNum() {
        return onsaleNum;
    }

    /**
     * 设置onsaleNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOnsaleNum(BigInteger value) {
        this.onsaleNum = value;
    }

    /**
     * 获取onrentNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOnrentNum() {
        return onrentNum;
    }

    /**
     * 设置onrentNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOnrentNum(BigInteger value) {
        this.onrentNum = value;
    }

    /**
     * 获取place属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TPlace }
     *     
     */
    public TPlace getPlace() {
        return place;
    }

    /**
     * 设置place属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TPlace }
     *     
     */
    public void setPlace(TPlace value) {
        this.place = value;
    }

    /**
     * Gets the value of the subwayInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subwayInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubwayInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSubwayInfo }
     * 
     * 
     */
    public List<TSubwayInfo> getSubwayInfo() {
        if (subwayInfo == null) {
            subwayInfo = new ArrayList<TSubwayInfo>();
        }
        return this.subwayInfo;
    }

    public static TRealEstate fromCommunity(HouseCommunity comm){
//    	if(comm.getCommunityFacilities().size() < 1){
//    		System.out.println("The surrounding facilities for NULL community:" + comm.getCommunityNo());
//    		return null;
//    	}
    	TPlace tPlace = TPlace.fromCommunity(comm);
    	if(tPlace == null) return null;
    	TRealEstate r = new TRealEstate();
    	r.setChangefreq(TChangefreq.HOURLY);
    	r.setDevelopers( strToCDATA(comm.getDeveloper(), "未知"));
    	r.setDomain(TDomain.房产);
//    	r.setGreenRate(BigInteger.valueOf(Long.valueOf(String.valueOf( comm.getLandScaping() * 100L ))));
    	r.setGreenRate(BigInteger.valueOf((long)( comm.getLandscapingRatio() * 100L )));
    	r.setHouseAge(StringUtil.yearToStr(comm.getBuildYear()) + "年");
    	r.setHouseType(THouseType.普通住宅.value());
    	String title = comm.getTitle();
    	if(StringUtil.isEmpty(title))
    		title = comm.getCommunityName();
    	r.setIntroduce(strToCDATA(title, ""));  //TODO:这里是小区介绍，暂时没有，用小区名称代，以后要改
    	//修改为修改时间和上传时间从数据库   Modify by He JianBo
//    	r.setLastmod(StringUtil.dateToStr(Calendar.getInstance().getTime()));
//    	r.setPublishTime(StringUtil.dateToStr(Calendar.getInstance().getTime()));
    	r.setPublishTime(StringUtil.dateToStr(comm.getBaiduHousePublishTime()));    	
    	r.setLastmod(StringUtil.dateToStr(comm.getBaiduHouseLastmod()));
    	r.setSource("豪世华邦");
    	r.setName(strToCDATA(comm.getCommunityName(), ""));
    	r.setOnrentNum(BigInteger.valueOf(comm.getRentCount()));	
    	r.setOnsaleNum(BigInteger.valueOf(comm.getShCount()));
    	r.setPlace(tPlace);
    	r.setPrice(BigInteger.valueOf(Float.valueOf(comm.getCurrentMonthShAvgPrice()).longValue())); //买卖房源均价
    	//物业公司
    	r.setPropertyCompany(strToCDATA(comm.getPropertymanagement(), "未知"));
    	r.setResidenceName(strToCDATA(comm.getCommunityName(), ""));
    	r.setSchoolDistrict(TSchoolDistrict.未知);
    	r.setTotalBuilding(BigInteger.valueOf(comm.getHouseCount()));
    	r.setTotalConArea(BigInteger.valueOf(comm.getCommunityArea()));
    	r.setTotalHoushold(BigInteger.valueOf(comm.getTents()));
    	r.setType(TType.HOUSE_PROPERTY);
//    	r.setUrl("http://"+StringUtil.HSHB_DOMAIN+"/community/communityDetail/"+comm.getErpId());
    	r.setUrl("http://"+StringUtil.HSHB_DOMAIN+"/xiaoqu/"+comm.getErpId()+".html");
    	r.setVolumeRate( new BigDecimal((comm.getPlotRatio()).toString()).setScale(2, RoundingMode.HALF_UP));    	
    	r.getSubwayInfo().addAll(TSubwayInfo.fromCommunity(comm));
    	//r.getBadSurroundFacility();  	//TODO: 周边不好的设施暂时没有
    	if(comm.getPictures() != null && comm.getPictures().size() > 0){
    		for(HousePicture hp : comm.getPictures()){
    			if(StringUtil.isEmpty(hp.getPictureUri())) continue;
    			String uri = "http://" + StringUtil.HSHB_DOMAIN + "/frontImages"+hp.getPictureUri();
    			r.getImage().add(uri);
    		}
    	}
    	

    	// 周边设施
    	if(comm.getFacilities() == null || comm.getFacilities().size() == 0){
    		r.getSurroundFacility().add("未知");
    	}else{
    		for(String cFacilities : comm.getFacilities())
    			if(!StringUtil.isBlank(cFacilities)) 
    				r.getSurroundFacility().add(cFacilities);
    	}
    	return r;
    }
}
