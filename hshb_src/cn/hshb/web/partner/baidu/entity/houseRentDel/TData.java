//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:33 PM CST 
//


package cn.hshb.web.partner.baidu.entity.houseRentDel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.huatek.hbwebsite.house.entity.BaiDuHouseRent;


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
 *         &lt;element name="RentDelete" type="{}tRentDelete"/&gt;
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
    "rentDelete"
})
public class TData {

    @XmlElement(name = "RentDelete", required = true)
    protected TRentDelete rentDelete;

    /**
     * 获取rentDelete属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TRentDelete }
     *     
     */
    public TRentDelete getRentDelete() {
        return rentDelete;
    }

    /**
     * 设置rentDelete属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TRentDelete }
     *     
     */
    public void setRentDelete(TRentDelete value) {
        this.rentDelete = value;
    }
    
    public static TData fromHouse(BaiDuHouseRent house){
    	TData data = new TData();
    	data.setRentDelete(TRentDelete.fromHouse(house));
    	return data;
    }
}
