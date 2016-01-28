//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 01:30:12 PM CST
//

package cn.hshb.web.partner.baidu.entity.houseRent;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * tRentalType的 Java 类。
 * 
 * <p>
 * 以下模式片段指定包含在此类中的预期内容。
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="tRentalType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="整租"/&gt;
 *     &lt;enumeration value="合租"/&gt;
 *     &lt;enumeration value="床位"/&gt;
 *     &lt;enumeration value="短租"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tRentalType")
@XmlEnum
public enum TRentalType {

	整租, 
	合租, 
	床位, 
	短租, 
	其它, 
	未知;

	public String value() {
		return name();
	}

	public static TRentalType fromValue(String v) {
		return valueOf(v);
	}

	public static TRentalType fromHBValue(String name) {
		if ("整租".equals(name))
			return TRentalType.整租;
		else if ("合租".equals(name))
			return TRentalType.合租;
		else
			return TRentalType.其它;
	}
}
