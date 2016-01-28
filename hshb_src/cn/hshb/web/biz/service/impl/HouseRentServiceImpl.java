package cn.hshb.web.biz.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
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
import cn.hshb.web.biz.mybatis.dao.CommonCountyMapper;
import cn.hshb.web.biz.mybatis.dao.CommonDecorationTypeMapper;
import cn.hshb.web.biz.mybatis.dao.CommonUsageMapper;
import cn.hshb.web.biz.mybatis.dao.HouseAppraiseMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityHospitalMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySchoolMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunitySubwayStationMappingMapper;
import cn.hshb.web.biz.mybatis.dao.HouseDefaultSortRuleMapper;
import cn.hshb.web.biz.mybatis.dao.HouseRentHouseMapper;
import cn.hshb.web.biz.mybatis.dao.SearchItemMapper;
import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCbdWebsiteExample;
import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonCountyTwo;
import cn.hshb.web.biz.mybatis.model.CommonDecorationType;
import cn.hshb.web.biz.mybatis.model.CommonDecorationTypeKey;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.CommonOrientations;
import cn.hshb.web.biz.mybatis.model.CommonRentType;
import cn.hshb.web.biz.mybatis.model.CommonSubway;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.CommonUsage;
import cn.hshb.web.biz.mybatis.model.CommonUsageKey;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HouseAppraiseExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExample;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.SearchField;
import cn.hshb.web.biz.mybatis.model.SearchItem;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.service.CommonCountyTwoService;
import cn.hshb.web.biz.service.CommonLiveFacilityService;
import cn.hshb.web.biz.service.CommonSubwayStationService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.biz.util.HouseRecommendUtil;
import cn.hshb.web.biz.util.HouseTagUtil;
import cn.hshb.web.house.enums.EnumHouseFurniture;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.partner.baidu.common.StringUtil;

@Service
public class HouseRentServiceImpl extends AnalyticalQueryFieldImpl implements HouseRentService{
	private static final Log log = LogFactory.getLog(HouseRentServiceImpl.class);
	public static final Integer PAGE_SIZE = 20; //默认每页房源数量
//	private final String[] shi = {"未知","一","二","三","四","五","六","七","八","九"};
	@Autowired
	HouseRentHouseMapper houseRentHouseMapper;
	@Autowired
	HouseCommunityService houseCommunityService;
	@Autowired
	private HouseAppraiseMapper houseAppraiseMapper;
	@Autowired
	SearchItemService searchItemService;
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private HousePictureService housePictureService;
	@Autowired
	private HouseCommunityHospitalMappingMapper houseCommunityHospitalMappingMapper;
	@Autowired
	private HouseCommunitySchoolMappingMapper houseCommunitySchoolMappingMapper;
	@Autowired
	private HouseCommunityStationMappingMapper houseCommunityStationMappingMapper;
	@Autowired
	private HouseCommunitySubwayStationMappingMapper houseCommunitySubwayStationMappingMapper;
	@Autowired
	private CommonCountyTwoService commonCountyTwoService;
	@Autowired
	private CommonCountyMapper commonCountyMapper;
	@Autowired
	private CommonCountyService commonCountyService;
	@Autowired
	private HouseDefaultSortRuleMapper houseDefaultSortRultMapper;
	@Autowired
	private CommonUsageMapper commonUsageMapper;
	@Autowired
	private CommonDecorationTypeMapper commonDecorationTypeMapper;
	@Autowired
	private BBrokerService bBrokerService;
	@Autowired
	private CommonCbdWebsiteMapper commonCbdWebsiteMapper;
	@Autowired
	private CommonLiveFacilityService commonLiveFacilityService;
	@Autowired
	private CommonSubwayStationService commonSubwayStationService;
	@Override
	public List<HouseRentHouseExt> getHouseList(Map<String, String> conditions,
			Map<String, String> orderFields, Integer pageNum) {
		return getHouseList(conditions, orderFields, pageNum, PAGE_SIZE);
	}
	@Override
	public List<HouseRentHouseExt> getHouseList(Map<String, String> conditions,
			Map<String, String> orderFields, Integer pageNum, Integer pageSize) {
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
			}else if(key.equals("countryTwoId")){
				//处理 城东租房等搜索条件
				cond.put("cbdWebsiteIds", commonCountyTwoService.getCommonCbdWebsiteIdList(value));
			}else{
				cond.put(key, new String[] {value});
			}
		}
		
		StringBuilder strOrderBy = new StringBuilder("");
		if(orderFields.size() == 0)	strOrderBy = getOrderField(strOrderBy);
		for(String key : orderFields.keySet()){
			if("sortIndex".equals(key)){
				strOrderBy = getOrderField(strOrderBy);
				continue;
			}
			String value = orderFields.get(key);
			if(StringUtils.isEmpty(value)) value = "ASC";
			strOrderBy.append(", ").append(key).append(" ").append(value);
		}
		if(strOrderBy.length() > 0 ){
			strOrderBy.deleteCharAt(0);
			cond.put("orderby", new String[]{strOrderBy.toString()});
		}
		
		Integer page = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(page, pageSize);
		
		//紧跟着的第一个select方法会被分页
		List<HouseRentHouseExt> houseList = houseRentHouseMapper.selectAssociateByMap(cond);
		
//		housePictureService.getCorrespondingRHPictures(houseList);
		housePictureService.handleHouseCoverImagebyList(houseList);
		//租赁配置  
//		for(HouseRentHouse h : houseList){
//			String furniture = h.getFurniture();
//			if(StringUtil.isNotEmpty(furniture)){
//				furniture = furniture.replaceAll(",,", ",");
//				String[] furnitureIds = furniture.split(",");
//				List<String> list = new ArrayList<String>();
//				for(String s : furnitureIds)
//					list.add(s);
//				h.setFurnitureIds(list);
//			}
//		}		
		
//		for(HouseRentHouse hr : houseList){
////			HouseCommunity community = hr.getCommunity();
//			//补全小区 信息 如商圈
//			houseCommunityService.populateCommunity(hr.getCommunity());
//		}
		
//		for(HouseRentHouse h : houseList){
//			String tagId = h.getTags();
//			if(StringUtil.isNotEmpty(tagId)){
//				String[] ids = tagId.split(",");
//				List<String> list = new ArrayList<String>();
//				for(String s : ids)
//					list.add(s);
//				h.setTagIds(list);
//			}
//		}
		
		return houseList;
	}
	/**
	 * 根据房源编号查询房源 
	 */
	@Override
	public HouseRentHouse loadHouseByHouseId(String houseId) {
		HouseRentHouseExample example = new HouseRentHouseExample();
		example.createCriteria()
			.andHouseIdEqualTo(houseId)
			.andDeleteflagEqualTo(0);
		List<HouseRentHouse> houseList = houseRentHouseMapper.selectAssociateByExample(example);
		
		if(houseList != null && houseList.size() > 0){
			//给查询到的房源载入图片
			housePictureService.loadPictureForHouse(houseList);
			
			//给房源查询小区信息
			houseCommunityService.loadCommunityForHouse(houseList);
			return arrangementPicture(houseList.get(0));
		}
		
		return null;
	}
	
	/**
	 * 根据房源编号查找房源
	 * @param houseNo
	 * @return
	 */
	@Override
	public HouseRentHouse findHouseByHouseId(String houseNo) {
		HouseRentHouse house = loadHouseByHouseId(houseNo);
		if(house!=null)
			loadCommonCounty(house);
		return house;
	}
	
	/**
	 * 为租赁加载小区
	 * @param hr
	 * @return
	 */
	private HouseRentHouse loadCommonCounty(HouseRentHouse hr){
		if(hr != null && hr.getCommunity()!=null){
			String countyId = hr.getCommunity().getRegionId();
			if(StringUtil.isNotEmpty(countyId))
				hr.getCommunity().setCounty(commonCountyMapper.selectCountyIdbyCounty(countyId));
		}
			
		return hr;
	}
	/**
	 * 根据房源Id和经纪人查询经纪人的房源评价
	 */
	@Override
	public HouseAppraise findHouseAppraiseByBroker(HouseRentHouse house,
			String brokerId) {
		String _brokerId = brokerId == null ? house.getPublisherId() : brokerId;
		
		HouseAppraiseExample example = new HouseAppraiseExample();
		example.createCriteria()
			.andHouseIdEqualTo(house.getHouseId())
			.andBrokerEqualTo(_brokerId)
			.andDeleteflagEqualTo(0);
			
		HouseAppraise houseAppraise = null;
		List<HouseAppraise> list = houseAppraiseMapper.selectByExample(example);
		if(list == null || list.size() < 1){
			houseAppraise = new HouseAppraise();
			houseAppraise.setBroker(_brokerId);
			houseAppraise.setTitle(house.getTitle());
		}
		
		return houseAppraise;
	}
	
	/**
	 * 把查询条件放入list中，用于显示该用户已查询哪些条件
	 */
	@Override
	public List<SearchField> getSearchFieldList(Map<String, String> condition, String url, String searchFields){
		List<SearchField> list = new ArrayList<SearchField>();
		for(String key : condition.keySet()){
			String[] str = searchFieldByClassify(key, condition.get(key), "3", searchFields);
			if(str != null){
				try{
				SearchField field = new SearchField();
				////查询字段的名称getFieldname
				String fieldName = str[0];
				if(!"".equals(fieldName) && !"".equals(str[2]) && !fieldName.contains(str[2]))
					fieldName += str[2];
				field.setFieldname(fieldName);
				//把需要删除的条件，在查询条件中替换为空 getReplaceBySearchFields
				field.setMinfieldvalue(url + str[1] );
				list.add(field);
				}catch(Exception ex){
					log.error("在租赁接口,分析'本次找房条件'失败,该查询条件为:" + searchFields + ", 分析到" + str[0] + "失败");
				}
			}
		}
		return list;
	}
	
	/**
	 * 载入推荐到首页的房源
	 * @param count	要载入的记录数
	 * @return
	 */
	@Override
	public List<HouseRentHouseExt> loadRecommandedHouse(Integer rows) {
//		HouseRentHouseExample example = new HouseRentHouseExample();
//		example.createCriteria().andDeleteflagEqualTo(0);
//		example.setOrderByClause(" homepage_recommand_flag desc, homepage_recommand_time desc");
		
		if(rows!=null && rows>0)
			PageHelper.startPage(1, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderby", new String[]{" homepage_recommand_flag desc, homepage_recommand_time desc"});
		List<HouseRentHouseExt> houseList = houseRentHouseMapper.selectAssociateByMap(map);
		
		for(HouseRentHouseExt h: houseList){
			dealHouseFurnitureIds(h);
		}
		
		//给查询到的房源载入图片
//		housePictureService.loadPictureForHouse(houseList);
		housePictureService.getCorrespondingRHPictures(houseList);
		return houseList;
	}
	
	/**
	 * 处理房源标签代码，拆分成列表放入房源对象
	 * @param house
	 */
	private void dealHouseFurnitureIds(HouseRentHouseExt house){
    	String furs = house.getFurniture();
    	if(StringUtil.isEmpty(furs)) return;
		String[] ids = furs.split(",");
		List<String> lst = new ArrayList<String>();
		for(String s: ids)
			lst.add(s);
		house.setTagIds(lst);
		house.setFurnitureIds(lst);
	}


	/**
	 * 计算并查询推荐房源
	 */
	@Override
	public List<HouseRentHouseExt> findHouseRentRecommend(HouseRentHouse house,
			HouseRecommendUtil recommandUtil) {
		if(recommandUtil != null){
			String price = recommandUtil.putPriceToQueue(house.getRentPrice());
			String area = recommandUtil.putAreaToQueue(house.getArea());
			String shi = recommandUtil.putShiToQueue(house.getShi());
			List<HouseRentHouseExt> recommandHouseList = getHouseByConditionRange(price, area, shi, house.getErpId(), 4);
			//封面处理,因为有些是没有封面图片的
			housePictureService.handleHouseCoverImagebyList(recommandHouseList);
			return recommandHouseList;
		}
		return null;
	}
	
	/**
	 * 根据查询条件范围查询房源记录,主要用于查询推荐房源
	 * @param priceRange	价格范围
	 * @param areaRange		面积范围
	 * @param shiRange		室范围
	 * @param houseId		房源Id
	 * @param maxRows		获取房源的数量
	 * @return
	 */
	public List<HouseRentHouseExt> getHouseByConditionRange(String priceRange, String areaRange, String shiRange, String houseId, int maxRows){
		String[] priceRanges = priceRange.split(",");
//		Float priceMin = Float.valueOf(priceRanges[0]);
//		Float priceMax = Float.valueOf(priceRanges[1]);
		String[] areaRanges = areaRange.split(",");
//		Float areaMin = Float.valueOf(areaRanges[0]);
//		Float areaMax = Float.valueOf(areaRanges[1]);
//		Integer shiInt = Integer.parseInt(shiRange);
//		HouseRentHouseExample example = new HouseRentHouseExample();
//		example.createCriteria()
//			.andRentPriceBetween(priceMin, priceMax)
//			.andAreaBetween(areaMin, areaMax)
//			.andShiEqualTo(shiInt)
//			.andErpIdNotEqualTo(houseId)
//			.andDeleteflagEqualTo(0);
//		if(maxRows > 0)
			PageHelper.startPage(1,maxRows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rent_price", priceRanges);
		map.put("area", areaRanges);
		map.put("shi", new String[]{shiRange});
		map.put("notEqualHouseId", new String[]{houseId});
		List<HouseRentHouseExt> houseList = houseRentHouseMapper.selectAssociateByMap(map);
		return houseList;
	}
	
	/**
	 * 整理图片 ,把户型图放在图片集的第一张,并判断封面字段是否存在
	 * @param house
	 * @return
	 */
	private HouseRentHouse arrangementPicture(HouseRentHouse house){
		List<HousePicture> hpList = house.getPictures();
		if(hpList.size() > 0){
			for(int i = 0 ; i < hpList.size(); i++){
				HousePicture hp = hpList.get(i);
				if("户型图".equals(hp.getPictureName())){
					HousePicture replaceHp = hpList.get(0);
					hpList.set(0, hp);
					hpList.set(i, replaceHp);
				}
			}
		}
		//
		housePictureService.handleHouseCoverImage(house);
		return house;
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
	public HouseRentHouse parseHouseRentHouseXmlToObject(String strXml) {
		HouseRentHouse house = new HouseRentHouse();
		SAXReader reader = new SAXReader();
		try{
			Document doc = reader.read(new StringReader(strXml));
			String communityId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/SubdistrictID", "");
			String houseId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseID", "");
			String houseNo = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseNo", "");
			String houseTitle = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Title", "");
			String releaserID = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/ReleaserID", "");
			String price = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/RentPrice", "0");
			String area = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Area", "0");
			String rentMode = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/RentMode", "");
			String paymentType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PaymentType", "");
			String visitTime = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimes", "0");
			String visitTimeRange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimeRange", "");
			String houseType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseType", "");
			String floor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Floor", "0");
			String totalFloor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/TotalFloor", "0");
			String toward = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Toward", "");
			String decorating = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Decorating", "");
			String propertyType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PropertyType", "");
			String commentTitle = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentTitle", "");
			String commentContent = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentContent", "");
			String priceChange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PriceChange", "");
			String tags = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Tag", "");
			String facilities = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Facilities", "");
			
			List<CommonLiveFacility> list = new ArrayList<CommonLiveFacility>();
			if(StringUtil.isNotBlank(facilities)){
				String[] furArr = facilities.split("\\|");
				StringBuffer sr = new StringBuffer("");  
				//给出所有设备
				List<CommonLiveFacility> allTagList = commonLiveFacilityService.getHouseFurnitures();
				//传过来的是设备名称
				for(String furName : furArr){
					for(CommonLiveFacility live : allTagList){
						if(furName.equals(live.getClfName()))
							list.add(live);
					}
				}
			}
			house.setHouseFurnitures(list);
//			house.setFurnitureIds(furIdList);
			
			String[] tagArr = tags.split("\\|");
			List<String> tagIdList = new ArrayList<String>();
			for(String tagId: tagArr){
//				tagIdList
			}
			house.setTagIds(tagIdList);
			
			List<Node> picNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/PictureList/PictureItem");
//			List<ERPHousePicture> housePicList = new ArrayList<ERPHousePicture>();
			List<HousePicture> housePicList = new ArrayList<HousePicture>();
			for(Node node: picNodeList){
				HousePicture housePic = new HousePicture();
				String layoutFlag = node.selectSingleNode("LayoutFlag").getText();
				layoutFlag = StringUtil.isBlank(layoutFlag)?"0":layoutFlag;
				housePic.setLayoutflag(StringUtil.parseInt(layoutFlag, 0));
				
				String orderNum = node.selectSingleNode("OrderNum").getText();
				orderNum = StringUtil.isBlank(orderNum)?"0":orderNum;
				housePic.setSortindex(StringUtil.parseInt(orderNum, 0));
				housePic.setPictureFormat(node.selectSingleNode("PictureFormat").getText());
				
				String picData = node.selectSingleNode("PictureData").getText();
				String imgPath = ERPPictureUtil.GenerateImage(picData, EnumHouseType.RENT, housePic.getPictureFormat());
				housePic.setPictureUri(imgPath);
				
				housePicList.add(housePic);
			}
			
			List<Evaluation> evaList = new ArrayList<Evaluation>();
			List<Node> evaNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/EvaluationList/EvaluationItem");
			for(Node node : evaNodeList){
				Evaluation eva = new Evaluation();
				// 获取经纪人信息,这块属于经纪人评价
				BBroker broker = bBrokerService.getBroker(node.selectSingleNode("ValuatorID").getText());
				eva.setbBroker(broker);
				eva.setTitle(node.selectSingleNode("Title").getText());
				eva.setContent(node.selectSingleNode("Content").getText());
				evaList.add(eva);
			}
			
			BBroker publisher = new BBroker();
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
			publisher.setPhotographPath(ERPPictureUtil.GenerateImage(publisherTouXiangUrl, EnumHouseType.RENT, "jpg"));
			
			//根据小区Id获取,获取小区信息
			house.setCommunity(houseCommunityService.findCommunityByCommunityId(communityId));
			house.setCommunityId(communityId);
			house.setErpId(houseId);
			house.setHouseId(houseNo);
			house.setTitle(houseTitle);
			house.setPublisher(publisher);
//			house.setPublisherId(releaserID);
			//StringUtil.parseFloat 判断有问题,只能判断不带小数点的数字,对于带有小数点的则判断不为数字 
//			house.setRentPrice( StringUtil.parseFloat(price, 0f));
//			house.setArea(StringUtil.parseFloat(area, 0f));
			house.setRentPrice( getStringByNumberFloat(price));
			house.setArea(getStringByNumberFloat(area));
			house.setPreviousRentPrice(getStringByNumberFloat(price));
			
			//出租类别
			CommonRentType rentType = new CommonRentType();
			rentType.setRentTypeName(rentMode);
			house.setRentType(rentType);
			if("整租".equals(rentMode))
				house.setRentTypeId(1);
			else 
				house.setRentTypeId(2);
			
			house.setPayforWay(paymentType);
			house.setTimeToSee(visitTimeRange);
			
			Apartment apartment = HouseOrientationUtil.getInstance().splitApartment(houseType);
			house.setShi(apartment.getShi());
			house.setTing(apartment.getTing());
			house.setWei(apartment.getWei());
			house.setYangtai(apartment.getBalcony());
			//floor:当前层数  totalFloor 总的层数
			house.setStoreyCount(StringUtil.parseInt(totalFloor, 1));
			house.setHouseVervicalLocation(getFloorPosition(StringUtil.parseInt(floor, 1),StringUtil.parseInt(totalFloor, 1)));
			//朝向
			CommonOrientations orientations = new CommonOrientations();
			orientations.setOrientationName(toward);
			house.setOrientation(orientations);
			
			//装修程度名称 
			CommonDecorationType decorations = selectDecorationTypeById(String.valueOf(HouseOrientationUtil.getInstance().convertDecoration(decorating)));
			house.setDecoration(decorations);
			house.setDaikan(StringUtil.parseInt(visitTime, 0));
			house.setProperty(propertyType);
			//房源用途名称
			CommonUsage usage = selectUsageById(String.valueOf(HouseOrientationUtil.getInstance().convertUsage(propertyType)));
			house.setUsage(usage);
//			house.setTitle(commentTitle);
			house.setContent(commentContent);
			house.setPictures(housePicList);

			house.setEvaluations(evaList);
			return house;
		}catch(Exception ex){
			log.error("分析失败ERPXML-------------");
			log.error("ERPXML内容为:");
			log.error(strXml);
			log.error("完毕",ex);
		}
		return null;
	}
	private String getNodeAsString(Document doc, String xmlPath, String defaultVal){
		String val = doc.selectSingleNode(xmlPath).getText();
		val = StringUtil.isBlank(val)?defaultVal:val;
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
	 * String 转float
	 * @param str
	 * @return
	 */
	private float getStringByNumberFloat(String str){
		if(StringUtil.isEmpty(str))
			return 0f;
		Matcher m = Pattern.compile("([\\d]+(.[\\d]+)?)").matcher(str);
		while(m.find()){
			return Float.parseFloat(m.group());
		}
		return 0f;
	}
	private  int getFloorPosition(int floor, int countFloor){
		double branch1 = countFloor / 3.0;
		if(floor <= branch1)
			return 1;
		else if(floor <= branch1*2)
			return 2;
		else 
			return 3;
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
	 * 解析房源中的房源标签
	 * @param tagStr 房源标签字符串，可能是逗号分隔的标签ID字符串，也可能是整型的按位存储的标签组合整型值
	 * @return
	 */
	@Override
	public List<CommonLiveFacility> parseHouseFurnitures(String furnitureStr){
		List<CommonLiveFacility> list = new ArrayList<CommonLiveFacility>();
		if(StringUtil.isNumeric(furnitureStr)){
			Integer furVal = StringUtil.parseInt(furnitureStr, 0);
			List<EnumHouseFurniture> furList = HouseTagUtil.getFurnitureByValue(furVal);
			for(EnumHouseFurniture t: furList){
				CommonLiveFacility fur = new CommonLiveFacility();
				fur.setErpId(String.valueOf(t.getValue()));
				fur.setClfName(t.getTitle());
				fur.setIconUrl(t.getIconUri());
				fur.setDisableIconUrl(t.getDisableIconUri());
				list.add(fur);
			}
		}else{
			List<CommonLiveFacility> allTagList = commonLiveFacilityService.getHouseFurnitures();
			String[] tagIds = furnitureStr.split(",");
			for(String s: tagIds){
				for(CommonLiveFacility t: allTagList){
					if(t.getErpId().equalsIgnoreCase(s)){
						list.add(t);
						break;
					}
				}
			}
		}
		return list;
	}
}
