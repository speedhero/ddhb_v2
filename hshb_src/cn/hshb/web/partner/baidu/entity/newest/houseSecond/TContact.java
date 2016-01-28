//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseSecond;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.house.entity.HouseSecond;


/**
 * 
 * 				房源的联系人，个人房源填写房东信息；中介房源填写经纪人信息。该标签可以重复出现，填写多个经纪人信息。
 * 			
 * 
 * <p>tContact complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tContact"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Type" type="{}t1Type"/&gt;
 *         &lt;element name="name" type="{}t1Name"/&gt;
 *         &lt;element name="telephone" type="{}tTelephone"/&gt;
 *         &lt;element name="worksFor" type="{}tWorksFor"/&gt;
 *         &lt;element name="Profile" type="{}tProfile" minOccurs="0"/&gt;
 *         &lt;element name="Gender" type="{}tGender" minOccurs="0"/&gt;
 *         &lt;element name="flag" type="{}tFlag" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tContact", propOrder = {
    "type",
    "name",
    "telephone",
    "worksFor",
    "profile",
    "gender",
    "flag"
})
public class TContact extends BaseEntity {

    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected T1Type type;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String telephone;
    @XmlElement(required = true)
    protected String worksFor="豪世华邦";
    @XmlElement(name = "Profile")
    @XmlSchemaType(name = "anyURI")
    protected String profile;
    @XmlElement(name = "Gender")
    @XmlSchemaType(name = "string")
    protected TGender gender;
    protected String flag;

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link T1Type }
     *     
     */
    public T1Type getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link T1Type }
     *     
     */
    public void setType(T1Type value) {
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
     * 获取telephone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置telephone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * 获取worksFor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorksFor() {
        return worksFor;
    }

    /**
     * 设置worksFor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorksFor(String value) {
        this.worksFor = value;
    }

    /**
     * 获取profile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 设置profile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * 获取gender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TGender }
     *     
     */
    public TGender getGender() {
        return gender;
    }

    /**
     * 设置gender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TGender }
     *     
     */
    public void setGender(TGender value) {
        this.gender = value;
    }

    /**
     * 获取flag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置flag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlag(String value) {
        this.flag = value;
    }

    public static List<TContact> fromHouse(HouseSecondHandHouse house){
    	List<TContact> list = new ArrayList<TContact>();
    	if(house.getPublisher() == null){
    		System.out.println("-------------房源经纪人为空");
    		return null;
    	}    	
//    	String publisherId = house.getBroker().getErpId();	//房源发布人ID
    	Boolean isFoundPublisher = false;
    	List<HouseAppraise> appList = house.getAppraises();
    	for(HouseAppraise app: appList){
    		TContact c = new TContact(); 
    		if(app.getPublisher() == null) continue;
    		
    		if(app.getPublisher().getErpId().equals(house.getPublisherId())){
	    		isFoundPublisher = true;
	    		c.setFlag("1");
    		}else
    			c.setFlag("0");
// 			house.getBaiDuHouseSecond().setBrokerName(strToCDATA(app.getBroker().getBname(), "未知"));
// 			house.getBaiDuHouseSecond().setTelephone(strToCDATA(app.getBroker().getTelephone(), "18967113307"));
    		c.setGender(TGender.男);  //TODO: 这里需要修改网站经纪人信息
    		c.setName(strToCDATA(app.getPublisher().getAgentName(), "未知"));
    		//有些经纪人没有图片,则不显示图片了
    		if(StringUtil.isNotEmpty(app.getPublisher().getPhotographPath()))
    			c.setProfile( "http://" + StringUtil.HSHB_DOMAIN + "/frontImages" + app.getPublisher().getPhotographPath());
    		c.setTelephone(strToCDATA(app.getPublisher().getTelephone(), "18967113307"));
    		c.setType(T1Type.中介);
    		c.setWorksFor(StringUtil.HSHB_NAME);
    		list.add(c);
    	}
    	if(!isFoundPublisher){
    		TContact c = new TContact();
    		c.setFlag( "1");
    		c.setGender(TGender.男);  //TODO: 这里需要修改网站经纪人信息
//    		house.getBaiDuHouseSecond().setBrokerName(strToCDATA(house.getBroker().getBname(), "未知"));
//    		house.getBaiDuHouseSecond().setTelephone(strToCDATA(house.getBroker().getTelephone(), "18967113307"));
	    	c.setName(strToCDATA(house.getPublisher().getAgentName(), "未知"));
	    	if(StringUtil.isNotEmpty(house.getPublisher().getPhotographPath()))
	    		c.setProfile("http://" + StringUtil.HSHB_DOMAIN + "/frontImages" + house.getPublisher().getPhotographPath());
	    	c.setTelephone(strToCDATA(house.getPublisher().getTelephone(), "18967113307"));
    		c.setType(T1Type.中介);
    		c.setWorksFor(StringUtil.HSHB_NAME);
    		list.add(c);
    	}
    	return list;
    }
}
