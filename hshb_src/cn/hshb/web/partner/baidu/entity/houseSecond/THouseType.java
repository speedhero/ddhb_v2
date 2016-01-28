//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.houseSecond;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tHouseType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tHouseType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="普通住宅"/&gt;
 *     &lt;enumeration value="公寓"/&gt;
 *     &lt;enumeration value="别墅"/&gt;
 *     &lt;enumeration value="平房"/&gt;
 *     &lt;enumeration value="经济适用房"/&gt;
 *     &lt;enumeration value="商住两用"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tHouseType")
@XmlEnum
public enum THouseType {

    普通住宅,
    公寓,
    别墅,
    平房,
    经济适用房,
    商住两用,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static THouseType fromValue(String v) {
        return valueOf(v);
    }

    public static THouseType fromHBValue(String name) {
    	if("公寓 普通住宅".equals(name))
    		return THouseType.普通住宅;
    	else if("商用".equals(name))
    		return THouseType.商住两用;
    	else if("商住两用".equals(name))
    		return THouseType.商住两用;
    	else
    		return THouseType.其它;

    }
}
