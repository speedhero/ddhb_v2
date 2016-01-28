/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 房源对比之房源类型
 * 
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public enum EnumCompareHouseType {
	SALE("S"),	//买卖房源
	RENT("R");	//租赁房源

	private final String value;

	EnumCompareHouseType(String value) { this.value = value; }
	public String getValue() { return value; }
}
