/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 数据同步结果代码枚举
 * 
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public enum EnumDataSyncResult {
	SUCCESS("0", "成功"),	//成功
	UNKNOWN_OPERATE("1", "未知的操作代码"),
	DB_ERROR("2", "数据库操作异常"),
	INVALID_XML_FORMAT("3", "XML文件格式有误"),
	NO_PERMISSION("4", "没有调用权限"),
	DATA_TYPE_NO_EXIST("5", "指定的数据同步类型不存在"),
	SAVE_PIC_ERROR("6", "保存图片失败"),
	UNKNOWN_ERROR("9", "未知错误");
	
	private final String code;
	private final String message;

	EnumDataSyncResult(String code, String message) { 
		this.code = code; 
		this.message = message;
	}
	public String getCode() { return code; }
	public String getMessage(){ return message; }
}
