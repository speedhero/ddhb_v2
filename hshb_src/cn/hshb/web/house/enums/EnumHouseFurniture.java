/**
 * 
 */
package cn.hshb.web.house.enums;

/**
 * 出租屋内设施枚举<br/>
 *  
 * 出租屋内设施在房源表中保存的一个整型值，该值的每一位分别对应一个设施，例如：  <br/>
 * 标签值=23，对应二进制是：10111，  <br/>
 * 根据下面枚举值可以知道：  <br/>
 * 空调的标志值是 1，对应二进制： 00001  <br/>
 * 冰箱的标志值是 2，对应二进制： 00010  <br/>
 * 电视的标志值是 4，对应二进制： 00100  <br/>
 * 热水器的标志值是16，对应二进制值：10000  <br/>
 * 而23（10111）刚好是 以上标志值按位或得到的值  00001 | 00010 | 00100 | 10000 = 10111 <br/>
 * 所以，该房源的设施为：空调、冰箱、电视、热水器  <br/>
 * 
 * @author ShengYoufu
 *
 */
public enum EnumHouseFurniture {
	KONG_TIAO(1, "空调", "/furniture/air-condition_y.png", "/furniture/air-condition_n.png"),
	BING_XIANG(2, "冰箱", "/furniture/freezer_y.png", "/furniture/freezer_n.png"),
	DIAN_SHI(4, "电视", "/furniture/television_y.png", "/furniture/television_n.png"),
	XI_YI_JI(8, "洗衣机", "/furniture/washer_y.png", "/furniture/washer_n.png"),
	RI_SHUI_QI(16, "热水器", "/furniture/bath_y.png", "/furniture/bath_n.png"),
	ZHAO_JU(32, "灶具", "/furniture/natgas_y.png", "/furniture/natgas_n.png"),
	CHUANG(64, "床", "/furniture/bed_y.png", "/furniture/bed_n.png"),
	JIA_JU(128, "家具", "/furniture/facility_default_y.png", "/furniture/facility_default_n.png"),
	KUAN_DAI(256, "宽带", "/furniture/network_y.png", "/furniture/network_n.png");
	
	private Integer value;	//设施值
	private String title;	//设施中文名称
	private String iconUri;	//设施图标URI
	private String disableIconUri;	//设施图标URI

	private EnumHouseFurniture(Integer value, String title, String iconUri, String disableIconUri ){
		this.value = value;
		this.title = title;
		this.iconUri = iconUri;
		this.disableIconUri = disableIconUri;
	}
	
	/**
	 * 根据房源标签名称得到枚举对象
	 * @param tagName
	 * @return
	 */
	public EnumHouseFurniture fromTitle(String tagName){
		for(EnumHouseFurniture tag: EnumHouseFurniture.values()){
			if(tag.getTitle().equals(tagName)){
				return tag;
			}
		}
		throw new IllegalArgumentException("指定的出租屋内附属设施名称["+tagName+"]不存在.");
	}

	/**
	 * 根据房源标签代码得到枚举对象
	 * @param val
	 * @return
	 */
	public EnumHouseFurniture fromValue(Integer val){
		for(EnumHouseFurniture tag: EnumHouseFurniture.values()){
			if(tag.value()==val){
				return tag;
			}
		}
		throw new IllegalArgumentException("指定的出租屋内附属设施代码["+val+"]不存在.");
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

	public String getIconUri() {
		return iconUri;
	}

	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}

	public String getDisableIconUri() {
		return disableIconUri;
	}

	public void setDisableIconUri(String disableIconUri) {
		this.disableIconUri = disableIconUri;
	}
}
