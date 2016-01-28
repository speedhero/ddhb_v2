//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 01:30:12 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseRent;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.house.entity.HouseRent;


/**
 * 
 * 				struct value (包含出租状态 )
 * 			
 * 
 * <p>tRentalInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tRentalInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{}tName"/&gt;
 *         &lt;element name="domain" type="{}tDomain"/&gt;
 *         &lt;element name="type" type="{}tType"/&gt;
 *         &lt;element name="lastmod" type="{}tLastmod"/&gt;
 *         &lt;element name="publishTime" type="{}tPublishTime"/&gt;
 *         &lt;element name="priority" type="{}tPriority" minOccurs="0"/&gt;
 *         &lt;element name="source" type="{}tSource"/&gt;
 *         &lt;element name="status" type="{}tStatus"/&gt;
 *         &lt;element name="RentalType" type="{}tRentalType"/&gt;
 *         &lt;element name="RentalPrice" type="{}tRentalPrice"/&gt;
 *         &lt;element name="RentalPayment" type="{}tRentalPayment"/&gt;
 *         &lt;element name="Contact" type="{}tContact" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Installations" type="{}tInstallations" maxOccurs="14"/&gt;
 *         &lt;element name="CheckinTime" type="{}tCheckinTime" minOccurs="0"/&gt;
 *         &lt;element name="RentRoom" type="{}tRentRoom" minOccurs="0"/&gt;
 *         &lt;element name="TimeforView" type="{}tTimeforView" minOccurs="0"/&gt;
 *         &lt;element name="RentalIntroduce" type="{}tRentalIntroduce"/&gt;
 *         &lt;element name="House" type="{}tHouse"/&gt;
 *         &lt;element name="RealEstate" type="{}tRealEstate"/&gt;
 *         &lt;element name="Broker" type="{}tBroker" minOccurs="0"/&gt;
 *         &lt;element name="BrokerTel" type="{}tBrokerTel" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tRentalInfo", propOrder = {
    "name",
    "domain",
    "type",
    "lastmod",
    "publishTime",
    "priority",
    "source",
    "status",
    "rentalType",
    "rentalPrice",
    "rentalPayment",
    "contact",
    "installations",
    "checkinTime",
    "rentRoom",
    "timeforView",
    "rentalIntroduce",
    "house",
    "realEstate",
    "broker",
    "brokerTel"
})
public class TRentalInfo extends BaseEntity {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TDomain domain;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TType type;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String lastmod;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String publishTime;
    protected BigDecimal priority;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TStatus status;
    @XmlElement(name = "RentalType", required = true)
    @XmlSchemaType(name = "string")
    protected TRentalType rentalType;
    @XmlElement(name = "RentalPrice", required = true)
    protected BigInteger rentalPrice;
    @XmlElement(name = "RentalPayment", required = true)
    protected String rentalPayment;
    @XmlElement(name = "Contact", required = true)
    protected List<TContact> contact;
    @XmlElement(name = "Installations", required = true)
    @XmlSchemaType(name = "string")
    protected List<TInstallations> installations;
    @XmlElement(name = "CheckinTime")
    protected String checkinTime;
    @XmlElement(name = "RentRoom")
    protected TRentRoom rentRoom;
    @XmlElement(name = "TimeforView")
    protected String timeforView;
    @XmlElement(name = "RentalIntroduce", required = true)
    protected String rentalIntroduce;
    @XmlElement(name = "House", required = true)
    protected THouse house;
    @XmlElement(name = "RealEstate", required = true)
    protected TRealEstate realEstate;
    @XmlElement(name = "Broker")
    protected String broker;
    @XmlElement(name = "BrokerTel")
    protected String brokerTel;

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
     * 获取priority属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriority() {
        return priority;
    }

    /**
     * 设置priority属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriority(BigDecimal value) {
        this.priority = value;
    }

    /**
     * 获取source属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置source属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TStatus }
     *     
     */
    public TStatus getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TStatus }
     *     
     */
    public void setStatus(TStatus value) {
        this.status = value;
    }

    /**
     * 获取rentalType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TRentalType }
     *     
     */
    public TRentalType getRentalType() {
        return rentalType;
    }

    /**
     * 设置rentalType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TRentalType }
     *     
     */
    public void setRentalType(TRentalType value) {
        this.rentalType = value;
    }

    /**
     * 获取rentalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRentalPrice() {
        return rentalPrice;
    }

    /**
     * 设置rentalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRentalPrice(BigInteger value) {
        this.rentalPrice = value;
    }

    /**
     * 获取rentalPayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalPayment() {
        return rentalPayment;
    }

    /**
     * 设置rentalPayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalPayment(String value) {
        this.rentalPayment = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TContact }
     * 
     * 
     */
    public List<TContact> getContact() {
        if (contact == null) {
            contact = new ArrayList<TContact>();
        }
        return this.contact;
    }

    /**
     * Gets the value of the installations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the installations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstallations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TInstallations }
     * 
     * 
     */
    public List<TInstallations> getInstallations() {
        if (installations == null) {
            installations = new ArrayList<TInstallations>();
        }
        return this.installations;
    }

    /**
     * 获取checkinTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckinTime() {
        return checkinTime;
    }

    /**
     * 设置checkinTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckinTime(String value) {
        this.checkinTime = value;
    }

    /**
     * 获取rentRoom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TRentRoom }
     *     
     */
    public TRentRoom getRentRoom() {
        return rentRoom;
    }

    /**
     * 设置rentRoom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TRentRoom }
     *     
     */
    public void setRentRoom(TRentRoom value) {
        this.rentRoom = value;
    }

    /**
     * 获取timeforView属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeforView() {
        return timeforView;
    }

    /**
     * 设置timeforView属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeforView(String value) {
        this.timeforView = value;
    }

    /**
     * 获取rentalIntroduce属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalIntroduce() {
        return rentalIntroduce;
    }

    /**
     * 设置rentalIntroduce属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalIntroduce(String value) {
        this.rentalIntroduce = value;
    }

    /**
     * 获取house属性的值。
     * 
     * @return
     *     possible object is
     *     {@link THouse }
     *     
     */
    public THouse getHouse() {
        return house;
    }

    /**
     * 设置house属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link THouse }
     *     
     */
    public void setHouse(THouse value) {
        this.house = value;
    }

    /**
     * 获取realEstate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TRealEstate }
     *     
     */
    public TRealEstate getRealEstate() {
        return realEstate;
    }

    /**
     * 设置realEstate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TRealEstate }
     *     
     */
    public void setRealEstate(TRealEstate value) {
        this.realEstate = value;
    }

    /**
     * 获取broker属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroker() {
        return broker;
    }

    /**
     * 设置broker属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroker(String value) {
        this.broker = value;
    }

    /**
     * 获取brokerTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerTel() {
        return brokerTel;
    }

    /**
     * 设置brokerTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerTel(String value) {
        this.brokerTel = value;
    }

    
    public static TRentalInfo fromHouse(HouseRentHouseExt house){
    	
    	THouse tHouse =  THouse.fromHouse(house);
    	if(tHouse == null) 
    		return null;
    	List<TContact> contactList = TContact.fromHouse(house);
    	if(contactList == null ||  contactList.size() < 1)
    		return null;
    	if(house.getFurnitures() == null || house.getFurnitures().size() < 1){
    		System.out.println("Indoor facilities	rentHouse_id:" + house.getHouseId());
    		return null;
    	}
    	TRentalInfo rent = new TRentalInfo();
//    	if(house.getBroker()!=null){
//	    	rent.setBroker(strToCDATA(house.getBroker().getBname(), "未知"));
//	    	rent.setBrokerTel(strToCDATA(house.getBroker().getTelephone(), "18967113307"));
//    	}

    	rent.setBroker(strToCDATA(house.getAgentName(), "未知"));
    	//TODO 这是盛经理的小手机
    	rent.setBrokerTel(strToCDATA(house.getTelephone(), "18967113307")); 
    	
    	String title = house.getTitle();
    	if(title != null){
    	if("".equals(title.trim())){
    		System.out.println("title is null rentHouse_id:" + house.getHouseId());
    		return null;
    	}
    	}
    	rent.setCheckinTime("随时");
    	rent.setName(title);
    	rent.setDomain(TDomain.房产);
    	rent.setHouse(tHouse);
    	//修改为修改时间和上传时间从数据库   Modify by He JianBo
//    	rent.setLastmod(StringUtil.dateToStr(Calendar.getInstance().getTime()));
//    	rent.setPublishTime(StringUtil.dateToStr(Calendar.getInstance().getTime()));
    	rent.setLastmod(StringUtil.dateToStr(house.getBaiduHouseLastmod()));
    	rent.setPublishTime(StringUtil.dateToStr(house.getBaiduHousePublishTime()));
    	rent.setPriority(BigDecimal.valueOf(0.5));  //TODO:这里以后要按网站设置的优先级来设值
    	rent.setRealEstate(TRealEstate.fromHouse(house));
    	rent.setRentalIntroduce(strToCDATA(house.getContent(), house.getTitle()));
    	rent.setRentalPayment(strToCDATA(house.getPayforWay(), "押1付3")); //TODO:这里暂时设置默认值 押1付3
    	rent.setRentalPrice(BigInteger.valueOf(Float.valueOf(house.getRentPrice()).longValue()));
    	//用于删除
//    	house.getBaiDuHouseRentList().setPrice((int)(house.getRentPrice()));
    	//整租or合租
    	rent.setRentalType(TRentalType.fromHBValue(house.getRentTypeId() ==1?"整租":"合租"));
    	rent.setRentRoom(TRentRoom.fromHouse(house));
    	rent.setSource(StringUtil.HSHB_NAME);
    	rent.setStatus(TStatus.ADD);  //TODO:这里要根据实际情况设置
    	rent.setTimeforView(house.getTimeToSee());
    	rent.setType(TType.HOUSE_PROPERTY);

//    	rent.getContact().addAll(TContact.fromHouse(house));
    	rent.getContact().addAll(contactList);
    	rent.getInstallations().addAll(rent.getInstallations(house));

    	return rent;
    }
    
    /**
     * 家具
     * @param house
     * @return
     */
    private List<TInstallations> getInstallations(HouseRentHouseExt house){
    	List<TInstallations> list = new ArrayList<TInstallations>();
    	List<CommonLiveFacility> furList = house.getFurnitures();
//    	List<String> listStr = new ArrayList<String>();
    	for(CommonLiveFacility fur: furList){
    		boolean b = true;
    		for(TInstallations t : list){
    			
    			if(t.value().equals((TInstallations.fromHBValue(fur.getClfName()).value())))
    			{
    				b = false;
    				break ;
    			}
    		}
    		if(b == false)
    			continue;
//    	System.out.println("TInstallations.fromHBValue(fur.getFurName())" + TInstallations.fromHBValue(fur.getFurName()));
    	list.add(TInstallations.fromHBValue(fur.getClfName()));
//    	listStr.add(TInstallations.fromHBValue(fur.getFurName()));
    	}
    	return list;
    }    
}
