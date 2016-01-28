//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseSecond;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tSellingPayment的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tSellingPayment"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="全款"/&gt;
 *     &lt;enumeration value="公积金贷款"/&gt;
 *     &lt;enumeration value="商业贷款"/&gt;
 *     &lt;enumeration value="组合贷款"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tSellingPayment")
@XmlEnum
public enum TSellingPayment {

    全款,
    公积金贷款,
    商业贷款,
    组合贷款,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static TSellingPayment fromValue(String v) {
        return valueOf(v);
    }

}
