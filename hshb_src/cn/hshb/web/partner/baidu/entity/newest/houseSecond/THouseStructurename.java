//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:47:29 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.houseSecond;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import cn.hshb.web.common.util.StringUtil;


/**
 * <p>tHouseStructurename的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="tHouseStructurename"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="一居"/&gt;
 *     &lt;enumeration value="两居"/&gt;
 *     &lt;enumeration value="三居"/&gt;
 *     &lt;enumeration value="四居"/&gt;
 *     &lt;enumeration value="五居"/&gt;
 *     &lt;enumeration value="六居"/&gt;
 *     &lt;enumeration value="七居"/&gt;
 *     &lt;enumeration value="八居"/&gt;
 *     &lt;enumeration value="九居"/&gt;
 *     &lt;enumeration value="十居"/&gt;
 *     &lt;enumeration value="其它"/&gt;
 *     &lt;enumeration value="未知"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tHouseStructurename")
@XmlEnum
public enum THouseStructurename {

    一居,
    两居,
    三居,
    四居,
    五居,
    六居,
    七居,
    八居,
    九居,
    十居,
    其它,
    未知;

    public String value() {
        return name();
    }

    public static THouseStructurename fromValue(String v) {
        return valueOf(v);
    }

    public static THouseStructurename fromHBValue(int roomNum) {
    	String v = StringUtil.numberToChinese(roomNum)+"居";
    	if("二居".equals(v)) v = "两居";
    	if("零居".equals(v)) v = "其它";
    	return THouseStructurename.fromValue(v);
    }
}
