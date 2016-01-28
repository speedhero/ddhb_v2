package cn.hshb.web.biz.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hshb.web.biz.mybatis.dao.SearchItemMapper;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonCountyTwo;
import cn.hshb.web.biz.mybatis.model.CommonSubway;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.mybatis.model.SearchItem;
import cn.hshb.web.biz.service.CommonCountyTwoService;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.common.util.StringUtil;

/**
 * 解析查询条件,用于列表页显示用户查询已查询的条件
 * @author hejianbo
 *	2015年9月14日
 */
public class AnalyticalQueryFieldImpl {
	private static final Log log = LogFactory.getLog(AnalyticalQueryFieldImpl.class);
	//范围 最大值上限
	private static final String MAXIMUM_UPPER_BOUND = "2147483647";
	private static final String[] SHI = {"未知","一","二","三","四","五","六","七","八","九"};
	
	@Autowired
	SearchItemService searchItemService;
	@Autowired
	private CommonCountyTwoService commonCountyTwoService;
	@Autowired
	private SearchItemMapper searchItemMapper;
	
	/**
	 * 把搜索条件进行分析
	 * @param key		需要分析的字段
	 * @param value		key对应的值
	 * @param houseType	查询类别: 二手房为2, 租赁为3, 小区为4
	 * @param field		未解析的查询条件  如:a_12_54/a1b1c1
	 * @return	new String[]{搜索显示的名称, 点击删除按钮的连接, 单位1, 单位2}
	 */
	protected String[] searchFieldByClassify(String key, String value, String houseType, String field){
		if(StringUtil.isNotEmpty(key)){
			if("countyId".equals(key)){
				//城区
				return new String[]{getCountyNameOrWebsiteName(value, houseType, true), getDelSearchFieldUri(field, "a_[\\d_]+",0), "", ""};
			}else if("cbdId".equals(key)){
				//商圈
				return new String[]{getCountyNameOrWebsiteName(value, houseType, false), 
						existContent(field,"a_[\\d]+")?getDelSearchFieldUri(field, "(a_[\\d]+_)([\\d]+)",1):getDelSearchFieldUri(field, "a__[\\d]+",0),"", ""};
			}else if("station_line_id".equals(key)){
				//地铁
				return new String[]{getSubwatStationName(value, houseType, true), getDelSearchFieldUri(field, "t_[\\d_]+", 0), "", ""};
			}else if("subway_station_id".equals(key)){
				//地铁线路
				return new String[]{getSubwatStationName(value, houseType, false), getDelSearchFieldUri(field, "(t_[\\d]+_)([\\d]+)", 1), "", ""};
			}else if("countryTwoId".equals(key)){
				//杭州租房
				return new String[]{getHzzfCbdName(value, true), getDelSearchFieldUri(field, "f_[\\d_]+", 0), "", ""};
			}else if("hzzfCbdId".equals(key)){
				//杭州租房 网站商圈
				return new String[]{getHzzfCbdName(value, false), getDelSearchFieldUri(field, "(f_[\\d]+_)([\\d]+)", 1), "", ""};
			}else if("area".equals(key)){
				//面积
				int number = 36;
				if("2".equals(houseType))number = 18;
				return new String[]{getRangeName(value, houseType, number), getDelSearchFieldUri(field, "a[\\d-]+", 0), "平", ""};
			}else if("shi".equals(key)){
				//室
				return new String[]{getShiName(value), getDelSearchFieldUri(field, "r[\\d-]", 0), "居", ""};
			}else if("complate_year".equals(key)){
				//房龄
				int number = 38;
				if("2".equals(houseType)) number = 20;
				return new String[]{getRangeName(value, houseType, number), getDelSearchFieldUri(field, "y[\\d]+", 0), "", ""};
			}else if("orientation_no".equals(key)){
				//朝向
				int number = 39;
				if("2".equals(houseType)) number = 21;
				return new String[]{getOrdinaryName(value, houseType, number), getDelSearchFieldUri(field, "f[\\d]+", 0), "", ""};
			}else if("decoration_id".equals(key)){
				//装修
				int number = 40;
				if("2".equals(houseType)) number = 22;
				return new String[]{getOrdinaryName(value, houseType, number), getDelSearchFieldUri(field, "z[\\d]+", 0), "", ""};
			}else if("tags".equals(key)){
				//房源标签
				return new String[]{getOrdinaryName(value, houseType, 23), getDelSearchFieldUri(field, "t[\\d]+", 0), "", ""};
			}else if("rentType".equals(key)){
				//租赁形式
				return new String[]{getOrdinaryName(value, houseType, 80), getDelSearchFieldUri(field, "xs[\\d]+", 0), "", ""};
			}else if("orderby".equals(key)){
				//排序条件
			}else if("furniture".equals(key)){
				//租赁配置
				return new String[]{getOrdinaryName(value, houseType, 81), getDelSearchFieldUri(field, "pz[\\d]+", 0), "", ""};
			}else if("price".equals(key)){
				//价格
				return new String[]{getPirceRangeName(value, houseType, 17), getDelSearchFieldUri(field, "p[\\d-]+", 0), "万","元"};
			}else if("pageNum".equals(key)){
				//页码
			}else if("subwayDistance".equals(key)){
				//距离地铁站距离
				int number = 83;
				if("2".equals(houseType))		number = 79;
				else if("3".equals(houseType))	number= 87;
				return new String[]{getRangeName(value, houseType, number), getDelSearchFieldUri(field, "d[\\d]+", 0), "", ""};
			}else if("firstpay".equals(key)){
				//首付
			}else if("monthpay".equals(key)){
				//月供
			}else if("loanyears".equals(key)){
				//贷款年限
			}else if("rent_price".equals(key)){
				//租金
				return new String[]{getRangeName(value, houseType, 35), getDelSearchFieldUri(field, "p[\\d-]+", 0), "元", ""};
			}else if("community".equals(key)){
				//小区
				return new String[]{getCommunityName(value),getDelSearchFieldUri(field, "v_[\\d]+", 0), "", ""};
			}else if("homePageBySearchField".equals(key)){
				//首页输入查询字段
				return new String[]{value ,getDelSearchFieldUri(field, "(cx[\u4E00-\u9FA5]+)|(cx([a-zA-Z]+)([\\d]+)?)", 0), "",""};
			}else if("inital".equals(key)){
				//字母查询
				return new String[]{value.toUpperCase(), getDelSearchFieldUri(field, "q_[a-zA-Z]([\\d_]+)?", 0), "",""};
			}else if("communityErpId".equals(key)){
				//字母下的小区
				return new String[]{getOrdinaryName(value, houseType, 58), getDelSearchFieldUri(field, "(q)_([a-zA-Z])_([\\d]+)", 3), "",""};
			}else if("buildYear".equals(key)){
				//小区年代
				return new String[]{getRangeName(value, houseType, 54), getDelSearchFieldUri(field, "nd[\\d]+", 0), "年","以后"};
			}else if("avgPrice".equals(key)){
				//小区均价
				return new String[]{getPirceRangeName(value, houseType, 52), getDelSearchFieldUri(field, "p[\\d-]+", 0), "万", ""};
			}
		}
		return null;
	}

	/**
	 * 返回小区名称
	 * @param value		小区Id
	 * @return
	 */
	private String getCommunityName(String value){
		String sql = "SELECT erp_id as id, 1 as searchitem, community_name as fieldname, deleteflag  FROM house_community  WHERE deleteflag = 0 and erp_id = " + value;
		List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
		return fields.get(0).getFieldname();
	}
	/**
	 * 返回商圈名称或者城区名
	 * @param value		
	 * @param houseType 房源查询条件类别: 二手房为2, 租赁为3, 小区为4 
	 * @param type		是返回城区名,否 返回网站商圈
	 * @return
	 */
	private String getCountyNameOrWebsiteName( String value, String houseType, boolean type){
		//获取搜索条件
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId(houseType);
		for(SearchItem item : list ){
			if(item.getId() == 34 || item.getId() == 16 || item.getId() == 51){
				for(CommonCounty cc : item.getCounty()){
					if(type){
						if(cc.getErpId().equals(value))
							return cc.getCountyName();		//返回城区名称
					}else{
						for(CommonCbdWebsite ccw : cc.getWebsiteCBD()){
							if(ccw.getWebsiteId() == Integer.parseInt(value))
								return ccw.getName();		//返回商圈名称
						}
					}
				}
			}
		}
		return "";
	}
	
	/**
	 * 返回地铁站点名称或者地铁线路名
	 * @param value
	 * @param houseType		房源查询条件类别: 二手房为2, 租赁为3, 小区为4
	 * @param type			true:地铁线路,false:地铁站点名
	 * @return
	 */
	private String getSubwatStationName(String value, String houseType, boolean type){
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId(houseType);
		for(SearchItem item : list){
			if(item.getId() == 42 || item.getId() == 24 || item.getId() == 55 ){
				for(CommonSubway cs : item.getSubway()){
					if(type){
						if(cs.getErpId().equals(value))
							return cs.getSubwayName();
					}else{
						for(CommonSubwayStation css : cs.getSubwayStation()){
							if(css.getErpId().equals(value))
								return css.getStationName();
						}
					}
				}
			}
		}
		return "";
	}
	
	/**
	 * 返回杭州租房 城区名或者商圈名
	 * @param value
	 * @param type		true: 城区名, false: 商圈名
	 * @return
	 */
	private String getHzzfCbdName(String value, boolean type){
		List<CommonCountyTwo> commonCountyTwoList = commonCountyTwoService.getCommonCountyTwoList();
		for(CommonCountyTwo cct : commonCountyTwoList ){
			if(type){
				if(cct.getErpId().equals(value))
					return cct.getCountyName();
			}else{
				for(CommonCbdWebsite ccw : cct.getCbdWebsiteList()){
					if(Integer.parseInt(value) == ccw.getWebsiteId())
						return ccw.getName();
				}
			}
		}
		return "";
	}

	/**
	 * 普通的值
	 * @param value
	 * @param houseType 房源查询条件类别: 二手房为2, 租赁为3, 小区为4
	 * @param id
	 * @return
	 */
	private String getOrdinaryName(String value, String houseType, int id){
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId(houseType);
		for(SearchItem item : list){
			if(item.getId() == id){
				for(SearchField field : item.getFields()){
					if(value.equals(String.valueOf(field.getId())))
						return field.getFieldname();
				}
			}
		}
		return "";
	}
	/**
	 * 一般的范围取值 
	 * @param value			
	 * @param houseType		房源查询条件类别: 二手房为2, 租赁为3, 小区为4
	 * @return
	 */
	private String getRangeName(String value, String houseType, int id){
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId(houseType);
		String[] val = value.split("_");
		if(val.length < 2)
			return val[0];
		for(SearchItem item : list){
			if(item.getId() == id){
				for(SearchField field : item.getFields()){
					if(val[0].equals(field.getMinfieldvalue()) && (val[1].equals(field.getMaxfieldvalue()) || val[1].equals(MAXIMUM_UPPER_BOUND)))
						return field.getFieldname();
				}
				return val[0] + "-" + val[1];
			}
		}
		return "";
	}
	
	/**
	 * 小区价格,二手房价格
	 * @param value
	 * @param houseType 房源查询条件类别: 二手房为2, 租赁为3, 小区为4
	 * @param number
	 * @return
	 */
	private String getPirceRangeName(String value, String houseType, int number){
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId(houseType);
		String[] val = value.split("_");
		if(val.length < 2) return val[0];
		String minVal = String.valueOf( Double.parseDouble(val[0])/10000);
		String maxVal = String.valueOf( Double.parseDouble(val[1])/10000);
		if(minVal.endsWith(".0"))
			minVal = minVal.replace(".0", "");
		if(maxVal.endsWith(".0"))
			maxVal = maxVal.replace(".0", "");
		if(val[1].equals(MAXIMUM_UPPER_BOUND))
			maxVal = MAXIMUM_UPPER_BOUND;
		for(SearchItem item : list){
			if(item.getId() == number){
				for(SearchField field : item.getFields()){
					if(minVal.equals(field.getMinfieldvalue()) && maxVal.equals(field.getMaxfieldvalue()) )
						return field.getFieldname();
				}
				return minVal + "-" + maxVal;
			}
		}
		return "";
	}
	
	/**
	 * 居室
	 * @param value
	 * @return
	 */
	private String getShiName(String value){
		String[] val = value.split("_");
		if(val.length < 2)
			return SHI[Integer.valueOf(val[0])];
		else if(val[0].equals(val[1]))
			return SHI[Integer.valueOf(val[0])];
		else 
			return SHI[Integer.valueOf(val[0])] + "-" + SHI[Integer.valueOf(val[1])];
	}

	/**
	 * 给出删除条件的链接
	 * @param searchField 	需要替换的内容
	 * @param regular		正则
	 * @param number		需要替换空""的部分,group(int number) number的值
	 * @return			  	返回删除条件的链接
	 */
	private String getDelSearchFieldUri(String searchField, String regular, int number){
		String _searchField = "";
		if(StringUtil.isNotEmpty(searchField)){
			Matcher m = Pattern.compile(regular).matcher(searchField);
			while(m.find()){
				if(number == 1){
					_searchField = m.group(1);
				}
				else if(number > 1){
					/* 
					 	例子：
					 	 	searchField = a_12_12/a1, regular = (a)_([\\d]+)_([\\d]+), number = 3
					 	 	field = a_12_12 ---> field = a_12_ 
					 	 	_searchField = a_12_12/a1 --->_searchField = a_12_/a1 
					  */
					String field = m.group();
					field = field.replace(m.group(number), "");
					_searchField = searchField.replace(m.group(), field);
				}else{
					_searchField = searchField.replace(m.group(number), "");
				}
			}
			if(_searchField.endsWith("null"))
				_searchField = _searchField.substring(0, _searchField.length()-5);
			if(_searchField.endsWith("/"))
				_searchField = _searchField.substring(0, _searchField.length()-2);
			if(!_searchField.startsWith("/") && !"".equals(_searchField))
				_searchField = "/" + _searchField;
		}
		return _searchField;
	}
	
	/**
	 * 判断 @param content 是否存在 regular正则的内容
	 * @param content	内容
	 * @param regular	正则
	 * @return			true 存在, false 不存在
	 */
	private boolean existContent(String content, String regular){
		if(StringUtil.isNotEmpty(content)){
			Matcher m = Pattern.compile(regular).matcher(content);
			return m.find();
		}
		return false;
	}
}
