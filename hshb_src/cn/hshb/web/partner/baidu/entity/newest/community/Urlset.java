//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:46:01 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.community;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.huatek.hbwebsite.common.entity.Community;

import cn.hshb.web.biz.mybatis.model.PartnerBaiduCommunity;
import cn.hshb.web.partner.baidu.entity.BaseEntity;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="url" type="{}tUrl" maxOccurs="50000" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "url"
})
@XmlRootElement(name = "urlset")
public class Urlset extends BaseEntity {

    protected List<TUrl> url;

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TUrl }
     * 
     * 
     */
    public List<TUrl> getUrl() {
        if (url == null) {
            url = new ArrayList<TUrl>();
        }
        return this.url;
    }

    public static Urlset fromHouse(List<PartnerBaiduCommunity> commList){
    	Urlset urlset = new Urlset();
    	for(PartnerBaiduCommunity comm: commList){
    		//空  小区
    		if(comm.getCommunity() == null ){
    			comm.setStatus(Byte.parseByte("4"));
    			continue;
    		}
    		TUrl t =  TUrl.fromCommunity(comm.getCommunity());
    		if(t == null){
    			//小区有问题
    			comm.setStatus(Byte.parseByte("4"));
    			continue;
    		}
    		urlset.getUrl().add(t);
    		comm.setIsSync(Byte.parseByte("1"));
    	}
    	return urlset;
    }    
}
