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
 * <p>tInstallations的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tInstallations"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="独立阳台"/&gt;
 *     &lt;enumeration value="卫生间"/&gt;
 *     &lt;enumeration value="宽带"/&gt;
 *     &lt;enumeration value="电视"/&gt;
 *     &lt;enumeration value="暖气"/&gt;
 *     &lt;enumeration value="空调"/&gt;
 *     &lt;enumeration value="热水器"/&gt;
 *     &lt;enumeration value="洗衣机"/&gt;
 *     &lt;enumeration value="冰箱"/&gt;
 *     &lt;enumeration value="床"/&gt;
 *     &lt;enumeration value="衣柜"/&gt;
 *     &lt;enumeration value="沙发"/&gt;
 *     &lt;enumeration value="煤气"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tInstallations")
@XmlEnum
public enum TInstallations {

    独立阳台,
    卫生间,
    宽带,
    电视,
    暖气,
    空调,
    热水器,
    洗衣机,
    冰箱,
    床,
    衣柜,
    沙发,
    煤气,
    未知;

    public String value() {
        return name();
    }

    public static TInstallations fromValue(String v) {
        return valueOf(v);
    }

}
