/**
 * 
 */
package cn.hshb.web.biz.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.huatek.hbwebsite.house.entity.Evaluation;

import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.SearchItem;
import cn.hshb.web.biz.service.CommonCountyTwoService;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author Administrator
 *
 */
public class HouseBaseController {
	private static final Log log = LogFactory.getLog(HouseBaseController.class);
	@Autowired
	SearchItemService searchItemService;
	
	@Autowired
	CommonCountyTwoService commonCountyTwoService;
	/**
	 * 解析价格条件
	 * @param pStr
	 * @return
	 */
	private String parsePrice(String pStr){
		String s = pStr.replaceAll("p", "");
		if(s.equals("0-50"))
			return "0_500000";
		else if(s.equals("50-80"))
			return "500000_800000";
		else if(s.equals("80-100"))
			return "800000_1000000";
		else if(s.equals("100-150"))
			return "1000000_1500000";
		else if(s.equals("150-200"))
			return "1500000_2000000";
		else if(s.equals("200-300"))
			return "2000000_3000000";
		else if(s.equals("300-500"))
			return "3000000_5000000";
		else if(s.equals("500-"))
			return "5000000_2147483647";
		else if(s.equals("500"))
			return "5000000_2147483647";
		else{
			//小区单位为万元
			String[] str = s.replaceAll("-", "_").split("_");
//				s = String.valueOf((Double.parseDouble(str[0]) * 10000 )) + "_";
//				s = s + String.valueOf((Double.parseDouble(str[1]) * 10000 ));
			if(str.length == 1)
				return str[0]+"0000_" + "2147483647"; 
			s = str[0] + "0000_" + str[1] + "0000"; 
		}
			return s;
	}
	
	/**
	 * 解析租金
	 * @param pStr
	 * @return
	 */
	private String parseRentPrice(String pStr){
		String s = pStr.replaceAll("zj", "");
		if(s.equals(""))
			return null;
		else if(s.equals("0-1500"))
			return "0_1500";
		else if(s.equals("1500-2500"))
			return "1500_2500";
		else if(s.equals("2500-3000"))
			return "2500_3000";
		else if(s.equals("3000-4000"))
			return "3000_4000";
		else if(s.equals("4000-5000"))
			return "4000_5000";
		else 
			s = s.replaceAll("-", "_");
		return s;
	}
	
	/**
	 * 解析小区均价
	 * @param pStr
	 * @return
	 */
	private String parseAveragePrice(String pStr){
		String s = pStr.replaceAll("jj", "");
		if("".equals(s))
			return "";
		else if("0-1".equals(s))
			return "0_10000";
		else if("1-1.5".equals(s))
			return "10000_15000";
		else if("1.5-2".equals(s))
			return "15000_20000";
		else if("2-3".equals(s))
			return "20000_30000";
		else if("3-4".equals(s))
			return "30000_40000";
		else if("4-5".equals(s))
			return "40000_50000";
		else{
			//小区单位为万元
			String[] str = s.replaceAll("-", "_").split("_");
				s = String.valueOf((Double.parseDouble(str[0]) * 10000 )) + "_";
				s = s + String.valueOf((Double.parseDouble(str[1]) * 10000 ));
		}
		return s ;
	}
	
	/**
	 * 解析面积查询条件
	 * @param pStr
	 * @return
	 */
	private String parseArea(String pStr){
		String s = pStr.replaceAll("a", "");
		if(s.equals("")){
			return "";
		}else if(s.equals("0-40"))
			return "0_40";
		else if(s.equals("40-50"))
			return "40_50";
		else if(s.equals("50-60"))
			return "50_60";
		else if(s.equals("60-70"))
			return "60_70";
		else if(s.equals("70-85"))
			return "70_85";
		else if(s.equals("85-95"))
			return "85_95";
		else if(s.equals("95-120"))
			return "95_120";
		else if(s.equals("120-150"))
			return "120_150";
		else if(s.equals("150-300"))
			return "150_300";
		else if(s.equals("300-"))
			return "300_2147483647";
		else if(s.equals("300"))
			return "300_2147483647";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析居室查询条件
	 * @param pStr
	 * @return
	 */
	private String parseRoom(String pStr){
		String s = pStr.replaceAll("r", "");
		if(s.equals(""))
			return "";
		else if(s.equals("1-1"))
			return "1";
		else if(s.equals("2-2"))
			return "2";
		else if(s.equals("3-3"))
			return "3";
		else if(s.equals("4-4"))
			return "4";
		else if(s.equals("5-5"))
			return "5";
		else if(s.equals("6-6"))
			return "6";
		else
			s = s.replace("-", "_");
			return s;
	}
	
	/**
	 * 解析房龄查询条件
	 * @param pStr
	 * @return
	 */
	private String parseHouseYears(String pStr){
		String s = pStr.replaceAll("y", "");
		
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
		if(s.equals(""))
			return "";
		else if(s.equals("1"))
			return "1000-01-01_1995-01-01";
		else if(s.equals("2"))
			return "1995-01-01_2000-01-01"; 
		else if(s.equals("3"))
			return "2000-01-01_2005-01-01"; 
		else if(s.equals("4"))
			return "2005-01-01_2010-01-01"; 
		else if(s.equals("5"))
			return "2010-01-01_3000-01-01"; 
			
		//s = s.replace("-", "_");
		return s;
	}
	
	/**
	 * 解析与地铁站点的距离
	 * @param pStr
	 * @return
	 */
	private String parseSubwayDistance(String pStr){
		String s = pStr.replaceAll("d", "");
		if(s.equals("")) 
			return "";
		else if(s.equals("1"))
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
	 * @param pStr
	 * @return
	 */
	private String parseFirstpay(String pStr){
		String s = pStr.replaceAll("fp", "");
		if(s.equals(""))
			return "";
		else if(s.equals("1"))
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
	 * @param pStr
	 * @return
	 */
	private String parseMonthpay(String pStr){
		String s = pStr.replaceAll("mp", "");
		if(s.equals(""))
			return "";
		else if(s.equals("1"))
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
	 * @param pStr
	 * @return
	 */
	private String parseLoanYears(String pStr){
		String s = pStr.replaceAll("yc", "");
		if(s.equals(""))
			return "";
		else if(s.equals("1"))
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
	 * 解析小区建造年代查询条件
	 * @param pStr
	 * @return
	 */
	private String parseBuildYear(String pStr){
		String s = pStr.replaceAll("nd", "");
		if("".equals(s))
			return "";
		else if("1".equals(s))
			return"1900-01-01_1995-01-01";
		else if("2".equals(s))
			return"1995-01-01_2000-01-01";
		else if("3".equals(s))
			return"2000-01-01_2005-01-01";
		else if("4".equals(s))
			return"2005-01-01_2010-01-01";
		else
			return s + "-01-01_3000-01-01";
	}
	
	/**
	 * 解析排序规则
	 * @param pStr
	 * @return
	 */
	private String parseSortRule(String pStr){
		String s = pStr.replaceAll("s", "");
		if(s.equals("1"))
			return "sortIndex";
		else if(s.equals("2"))
			return "area";
		else if(s.equals("3"))
			return "price";
		else if(s.equals("4"))
			return "unit_price";
		//新增租赁的排序条件:年份,租金
		else if(s.equals("5"))
			return "startSaleDate";
		else if(s.equals("6"))
			return "rent_price";
		//小区排序条件
		else if(s.equals("7"))
			return "community_area";
		else if(s.equals("8"))
			return "current_month_sh_avg_price";
		else if(s.equals("9"))
			return "build_year";
			return s;
	}
	
	/**
	 * 解析城区商圈条件
	 * @param pStr
	 * @param condition
	 */
	private void parseCountryAndCbd(String pStr, Map<String, String> condition){
		Pattern p = Pattern.compile("a_(\\d+)?_(\\d+)?");
		Matcher m = p.matcher(pStr);
		if(m.find()){
			if(StringUtil.isNotEmpty(m.group(1)))
				condition.put("countyId", m.group(1));
			if(StringUtil.isNotEmpty(m.group(2)))
				condition.put("cbdId", m.group(2));
		}
	}
	
	/**
	 * 解析区域商圈条件
	 */
	private void parseCountryTwoAndCbd(String pStr, Map<String, String>condition){
		Pattern p = Pattern.compile("f_(\\d+)?_(\\d+)?");
		Matcher m = p.matcher(pStr);
		if(m.find()){
			if(StringUtil.isNotEmpty(m.group(1)))
				condition.put("countryTwoId", m.group(1));
			if(StringUtil.isNotEmpty(m.group(2)))
				condition.put("hzzfCbdId", m.group(2));
		}
		
	}
	
	/**
	 * 解析字母商圈条件
	 * @param pStr
	 * @param condition
	 */
	private void parseInitalmeAndCbd(String pStr, Map<String, String> condition){
		Matcher m = Pattern.compile("q_([A-Z])?([\\d_]+)?").matcher(pStr);
		if(m.find()){
			if(StringUtil.isNotEmpty(m.group(1)))
				condition.put("inital", m.group(1));
			if(StringUtil.isNotEmpty(m.group(2))){
				if(!"".equals(m.group(2).replace("_", "")))
				condition.put("communityErpId", m.group(2).replace("_", ""));
			}
		}
	}
	
	/**
	 * 解析地铁及地铁站查询条件
	 * @param pStr
	 * @param condition
	 */
	private void parseSubwayAndStation(String pStr, Map<String, String> condition){
		Pattern p = Pattern.compile("t_(\\d+)?_(\\d+)?");
		Matcher m = p.matcher(pStr);
		if(m.find()){
			if(StringUtil.isNotEmpty(m.group(1)))
				condition.put("station_line_id", m.group(1));
			if(StringUtil.isNotEmpty(m.group(2)))
				condition.put("subway_station_id", m.group(2));
		}
	}
	/**
	 * 解析二手房卖点查询条件 
	 * @param pStr
	 * @return
	 */
	private String parseTags(String pStr){
		String s = pStr.replace("t", "");
		switch(s){
			case "1":
				//学区房
				return "%030d8373-c5df-4326-92c3-1e453c115e73%";
			case "2":
				//投资保值
				return "%29577b85-6d3e-43e5-a887-c8b3b0326953%";
			case "3":
				//刚需小户
				return "%33fd041d-c444-4f83-9b77-426aac50cb3b%";
			case "4":
				//超值性价
				return "%6d98f9e0-b3da-4296-9021-099c9925ee57%";
			case "5":
				//次新房
				return "%80f9e697-a857-423a-b8e0-9e115699268f%";
			case "6":
				//经济适用房
				return "%8155a1ca-559d-460f-b379-9bf56eeec6b7%";
			case "7":
				//婚房
				return "%8a0d896d-a6d5-409f-8e92-1f9cd6480896%";
			case "8":
				//免双税
				return "%b91edfdd-5d11-48c0-af89-211ec3ae53fc%";
			case "9":
				//经济用房
				return "%da8441f0-5238-42d2-96af-ccf28c67a376%";
			case "10":
				//免营业税
				return "%ebd3955b-7a26-49fe-8b8a-94bbb7d4f02c%";
			default:
				return "";
		}
	}
	
	/**
	 * 解析首页查询条件
	 * @param houseType 二手房:1, 租赁:2 , 小区: 3
	 * @param pathStr
	 * @return
	 */
//	protected Map<String, String> parseHomePageQueryStr(String pathStr){
//		Map<String, String> condition = new HashMap<String, String>();
//		String homePageBySearchField = "";
//		String regexp = "([\u4E00-\u9FA5]+)|([A-Z][A-Z]+\\d+)|(-\\d+)";
//		if(StringUtils.isNotEmpty(pathStr)){
//			Matcher f = Pattern.compile(regexp).matcher(pathStr);
//			while(f.find()){
//				if(StringUtils.isNotEmpty(f.group(0))){
//					condition.put("homePageBySearchField", f.group(0).replace("-", ""));
//				}
//			}
//		}
//		return condition;
//	}
	/**
	 * 解析查询条件
	 * @param pathStr
	 * @return
	 */
	protected Map<String, String> parseQueryStr(String pathStr){
		Map<String, String> condition = new HashMap<String, String>();
		
		//String[] regexps = {"((a|t)[\\d_]+)","((a|r|y|f|z|t|s|d|p|n)[^aryfztsdpn]+)"}; ((-[\u4E00-\u9FA5]+)|(-[A-Z][A-Z]+)?(-\\d+))
		//((a_|t_|q_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q)(([a-z]([\\d_]+)?)|([\\d_]+)))
//		String regexp = "((a_|t_|q_|f_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|q|-)(([\u4E00-\u9FA5]+)|(([a-z]+)?([\\d_]+)?)))";
//		String regexp = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|-)(([\u4E00-\u9FA5]+)|(([a-z]+)?([\\d-]+)?)))";
//		String regexp = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|cx)(([\u4E00-\u9FA5]+)|(([a-z]+([\\d]+)?)?([_\\d]+([-\\d.]+)?)?)))";
		String regexp = "((a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|cx)((([\u4E00-\u9FA5]+)|([a-zA-Z]+([\\d]+)?))|(([A-Z])?([-\\d._]+))))";
		/*
		 * TODO 详解：
		 * (a_|t_|q_|f_|v_|nd|jj|zj|pz|xs|a|r|y|f|z|t|s|d|p|n|cx) 前半部分中:
		 * cx 表示的是首页手动输入查询条件的前缀,查询首字母拼音
		 * zj：在租赁列表中,因为p价格的范围不一致,所在租赁代码中,把p字母替换成zj,用以区分,代码见handleCondition()方法
		 * 其他的条件都可以在文档中查看
		 * ((([\u4E00-\u9FA5]+)|([a-zA-Z]+([\\d]+)?))|(([_-\\d.]+))))
		 * (([\u4E00-\u9FA5]+)|([a-zA-Z]+([\\d]+)?)):用户输入查询条件,用于解析中文以及其他字符串正则 如 ： cx米市巷 cxHS12456
		 * ([_-\\d.]+):价格区间查询 以及其他普通查询 如: p123-312  a_1_2 p12.2-12.5 
		 * */
		
		if(StringUtil.isNotEmpty(pathStr)){
			Pattern p = Pattern.compile(regexp);  //查询条件解析
			
			//对于第二级查询条件: a_<城区ID>_<商圈ID> 或  t_<地铁线路ID>_<地铁站点ID>
			//对于第三级查询条件：a: 面积, r:居室, y为房龄, f为朝向, z为装修, t为买点, s为排序, d为离地铁站距离, p为价格, n为页码
			//新增  zj:租金 ,q: 小区,pz:屋内配置,xs:租赁形式
			//新增 jj:小区均价, nd:小区年代 , q_:小区字母查询：格式为q_<字母>_<小区代码>
			//\u4E00-\u9FA5 输入框输入  ,cx 输入框搜索,f_ <区域>_<商圈Id> 区域指：城东租房等
			//v_把q小区替换成v_ 与a_等为同一级
			String value = null;
//			pathStr = pathStr.toLowerCase();
			Matcher m = p.matcher(pathStr);
			while(m.find()){
				String s = m.group(0);
				if(s.startsWith("a_")){
					//城区和商圈
					parseCountryAndCbd(s, condition);
				}else if(s.startsWith("t_")){
					//地铁线路
					parseSubwayAndStation(s, condition);
				}else if(s.startsWith("q_")){
					//小区字母查询
					parseInitalmeAndCbd(s, condition);
				}else if(s.startsWith("f_")){
					//租赁区域搜索指的是:城东租房,城西租房
					parseCountryTwoAndCbd(s, condition);
				}else if(s.startsWith("nd")){
					//小区年代
					value = parseBuildYear(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("buildYear", value);
				}else if(s.startsWith("jj")){
					//TODO 小区均价
					condition.put("avgPrice", parseAveragePrice(s));
				}else if(s.startsWith("a")){
					//面积
					 value = parseArea(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("area", value);
				}else if(s.startsWith("r")){
					//居室
					value = parseRoom(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("shi", value);
				}else if(s.startsWith("y")){
					//房龄
					value = parseHouseYears(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("complate_year", value);
				}else if(s.startsWith("f")){
					//朝向
					value = s.replaceAll("f", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("orientation_no", value );
				}else if(s.startsWith("zj")){
					//租金
					value = parseRentPrice(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("rent_price", value);
				}else if(s.startsWith("z")){
					//装修
					value = s.replaceAll("z", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("decoration_id", value);
				}else if(s.startsWith("t")){
					//房源标签（卖点、特色）
//					condition.put("tags", parseTags(s));
					value = s.replace("t", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("tags", value);
				}else if(s.startsWith("xs")){
					//租赁形式
					value = s.replaceAll("xs", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("rentType", value);
				}else if(s.startsWith("s")){
					//排序条件(sort)
					value = parseSortRule(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("orderby", value);
				}else if(s.startsWith("pz")){
					//租赁配置
					value = s.replaceAll("pz", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("furniture", value);
				}else if(s.startsWith("p")){
					//价格
					value = parsePrice(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("price", value);
				}else if(s.startsWith("n")){
					//页码(page)
					value = s.replaceAll("n", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("pageNum", value);
				}else if(s.startsWith("d")){
					//距离地铁站距离
					value = parseSubwayDistance(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("subwayDistance", value);
				}else if(s.startsWith("fp")){
					//首付
					value = parseFirstpay(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("firstpay", value);
				}else if(s.startsWith("mp")){
					//月供
					value = parseMonthpay(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("monthpay", value);
				}else if(s.startsWith("yc")){
					//贷款年限
					value = parseLoanYears(s);
					if(StringUtil.isNotEmpty(value))
						condition.put("loanyears", value);
				}else if(s.startsWith("v_")){
					//小区
					value = s.replaceAll("v_", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("community", value);
				}else if(s.startsWith("cx")){
//					value = s.replace("cx", "").toUpperCase();
					value = s.replace("cx", "");
					if(StringUtil.isNotEmpty(value))
						condition.put("homePageBySearchField", value);
				}
			}
		}
		return condition;
	}

	protected Map<String, String> parseQueryCondition(String pathStr1, String pathStr2){
		Map<String, String> condition = new HashMap<String, String>();
		String pStr = "";
		pStr += pathStr1 == null? "": pathStr1;
		pStr += pathStr2 == null? "": pathStr2;
		//首页附带中文和房源Id的条件的查询
//		Map<String, String> c = parseHomePageQueryStr(pStr); 
		//正常的搜索条件
//		if(c.size()==0)
		Map<String, String> c = parseQueryStr(pStr);
		condition.putAll(c);

		return condition;
	}
	/**
	 * 字符编码转换
	 * @param str
	 * @return
	 */
	protected String getConversionEncoding(String str) {      
	       String  encode = "ISO-8859-1";
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
//	               String s = encode;      
	        	  System.out.println(encode);
	        	  return new String(str.getBytes(encode),"UTF-8");      
	           }    
	          encode = "UTF-8";      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
//	               String s2 = encode;     
	        	  System.out.println(encode);
	              return str;      
	           }  
	          encode = "GB2312"; 
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
//	               String s1 = encode;      
	        	  System.out.println(encode);
	               return new String(str.getBytes(encode),"UTF-8");       
	           }      
	          encode = "GBK";      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
//	               String s3 = encode;
	        	  System.out.println(encode);
	               return new String(str.getBytes(encode),"UTF-8");       
	           }      
	      } catch (Exception exception) {      
	    	   exception.printStackTrace();
	    	   log.error("字符转换失败");
	   }  
	      return "未知";      
	   }   
	/**
	 * 从数据库查询查询条件定义数据 
	 * @param model
	 * @param modelId	模块ID，“1”： 二手房， “2”：出租，"3": 小区
	 */
	protected void getSearchItem(Model model, String modelId){
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId(modelId);
		model.addAttribute("searchItems", list);
	}
	
	/**
	 * 创建查询房源请求XML
	 * @param dataType
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	protected String createQueryHouseRequestXML(String dataType,String houseId, String agentId){
		String strXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		strXML = strXML + "<BasicData>";
		strXML = strXML + "<DataHeader>";
		strXML = strXML + "<DataSetID>" + UUID.randomUUID() + "</DataSetID>";
		strXML = strXML + "<DataType>"+dataType+"</DataType>";
		strXML = strXML + "</DataHeader>";
		strXML = strXML + "<DataBody>";
		strXML = strXML + "<Item>";
		strXML = strXML + "<ItemID>" + UUID.randomUUID() + "</ItemID>";
		strXML = strXML + "<HouseID>" + houseId + "</HouseID>";
		strXML = strXML + "<AgentID>" + agentId + "</AgentID>";
		strXML = strXML + "</Item>";
		strXML = strXML + "</DataBody>";
		strXML = strXML + "</BasicData>";
		return strXML;
	}
	/**
	 * 把ERP传过来的房源评价进行添加处理
	 * @param appraises   房源评价
	 * @param evaluations ERP传过来的房源评价
	 */
	protected void addToHouseAppraiseList(List<HouseAppraise> appraises, List<Evaluation> evaluations){
		if(appraises == null)
			appraises = new ArrayList<HouseAppraise>();
		for(Evaluation ev : evaluations){
			HouseAppraise apprsise = new HouseAppraise();
			if(ev.getbBroker() == null)continue;
			apprsise.setPublisher(ev.getbBroker());
			apprsise.setTitle(ev.getTitle());
			apprsise.setContent(ev.getContent());
			appraises.add(apprsise);
		}
	}
	/**
	 * 租赁价格,小区,与二手房的价格分开计算
	 * @param pathStr
	 * @return
	 */
	protected String handleCondition(String pathStr1, String pathStr2){
		return handleCondition(pathStr1, pathStr2, null);
	}
	protected String handleCondition(String pathStr1, String pathStr2, String replaceChar){
		String pStr = "";
		//默认租金
		if(StringUtil.isEmpty(replaceChar))
			replaceChar = "zj";
		pStr += pathStr1 == null? "": pathStr1;
		pStr += pathStr2 == null? "": pathStr2;
		if(StringUtil.isEmpty(pStr)) return pStr;
		String regexp = "p[\\d-.]+"; 
		Matcher m = Pattern.compile(regexp).matcher(pStr);
		while(m.find()){
			String s = m.group();
			if(s.startsWith("p")){
				pStr = pStr.replaceAll("p", replaceChar);
			}
		}
		return pStr;
	}
}
