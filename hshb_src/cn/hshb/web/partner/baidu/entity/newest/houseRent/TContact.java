//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 01:30:12 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseRent;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;



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
    protected String worksFor;
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

    public static List<TContact> fromHouse(HouseRentHouseExt house){
    	List<TContact> list = new ArrayList<TContact>();
//    	String publisherId = house.getBroker().getErpId();	//房源发布人ID
    	Boolean isFoundPublisher = false;
    	
    	List<HouseAppraise> haList = house.getAppraises();
    	if(haList != null || haList.size() != 0){
	    	for(HouseAppraise ha : haList){
	    		TContact c = new TContact();
	    		String flag = "0";//如果该房源有多个经纪人,主要经纪人标志为1
	    		if(ha.getPublisher() == null) continue; 
	    		if(ha.getPublisher().getErpId().equals(house.getPublisherId())){
	    			isFoundPublisher = true;
	    			flag = "1";
	    		}
	    		c.setFlag(flag);
	    		c.setGender(TGender.男);
    			c.setName(ha.getPublisher().getAgentName());
    			c.setProfile("http://" + StringUtil.HSHB_DOMAIN + "/frontImages" + ha.getPublisher().getPhotographPath());
    			c.setTelephone(strToCDATA(ha.getPublisher().getTelephone(),"18967113307"));
	    		c.setType(T1Type.中介);
	    		c.setWorksFor(StringUtil.HSHB_NAME);
//	    		if(ha.getBroker()!= null ){
//	    			isFoundPublisher = ha.getPublisher().getErpId().equals(house.getPublisher().getErpId())?true:false;
//	    		}
	    		list.add(c);
	    	}
    	}
    	if(!isFoundPublisher){
    		TContact c = new TContact();
  			c.setFlag( "1");
    		c.setGender(TGender.男);  //TODO: 这里需要修改网站经纪人信息
    		//因为获取的不是经纪人对象
    		if(StringUtil.isNotEmpty(house.getAgentName())){
	    		c.setName(strToCDATA(house.getAgentName(), "未知"));
	    		if(StringUtil.isNotEmpty(house.getPhotographPath()))
	    			c.setProfile("http://" + StringUtil.HSHB_DOMAIN + "/frontImages" + house.getPhotographPath());
	    		c.setTelephone(strToCDATA(house.getTelephone(), "18967113307")); //TODO :这里的号码是盛有富的小手机，以后可以换掉
    		}else{
    			System.out.println("No broker rentHouse: " + house.getHouseId());
    			return null;
    		}
    		c.setType(T1Type.中介);
    		c.setWorksFor(StringUtil.HSHB_NAME);
    		list.add(c);
    	}
    	return list;
    }      
}