/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 房源列表显示模式枚举
 * 
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public enum EnumHouseListType {
	IMAGE(1),	//图片 
	TEXT(2);	//文本

	private final Integer value;

	EnumHouseListType(Integer value) { this.value = value; }
	public Integer getValue() { return value; }	
	public Integer value() { return value; }
	public String strValue(){ return String.valueOf(value); }
}
