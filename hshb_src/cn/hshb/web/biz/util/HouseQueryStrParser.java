/**
 * 
 */
package cn.hshb.web.biz.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.huatek.framework.util.SpringContext;

import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.service.CommonCbdService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author Administrator
 *
 */
public class HouseQueryStrParser {
	private static Log log = LogFactory.getLog(HouseQueryStrParser.class);
	
	//String[] regexps = {"((a|t)[\\d_]+)","((a|r|y|f|z|t|s|d|p|n)[^aryfztsdpn]+)"};
//	String regexp = "((a_|t_|a|r|y|f|z|t|s|d|p|n)([^aryfztsdpn]+))";
//	private static final String CONDITION_REGEXP = "((a_|t_|q_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q)(([a-z]([\\d_]+)?)|([\\d_]+)))";
//	private static final String CONDITION_REGEXP = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|-)(([\u4E00-\u9FA5]+)|(([a-z]+)?([\\d-]+)?)))";
//	private static final String CONDITION_REGEXP = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|-)(([\u4E00-\u9FA5]+)|(([a-zA-Z]+([\\d]+)?)?([_\\d]+(-[\\d.]+)?)?)))";
	private static final String CONDITION_REGEXP = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|cx)((([\u4E00-\u9FA5]+)|([a-zA-Z]+([\\d]+)?))|(([A-Z])?([-\\d._]+))))";
	/**
	 * 解析查询条件
	 * @param pathStr
	 * @return
	 */
	protected static Map<String, String> parseQueryStr(String pathStr){
		Map<String, String> condition = new HashMap<String, String>();

		//String[] regexps = {"((a|t)[\\d_]+)","((a|r|y|f|z|t|s|d|p|n)[^aryfztsdpn]+)"};
//		String regexp = "((a_|t_|a|r|y|f|z|t|s|d|p|n)([^aryfztsdpn]+))";
//		String regexp = "((a_|t_|q_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q)(([a-z]([\\d_]+)?)|([\\d_]+)))";
//		String regexp = "((a_|t_|q_|f_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q|-)(([\u4E00-\u9FA5]+)|(([a-z]+)?([\\d_]+)?)))";
//		String regexp = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|-)(([\u4E00-\u9FA5]+)|(([a-z]+)?([\\d-]+)?)))";
		//该正则 详解看HouseBaseCotroller.java类
		//String regexp = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|-)(([\u4E00-\u9FA5]+)|((([a-zA-Z]+)([\\d]+)?)?([_\\d]+([-\\d.]+)?)?)))";
		
		if(StringUtil.isNotEmpty(pathStr)){
			Pattern p = Pattern.compile(CONDITION_REGEXP);  //查询条件解析
			//对于第二级查询条件: a_<城区ID>_<商圈ID> 或  t_<地铁线路ID>_<地铁站点ID>
			//对于第三级查询条件：a: 面积, r:居室, y为房龄, f为朝向, z为装修, t为买点, s为排序, d为离地铁站距离, p为价格, n为页码

//			pathStr = pathStr.toLowerCase();
			Matcher m = p.matcher(pathStr);
			while(m.find()){
				//String s = m.group(0);
				String key = m.group(2);
				String value = m.group(3);
				condition.put(key, value);
			}
		}
		return condition;
	}

	/**
	 * 解析URL中的查询参数
	 * @param pathStr1 	第一级参数
	 * @param pathStr2	第二级参数
	 * @return
	 */
	public static Map<String, String> parseQueryCondition(String pathStr1, String pathStr2){
		String pStr = "";
		pStr += pathStr1 == null? "": pathStr1;
		pStr += pathStr2 == null? "": pathStr2;
		
		Map<String, String> c = parseQueryStr(pStr);
		
		return c;
	}
	
	
	
	/**
	 * 解析URL中的查询参数
	 * @param map 传入的map
	 * @return
	 */
	public static Map<String, String> parseQueryCondition(Map<String,String> map){
		
		String condition = "";
		if(map != null){
			for(String key : map.keySet()){
				condition += key + map.get(key);
			}
		}
		
		return parseQueryStr(condition);
	}
	/**
	 * 把查询条件转换成实际的查询条件
	 * @param condition
	 * @return
	 */
	public static Map<String, String> convertQueryCondition(Map<String, String> condition){
		return convertQueryStr(condition);
	}	
	
	/**
	 * 各个查询条件转换成实际查询条件
	 * @param params
	 * @return
	 */
	protected static Map<String, String> convertQueryStr(Map<String, String> params){
		Map<String, String> condition = new HashMap<String, String>();
		for(Map.Entry<String, String> e: params.entrySet()){
			String key = e.getKey();
			String value = e.getValue();
			if(key.startsWith("a_")){
				//城区和商圈
				convertCountryAndCbd(value, condition);
			}else if(key.startsWith("t_")){
				//地铁线路
				ConvertSubwayAndStation(value, condition);
			}else if(key.startsWith("a")){
				//面积
				condition.put("area", convertArea(value));
			}else if(key.startsWith("r")){
				//居室
				condition.put("shi", convertRoom(value));
			}else if(key.startsWith("y")){
				//房龄
				condition.put("complate_year", convertHouseYears(value));
			}else if(key.startsWith("f")){
				//朝向
				condition.put("orientation_no", value.replaceAll("f", ""));
			}else if(key.startsWith("z")){
				//装修
				condition.put("decoration_id", value.replaceAll("z", ""));
			}else if(key.startsWith("t")){
				//房源标签（卖点、特色）
				condition.put("tags", value.replaceAll("t", ""));
			}else if(key.startsWith("s")){
				//排序条件(sort)
				condition.put("orderby", convertSortRule(value));
			}else if(key.startsWith("p")){
				//价格
				condition.put("price", convertPrice(value));
			}else if(key.startsWith("n")){
				//页码(page)
				condition.put("pageNum", value.replaceAll("n", ""));
			}else if(key.startsWith("d")){
				//距离地铁站距离
				condition.put("subwayDistance", convertSubwayDistance(value));
			}else if(key.startsWith("fp")){
				//首付
				condition.put("firstpay", convertFirstpay(value));
			}else if(key.startsWith("mp")){
				//月供
				condition.put("monthpay", convertMonthpay(value));
			}else if(key.startsWith("yc")){
				//贷款年限
				condition.put("loanyears", convertLoanYears(value));
			}			
		}
		return condition;
	}
	
	/**
	 * 解析价格条件
	 * @param pStr
	 * @return
	 */
	private static String convertPrice(String s){
		if(s.equals("1"))
			return "0_500000";
		else if(s.equals("2"))
			return "500000_800000";
		else if(s.equals("3"))
			return "800000_1000000";
		else if(s.equals("4"))
			return "1000000_1500000";
		else if(s.equals("5"))
			return "1500000_2000000";
		else if(s.equals("6"))
			return "2000000_3000000";
		else if(s.equals("7"))
			return "3000000_5000000";
		else if(s.equals("8"))
			return "5000000_2147483647";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析面积查询条件
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertArea(String s){
		if(s.equals("1"))
			return "0_40";
		else if(s.equals("2"))
			return "40_50";
		else if(s.equals("3"))
			return "50_60";
		else if(s.equals("4"))
			return "60_70";
		else if(s.equals("5"))
			return "70_85";
		else if(s.equals("6"))
			return "85_95";
		else if(s.equals("7"))
			return "95_120";
		else if(s.equals("8"))
			return "120_150";
		else if(s.equals("9"))
			return "150_300";
		else if(s.equals("10"))
			return "300_2147483647";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析居室查询条件
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertRoom(String s){
		if(s.equals("1"))
			return "1";
		else if(s.equals("2"))
			return "2";
		else if(s.equals("3"))
			return "3";
		else if(s.equals("4"))
			return "4";
		else if(s.equals("5"))
			return "5";
		else if(s.equals("6"))
			return "6";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析房龄查询条件
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertHouseYears(String s){
		
		//FIXME: 房龄条件要再根据查询条件定义表中的值进行转换
		String moduleId = "1";
//		List<SearchField> houseYearList = new ArrayList<SearchField>();
//		if(this instanceof Exchange){
//			moduleId = "1";
//			List<SearchItem> list = searchItemService.loadSearchItemByModuleId(moduleId);
//			
//			for(SearchItem item: list){
//				if(item.getId()==12){	//二手房源的区域
//					List<SearchItem> subList = item.getSubItems();
//					for(SearchItem item1: subList){
//						if(item1.getId()==20){	//二手房源的房龄
//							houseYearList = item1.getFields();
//							break;
//						}
//					}
//				}
//			}
//		}
//		if(StringUtil.isNumericSpace(s)){
//			Integer s1 = Integer.parseInt(s);
//			for(SearchField field: houseYearList){
//				if(field.getId()==s1){
//					return field.getMinfieldvalue() + "_" + field.getMaxfieldvalue();
//				}
//			}
//		}
		s = s.replace("-", "_");
		return s;
	}
	
	/**
	 * 解析与地铁站点的距离
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertSubwayDistance(String s){
		if(s.equals("1"))
			return "0_500";
		else if(s.equals("2"))
			return "500_1000";
		else if(s.equals("3"))
			return "1000_1500";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析首付查询条件
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertFirstpay(String s){
		if(s.equals("1"))
			return "200000_300000";
		else if(s.equals("2"))
			return "300000_400000";
		else if(s.equals("3"))
			return "400000_500000";
		else if(s.equals("4"))
			return "500000_800000";
		else if(s.equals("5"))
			return "800000_1100000";
		else if(s.equals("6"))
			return "1100000_1600000";
		else if(s.equals("7"))
			return "1600000_2147483647";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析月供查询条件
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertMonthpay(String s){
		if(s.equals("1"))
			return "0_2000";
		else if(s.equals("2"))
			return "2000_3000";
		else if(s.equals("3"))
			return "3000_4000";
		else if(s.equals("4"))
			return "4000_5000";
		else if(s.equals("5"))
			return "5000_6000";
		else if(s.equals("6"))
			return "6000_7000";
		else if(s.equals("7"))
			return "7000_2147483647";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析贷款年限查询条件
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertLoanYears(String s){
		if(s.equals("1"))
			return "0_5";
		else if(s.equals("2"))
			return "5_10";
		else if(s.equals("3"))
			return "10_20";
		else if(s.equals("4"))
			return "20_30";
		else if(s.equals("5"))
			return "30_40";
		else if(s.equals("6"))
			return "40_60";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析排序规则
	 * @param s			查询条件原始值
	 * @return
	 */
	private static String convertSortRule(String s){
		if(s.equals("1"))
			return "sortIndex";
		else if(s.equals("2"))
			return "area";
		else if(s.equals("3"))
			return "price";
		else if(s.equals("4"))
			return "unitPrice";
		else
			return s;
	}
	
	/**
	 * 解析城区商圈条件
	 * @param s			查询条件原始值
	 * @param condition
	 */
	private static void convertCountryAndCbd(String S, Map<String, String> condition){
		Pattern p = Pattern.compile("(a_)?(\\d+)?_(\\d+)?");
		Matcher m = p.matcher(S);
		if(m.find()){
			if(StringUtil.isNotEmpty(m.group(1)))
				condition.put("countyId", m.group(1));
			if(StringUtil.isNotEmpty(m.group(2)))
				condition.put("cbdId", m.group(2));
		}
	}
	
	/**
	 * 解析地铁及地铁站查询条件
	 * @param s			查询条件原始值
	 * @param condition
	 */
	private static void ConvertSubwayAndStation(String S, Map<String, String> condition){
		Pattern p = Pattern.compile("(t_?(\\d+)?_(\\d+)?");
		Matcher m = p.matcher(S);
		if(m.find()){
			if(StringUtil.isNotEmpty(m.group(1)))
				condition.put("station_line_id", m.group(1));
			if(StringUtil.isNotEmpty(m.group(2)))
				condition.put("subway_station_id", m.group(2));
		}
	}

	/**
	 * 把查询条件转换成描述语言，用以在页面META中显示
	 * @return
	 */
	public static Map<String, String> convertConditionToDescription(Map<String, String> condition){
		Map<String, String> descMap = new HashMap<String, String>();
		for(Map.Entry<String, String> e: condition.entrySet()){
			String key = e.getKey();
			String val = e.getValue();
			if("countyId".equals(key)){
				descMap.put("countyName", val);
			}
			if("cbdId".equals(key)){
				descMap.put("cbdName", val);
			}
			if("communityId".equals(key)){
				descMap.put("communityName", val);
			}

			if("area".equals(key)){
				String[] ps = val.split("_");
				if(ps[0]=="0")
					descMap.put("area", ps[1]+"平以下");
				else if(StringUtil.parseInt(ps[1], 0)>=10000)
					descMap.put("area", ps[0]+"平以上");
				else
					descMap.put("area", ps[0]+"到"+ps[1]+"平");
			}
			if("price".equals(key)){
				String[] ps = val.split("_");
				Integer[] p = new Integer[2];
				p[0] = StringUtil.parseInt(ps[0], 0) / 10000;
				p[1] = StringUtil.parseInt(ps[1], 0) / 10000;
				if(p[0] <= 0)
					descMap.put("price", p[1]+"万以下");
				else if(p[1]>=10000)
					descMap.put("price", p[0]+"万以上");
				else
					descMap.put("price", p[0]+"到"+p[1]+"万");
			}
		}
		
		return descMap;
	}

	/*******************以下为页面META区所需要用到的条件 *****************/
	/**
	 * 根据查询条件得到房源面积描述（如果存在房源面积条件的话）
	 * @param params	查询条件Map
	 * @return
	 */
	public String getAreaDescriptionByCondition(Map<String, String> params){
		String area = "";
		if(params.containsKey("area")){
			String val = params.get("area");
			String[] ps = val.split("_");
			if(ps[0]=="0")
				area = ps[1]+"平以下";
			else if(StringUtil.parseInt(ps[1], 0)>=10000)
				area = ps[0]+"平以上";
			else
				area = ps[0]+"到"+ps[1]+"平";
		}
		return area;
	}

	/**
	 * 根据查询条件得到房源价格描述（如果存在房源价格条件的话）
	 * @param params	查询条件Map
	 * @return
	 */
	public String getPriceDescriptionByCondition(Map<String, String> params){
		String price = "";
		if(params.containsKey("price")){
			String val = params.get("price");
			String[] ps = val.split("_");
			Integer[] p = new Integer[2];
			p[0] = StringUtil.parseInt(ps[0], 0) / 10000;
			p[1] = StringUtil.parseInt(ps[1], 0) / 10000;
			if(p[0] <= 0)
				price = p[1]+"万以下";
			else if(p[1]>=10000)
				price = p[0]+"万以上";
			else
				price = p[0]+"到"+p[1]+"万";
		}
		if(params.containsKey("rent_price")){
			String val = params.get("rent_price");
			String[] ps = val.split("_");
			Integer[] p = new Integer[2];
			p[0] = StringUtil.parseInt(ps[0], 0);
			p[1] = StringUtil.parseInt(ps[1], 0);
			if(p[0] <= 0)
				price = p[1]+"元以下";
			else if(p[1]>=10000)
				price = p[0]+"元以上";
			else
				price = p[0]+"到"+p[1]+"元";
		}		
		return price;
	}


	/**
	 * 根据查询条件得到房源均价描述
	 * @param params	查询条件Map
	 * @return
	 */
	public String getAvgPriceDescriptionByCondition(Map<String, String> params){
		String price = "";
		if(params.containsKey("avgPrice")){
			String val = params.get("avgPrice");
			String[] ps = val.split("_");
			Integer[] p = new Integer[2];
			p[0] = StringUtil.parseInt(ps[0], 0) / 10000;
			p[1] = StringUtil.parseInt(ps[1], 0) / 10000;
			if(p[0] <= 0)
				price = p[1]+"万以下";
			else if(p[1]>=10000)
				price = p[0]+"万以上";
			else
				price = p[0]+"到"+p[1]+"万";
		}
		return price;
	}

	
	/**
	 * 根据查询条件得到城区名称（如果查询条件中包含城区的话）
	 * @param params 查询条件Map
	 * @return
	 */
	public String getCountryNameByCondition(Map<String, String> params){
		String countryName = "";

		if(params.containsKey("countyId")){
			Object bean = this.getServiceBeanByName("commonCountyServiceImpl");
			if(bean!=null){
				CommonCountyService svc = (CommonCountyService)bean;
				String countyId = params.get("countyId");
				CommonCounty country = svc.getCounty(countyId);
				if(country!=null)
					countryName = country.getCountyName();
			}
		}

		return countryName;
	}

	/**
	 * 根据查询条件得到商圈名称（如果查询条件中包含商圈的话）
	 * @param params 查询条件Map
	 * @return
	 */
	public String getCbdNameByCondition(Map<String, String> params){
		String cbdName = "";
		if(params.containsKey("cbdId")){
			Object bean = this.getServiceBeanByName("commonCbdServiceImpl");
			if(bean!=null){
				CommonCbdService svc = (CommonCbdService)bean;
				String cbdId = params.get("cbdId");
				if(StringUtil.isNumeric(cbdId)){
					CommonCbdWebsite cbdw = svc.getCommonCbdWebsiteById(StringUtil.parseInt(cbdId, -1));
					if(cbdw!=null)
						cbdName = cbdw.getName();
				}
			}
		}

		return cbdName;
	}

	/**
	 * 根据查询条件得到小区名称（如果查询条件中包含小区的话）
	 * @param params 查询条件Map
	 * @param params
	 * @return
	 */
	public String getCommunityNameByCondition(Map<String, String> params){
		String communityName = "";
		if(params.containsKey("community")){
			Object bean = this.getServiceBeanByName("houseCommunityServiceImpl");
			if(bean!=null){
				HouseCommunityService svc = (HouseCommunityService)bean;
				String communityId = params.get("community");
				if(StringUtil.isNumeric(communityId)){
					HouseCommunity community = svc.findCommunityByCommunityId(communityId);
					if(community!=null)
						communityName = community.getCommunityName();
				}
			}
		}

		return communityName;
	}
	

	/**
	 * 获取ApplicationContext对象
	 * @return
	 */
	private ApplicationContext getApplicationContext(){
		return SpringContext.getApplicationContext();
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
	
	/**
	 * 根据类名获取Bean实例
	 * @param serviceClassName
	 * @return
	 */
	private Object getServiceBeanByClassName(String serviceClassName){
		ApplicationContext ac = this.getApplicationContext();
		if(ac!=null){
			try{
				Class clz = Class.forName(serviceClassName);
				if(clz!=null){
					Object bean = ac.getBean(clz);
					return bean;
				}
			}catch(Exception ex){
				log.error(ex);
			}
		}
		return null;
	}	

	/*******************************************/
	
	
	
	/**
	 * @deprecated 本方法已废除，用parseQueryCondition代替
	 * @param query
	 * @return
	 * @throws JspException
	 */
	private static Map<String, String> parseQueryStr(Object query) throws JspException{
		Map<String, String> params = new HashMap<String, String>();
		if(query instanceof String){
			//把字符串方式保存的搜索参数转成List

			//下面的正则表达式所表示的url参数规则是人为约定的：
			//形如：a_123_234 或 t_1_234 的表示是城区商圈或地铁线路和站点参数，位于URL前一级
			//形如: a1p102-130f1 之类的表示是其他参数,位于URL的后一级
//			Pattern pattern = Pattern.compile("((a|t)_(\\d+)?_(\\d+)?)|((a|r|y|f|z|t|s|d|p)[-\\d]+)");
			Pattern pattern = Pattern.compile("((a_|t_|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q)[\\d_]+)");
			Matcher macher = pattern.matcher((String)query);
			while(macher.find()){
				String p = macher.group(0);
				parseSubQueryStr(p, params);
			}
		}else if(query instanceof List){
			List<String> lst = (List<String>)query;
			for(String p: lst){
				parseSubQueryStr(p, params);
			}
		}else if(query instanceof String[]){
			//把字符串数据方式保存的搜索参数转成List
			for(String p: (String[])query){
				parseSubQueryStr(p, params);
			}
		}else if(query instanceof Map){
			return (Map<String, String>)query;
		}else{
			throw new JspException("原有查询参数格式错误，必须是List、Map<String,Stirng>、String[]或字符串格式！");
		}
		return params;
	}

	/**
	 * @deprecated 本方法已废除
	 * @param str
	 * @param params
	 */
	private static void parseSubQueryStr(String str, Map<String, String> params){
		if(str.startsWith("a_")){
			params.put("a_", str.replace("a_", ""));
		}else if(str.startsWith("t_")){
			params.put("t_", str.replace("a_", ""));
		}else{
//			Pattern pattern = Pattern.compile("((a|r|y|f|z|t|s|d|p)([-\\d]+))");xs|pz|zj|q
			Pattern pattern = Pattern.compile("((a_|t_|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q)([\\d_]+))");
			Matcher macher = pattern.matcher(str);
			if(macher.find()){
				params.put(macher.group(2), macher.group(3));
			}
		}
	}

}
