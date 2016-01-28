/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 数据同步操作类型枚举
 * 
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public enum EnumDataSyncOperateType {
	ADD("1", "新增"),
	UPDATE("2", "更新"),
	DELETE("3", "删除");
	
	private final String code;
	private final String name;

	EnumDataSyncOperateType(String code, String name) { 
		this.code = code; 
		this.name = name;
	}
	
	public String getCode() { return code; }
	public String getName(){ return name; }

}
