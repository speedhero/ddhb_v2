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
import javax.xml.bind.annotation.XmlType;

import com.huatek.hbwebsite.house.entity.HouseSecond;


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
 *         &lt;element name="SellingInfo" type="{}tSellingInfo"/&gt;
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
    "sellingInfo"
})
public class TData {

    @XmlElement(name = "SellingInfo", required = true)
    protected TSellingInfo sellingInfo;

    /**
     * 获取sellingInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TSellingInfo }
     *     
     */
    public TSellingInfo getSellingInfo() {
        return sellingInfo;
    }

    /**
     * 设置sellingInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TSellingInfo }
     *     
     */
    public void setSellingInfo(TSellingInfo value) {
        this.sellingInfo = value;
    }
    
    public static TData fromHouse(HouseSecond house){
    	TData dat = new TData();
    	TSellingInfo sell = TSellingInfo.fromHouse(house);
    	if(sell == null) return null;
    	dat.setSellingInfo(sell);
    	return dat;
    }

}
