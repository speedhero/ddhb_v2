package com.huatek.hbwebsite.show;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.partner.baidu.common.StringUtil;

import com.google.gson.Gson;
import com.huatek.base.entity.BaseEntity;
import com.huatek.ddhb.manage.activityManage.entity.ADAndActivityItem;
import com.huatek.ddhb.manage.activityManage.service.ActivityManageService;
import com.huatek.ddhb.manage.footerManage.entity.FooterLink;
import com.huatek.ddhb.manage.footerManage.entity.FooterMenu;
import com.huatek.ddhb.manage.footerManage.service.FooterService;
import com.huatek.ddhb.manage.frendLink.entity.FrendLink;
import com.huatek.ddhb.manage.frendLink.service.FrendLinkService;
import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.ddhb.manage.frontsystemsetting.service.FrontSystemSettingService;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.FooterLinkDTO;
import com.huatek.hbwebsite.common.entity.FooterMenuDTO;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseNew;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseNewService;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.entity.SubscribeInfo;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import com.huatek.hbwebsite.member.service.SubscribeInfoService;
import com.huatek.hbwebsite.news.service.NewsService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.HouseListPictureUtil;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

@Controller
@RequestMapping({ "/welcome.show" })
public class WebIndexAction {
	@Autowired
	PlatMemberInfoService platMemberInfoService;
	@Autowired
	private SubscribeInfoService subScribeInfoService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private RentService rentService;
	@Autowired
	private HouseNewService houseNewService;
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	private HouseSecondService houseSecondService;
	@Autowired
	PlatCollectionService platCollectionService;
	@Autowired
	FrendLinkService frendLinkService;
	@Autowired
	FooterService footerService;
	@Autowired
	FrontSystemSettingService frontSystemSettingService;
	@Autowired
	CommunityService communityService;
	
	@Autowired
	NewsService newsService;	//新闻Service

	
	private static final Logger LOGGER = Logger.getLogger(WebIndexAction.class);
	//邮件格式校验正则表达式
	private static final String EMAIL_PARTTERN = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
	
	@RequestMapping(params = { "actionMethod=welcome" })
	public String showIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		SubscribeInfo subScribeInfo = new SubscribeInfo();
		subScribeInfo.setEmail("请输入您的Email地址");
		model.addAttribute("subScribeInfo", subScribeInfo);
		
		model.addAttribute("backType", 1);
		request.setAttribute("backType", 1);

		List<HouseNew> newHouseList = this.houseNewService.loadHouseNewListWithCount(8);
		model.addAttribute("newHouseList", newHouseList);

		getRecommandSaleHouse(request, model);

		getRecommandRentHouse(request, model);

		model.addAttribute("activitieList", activityManageService.getActivity("1"));
		model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("1"));
		FrontSystemSetting homeBG = frontSystemSettingService.loadSpecificSetting("homepage_background");
		FrontSystemSetting homehover = frontSystemSettingService.loadSpecificSetting("homepage_logo_hover");
		FrontSystemSetting companyInfo = frontSystemSettingService.loadSpecificSetting("company_info");
		FrontSystemSetting companyRight = frontSystemSettingService.loadSpecificSetting("company_copyright");
		model.addAttribute("homePageLogoHover", homehover.getSettingValue());
		model.addAttribute("homePageBanner", homeBG.getSettingValue());
		model.addAttribute("companyInfo", companyInfo.getSettingValue());
		model.addAttribute("companyCopy", companyRight.getSettingValue());

		String logoPC = FrontSystemSettingUtil.getInstance().getLogoPc();
		//String homeLogoPad = FrontSystemSettingUtil.getInstance().getLogoPc();
		//String homeLogoMb = FrontSystemSettingUtil.getInstance().getLogoPc();
		String logPad = FrontSystemSettingUtil.getInstance().getLogoPad();
		String logMb = FrontSystemSettingUtil.getInstance().getLogoMb();
		model.addAttribute("homeLogoPc", logoPC);
		model.addAttribute("homeLogoPad", logPad);
		model.addAttribute("homeLogoMb", logMb);
		model.addAttribute("frendLinkList", this.frendLinkService.loadAllFrendLink());
		model.addAttribute("topADBar", this.activityManageService.getActivityByPageAndPosition(1, 1));
		model.addAttribute("bottomADBar", this.activityManageService.getActivityByPageAndPosition(1, 2));
		String clientFlag = ClientFlagUtil.getClientFlag(request);

		String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
		model.addAttribute("secondHouseCompare", comparedSHString==null? "" : comparedSHString);
		
		String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
		model.addAttribute("secondHouseHistory", historySHString==null?"":historySHString);

		String comparedRHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
		model.addAttribute("rentHouseCompare", comparedRHString==null? "": comparedRHString);

		String historyRHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "rentHouseHistory");
		model.addAttribute("rentHouseHistory", historyRHString==null?"":historyRHString);

		//二手房源价格走势
		List<Object[]> listUnitPriceTrend = houseSecondService.getHouseAvgUnitPriceTrendLastYear();
		model.addAttribute("listUnitPriceTrend", listUnitPriceTrend);

		//出租房源价格走势
		List<Object[]> listRentPriceTrend = rentService.getHouseAvgRentPriceTrendLastYear();
		model.addAttribute("listRentPriceTrend", listRentPriceTrend);

		//TODO 首页价格走势图 Modify by 何剑波 start 
		List<List<Object[]>> priceChartsList = houseSecondService.getPriceChart(1);
		if(priceChartsList.size() > 0){
			//挂牌均价
			model.addAttribute("medianListing",priceChartsList.get(0));
			//成交均价
			model.addAttribute("averageTransationPrice",priceChartsList.get(1));
			//成交量
			model.addAttribute("volume",priceChartsList.get(2));

			for(Object[] ob : priceChartsList.get(0)){
				System.out.println(ob[0]+":"+ob[1]);
			}
		}
		//END
		//行业动态
		CutPageBean pageBean = new CutPageBean();
		pageBean.setPageSize(10);
		pageBean.setCurrentPage(0);
		CutPageBean newsBean = this.newsService.getCutPageBean(pageBean, new ArrayList<CommonBean>(), 2);
		model.addAttribute("newPageBean", newsBean);

		//查询页脚热门小区
		pageBean.setPageSize(10);
		CutPageBean footMenuBean = footerService.getFooterMenu(pageBean, new ArrayList<CommonBean>());		
		model.addAttribute("footMenuBean", footMenuBean);

		//webUrl = www.hshb.cn		
		String webUrl = (String) request.getAttribute("globalUrl");
//		System.out.println("webURl:" + webUrl);
		//获取二手房热门小区
		List<FooterLink> listFL = footerService.getRandomLinksForSale(7);
//		listFL = getFooterLinkUrl(listFL, webUrl);
		model.addAttribute("saleHotCommunityList", listFL);
		
		//获取出租房热门小区
		List<Community> listComm = rentService.getRandomHotCommunityForRent(7);
		model.addAttribute("rentHotCommunityList", listComm);
		
		//获取城区
		List<BaseEntity> cityAreaList = searchService.loadQuery("Area", (String) null, " sortFlag");
		model.addAttribute("cityAreaList", cityAreaList);
		
		//return "base.definition";
		return "page.homepage2";
	}

	/**
	 * 新首页测试
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=homepage" })
	public String homepage2(Model model, HttpServletRequest request, HttpServletResponse response){
		showIndex(model, request, response);
		return "base.definition";
	}
	
	/**
	 * 给热门二手房房源附上URL
	 * @param listFL
	 * @return
	 */
	private List<FooterLink> getFooterLinkUrl(List<FooterLink> listFL, String webUrl){
		List<FooterLink> list = houseSecondService.getFooterLinkUrl(listFL, webUrl);
		return list;
	}

	/**
	 * 对于已登录会员用户，给房源设置收藏ID和房源评价快速搜索代码(二手房)
	 * 对于新版首页，已经不再使用收藏ID和评价快速搜索代码
	 * 
	 * @deprecated
	 * @param request
	 * @param houseList
	 */
	private void judgeCollectForSale(HttpServletRequest request, List<HouseSecond> houseList) {
		PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (memberInfo != null) {
			Long memberId = memberInfo.getId();

			List<String> houseNoList = new ArrayList<String>();
			for(HouseSecond house: houseList){
				houseNoList.add(house.getHouseNo());
			}
			List<HouseAppraise> haList = houseSecondService.getHouseAppraisesByHouseNo(houseNoList);

			Map<String, String> searchNoMap = new HashMap<String, String>();
			for(HouseAppraise app: haList){
				if (app.getBroker() != null) {
					searchNoMap.put(app.getHouseNo() + "-" + app.getBroker().getErpId(), app.getSearchno());
				}
			}

			//根据会员ID查询会员收藏房源的收藏 ID
			List<PlatCollection> platList = platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectIdMap = new HashMap<String, Long>();
			for(PlatCollection pc: platList){
				collectIdMap.put(pc.getObjectId() + "-" + pc.getCollectType(), pc.getId());
			}

			//给房源设置收藏ID
			for(HouseSecond house: houseList){
				String searchNo = "";
				if (house.getBroker() != null) {
					searchNo = (String) searchNoMap.get(house.getHouseNo() + "-" + house.getBroker().getErpId());
				}
				Long collectId = collectIdMap.get(searchNo + "-" + 0);
				house.setCollectId(collectId);
				house.setObjectId(searchNo);
			}
		}
	}

	/**
	 * 对于已登录会员用户，给房源设置收藏ID和房源评价快速搜索代码
	 * 对于新版首页，已经不再使用收藏ID和评价快速搜索代码
	 * 
	 * @deprecated
	 * @param request
	 * @param houseList
	 */
	private void judgeCollectForRent(HttpServletRequest request, List<HouseRent> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			
			List<String> houseNoList = new ArrayList<String>();
			for(HouseRent house: houseList){
				houseNoList.add(house.getHourseNo());
			}

			List<HouseAppraise> haList = houseSecondService.getHouseAppraisesByHouseNo(houseNoList);
			Map<String, String> searchNoMap = new HashMap<String, String>();
			for(HouseAppraise app: haList){
				if (app.getBroker() != null) {
					searchNoMap.put(app.getHouseNo() + "-" + app.getBroker().getErpId(), app.getSearchno());
				}
			}

			List<PlatCollection> platList = platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectIdMap = new HashMap<String, Long>();
			for(PlatCollection pc: platList){
				collectIdMap.put(pc.getObjectId() + "-" + pc.getCollectType(), pc.getId());
			}

			if (houseList != null) {
				for(HouseRent house: houseList){
					String searchNo = "";
					if (house.getBroker() != null) {
						searchNo = (String) searchNoMap.get(house.getHourseNo() + "-" + house.getBroker().getId());
					}
					Long collectId = collectIdMap.get(searchNo + "-" + 1);
					house.setCollectId(collectId);
					house.setObjectId(searchNo);
				}
			}
		}
	}

	
	/**
	 * 订阅
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=subscribe" }, method = { RequestMethod.POST })
	public String subscribe(Model model, HttpServletRequest request) {
		String email = request.getParameter("email");
		Boolean flag = this.subScribeInfoService.checkEmailIfExist(email);
		if (flag.booleanValue()) {
			model.addAttribute("promptMessage", "您已经订阅过此邮件");
			return "base.definition";
		} else if (CommonUtil.isZeroLengthTrimString(email)) {
			model.addAttribute("promptMessage", "请输入邮件地址");
			return "base.definition";
		} else if (!Util.getMatchResult(email, EMAIL_PARTTERN)) {
			model.addAttribute("promptMessage", "邮件格式不正确，请重新输入");
			return "base.definition";
		} else {
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			this.subScribeInfoService.saveSubScribeInfo(email, platMemberInfo);
			model.addAttribute("promptMessage", "您已经订阅成功");
			return "base.definition";
		}
	}

	/**
	 * 按条件搜索
	 * @param searchModule	搜索板块，1：二手房，2：租房，3：小区
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = { "actionMethod=dimQuery" })
	public String dimQuery(@RequestParam("searchModule") int searchModule, @RequestParam("type") int type,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String inputSearch = request.getParameter("inputSearch");
		request.setAttribute("backType", 1);
		LOGGER.debug("inputSearch = " + inputSearch);
		if (inputSearch != null) {
			inputSearch = new String(inputSearch.getBytes("iso-8859-1"), "UTF-8");
		}

		if (StringUtils.isNotBlank(inputSearch)) {
			inputSearch = inputSearch.trim();
			if (houseSecondService.hasThisHouse(inputSearch)) {
				request.setAttribute("houseNo", inputSearch);
				return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=1";
			}

			if (rentService.hasThisHouse(inputSearch)) {
				request.setAttribute("hourseNo", inputSearch);
				return "forward:rent.show?actionMethod=dimquery&ispage=0&type=1";
			}

			if (communityService.hasThisCommunity(inputSearch)) {
				request.setAttribute("communityNo", inputSearch);
				return "forward:community.show?actionMethod=dimquery&ispage=0&type=" + type;
			}
		}

		if (StringUtils.isNotBlank(inputSearch)) {
			request.setAttribute("inputSearch", inputSearch.trim());
		}

		Enumeration<String> params = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (params.hasMoreElements()) {
			String pName = params.nextElement().trim();
			if (pName.indexOf("searchModule") < 0 && pName.indexOf("actionMethod") < 0 && pName.indexOf("inputSearch") < 0) {
				sb.append("&").append(pName).append("=").append(request.getParameter(pName).trim());
			}
		}

		if (searchModule == 1) {
			return "forward:houseSecond.show?actionMethod=dimquery&ispage=0" + sb.toString();
		} else if (searchModule == 2) {
			return "forward:rent.show?actionMethod=dimquery&ispage=0" + sb.toString();
		} else {
			return "forward:community.show?actionMethod=dimquery&ispage=0" + sb.toString();
		}
	}

	/**
	 * 查询推荐的二手房源
	 * @param request
	 * @param model
	 */
	private void getRecommandSaleHouse(HttpServletRequest request, Model model){
		List<HouseSecond> secondHouseList = this.houseSecondService.loadRecommandedHouse(12);
		HouseListPictureUtil.getCorrespondingSHPictures(secondHouseList, this.houseSecondService);
		
		String[] ids = new String[secondHouseList.size()];
    for (HouseSecond house : secondHouseList){
      String[] tagArr = house.getShowTags().split(",");
      List<String> tagIdList = new ArrayList<String>();
      for (int ii = 1; ii < tagArr.length; ii++) {
        tagIdList.add(tagArr[ii]);
      }
      house.setTagIdList(tagIdList);
    }
		for (int ii = 0; ii < secondHouseList.size(); ++ii) {
			ids[ii] = secondHouseList.get(ii).getErpId();
		}

		List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(ids, 1, 1, 0);
		Iterator<HouseSecond> itHouseS = secondHouseList.iterator();

		while (itHouseS.hasNext()) {
			HouseSecond house = itHouseS.next();
			Iterator<HousePic> itHousPic = housePicList.iterator();

			while (itHousPic.hasNext()) {
				HousePic housePic = itHousPic.next();
				if (house.getErpId().equals(housePic.getHouseId())) {
					house.setHouseUrl(housePic.getPicUrl());
				}
			}
			//解析房源标签
			house.setHouseTags(houseSecondService.parseHouseTag(house.getTags()));
		}
		model.addAttribute("secondList", secondHouseList);		
		
//		judgeCollectForSale(request, secondHouseList);
	}
	
	/**
	 * 获取推荐租赁房源
	 * @param request
	 * @param model
	 */
	private void getRecommandRentHouse(HttpServletRequest request, Model model) {
		List<HouseRent> rentList = this.rentService.loadRecommandedHouse(12);
		if (rentList != null) {
			Iterator<HouseRent> itHouse = rentList.iterator();

			while (itHouse.hasNext()) {
				HouseRent house = itHouse.next();
				String[] furList = house.getShowFunitures().split(",");
				List<String> furIdList = new ArrayList<String>();
				for (int ii = 1; ii < furList.length; ++ii) {
					furIdList.add(furList[ii]);
				}
				house.setFurIdList(furIdList);
			}
		}

		String[] houseIDs = new String[rentList.size()];

		for (int ii = 0; ii < rentList.size(); ++ii) {
			houseIDs[ii] = rentList.get(ii).getErpId();
		}

		List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(houseIDs, 2, 1, 0);
		Iterator<HouseRent> itHouseRent = rentList.iterator();
		while (itHouseRent.hasNext()) {
			HouseRent house = itHouseRent.next();
			Iterator<HousePic> itHousePic = housePicList.iterator();

			while (itHousePic.hasNext()) {
				HousePic housePic = itHousePic.next();
				if (house.getShelvingID().trim().equals(housePic.getShelvingID().trim())) {
					house.setPictureUrl(housePic.getPicUrl());
					break;
				}
			}
		}

		List<Furniture> furList = this.rentService.getFurList();
		model.addAttribute("furList", furList);

		model.addAttribute("rentList", rentList);

//		this.judgeCollectForRent(request, rentList);
	}

	/**
	 * 顶部输入条件搜索
	 * @param type
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = { "actionMethod=dimQueryTopSearchBar" })
	public String dimQueryTopSearchBar(@RequestParam("type") int type, HttpServletRequest request) throws UnsupportedEncodingException {
		String input = request.getParameter("searchInput");
		String inputSearch = null;
		request.setAttribute("backType", Integer.valueOf(1));

		if (input != null) {
			inputSearch = new String(input.getBytes("iso-8859-1"), "UTF-8");
		}

//	boolean searchModule = false;
		if (inputSearch != null && !"".equals(inputSearch.trim())) {
			request.setAttribute("searchContent", inputSearch);
			if (inputSearch.indexOf("SH") >= 0) {
				request.setAttribute("houseNo", inputSearch.trim());
//				searchModule = true;
				return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=" + type;
			}

			if (inputSearch.indexOf("RH") >= 0) {
				request.setAttribute("hourseNo", inputSearch.trim());
//				searchModule = true;
				return "forward:rent.show?actionMethod=dimquery&ispage=0&type=" + type;
			}

			if (inputSearch.indexOf("SO") >= 0) {
				byte searchModule1 = 3;
				HouseAppraise houseAppraise = this.subScribeInfoService.getAppraiseBySearchno(inputSearch.trim());
				if (houseAppraise != null) {
					if (houseAppraise.getHouseType() == 1) {
						request.setAttribute("houseNo", houseAppraise.getHouseNo());
						request.setAttribute("houseAppraise", houseAppraise);
						return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=" + type;
					}

					request.setAttribute("hourseNo", houseAppraise.getHouseNo());
					request.setAttribute("houseAppraise", houseAppraise);
					return "forward:rent.show?actionMethod=dimquery&ispage=0&type=" + type;
				}

				if (searchModule1 == 1) {
					request.setAttribute("houseNo", "-1");
					return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=" + type;
				}

				if (searchModule1 == 2) {
					request.setAttribute("hourseNo", "-1");
					return "forward:rent.show?actionMethod=dimquery&ispage=0&type=" + type;
				}
			}

			if (inputSearch.indexOf("10000") >= 0) {
				request.setAttribute("communityNo", inputSearch.trim());
				return "forward:community.show?actionMethod=dimquery&ispage=0&type=" + type;
			}
		}

		return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=" + type;
	}

	/**
	 * 获取友情链接
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = { "actionMethod=doRendLink" })
	public void doRendLink(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson e = new Gson();
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			List<FrendLink> frendLinkList = this.frendLinkService.loadAllFrendLink();
			String returnedString = e.toJson(frendLinkList);
			PrintWriter write = response.getWriter();
			write.print(returnedString);
			write.close();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}

	}

	/**
	 * 获取页脚菜单
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = { "actionMethod=doFooterMenu" })
	public void doFooterMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson e = new Gson();
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			List<CommonBean> commonList = QueryCondition.getQueryCondition(request, FooterMenu.class);
			CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
			CutPageBean pageBean = this.footerService.getFooterMenu(cutPageBean, commonList);
			List<FooterMenu> menuList = (List<FooterMenu>)pageBean.getDataList();
			List<FooterMenuDTO> menuDTOList = new ArrayList<FooterMenuDTO>();
			if (menuList != null && menuList.size() >= 1) {
				Iterator<FooterMenu> itFM = menuList.iterator();
				while (itFM.hasNext()) {
					FooterMenu fmParent = itFM.next();
					List<FooterLink> linkList = this.footerService.getLinksByMenuId(fmParent.getId());
					
					List<FooterLinkDTO> linkDTOList = new ArrayList<FooterLinkDTO>();
					if (linkList != null && linkList.size() > 0) {
						Iterator<FooterLink> itFL = linkList.iterator();
						while (itFL.hasNext()) {
							FooterLink link = itFL.next();
							FooterLinkDTO linkDTO = new FooterLinkDTO();
							linkDTO.setId(link.getId());
							linkDTO.setLinkName(link.getLinkName());
							linkDTO.setLinkUrl(link.getLinkUrl());
							linkDTOList.add(linkDTO);
						}
					}

					FooterMenuDTO fmDTOParent = new FooterMenuDTO();
					fmDTOParent.setId(fmParent.getId());
					fmDTOParent.setMenuName(fmParent.getMenuName());
					fmDTOParent.setLinkDTOList(linkDTOList);
					menuDTOList.add(fmDTOParent);
					fmParent.setLinkList(linkList);
				}
			}
			String returnedStr = e.toJson(menuDTOList);
			PrintWriter write = response.getWriter();
			write.print(returnedStr);
			write.close();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}

	}

	/**
	 * 获取首页LOGO
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = { "actionMethod=doFrontLogo" })
	public void doFrontLogo(Model model, HttpServletRequest request, HttpServletResponse response) {
		FrontSystemSetting homeLogo = this.frontSystemSettingService.loadSpecificSetting("homepage_logo");
		model.addAttribute("homePageLogo", homeLogo.getSettingValue());
		FrontSystemSetting homehover = this.frontSystemSettingService.loadSpecificSetting("homepage_logo_hover");
		model.addAttribute("homePageLogoHover", homehover.getSettingValue());
		try {
			PrintWriter write = response.getWriter();
			write.print(homeLogo.getSettingValue());
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * 获取首页活动
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=activityDetail" })
	public String getActivityById(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		Long activityIdLong = Long.parseLong(id);
		ADAndActivityItem commonActivity = (ADAndActivityItem) this.activityManageService.getObjectById(ADAndActivityItem.class, activityIdLong);
		model.addAttribute("commonActivity", commonActivity);
		return "activity.detail";
	}
}
