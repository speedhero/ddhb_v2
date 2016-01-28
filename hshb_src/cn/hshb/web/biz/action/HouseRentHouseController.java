package cn.hshb.web.biz.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.huatek.hbwebsite.house.entity.Evaluation;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseReduceNotice;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonCountyTwoService;
import cn.hshb.web.biz.service.CommonFlagService;
import cn.hshb.web.biz.service.CommonFooterMenuService;
import cn.hshb.web.biz.service.CommonLiveFacilityService;
import cn.hshb.web.biz.service.CommonUsageService;
import cn.hshb.web.biz.service.HouseAppraiseService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HouseReduceNoticeService;
import cn.hshb.web.biz.service.HouseRentBrowseAnalyseService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.MemberBrowseHistoryService;
import cn.hshb.web.biz.util.HouseBrowseCacheUtil;
import cn.hshb.web.biz.util.HouseQueryStrParser;
import cn.hshb.web.biz.util.HouseRecommendUtil;
import cn.hshb.web.biz.util.PageMetaBean;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.partner.baidu.common.StringUtil;



/**
 * 租赁Controller
 * @author hejianbo
 *	2015年7月14日
 */
@Controller("rent")
@RequestMapping("/chuzu")
public class HouseRentHouseController extends HouseBaseController{
	private static final Log log = LogFactory.getLog(HouseRentHouseController.class);
	private static final String PATH="chuzu";
	
	@Autowired
	private HouseRentService houseRentService;
	@Autowired
	private CommonFlagService commonFlagService;
	@Autowired
	private HouseAppraiseService houseAppraiseService;
	@Autowired
	private MemberBrowseHistoryService memberBrowseHistoryService;
	@Autowired
	private HouseReduceNoticeService houseReduceNoticeService;
	@Autowired
	private HouseCommunityService houseCommunityService;
	@Autowired
	private HouseRentBrowseAnalyseService houseRentBrowseAnalyseService;
	@Autowired
	private CommonLiveFacilityService commonLiveFacilityService;
	@Autowired
	private CommonCountyTwoService commonCountyTwoService;
	@Autowired
	private BBrokerService bBrokerService;
	@Autowired
	private CommonFooterMenuService commonFooterMenuService;
	@Autowired
	private CommonUsageService commonUsageService;
	
	/** 下面是测试方法 **/
	@RequestMapping(value= {"index"})
	public void index(HttpServletRequest request, HttpServletResponse response,Model model){
		try{
			response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("这里是租赁的首页");
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
//	@RequestMapping(value={"/noInput"})
	@RequestMapping(value={""})
	public String list(HttpServletRequest request, Model model){
		return list(null, null, request, model);
	}
	
	/**
	 * 查询房源
	 * @param pathStr 如果是房源编号，则显示房源详情，否则查询房源列表并显示
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/{houseNo}.html"})
	public String detail(@PathVariable String houseNo, HttpServletRequest request, Model model){
		HouseRentHouse house = houseRentService.findHouseByHouseId(houseNo);
		if(house != null){
			//如果按房源编号能找到房源,则调到房源详情页
			//return houseDetail();
			return houseDetail(house, "", request, model);
		}else{
			model.addAttribute("fromERP", "1");
			if(!houseNo.contains("_"))
				return "house.err.notexist";
			String[] str = houseNo.split("_");
			if(str.length != 2)
				return "house.err.notexist";
			house = houseRentService.findHouseByHouseId(str[0]);
			//判断经纪人是否存在 ,不存在则还是从ERP里获取房源
			BBroker broker = bBrokerService.getBroker(str[1]);
			if(house != null && broker != null){
				house.setPublisher(broker);
				return houseDetail(house, "", request, model);
			}else{
				//用于不给该经纪人链接,因为网站上没有改经纪人信息
				model.addAttribute("notExistBrokerUri", "bucunzai");
				return detail(str[0],str[1],request, model);
			}
		}
//		return "house.rent.notexist";
	}
	
	@RequestMapping(value = {"/hzzf/{houseNo}.html"})
	public String detailByHZZF(@PathVariable String houseNo, HttpServletRequest request, Model model){
		return detail(houseNo, request, model);
	}
	/**
	 * 查询房源
	 * @param pathStr 查询房源列表并显示
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/{pathStr}"})
	public String list(@PathVariable String pathStr, HttpServletRequest request, Model model){
		return list(pathStr, null, request, model);
	}
	@RequestMapping(value = {"/hzzf"})
	public String searchHZZF( HttpServletRequest request, Model model){
		return searchHZZF("s1", request, model);
	}
	@RequestMapping(value = {"/hzzf/{pathStr}"})
	public String searchHZZF(@PathVariable String pathStr, HttpServletRequest request, Model model){
		return searchHZZF(pathStr, null, request, model);
	}
	
	/**
	 * 查询杭州租房列表
	 * @param pathStr1
	 * @param pathStr2
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/hzzf/{pathStr1}/{pathStr2}"})
	public String searchHZZF(@PathVariable String pathStr1, @PathVariable String pathStr2, HttpServletRequest request, Model model){
		
		Map<String, String> condition = searchHouseInformationList(model, pathStr1, pathStr2, request);
		
		//杭州租房列表条件
		model.addAttribute("hzzfSearchItems", commonCountyTwoService.getCommonCountyTwoList());
		
		//把上次的搜索参数保存到Model，以便在页面上重新生成各个链接
//		String basePath = (String)request.getAttribute("globalUrl") + PATH;
		String basePath =  PATH + "/hzzf";
		model.addAttribute("basePath", basePath);
		// 对搜索条件进行保存到model里,还需要修改
		model.addAttribute("searchResults", houseRentService.getSearchFieldList(condition, basePath, pathStr1 + "/" + pathStr2));
		
		return "house.rent.list";
	}
	
	/**
	 * 查询房源列表
	 * @param pathStr1	第一级查询参数, a_(\d+)_(\d+):城区商圈; t_(\d+)_(\d+):地铁及站点
	 * @param pathStr2	其他查询参数,
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/{pathStr1}/{pathStr2}"})
	public String list(@PathVariable String pathStr1, @PathVariable String pathStr2, HttpServletRequest request, Model model){
		
		Map<String, String> condition = searchHouseInformationList(model, pathStr1, pathStr2, request);
		
		//把上次的搜索参数保存到Model，以便在页面上重新生成各个链接
//		String basePath = (String)request.getAttribute("globalUrl") + PATH;
		String basePath =  PATH;
		model.addAttribute("basePath", basePath);
		// 对搜索条件进行保存到model里,还需要修改
		model.addAttribute("searchResults", houseRentService.getSearchFieldList(condition, basePath, pathStr1 + "/" + pathStr2));

		return "house.rent.list";
	}
	private String detail( String houseId,  String publisherId, HttpServletRequest request, Model model){
		String requestXML = createQueryHouseRequestXML("GetERPHouseForLease", houseId, publisherId);
		String returnedXML = CallERPPublicCls.CallERPWebser(requestXML);
//		System.out.println(returnedXML);
		HouseRentHouse hr = houseRentService.parseHouseRentHouseXmlToObject(returnedXML);
		if(hr == null)return "house.err.notexist";
		return houseDetail(hr, publisherId, request, model);
	}
	/**
	 * 处理房源详情附件信息
	 * @param house
	 * @param publisherId 只有从ERP系统里查询,才有值
	 * @return
	 */
	private String houseDetail(HouseRentHouse house, String publisherId, HttpServletRequest request, Model model){
		boolean bType = false;
		if(StringUtil.isNotEmpty(publisherId)){ 
			model.addAttribute("fromERP","fromERP");
			bType=true;
		}
		//FIXME 这里的用户对象用的是HiberNate里的，以后废弃Hibernate后必须改成MyBatis的
		PlatMemberInfo member = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		
		String brokerId = request.getParameter("brokerId");
//		if(brokerId == null) brokerId = house.getPublisher().getErpId();
		if(StringUtil.isEmpty(brokerId)) brokerId = house.getPublisherId();
		if(bType) brokerId = publisherId;
		model.addAttribute("brokerId", brokerId);
		
		List<HouseAppraise> appList = new ArrayList<HouseAppraise>();
		//对ERP传过来的评价进行处理
		if(bType){
			if(house.getEvaluations() != null && house.getEvaluations().size() > 0){
				addToHouseAppraiseList(appList, house.getEvaluations());
			}
		}else{
			//appList = houseAppraiseService.findAppraiseListByHouseId(house.getHouseId());
			appList = houseAppraiseService.findAppraiseListByShelvingId(house.getShelvingId());
		}
		//对房源评价进行排序 --->不需要这么排了
//		sortHouseAppraise(appList, brokerId);
		//TODO 下面用于显示该房源发布人,不知道为什么要这么写,直接显示房源经纪人不就好了么？代码块 213-219 228-229
		model.addAttribute("appraiseList", appList);
//		for(HouseAppraise a : appList){
//			if(brokerId.equals(a.getBroker())){
//				model.addAttribute("houseAppraise", a );
//				break;
//			}
//		}
		
		//当短网址访问时,网站上有这套房源,且该经纪人不是发这套房源处理
		if(bType && StringUtils.isNotEmpty(publisherId)){
			BBroker bBroker = bBrokerService.getBroker(publisherId);
			if(bBroker != null)
				house.setPublisher(bBroker);
		}
//		if(bType && house.getPublisher() == null)
//			house.setPublisher(bBrokerService.getBroker(publisherId));
//		else if(house.getPublisher() == null)
//			house.setPublisher(bBrokerService.getBroker(brokerId));
		
		if(member != null){
			//TODO 房源类型 先跟二手房的一样，到时候 其他弄好了之后 ，在进行对他修改
			memberBrowseHistoryService.saveUserBrowseHistory(member.getId(), 0, house.getErpId());
			List<HouseReduceNotice> noticeList = houseReduceNoticeService.getHouseNoticeByMemberAndHouseId(member.getId(), house.getErpId());
			model.addAttribute("noticeResult", noticeList.size() > 0 ? "noticed" : "unnoticed");
		}
		//设备
		if((house.getHouseFurnitures() == null || house.getHouseFurnitures().size()<1) && StringUtil.isNotEmpty(house.getFurniture())){
			List<CommonLiveFacility> furList = houseRentService.parseHouseFurnitures(house.getFurniture());
			house.setHouseFurnitures(furList);
		}
//		HouseAppraise houseAppraise = houseRentService.findHouseAppraiseByBroker(house, brokerId);
//		model.addAttribute("houseAppraise", houseAppraise);

		//把房源图片按要求进行排序
		sortHousePictures(house.getPictures());

		if(house.getCommunity() != null){
			//载入小区最近门店 
			houseCommunityService.loadNearestStores(house.getCommunity());
		}
		//查询小区与医院、学校、地铁站、其他车站间的关联关系  原本是house.getCommunity().getErpId() ,后改成 house.getCommunityId(),防止小区为空时报错
		model.addAttribute("cHMapping",houseCommunityService.findCHMapping(house.getCommunityId()));
		model.addAttribute("cSMapping", houseCommunityService.findCSMapping(house.getCommunityId()));
		model.addAttribute("cStaMapping", houseCommunityService.findCStaMapping(house.getCommunityId()));
		model.addAttribute("cSubMapping", houseCommunityService.findCSubMapping(house.getCommunityId()));
		//查询小区图片
		model.addAttribute("communityPics", houseCommunityService.getHousePictureByCommunityId(house.getCommunityId()));
		
		//增加并获取房源浏览量
		if(!bType){
			Integer browse = HouseBrowseCacheUtil.getInstance().getBrowsed(EnumHouseType.RENT.getValue(), house.getHouseId(), house.getBrowsed());
			model.addAttribute("houseBrowsed", browse);
		}
		
		//载入房源配置信息
		model.addAttribute("furList", commonLiveFacilityService.getHouseFurnitures());
		
//		//载入房源标签
		if(!bType)
			model.addAttribute("tagList", commonFlagService.getHouseTags());
		
		//载入推荐房源("您可以喜欢"的哪些房源)
		HouseRecommendUtil recommandUtil = (HouseRecommendUtil) request.getSession().getAttribute("houseRentHistorys");
		List<HouseRentHouseExt> recommHouseList = houseRentService.findHouseRentRecommend(house, recommandUtil);
		model.addAttribute("recommendList", recommHouseList);
		
		//记录浏览历史
		String shHistoryBespeak = house.getErpId() + "," + new Timestamp(Calendar.getInstance().getTimeInMillis());
		Queue<String> queue = (Queue<String>) request.getSession().getAttribute("rhHistoryBespeakQueue");
		if(queue == null)
			queue = new LinkedList<String>();
		queue.add(shHistoryBespeak);
		if(queue.size() > 50){
			queue.remove();
		}
		
		request.getSession().setAttribute("rhHistoryBespeakQueue", queue);
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		//FIXME: 下面的getCompareString中有调用Hibernate的内容,以后需要重构
		String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
		comparedSHString = comparedSHString==null?"":comparedSHString;
		model.addAttribute("rentHouseCompare", comparedSHString);
		//住房类型
		if(house.getUsage() == null && StringUtil.isNotEmpty(house.getUsageId())){
			house.setUsage(commonUsageService.getCommonUsage(house.getUsageId()));
		}
		
		model.addAttribute("house", house);
		if(!bType){
			//保存浏览分析对象
			houseRentBrowseAnalyseService.save(house, member, clientFlag);
		}
		// 用于顶部菜单栏 搜索
		model.addAttribute("backType", "3");
		//用于房源举报
		model.addAttribute("houseType", 2);
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		if(house.getCommunity() != null)
			houseCommunityService.populateCommunity(house.getCommunity());
		// 对比链接判断 用于compare_history.jsp,开始对比链接
		model.addAttribute("showCompare", "R");
		
		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("出租房");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_DETAIL);
		pageMeta.setHouseTitle(house.getTitle());
		if(house.getCommunity()!=null)
			pageMeta.setCommunityName(house.getCommunity().getCommunityName());
		if(house.getCommunity()!=null && house.getCommunity().getCbdWebsite()!=null)
			pageMeta.setCbdName(house.getCommunity().getCbdWebsite().getName());
		if(house.getCommunity()!=null && house.getCommunity().getCbd()!=null && house.getCommunity().getCbd().getCounty()!=null)
			pageMeta.setCountryName(house.getCommunity().getCbd().getCounty().getCountyName());
		model.addAttribute(PageMetaBean.BEAN_NAME_MODULE, pageMeta);
		
		return "house.rent.detail";
	}
	
	/**
	 * 把房源评价按要求进行排序
	 * @param appraises	房源评价列表
	 * @param brokerId  房源发布人ID
	 */
	private void sortHouseAppraise(List<HouseAppraise> appraises, String publisherId ){
		for(HouseAppraise a : appraises){
			if(publisherId.equals(a.getBroker())){
				appraises.remove(a);
				appraises.add(0,a);
				break;
			}
		}
	}
	
	private void sortHousePictures(List<HousePicture> housePics){
		for(HousePicture pic : housePics){
			if("户型图".equals(pic.getPictureComment())){
				housePics.remove(pic);
				housePics.add(0, pic);
				break;
			}
		}
	}

	/**
	 * 查询房源列表基本信息
	 * @param model
	 * @param pathStr1
	 * @param pathStr2
	 */
	private Map<String, String> searchHouseInformationList(Model model,String pathStr1, String pathStr2, HttpServletRequest request){
		// 对参数进行解析
		Map<String, String> condition = parseQueryCondition(handleCondition(pathStr1,pathStr2), null);

		Integer pageNum = 1;
		if (condition.containsKey("pageNum")) {
			if (StringUtil.isNumeric(condition.get("pageNum"))) {
				pageNum = Integer.parseInt(condition.get("pageNum"));
			}
		}

		//TODO 相似楼层 是用?加参数的形势,会导致第二次查询丢失该条件
		String floor = (String)request.getParameter("floor");
		String vervicalLocation = (String)request.getParameter("vervicalLocation");
		if(StringUtil.isNotEmpty(floor))
			condition.put("floor", floor.replace("@", "_"));
		if(StringUtil.isNotEmpty(vervicalLocation))
			condition.put("vervicalLocation", vervicalLocation);
		
		Map<String, String> orderFields = new HashMap<String, String>();
		if (condition.containsKey("orderby")) {
			orderFields.put(condition.get("orderby"), "asc");
		}

		List<HouseRentHouseExt> houseList = houseRentService.getHouseList(condition, orderFields, pageNum);
		model.addAttribute("houseList", houseList);

		PageInfo<HouseRentHouseExt> page = new PageInfo<HouseRentHouseExt>(houseList);
		model.addAttribute("pageBean", page);

		// 查询搜索条件
		getSearchItem(model, "3");

//		//取出租屋内设施数据
		for(HouseRentHouseExt h: houseList){
			if(StringUtil.isNotEmpty(h.getFurniture())){
				List<CommonLiveFacility> furList = houseRentService.parseHouseFurnitures(h.getFurniture());
				h.setHouseFurnitures(furList);
			}
		}
		
//		取房源标签定义数据
//		model.addAttribute("tagList", commonFlagService.getHouseTags());

		Map<String, String> params = HouseQueryStrParser.parseQueryCondition(pathStr1, pathStr2);
		// 如果未指定排序方式，则按默认条件排序
		if (!params.containsKey("s"))
			params.put("s", "1");
		model.addAttribute("params", params);
		// 用于顶部菜单栏 搜索
		model.addAttribute("backType", "3");
		// 对比链接判断 用于compare_history.jsp,开始对比链接
		model.addAttribute("showCompare", "R");
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		
		
		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("出租房");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_LIST);
		HouseQueryStrParser parser = new HouseQueryStrParser();
		String priceDesc = parser.getPriceDescriptionByCondition(condition);
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

		return condition;
	}
}
