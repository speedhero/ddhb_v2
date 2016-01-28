/**
 * 
 */
package cn.hshb.web.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.jfree.util.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.service.CommonCbdService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.util.HouseQueryStrParser;
import cn.hshb.web.biz.util.PageMetaBean;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author ShengYoufu
 *
 */
public class HousePageMetaTag extends HshbBaseSimpleTag {
	
	private String infoType;  //信息类型，用以控制输入的内格式，取值：title、keyword或description
	private String infoContent;  //信息内容
	private static Map<String, Map<String, String>> TEMPL_SALE = new HashMap<String, Map<String, String>>();
	private static Map<String, Map<String, String>> TEMPL_RENT = new HashMap<String, Map<String, String>>();
	private static Map<String, Map<String, String>> TEMPL_NEW = new HashMap<String, Map<String, String>>();
	private static Map<String, Map<String, String>> TEMPL_COMMUNITY = new HashMap<String, Map<String, String>>();
	private static Map<String, Map<String, Map<String, String>>> TEMPL = new HashMap<String, Map<String, Map<String, String>>>();
	static {
		Map<String, String> TEMPL_SALE_LIST = new HashMap<String, String>();
		TEMPL_SALE_LIST.put("title", "${countyName}${cbdName}${price}${area}二手房_杭州二手房推荐-豪世华邦，专业因为懂你");
		TEMPL_SALE_LIST.put("keywords", "${countyName}${cbdName}${price}${area}二手房,${countyName}${cbdName}${price}${area}二手房信息,${countyName}${cbdName}${price}${area}买房,${countyName}${cbdName}${price}${area}二手房买卖,${countyName}${cbdName}${price}${area}二手房交易,${countyName}${cbdName}${price}${area}二手房出售,${countyName}${cbdName}${price}${area}二手房价格");
		TEMPL_SALE_LIST.put("description", "豪世华邦${countyName}${cbdName}${price}${area}二手房网在${countyName}${cbdName}${price}${area}二手房市场用户口碑极佳，为${countyName}${cbdName}${price}${area}二手房用户使用率和满意度极好的网络二手房交易及信息平台，同时也是${countyName}${cbdName}${price}${area}二手房用户重点推荐使用的网站。豪世华邦${countyName}${cbdName}${price}${area}二手房网提供${countyName}${cbdName}${price}${area}二手房最新房源，房源真实可靠无虚假无重复，让每一个用户安心满意的进行${countyName}${cbdName}${price}${area}二手房交易，营造最值得用户信赖的${countyName}${cbdName}${price}${area}二手房交易平台。");
		TEMPL_SALE.put(PageMetaBean.VIEW_TYPE_LIST, TEMPL_SALE_LIST);
		Map<String, String> TEMPL_SALE_DETAIL = new HashMap<String, String>();
		TEMPL_SALE_DETAIL.put("title", "${cbdName}${communityName}二手房-豪世华邦，专业因为懂你");
		TEMPL_SALE_DETAIL.put("keywords", "${communityName}${houseTitle}二手房源推荐，杭州${cbdName} ${communityName}热门二手房房源,豪世华邦,华邦地产");
		TEMPL_SALE_DETAIL.put("description", "豪世华邦官网提供${communityName}${houseTitle}二手房源，推荐最新杭州${cbdName} ${communityName}二手房源。");
		TEMPL_SALE.put(PageMetaBean.VIEW_TYPE_DETAIL, TEMPL_SALE_DETAIL);
		TEMPL.put("二手房", TEMPL_SALE);
		
		Map<String, String> TEMPL_RENT_LIST = new HashMap<String, String>();
		TEMPL_RENT_LIST.put("title", "${countyName}${cbdName}${price}${area} 租房_杭州租房推荐-豪世华邦，专业因为懂你");
		TEMPL_RENT_LIST.put("keywords", "${countyName}${cbdName}${price}${area} 租房,${countyName}${cbdName}${price}${area} 租房信息,${countyName}${cbdName}${price}${area} 租房,${countyName}${cbdName}${price}${area} 租房租赁,${countyName}${cbdName}${price}${area} 租房交易,${countyName}${cbdName}${price}${area} 租房出租,${countyName}${cbdName}${price}${area} 租房价格");
		TEMPL_RENT_LIST.put("description", "豪世华邦${countyName}${cbdName}${price}${area} 租房网在${countyName}${cbdName}${price}${area} 租房市场用户口碑极佳，为${countyName}${cbdName}${price}${area} 租房用户使用率和满意度极好的网络租房交易及信息平台，同时也是${countyName}${cbdName}${price}${area} 租房用户重点推荐使用的网站。豪世华邦${countyName}${cbdName}${price}${area} 租房网提供${countyName}${cbdName}${price}${area} 租房最新房源，房源真实可靠无虚假无重复，让每一个用户安心满意的进行${countyName}${cbdName}${price}${area} 租房交易，营造最值得用户信赖的${countyName}${cbdName}${price}${area} 租房交易平台。");
		TEMPL_RENT.put(PageMetaBean.VIEW_TYPE_LIST, TEMPL_RENT_LIST);
		Map<String, String> TEMPL_RENT_DETAIL = new HashMap<String, String>();
		TEMPL_RENT_DETAIL.put("title", "${cbdName}${communityName}出租房-豪世华邦，专业因为懂你");
		TEMPL_RENT_DETAIL.put("keywords", "${communityName}${houseTitle}出租房源推荐，杭州${cbdName}${communityName}热门出租房房源,豪世华邦,华邦地产");
		TEMPL_RENT_DETAIL.put("description", "豪世华邦官网提供${communityName}${houseTitle}出租房源，推荐最新杭州${cbdName}${communityName}出租房源。");
		TEMPL_RENT.put(PageMetaBean.VIEW_TYPE_DETAIL, TEMPL_RENT_DETAIL);
		TEMPL.put("出租房", TEMPL_RENT);

		Map<String, String> TEMPL_COMMUNITY_LIST = new HashMap<String, String>();
		TEMPL_COMMUNITY_LIST.put("title", "杭州${countyName}${cbdName}小区_杭州${countyName}${cbdName}${price}二手房推荐，优质出租房-豪世华邦，专业因为懂");
		TEMPL_COMMUNITY_LIST.put("keywords", "${countyName}${cbdName}${price}小区, ${countyName}${cbdName}${price}小区房价, ${countyName}${cbdName}${price}小区房源");
		TEMPL_COMMUNITY_LIST.put("description", "豪世华邦提供${countyName}${cbdName}${price}小区房价走势数据，最新${countyName}${cbdName}${price}小区房源房源以及成交信息，为用户展示最新${countyName}${cbdName}${price}小区二手房、${countyName}${cbdName}小区租房和${countyName}${cbdName}${price}小区房价等相关消息。");
		TEMPL_COMMUNITY.put(PageMetaBean.VIEW_TYPE_LIST, TEMPL_COMMUNITY_LIST);
		Map<String, String> TEMPL_COMMUNITY_DETAIL = new HashMap<String, String>();
		TEMPL_COMMUNITY_DETAIL.put("title", "${countyName} ${cbdName}二手房 出租房-豪世华邦，专业因为懂你");
		TEMPL_COMMUNITY_DETAIL.put("keywords", "杭州${countyName} ${cbdName}热门二手房源、出租房源");
		TEMPL_COMMUNITY_DETAIL.put("description", "豪世华邦房产网推荐最新杭州${countyName} ${cbdName}二手房、出租房源");
		TEMPL_COMMUNITY.put(PageMetaBean.VIEW_TYPE_DETAIL, TEMPL_COMMUNITY_DETAIL);
		TEMPL.put("小区", TEMPL_COMMUNITY);
		
		Map<String, String> TEMPL_NEW_LIST = new HashMap<String, String>();
		TEMPL_NEW_LIST.put("title", "杭州新房_杭州新楼盘开盘信息-豪世华邦，专业因为懂你");
		TEMPL_NEW_LIST.put("keywords", "杭州新房，杭州新楼盘开盘信息，杭州新房开盘信息，杭州新开盘楼盘项目");
		TEMPL_NEW_LIST.put("description", "豪世华邦新房频道第一时间提供杭州新房开盘信息，关注最新杭州新房开盘信息和杭州新楼盘开盘项目。");
		TEMPL_NEW.put(PageMetaBean.VIEW_TYPE_LIST, TEMPL_NEW_LIST);
		Map<String, String> TEMPL_NEW_DETAIL = new HashMap<String, String>();
		TEMPL_NEW_DETAIL.put("title", "杭州新房_杭州新楼盘开盘信息-豪世华邦，专业因为懂你");
		TEMPL_NEW_DETAIL.put("keywords", "${houseTitle} 新房频道第一时间推荐，杭州新房开盘信息，杭州新开盘楼盘项目");
		TEMPL_NEW_DETAIL.put("description", "豪世华邦官网提供${houseTitle}新房，关注最新杭州新房开盘信息和杭州新楼盘开盘项目。");
		TEMPL_NEW.put(PageMetaBean.VIEW_TYPE_DETAIL, TEMPL_NEW_DETAIL);
		TEMPL.put("新房", TEMPL_NEW);

	}
	
	//原有查询参数
	private String params;

	@Override
	public void doTag() throws JspException, IOException{
		if(StringUtil.isNotEmpty(infoContent)){
			write(infoContent);
			return;
		}
		
		PageMetaBean meta = (PageMetaBean)getRequest().getAttribute(PageMetaBean.BEAN_NAME_MODULE);
		if(meta==null) return;

		String _viewType = this.checkViewType();
		if(!StringUtil.isEmpty(meta.getViewType()))
			_viewType = meta.getViewType();
		
		if("title".equalsIgnoreCase(infoType))
			write(this.getTitleContent(_viewType));
		else if("keyword".equalsIgnoreCase(infoType))
			write(this.getKeywordContent(_viewType));
		else if("description".equalsIgnoreCase(infoType))
			write(this.getDescriptionContent(_viewType));
	}
	
	/**
	 * 检测页面展示模式，列表还是详情
	 * @return
	 */
	private String checkViewType(){
		String _viewType = "LIST";	//展示模式，默认是列表

		String uri = getRequest().getRequestURI();
		if(uri.endsWith(".html"))
			_viewType = "DETAIL";

		return _viewType;
	}
	
	/**
	 * 生成内容
	 * @param viewType		查看类型，取值 LIST、DETAIL
	 * @param contentType	要生成的内容类型，取值：title, keywords, description
	 * @return
	 */
	private String makeContent(String viewType, String contentType){
		PageMetaBean meta = (PageMetaBean)getRequest().getAttribute(PageMetaBean.BEAN_NAME_MODULE);
		String _modelName = meta.getModuleName();
		
		String countryName = "";
		String cbdName = "";
		String communityName = "";
		String price = "";
		String area = "";
		String houseTitle = "";
		String newHouseTitle = "";
		String metaKeywords = "";
		String metaDescription = "";
		
		if(StringUtil.isNotEmpty(meta.getCountryName()))
			countryName = meta.getCountryName();

		if(StringUtil.isNotEmpty(meta.getCbdName()))
			cbdName = meta.getCbdName();
		
		if(StringUtil.isNotEmpty(meta.getCommunityName()))
			communityName = meta.getCommunityName();

		if(StringUtil.isNotEmpty(meta.getPrice()))
			price = meta.getPrice();

		if(StringUtil.isNotEmpty(meta.getArea()))
			area = meta.getArea();
		
		if(StringUtil.isNotEmpty(meta.getHouseTitle()))
			houseTitle = meta.getHouseTitle();
		
		if(StringUtil.isNotEmpty(meta.getNewHouseTitle()))
			newHouseTitle = meta.getNewHouseTitle();
		
		if(StringUtil.isNotEmpty(meta.getMetaKeywords()))
			metaKeywords = meta.getMetaKeywords();
		
		if(StringUtil.isNotEmpty(meta.getMetaDescription()))
			metaDescription = meta.getMetaDescription();
			
		String content = "";

		if(TEMPL.containsKey(_modelName)){
			Map<String, Map<String, String>> tempMap = TEMPL.get(_modelName);
			if(tempMap.containsKey(viewType)){
				Map<String, String> subTemp = tempMap.get(viewType);
				content = subTemp.get(contentType);
				content = content.replaceAll("\\$\\{countyName\\}", countryName);
				content = content.replaceAll("\\$\\{cbdName\\}", cbdName);
				content = content.replaceAll("\\$\\{communityName\\}", communityName);
				content = content.replaceAll("\\$\\{modelName\\}", _modelName);
				content = content.replaceAll("\\$\\{price\\}", price);
				content = content.replaceAll("\\$\\{area\\}", area);
				content = content.replaceAll("\\$\\{houseTitle\\}", houseTitle);
				if("title".equals(contentType) && StringUtil.isNotEmpty(newHouseTitle)){
					content = newHouseTitle;
				}else if("keywords".equals(contentType) && StringUtil.isNotEmpty(metaKeywords)){
					content = metaKeywords;
				}else if("description".equals(contentType) && StringUtil.isNotEmpty(metaDescription)){
					content = metaDescription;
				}
				
			}
		}

		return content;
	}
	
	/**
	 * 生成标题内容
	 * @param model
	 * @return
	 */
	private String getTitleContent(String viewType){
		String title = makeContent(viewType, "title");
		return title;
	}

	/**
	 * 生成关键词
	 * @param model
	 * @return
	 */
	private String getKeywordContent(String viewType){
		String keywords = makeContent(viewType, "keywords");
		return keywords;
	}
	
	/**
	 * 生成META描述
	 * @param viewType
	 * @return
	 */
	private String getDescriptionContent(String viewType){
		String description = makeContent(viewType, "description");
		return description;
	}
	
	/**
	 * 获取ApplicationContext对象
	 * @return
	 */
	private ApplicationContext getApplicationContext(){
		return WebApplicationContextUtils.getWebApplicationContext(this.getRequest().getServletContext());
	}
	
	/**
	 * 根据Bean的名称获取Bean实例
	 * @param serviceName
	 * @return
	 */
	private Object getServiceBeanByName(String serviceName){
		ApplicationContext ac = this.getApplicationContext();
		if(ac!=null){
			Object bean = ac.getBean(serviceName);
			return bean;
		}
		return null;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
