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
 * <p>tHouseOrientation的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tHouseOrientation"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="朝东"/&gt;
 *     &lt;enumeration value="朝南"/&gt;
 *     &lt;enumeration value="朝西"/&gt;
 *     &lt;enumeration value="朝北"/&gt;
 *     &lt;enumeration value="南北通透"/&gt;
 *     &lt;enumeration value="东西朝向"/&gt;
 *     &lt;enumeration value="东南朝向"/&gt;
 *     &lt;enumeration value="东北朝向"/&gt;
 *     &lt;enumeration value="西北朝向"/&gt;
 *     &lt;enumeration value="西南朝向"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tHouseOrientation")
@XmlEnum
public enum THouseOrientation {

    朝东,
    朝南,
    朝西,
    朝北,
    南北通透,
    东西朝向,
    东南朝向,
    东北朝向,
    西北朝向,
    西南朝向,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static THouseOrientation fromValue(String v) {
        return valueOf(v);
    }
    
    public static THouseOrientation fromHBValue(String v){
		if ("东".equals(v))
			return THouseOrientation.朝东;
		else if ("西".equals(v))
			return THouseOrientation.朝西;
		else if ("南".equals(v))
			return THouseOrientation.朝南;
		else if ("北".equals(v))
			return THouseOrientation.朝北;
		else if ("东南".equals(v))
			return THouseOrientation.东南朝向;
		else if ("西南".equals(v))
			return THouseOrientation.西南朝向;
		else if ("东北".equals(v))
			return THouseOrientation.东北朝向;
		else if ("西北".equals(v))
			return THouseOrientation.西北朝向;
		else if ("东西".equals(v))
			return THouseOrientation.东西朝向;
		else if ("南北".equals(v))
			return THouseOrientation.南北通透;
		else
			return THouseOrientation.其它;
    }
}
