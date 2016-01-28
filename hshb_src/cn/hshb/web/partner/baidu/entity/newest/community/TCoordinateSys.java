//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.03.12 时间 12:46:01 PM CST 
//


package cn.hshb.web.partner.baidu.entity.newest.community;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tCoordinateSys的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
		&lt;xsd:annotation&gt;
			&lt;xsd:documentation&gt;
				填写坐标系所属类型的数字代码（0-8有效）。
				1：GPS设备获取的角度坐标; 
				2：GPS获取的米制坐标、sogou地图所用坐标; 
				3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标 
				4：3中列表地图坐标对应的米制坐标
				5：百度地图采用的经纬度坐标
				6：百度地图采用的米制坐标 
				7：mapbar地图坐标; 
				8：51地图坐标。
				如果没有坐标信息，此项填0
			&lt;/xsd:documentation&gt;
		&lt;/xsd:annotation&gt;<br/>
 * &lt;simpleType name="tCoordinateSys"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
			&lt;xsd:enumeration value="0" /&gt;
			&lt;xsd:enumeration value="1" /&gt;
			&lt;xsd:enumeration value="2" /&gt;
			&lt;xsd:enumeration value="3" /&gt;
			&lt;xsd:enumeration value="4" /&gt;
			&lt;xsd:enumeration value="5" /&gt;
			&lt;xsd:enumeration value="6" /&gt;
			&lt;xsd:enumeration value="7" /&gt;
			&lt;xsd:enumeration value="8" /&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tCoordinateSys")
@XmlEnum
public enum TCoordinateSys {

	  @XmlEnumValue("0")
	  NONE("0"),
    @XmlEnumValue("1")
    GPS_ANGLE("1"),
    @XmlEnumValue("2")
	  SOGOU("2"),
    @XmlEnumValue("3")
    GOOGLE("3"),
    @XmlEnumValue("4")
    GOOGLE_METRIC("4"),
    @XmlEnumValue("5")
    BAIDU("5"),
    @XmlEnumValue("6")
    BAIDU_METRIC("6"),
    @XmlEnumValue("7")
    MAPBAR("7"),
    @XmlEnumValue("8")
    _51DITU("8");
    
    private final String value;

    TCoordinateSys(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TCoordinateSys fromValue(String v) {
        for (TCoordinateSys c: TCoordinateSys.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
