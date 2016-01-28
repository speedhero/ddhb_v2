//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 01:30:12 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseRent;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tDecoration的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tDecoration"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="豪华装修"/&gt;
 *     &lt;enumeration value="精装修"/&gt;
 *     &lt;enumeration value="中等装修"/&gt;
 *     &lt;enumeration value="简单装修"/&gt;
 *     &lt;enumeration value="毛坯"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tDecoration")
@XmlEnum
public enum TDecoration {

    豪华装修,
    精装修,
    中等装修,
    简单装修,
    毛坯,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static TDecoration fromValue(String v) {
        return valueOf(v);
    }

    public static TDecoration fromHBValue(String name) {
    	if("豪装".equals(name)){
    		return fromValue("豪华装修");
    	}else if("精装".equals(name)){
    		return fromValue("精装修");
    	}else if("中装".equals(name)){
    		return fromValue("中等装修");
    	}else if("简装".equals(name)){
    		return fromValue("简单装修");
    	}else if("毛坯".equals(name)){
    		return fromValue("毛坯");
    	}else{
    		return fromValue("其它");
    	}
    }
}
