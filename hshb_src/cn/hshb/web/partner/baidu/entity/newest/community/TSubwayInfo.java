//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:46:01 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.community;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;

import com.huatek.hbwebsite.common.entity.Community;


/**
 * 
 * 				地铁信息，包括地铁线路subwayline、地铁站subwaystation，距离distance，若有多个地铁站或者地铁线路，该标签可以多次出现
 * 			
 * 
 * <p>tSubwayInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tSubwayInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SubwayLine" type="{}tSubwayLine"/&gt;
 *         &lt;element name="SubwayStation" type="{}tSubwayStation"/&gt;
 *         &lt;element name="Distance" type="{}tDistance"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSubwayInfo", propOrder = {
    "subwayLine",
    "subwayStation",
    "distance"
})
public class TSubwayInfo {

    @XmlElement(name = "SubwayLine", required = true)
    protected String subwayLine;
    @XmlElement(name = "SubwayStation", required = true)
    protected String subwayStation;
    @XmlElement(name = "Distance", required = true)
    protected BigInteger distance;

    /**
     * 获取subwayLine属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubwayLine() {
        return subwayLine;
    }

    /**
     * 设置subwayLine属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubwayLine(String value) {
        this.subwayLine = value;
    }

    /**
     * 获取subwayStation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubwayStation() {
        return subwayStation;
    }

    /**
     * 设置subwayStation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubwayStation(String value) {
        this.subwayStation = value;
    }

    /**
     * 获取distance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDistance() {
        return distance;
    }

    /**
     * 设置distance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDistance(BigInteger value) {
        this.distance = value;
    }

    
    public static List<TSubwayInfo> fromCommunity(HouseCommunity comm){
    	List<TSubwayInfo> list = new ArrayList<TSubwayInfo>();
    	//TODO: 小区暂时没有绑定地铁站信息
    	
    	return list;
    }
}
