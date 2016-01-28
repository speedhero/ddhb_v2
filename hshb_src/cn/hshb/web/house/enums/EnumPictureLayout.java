/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 房源照片类别枚举
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public enum EnumPictureLayout {
	COVER(1), 	//封面图片
	ALL(2), 		//全部
	NORMAIL(3);  //非封面

	private final Integer value;

	EnumPictureLayout(Integer value) {
		this.value = value;
	}

	public Integer value() {
		return value;
	}

	public static EnumPictureLayout fromValue(Integer v) {
		for (EnumPictureLayout t : EnumPictureLayout.values()) {
			if (t.value.equals(v)) { return t; }
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}
}
