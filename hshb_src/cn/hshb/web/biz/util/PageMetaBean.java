/**
 * 
 */
package cn.hshb.web.biz.util;

/**
 * 页面顶部Meta信息对象，
 * 系统根据这个对象里面的信息生成Meta信息
 * 
 * @author ShengYoufu
 *
 */
public class PageMetaBean {
	public static String VIEW_TYPE_LIST = "LIST";
	public static String VIEW_TYPE_DETAIL = "DETAIL";
	
	public static String BEAN_NAME_MODULE = "pageMetaBean";	//PageMetaBean在Model中的名字
	
	/**
	 * 城区名称
	 */
	String countryName;
	
	/**
	 * 商圈名称
	 */
	String cbdName;
	
	/**
	 * 小区名称
	 */
	String communityName;
	
	/**
	 * 价格描述
	 */
	String price;
	
	/**
	 * 面积描述 
	 */
	String area;
	
	/**
	 * 模块名称
	 */
	String moduleName;
	
	/**
	 * 房源标题
	 */
	String houseTitle;
	
	/**
	 * 页面类别，是列表页还是详情页
	 */
	String viewType;
	
	/**
	 * 新房标题
	 */
	String newHouseTitle;
	/**
	 * 关键字
	 */
	String metaKeywords;
	/**
	 * 描述
	 */
	String metaDescription;
	
	
	public String getNewHouseTitle() {
		return newHouseTitle;
	}

	public void setNewHouseTitle(String newHouseTitle) {
		this.newHouseTitle = newHouseTitle;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getCountryName() {
		return countryName==null?"杭州":countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCbdName() {
		return cbdName;
	}

	public void setCbdName(String cbdName) {
		this.cbdName = cbdName;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHouseTitle() {
		return houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	
}
