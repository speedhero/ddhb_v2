//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:46:01 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.community;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tSchoolDistrict的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tSchoolDistrict"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="是"/&gt;
 *     &lt;enumeration value="否"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tSchoolDistrict")
@XmlEnum
public enum TSchoolDistrict {

    是,
    否,
    未知;

    public String value() {
        return name();
    }

    public static TSchoolDistrict fromValue(String v) {
        return valueOf(v);
    }

}
