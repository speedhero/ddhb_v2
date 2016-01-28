/**
 * 
 */
package cn.hshb.web.biz.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.HouseLoadCalculator;
import com.huatek.hbwebsite.util.UserCompareUtil;

import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonLoanRate;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseReduceNotice;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonDecorationTypeService;
import cn.hshb.web.biz.service.CommonFlagService;
import cn.hshb.web.biz.service.CommonFooterMenuService;
import cn.hshb.web.biz.service.CommonLoanRateService;
import cn.hshb.web.biz.service.CommonUsageService;
import cn.hshb.web.biz.service.HouseAppraiseService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HouseReduceNoticeService;
import cn.hshb.web.biz.service.HouseSecondBrowseAnalyseService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.biz.service.MemberBrowseHistoryService;
import cn.hshb.web.biz.service.SearchItemService;
import cn.hshb.web.biz.util.HouseBrowseCacheUtil;
import cn.hshb.web.biz.util.HouseQueryStrParser;
import cn.hshb.web.biz.util.HouseRecommendUtil;
import cn.hshb.web.biz.util.PageMetaBean;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.partner.baidu.common.StringUtil;


/**
 * 二手房Controller
 * @author Administrator
 *
 */
@Controller("sale")
@RequestMapping(value = {"/chushou"} )
public class HouseSecondHandHouseController extends HouseBaseController {
	
	private static final Log log = LogFactory.getLog(HouseSecondHandHouseController.class);
	private static final String PATH = "chushou";

	@Autowired
	private HouseSecondhandHouseService houseSecondhandHouseService;

	@Autowired
	private SearchItemService searchItemService;

	@Autowired
	private CommonFlagService commonFlagService;

	@Autowired
	private CommonLoanRateService commonLoanRateService;
	
	@Autowired
	private HouseAppraiseService houseAppraiseService;
	
	@Autowired
	private MemberBrowseHistoryService memberBrowseHistoryService;
	
	@Autowired
	private HouseReduceNoticeService houseReduceNoticeService;

	@Autowired
	private HouseCommunityService houseCommunityService;

	@Autowired 
	private HouseSecondBrowseAnalyseService houseSecondBrowseAnalyseService;

	@Autowired
	private BBrokerService bBrokerService;
	
	@Autowired
	private CommonFooterMenuService commonFooterMenuService;
	
	@Autowired
	private CommonUsageService commonUsageService;	//住房类型
	
	@Autowired
	private CommonDecorationTypeService commonDecorationTypeService;//装修
	
	/** 以下是测试方法 **/
	@RequestMapping(value= {"index"})
	public String index(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		return index(null, null, request, model);
	}
	@RequestMapping(value= {"index/{name}"})
	public String index(
			@PathVariable String name,
			HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		return index(name, null, request, model);
	}
	@RequestMapping(value= {"index/{name}/{page}" })
	public String index(
			@PathVariable String name,
			@PathVariable Integer page,
			HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		log.debug("Hello.");
		return "";
	}
	/** 测试方法结束 **/

	/**
	 * 查询房源
	 * @param pathStr 如果是房源编号，则显示房源详情，否则查询房源列表并显示
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {"/{houseNo}.html"})
	public String detail(@PathVariable String houseNo, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		HouseSecondHandHouse house = houseSecondhandHouseService.findHouseByHouseId(houseNo);
		if(house!=null){
			//如果按房源编号能找到房源，则跳到房源详情页
			return houseDetail(house, "", request, model);
		}else{
			model.addAttribute("fromERP", "1");
			if(!houseNo.contains("_"))return "house.err.notexist";
			String[] str = houseNo.split("_");
			if(str.length != 2)return "house.err.notexist";
			house = houseSecondhandHouseService.findHouseByHouseId(str[0]);
			//判断经纪人是否存在 ,不存在则还是从ERP里获取房源
			BBroker broker = bBrokerService.getBroker(str[1]);
			if(house != null && broker != null){
				house.setPublisher(broker);
				return houseDetail(house, "", request, model);
			}
			//用于不给该经纪人链接,因为网站上没有改经纪人信息
			model.addAttribute("notExistBrokerUri", "bucunzai");
			return detail(str[0],str[1],request, model);
//			return "house.sale.notexist";
		}
	}	
	private String detail(String houseId, String brokerId, HttpServletRequest request, Model model){
		String requestXML = createQueryHouseRequestXML("GetERPHouseForSale", houseId, brokerId);
		String returnedXML = CallERPPublicCls.CallERPWebser(requestXML);
//		System.out.println(returnedXML);
		HouseSecondHandHouse hr = houseSecondhandHouseService.parseHosueSecondHouseXmlToObject(returnedXML);
		if(hr == null) return "house.err.notexist";
		return houseDetail(hr, brokerId, request, model);
	}
	/**
	 * 未输入条件，则表示查询列表
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value={""})
	public String list(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		return list(null, null, request, model);
	}

	/**
	 * 查询房源
	 * @param pathStr 如果是房源编号，则显示房源详情，否则查询房源列表并显示
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {"/{pathStr}"})
	public String list(@PathVariable String pathStr, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
//		log.debug("首页输入1+" + pathStr);
//		log.debug("转换后的pathStr:" + pathStr);
		return list(pathStr, null, request, model);
	}

	/**
	 * 查询房源列表
	 * @param pathStr1	第一级查询参数，a_(\d+)_(\d+): 城区商圈； t_(\d+)_(\d+): 地铁及站点
	 * @param pathStr2	其他查询参数，格式:
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {"/{pathStr1}/{pathStr2}"})
	public String list(@PathVariable String pathStr1, @PathVariable String pathStr2, HttpServletRequest request, Model model) 
			throws UnsupportedEncodingException {
		
//		Long st = System.currentTimeMillis();

		Map<String, String> condition = parseQueryCondition(pathStr1, pathStr2);

//		System.out.println("time1: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();
		
		Integer pageNum = 1;
		if(condition.containsKey("pageNum")){
			if(StringUtil.isNumeric(condition.get("pageNum"))){
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
		if(condition.containsKey("orderby")){
			orderFields.put(condition.get("orderby"), "asc");
		}

//		System.out.println("time2: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		List<HouseSecondHandHouse> houseList = houseSecondhandHouseService.getHouseList(condition, orderFields, pageNum);
		model.addAttribute("houseList", houseList);

//		System.out.println("time3: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		PageInfo<HouseSecondHandHouse> page = new PageInfo<HouseSecondHandHouse>(houseList);
		model.addAttribute("pageBean", page);

		//查询搜索条件
		getSearchItem(model, "2");
		
//		System.out.println("time4: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		
//		//取房源标签定义数据
		for(HouseSecondHandHouse h: houseList){
			List<CommonFlag> tagList = houseSecondhandHouseService.parseHouseTag(h.getTags());
			h.setHouseTags(tagList);
		}

//		System.out.println("time5: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		//把上次的搜索参数保存到Model，以便在页面上重新生成各个链接
//		String basePath = (String)request.getAttribute("globalUrl") + PATH;
		String basePath =  PATH;
		model.addAttribute("basePath", basePath);

		// 对搜索条件进行保存到model里,还需要修改
		model.addAttribute("searchResults", houseSecondhandHouseService.getSearchFieldList(condition, basePath, pathStr1 + "/" + pathStr2));
		
//		System.out.println("time6: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		
		Map<String, String> params = HouseQueryStrParser.parseQueryCondition(pathStr1, pathStr2);
		if(!params.containsKey("s")) params.put("s", "1"); //如果未指定排序方式，则默认按默认条件排序
		model.addAttribute("params", params);

//		System.out.println("time7: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		//用于顶部菜单栏 搜索
		model.addAttribute("backType", "2");
		//对比链接判断 用于compare_history.jsp,开始对比链接
		model.addAttribute("showCompare","S");
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());

//		System.out.println("time8: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("二手房");
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
		
//		System.out.println("time9: "+(System.currentTimeMillis() - st));
//		st = System.currentTimeMillis();

		return "house.sale.list";
	}
	
	/**
	 * 处理房源详情附件信息
	 * @param house
	 * @return
	 */
	private String houseDetail(HouseSecondHandHouse house, String publisherId, HttpServletRequest request, Model model){
		boolean bType = false;
		if(StringUtil.isNotEmpty(publisherId)){
			//用于隐藏界面上一些功能
			model.addAttribute("fromERP","fromERP");
			bType = true;
		}
		String brokerId = request.getParameter("brokerId");
		//如果publisher为null，则会报错
		//if(brokerId==null) brokerId = house.getPublisher().getErpId();
		if(StringUtil.isEmpty(brokerId)) brokerId = house.getPublisherId();
		if(bType) brokerId = publisherId;
		model.addAttribute("brokerId", brokerId);
		//获取该房源评价总数
		List<HouseAppraise> appList = new ArrayList<HouseAppraise>(); 
		//对ERP传过来的评价进行处理
		if(bType){
			if(house.getEvaList()!= null && house.getEvaList().size() > 0){
				addToHouseAppraiseList(appList, house.getEvaList());
			}
		}else {
			appList = houseAppraiseService.findAppraiseListByHouseId(house.getHouseId());
		}
		//对房源评价进行排序,就是把发布该房源的人的评价排在第一位 --->不用按这个排序了
//		sortHouseAppraise(appList, brokerId);
		model.addAttribute("appraiseList", appList);

		//TODO 下面用于显示该房源发布人,不知道为什么要这么写,直接显示房源经纪人不就好了么？237-244 258-259
//		HouseAppraise ha = null;
//		for(HouseAppraise a: appList){
//			if(brokerId.equals(a.getBroker())){
//				model.addAttribute("houseAppraise", a);
//				ha = a;
//				break;
//			}
//		}
		//当短网址访问时,网站上有这套房源,且该经纪人不是发这套房源处理
//		if(bType && StringUtils.isNotEmpty(publisherId)){
//			BBroker bBroker = bBrokerService.getBroker(publisherId);
//			if(bBroker != null)
//				house.setPublisher(bBroker);
//		}
		if(!bType && house.getPublisher() == null){
			if(StringUtil.isNotEmpty(house.getPublisherId())){
				house.setPublisher(bBrokerService.getBroker(house.getPublisherId()));
			}else{
				house.setPublisher(bBrokerService.getBroker(brokerId));
			}
		}
			
		//FIXME: 这里的用户对象用的是Hibernate里的，以后废弃Hibernate后要记得改成MyBatis的
		PlatMemberInfo member = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");

		if(member!=null){
			memberBrowseHistoryService.saveUserBrowseHistory(member.getId(), 0, house.getErpId());
			List<HouseReduceNotice> noticeList =  houseReduceNoticeService.getHouseNoticeByMemberAndHouseId(member.getId(), house.getErpId());
			model.addAttribute("noticeResult", noticeList.size()>0 ? "noticed" : "unnoticed");
		}

//			HouseAppraise houseAppraise = houseSecondhandHouseService.findHouseAppraiseByBroker(house, brokerId);
//			model.addAttribute("houseAppraise", houseAppraise);

		//把房源图片按要求进行排序
		sortHousePictures(house.getPictures());

		if(house.getCommunity() != null){
			//载入小区最近门店
			houseCommunityService.loadNearestStores(house.getCommunity());
		}
		
		//查询小区与医院、学校、地铁站、其他车站间的关联关系 	把house.getCommunity().getErpId()改成了house.getCommunityId() 
		model.addAttribute("cHMapping", houseSecondhandHouseService.findCHMapping(house.getCommunityId()));
		model.addAttribute("cSMapping", houseSecondhandHouseService.findCSMapping(house.getCommunityId()));
		model.addAttribute("cStaMapping", houseSecondhandHouseService.findCStaMapping(house.getCommunityId()));
		model.addAttribute("cSubMapping", houseSecondhandHouseService.findCSubMapping(house.getCommunityId()));		

		//增加并获取房源浏览量
		if(!bType){
			Integer browse = HouseBrowseCacheUtil.getInstance().getBrowsed( EnumHouseType.SALE.getValue() , house.getHouseId(), house.getBrowsed());
			model.addAttribute("houseBrowsed", browse);
		}
		//解析房源的标签列表
		//if(!bType){
			List<CommonFlag> tagList = houseSecondhandHouseService.parseHouseTag(house.getTags());
			house.setHouseTags(tagList);
//		}
		//载入推荐房源（“您可以喜欢”的那些房源）
		HouseRecommendUtil recommandUtil = (HouseRecommendUtil) request.getSession().getAttribute("houseSecondHistorys");
		List<HouseSecondHandHouse> recommHouseList = houseSecondhandHouseService.findHouseSecondRecommend(house, recommandUtil);
		model.addAttribute("recommendList", recommHouseList);

		List<CommonLoanRate> rateList = commonLoanRateService.getCommonLoanRates();
		if(rateList != null && rateList.size() > 0){
			//TODO 下面这isNumeric是判断否是int类型的数字 ,当数字带有小数点时,则返回false,先注释掉了
//			if(StringUtil.isNumeric(rateList.get(0).getRateVal())){
			try{
			Double rate = Double.parseDouble(rateList.get(0).getRateVal());
			Double loanAssets = HouseLoadCalculator.getInstance().calculateMoneyPerMonth(
					house.getPrice() * HouseLoadCalculator.LOAN_TO_VALUE_RATIO/10D, 
					HouseLoadCalculator.LOAN_MONTHS, 
					rate / 100.0D / 12.0D);
				
				model.addAttribute("loanAssets", loanAssets);				
//			}
			}catch(Exception ex){
				log.error("rateList.get(0).getRateVal()值不为数字,HouseSecondHandHouseController-->280行", ex);
			}
		}
		if(!model.containsAttribute("loanAssets")){
			model.addAttribute("loanAssets", "暂无");
		}

		model.addAttribute("loanMonths", HouseLoadCalculator.LOAN_MONTHS);	//月利率
		model.addAttribute("loanRatios", HouseLoadCalculator.LOAN_TO_VALUE_RATIO);	//贷款利率
		
		//记录浏览历史
		String shHistoryBespeak = house.getErpId() + "," + new Timestamp(Calendar.getInstance().getTimeInMillis());
		Queue<String> queue = (Queue<String>) request.getSession().getAttribute("shHistoryBespeakQueue");
		if (queue == null) {
			queue = new LinkedList<String>();
		}
		queue.add(shHistoryBespeak);
		if (queue.size() > 50) {
			queue.remove();
		}
		//request.getSession().setAttribute("shHistoryBespeakQueue", queue);
		
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		//FIXME: 下面的getCompareString中有调用 Hibernate的内容，以后需要重构
		String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
		comparedSHString = comparedSHString==null?"":comparedSHString;
		model.addAttribute("secondHouseCompare", comparedSHString);
		
		//住房类型
		if(house.getUsage() == null && StringUtil.isNotEmpty(house.getUsageId())){
			house.setUsage(commonUsageService.getCommonUsage(house.getUsageId()));
		}
		//装修
		if(house.getDecoration() == null && StringUtil.isNotEmpty(house.getDecorationId())){
			house.setDecoration(commonDecorationTypeService.getDecorationType(house.getDecorationId()));
		}
		if(!bType){
			//保存浏览分析对象
			houseSecondBrowseAnalyseService.save(house, member, clientFlag);
		}
		model.addAttribute("house", house);
		// 用于顶部菜单栏 搜索
		model.addAttribute("backType", "2");
		//用于房源举报
		model.addAttribute("houseType", 1);
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		//对比链接判断 用于compare_history.jsp,开始对比链接
		model.addAttribute("showCompare","S");
		
		
		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("二手房");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_DETAIL);
		pageMeta.setHouseTitle(house.getTitle());
		if(house.getCommunity()!=null)
			pageMeta.setCommunityName(house.getCommunity().getCommunityName());
		if(house.getCommunity()!=null && house.getCommunity().getCbdWebsite()!=null)
			pageMeta.setCbdName(house.getCommunity().getCbdWebsite().getName());
		if(house.getCommunity()!=null && house.getCommunity().getCbd()!=null && house.getCommunity().getCbd().getCounty()!=null)
			pageMeta.setCountryName(house.getCommunity().getCbd().getCounty().getCountyName());
		model.addAttribute(PageMetaBean.BEAN_NAME_MODULE, pageMeta);		

		return "house.sale.detail";
	}

	/**
	 * 把房源评价按要求进行排序 
	 * @param appraises		房源评价列表
	 * @param publisherId	房源发布人ID
	 */
	private void sortHouseAppraise(List<HouseAppraise> appraises, String publisherId){
//		for(HouseAppraise a: appraises){
//			if(publisherId.equals(a.getBroker())){
//				appraises.remove(a);
//				appraises.add(0, a);
//				break;
//			}
//		}
		for(int i=0; i<appraises.size(); i++){
			if(publisherId.equals(appraises.get(i).getBroker())){
				HouseAppraise ha = appraises.get(i);
				appraises.set(i, appraises.get(0));
				appraises.set(0, ha);
			}
		}
	}
	
	/**
	 * 从图片集中，把户型图放到第一个位置
	 * Adding to 20150624 hejianbo
	 * @param HousePic
	 */
	private void sortHousePictures(List<HousePicture> housePics){
		/* 这种方法对于本功能效率比较低，不用，放在这里做个排序的例子
        Collections.sort(housePics,new Comparator<HousePicture>(){
            @Override  
            public int compare(HousePicture pic1, HousePicture pic2) {
            	if("户型图".equals(pic2.getPictureName())){
            		return 1;	//返回-1： pic1排前，返回0:不调整顺序 ，返回1：pic2排前
            	}
            }
        });
        */
		if(housePics==null) return;
        for(HousePicture pic: housePics){
        	if("户型图".equals(pic.getPictureName())){
        		housePics.remove(pic);
        		housePics.add(0, pic);
        		break;
        	}
        }
	}
}
