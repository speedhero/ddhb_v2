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
 * <p>tHouseSituation的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tHouseSituation"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="自住"/&gt;
 *     &lt;enumeration value="出租"/&gt;
 *     &lt;enumeration value="闲置"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tHouseSituation")
@XmlEnum
public enum THouseSituation {

    自住,
    出租,
    闲置,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static THouseSituation fromValue(String v) {
        return valueOf(v);
    }

    public static THouseSituation fromHBValue(String name) {
    	if(name!=null )name = name.trim();
    	if("业主住".equals(name))
    		return THouseSituation.自住;
    	else if("租客住".equals(name))
    		return THouseSituation.出租;
    	else if("主住".equals(name))
    		return THouseSituation.自住;
    	else if("空置".equals(name))
    		return THouseSituation.闲置;
    	else
    		return THouseSituation.其它;
    }
}
