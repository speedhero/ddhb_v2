//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.houseSecond;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.hbwebsite.house.entity.HouseSecond;


/**
 * 
 * 				url标记每条信息的开始和结束
 * 			
 * 
 * <p>tUrl complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tUrl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="loc" type="{}tLoc"/&gt;
 *         &lt;element name="data" type="{}tData"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tUrl", propOrder = {
    "loc",
    "data"
})
public class TUrl {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String loc;
    @XmlElement(required = true)
    protected TData data;

    /**
     * 获取loc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoc() {
        return loc;
    }

    /**
     * 设置loc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoc(String value) {
        this.loc = value;
    }

    /**
     * 获取data属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TData }
     *     
     */
    public TData getData() {
        return data;
    }

    /**
     * 设置data属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TData }
     *     
     */
    public void setData(TData value) {
        this.data = value;
    }

    public static TUrl fromHouse(HouseSecond house){

    	TData data = TData.fromHouse(house);
    	if(data==null) return null;
    	
    	TUrl url = new TUrl();

    	url.setData(data);
    	
    	String loc = "http://"+StringUtil.HSHB_DOMAIN+"/baidu/houseSecond/"+house.getHouseNo();
    	if(house.getBroker()!=null){
    		//用于删除 
    		house.getBaiDuHouseSecond().setBrokerErpId(house.getBroker().getErpId());
    		loc += "/"+house.getBroker().getErpId();
    	}
    	url.setLoc(loc);
    	
    	return url;
    }
}
