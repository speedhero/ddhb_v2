//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:46:01 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.community;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.common.entity.Community;


/**
 * <p>tData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RealEstate" type="{}tRealEstate"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tData", propOrder = {
    "realEstate"
})
public class TData extends BaseEntity {

    @XmlElement(name = "RealEstate", required = true)
    protected TRealEstate realEstate;

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

    public static TData fromCommunity(HouseCommunity comm){
    	TData data = new TData();
    	TRealEstate t = TRealEstate.fromCommunity(comm);
    	if(t == null)	return null;
    	data.setRealEstate(t);

    	return data;
    }
}
