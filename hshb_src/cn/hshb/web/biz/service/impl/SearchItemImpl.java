/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonCountyMapper;
import cn.hshb.web.biz.mybatis.dao.CommonSubwayMapper;
import cn.hshb.web.biz.mybatis.dao.SearchItemMapper;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.CommonSubway;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.mybatis.model.SearchItem;
import cn.hshb.web.biz.mybatis.model.SearchItemExample;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.biz.util.HouseTagUtil;

/**
 * @author Administrator
 *
 */
@Service
public class SearchItemImpl implements SearchItemService {
	private static Map<String, List<SearchItem>> searchItemMap = new HashMap<String, List<SearchItem>>();
	
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private CommonCountyMapper commonCountyMapper;
	@Autowired
	private CommonSubwayMapper commonSubwayMapper;
	
	@Override
	public List<SearchItem> getSearchItems() {
		return searchItemMapper.selectWithFields();
	}

	/**
	 * 载入其他查询条件
	 * @param item
	 */
	private void loadSearchFields(SearchItem item){
		if(item.getIsfromtable() == 1){
			//从id判断
			int searchItemId = item.getId(); 
			//从其他表查询
			String typeName = item.getEntityname();
			
			if("Community".equals(typeName)){
				//从小区表查  用于字母查询 maxfieldvalue:存放字母
				String sql = "SELECT erp_id as id,"+ item.getId() +" as searchitem, community_name as fieldname, inital as maxfieldvalue, deleteflag   FROM house_community  WHERE deleteflag=0 ORDER BY erp_id";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
				item.setInitals(initInital(fields));
			}else if("Type".equals(typeName)){
				//从户形表(common_housetype)
				String sql = "select erp_id as id, "+item.getId()+" as searchitem, ht_name as fieldname, deleteflag from common_housetype where deleteflag=0 order by erp_id ";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
			}else if("Usage".equals(typeName)){
				//从房源用途表(common_usage)
				String sql = "select erp_id as id, "+item.getId()+" as searchitem, usage_name as fieldname, deleteflag from common_usage where deleteflag=0 order by erp_id ";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
			}else if("Tag".equals(typeName)){
				//从房源标签表(common_flag)
//				String sql = "select id, "+item.getId()+" as searchitem, tag_name as fieldname, deleteflag from common_flag where deleteflag=0 order by id ";
//				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
//				item.setFields(fields);
				//因房源标签数据更改
				List<CommonFlag> list = HouseTagUtil.getTagNamesAndValue();
				List<SearchField> fields = new ArrayList<SearchField>();
				for(CommonFlag flag : list){
					SearchField field = new SearchField();
					field.setId(flag.getModuleId());
					field.setSearchitem(item.getId());
					field.setFieldname(flag.getTagName());
					field.setDeleteflag(0);
					fields.add(field);
				}
				item.setFields(fields);
//			}else if("SubwayLine".equals(typeName)){
//				//从地铁线路表(common_subway)
//				String sql = "select erp_id as id, "+item.getId()+" as searchitem, subway_name as fieldname, deleteflag from common_subway where deleteflag=0 order by erp_id ";
//				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
//				item.setFields(fields);
			}else if("Station".equals(typeName)){
				//从地铁站点表(common_subway_station)
				String sql = "select erp_id as id, "+item.getId()+" as searchitem, station_name as fieldname, "
						+"station_line_id as maxfieldvalue/*用maxfieldvalue存放地铁线路ID*/, deleteflag "
						+"from common_subway_station "
						+"where deleteflag=0 "
						+"order by station_line_id, sortIndex ";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
			}else if("Decorations".equals(typeName)){
				//从装修情况表(common_decoration_type)
				String sql = "select erp_id as id, "+item.getId()+" as searchitem, decoration_name as fieldname, deleteflag from common_decoration_type where deleteflag=0 order by erp_id ";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
			}else if("Orientations".equals(typeName)){
				//从房层朝向情况表(common_decoration_type)
				String sql = "select orientation_no as id, "+item.getId()+" as searchitem, orientation_name as fieldname, deleteflag from common_orientations where deleteflag=0 order by orientation_no ";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
			}else if(searchItemId == 16 || searchItemId == 34 || searchItemId == 51 ){
				//对区域进行查询
				List<CommonCounty> countys = commonCountyMapper.selectByCounty();
				item.setCounty(countys);
			}else if(searchItemId == 24 || searchItemId == 42 || searchItemId == 55){
				//对地铁进行查询
				List<CommonSubway> subways = commonSubwayMapper.selectBySubway();
				item.setSubway(subways);
			}else if("RentType".equals(typeName)){
				//租赁形式
				String sql = "SELECT rent_type_no as id, "+item.getId()+" as searchitem, rent_type_name as fieldname, deleteflag FROM common_rent_type WHERE deleteflag = 0  ORDER BY rent_type_no ";
				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
				item.setFields(fields);
			}else if("Furniture".equals(typeName)){
				//租赁配置 maxfieldvalue 存放图片地址
//				String sql = "SELECT erp_id as id, "+item.getId()+" as searchitem, clf_name as fieldname, icon_url as maxfieldvalue, deleteflag FROM common_live_facility WHERE deleteflag = 0 ORDER BY erp_id";
//				List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
//				item.setFields(fields);
				
				//更改为枚举
				List<CommonLiveFacility> furnitureList = HouseTagUtil.getFurnitureList();
				List<SearchField> fields = new ArrayList<SearchField>();
				for(CommonLiveFacility furniture : furnitureList){
					SearchField field = new SearchField();
					field.setId(Integer.valueOf(furniture.getErpId()));
					field.setSearchitem(item.getId());
					field.setFieldname(furniture.getClfName());
					field.setMaxfieldvalue(furniture.getIconUrl());
					fields.add(field);
				}
				item.setFields(fields);
			}
		}
	}
	
	/**
	 * 查询二手房搜索条件
	 */
	public List<SearchItem> loadSearchItemByModuleId(String moduleId) {
		if(!searchItemMap.containsKey(moduleId) || searchItemMap.get(moduleId).size()<1){
			synchronized (SearchItemImpl.class) {
				if(!searchItemMap.containsKey(moduleId) || searchItemMap.get(moduleId).size()<1) 
					loadSearchItems(moduleId);
			}
		}
		return searchItemMap.get(moduleId);
	}
	
	/**
	 * 载入指定模块的查询条件
	 * @param moduleId
	 */
	private void loadSearchItems(String moduleId){
		SearchItemExample example = new SearchItemExample();
		example.createCriteria()
			.andModulenameEqualTo(moduleId)
			.andIsparentEqualTo(0)
			.andDeleteflagEqualTo(0);
		List<SearchItem> searchItemList = searchItemMapper.selectWithFieldsByExample(example);
		
		loadSubSearchItems(searchItemList);
		
		//放入缓存
		searchItemMap.put(moduleId, searchItemList);
	}
	

	/**
	 * 递归查询下级搜索条件
	 * @param searchItemList
	 */
	private void loadSubSearchItems(List<SearchItem> searchItemList){
		SearchItemExample example = new SearchItemExample();
		for(SearchItem item: searchItemList){
			//载入其他查询条件
			loadSearchFields(item);

			example = new SearchItemExample();
			example.createCriteria()
				.andParentEqualTo(item.getId())
				.andIspublicEqualTo(0)
				.andDeleteflagEqualTo(0);
			
			List<SearchItem> subList = searchItemMapper.selectWithFieldsByExample(example);
			item.setSubItems(subList);
			if(subList.size()>0)
				loadSubSearchItems(subList);
		}
	}
	
	/**
	 * 初始化字母查询条件
	 */
	private List<String> initInital(List<SearchField> fields){
		List<String> initalList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		//根据map key的唯一性,对重复的字母进行解析
		for(SearchField sf : fields)
			//获取字母,转换为大写
			//map.put(sf.getMaxfieldvalue().toLowerCase(), sf.getMaxfieldvalue().toLowerCase());
			map.put(sf.getMaxfieldvalue().toUpperCase(), sf.getMaxfieldvalue().toUpperCase());
		for(String inital : map.keySet())
			initalList.add(inital);
		//对字母进行排序
		Collections.sort( initalList);
		return initalList;
	}
}
