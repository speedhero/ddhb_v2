//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 01:30:12 PM CST 
//


package cn.hshb.web.partner.baidu.entity.houseRent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.house.entity.HouseRent;


/**
 * 
 * 				若整租，忽略此项
 * 			
 * 
 * <p>tRentRoom complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tRentRoom"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RoomArea" type="{}tRoomArea"/&gt;
 *         &lt;element name="RoomOrientation" type="{}tRoomOrientation"/&gt;
 *         &lt;element name="AdditionalInformation" type="{}tAdditionalInformation" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tRentRoom", propOrder = {
    "roomArea",
    "roomOrientation",
    "additionalInformation"
})
public class TRentRoom extends BaseEntity{

    @XmlElement(name = "RoomArea", required = true)
    protected String roomArea;
    @XmlElement(name = "RoomOrientation", required = true)
    @XmlSchemaType(name = "string")
    protected TRoomOrientation roomOrientation;
    @XmlElement(name = "AdditionalInformation")
    protected String additionalInformation;

    /**
     * 获取roomArea属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomArea() {
        return roomArea;
    }

    /**
     * 设置roomArea属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomArea(String value) {
        this.roomArea = value;
    }

    /**
     * 获取roomOrientation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TRoomOrientation }
     *     
     */
    public TRoomOrientation getRoomOrientation() {
        return roomOrientation;
    }

    /**
     * 设置roomOrientation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TRoomOrientation }
     *     
     */
    public void setRoomOrientation(TRoomOrientation value) {
        this.roomOrientation = value;
    }

    /**
     * 获取additionalInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * 设置additionalInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalInformation(String value) {
        this.additionalInformation = value;
    }

    public static TRentRoom fromHouse(HouseRent house){
    	TRentRoom h = new TRentRoom();
    	h.setRoomOrientation(TRoomOrientation.fromHBValue(house.getOrientations() == null ? null:house.getOrientations().getOrientationName()));
    	h.setRoomArea((int)house.getArea() + "m2");
    	h.setAdditionalInformation(strToCDATA(house.getContent(), ""));
    	return h;
    }
}
