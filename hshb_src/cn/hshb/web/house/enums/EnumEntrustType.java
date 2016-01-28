/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 在线反馈枚举
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public enum EnumEntrustType {
	BUY(1, "我要买房"), // 我要买房
	SALE(2, "我要卖房"), // 我要卖房
	RENT(3, "我要租房"), // 我要租房
	FOR_RENT(4, "我要出租"); // 我要出租
	
	private final Integer value;
	private final String caption;
	
	EnumEntrustType(Integer value, String caption) {
		this.value = value;
		this.caption = caption;
	}

	public Integer getValue() {
		return value;
	}

	public Integer value() {
		return value;
	}
	public String caption() {
		return caption;
	}

	public static EnumEntrustType fromValue(Integer v) {
		for (EnumEntrustType t : EnumEntrustType.values()) {
			if (t.value.equals(v)) { return t; }
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}
}
