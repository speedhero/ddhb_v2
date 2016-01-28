/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 二手房源标签枚举  <br/> <br/>
 * 
 * 房源标签在房源表中保存的一个整型值，该值的每一位分别对应一个标签，例如：  <br/>
 * 标签值=23，对应二进制是：10111，  <br/>
 * 根据下面枚举值可以知道：  <br/>
 * 婚房的标志值是 1，对应二进制： 00001  <br/>
 * 刚需小户的标志值是 2，对应二进制： 00010  <br/>
 * 超值性价的标志值是 4，对应二进制： 00100  <br/>
 * 学区房的标志值是16，对应二进制值：10000  <br/>
 * 而23（10111）刚好是 以上标志值按位或得到的值  00001 | 00010 | 00100 | 10000 = 10111 <br/>
 * 所以，该房源的标签为：婚房、刚需小户、超值性价、学区房  <br/>
 * 
 * @author ShengYoufu
 *
 */
public enum EnumHouseTag {
	HUN_FANG(1, "婚房", "#95B3D7"),
	GANG_XU_XIAO_HU(2, "刚需小户", "#93CDDC"),
	CHAO_CHI_XING_JIA(4, "超值性价", "#B2A2C7"),
	TOU_ZI_BAO_ZHI(8, "投资保值", "#FAC090"),
	XUE_QU_FANG(16, "学区房 ", "#41599C"),
	CI_XIN_FANG(32, "次新房", "#C3D69B"),
	MIAN_SHUANG_SHUI(64, "免双税", "#548DD4"),
	MIAN_GE_SHUI(128, "免个税 ", "#C3D69B"),
	MIAN_YIN_YE_SHUI(256, "免营业税", "#938953");

	private Integer value;	//标签值
	private String title;	//标签中文名称
	private String color;	//标签字体颜色

	private EnumHouseTag(Integer value, String title, String color){
		this.value = value;
		this.title = title;
		this.color = color;
	}
	
	/**
	 * 根据房源标签名称得到枚举对象
	 * @param tagName
	 * @return
	 */
	public EnumHouseTag fromTitle(String tagName){
		for(EnumHouseTag tag: EnumHouseTag.values()){
			if(tag.getTitle().equals(tagName)){
				return tag;
			}
		}
		throw new IllegalArgumentException("指定的房源标签名称["+tagName+"]不存在.");
	}
	
	/**
	 * 根据房源标签代码得到枚举对象
	 * @param val
	 * @return
	 */
	public EnumHouseTag fromValue(Integer val){
		for(EnumHouseTag tag: EnumHouseTag.values()){
			if(tag.value()==val){
				return tag;
			}
		}
		throw new IllegalArgumentException("指定的房源标签代码["+val+"]不存在.");
	}
	
	public Integer value() {
		return value;
	}
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
