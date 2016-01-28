/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.dao.CommonCbdWebsiteMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityAveragePriceMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityHospitalMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySchoolMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySubwayStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HousePictureMapper;
import cn.hshb.web.biz.mybatis.model.CommonCbd;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsiteExample;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonStore;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityAveragePrice;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExt;
import cn.hshb.web.biz.mybatis.model.HouseCommunityHospitalMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunityStationMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySubwayStationMapping;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HousePictureExample;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.InputSearch;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.service.CommonCbdService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.service.CommonStoreService;
import cn.hshb.web.biz.service.CommonSubwayStationService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.biz.util.ChineseConversionUtil;
import cn.hshb.web.common.util.DeepCloneUtil;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class HouseCommunityServiceImpl extends AnalyticalQueryFieldImpl implements HouseCommunityService {
	private static final Log log = LogFactory.getLog(HouseCommunityServiceImpl.class);
	//缓存小区基本信息
	private static List<HouseCommunity> communityList = new ArrayList<HouseCommunity>();
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 1 * 3600L;

	private static final Integer PAGE_SIZE = 20;//默认每页房源数量
	
	@Autowired
	private CommonCbdService commonCbdService;
	
	@Autowired
	private HouseCommunityMapper houseCommunityMapper;
	
	@Autowired
	SearchItemService searchItemService;
	
	@Autowired
	private HousePictureMapper housePictureMapper;
	
	@Autowired
	private HouseCommunityAveragePriceMapper houseCommunityAveragePriceMapper;
	
	@Autowired
	private HouseCommunityStationMappingMapper houseCommunityStationMappingMapper;
	
	@Autowired
	private HouseCommunitySchoolMappingMapper houseCommunitySchoolMappingMapper;
	
	@Autowired
	private HouseCommunitySubwayStationMappingMapper houseCommunitySubwayStationMappingMapper;
	
	@Autowired
	private HouseCommunityHospitalMappingMapper houseCommunityHospitalMappingMapper;
	
	@Autowired
	private CommonCbdWebsiteMapper commonCbdWebsiteMapper;
	
	@Autowired
	private HousePictureService housePictureService;
	
	@Autowired
	private CommonCountyService commonCountyService;
	
	@Autowired
	private CommonStoreService commonStoreService;
	
	@Autowired
	private CommonSubwayStationService commonSubwayStationService;
	/**
	 * 给HouseCommunity注入依赖的属性，比如商圈和城区
	 * @param community
	 */
	@Override
	public void populateCommunity(HouseCommunity community) {
		String cbdId = community.getBusinessCircleId();
		CommonCbd cbd = commonCbdService.getCommonCbdById(cbdId);
		if(cbd!=null){
			community.setCbd(cbd);
			String webCbdId = cbd.getBelongTo();
			if(StringUtil.isNotEmpty(webCbdId) && StringUtil.isNumeric(webCbdId)){
				CommonCbdWebsite webCbd = commonCbdService.getCommonCbdWebsiteById(Integer.parseInt(webCbdId));
				community.setCbdWebsite(webCbd);
			}
		}
		String countyId = community.getRegionId();
//		CommonCountyKey key = new CommonCountyKey();
//		key.setErpId(countyId);
//		CommonCounty county = commonCountyMapper.selectByPrimaryKey(key);
		CommonCounty county = commonCountyService.getCounty(countyId);

		community.setCounty(county);
	}

	/**
	 * 查询小区最近门店列表
	 * @param community
	 */
	@Override
	public void loadNearestStores(HouseCommunity community){
		if(community.getNearestStore()!=null){
			String[] storeIds = community.getNearestStore().split(",|\\|;");
			List<String> param = new ArrayList<String>();
			for(String s: storeIds)
				param.add(s);
//			CommonStoreExample example = new CommonStoreExample();
//			example.createCriteria()
//				.andErpIdIn(param)
//				.andDeleteflagEqualTo(0);
//			List<CommonStore> list = commonStoreMapper.selectByExample(example);

			List<CommonStore> list = commonStoreService.getCommonStoresByIds(param);
			community.setNearestStores(list);
		}
	}

	@Override
	public List<HouseCommunityExt> getCommunityList(
			Map<String, String> conditions, Map<String, String> orderFields,
			Integer pageNum) {
		Map<String, Object> cond = new HashMap<String, Object>();
		for(String key : conditions.keySet()){
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
				cond.put(key, new String[]{value});
			}
		}
		
		StringBuilder strOrderBy = new StringBuilder("");
		for(String key : orderFields.keySet()){
			String value = orderFields.get(key);
			if(StringUtils.isEmpty(value)) value = "ASC";
			strOrderBy.append(", ")
			.append(key)
			.append(" ")
			.append(value);
		}
		
		if(strOrderBy.length() > 0){
			strOrderBy.deleteCharAt(0);
			cond.put("orderby", new String[]{strOrderBy.toString()});
		}
		
		Integer page = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(page, PAGE_SIZE);

		//紧跟着的第一个select方法会被分页
		List<HouseCommunityExt> communityList = houseCommunityMapper.selectAssociateByMap(cond);

		//对图片总数的处理
//		housePictureService.queryPictureCountForHouse(communityList);

		//给小区查询图片
		for(HouseCommunityExt hc: communityList){
			housePictureService.loadPictureForCommunity(hc);
		}

		//对小区的封面处理
		housePictureService.handleHouseCoverImagebyList(communityList);
		return communityList;
	}
	
	/**
	 * 根据小区编号获取小区数据
	 */
	@Override
	public HouseCommunity loadCommunityByHouseId(String communityId) {
		/*
		HouseCommunityExample example = new HouseCommunityExample();
		example.createCriteria().andErpIdEqualTo(communityId).andDeleteflagEqualTo(0);
		List<HouseCommunity> communityList = houseCommunityMapper.selectAssociateByExample(example);
		if(communityList != null && communityList.size() > 0){
			HouseCommunity hc = communityList.get(0);
			housePictureService.handleHouseCoverImage(hc);
			return communityList.get(0);
		}
		return null;
		*/
		this.loadAllCommunities();
		
		for(HouseCommunity hc: communityList){
			if(hc.getErpId().equals(communityId)){
				housePictureService.loadPictureForCommunity(hc);
				housePictureService.handleHouseCoverImage(hc);
				try{
					return (HouseCommunity)DeepCloneUtil.cloneObject(hc);
				} catch (IllegalArgumentException ex) {
					log.warn("Clone HouseCommunity failed.", ex);
				} catch (IllegalAccessException ex) {
					log.warn("Clone HouseCommunity failed.", ex);
				} catch (InstantiationException ex) {
					log.warn("Clone HouseCommunity failed.", ex);
				}
				//如果克隆失败，则返回原始对象
				return hc;
			}
		}

		return null;
	}

	/**
	 * 根据小区编号搜索小区
	 */
	@Override
	public HouseCommunity findCommunityByCommunityId(String communityId){
		HouseCommunity community = loadCommunityByHouseId(communityId);
		if(community!=null){
			
			loadWebsiteCbdByCommunity(community);
		}
		return community;
	}
	
	/**
	 * 为小区加载网站商圈信息
	 * @param hc
	 */
	private HouseCommunity loadWebsiteCbdByCommunity(HouseCommunity hc){
		if(hc != null && hc.getCbd() != null){
			String websiteCbdId = hc.getCbd().getBelongTo();
			if(StringUtil.isNotEmpty(websiteCbdId))
				hc.setCbdWebsite(commonCbdWebsiteMapper.selectCbdIdByWebsiteCbd(Integer.parseInt(websiteCbdId)));
		}
		return hc;
	}
	/**
	 * 用于显示"本次找房条件"
	 */
	@Override
	public List<SearchField> getSearchFieldList(Map<String, String> condition, String url, String searchFields){
		List<SearchField> list = new ArrayList<SearchField>();
		for(String key : condition.keySet()){
			String[] str =  searchFieldByClassify(key, condition.get(key), "4", searchFields);
			if(str != null){
				try{
				SearchField field = new SearchField();
				////查询字段的名称getFieldname
				String fieldName = str[0];
				if(!"".equals(fieldName) && !"".equals(str[2]) && !fieldName.contains(str[2]))
					fieldName += str[2] + str[3];
				field.setFieldname(fieldName);
				//把需要删除的条件，在查询条件中替换为空 getReplaceBySearchFields
				field.setMinfieldvalue(url+str[1]);
				list.add(field);
				}catch(Exception ex){
					log.error("在小区接口,分析'本次找房条件'失败,该查询条件为:" + searchFields + ", 分析到" + str[0] + "失败");
				}
			}
		}
		return list;
	}

	/**
	 * 根据小区Id获取小区图片集
	 * @param CommunityId	小区Id
	 * @return
	 */
	@Override
	public List<HousePicture> getHousePictureByCommunityId(String CommunityId) {
		HousePictureExample example = new  HousePictureExample();
		example.createCriteria()
			.andHouseIdEqualTo(CommunityId)
			.andDeleteflagEqualTo(0);
		return housePictureMapper.selectByExample(example);
	}

	/**
	 * 根据小区Id获取小区近6个月的平均价格
	 */
	@Override
	public List<HouseCommunityAveragePrice> getPriceTrendSixMonth(
			String communityId) {
		List<HouseCommunityAveragePrice> list = houseCommunityAveragePriceMapper.selectPriceTrendSixMonth( communityId);
		
		//因为偶写数据日期重复,这里溢出重复日期数据
		for(int ii=0; ii<list.size(); ii++){
			for(int jj=ii+1; jj<list.size(); jj++){
				if(list.get(ii).getCalculatedate().equals(list.get(jj).getCalculatedate())){
					list.remove(jj--);
				}
			}
		}

		return list;
	}
	
	/**
	 * 根据小区Id获取小区与医院关联关系
	 */
	@Override
	public List<HouseCommunityHospitalMapping> findCHMapping(String communityId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("communityId", communityId);
		return houseCommunityHospitalMappingMapper.selectAssociateByMap(map);
	}

	/**
	 * 根据小区Id获取小区与学校关联关系
	 */
	@Override
	public List<HouseCommunitySchoolMapping> findCSMapping(String communityId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("communityId", communityId);
		return houseCommunitySchoolMappingMapper.selectAssociateByMap(map);
	}

	/**
	 * 根据小区Id获取小区与车站对应关系
	 */
	@Override
	public List<HouseCommunityStationMapping> findCStaMapping(String communityId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("communityId", communityId);
		return houseCommunityStationMappingMapper.selectAssociateByMap(map);
	}

	/**
	 * 根据小区Id获取小区与地铁站对应关系
	 */
	@Override
	public List<HouseCommunitySubwayStationMapping> findCSubMapping(
			String communityId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("communityId", communityId);
		return houseCommunitySubwayStationMappingMapper.selectAssociateByMap(map);
	}


	/**
	 * 给指定房源查询小区信息
	 * @param houseList
	 */
	@Override
	public void loadCommunityForHouse(List<? extends Object> houseList) {
		if(houseList == null || houseList.size()<1) return;

		List<String> idList = new ArrayList<String>();
		Object h = houseList.get(0);
		if(h instanceof HouseSecondHandHouse){
			for(HouseSecondHandHouse house: (List<HouseSecondHandHouse>)houseList){
				idList.add(house.getCommunityId());
			}
		}else if(h instanceof HouseRentHouse){
			for(HouseRentHouse house: (List<HouseRentHouse>)houseList){
				idList.add(house.getCommunityId());
			}
		}else{
			log.error("传入房源类型错误，必须是 HouseSecondHandHouse或HouseRentHouse.");
			return;
		}

		/*
		HouseCommunityExample example = new HouseCommunityExample();
		example.createCriteria()
			.andErpIdIn(idList)
			.andDeleteflagEqualTo(0);

		List<HouseCommunity> list = houseCommunityMapper.selectByExample(example);
		for(HouseCommunity hc: list){
			if(hc.getCbd()==null){
				populateCommunity(hc);
			}
		}
		*/
		//改成缓存模式
		this.loadAllCommunities();
		List<HouseCommunity> list = communityList;

		if(h instanceof HouseSecondHandHouse){
			for(HouseSecondHandHouse house: (List<HouseSecondHandHouse>)houseList){
				for(HouseCommunity hc: list){
					if(hc.getErpId().equals(house.getCommunityId())){
						housePictureService.loadPictureForCommunity(hc);
						house.setCommunity(hc);
						break;
					}
				}
			}
		}else if(h instanceof HouseRentHouse){
			for(HouseRentHouse house: (List<HouseRentHouse>)houseList){
				for(HouseCommunity hc: list){
					if(hc.getErpId().equals(house.getCommunityId())){
						housePictureService.loadPictureForCommunity(hc);
						house.setCommunity(hc);
						break;
					}
				}
			}
		}
	}

	@Override
	public List<HouseCommunity> getHouseCommunityByCommunityName(String searchField) {
//		HouseCommunityExample example = new HouseCommunityExample();
//		example.createCriteria().andCommunityNameLike(searchField);
//		List<HouseCommunity> communityList = houseCommunityMapper.selectByExample(example);
//		return communityList;

		//改成缓存模式
		this.loadAllCommunities();
		List<HouseCommunity> list = new ArrayList<HouseCommunity>();
		for(HouseCommunity hc: communityList){
			if(hc.getCommunityName().contains(searchField)){
				try {
					list.add((HouseCommunity)DeepCloneUtil.cloneObject(hc));
				} catch (IllegalArgumentException ex) {
					log.warn("Clone HouseCommunity failed.", ex);
					list.add(hc);
				} catch (IllegalAccessException ex) {
					log.warn("Clone HouseCommunity failed.", ex);
					list.add(hc);
				} catch (InstantiationException ex) {
					log.warn("Clone HouseCommunity failed.", ex);
					list.add(hc);
				}
			}
		}
		return list;
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
		List<HouseCommunity> communityList = getHouseCommunityByCommunityName(searchField);
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
	 * 返回网站名称包含该关键字的数据
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
	
	/**
	 * 载入所有小区数据，并缓存在静态变量
	 */
	public void loadAllCommunities(){
		if(communityList==null || communityList.isEmpty() || isNeedReload()){
			synchronized (HouseCommunityServiceImpl.class) {
				if(communityList==null || communityList.isEmpty() || isNeedReload()) {
					HouseCommunityExample example = new HouseCommunityExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					communityList = houseCommunityMapper.selectByExample(example);

					//给小区设置城区、商圈等信息
					for(HouseCommunity hc: communityList){
						populateCommunity(hc);
					}

					LAST_LOAD_TIME = System.currentTimeMillis();  //重新设置最后刷新时间
				}
			}
		}
	}
	/**
	 *返回所有小区数据
	 */
	public List<HouseCommunity> getAllHouseCommunitys() {
		loadAllCommunities();
		return communityList;
	}
	
	/**
	 * 判断是否到了指定的刷新时间
	 * @return
	 */
	protected Boolean isNeedReload(){
		Long t = System.currentTimeMillis() - LAST_LOAD_TIME;
		return ( t / 1000 >= RELOAD_INTERVAL);
	}
}
