/**
 * 
 */
package cn.hshb.web.biz.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huatek.hbwebsite.house.entity.Apartment;
import com.huatek.hbwebsite.house.entity.Evaluation;
import com.huatek.hbwebsite.util.ERPPictureUtil;
import com.huatek.hbwebsite.util.HouseOrientationUtil;

import cn.hshb.web.biz.mybatis.dao.CommonCbdWebsiteMapper;
import cn.hshb.web.biz.mybatis.dao.CommonDecorationTypeMapper;
import cn.hshb.web.biz.mybatis.dao.CommonOrientationsMapper;
import cn.hshb.web.biz.mybatis.dao.CommonSubwayStationMapper;
import cn.hshb.web.biz.mybatis.dao.CommonUsageMapper;
import cn.hshb.web.biz.mybatis.dao.HouseAppraiseMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityHospitalMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySchoolMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySubwayStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseDefaultSortRuleMapper;
import cn.hshb.web.biz.mybatis.dao.HouseSecondHandHouseMapper;
import cn.hshb.web.biz.mybatis.dao.SearchItemMapper;
import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsiteExample;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonDecorationType;
import cn.hshb.web.biz.mybatis.model.CommonDecorationTypeKey;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonOrientations;
import cn.hshb.web.biz.mybatis.model.CommonOrientationsExample;
import cn.hshb.web.biz.mybatis.model.CommonOrientationsKey;
import cn.hshb.web.biz.mybatis.model.CommonSubway;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.CommonUsage;
import cn.hshb.web.biz.mybatis.model.CommonUsageKey;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseAppraiseExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMappingExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMappingExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMappingExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMappingExample;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouseExample;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.mybatis.model.SearchItem;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.service.CommonFlagService;
import cn.hshb.web.biz.service.CommonSubwayStationService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.biz.service.MemberBrowseHistoryService;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.biz.util.HouseRecommendUtil;
import cn.hshb.web.biz.util.HouseTagUtil;
import cn.hshb.web.common.helper.HouseHelper;
import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.house.enums.EnumHouseTag;
import cn.hshb.web.house.enums.EnumHouseType;
import net.sf.cglib.asm.*;

/**
 * @author Administrator
 *
 */
@Service
public class HouseSecondHandHouseServiceImpl implements HouseSecondhandHouseService {
	private static final Log log = LogFactory.getLog(HouseSecondHandHouseServiceImpl.class);
	public static final Integer PAGE_SIZE=20;  //默认每页记录数
	private final String[] shi = {"未知","一","二","三","四","五","六","七","八","九"};
	@Autowired
	private HouseSecondHandHouseMapper houseSecondHandHouseMapper;

	@Autowired
	private HousePictureService housePictureService;
	
	@Autowired
	private HouseCommunityService houseCommunityService;

	@Autowired
	private HouseAppraiseMapper houseAppraiseMapper;
	
	@Autowired
	private MemberBrowseHistoryService memberBrowseHistoryService;
	
	@Autowired
	private HouseCommunityHospitalMappingMapper communityHospitalMappingMapper;

	@Autowired
	private HouseCommunitySchoolMappingMapper houseCommunitySchoolMappingMapper;
	
	@Autowired
	private HouseCommunityStationMappingMapper houseCommunityStationMappingMapper;
	
	@Autowired
	private HouseCommunitySubwayStationMappingMapper houseCommunitySubwayStationMappingMapper;
	
	@Autowired
	SearchItemService searchItemService;
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	
	@Autowired
	private CommonFlagService commonFlagService;
	
	@Autowired
	private CommonUsageMapper commonUsageMapper;
	
	@Autowired
	private CommonDecorationTypeMapper commonDecorationTypeMapper;
	
	@Autowired
	private HouseDefaultSortRuleMapper houseDefaultSortRultMapper;
	
	@Autowired
	private BBrokerService bBrokerService;
	
	@Autowired
	private CommonCbdWebsiteMapper commonCbdWebsiteMapper;
	
	@Autowired
	private CommonCountyService commonCountyService;
	
	@Autowired
	private CommonOrientationsMapper commonOrientationsMapper;
	
	@Autowired
	private CommonSubwayStationService commonSubwayStationService;
	/**
	 * 本例中分页组件的用法：
		//用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list);
		//测试PageInfo全部属性
		//PageInfo包含了非常全面的分页属性
		assertEquals(1, page.getPageNum());
		assertEquals(10, page.getPageSize());
		assertEquals(1, page.getStartRow());
		assertEquals(10, page.getEndRow());
		assertEquals(183, page.getTotal());
		assertEquals(19, page.getPages());
		assertEquals(1, page.getFirstPage());
		assertEquals(8, page.getLastPage());
		assertEquals(true, page.isFirstPage());
		assertEquals(false, page.isLastPage());
		assertEquals(false, page.isHasPreviousPage());
		assertEquals(true, page.isHasNextPage());

	 */
	@Override
	public List<HouseSecondHandHouse> getHouseList(Map<String, String> conditions, Map<String, String> orderFields, Integer pageNum) {
		return getHouseList(conditions, orderFields, pageNum, PAGE_SIZE);
	}

	@Override
	public List<HouseSecondHandHouse> getHouseList(Map<String, String> conditions, Map<String, String> orderFields,	Integer pageNum, Integer pageSize) {
		Map<String, Object> cond = new HashMap<String, Object>();
		for(String key: conditions.keySet()){
			String value = conditions.get(key);
			if(value.contains("_")){
				//带多参数的表示范围值
				String[] s = value.split("_");
				cond.put(key, s);
			}else if("homePageBySearchField".equals(key)){
				//TODO 处理手动输入条件的分类首页,为什么需要这么做呢？是因为未找到sql执行like中文时,查询不准确的问题
				//城区的
				cond.put("inCountyErpIds", getCountyErpId(value));
				//网站商圈的
				cond.put("inCbdWebsiteIds", getWebsiteCbdId(value));
				//小区
				cond.put("inCommunityErpIds", getCommunityErpId(value));
				//地铁
				cond.put("subwayStationId", getSubwayStationId(value));
				//房源编号
				cond.put(key, new String[]{value});
			}else{
				cond.put(key, new String[] {value});
			}
		}
		
		StringBuilder strOrderBy = new StringBuilder("");
		if(orderFields.size() == 0)	strOrderBy = getOrderField(strOrderBy);
		for(String key: orderFields.keySet()){
			if("sortIndex".equals(key)){
				strOrderBy = getOrderField(strOrderBy);
				continue;
			}
			String value = orderFields.get(key);
			if(StringUtils.isEmpty(value)) value = "ASC";
			strOrderBy.append(", ").append(key).append(" ").append(value);
			
		}
		if(strOrderBy.length()>0){
			strOrderBy.deleteCharAt(0);
			cond.put("orderby", new String[]{strOrderBy.toString()});
		}

		Integer page = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(page, pageSize);

		//紧跟着的第一个select方法会被分页
	    List<HouseSecondHandHouse> houseList = houseSecondHandHouseMapper.selectHouseForListByMap(cond);

//	    houseSecondHandHouseMapper.getClass().
//	    PageInfo p = new PageInfo(houseList);
//	    log.debug("HouseSecondHandHouseServiceImpl.getHouseList.page.Count: " 
//	    + p.getTotal()+", page.pages:" + p.getPages() + ", page.pageNum: " + p.getPageNum());

		//计算房源照片数
	    //已废止，改成直接从房源表中读取图片数量
//		housePictureService.queryPictureCountForHouse(houseList);

		//处理没有封面的房源
		housePictureService.handleHouseCoverImagebyList(houseList);
		
	    for(HouseSecondHandHouse h: houseList){
	    	dealHouseTagIds(h);
	    }

		return houseList;
	}
	
	/**
	 * 根据房源编号检测房源是否存在
	 * @param houseId
	 * @return
	 */
	@Override
	public HouseSecondHandHouse findHouseByHouseId(String houseId){
		HouseSecondHandHouseExample example = new HouseSecondHandHouseExample();
		example.createCriteria().andHouseIdEqualTo(houseId).andDeleteflagEqualTo(0);
		List<HouseSecondHandHouse> houseList = houseSecondHandHouseMapper.selectAssociateByExample(example);

		if(houseList!=null && houseList.size()>0){
			//给查询到的房源载入图片
			housePictureService.loadPictureForHouse(houseList);
			//房源表中有房源封面字段，所以不再按下列方法中的算法进行处理，以提高速度，如果房源表中封面字段无值，则检查房源同步接口
//			housePictureService.getCorrespondingSHPictures(houseList);

			//给房源查询小区信息
			houseCommunityService.loadCommunityForHouse(houseList);
		
			HouseSecondHandHouse house = houseList.get(0);
			this.dealHouseTagIds(house);
			//给小区附上朝向
			if(house.getOrientation() == null){
				CommonOrientationsKey key = new CommonOrientationsKey();
				key.setOrientationNo(house.getOrientationNo());
				house.setOrientation(commonOrientationsMapper.selectByPrimaryKey(key));
			}
			return house;
		}
		return null;
	}
	
	/**
	 * 根据房源ID和经纪人查询经纪人的房源评价
	 * @param house
	 * @param brokerId
	 * @return
	 */
	@Override
	public HouseAppraise findHouseAppraiseByBroker(HouseSecondHandHouse house, String brokerId){
		String _brokerId = brokerId == null? house.getPublisherId() : brokerId;
		
		HouseAppraiseExample example = new HouseAppraiseExample();
		example.createCriteria()
			.andHouseIdEqualTo(house.getHouseId())
			.andBrokerEqualTo(_brokerId)
			.andDeleteflagEqualTo(0);
		
		HouseAppraise houseAppraise = null;
		List<HouseAppraise> list = houseAppraiseMapper.selectByExample(example);
		if(list==null || list.size()<1){
			houseAppraise = new HouseAppraise();
			houseAppraise.setBroker(_brokerId);
			houseAppraise.setTitle(house.getTitle());
		}

		return houseAppraise;
	}
	
	/**
	 * 根据小区ID获取小区与医院关联关系
	 * @param communityId	小区ID
	 * @return
	 */
	@Override
	public List<HouseCommunityHospitalMapping> findCHMapping(String communityId) {
		HouseCommunityHospitalMappingExample example = new HouseCommunityHospitalMappingExample();
		example.createCriteria()
			.andCommunityIdEqualTo(communityId)
			.andDeleteflagEqualTo(0);
		
		return communityHospitalMappingMapper.selectByExample(example);
	}


	/**
	 * 根据小区ID获取小区与学校关联关系
	 * @param communityId	小区ID
	 * @return
	 */
	@Override
	public List<HouseCommunitySchoolMapping> findCSMapping(String communityId) {
		HouseCommunitySchoolMappingExample example = new HouseCommunitySchoolMappingExample();
		example.createCriteria()
			.andCommunityIdEqualTo(communityId)
			.andDeleteflagEqualTo(0);
		return houseCommunitySchoolMappingMapper.selectByExample(example);
	}	

	/**
	 * 根据小区ID获取小区与车站对应关系
	 * @param communityId	小区ID
	 * @return
	 */
	@Override
	public List<HouseCommunityStationMapping> findCStaMapping(String communityId) {
		HouseCommunityStationMappingExample example = new HouseCommunityStationMappingExample();
		example.createCriteria()
			.andCommunityIdEqualTo(communityId)
			.andDeleteflagEqualTo(0);
		
		return houseCommunityStationMappingMapper.selectByExample(example);
	}

	/**
	 * 根据小区ID获取小区与地铁站对应关系
	 * @param communityId	小区ID
	 * @return
	 */
	@Override
	public List<HouseCommunitySubwayStationMapping> findCSubMapping(String communityId) {
		HouseCommunitySubwayStationMappingExample example = new HouseCommunitySubwayStationMappingExample();
		example.createCriteria()
			.andCommunityIdEqualTo(communityId)
			.andDeleteflagEqualTo(0);
		
		return houseCommunitySubwayStationMappingMapper.selectByExample(example);
	}
	
	/**
	 * 计算并查询推荐房源
	 * @param house
	 * @param recommandUtil
	 * @return
	 */
	@Override
	public List<HouseSecondHandHouse> findHouseSecondRecommend(HouseSecondHandHouse house, HouseRecommendUtil recommandUtil){
		if (recommandUtil != null) {
			String price = recommandUtil.putPriceToQueue(house.getPrice());
			String area = recommandUtil.putAreaToQueue(house.getArea());
			String shi = recommandUtil.putShiToQueue(house.getShi());
			List<HouseSecondHandHouse> houseList = getHouseByConditionRange(price, area, shi, house.getErpId(), 4);   //原代码中只取四条，这里也参考它取四条记录
			
			//给查询到的房源载入图片
			housePictureService.loadPictureForHouse(houseList);
			
			//给房源查询小区信息
			houseCommunityService.loadCommunityForHouse(houseList);
			
			return houseList;
		}
		return null;
	}
	
	/**
	 * 根据查询条件范围查询房源记录，主要用于查询推荐房源 
	 * @param priceRange
	 * @param areaRange
	 * @param shi
	 * @param houseErpId
	 * @param maxRows
	 * @return
	 */
	@Override
	public List<HouseSecondHandHouse> getHouseByConditionRange(String priceRange, String areaRange, String shi, String houseErpId, int maxRows) {
		String[] priceRanges = priceRange.split(",");
		Float priceMin = Float.valueOf(priceRanges[0]);
		Float priceMax = Float.valueOf(priceRanges[1]);
		String[] areaRanges = areaRange.split(",");
		Float areaMin = Float.valueOf(areaRanges[0]);
		Float areaMax = Float.valueOf(areaRanges[1]);
		Integer shiInt = Integer.parseInt(shi);
		HouseSecondHandHouseExample example = new HouseSecondHandHouseExample();
		example.createCriteria()
			.andPriceBetween(priceMin, priceMax)
			.andAreaBetween(areaMin, areaMax)
			.andShiEqualTo(shiInt)
			.andErpIdNotEqualTo(houseErpId)
			.andDeleteflagEqualTo(0);
		
		if(maxRows>0)
			PageHelper.startPage(1, maxRows);
		List<HouseSecondHandHouse> houseList = houseSecondHandHouseMapper.selectByExample(example);
		
		//给查询到的房源载入图片
		housePictureService.loadPictureForHouse(houseList);
		
		//给房源查询小区信息
		houseCommunityService.loadCommunityForHouse(houseList);
		
		return houseList;
	}

	/**
	 * 载入推荐到首页的房源
	 * @param count	要载入的记录数
	 * @return
	 */
	@Override
	public List<HouseSecondHandHouse> loadRecommandedHouse(Integer rows) {
		HouseSecondHandHouseExample example = new HouseSecondHandHouseExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		example.setOrderByClause(" homepage_recommand_flag desc, homepage_recommand_time desc");

		if(rows!=null && rows>0)
			PageHelper.startPage(1, rows);
		List<HouseSecondHandHouse> houseList = houseSecondHandHouseMapper.selectAssociateByExample(example);
		
		for(HouseSecondHandHouse h: houseList){
			dealHouseTagIds(h);
		}
		
		//给查询到的房源载入图片
		housePictureService.loadPictureForHouse(houseList);
		
		//给房源查询小区信息
		houseCommunityService.loadCommunityForHouse(houseList);

		return houseList;
	}

	
	/**
	 * 处理房源标签代码，拆分成列表放入房源对象
	 * @param house
	 */
	private void dealHouseTagIds(HouseSecondHandHouse house){
    	String tags = house.getTags();
    	if(StringUtil.isEmpty(tags)) return;
		String[] ids = tags.split(",");
		List<String> lst = new ArrayList<String>();
		for(String s: ids)
			lst.add(s);
		house.setTagIds(lst);
	}

	/**
	 * 把条件放入list,用于'本次查询条件'显示
	 */
	@Override
	public List<SearchField> getSearchFieldList(Map<String, String> condition, String url, String searchFields){
		List<SearchField> list = new ArrayList<SearchField>();
		for(String key : condition.keySet()){
			String[] str =  searchFieldByClassify(key);
			if(str != null){
				try{
				SearchField field = new SearchField();
				////查询字段的名称getFieldname
				String fieldName = getFieldname(key,condition.get(key), Integer.parseInt(str[1]));
				if(!"".equals(fieldName) && !"".equals(str[2]) && !fieldName.contains(str[2]))
					fieldName += str[2] + str[3];
				field.setFieldname(fieldName);
				//把需要删除的条件，在查询条件中替换为空 getReplaceBySearchFields
				field.setMinfieldvalue(url+getReplaceBySearchFields(searchFields, str));
				list.add(field);
				}catch(Exception ex){
					log.error("在二手房接口,分析'本次找房条件'失败,该查询条件为:" + searchFields + ", 分析到" + str[0] + "失败");
				}
			}
		}
		return list;
	}

	/**
	 * 根据字段查询名称
	 * @param key
	 * @return
	 */
	private String getFieldname(String key, String value, int number){
		
		//获取全部搜索条件
		List<SearchItem> list = searchItemService.loadSearchItemByModuleId("2");
		if(number == -3){
			//直接搜索小区
			String sql = "SELECT erp_id as id, 1 as searchitem, community_name as fieldname, deleteflag  FROM house_community  WHERE deleteflag = 0 and erp_id = " + value;
			List<SearchField> fields = searchItemMapper.selectFieldsBySQL(sql);
			return fields.get(0).getFieldname();
		}else if(number == 0 )
			return value;
		for(SearchItem si : list){
			//区域
			if(number == 16 || number == -1){
				if(si.getId() == 16){
					for(CommonCounty cc : si.getCounty()){
						if(number == -1){
							for(CommonCbdWebsite ccw : cc.getWebsiteCBD()){
								//返回商圈名称
								if(ccw.getWebsiteId() ==Integer.parseInt(value))
									return ccw.getName();
							}
						}else if(number == 16)
							//返回城区名称 
							if(cc.getErpId().equals(value))return cc.getCountyName();
					}
				}
			}else if( (number == -2 || number == 24) && si.getId() == 24 ){
				for(CommonSubway cs : si.getSubway()){
					if(number == -2){
						// 地铁站点
						for(CommonSubwayStation css : cs.getSubwayStation()){
							if(css.getErpId().equals(value))
								return css.getStationName();
						}
					}else if(number == 24){
						//地铁线路
						if(cs.getErpId().equals( value))
							return cs.getSubwayName();
					}
				}
			}else{
				//其他没有下级查询的字段
				if(si.getId() == number){
					boolean b = true;
					for(SearchField field : si.getFields()){
						//居室, 朝向, 装修等
						if( number == 21 || number == 22 || number == 23 ){
							if(field.getId() == Integer.parseInt(value)){
								return field.getFieldname();
							}
						}else{
							//剩下的都是范围性的值
							String[] val = value.split("_");
							if(number == 19){
								return getShi(val);
							}
//							if(value.contains(field.getMinfieldvalue()) && value.contains(field.getMaxfieldvalue())){
							if(val[0].equals(field.getMinfieldvalue()) && val[1].equals(field.getMaxfieldvalue()))
								return field.getFieldname();
							else if(val[0].length()>5 && val[1].length()>5){
								if((val[0].substring(0, val[0].length()-4)).equals(field.getMinfieldvalue())){ 
									if(val[1].equals(field.getMaxfieldvalue()))
										return field.getFieldname();	
									else if((val[1].substring(0, val[1].length()-4)).equals(field.getMaxfieldvalue()) )
										return field.getFieldname();
								}
								b = false;
							}else if(val[0].equals("0") && val[1].length() >4){
								if((val[0].equals(field.getMinfieldvalue())) 
										&& (val[1].substring(0, val[1].length()-4)).equals(field.getMaxfieldvalue()))
									return field.getFieldname();
								b = false;
							}
								//判断是否是自定义输入
								b = false;
							
						}
					}
					if(!b){
						if(value.length() > 10){
							//自定义输入价格
							String[] val = value.split("_");
							if(val[0].length() > 4 )
								val[0] = val[0].substring(0,val[0].length()-4);
							if(val[1].length() > 4)
								val[1]= val[1].substring(0, val[1].length()-4);
							return val[0] + "-" + val[1];
						}
						return value.replace("_", "-");
					}
				}
			}

		}
		return "";
	}
	/**
	 * 把搜索条件分类
	 * @return
	 */
	private String[]  searchFieldByClassify(String key){
		if(!StringUtils.isEmpty(key)){
			if("countyId".equals(key)){
				//城区
				return new String[]{"a_","16", "", ""};
			}else if("cbdId".equals(key)){
				//商圈
				return new String[]{"a__","-1", "", ""};
			}else if("station_line_id".equals(key)){
				//地铁
				return new String[]{"t_", "24", "", ""};
			}else if("subway_station_id".equals(key)){
				//地铁线路
				return new String[]{"t__", "-2", "", ""};
			}else if("area".equals(key)){
				//面积
				return new String[]{"a", "18", "平", "米"};
			}else if("shi".equals(key)){
				//室
				return new String[]{"r", "19", "", ""};
			}else if("complate_year".equals(key)){
				//房龄
				return new String[]{"y", "20", "", ""};
			}else if("orientation_no".equals(key)){
				//朝向
				return new String[]{"f", "21", "", ""};
			}else if("decoration_id".equals(key)){
				//装修
				return new String[]{"z", "22", "", ""};
			}else if("tags".equals(key)){
				//房源标签
				return new String[]{"xs", "23", "", ""};
			}else if("rentType".equals(key)){
				//租赁形式
			}else if("orderby".equals(key)){
				//排序条件
			}else if("furniture".equals(key)){
				//租赁配置
			}else if("price".equals(key)){
				//价格
				return new String[]{"p", "17", "万", "元"};
			}else if("pageNum".equals(key)){
				//页码
			}else if("subwayDistance".equals(key)){
				//距离地铁站距离
				return new String[]{"d","79", "", ""};
			}else if("community".equals(key)){
				//小区
				return new String[]{"v_", "-3", "", ""};
			}else if("homePageBySearchField".equals(key)){
				//首页输入查询字段
				return new String[]{"cx","0","", ""};
			}
		}
		return null;
	}
	
	/**
	 * 返回被替换过的查询条件
	 * @return
	 */
	private String getReplaceBySearchFields(String field, String[] str){
		Pattern p = null;
		Matcher m = null;
		//一般查询条件
		String regular = str[0]+"[\\d]+";
		if("p".equals(str[0]))
			regular = str[0]+"[-\\d.]+";
		else if(("r").equals(str[0]))
			regular = str[0]+ "(([\\d]+-[\\d]+)|([\\d]+-))";
		//首页搜索查询
		else if("cx".equals(str[0])) regular = "(cx[\u4E00-\u9FA5]+)|(cx[a-zA-Z]+\\d+)|(cx\\d+)";
		//删除区域或地铁
		else if("a_".equals(str[0]) || "t_".equals(str[0]) || "zj".equals(str[0]) || "v_".equals(str[0]))
			regular = str[0]+"[\\d_]+";
		else if("a".equals(str[0]))
			//面积与区域发生冲突,所以写的比较死
//			regular = str[0] + "(([\\d]+_[\\d]+)|([\\d]+))";
			regular = str[0] + "(([\\d]+-[\\d]+)|([\\d]+-))";
		if("a__".equals(str[0]) || "t__".equals(str[0])){
			//当需要删除商圈,线路
			//先判断是否包含了城区
			regular = "(a|t)_[\\d]+";
			m =  Pattern.compile(regular).matcher(field);
			if(!m.find()){
				regular = "a__[\\d]+";
				m = Pattern.compile(regular).matcher(field);
				while(m.find()){
					field = field.replaceAll( m.group(), "");
				}
			}else{
				//如果有包含商圈的,那么则去掉第二个"_"后的值
				m = Pattern.compile("(a|t)(_[\\d]+)(_[\\d]+)").matcher(field);
				while(m.find()){
					field = field.replaceFirst( m.group(3), "_");
				}
			}
		}else{
			p = Pattern.compile(regular);
			m = p.matcher(field);
			while(m.find()){
				String s = m.group();
				field = field.replaceAll(s, "");
			}
		}
		if(field.endsWith("null")){
			field = field.substring(0,field.length()-5);
		}
		if(field.endsWith("/")){
			field = field.substring(0, field.length()-2);
		}
		if(!field.startsWith("/") && !"".equals(field)){
			field = "/" + field;
		}
		return field;
	}
	
	/**
	 * 解析房源中的房源标签
	 * @param tagStr 房源标签字符串，可能是逗号分隔的标签ID字符串，也可能是整型的按位存储的标签组合整型值
	 * @return
	 */
	@Override
	public List<CommonFlag> parseHouseTag(String tagStr){
		List<CommonFlag> list = new ArrayList<CommonFlag>();
		if(StringUtil.isNumeric(tagStr)){
			Integer tagVal = StringUtil.parseInt(tagStr, 0);
			List<EnumHouseTag> tagList = HouseTagUtil.getTagsByValue(tagVal);
			for(EnumHouseTag t: tagList){
				CommonFlag tag = new CommonFlag();
				tag.setErpId(String.valueOf(t.getValue()));
				tag.setTagName(t.getTitle());
				tag.setFlagcolor(t.getColor());
				tag.setFontcolor("#FFFFFF");
				list.add(tag);
			}
		}else{
			List<CommonFlag> allTagList = commonFlagService.getHouseTags();
			String[] tagIds = tagStr.split(",");
			for(String s: tagIds){
				for(CommonFlag t: allTagList){
					if(t.getErpId().equalsIgnoreCase(s)){
						list.add(t);
						break;
					}
				}
			}
		}
		return list;
	}
	/**
	 * 默认 排序 
	 * @return
	 */
	private StringBuilder getOrderField(StringBuilder sb){
		List<String> list = houseDefaultSortRultMapper.selectAllData();
		if(list != null || list.size() >0){
			for(String s : list){
				if("homepageRecommandFlag".equalsIgnoreCase(s))
					sb.append(", h.homepage_recommand_flag DESC");
				else if("exclusiveFlag".equalsIgnoreCase(s))
					sb.append(", h.exclusiveflag DESC");
				else if("focusFlag".equalsIgnoreCase(s))
					sb.append(", h.focusFlag DESC");
				else if("lastSync".equalsIgnoreCase(s))
					sb.append(", h.LastSync DESC");
			}
		}
		return sb;
	}

	@Override
	public HouseSecondHandHouse parseHosueSecondHouseXmlToObject(String strXml) {
		HouseSecondHandHouse hs = new HouseSecondHandHouse();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new StringReader(strXml));
			String communityId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/SubdistrictID", "");
			String houseId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseID", "");
			String houseNo = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseNo", "");
			String houseTitle = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Title", "");
			String releaserID = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/ReleaserID", "");
			String price = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Price",  "0");
			String area = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Area",  "0");
			String visitTime = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimes", "0");
			String visitTimeRange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimeRange", "");
			String houseType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseType", "");
			String floor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Floor", "0");
			String totalFloor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/TotalFloor", "0");
			String toward = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Toward", "");
			String decorating = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Decorating", "");
			String existingState = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/ExistingState", "");
			String propertyType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PropertyType", "");
			String commentTitle = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentTitle", "");
			String commentContent = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentContent", "");
			String priceChange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PriceChange", "");
			String tags = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Tag", "");

//			String[] tagArr = tags.split("\\|");
//			List<String> tagIdList = new ArrayList<String>();
//			for (int ii = 0; ii < tagArr.length; ++ii) {
//				//TODO 房源标签
////				Tag tag = this.rentService.getTagListByName(tagArr[ii]);
////				tagIdList.add(tag.getErpId());
//			}
//			hs.setTags(tagIdList);
			hs.setTags(StringUtil.isNotEmpty(tags)?tags:"0");
			hs.setHouseTags(parseHouseTag(tags));
			hs.setUsageStatus(existingState);
			List<Node> picNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/PictureList/PictureItem");
			List<HousePicture> housePicList = new ArrayList<HousePicture>();
			for(Node node: picNodeList){
				HousePicture housePic = new HousePicture();

				String layoutFlag = getNodeAsString(node, "LayoutFlag", "0");
				housePic.setLayoutflag(Integer.valueOf(layoutFlag));
				
				String orderNum = getNodeAsString(node, "OrderNum", "0");
				housePic.setSortindex(Integer.valueOf(orderNum));

				housePic.setPictureFormat(getNodeAsString(node, "PictureFormat", ""));
				String picData = getNodeAsString(node, "PictureData", "");
				String picURL = ERPPictureUtil.GenerateImage(picData, EnumHouseType.SALE, housePic.getPictureFormat());
				housePic.setPictureUri(picURL);
				housePicList.add(housePic);
			}
			//对房源的描述
			List<Evaluation> evaList = new ArrayList<Evaluation>();
			
			List<Node> evaNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/EvaluationList/EvaluationItem");

			for (int ii = 0; ii < evaNodeList.size(); ++ii) {
				Evaluation eva = new Evaluation();
				// 评价 经纪人信息
//				Broker _broker = this.rentService.getBrokerById(((Node) evaNodeList.get(ii)).selectSingleNode("ValuatorID").getText());
				BBroker broker = bBrokerService.getBroker(((Node) evaNodeList.get(ii)).selectSingleNode("ValuatorID").getText());
				eva.setbBroker(broker);
				eva.setTitle(((Node) evaNodeList.get(ii)).selectSingleNode("Title").getText());
				eva.setContent(((Node) evaNodeList.get(ii)).selectSingleNode("Content").getText());
				evaList.add(eva);
			}
			
			BBroker publisher = new BBroker();
			//经纪人Id
			String publisherId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/AgentID", "");
			String publisherName = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/Name", "");
			String publisherTel = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/Tel", "");
			String publisherWeixin = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/Weixin", "");
			String publisherWeixinUrl = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/WeixinUrl", "");
			String publisherJianJie = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/JianJie", "");
			String publisherTouXiangUrl = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/AgentData/TouXiangUrl", "");
			publisher.setErpId(publisherId);
			publisher.setAgentName(publisherName);
			publisher.setTelephone(publisherTel);
			publisher.setWchat(publisherWeixin);
			publisher.setWechatUrl(publisherWeixinUrl);
			publisher.setIntroduction(publisherJianJie);
			//TODO 这是从ERP获取经纪人图片的,因为该图片为临时图片,可以删除,所以先放在这里二手房了
			publisher.setPhotographPath(ERPPictureUtil.GenerateImage(publisherTouXiangUrl, EnumHouseType.SALE, "jpg"));
			
			hs.setCommunity(houseCommunityService.findCommunityByCommunityId(communityId));
			hs.setCommunityId(communityId);
			hs.setErpId(houseId);
			hs.setHouseId(houseNo);
			hs.setPublisher(publisher);
			
//			hs.setBroker(rentService.getBrokerById(releaserID));
//			hs.setPublisherId(releaserID);
			hs.setPrice(Float.valueOf(price));
			hs.setArea(Float.valueOf(area));
			hs.setTimeToSee(visitTimeRange);
			Apartment apartment = HouseOrientationUtil.getInstance().splitApartment(houseType);
			hs.setShi(Integer.valueOf(apartment.getShi()));
			hs.setTing(Integer.valueOf(apartment.getTing()));
			hs.setWei(Integer.valueOf(apartment.getWei()));
			hs.setYangtai(Integer.valueOf(apartment.getBalcony()));
			
//			hs.setStoryCount(Integer.valueOf(floor));
			hs.setStoreyCount(Integer.valueOf(totalFloor));

			//计算房源所在楼层位于总楼层的上、中或下部
			int houseVervicalLocation = HouseHelper.funFloorPoint( Integer.parseInt(floor), Integer.parseInt(totalFloor));
			hs.setHouseVervicalLocation(houseVervicalLocation);
			
			CommonOrientations orient = new CommonOrientations();
			orient.setOrientationName(toward);
			hs.setOrientation(orient);
			CommonDecorationType decoration = selectDecorationTypeById(String.valueOf(HouseOrientationUtil.getInstance().convertDecoration(decorating)));
			hs.setDecoration(decoration);
			hs.setDaikan(Integer.valueOf(visitTime));
			hs.setProperty(propertyType);
			CommonUsage usage = selectUsageById(String.valueOf(HouseOrientationUtil.getInstance().convertUsage(propertyType)));
			hs.setUsage(usage);
			hs.setTitle(houseTitle);
			hs.setContent(commentContent);
//			hs.setLastedThirtyPriceRatio(priceChange);
			hs.setPictures(housePicList);
			hs.setEvaList(evaList);
			Float unitPrice = hs.getArea()!=0 ? hs.getPrice() / hs.getArea() : 0f;
			hs.setUnitPrice(unitPrice);
			hs.setPreviousUnitPrice(unitPrice);
			return hs;
		} catch (DocumentException ex) {
			log.error("分析失败ERPXML-------------");
			log.error("ERPXML内容为:");
			log.error(strXml);
			log.error("完毕",ex);
		}
		return null;
	}
	/**
	 * 从XML节点取值，如果为空则设为默认值
	 * @param doc			XML Document
	 * @param xmlPath		节点路径
	 * @param defaultVal	默认值
	 * @return
	 */
	private String getNodeAsString(Document doc, String xmlPath, String defaultVal){
		String val = doc.selectSingleNode(xmlPath).getText();
		val = StringUtils.isBlank(val)?defaultVal:val;
		return val;
	}
	/**
	 * 从XML节点取值，如果为空则设为默认值
	 * @param doc			XML Document
	 * @param xmlPath		节点路径
	 * @param defaultVal	默认值
	 * @return
	 */
	private String getNodeAsString(Node node, String xmlPath, String defaultVal){
		String val = node.selectSingleNode(xmlPath).getText();
		val = StringUtils.isBlank(val)?defaultVal:val;
		return val;
	}
	/**
	 * 根据房源用途id,获取信息
	 * @param usageId
	 * @return
	 */
	private CommonUsage selectUsageById(String usageId) {
		if(StringUtil.isEmpty(usageId))
			return null;
		CommonUsageKey usageKey = new CommonUsageKey();
		usageKey.setErpId(usageId);
		return commonUsageMapper.selectByPrimaryKey(usageKey);
	}
	/**
	 * 根据装修程度id,获取信息
	 * @param erpId
	 * @return
	 */
	private CommonDecorationType selectDecorationTypeById(String erpId){
		if(StringUtil.isEmpty(erpId))
			return null;
		CommonDecorationTypeKey decoration = new CommonDecorationTypeKey();
		decoration.setErpId(erpId);
		return commonDecorationTypeMapper.selectByPrimaryKey(decoration);
	}
	/**
	 * 返回城区名称是否包含该关键字的数据 
	 * @param countyName
	 * @return
	 */
	private List<String> getCountyErpId(String countyName){
		List<CommonCounty> list = commonCountyService.getCommonCountyies();
		List<String > sList = null;
		if(list != null && list.size() >0){
		 sList = new ArrayList<String>();
			for(CommonCounty cc : list){
				if(cc.getCountyName().contains(countyName)){
					sList.add(cc.getErpId());
				}
			}
			if(sList.size() <1)	sList = null;
		}
		return sList;
	}
	/**
	 * 返回小区名称包含该关键字的数据
	 * @param searchField
	 * @return
	 */
	private List<String> getCommunityErpId(String searchField){
		List<String> sList = null;
		List<HouseCommunity> communityList = houseCommunityService.getHouseCommunityByCommunityName(searchField);
		if(communityList != null && communityList.size() > 0){
			sList = new ArrayList<String>();
			for(HouseCommunity community : communityList){
				if(community.getCommunityName().contains(searchField)){
					sList.add(community.getErpId());
				}
			}
			if(sList.size() < 1) sList = null;
		}
		return sList;
	}
	/**
	 * 返回地铁线路包含该关键字的Id
	 * @param searchField
	 * @return
	 */
	private List<String> getSubwayStationId(String searchField){
		List<String> sList = null;
		List<CommonSubwayStation> commonSubwayStationList = commonSubwayStationService.getCommonSubwayStationByName(searchField); 
		if(commonSubwayStationList != null && commonSubwayStationList.size() >0){
			sList = new ArrayList<String>();
			for(CommonSubwayStation cs : commonSubwayStationList){
				if(cs.getStationName().contains(searchField)){
					sList.add(cs.getErpId());
				}
			}
		}
		return sList;
	}
	/**
	 * 返回网站商圈名称包含该关键字的数据
	 * @param searchField
	 * @return
	 */
	private List<Integer> getWebsiteCbdId(String searchField){
		CommonCbdWebsiteExample example = new CommonCbdWebsiteExample();
		example.createCriteria().andNameLike(searchField).andDeleteFlagEqualTo(0);
		List<CommonCbdWebsite> list = commonCbdWebsiteMapper.selectByExample(example);
		List<Integer> sList = null;
		if(list != null && list.size() > 0){
			sList = new ArrayList<Integer>();
			for(CommonCbdWebsite website : list){
				//这一步可能多此一举,可删
				if(website.getName().contains(searchField)){
					sList.add(website.getWebsiteId());
				}
			}
			if(sList.size()<1)sList=null;
		}
		return sList;
	}
	
	private String getShi(String[] value){
		String regular = "[\\d]";
		if(value.length == 0)
			return shi[0]+"居";
		else if(value.length == 1)
			return shi[getFigure(value[0], regular)] + "居";
		else if(value[0].equals(value[1]))
			return shi[getFigure(value[0], regular)] + "居";
		return shi[getFigure(value[0], regular)] + "-" + shi[getFigure(value[1], regular)] + "居";
			
	}
	private int getFigure(String s, String regular){
		Matcher m = Pattern.compile(regular).matcher(s);
		if(m.find()){
			return Integer.parseInt(m.group());
		}
		return 0;
	}
}
