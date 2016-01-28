package com.huatek.hbwebsite.community.show;

import cn.hshb.web.house.enums.EnumHouseType;

import com.google.zxing.WriterException;
import com.huatek.ddhb.manage.activityManage.entity.ADAndActivityBar;
import com.huatek.ddhb.manage.activityManage.service.ActivityManageService;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.MemberBrowseHistoryAnalyser;
import com.huatek.framework.util.QueryCondition;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.CommunityAveragePrice;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.house.entity.HouseCommunityAveragePrice;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.MemberBrowseHistory;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.MemberBrowseHistoryService;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.saveQuery.service.SaveQueryConditionsService;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.search.service.SharedSearchService;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.TwoDimensionMaker;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/community.show" })
public class CommunityAction {
	private static final Log log = LogFactory.getLog(CommunityAction.class);
	
	@Autowired
	private CommunityService communityService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private HouseSecondService houseSecondService;
	@Autowired
	private RentService rentService;
	@Autowired
	private PlatCollectionService platCollectionService;
	@Autowired
	MemberBrowseHistoryService memberBrowseHistoryService;
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	SharedSearchService sharedSearchService;
	@Autowired
	SaveQueryConditionsService saveQueryConditionsService;
	
	private static final String COMMUNITY_DETAIL_SHOW = "community.detail.show";
	private static final String COMMUNITY_COMPARE = "community.compare";
	private static final int BUFSIZE_10K = 10240;
//	private static final Logger LOGGER = Logger.getLogger(CommunityAction.class);
	
	//<meta name="keywords" content="KEYWORDS" /> 关键字    搜索引擎预期查找
	private static final String KEYWORDS ="小区,杭州小区,小区信息";			
	//<meta name="description" content="DESCRIPTION" /> 网页内容 不超过
	private static final String DESCRIPTION = "豪世华邦联动百家社区直营店，不断为消费者开拓“家服务“领域的增值服务项"; 		
	//<title>TITLECONTENT</title> 标题 	
	private static final String TITLECONTENT = "杭州小区";	

	@RequestMapping(params = { "actionMethod=communityCompare" })
	public String communityCompare(@RequestParam("houseNos") String communityNos, Model model) {
		model.addAttribute("backType", Integer.valueOf(4));
		List<Community> communityList = this.communityService.findCommunityListByCommunityNos(communityNos);
		if (communityList != null && communityList.size() > 0) {
			String[] ids = new String[communityList.size()];

			for (int ii = 0; ii < communityList.size(); ++ii) {
				ids[ii] = ((Community) communityList.get(ii)).getErpId();
			}

			List<HousePic> picList = this.communityService.getCommunityPicByIdsAndPicType(ids, 3, 1, 0);
			Iterator<Community> itCommunity = communityList.iterator();

			while (itCommunity.hasNext()) {
				Community community = itCommunity.next();
				Iterator<HousePic> itPic = picList.iterator();

				while (itPic.hasNext()) {
					HousePic housePic = itPic.next();
					if (housePic.getHouseId().equals(community.getErpId())) {
						community.setCommunityUrl(housePic.getPicUrl());
						break;
					}
				}
			}

			model.addAttribute("communityList", communityList);
			return "community.compare";
		} else {
			return "redirect:community.show?actionMethod=showList";
		}
	}

	@RequestMapping(params = { "actionMethod=communityDetail" })
	public String showCommunityDetail(@RequestParam("id") String id, Model model, HttpServletRequest request) {
		model.addAttribute("backType", Integer.valueOf(4));
		model.addAttribute("showCompare", "C");
		Community community = (Community) this.communityService.getObjectById(Community.class, id);
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (platMemberInfo != null) {
			List<MemberBrowseHistory> bhList = this.memberBrowseHistoryService.findMemberBrowseHistoryByMember(platMemberInfo.getId(), 2, community.getErpId());
			if (bhList.size() > 0) {
				((MemberBrowseHistory) bhList.get(0)).setModifiedTime(new Date());
				this.memberBrowseHistoryService.update(bhList.get(0));
			} else {
				MemberBrowseHistory housePics = new MemberBrowseHistory();
				housePics.setPlatMemberInfo(platMemberInfo);
				housePics.setObjectId(community.getErpId());
				housePics.setObjectType(2);
				housePics.setCreateTime(new Date());
				housePics.setModifiedTime(new Date());
				this.memberBrowseHistoryService.save(housePics);
			}
		}
		//载入最近门店列表
		//Added by syf at 2015.02.07
		communityService.loadNearestStore(community);
		
		model.addAttribute("community", community);
		model.addAttribute("aroundLastYearRent", Double.valueOf(community.getAroundLastYearrh()));
		model.addAttribute("aroundLastMonthRent", Double.valueOf(community.getCompareWithLastMonthrh()));
		String[] ids = new String[] { community.getErpId() };
		model.addAttribute("houseHeadPics", this.houseSecondService.getHousePicByIdsAndPicType(ids, 3, 1, 0));
		model.addAttribute("communityPics", this.houseSecondService.getHousePicByIdsAndPicType(ids, 3, 2, 0));
		List<HousePic> picList = this.houseSecondService.getHousePicByIdsAndPicType(ids, 3, 1, 1);
		if (picList != null && picList.size() > 0) {
			HousePic cutPageBean = picList.get(0);
			community.setCommunityUrl(cutPageBean.getPicUrl());
		}

		CutPageBean var33 = QueryCondition.getQueryPageBean(request);
		model.addAttribute("expertList", this.communityService.getBrokerList(var33, id));
		List<HouseCommunityAveragePrice> trendList = this.communityService.getPriceTrendForSixMonth(community.getErpId());
		model.addAttribute("trendList", trendList);
		List<CommunityStationMapping> communityStationMappings = this.communityService.getCommunityStationsById(community.getErpId());
		if (communityStationMappings.size() > 0) {
			model.addAttribute("communityStationMappings", communityStationMappings);
		}

		List<CommunitySubwayStationMapping> cSubMapping = this.houseSecondService.findCSubMapping(community.getErpId());
		if (cSubMapping.size() > 0) {
			model.addAttribute("cSubMapping", cSubMapping);
		}

//		float lastMonthRent = 0.0F;
//		float thisMonthRent = 0.0F;
		CommunityAveragePrice communityAveragePrice = communityService.getCommunityAveragePriceLastMonth(community.getErpId());
		CommunityAveragePrice commAvgPriceThisMonthYears = communityService.getCommunityAveragePriceThisMonth(community.getErpId());
		if (communityAveragePrice != null) {
			model.addAttribute("lastMonthPriceSecond", communityAveragePrice.getPriceAnnounced());
			model.addAttribute("lastMonthPriceRent", communityAveragePrice.getRentPrice());
			model.addAttribute("secondHousePercent",
					(community.getCurrentSHAvePrice() - communityAveragePrice.getPriceAnnounced()) / communityAveragePrice.getPriceAnnounced() * 100.0F);
			model.addAttribute("rentHousePercent",
					(community.getCurrentRHAvePrice() - communityAveragePrice.getRentPrice()) / communityAveragePrice.getPriceAnnounced() * 100.0F);
//			lastMonthRent = communityAveragePrice.getRentPrice();
//			if (commAvgPriceThisMonthYears != null) {
//				thisMonthRent = commAvgPriceThisMonthYears.getRentPrice();
//			}
		} else if (commAvgPriceThisMonthYears != null) {
			model.addAttribute("lastMonthPriceSecond", commAvgPriceThisMonthYears.getPriceAnnounced());
			model.addAttribute("lastMonthPriceRent", commAvgPriceThisMonthYears.getRentPrice());
			model.addAttribute("secondHousePercent",
					(community.getCurrentSHAvePrice() - commAvgPriceThisMonthYears.getPriceAnnounced()) / commAvgPriceThisMonthYears.getPriceAnnounced() * 100.0F);
			model.addAttribute("rentHousePercent",
					(community.getCurrentRHAvePrice() - commAvgPriceThisMonthYears.getRentPrice()) / commAvgPriceThisMonthYears.getPriceAnnounced() * 100.0F);
		} else {
			model.addAttribute("lastMonthPriceSecond", "0");
			model.addAttribute("lastMonthPriceRent", "0");
			model.addAttribute("secondHousePercent", "0");
			model.addAttribute("rentHousePercent", "0");
		}

//		float lastYearRent = 0.0F;
//		float thisYearRent = 0.0F;
		CommunityAveragePrice commAvgPricesLastYear = communityService.getCommunityAveragePriceLastYear(community.getErpId());
		CommunityAveragePrice commAvgPriceThisMonth = this.communityService.getCommunityAveragePriceThisMonth(community.getErpId());
		if (commAvgPricesLastYear != null) {
			model.addAttribute("lastYearPriceSecond", commAvgPricesLastYear.getPriceAnnounced());
			model.addAttribute("lastYearPriceRent", commAvgPricesLastYear.getRentPrice());
//			lastYearRent = commAvgPricesLastYear.getRentPrice();
//			thisYearRent = commAvgPriceThisMonth.getRentPrice();
		} else if (commAvgPriceThisMonth != null) {
			model.addAttribute("lastYearPriceSecond", commAvgPriceThisMonth.getPriceAnnounced());
			model.addAttribute("lastYearPriceRent", commAvgPriceThisMonth.getRentPrice());
		} else {
			model.addAttribute("lastYearPriceSecond", "0");
			model.addAttribute("lastYearPriceRent", "0");
		}

		MemberBrowseHistoryAnalyser analyser = (MemberBrowseHistoryAnalyser) request.getSession().getAttribute("browsedHistory");
		String clientFlag;
		if (analyser != null && community.getCbd() != null) {
			clientFlag = analyser.putCbdToQueue(community.getCbd().getErpId());
			List<Community> recommCommList = this.communityService.getRecommandedCommunity(clientFlag);
			String[] recommCommIds = new String[recommCommList.size()];
			if (recommCommList.size() > 0) {
				for (int ii = 0; ii < recommCommList.size(); ++ii) {
					recommCommIds[ii] = recommCommList.get(ii).getErpId();
				}
			}

			CutPageBean cPageBean = new CutPageBean();
			this.judgecommunity(request, recommCommList);
			cPageBean.setDataList(recommCommList);
			CutPageBean pageBean1 = this.addPicUrl(cPageBean);
			List<HousePic> commPicList = this.houseSecondService.getHousePicByIdsAndPicType(recommCommIds, 1, 0, 0);
			Iterator<Community> itComm = recommCommList.iterator();

			while (itComm.hasNext()) {
				Community comm = itComm.next();
				Iterator<HousePic> itPic = commPicList.iterator();

				while (itPic.hasNext()) {
					HousePic pic = (HousePic) itPic.next();
					if (comm.getErpId().trim().equals(pic.getHouseId().trim())) {
						comm.setPictureUrl(pic.getPicUrl());
						break;
					}
				}
			}

			model.addAttribute("showCompare", "C");
			model.addAttribute("recommandedCommunity", pageBean1.getDataList());
		}

		clientFlag = ClientFlagUtil.getClientFlag(request);
		String compareStr = UserCompareUtil.getInstance().getCompareString(clientFlag, "communityCompare");
		if (compareStr == null) {
			compareStr = "";
		}

		model.addAttribute("communityCompare", compareStr);
		String hisStr = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "communityHistory");
		if (hisStr == null) {
			hisStr = "";
		}

		model.addAttribute("communityHistory", hisStr);

		//Add by 何剑波  20150430
		//功能：在<head>标签添加<meta>的值
		//title内容小区站点地址
		model.addAttribute("titleContent","杭州小区-" + community.getCommunityName());
		model.addAttribute("keywords",null);
		model.addAttribute("description","杭州豪世华邦为您提供最新杭州"+ community.getRegion().getCountyName() +"小区信息，找小区不再难");
		
		return "community.detail.show";
	}

	@RequestMapping(params = { "actionMethod=brokerPage" })
	public String showCommunityBroker(@RequestParam("communityId") String communityId, Model model, HttpServletRequest request) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(5);
		CutPageBean pageBean = this.communityService.getBrokerList(cutPageBean, communityId);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("communityId", communityId);
		return "brokers_div";
	}

	@RequestMapping(params = { "actionMethod=communityhouse" })
	public String showCommunitySecondHouse(@RequestParam("communityId") String communityId, Model model, HttpServletRequest request) {
		String houseTypeStr = request.getParameter("housetype");
//		boolean housetype = true;

		int houseType;
		try {
			houseType = Integer.parseInt(houseTypeStr);
		} catch (Exception ex) {
			//houseType = 1;
			houseType = EnumHouseType.SALE.value();
			log.error(ex.getMessage());
		}

		String ispage = request.getParameter("ispage");
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(10);
		CutPageBean pageBean;
		if (houseType == EnumHouseType.SALE.value()) {
			pageBean = this.communityService.getSecondByCommuId(cutPageBean, new ArrayList<CommonBean>(), communityId);
			model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("5"));
			if (pageBean.getDataList() != null) {
				Iterator<HouseSecond> itHouse = ((List<HouseSecond>)pageBean.getDataList()).iterator();
				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
					String tagStr = house.getShowTags();
					String[] tags = tagStr.split(",");
					List<String> tagIdList = new ArrayList<String>();
					for(String id: tags)
						tagIdList.add(id);

					house.setTagIdList(tagIdList);
					
					//解析房源标签
					house.setHouseTags(houseSecondService.parseHouseTag(house.getTags()));
				}
			}

			model.addAttribute("housePics", this.addSecondPicUrl(pageBean));
			this.judgesecond(request, (List<HouseSecond>) pageBean.getDataList());
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
			model.addAttribute("secondHouseCompare", comparedSHString == null? "" : comparedSHString);

			String hisStr = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
			model.addAttribute("secondHouseHistory", hisStr == null ? "" : hisStr);
		} else {
			pageBean = this.communityService.getRentByCommuId(cutPageBean, new ArrayList<CommonBean>(), communityId);
			model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("6"));
			if (pageBean.getDataList() != null) {
				Iterator<HouseRent> itHouse = ((List<HouseRent>)pageBean.getDataList()).iterator();
				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					String[] furIds = house.getShowFunitures().split(",");
					List<String> furIdList = new ArrayList<String>();
					for(String furId: furIds)
						furIdList.add(furId);
					house.setFurIdList(furIdList);
				}
			}

			model.addAttribute("furList", rentService.getFurList());
			model.addAttribute("housePics", this.addRentPicUrl(pageBean));
			this.judgerent(request, (List<HouseRent>) pageBean.getDataList());
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedRHStr = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
			model.addAttribute("rentHouseCompare", comparedRHStr == null ? "" : comparedRHStr);
			
			String hisStr = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "rentHouseHistory");
			model.addAttribute("rentHouseHistory", hisStr == null? "" : hisStr);
		}

		model.addAttribute("comId", communityId);
		model.addAttribute("housetype", houseType);
		model.addAttribute("pageBean", pageBean);
		
		String forward = "";
		if(houseType == EnumHouseType.SALE.value())
			forward = "1".equals(ispage) ? "community.houseSecond.search.list" : "community.second.house.show";
		else
			forward = "1".equals(ispage) ? "houseRent.search.list" : "community.rent.house.show";
		
		return forward;
	}

	private void judgerent(HttpServletRequest request, List<HouseRent> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			List<PlatCollection> pCollList = this.platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			Iterator<PlatCollection> itColl = pCollList.iterator();

			while (itColl.hasNext()) {
				PlatCollection house = itColl.next();
				collectMap.put(house.getObjectId(), house.getId());
			}

			if (houseList != null) {
				Iterator<HouseRent> itHouse = houseList.iterator();
				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					house.setCollectId(collectMap.get(house.getShelvingID()));
					house.setObjectId(house.getErpId());
				}
			}
		}

	}

	private void judgesecond(HttpServletRequest request, List<HouseSecond> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			List<PlatCollection> pCollList = this.platCollectionService.getPlatCollectionListByMemberId(accountBean.getId());
			Map<String, Long> collectMap = new HashMap<String, Long>();
			Iterator<PlatCollection> itColl = pCollList.iterator();

			while (itColl.hasNext()) {
				PlatCollection house = itColl.next();
				collectMap.put(house.getObjectId(), house.getId());
			}

			if (houseList != null) {
				Iterator<HouseSecond> itHouse = houseList.iterator();
				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
					house.setCollectId(collectMap.get(house.getShelvingID()));
					house.setObjectId(house.getErpId());
				}
			}
		}
	}

	public void judgecommunity(HttpServletRequest request, List<Community> communityList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (communityList != null) {
			if (accountBean != null) {
				Long memberId = accountBean.getId();
				List<PlatCollection> pCollList = this.platCollectionService.getPlatCollectionListByMemberId(memberId);
				Iterator<Community> itComm = communityList.iterator();
				while (itComm.hasNext()) {
					Community community = itComm.next();
					Iterator<PlatCollection> itPColl = pCollList.iterator();
					while (itPColl.hasNext()) {
						PlatCollection platCollection = itPColl.next();
						if (platCollection.getObjectId().equals(community.getErpId())) {
							community.setCollectId(Long.valueOf(1L));
						}
					}
				}
			}
		}
	}

	@RequestMapping(params = { "actionMethod=dimquery" })
	public String dimQuery(HttpServletRequest request, Model model) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		String shortValue = request.getParameter("shortValue");
		Map<String, String> searchItemMap = null;

		//用于存放查询条件
		Map<String, String> queryCondition = new HashMap<String, String>();
		
		boolean isShort = false;
		if (shortValue != null) {
			isShort = true;
			String shareUrl = this.sharedSearchService.getSharedUrl(shortValue);
			model.addAttribute("sharedUrl", shareUrl);
			searchItemMap = this.sharedSearchService.getParameterMap(shareUrl);
		}

		String type = "1";
		if (!isShort) {
			type = request.getParameter("type");
		} else if (searchItemMap != null) {
			type = (String) searchItemMap.get("type");
		}

		ADAndActivityBar topADBar = this.activityManageService.getActivityByPageAndPosition(4, 1);
		model.addAttribute("topADBar", topADBar);
		ADAndActivityBar bottomADBar = this.activityManageService.getActivityByPageAndPosition(4, 2);
		model.addAttribute("bottomADBar", bottomADBar);
		if ("1".equals(type)) {
			cutPageBean.setPageSize(FrontSystemSettingUtil.getInstance().getListTypeSize().intValue());
		} else {
			cutPageBean.setPageSize(FrontSystemSettingUtil.getInstance().getPicTypeSize().intValue());
		}

		model.addAttribute("backType", Integer.valueOf(4));
		Map oneMap = new HashMap();
		Map<String, List> twoMap = new HashMap<String, List>();
		String searchUrl = "";
		if (request.getParameter("communityNo") != null) {
			oneMap.put("communityNo", request.getParameter("communityNo"));
			queryCondition.put("communityNo", request.getParameter("communityNo"));
			searchUrl = searchUrl + "communityNo=" + request.getParameter("communityNo") + "&";
		}

		if (request.getAttribute("communityNo") != null) {
			oneMap.put("communityNo", request.getAttribute("communityNo"));
			queryCondition.put("communityNo", (String)request.getAttribute("communityNo"));
			searchUrl = searchUrl + "communityNo=" + request.getAttribute("communityNo") + "&";
		} else {
			if (request.getAttribute("inputSearch") != null) {
				oneMap.put("inputSearch", request.getAttribute("inputSearch"));
				
				queryCondition.put("inputSearch", (String)request.getAttribute("inputSearch"));
				searchUrl = searchUrl + "inputSearch=" + request.getAttribute("inputSearch") + "&";
			}
			
			Enumeration<String> enumParam = request.getParameterNames();
			if (!isShort) {
//			String ispage;
//			String orderStr;
//				for (; enumParam.hasMoreElements(); this.getSearchMap(orderStr, ispage, oneMap, twoMap)) {
//					orderStr = ((String) enumParam.nextElement()).toString();
//					ispage = request.getParameter(orderStr);
//					if (!orderStr.equals("ispage")) {
//						if (orderStr.equals("inputSearch")) {
//							if (searchUrl.indexOf("inputSearch") < 0) {
//								try {
//									String pageBean = new String(ispage.getBytes("iso-8859-1"), "UTF-8");
//									searchUrl = searchUrl + orderStr + "=" + pageBean + "&";
//									oneMap.put("inputSearch", pageBean);
//									ispage = pageBean;
//								} catch (UnsupportedEncodingException ex) {
//									LOGGER.warn(ex);
//								}
//							}
//						} else {
//							searchUrl = searchUrl + orderStr + "=" + ispage + "&";
//						}
//					}
//				}
				
				while(enumParam.hasMoreElements()){
					String paramName = enumParam.nextElement();
					String paramValue = request.getParameter(paramName);
					if (!paramName.equals("ispage")) {
						if (paramName.equals("inputSearch")) {
							if (searchUrl.indexOf("inputSearch") < 0) {
								try {
									paramValue = new String(paramValue.getBytes("iso-8859-1"), "UTF-8");
									searchUrl = searchUrl + paramName + "=" + paramValue + "&";
									oneMap.put("inputSearch", paramValue);
								} catch (UnsupportedEncodingException ex) {
									log.warn(ex);
								}
							}
						} else {
							searchUrl = searchUrl + paramName + "=" + paramValue + "&";
						}
					}
					this.getSearchMap(paramName, paramValue, oneMap, twoMap);
					queryCondition.put(paramName, paramValue);
				}
			} else if (searchItemMap != null) {
				Iterator<String> itKey = searchItemMap.keySet().iterator();
				while (itKey.hasNext()) {
					String key = itKey.next();
					this.getSearchMap(key, searchItemMap.get(key), oneMap, twoMap);
					
					queryCondition.put(key, searchItemMap.get(key));
				}
			}
		}

		if (searchUrl.lastIndexOf("&") >= 0) {
			searchUrl = searchUrl.substring(0, searchUrl.lastIndexOf("&"));
		}

		if (shortValue == null) {
			String url = "community.show?actionMethod=dimquery&" + searchUrl;
			url = url.replaceAll("&actionMethod=dimquery", "");
			model.addAttribute("sharedUrl", url);
		}

		String sortfield = null;
		String orderStr = null;
		String ispage = null;
		if (!isShort) {
			sortfield = request.getParameter("sort");
			orderStr = request.getParameter("order");
			ispage = request.getParameter("ispage");
		} else if (searchItemMap != null) {
			sortfield = (String) searchItemMap.get("sort");
			orderStr = (String) searchItemMap.get("order");
			ispage = (String) searchItemMap.get("ispage");
		}

		CutPageBean resultBean;
		if (!"1".equals(ispage) && !"0".equals(ispage)) {
			//Modify by 20140424 何剑波 : 点击小区信息如所在商圈 搜索失败
			//resultBean = this.communityService.getSearchFiledList(cutPageBean, new HashMap(), new HashMap<String, List>(), "Asc", "sortIndex");
			resultBean = this.communityService.getSearchFiledList(cutPageBean, oneMap, twoMap, "Asc","sortIndex");
		} else {
			if (sortfield == null) {
				sortfield = "sortIndex";
				orderStr = "Asc";
			}
			resultBean = this.communityService.getSearchFiledList(cutPageBean, oneMap, twoMap, orderStr, sortfield);
		}

		try{
			//存储查询的条件
			saveQueryConditionsService.saveQueryConditions(queryCondition, "community");
			}catch(Exception ex){
				log.error("存储查询的条件报错",ex);
				ex.printStackTrace();
			}
		
		this.judgecommunity(request, (List<Community>) resultBean.getDataList());
		model.addAttribute("pageBean", this.addPicUrl(resultBean));
		model.addAttribute("showCompare", "C");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		String compareStr = UserCompareUtil.getInstance().getCompareString(clientFlag, "communityCompare");
		model.addAttribute("communityCompare", compareStr == null ? "" : compareStr);

		String hisStr = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "communityHistory");
		model.addAttribute("communityHistory", hisStr == null ? "" : hisStr);

		//Add by 何剑波  20150430
		//功能：在<head>标签添加<meta>的值
		model.addAttribute("titleContent",CommunityAction.TITLECONTENT);
		model.addAttribute("keywords",CommunityAction.KEYWORDS);
		model.addAttribute("description",CommunityAction.DESCRIPTION);
		
		if ("1".equals(ispage)) {
			return "community.search.list";
		} else {
			String searchMenuStr = this.searchService.loadSearchMenuByModuleName("4");
			model.addAttribute("jsonString", searchMenuStr);
			return "community.list.show";
		}
	}

	@RequestMapping(params = { "actionMethod=getCommunityListMyName" })
	public void getCommunityListMyName(@RequestParam("communityName") String communityName, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String commJsonStr = this.communityService.getCommunityJsonStringByName(communityName);
		byte[] bufs = "[]".getBytes();

		try {
			bufs = commJsonStr.getBytes("utf-8");
		} catch (UnsupportedEncodingException ex) {
			log.error(ex.getMessage());
		}

		int cnt = 0;
		try {
			while (cnt < bufs.length) {
				response.getOutputStream().write(bufs, cnt, bufs.length - cnt >= 10240 ? 10240 : bufs.length - cnt);
				cnt += 10240;
			}
			response.getOutputStream().flush();
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
	}

	@RequestMapping(params = { "actionMethod=dimdetailquery" })
	public String dimDetailQuery(@RequestParam("communityId") String communityId,
			@RequestParam("housetype") String housetype, HttpServletRequest request, Model model) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(10);
		Map oneMap = new HashMap();
		Map<String, String> tagsMap = new HashMap<String, String>();
		Map<String, List> twoMap = new HashMap<String, List>();
		Enumeration<String> enumParamNames = request.getParameterNames();

		while (enumParamNames.hasMoreElements()) {
			String pName = enumParamNames.nextElement();
			if (pName.startsWith("ddhb_one_")) {
				String pVal = request.getParameter(pName);
				pName = pName.substring(9);
				if (!"".equals(pVal.trim())) {
					if ("tags".equals(pName)) {
						tagsMap.put(pName, pVal);
					} else if ("shi".equals(pName)) {
						oneMap.put(pName, Integer.valueOf(pVal));
					} else if (pName.endsWith("erpId")) {
						oneMap.put(pName, pVal);
					} else if (pName.equals("haslift")) {
						oneMap.put(pName, Integer.valueOf(pVal));
					} else {
						oneMap.put(pName, Long.valueOf(pVal));
					}
				}
			}

			if (pName.startsWith("ddhb_two_")) {
				String pVal = request.getParameter(pName);
				pName = pName.substring(9);
				if (!"".equals(pVal.trim())) {
					int idx = pVal.indexOf("@");
					if (pVal.indexOf("@") > 0) {
						String pVal1 = pVal.substring(0, idx);
						String pVal2 = pVal.substring(idx + 1);
						List<Float> valList = new ArrayList<Float>();
						valList.add(Float.valueOf(pVal1));
						valList.add(Float.valueOf(pVal2));
						twoMap.put(pName, valList);
					}
				}
			}
		}

		String sortfield = request.getParameter("sort");
		String orderStr = request.getParameter("order");
		if (sortfield == null) {
			sortfield = "sortIndex";
			orderStr = "Asc";
		}

		CutPageBean houseListBean = this.communityService.getSearchHouseList(cutPageBean, oneMap, twoMap, tagsMap, orderStr, sortfield, housetype, communityId);
		model.addAttribute("housetype", housetype);
		if ("1".equals(housetype)) {
			List<HouseSecond> houseList = (List<HouseSecond>) houseListBean.getDataList();
			if (houseList != null) {
				Iterator<HouseSecond> itHouse = houseList.iterator();

				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
					String[] furIdArr = house.getShowTags().split(",");
					List<String> furIdList = new ArrayList<String>();
					for (int ii = 1; ii < furIdArr.length; ++ii) {
						furIdList.add(furIdArr[ii]);
					}
					house.setTagIdList(furIdList);
					
					//解析房源标签
					house.setHouseTags(houseSecondService.parseHouseTag(house.getTags()));
				}
			}

			this.judgesecond(request, houseList);
			model.addAttribute("pageBean", this.addSecondPicUrl(houseListBean));
		} else {
			List<HouseRent> houseList = (List<HouseRent>) houseListBean.getDataList();
			if (houseList != null) {
				Iterator<HouseRent> itHouse = houseList.iterator();

				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					String[] furArr = house.getShowFunitures().split(",");
					List<String> furIdList = new ArrayList<String>();
					for (String furId : furArr)
						furIdList.add(furId);
					house.setFurIdList(furIdList);
				}
			}

			List<Furniture> furList = this.rentService.getFurList();
			model.addAttribute("furList", furList);
			this.judgerent(request, houseList);
			model.addAttribute("pageBean", this.addRentPicUrl(houseListBean));
			model.addAttribute("housePics", this.addRentPicUrl(houseListBean));
		}

		return "1".equals(housetype) ? "community.detail.search.list" : "community.detail.search.rent.list";
	}

	@RequestMapping(params = { "actionMethod=drawcode" })
	public void drawCode(@RequestParam("id") String id, @RequestParam("pictureType") String pictureType,
			HttpServletRequest request, HttpServletResponse response) throws WriterException, IOException {
		Community community = (Community) this.communityService.getObjectById(Community.class, id);
		String communityNo = community.getCommunityNo();
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://").append(request.getServerName()).append(":").append(request.getServerPort())
		.append(request.getContextPath()).append("/community.show?actionMethod=communityDetail&id=" + id);
		String format = "JPEG";
		BufferedImage image = null;
		if (pictureType.trim().equals("large")) {
			image = TwoDimensionMaker.getFinalPic(urlBuilder.toString(), communityNo, 135, 135);
		} else if (pictureType.trim().equals("mini")) {
			image = TwoDimensionMaker.getFinalPic(urlBuilder.toString(), communityNo, 100, 100);
		} else {
			image = TwoDimensionMaker.getFinalPic(urlBuilder.toString(), communityNo, 135, 135);
		}

		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, format, sos);
		sos.close();
	}

	public CutPageBean addPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];
		// new ArrayList();
		List<Community> communityList = (List<Community>) pageBean.getDataList();
		if (communityList == null) {
			return pageBean;
		} else {
			for (int ii = 0; ii < communityList.size(); ++ii) {
				ids[ii] = communityList.get(ii).getErpId();
			}

			List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(ids, 3, 2, 0);
			List<Object> commPicCountList = this.communityService.findContPicPerCommunity(ids);
			
			//原逻辑有错，不能因为没有二手房源图片就不处理小区图片
			//Modified by ShengYoufu at 2015.03.27
//			if (housePicList == null) {
//				return pageBean;
//			} else {
//				Iterator<Community> itCommunity = communityList.iterator();
//				while (itCommunity.hasNext()) {
//					Community community = itCommunity.next();
//					Iterator<HousePic> itHousePic = housePicList.iterator();
//					while (itHousePic.hasNext()) {
//						HousePic housePic = itHousePic.next();
//						if (community.getErpId().equals(housePic.getHouseId())) {
//							community.setCommunityUrl(housePic.getPicUrl());
//						}
//					}
//
//					Iterator itCommPic = commPicCountList.iterator();
//					while (itCommPic.hasNext()) {
//						Object[] objArray = (Object[]) itCommPic.next();
//						if (community.getErpId().equals(objArray[0].toString())) {
//							community.setCommunityPicCount(Integer.parseInt(objArray[1].toString()));
//						}
//					}
//				}
//				pageBean.setDataList(communityList);
//				return pageBean;
//			}
			
			Iterator<Community> itCommunity = communityList.iterator();
			while (itCommunity.hasNext()) {
				Community community = itCommunity.next();
				if(housePicList != null){
					Iterator<HousePic> itHousePic = housePicList.iterator();
					while (itHousePic.hasNext()) {
						HousePic housePic = itHousePic.next();
						if (community.getErpId().equals(housePic.getHouseId())) {
							community.setCommunityUrl(housePic.getPicUrl());
						}
					}
				}
				if(commPicCountList!=null){
					Iterator itCommPic = commPicCountList.iterator();
					while (itCommPic.hasNext()) {
						Object[] objArray = (Object[]) itCommPic.next();
						if (community.getErpId().equals(objArray[0].toString())) {
							community.setCommunityPicCount(Integer.parseInt(objArray[1].toString()));
						}
					}
				}
			}

			return pageBean;
		}
	}

	public CutPageBean addSecondPicUrl(CutPageBean pageBean) {
		List<String> idsList = new ArrayList<String>();
		List<HouseSecond> houseSecondList = (List<HouseSecond>) pageBean.getDataList();
		if (houseSecondList == null) {
			return pageBean;
		} else {
			for (int ii = 0; ii < houseSecondList.size(); ++ii) {
				idsList.add(houseSecondList.get(ii).getShelvingID());
			}

			String[] idArr = idsList.toArray(new String[idsList.size()]);
			List<HousePic> housePics = houseSecondService.getHousePicByIdsAndPicType(idArr, 1, 1, 0);
			if (housePics == null) {
				return pageBean;
			} else {
				Iterator<HouseSecond> itHouse = houseSecondList.iterator();
				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
					Iterator<HousePic> itHousePic = housePics.iterator();

					while (itHousePic.hasNext()) {
						HousePic housepic = itHousePic.next();
						if (house.getErpId().equals(housepic.getHouseId())) {
							house.setHouseUrl(housepic.getPicUrl());
							house.setPictureUrl(housepic.getPicUrl());
						}
					}
//					图片总数目
					String[] houseShelvingId = { house.getShelvingID() };
				      List<HousePic> list = this.houseSecondService.getHousePicByIdsAndPicType(houseShelvingId, 1, 2, 0);
				      if (list != null) {
				        house.setHousePicSize(Integer.valueOf(list.size()));
				      }
				}

				pageBean.setDataList(houseSecondList);
				return pageBean;
			}
		}
	}

	public CutPageBean addRentPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];
		List<HouseRent> houseRentList = (List<HouseRent>) pageBean.getDataList();
		if (houseRentList == null) {
			return pageBean;
		} else {
			for (int ii = 0; ii < houseRentList.size(); ++ii) {
				ids[ii] = houseRentList.get(ii).getShelvingID();
			}

			List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(ids, 2, 1, 0);
			if (housePicList == null) {
				return pageBean;
			} else {
				Iterator<HouseRent> itHouse = houseRentList.iterator();

				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					Iterator<HousePic> itHousePic = housePicList.iterator();
					while (itHousePic.hasNext()) {
						HousePic housepic = itHousePic.next();
						if (house.getShelvingID().trim().equals(housepic.getShelvingID().trim())) {
							house.setPictureUrl(housepic.getPicUrl());
							break;
						}
					}
					String[] houseShelvingId = { house.getShelvingID() };
				      List<HousePic> list = this.houseSecondService.getHousePicByIdsAndPicType(houseShelvingId, 2, 2, 0);
				      if (list != null) 
				    	  house.setHousePicSize(Integer.valueOf(list.size()));
				}
				pageBean.setDataList(houseRentList);
				return pageBean;
			}
		}
	}

	@RequestMapping(params = { "actionMethod=startQuery" })
	public String startQuery(HttpServletRequest request, HttpServletResponse reponse) {
		String shortValue = request.getParameter("shortValue");
		return shortValue != null ? "forward:/community.show?actionMethod=dimquery&shortValue=" + shortValue
				: "forward:/community.show?actionMethod=dimquery";
	}

	private void getSearchMap(String name, String value, Map oneMap, Map<String, List> twoMap) {
		String fieldName;
		if (name.startsWith("ddhb_one_")) {
			fieldName = name.substring(9);
			if (!"".equals(value.trim())) {
				if (!"inital".equals(fieldName.trim()) && !"communityName".equals(fieldName.trim())) {
					if (fieldName.endsWith("erpId")) {
						oneMap.put(fieldName, value);
					} else {
						oneMap.put(fieldName, Long.valueOf(value));
					}
				} else {
					oneMap.put(fieldName.trim(), value);
				}
			}
		}

		if (name.startsWith("ddhb_two_")) {
			fieldName = name.substring(9);
			if (!"".equals(value.trim())) {
				int index = value.indexOf("@");
				if (index > 0) {
					String minValue = value.substring(0, index);
					String maxValue = value.substring(index + 1);
					List list = new ArrayList();
					if ("startSaleDate".equals(fieldName)) {
						minValue = minValue + " 00:00:01";
						maxValue = maxValue + " 23:59:59";
						maxValue = maxValue.replace("-01-01", "-12-31");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date minDate = null;
						Date maxDate = null;

						try {
							minDate = sdf.parse(minValue);
							maxDate = sdf.parse(maxValue);
						} catch (ParseException var14) {
							minDate = new Date();
							maxDate = new Date();
						}

						list.add(minDate);
						list.add(maxDate);
						twoMap.put(fieldName, list);
					} else if ("currentSHAvePrice".equals(fieldName)) {
						list.add(Float.valueOf(Float.valueOf(minValue).floatValue() * 10000.0F));
						list.add(Float.valueOf(Float.valueOf(maxValue).floatValue() * 10000.0F));
						twoMap.put(fieldName, list);
					} else {
						list.add(Float.valueOf(minValue));
						list.add(Float.valueOf(maxValue));
						twoMap.put(fieldName, list);
					}
				}
			}
		}

	}
}
