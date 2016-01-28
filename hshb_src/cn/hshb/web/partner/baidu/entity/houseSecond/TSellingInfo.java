//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.houseSecond;

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

import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.house.entity.HouseSecond;


/**
 * <p>tSellingInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tSellingInfo"&gt;
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
 *         &lt;element name="SchoolDistrict" type="{}tSchoolDistrict" minOccurs="0"/&gt;
 *         &lt;element name="SellingPrice" type="{}tSellingPrice"/&gt;
 *         &lt;element name="SellingPriceAvg" type="{}tSellingPriceAvg"/&gt;
 *         &lt;element name="SellingPayment" type="{}tSellingPayment"/&gt;
 *         &lt;element name="TaxInfo" type="{}tTaxInfo"/&gt;
 *         &lt;element name="Contact" type="{}tContact" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Installations" type="{}tInstallations" maxOccurs="14"/&gt;
 *         &lt;element name="CheckinTime" type="{}tCheckinTime" minOccurs="0"/&gt;
 *         &lt;element name="TimeforView" type="{}tTimeforView" minOccurs="0"/&gt;
 *         &lt;element name="SellingIntroduce" type="{}tSellingIntroduce"/&gt;
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
@XmlType(name = "tSellingInfo", propOrder = {
    "name",
    "domain",
    "type",
    "lastmod",
    "publishTime",
    "priority",
    "source",
    "status",
    "schoolDistrict",
    "sellingPrice",
    "sellingPriceAvg",
    "sellingPayment",
    "taxInfo",
    "contact",
    "installations",
    "checkinTime",
    "timeforView",
    "sellingIntroduce",
    "house",
    "realEstate",
    "broker",
    "brokerTel",
    "isFreeTax"
})
public class TSellingInfo extends BaseEntity{

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
    @XmlElement(name = "SchoolDistrict")
    protected Boolean schoolDistrict;
    @XmlElement(name = "SellingPrice", required = true)
    protected BigInteger sellingPrice;
    @XmlElement(name = "SellingPriceAvg", required = true)
    protected String sellingPriceAvg;
    @XmlElement(name = "SellingPayment", required = true)
    @XmlSchemaType(name = "string")
    protected TSellingPayment sellingPayment;
    @XmlElement(name = "TaxInfo", required = true)
    @XmlSchemaType(name = "string")
    protected TTaxInfo taxInfo;
    @XmlElement(name = "Contact", required = true)
    protected List<TContact> contact;
    @XmlElement(name = "Installations", required = true)
    @XmlSchemaType(name = "string")
    protected List<TInstallations> installations;
    @XmlElement(name = "CheckinTime")
    protected String checkinTime;
    @XmlElement(name = "TimeforView")
    protected String timeforView;
    @XmlElement(name = "SellingIntroduce", required = true)
    protected String sellingIntroduce;
    @XmlElement(name = "House", required = true)
    protected THouse house;
    @XmlElement(name = "RealEstate", required = true)
    protected TRealEstate realEstate;
    @XmlElement(name = "Broker")
    protected String broker;
    @XmlElement(name = "BrokerTel")
    protected String brokerTel;
    @XmlElement(name = "IsFreeTax")
    protected int isFreeTax;
    
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
     * 获取schoolDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSchoolDistrict() {
        return schoolDistrict;
    }

    /**
     * 设置schoolDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSchoolDistrict(Boolean value) {
        this.schoolDistrict = value;
    }

    /**
     * 获取sellingPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSellingPrice() {
        return sellingPrice;
    }

    /**
     * 设置sellingPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSellingPrice(BigInteger value) {
        this.sellingPrice = value;
    }

    /**
     * 获取sellingPriceAvg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingPriceAvg() {
        return sellingPriceAvg;
    }

    /**
     * 设置sellingPriceAvg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingPriceAvg(String value) {
        this.sellingPriceAvg = value;
    }

    /**
     * 获取sellingPayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TSellingPayment }
     *     
     */
    public TSellingPayment getSellingPayment() {
        return sellingPayment;
    }

    /**
     * 设置sellingPayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TSellingPayment }
     *     
     */
    public void setSellingPayment(TSellingPayment value) {
        this.sellingPayment = value;
    }

    /**
     * 获取taxInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TTaxInfo }
     *     
     */
    public TTaxInfo getTaxInfo() {
        return taxInfo;
    }

    /**
     * 设置taxInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TTaxInfo }
     *     
     */
    public void setTaxInfo(TTaxInfo value) {
        this.taxInfo = value;
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
     * 获取sellingIntroduce属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellingIntroduce() {
        return sellingIntroduce;
    }

    /**
     * 设置sellingIntroduce属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellingIntroduce(String value) {
        this.sellingIntroduce = value;
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
    
    /**
     * 设置isFreeTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public int getIsFreeTax() {
		return isFreeTax;
	}

	public void setIsFreeTax(int isFreeTax) {
		this.isFreeTax = isFreeTax;
	}

	public static TSellingInfo fromHouse(HouseSecond house){
    	
    	THouse thouse = THouse.fromHouse(house);
    	if(thouse==null) return null;
    	List<TContact> contactList = TContact.fromHouse(house);
    	if(contactList==null || contactList.size()<1) return null;
    	TRealEstate realEstate = TRealEstate.fromHouse(house);
    	if(realEstate==null) return null;;
    	String title =house.getTitle();
    	if(title !=null){
    		if("".equals(title.trim()))
    			{
    				System.out.println("title is NULL secondHouse:" + house.getHouseNo());
    				return null;
    			}
    	}
    	TSellingInfo sell = new TSellingInfo();
    	if(house.getBroker()!=null){
	    	sell.setBroker(strToCDATA(house.getBroker().getBname(), "未知"));
	    	sell.setBrokerTel(strToCDATA(house.getBroker().getTelephone(), "18967113307"));
    	}
    	sell.setCheckinTime("随时");
    	sell.setName(strToCDATA(title, "未知"));
    	sell.setDomain(TDomain.房产);
    	sell.setHouse(thouse);
    	//修改为修改时间和上传时间从数据库   Modify by He JianBo
//    	sell.setLastmod(StringUtil.dateToStr(Calendar.getInstance().getTime()));
//    	sell.setPublishTime(StringUtil.dateToStr(Calendar.getInstance().getTime()));
    	sell.setLastmod(StringUtil.dateToStr(house.getBaiDuHouseSecond().getLastmod()));
    	sell.setPublishTime(StringUtil.dateToStr(house.getBaiDuHouseSecond().getPublishTime()));
    	sell.setPriority(BigDecimal.valueOf(0.5));  //TODO:这里以后要按网站设置的优先级来设值
    	sell.setRealEstate(realEstate);
    	sell.setSchoolDistrict(house.getSchoolRegionFlag()==1? true: false);
    	sell.setSellingIntroduce(strToCDATA(house.getContent(), house.getTitle()) );
    	sell.setSellingPayment(TSellingPayment.组合贷款);  //TODO: 目前二手房没有付款方式
    	sell.setSellingPrice(BigInteger.valueOf(house.getPrice().longValue()));
    	if(house.getPrice()!=null && house.getArea()!=null && house.getArea()!=0)
    	sell.setSellingPriceAvg(StringUtil.moneyToStr(house.getPrice() / house.getArea())+"元/m2");
    	//用于删除
    	house.getBaiDuHouseSecond().setPrice((Integer)((house.getPrice()).intValue()));
    	sell.setSource(StringUtil.HSHB_NAME);
    	sell.setStatus(TStatus.ADD);  		//TODO:这里要根据实际情况设置
    	sell.setTaxInfo(TTaxInfo.其它);   //TODO:网站需要补充税费标志
    	sell.setTimeforView(house.getTimeToSee());
    	sell.setType(TType.HOUSE_PROPERTY);
    	
    	sell.getContact().addAll(contactList);
    	sell.getInstallations().add(TInstallations.未知);

    	//免双税的ERPID
    	String tagsErpId = "b91edfdd-5d11-48c0-af89-211ec3ae53fc";
    	//IsFreeTax: 是否免税，是否免税，是填写1，不是填写0，最少出现0次 最多出现1次，类型为字符串，有效值为：0、1
    	if(house.getTags().indexOf(tagsErpId) == -1 ){
    		sell.setIsFreeTax(0);
    	}else{
    		sell.setIsFreeTax(1);
    	}
    	return sell;
    }
    
}
