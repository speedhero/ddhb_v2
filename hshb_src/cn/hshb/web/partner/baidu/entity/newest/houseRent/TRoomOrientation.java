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
 * <p>tRoomOrientation的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tRoomOrientation"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="东"/&gt;
 *     &lt;enumeration value="西"/&gt;
 *     &lt;enumeration value="南"/&gt;
 *     &lt;enumeration value="北"/&gt;
 *     &lt;enumeration value="东北"/&gt;
 *     &lt;enumeration value="东南"/&gt;
 *     &lt;enumeration value="西北"/&gt;
 *     &lt;enumeration value="西南"/&gt;
 *     &lt;enumeration value="南北"/&gt;
 *     &lt;enumeration value="东西"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tRoomOrientation")
@XmlEnum
public enum TRoomOrientation {

    东,
    西,
    南,
    北,
    东北,
    东南,
    西北,
    西南,
    南北,
    东西,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static TRoomOrientation fromValue(String v) {
        return valueOf(v);
    }
    
    public static TRoomOrientation fromHBValue(String name) {
    	if("东".equals(name))
    		return TRoomOrientation.东;
    	else if("南".equals(name))
    		return TRoomOrientation.南;
    	else if("西".equals(name))
    		return TRoomOrientation.西;
    	else if("北".equals(name))
    		return TRoomOrientation.北;
    	else if("东南".equals(name))
    		return TRoomOrientation.东南;
    	else if("东北".equals(name))
    		return TRoomOrientation.东北;
    	else if("西南".equals(name))
    		return TRoomOrientation.西南;
    	else if("西北".equals(name))
    		return TRoomOrientation.西北;
    	else if("东西".equals(name))
    		return TRoomOrientation.东西;
    	else if("南北".equals(name))
    		return TRoomOrientation.南北;
    	else
    		return TRoomOrientation.其它;
    }
}
