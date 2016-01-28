/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 房源类型枚举
 * 
 * @author Administrator
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public enum EnumHouseType {
	SALE(1), // 售
	RENT(2), // 租
	COMMUNITY(3), //小区
	NEW(4); // 新房

	private final Integer value;

	EnumHouseType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public Integer value() {
		return value;
	}

	public static EnumHouseType fromValue(Integer v) {
		for (EnumHouseType t : EnumHouseType.values()) {
			if (t.value.equals(v)) { return t; }
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}
}
