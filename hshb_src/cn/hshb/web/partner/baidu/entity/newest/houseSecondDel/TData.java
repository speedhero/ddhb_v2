//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:31 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseSecondDel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseSecond;

import com.huatek.hbwebsite.house.entity.BaiDuHouseSecond;


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
 *         &lt;element name="SellDelete" type="{}tSellDelete"/&gt;
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
    "sellDelete"
})
public class TData {

    @XmlElement(name = "SellDelete", required = true)
    protected TSellDelete sellDelete;

    /**
     * 获取sellDelete属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TSellDelete }
     *     
     */
    public TSellDelete getSellDelete() {
        return sellDelete;
    }

    /**
     * 设置sellDelete属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TSellDelete }
     *     
     */
    public void setSellDelete(TSellDelete value) {
        this.sellDelete = value;
    }
    
    public static TData fromHouse(PartnerBaiduHouseSecond house){
    	TData tData = new TData();
    	tData.setSellDelete(TSellDelete.fromHouse(house));
    	return tData;
    	
    }
//    public static TData fromHouse(HouseSecondDel house){
//    	TData data = new TData();
//    	data.setSellDelete(TSellDelete.fromHouse(house));
//    	return data;
//    }
}
