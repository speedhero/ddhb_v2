//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseSecond;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.house.entity.HouseSecond;


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
 *         &lt;element name="RealestateID" type="{}tRealestateID"/&gt;
 *         &lt;element name="RealestateName" type="{}tRealestateName"/&gt;
 *         &lt;element name="RealestateCity" type="{}tRealestateCity" minOccurs="0"/&gt;
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
    "realestateID",
    "realestateName",
    "realestateCity",
    "realestateDistrict",
    "realestateStreet",
    "localBusiness"
})
public class TRealEstate extends BaseEntity {

    @XmlElement(name = "RealestateID", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String realestateID;
    @XmlElement(name = "RealestateName", required = true)
    protected String realestateName;
    @XmlElement(name = "RealestateCity")
    protected String realestateCity;
    @XmlElement(name = "RealestateDistrict")
    protected String realestateDistrict;
    @XmlElement(name="RealestateStreet")
    protected String realestateStreet;
    @XmlElement(name="LocalBusiness")
    protected String localBusiness;
    
    /**
     * 获取realestateID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealestateID() {
        return realestateID;
    }

    /**
     * 设置realestateID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealestateID(String value) {
        this.realestateID = value;
    }

    /**
     * 获取realestateName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealestateName() {
        return realestateName;
    }

    /**
     * 设置realestateName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealestateName(String value) {
        this.realestateName = value;
    }

    /**
     * 获取realestateCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealestateCity() {
        return realestateCity;
    }

    /**
     * 设置realestateCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealestateCity(String value) {
        this.realestateCity = value;
    }
    /**
     * 所在区域的名称
     * 后来他们要求加上去的  
     * modify by 何剑波 20150418
     * @param value
     * @return
     */
    public String getRealestateDistrict(String value){
    	return realestateDistrict;
    }

    public void setRealestateDistrict(String value){
    	this.realestateDistrict = value;
    } 
    /**
     * 新增20150611小区地址
     * @return
     */
    public String getRealestateStreet() {
		return realestateStreet;
	}

	public void setRealestateStreet(String realestateStreet) {
		this.realestateStreet = realestateStreet;
	}
	
	public String getRealestateDistrict() {
		return realestateDistrict;
	}
	/**
	 * 商圈
	 * @return
	 */
	public String getLocalBusiness() {
		return localBusiness;
	}

	public void setLocalBusiness(String localBusiness) {
		this.localBusiness = localBusiness;
	}

	public static TRealEstate fromHouse(HouseSecondHandHouse house){
    	TRealEstate r = new TRealEstate();
    	r.setRealestateCity("杭州");
//    	小区 为空
    	
    	if(house.getCommunity() == null){
    		System.out.println("community is NULL SecondHouse:" + house.getHouseId());
    		return null;
    	}
//    	r.setRealestateID("http://"+StringUtil.HSHB_DOMAIN+"/community/communityDetail/"+house.getCommunity().getErpId());
    	r.setRealestateID("http://"+StringUtil.HSHB_DOMAIN+"/xiaoqu/"+house.getCommunityId()+".html");
//      小区所在城市，对应小区所在城市
    	r.setRealestateName(strToCDATA(house.getCommunity().getCommunityName(), "未知"));
//    	对应小区的区域，区域名称无需在后方加区，直接写XX；没有该信息填写“未知”
    	r.setRealestateDistrict(strToCDATA(house.getCountyName(), "未知"));
    	//小区所在的地址
//    	r.setRealestateStreet(strToCDATA(house.getCommunity().getCommunityAddress(),"未知"));
    	r.setRealestateStreet(strToCDATA("","未知"));
    	//小区所在的商圈
    	String cbdName = null;
    	 if(StringUtil.isNotEmpty(house.getWebsiteName()))
    		cbdName = house.getWebsiteName();
    	r.setLocalBusiness(strToCDATA(cbdName,"未知"));
    	return r;
    }
}
