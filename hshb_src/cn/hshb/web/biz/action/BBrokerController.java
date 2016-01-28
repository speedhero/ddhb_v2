/**
 * 
 */
package cn.hshb.web.biz.action;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.QueryCondition;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.BBrokeranswered;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.HouseCbdExpert;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExpert;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExt;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.PageAccessQuantity;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonFlagService;
import cn.hshb.web.biz.service.CommonFooterMenuService;
import cn.hshb.web.biz.service.CommonLiveFacilityService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.biz.service.PageAccessQuantityService;
import cn.hshb.web.common.util.ShortUrlUtil;
import cn.hshb.web.common.util.StringUtil;

/**
 * 
 * @author ShengYoufu
 */
@Controller("broker")
@RequestMapping(value={"/broker", "/rentbroker"})
public class BBrokerController extends HouseBaseController {
	private static final Log log = LogFactory.getLog(BBrokerController.class);
	private static final Integer PAGE_SIZE = 15;	//每页房源显示的数量 
	@Autowired
	private BBrokerService bBrokerService;
	
	@Autowired
	private CommonLiveFacilityService commonLiveFacilityService;
	
	@Autowired
	private HousePictureService housePictureService;
	
	@Autowired
	private CommonFlagService commonFlagService;
	
	@Autowired
	private PageAccessQuantityService pageAccessQuantityService;

	@Autowired
	private HouseSecondhandHouseService houseSecondhandHouseService;
	
	@Autowired
	private HouseRentService houseRentService;
	
	@Autowired
	private CommonFooterMenuService commonFooterMenuService;
	
	@RequestMapping(value = { "/{brokerId}.html" })
	public String brokerDetail(@PathVariable String brokerId, Model model, HttpServletRequest request) {
		return brokerDetail(brokerId, "", model, request);
	}

	/**
	 * 用于经纪人房源刷新--->翻页
	 * @param brokerId	经纪人Id
	 * @param pathStr	搜索的条件：主要是翻页功能
	 * @param model		
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/{brokerId}.html/{pathStr}"})
	public String brokerDetail(@PathVariable String brokerId, @PathVariable String pathStr, Model model, HttpServletRequest request){

		BBroker broker = (BBroker) bBrokerService.getBroker(brokerId);
		model.addAttribute("broker", broker);
		
		//处理经纪人详情页背景图设置
		bBrokerService.saveBackgroundImage(broker);
		
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(6);
		//TODO returnString 需要修改.在页面上显示的json数据为查询字段的条件
		String returnString = "[]";
		
		//查询条件
		Map<String, String> condition = parseQueryCondition(pathStr, "");
		//因为就经纪人页面里用到publisher_id,所以就不添加到解析正则里面去了
		condition.put("publisher_id", brokerId);
		condition.put("orderby", "sortIndex");
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
		
		String type = "1";
//		List<HouseSecondHandHouse> houseSeconds = this.bBrokerService.findHouseSecondByBrokerId(brokerId, cutPageBean.getPageSize(), cutPageBean.getCurrentPage());
		List<HouseSecondHandHouse> houseSeconds = houseSecondhandHouseService.getHouseList(condition, orderFields, pageNum, PAGE_SIZE);
		if(houseSeconds!=null && houseSeconds.size()>0){
			//如果有二手房源，说明经纪人是买卖经纪人
			type = "1";
			model.addAttribute("pageBean", houseSeconds);
			
//			//取房源标签定义数据
			for(HouseSecondHandHouse h: houseSeconds){
				List<CommonFlag> tagList = houseSecondhandHouseService.parseHouseTag(h.getTags());
				h.setHouseTags(tagList);
			}
			
			//获取房源封面图片
			housePictureService.getCorrespondingSHPictures(houseSeconds);

			//取房源评价和房源收藏记录
//			this.judgesecond(request, houseSeconds);
			
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
			model.addAttribute("secondHouseCompare", comparedSHString==null ? "" : comparedSHString);
			
			String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
			model.addAttribute("secondHouseHistory", historySHString==null ? "" : historySHString);
			
			model.addAttribute("showCompare", "S");
			PageInfo<HouseSecondHandHouse> page = new PageInfo<HouseSecondHandHouse>(houseSeconds);
			model.addAttribute("pageBean", page);
			model.addAttribute("houseList", houseSeconds);
			model.addAttribute("basePath", "broker/" + brokerId + ".html");
		}else{
//			List<HouseRentHouse> houserents = bBrokerService.findHouseRentByBrokerId(brokerId, cutPageBean.getPageSize(), cutPageBean.getCurrentPage());
			List<HouseRentHouseExt> houserents = houseRentService.getHouseList(condition, orderFields, pageNum, PAGE_SIZE);
			if(houserents!=null && houserents.size()>0){
				//如果有租赁房源，说明经纪人是租赁经纪人
				type = "2";
				model.addAttribute("pageBean", houserents);
				
				//出租屋内设施
				List<CommonLiveFacility> furList = commonLiveFacilityService.getHouseFurnitures();
				model.addAttribute("furList", furList);
				
				//取房源封面照片
				housePictureService.getCorrespondingRHPictures(houserents);
				
				//取房源评价和房源收藏记录
//				this.judgerent(request, houserents);

				String clientFlag = ClientFlagUtil.getClientFlag(request);
				String comparedRHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
				if (comparedRHString == null) {
					comparedRHString = "";
				}
				model.addAttribute("rentHouseCompare", comparedRHString);
				String historyRHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "rentHouseHistory");
				if (historyRHString == null) {
					historyRHString = "";
				}
				model.addAttribute("rentHouseHistory", historyRHString);
				model.addAttribute("showCompare", "R");		
				PageInfo<HouseRentHouseExt> page = new PageInfo<HouseRentHouseExt>(houserents);
				model.addAttribute("pageBean", page);
				model.addAttribute("houseList", houserents);
				model.addAttribute("basePath", "broker/" + brokerId + ".html");
			}
		}

		//取经纪人页面浏览量
		PageAccessQuantity pageAccessQuantity = pageAccessQuantityService.getAccessQuantity(broker.getErpId(), "经纪人详情页");
		//更新页面浏览量
		if(pageAccessQuantity != null){
			pageAccessQuantityService.updateAccessQuantity(pageAccessQuantity);
		}else{
			pageAccessQuantity = pageAccessQuantityService.insertAccessQuantity( broker.getErpId(), "经纪人详情页");
		}
		DecimalFormat df = new DecimalFormat("00000000");
		String count = df.format(pageAccessQuantity.getAccessQuantity());
		List<String> accessQuantity = new ArrayList<String>();
		for(int ii=0; ii<count.length(); ii++){
			accessQuantity.add(String.valueOf(count.charAt(ii)));
		}
		model.addAttribute("accessQuantityList",accessQuantity);

		
		//经纪人问答被接受的答案数
		int broderAnseredCount = this.bBrokerService.getAcceptedAnswereCountByBrokerId(broker.getErpId());
		model.addAttribute("broderAnseredCount", broderAnseredCount);
		
		//经纪人问答被接受的答案列表
		List<BBrokeranswered> broderAnseredList = this.bBrokerService.getBrokerAnswerConditionBrokerErpId(broker.getErpId());
		model.addAttribute("broderAnseredList",broderAnseredList);

		//经纪人熟悉的商圈
		List<HouseCbdExpert> cbdExportList = bBrokerService.getCBDExpertByBrokerId(brokerId);
		model.addAttribute("cbdExportList", cbdExportList);

		//经纪人熟悉的小区
		List<HouseCommunityExpert> communityList = this.bBrokerService.getCommunityExpertByBrokerId(brokerId);
		model.addAttribute("communityList", communityList);
		
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		
		//返回经纪人 店址
//		model.addAttribute("broderWebShop", "http://www.hshb.cn/broker/brokerDetail/" + broker.getErpId() + "/" + type);
//		model.addAttribute("broderWebShop", "http://www.hshb.cn/broker/" + broker.getErpId() + ".html");
		String brokerHomePageUrl = "http://www.hshb.cn/broker/" + broker.getErpId() + ".html";
		brokerHomePageUrl = ShortUrlUtil.getShortURL(brokerHomePageUrl);
		model.addAttribute("broderWebShop", brokerHomePageUrl);
		
		model.addAttribute("housetype", type);
		model.addAttribute("jsonString", returnString);
		return "new.broker.detail.show";
	}
	@RequestMapping(params = { "actionMethod=brokerHouse" })
	public String brokerHouse(@RequestParam("brokerId") String brokerId, @RequestParam("housetype") String type,
			Model model, HttpServletRequest request) {
		BBroker broker = (BBroker)bBrokerService.getBroker(brokerId);
		model.addAttribute("broker", broker);
		
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(6);
		String returnString = null;
		
		if ("2".equals(type)) {
			List<HouseRentHouse> houseList = bBrokerService.findHouseRentByBrokerId(brokerId, cutPageBean.getPageSize(), cutPageBean.getCurrentPage());

			//生成查询条件
//			returnString = this.searchService.loadSearchMenuByModuleName("9");
			//载入房源评价和 房源收藏记录
//			this.judgerent(request, (List<HouseRent>) pageBean.getDataList());

			model.addAttribute("pageBean", houseList);
			
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedRHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
			model.addAttribute("rentHouseCompare", comparedRHString==null? "" : comparedRHString);
			
			String historyRHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "rentHouseHistory");
			model.addAttribute("rentHouseHistory", historyRHString==null? "" : historyRHString);
			model.addAttribute("showCompare", "R");
		} else {
			List<HouseSecondHandHouse> houseList = bBrokerService.findHouseSecondByBrokerId(brokerId, cutPageBean.getPageSize(), cutPageBean.getCurrentPage());
			//生成查询条件
//			returnString = this.searchService.loadSearchMenuByModuleName("8");
			//载入房源评价和 房源收藏记录
//			this.judgesecond(request, (List<HouseSecond>) pageBean.getDataList());
			
			model.addAttribute("pageBean", houseList);

			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
			model.addAttribute("secondHouseCompare", comparedSHString==null? "" : comparedSHString);
			String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
			model.addAttribute("secondHouseHistory", historySHString==null? "" : historySHString);
			model.addAttribute("showCompare", "S");
		}
		model.addAttribute("jsonString", returnString);
		model.addAttribute("housetype", type);
		return "broker.house.search.show";
	}

	private void validateQuestion(QuestionStategy questionStategy, Errors errors, HttpServletRequest request,
			String questionstategySubType) {
		if (StringUtil.isEmpty(questionStategy.getTitle())) {
			errors.reject("问题标题必须录入.");
		} else if (questionStategy.getTitle().getBytes(Charset.forName("GBK")).length > 80) {
			errors.reject("问题标题不能超过80个字.");
		}
		if (StringUtil.isEmpty(questionStategy.getContent())) {
			errors.reject("问题内容必须录入。");
		} else if (questionStategy.getContent().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("问题内容不能超过400个字。");
		}
		if (questionstategySubType == null || questionstategySubType.equals("0")) {
			errors.reject("请选择问题类型。");
		}
		if (StringUtil.isEmpty(questionStategy.getVerifyCode())) {
			errors.reject("请输入验证码。");
		} else {
			String sessId = request.getSession().getId();
			Boolean flag = CaptchaServiceSingleton.getInstance().validateResponseForID(sessId,
					questionStategy.getVerifyCode().toLowerCase());
			if (!flag) {
				errors.reject("验证码错误。");
			}
		}
	}	
}
