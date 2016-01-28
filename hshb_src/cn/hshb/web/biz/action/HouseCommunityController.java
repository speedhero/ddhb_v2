package cn.hshb.web.biz.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExt;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseReduceNotice;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.CommonFooterMenuService;
import cn.hshb.web.biz.service.HouseCommunityExpertService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HouseReduceNoticeService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.biz.service.MemberBrowseHistoryService;
import cn.hshb.web.biz.util.HouseQueryStrParser;
import cn.hshb.web.biz.util.HouseRecommendUtil;
import cn.hshb.web.biz.util.PageMetaBean;
import cn.hshb.web.partner.baidu.common.StringUtil;

@Controller("village")
@RequestMapping("/xiaoqu")
public class HouseCommunityController extends HouseBaseController {
	private static final Log log = LogFactory.getLog(HouseCommunityController.class);
	private static final String PATH="xiaoqu";
	private static final String HOUSE_SECOND = "sale";
	private static final String HOUSE_RENT = "rent";
	
	@Autowired
	private HouseCommunityService houseCommunityService;
	@Autowired
	private MemberBrowseHistoryService memberBrowseHistoryService;
	@Autowired
	private HouseReduceNoticeService houseReduceNoticeService;
	@Autowired
	private HouseRentService houseRentService;
	@Autowired
	private HouseSecondhandHouseService  houseSecondhandHouseService;
	@Autowired
	private CommonFooterMenuService commonFooterMenuService;
	@Autowired
	private HouseCommunityExpertService houseCommunityExpertService;
	
	/** 测试方法 开始**/
	@RequestMapping(value = {"/index"})
	public void index(HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("这里是小区的首页");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/** 测试方法结束 **/
	
	/**
	 * 未输入条件,则表示查询列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {""})
	public String list(HttpServletRequest request, Model model){
		return list(null, null, request, model);
	}
	
	//TODO 利用正则限制输出[_.a-zA-Z\\d-\u4E00-\u9FA5]+
	@RequestMapping(value = {"/{pathStr:[_.a-zA-Z\\d-\u4E00-\u9FA5]+}"})
	public String list(@PathVariable String pathStr, HttpServletRequest request, Model model){
			return list(pathStr, null, request, model);
	}
	
	@RequestMapping(value = {"/{communityId}.html"})
	public String detail(@PathVariable String communityId, HttpServletRequest request, Model model){
		HouseCommunity community = houseCommunityService.findCommunityByCommunityId(communityId);
		if(community != null)
			//如果按房源编号能找到房源,则调到房源详情页
			return houseDetail(community, communityId, request, model);
		else
			return "house.community.notexist";
	}
	/**
	 * 查找小区房源
	 * @param pathStr
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/search/{pathStr}"})
	public String searchList(@PathVariable String pathStr, HttpServletRequest request, Model model){
		//1：显示二手房 2：显示租赁
		String returnUrl = "";
		//对参数进行分析
		Map<String, String> condition = parseQueryCondition(pathStr,"s1");
		String houseType = request.getParameter("housetype");
		if("1".equals(houseType)){
			//二手房
			returnUrl = "hshb/front/community/community_houseSecond_search_condition.jsp";
			handleSearchByHouseSecond(condition, model, 1);
		}else if("2".equals(houseType)) {
			//租赁
			returnUrl = "hshb/front/community/community_rent_search_condition.jsp";
			handleSearchByHouseRent(condition, model, 1);
		}
		//把上次的搜索参数保存到Model,以便在页面上重新生成各个链接
		String basePath =  PATH + "/search/" + pathStr + "/" + ("1".equals(houseType)?HOUSE_SECOND:HOUSE_RENT);
		model.addAttribute("basePath", basePath );
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("s", "1");
		model.addAttribute("params", params);
		return returnUrl;
	}
	
	/**
	 * 根据条件 查询小区房源
	 * @param pathStr1	 小区编号
	 * @param PathStr2	查询类别 
	 * @param PathStr3	查询条件
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/search/{pathStr1}/{pathStr2}/{pathStr3}"})
	public String searchList(@PathVariable String pathStr1, @PathVariable String pathStr2, @PathVariable String pathStr3, HttpServletRequest request, Model model){
		//对参数进行分析
		Map<String, String> condition = parseQueryCondition(pathStr3,pathStr1);
		Integer pageNum = 1;
		if(condition.containsKey("pageNum")){
			if(StringUtil.isNumeric(condition.get("pageNum"))){
				pageNum = Integer.parseInt(condition.get("pageNum"));
			}
		}
		if(HOUSE_SECOND.equals(pathStr2)){
			model.addAttribute("communitySearchHouseSecond", "communitySearchHouseSecond");
			
			handleSearchByHouseSecond(condition, model, pageNum);
		}else if(HOUSE_RENT.equals(pathStr2)){
			model.addAttribute("communitySearchHouseRent", "communitySearchHouseRent");
			
			handleSearchByHouseRent(condition,model,pageNum);
		}
		//把上次的搜索参数保存到Model,以便在页面上重新生成各个链接
		String basePath =  PATH + "/search/" + pathStr1 + "/" + pathStr2;
		model.addAttribute("basePath", basePath );
		
		//Map<String, String> params = HouseQueryStrParser.parseQueryCondition(pathStr1, pathStr3);
		Map<String, String> params = HouseQueryStrParser.parseQueryCondition("", pathStr3);
		//如果未指定排序方式,则按默认条件排序
		if(!params.containsKey("s"))
			params.put("s", "1");
		model.addAttribute("params", params);

		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		return detail(pathStr1.substring(4),request,model);
	}
	
	@RequestMapping(value={"/{pathStr1:[_.a-zA-Z\\d-\u4E00-\u9FA5]+}/{pathStr2:[_.a-zA-Z\\d-\u4E00-\u9FA5]+}"})
	public String list(@PathVariable String pathStr1, @PathVariable String pathStr2, HttpServletRequest request, Model model){
		
		//对参数进行解析
		Map<String, String> condition = parseQueryCondition(handleCondition(pathStr1,pathStr2,"jj"),null);
		
		Integer pageNum = 1;
		if(condition.containsKey("pageNum")){
			if(StringUtil.isNumeric(condition.get("pageNum"))){
				pageNum = Integer.parseInt(condition.get("pageNum"));
			}
		}
		
		Map<String, String> orderFields = new HashMap<String, String>(); 
		if(condition.containsKey("orderby")){
			orderFields.put(condition.get("orderby"), "asc");
		}
		
		List<HouseCommunityExt> communityList = houseCommunityService.getCommunityList(condition, orderFields, pageNum);
		model.addAttribute("communityList", communityList);
		
		PageInfo<HouseCommunityExt> page = new PageInfo<HouseCommunityExt>(communityList);
		model.addAttribute("pageBean", page);
		
		//查询搜索条件
		getSearchItem(model, "4");

		//把上次的搜索参数保存在model, 以便在页面上重新生成各个连接
		String basePath =  PATH; 
		model.addAttribute("basePath", basePath);
		//对搜索条件进行保存在model里,用于显示"本次搜索条件 "
		model.addAttribute("searchResults", houseCommunityService.getSearchFieldList(condition, basePath, pathStr1 + "/" + pathStr2));
		
		Map<String, String> params = HouseQueryStrParser.parseQueryCondition(pathStr1, pathStr2);
		//如果未指定排序方式,则按默认条件排序
		if(!params.containsKey("s"))
			params.put("s", "1");

		model.addAttribute("params", params);
		//用于顶部菜单栏 搜索
		model.addAttribute("backType", "5");
		//对比链接判断 用于compare_history.jsp
		model.addAttribute("showCompare","C");
		
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		
		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("小区");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_LIST);
		HouseQueryStrParser parser = new HouseQueryStrParser();
		String priceDesc = parser.getAvgPriceDescriptionByCondition(condition);
		String areaDesc = parser.getAreaDescriptionByCondition(condition);
		String countryName = parser.getCountryNameByCondition(condition);
		String cbdName = parser.getCbdNameByCondition(condition);
		String communityName = parser.getCommunityNameByCondition(condition);
		pageMeta.setCommunityName(communityName);
		pageMeta.setCbdName(cbdName);
		pageMeta.setCountryName(countryName);
		pageMeta.setPrice(priceDesc);
		pageMeta.setArea(areaDesc);
		model.addAttribute(PageMetaBean.BEAN_NAME_MODULE, pageMeta);

		return "house.community.list";
	}
	
	/**
	 * 处理房源详情附件信息
	 * @param house
	 * @param pathStr
	 * @param request
	 * @param model
	 * @return
	 */
	private String houseDetail(HouseCommunity community, String pathStr, HttpServletRequest request, Model model){
		//这里的用户对象用的HiberNate里的,以后废弃Hibernate后必须改成MyBatis的
		PlatMemberInfo member = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		
		if(member != null){
			memberBrowseHistoryService.saveUserBrowseHistory(member.getId(), 2, community.getErpId());
			List<HouseReduceNotice> noticeList = houseReduceNoticeService.getHouseNoticeByMemberAndHouseId(member.getId(), community.getErpId());
			model.addAttribute("noticeResult", noticeList.size() >0 ? "noticed" : "unnoticed");
		}
		//载入小区专家
		houseCommunityExpertService.loadHouseCOmmunityExpert(community);
		//载入最近门店列表
		houseCommunityService.loadNearestStores(community);
		model.addAttribute("community",community);
		
		//获取近6个月的平均价格
		model.addAttribute("trendList", houseCommunityService.getPriceTrendSixMonth(community.getErpId()));
		//车站
		model.addAttribute("communityStationMappings", houseCommunityService.findCStaMapping(community.getErpId()));
		//地铁站
		model.addAttribute("cSubMapping", houseCommunityService.findCSubMapping(community.getErpId()));
		
		//附近小区-------
//		HouseRecommendUtil analyser = (HouseRecommendUtil)request.getSession().getAttribute("browsedHistorys");
		String clientFlag;
//		if(analyser != null && community.getCbd()!=null){
//			clientFlag = analyser.putCbdToQueue(community.getCbd().getErpId());
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("cbdId", clientFlag);
//			List<HouseCommunityExt> recommCommList = houseCommunityService.getCommunityList(map, new HashMap<String ,String>(), 4);
//			
//			model.addAttribute("recommandedCommunity", recommCommList);
//		}
		//---
		//附近小区
		Map<String, String> map = new HashMap<String, String>();
		map.put("neCommunityId", community.getErpId());
		if(community.getCbdWebsite() != null)
			map.put("cbdId", community.getCbdWebsite().getWebsiteId().toString());
		map.put("ERPcbdId", community.getBusinessCircleId());
		List<HouseCommunityExt> recommCommList = houseCommunityService.getCommunityList(map, new HashMap<String ,String>(), 4);
		model.addAttribute("recommandedCommunity", recommCommList);
		
		clientFlag = ClientFlagUtil.getClientFlag(request);
		String compareStr = UserCompareUtil.getInstance().getCompareString(clientFlag, "communityCompare");
		if(compareStr == null)
			compareStr = "";
		model.addAttribute("communityCompare", compareStr);
		
		//用于顶部菜单栏 搜索
		model.addAttribute("backType", "5");
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		if(community.getCbd() != null)
			houseCommunityService.populateCommunity(community);
		// 对比链接判断 用于compare_history.jsp,开始对比链接
		model.addAttribute("showCompare","C");

		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("小区");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_DETAIL);
		pageMeta.setHouseTitle(community.getCommunityName());
		pageMeta.setCommunityName(community.getCommunityName());
		if(community.getCbdWebsite()!=null)
			pageMeta.setCbdName(community.getCbdWebsite().getName());
		if(community.getCbd()!=null && community.getCbd().getCounty()!=null)
			pageMeta.setCountryName(community.getCbd().getCounty().getCountyName());
		model.addAttribute(PageMetaBean.BEAN_NAME_MODULE, pageMeta);
		
		return "house.community.detail";
	}
	
	
	private String getMapToString(Map<String, String> map){
		StringBuffer sb = new StringBuffer();
		for(String s : map.keySet()){
			sb.append(s + map.get(s));
		}
		return sb.toString();
	}
	/**
	 * 查询小区租赁房源代码
	 * @param condition
	 * @param model
	 */
	private void handleSearchByHouseRent(Map<String, String> condition, Model model, Integer pageNum){
		getSearchItem(model,"3");
		Map<String, String> orderFields = new HashMap<String, String>();
		if(condition.containsKey("orderby")){
			orderFields.put(condition.get("orderby"), "asc");
		}
		List<HouseRentHouseExt> houseList = houseRentService.getHouseList(condition, orderFields, pageNum, 8);
		model.addAttribute("houseList", houseList);
		PageInfo<HouseRentHouseExt> page = new PageInfo<HouseRentHouseExt>(houseList);
		model.addAttribute("pageBean", page);
	}
	/**
	 * 查询小区二手房房源代码
	 * @param condition
	 * @param model
	 * @param pageNum
	 */
	private void handleSearchByHouseSecond(Map<String, String> condition, Model model, Integer pageNum){
		getSearchItem(model,"2");
		Map<String, String> orderFields = new HashMap<String, String>();
		if(condition.containsKey("orderby")){
			orderFields.put(condition.get("orderby"), "asc");
		}
		List<HouseSecondHandHouse> houseList = houseSecondhandHouseService.getHouseList(condition, orderFields, pageNum,8);
		model.addAttribute("houseList", houseList);
		PageInfo<HouseSecondHandHouse> page = new PageInfo<HouseSecondHandHouse>(houseList);
		model.addAttribute("pageBean", page);
	}
}
