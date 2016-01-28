/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * @author ShengYoufu
 *
 */
public enum EnumNewsType {
	COMPANY(1, "公司新闻"),
	INDUSTRY(2, "行业新闻");
	
	private Integer val;
	private String title;
	EnumNewsType(Integer value, String title){
		this.val = value;
		this.title = title;
	}

	public Integer value(){
		return val;
	}
	public Integer getVal() {
		return val;
	}
	public void setVal(Integer val) {
		this.val = val;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
