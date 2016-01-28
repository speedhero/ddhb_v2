/**
 * 
 */
package cn.hshb.web.biz.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huatek.hbwebsite.util.ClientFlagUtil;

import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonFooterLink;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseWithBLOBs;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.SubscribeInfo;
import cn.hshb.web.biz.service.CommonActivityService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.service.CommonCountyTwoService;
import cn.hshb.web.biz.service.CommonFlagService;
import cn.hshb.web.biz.service.CommonFooterLinkService;
import cn.hshb.web.biz.service.CommonFooterMenuService;
import cn.hshb.web.biz.service.CommonLiveFacilityService;
import cn.hshb.web.biz.service.DdhbSystemSetService;
import cn.hshb.web.biz.service.FrendLinkService;
import cn.hshb.web.biz.service.HouseNewhouseService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.biz.service.NewsTypeService;
import cn.hshb.web.biz.service.PriceChartService;
import cn.hshb.web.biz.util.UserBrowsedHistoryUtil;
import cn.hshb.web.biz.util.UserCompareUtil;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.house.enums.EnumNewsType;


/**
 * 网站首页Controller
 * 
 * @author ShengYoufu
 *
 */
@Controller("homepage")
@RequestMapping("/index.html")
public class HomepageController extends HouseBaseController {
	private static final Log log = LogFactory.getLog(HomepageController.class);
	
//	@Autowired
//	@Qualifier("houseNewHouseService1")
//	private HouseNewhouseService houseNewHouseService1;

//	@Autowired
//	@Qualifier("houseNewHouseService2")
//	private HouseNewhouseService houseNewHouseService2;

	@Autowired
	private HouseNewhouseService houseNewHouseService;
	
	@Autowired
	private HouseSecondhandHouseService houseSecondhandHouseService;

	@Autowired
	private HouseRentService houseRentService;
	
	@Autowired
	private CommonFlagService commonFlagService;
	
	@Autowired
	private CommonLiveFacilityService commonLiveFacilityService;
	
	@Autowired
	private CommonActivityService commonActivityService;
	
	@Autowired
	private DdhbSystemSetService ddhbSystemSetService;
	
	@Autowired
	private FrendLinkService frendLinkService;
	
	@Autowired
	private PriceChartService priceChartService;
	
	@Autowired
	private NewsTypeService newsTypeService;
	
	@Autowired
	private CommonFooterLinkService commonFooterLinkService;
	
	@Autowired
	private CommonCountyService commonCountyService;
	
	@Autowired
	private CommonCountyTwoService commonCountyTwoService;

	@Autowired
	private CommonFooterMenuService commonFooterMenuService;
	
	@RequestMapping(value= {""})
	public String index(Model model, HttpServletRequest request, HttpServletResponse response){
		
		SubscribeInfo subScribeInfo = new SubscribeInfo();
		subScribeInfo.setEmail("请输入您的Email地址");
		model.addAttribute("subScribeInfo", subScribeInfo);
		
		model.addAttribute("backType", 1);
		request.setAttribute("backType", 1);

		List<HouseNewhouseWithBLOBs> newHouseList = houseNewHouseService.loadNewHouse(8);
		model.addAttribute("newHouseList", newHouseList);

		List<HouseSecondHandHouse> houseSecondList = houseSecondhandHouseService.loadRecommandedHouse(12);
		model.addAttribute("secondList", houseSecondList);

		List<HouseRentHouseExt> houseRentList = houseRentService.loadRecommandedHouse(12);
		model.addAttribute("rentList", houseRentList);

		//解析二手房房源标签 
		for(HouseSecondHandHouse h: houseSecondList){
			List<CommonFlag> tagList = houseSecondhandHouseService.parseHouseTag(h.getTags());
			h.setHouseTags(tagList);
		}
		model.addAttribute("furList", commonLiveFacilityService.getHouseFurnitures());

		//取系统参数
		model.addAttribute("activitieList", commonActivityService.loadActivity("1"));
		model.addAttribute("homePageLogoHover", ddhbSystemSetService.getSystemSetting("homepage_logo_hover").getSetValue());
		model.addAttribute("homePageBanner", ddhbSystemSetService.getSystemSetting("homepage_background").getSetValue());
		model.addAttribute("companyInfo", ddhbSystemSetService.getSystemSetting("company_info").getSetValue());
		model.addAttribute("companyCopy", ddhbSystemSetService.getSystemSetting("company_copyright").getSetValue());		
		model.addAttribute("homeLogoPc", ddhbSystemSetService.getSystemSetting("homepage_logo").getSetValue());
		model.addAttribute("homeLogoPad", ddhbSystemSetService.getSystemSetting("homepage_logo_pad").getSetValue());
		model.addAttribute("homeLogoMb", ddhbSystemSetService.getSystemSetting("homepage_logo_mb").getSetValue());
		model.addAttribute("frendLinkList", frendLinkService.getFrendLinks());

		//取广告
		model.addAttribute("topADBar", commonActivityService.getActivityByPageAndPosition(1, 1));
		model.addAttribute("bottomADBar", commonActivityService.getActivityByPageAndPosition(1, 2));
		
		String clientFlag = ClientFlagUtil.getClientFlag(request);

		String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, EnumHouseType.SALE);
		model.addAttribute("secondHouseCompare", comparedSHString==null? "" : comparedSHString);
		
		String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, EnumHouseType.SALE);
		model.addAttribute("secondHouseHistory", historySHString==null?"":historySHString);

		String comparedRHString = UserCompareUtil.getInstance().getCompareString(clientFlag, EnumHouseType.RENT);
		model.addAttribute("rentHouseCompare", comparedRHString==null? "": comparedRHString);

		String historyRHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, EnumHouseType.RENT);
		model.addAttribute("rentHouseHistory", historyRHString==null?"":historyRHString);
		
		
		//首页价格走势图
		List<List<Object[]>> priceChartsList = priceChartService.getPriceChart(EnumHouseType.SALE);
		if(priceChartsList.size() > 0){
			//挂牌均价
			model.addAttribute("medianListing",priceChartsList.get(0));
			//成交均价
			model.addAttribute("averageTransationPrice",priceChartsList.get(1));
			//成交量
			model.addAttribute("volume",priceChartsList.get(2));
		}
		
		//行业动态
		model.addAttribute("newList", newsTypeService.getNewsByType(EnumNewsType.INDUSTRY.value(), 1, 10));
		//新闻动态
		model.addAttribute("companyList", newsTypeService.getNewsByType(EnumNewsType.COMPANY.value(), 1, 10));
		
		//查询页脚热门小区
		model.addAttribute("footMenuBean", commonFooterLinkService.getFooterMenu(1, 10));

		String webUrl = (String) request.getAttribute("globalUrl");
		//获取二手房热门小区
		List<CommonFooterLink> listFL = commonFooterLinkService.getRandomLinksForSale(7);

		//TODO: 这里生成链接部分要重构
//		listFL = getFooterLinkUrl(listFL, webUrl);
		model.addAttribute("saleHotCommunityList", listFL);

		//获取出租房热门小区
		model.addAttribute("rentHotCommunityList", commonCountyTwoService.getRandomHotCommunityForRent(7));

		//获取城区
		model.addAttribute("cityAreaList", commonCountyService.getCounties());

//		//取查询菜单参数
//		model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("1"));
		
		//首页底部菜单信息
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		return "homepage.index";
	}
}
