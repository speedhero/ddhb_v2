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
import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.BaseEntity;

import com.huatek.hbwebsite.common.entity.Community;


/**
 * 
 * 				小区的地址
 * 			
 * 
 * <p>tPlace complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="tPlace"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="City" type="{}tCity"/&gt;
 *         &lt;element name="District" type="{}tDistrict"/&gt;
 *         &lt;element name="LocalBusiness" type="{}tLocalBusiness"/&gt;
 *         &lt;element name="Street" type="{}tStreet"/&gt;
 *         &lt;element name="Map" type="{}tMap" minOccurs="0"/&gt;
 *         &lt;element name="Coordinate" type="{}tCoordinate"/&gt;
 *         &lt;element name="CoordinateSys" type="{}tCoordinateSys"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tPlace", propOrder = {
    "city",
    "district",
    "localBusiness",
    "street",
    "map",
    "coordinate",
    "coordinateSys"
})
public class TPlace extends BaseEntity {

    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "District", required = true)
    protected String district;
    @XmlElement(name = "LocalBusiness", required = true)
    protected String localBusiness;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "Map")
    protected String map;
    @XmlElement(name = "Coordinate", required = true)
    protected String coordinate;
    @XmlElement(name = "CoordinateSys", required = true)
    protected String coordinateSys;

    /**
     * 获取city属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置city属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * 获取district属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置district属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * 获取localBusiness属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalBusiness() {
        return localBusiness;
    }

    /**
     * 设置localBusiness属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalBusiness(String value) {
        this.localBusiness = value;
    }

    /**
     * 获取street属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置street属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * 获取map属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMap() {
        return map;
    }

    /**
     * 设置map属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMap(String value) {
        this.map = value;
    }

    /**
     * 获取coordinate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * 设置coordinate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinate(String value) {
        this.coordinate = value;
    }

    /**
     * 获取coordinateSys属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinateSys() {
        return coordinateSys;
    }

    /**
     * 设置coordinateSys属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinateSys(String value) {
        this.coordinateSys = value;
    }

    public static TPlace fromCommunity(HouseCommunity comm){
    	if(comm.getLocation() == null){
    			System.out.println("commonty's location is NULL community:" + comm.getCommunityNo());
    			return null;
    		}
//    	if(comm.getCbd() == null) return null;
//    	if(comm.getCbd().getCounty() == null)return null;
    	TPlace p = new TPlace();
    	p.setCity("杭州");  //TODO: 城市目前只有杭州，暂时写死
    	//数据库小区默认坐标为:30.201989,120.188752
    	p.setCoordinate(strToCDATA(getLocation(comm.getLocation()), "120.188752,30.201989"));  //TODO:这里的默认坐标是随便取的，下次改成杭州市政府所在坐标
    	//使用的是腾讯地图,值也为3
    	p.setCoordinateSys(TCoordinateSys.GOOGLE.value());
//    	if(comm.getCbd()!=null && comm.getCbd().getCounty()!=null)
//    		p.setDistrict(strToCDATA(comm.getCbd().getCounty().getCountyName(), "未知"));
//    	else
//    		p.setDistrict("未知");
    	//所在城区
    	if(comm.getCounty() != null)
    		p.setDistrict(strToCDATA(comm.getCounty().getCountyName(), "未知"));
    	else
    		p.setDistrict("未知");
    	//所在的商圈
//    	if(comm.getCbd()!=null && comm.getCbd().getCbdName()!=null){
//    		p.setLocalBusiness(strToCDATA(comm.getCbd().getCbdName(), "未知"));
//    	}else{
//    		p.setLocalBusiness("未知");
//    	}
    	if(comm.getCbdWebsite() != null){
    		p.setLocalBusiness(strToCDATA(comm.getCbdWebsite().getName(),"未知"));
    	}else{
    		p.setLocalBusiness("未知");
    	}
    	//p.setMap(value); 
    	p.setStreet("未知");
    	return p;
    }
    private static String getLocation(String location){
    	if(StringUtil.isBlank(location))
    		return null;
//   		String content = location.substring(0,location.indexOf(","));
//   		content = location.substring(location.indexOf(",")+1,location.length()) + "," + content;
    	String[] content = location.split(",");
    	return content[1]+","+content[0];
    }
}
