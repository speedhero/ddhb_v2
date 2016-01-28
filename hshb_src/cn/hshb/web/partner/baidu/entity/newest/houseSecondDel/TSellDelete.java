//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:31 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseSecondDel;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseSecond;
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.house.entity.BaiDuHouseSecond;


/**
 * 
 * 				需要删除的二手房房源
 * 			
 * 
 * <p>tSellDelete complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tSellDelete"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="publishTime" type="{}tPublishTime"/&gt;
 *         &lt;element name="lastmod" type="{}tLastmod"/&gt;
 *         &lt;element name="ID" type="{}tID"/&gt;
 *         &lt;element name="name" type="{}tName"/&gt;
 *         &lt;element name="domain" type="{}tDomain"/&gt;
 *         &lt;element name="type" type="{}tType"/&gt;
 *         &lt;element name="DeleteReason" type="{}tDeleteReason"/&gt;
 *         &lt;element name="SellinglPrice" type="{}tSellinglPrice" minOccurs="0"/&gt;
 *         &lt;element name="source" type="{}tSource"/&gt;
 *         &lt;element name="status" type="{}tStatus"/&gt;
 *         &lt;element name="Broker" type="{}tBroker"/&gt;
 *         &lt;element name="BrokerTel" type="{}tBrokerTel" minOccurs="0"/&gt;
 *         &lt;element name="BuildNum" type="{}tBuildNum" minOccurs="0"/&gt;
 *         &lt;element name="BuildUnit" type="{}tBuildUnit" minOccurs="0"/&gt;
 *         &lt;element name="RoomNum" type="{}tRoomNum" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSellDelete", propOrder = {
    "publishTime",
    "lastmod",
    "id",
    "name",
    "domain",
    "type",
    "deleteReason",
    "sellinglPrice",
    "source",
    "status",
    "broker",
    "brokerTel",
    "buildNum",
    "buildUnit",
    "roomNum"
})
public class TSellDelete extends BaseEntity {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String publishTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String lastmod;
    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TDomain domain;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TType type;
    @XmlElement(name = "DeleteReason", required = true)
    @XmlSchemaType(name = "string")
    protected TDeleteReason deleteReason;
    @XmlElement(name = "SellinglPrice")
    protected BigInteger sellinglPrice;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TStatus status;
    @XmlElement(name = "Broker", required = true)
    protected String broker;
    @XmlElement(name = "BrokerTel")
    protected String brokerTel;
    @XmlElement(name = "BuildNum")
    protected String buildNum;
    @XmlElement(name = "BuildUnit")
    protected String buildUnit;
    @XmlElement(name = "RoomNum")
    protected String roomNum;

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
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
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
     * 获取deleteReason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TDeleteReason }
     *     
     */
    public TDeleteReason getDeleteReason() {
        return deleteReason;
    }

    /**
     * 设置deleteReason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TDeleteReason }
     *     
     */
    public void setDeleteReason(TDeleteReason value) {
        this.deleteReason = value;
    }

    /**
     * 获取sellinglPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSellinglPrice() {
        return sellinglPrice;
    }

    /**
     * 设置sellinglPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSellinglPrice(BigInteger value) {
        this.sellinglPrice = value;
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
     *     {@link String }
     *     
     */
    public String getBuildUnit() {
        return buildUnit;
    }

    /**
     * 设置buildUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildUnit(String value) {
        this.buildUnit = value;
    }

    /**
     * 获取roomNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * 设置roomNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNum(String value) {
        this.roomNum = value;
    }
    
    public static TSellDelete fromHouse(PartnerBaiduHouseSecond house)
    {
    	TSellDelete sell = new TSellDelete();
//    	String idUrl = "http://" + StringUtil.HSHB_DOMAIN + "/baidu/houseSecond/" + house.getHouseId() ;
    	String idUrl = "http://" + StringUtil.HSHB_DOMAIN + "/chushou/" + house.getHouseId() +".html" ;
//    	if(house.getBroker() != null){
//    		sell.setBroker(house.getBroker().getBname());
//    		if(!"".equals(house.getBroker().getTelephone().trim())){
//    		sell.setBrokerTel(house.getBroker().getTelephone().trim());
//    		idUrl +=  "/"+house.getBroker().getErpId();
//    		}
//    	}
    	
//    	if(house.getBroker() != null){
    	sell.setBroker(strToCDATA(house.getBrokerName(),"未知"));
    	sell.setBrokerTel(strToCDATA(house.getTelephone(),"18967113307"));
//    		if( house.getBrokerErpId() != null )
//    			idUrl +=  "/"+house.getBrokerErpId();
    		
//    	}
    	
    	sell.setPublishTime(StringUtil.dateToStr(house.getPublishTime()));
    	sell.setLastmod(StringUtil.dateToStr(house.getLastmod()));
    	sell.setID(idUrl);
    	sell.setName(house.getTitle());
    	sell.setDomain(TDomain.房产);
    	sell.setType(TType.HOUSE_PROPERTY);
    	sell.setDeleteReason(TDeleteReason.已售);
    	sell.setSellinglPrice(BigInteger.valueOf((long)((house.getPrice()*1L))));
    	sell.setSource(StringUtil.HSHB_NAME);
    	sell.setStatus(TStatus.DEL);
    	return sell;
    }
}
