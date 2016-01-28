//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 01:30:12 PM CST 
//


package cn.hshb.web.partner.baidu.entity.houseRent;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.hbwebsite.house.entity.ERPHousePicture;
import com.huatek.hbwebsite.house.entity.HouseRent;


/**
 * <p>tHouse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tHouse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PropertyVerified" type="{}tPropertyVerified"/&gt;
 *         &lt;element name="Url" type="{}t1Url"/&gt;
 *         &lt;element name="HouseArea" type="{}tHouseArea"/&gt;
 *         &lt;element name="HouseType" type="{}tHouseType"/&gt;
 *         &lt;element name="HouseOrientation" type="{}tHouseOrientation"/&gt;
 *         &lt;element name="HouseAllFloor" type="{}tHouseAllFloor"/&gt;
 *         &lt;element name="HouseSituation" type="{}tHouseSituation" minOccurs="0"/&gt;
 *         &lt;element name="Decoration" type="{}tDecoration"/&gt;
 *         &lt;element name="CovorImage" type="{}tCovorImage"/&gt;
 *         &lt;element name="HouseImage" type="{}tHouseImage" maxOccurs="unbounded"/&gt;
 *         &lt;element name="HouseImageNum" type="{}tHouseImageNum"/&gt;
 *         &lt;element name="IsPartition" type="{}tIsPartition" minOccurs="0"/&gt;
 *         &lt;element name="Tag" type="{}tTag" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="HouseFloor" type="{}tHouseFloor"/&gt;
 *         &lt;element name="BuildNum" type="{}tBuildNum"/&gt;
 *         &lt;element name="BuildUnit" type="{}tBuildUnit" minOccurs="0"/&gt;
 *         &lt;element name="RoomNum" type="{}tRoomNum" minOccurs="0"/&gt;
 *         &lt;element name="BuildType" type="{}tBuildType"/&gt;
 *         &lt;element name="HouseStructurename" type="{}tHouseStructurename"/&gt;
 *         &lt;element name="HouseStructureshi" type="{}tHouseStructureshi"/&gt;
 *         &lt;element name="HouseStructureting" type="{}tHouseStructureting"/&gt;
 *         &lt;element name="HouseStructurewei" type="{}tHouseStructurewei"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tHouse", propOrder = {
    "propertyVerified",
    "url",
    "houseArea",
    "houseType",
    "houseOrientation",
    "houseAllFloor",
    "houseSituation",
    "decoration",
    "covorImage",
    "houseImage",
    "houseImageNum",
    "isPartition",
    "tag",
    "houseFloor",
    "buildNum",
    "buildUnit",
    "roomNum",
    "buildType",
    "houseStructurename",
    "houseStructureshi",
    "houseStructureting",
    "houseStructurewei"
})
public class THouse {

    @XmlElement(name = "PropertyVerified", required = true)
    protected String propertyVerified;
    @XmlElement(name = "Url", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;
    @XmlElement(name = "HouseArea", required = true)
    protected String houseArea;
    @XmlElement(name = "HouseType", required = true)
    @XmlSchemaType(name = "string")
    protected THouseType houseType;
    @XmlElement(name = "HouseOrientation", required = true)
    @XmlSchemaType(name = "string")
    protected THouseOrientation houseOrientation;
    @XmlElement(name = "HouseAllFloor", required = true)
    protected String houseAllFloor;
    @XmlElement(name = "HouseSituation")
    @XmlSchemaType(name = "string")
    protected THouseSituation houseSituation;
    @XmlElement(name = "Decoration", required = true)
    @XmlSchemaType(name = "string")
    protected TDecoration decoration;
    @XmlElement(name = "CovorImage", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String covorImage;
    @XmlElement(name = "HouseImage", required = true)
    @XmlSchemaType(name = "anyURI")
    protected List<String> houseImage;
    @XmlElement(name = "HouseImageNum", required = true)
    protected String houseImageNum;
    @XmlElement(name = "IsPartition")
    @XmlSchemaType(name = "string")
    protected TIsPartition isPartition;
    @XmlElement(name = "Tag")
    protected List<String> tag;
    @XmlElement(name = "HouseFloor", required = true)
    protected String houseFloor;
    @XmlElement(name = "BuildNum", required = true)
    protected String buildNum;
    @XmlElement(name = "BuildUnit")
    protected BigInteger buildUnit;
    @XmlElement(name = "RoomNum")
    protected BigInteger roomNum;
    @XmlElement(name = "BuildType", required = true)
    @XmlSchemaType(name = "string")
    protected TBuildType buildType;
    @XmlElement(name = "HouseStructurename", required = true)
    @XmlSchemaType(name = "string")
    protected THouseStructurename houseStructurename;
    @XmlElement(name = "HouseStructureshi", required = true)
    protected String houseStructureshi;
    @XmlElement(name = "HouseStructureting", required = true)
    protected String houseStructureting;
    @XmlElement(name = "HouseStructurewei", required = true)
    protected String houseStructurewei;

    /**
     * 获取propertyVerified属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyVerified() {
        return propertyVerified;
    }

    /**
     * 设置propertyVerified属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyVerified(String value) {
        this.propertyVerified = value;
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
     * 获取houseArea属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseArea() {
        return houseArea;
    }

    /**
     * 设置houseArea属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseArea(String value) {
        this.houseArea = value;
    }

    /**
     * 获取houseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link THouseType }
     *     
     */
    public THouseType getHouseType() {
        return houseType;
    }

    /**
     * 设置houseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link THouseType }
     *     
     */
    public void setHouseType(THouseType value) {
        this.houseType = value;
    }

    /**
     * 获取houseOrientation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link THouseOrientation }
     *     
     */
    public THouseOrientation getHouseOrientation() {
        return houseOrientation;
    }

    /**
     * 设置houseOrientation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link THouseOrientation }
     *     
     */
    public void setHouseOrientation(THouseOrientation value) {
        this.houseOrientation = value;
    }

    /**
     * 获取houseAllFloor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseAllFloor() {
        return houseAllFloor;
    }

    /**
     * 设置houseAllFloor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseAllFloor(String value) {
        this.houseAllFloor = value;
    }

    /**
     * 获取houseSituation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link THouseSituation }
     *     
     */
    public THouseSituation getHouseSituation() {
        return houseSituation;
    }

    /**
     * 设置houseSituation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link THouseSituation }
     *     
     */
    public void setHouseSituation(THouseSituation value) {
        this.houseSituation = value;
    }

    /**
     * 获取decoration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TDecoration }
     *     
     */
    public TDecoration getDecoration() {
        return decoration;
    }

    /**
     * 设置decoration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TDecoration }
     *     
     */
    public void setDecoration(TDecoration value) {
        this.decoration = value;
    }

    /**
     * 获取covorImage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCovorImage() {
        return covorImage;
    }

    /**
     * 设置covorImage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCovorImage(String value) {
        this.covorImage = value;
    }

    /**
     * Gets the value of the houseImage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the houseImage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHouseImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHouseImage() {
        if (houseImage == null) {
            houseImage = new ArrayList<String>();
        }
        return this.houseImage;
    }

    /**
     * 获取houseImageNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseImageNum() {
        return houseImageNum;
    }

    /**
     * 设置houseImageNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseImageNum(String value) {
        this.houseImageNum = value;
    }

    /**
     * 获取isPartition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TIsPartition }
     *     
     */
    public TIsPartition getIsPartition() {
        return isPartition;
    }

    /**
     * 设置isPartition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TIsPartition }
     *     
     */
    public void setIsPartition(TIsPartition value) {
        this.isPartition = value;
    }

    /**
     * Gets the value of the tag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTag() {
        if (tag == null) {
            tag = new ArrayList<String>();
        }
        return this.tag;
    }

    /**
     * 获取houseFloor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseFloor() {
        return houseFloor;
    }

    /**
     * 设置houseFloor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseFloor(String value) {
        this.houseFloor = value;
    }

    /**
     * 获取buildNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildNum() {
        return buildNum;
    }

    /**
     * 设置buildNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildNum(String value) {
        this.buildNum = value;
    }

    /**
     * 获取buildUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBuildUnit() {
        return buildUnit;
    }

    /**
     * 设置buildUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBuildUnit(BigInteger value) {
        this.buildUnit = value;
    }

    /**
     * 获取roomNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRoomNum() {
        return roomNum;
    }

    /**
     * 设置roomNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRoomNum(BigInteger value) {
        this.roomNum = value;
    }

    /**
     * 获取buildType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TBuildType }
     *     
     */
    public TBuildType getBuildType() {
        return buildType;
    }

    /**
     * 设置buildType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TBuildType }
     *     
     */
    public void setBuildType(TBuildType value) {
        this.buildType = value;
    }

    /**
     * 获取houseStructurename属性的值。
     * 
     * @return
     *     possible object is
     *     {@link THouseStructurename }
     *     
     */
    public THouseStructurename getHouseStructurename() {
        return houseStructurename;
    }

    /**
     * 设置houseStructurename属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link THouseStructurename }
     *     
     */
    public void setHouseStructurename(THouseStructurename value) {
        this.houseStructurename = value;
    }

    /**
     * 获取houseStructureshi属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseStructureshi() {
        return houseStructureshi;
    }

    /**
     * 设置houseStructureshi属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseStructureshi(String value) {
        this.houseStructureshi = value;
    }

    /**
     * 获取houseStructureting属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseStructureting() {
        return houseStructureting;
    }

    /**
     * 设置houseStructureting属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseStructureting(String value) {
        this.houseStructureting = value;
    }

    /**
     * 获取houseStructurewei属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseStructurewei() {
        return houseStructurewei;
    }

    /**
     * 设置houseStructurewei属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseStructurewei(String value) {
        this.houseStructurewei = value;
    }
    
    public static THouse fromHouse(HouseRent house){
    	if(house.getErpHousePicList()==null || house.getErpHousePicList().size()<1)
    	{
    		System.out.println("The other picture size <0	 rentHouse_id:" + house.getHourseNo());
    		return null;
    	}
    	if(house.getPictureUrl() == null ){ 
    		System.out.println("The cover image is empty rentHouse_id:" + house.getHourseNo());
    		return null;
    		}
    	THouse h = new THouse();
    	h.setBuildNum("0");  //无楼号
    	h.setBuildType(TBuildType.其它);
    	h.setBuildUnit(BigInteger.valueOf(0)); //无单元号
    	h.setCovorImage("http://"+ StringUtil.HSHB_DOMAIN  + "/frontImages" + house.getPictureUrl());
//    	装修
//    	if(house.getDecoration()!=null)
//    		h.setDecoration(TDecoration.fromHBValue( house.getDecoration().getDecorationName()));
//    	else
//    		return null;
    	if(house.getDecoration()!=null)
		h.setDecoration(TDecoration.fromHBValue( house.getDecoration().getDecorationName()));
	else
		h.setDecoration(TDecoration.fromHBValue("其他"));
    	
    	h.setHouseArea(String.valueOf(house.getArea())+"m2");
    	h.setHouseAllFloor(String.valueOf(house.getStoryCount()) + "层");
    	
    	for(ERPHousePicture img: house.getErpHousePicList())
    		h.getHouseImage().add("http://"+StringUtil.HSHB_DOMAIN + "/frontImages" + img.getPicUrl());
    	
    	if(house.getErpHousePicList()!=null)
    		h.setHouseImageNum(String.valueOf(house.getErpHousePicList().size())+"张");
    	else
    		h.setHouseImageNum("0张");
    	
    	String fn = "中";
    	if(house.getStoryCount()>0){
    		double f = house.getStorey()*0.1 / house.getStoryCount()*0.1;
    		if(f>=2f) fn = "上";
    		else if(f<2f && f>=1f) fn = "中";
    		else if(f<1f) fn = "下";
    	}
    	
    	h.setHouseFloor(fn+"层");
    	//为空时，其他
    	if(house.getOrientations()!=null)
    		h.setHouseOrientation(THouseOrientation.fromHBValue(house.getOrientations().getOrientationName()));
    	else
    		h.setHouseOrientation(THouseOrientation.fromHBValue("其他"));
    	
    	h.setHouseSituation(THouseSituation.fromHBValue(house.getUseageStatus()));
    	h.setHouseStructurename(THouseStructurename.fromHBValue(house.getShi()));
    	h.setHouseStructureshi(house.getShi()+"室");
    	h.setHouseStructureting(house.getTing()+"厅");
    	h.setHouseStructurewei(house.getWei()+"卫");
    	if(house.getCommunity()!=null && house.getCommunity().getPropertyType()!=null)
    		h.setHouseType(THouseType.fromHBValue(house.getCommunity().getPropertyType().getUsageName()));
    	h.setPropertyVerified("1");
    	h.setRoomNum(BigInteger.valueOf(0));  //房间号，不能为空，但不能写详细房号，所以置0
    	
    	String url = "http://www.hshb.cn/rent/houseRentDetail/"+house.getHourseNo();
    	if(house.getBroker()!=null)
    		url += "/"+house.getBroker().getErpId();
    	h.setUrl(url);
    	
    	return h;
    }

}
