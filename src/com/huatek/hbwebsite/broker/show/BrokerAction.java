package com.huatek.hbwebsite.broker.show;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hshb.web.house.enums.EnumHouseType;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.broker.service.BrokerService;
import com.huatek.hbwebsite.common.entity.BroderAnsered;
import com.huatek.hbwebsite.common.entity.CBDExport;
import com.huatek.hbwebsite.common.entity.CommunityExpert;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.PageAccessQuantity;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import com.huatek.hbwebsite.common.entity.QuestionStrategySubtype;
import com.huatek.hbwebsite.common.entity.QuestionStrategyType;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.GsonUtil;
import com.huatek.hbwebsite.util.HouseListPictureUtil;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

@Controller
@RequestMapping({ "/broker.show" })
public class BrokerAction {
	@Autowired
	BrokerService brokerService;
	@Autowired
	HouseSecondService houseSecondService;
	@Autowired
	PlatCollectionService platCollectionService;
	@Autowired
	SearchService searchService;
	@Autowired
	RentService rentService;
	private static final Logger LOGGER = Logger.getLogger(BrokerAction.class);

	@RequestMapping(params = { "actionMethod=brokerDetail" })
	public String brokerDetail(@RequestParam("brokerId") String brokerId, @RequestParam("housetype") String type,
			Model model, HttpServletRequest request) {
		Broker broker = (Broker) this.brokerService.getObjectById(Broker.class, brokerId);
		List<CBDExport> cbdExportList = this.brokerService.getCBDExportByBrokerId(brokerId);
		//处理经纪人详情页背景图设置
		brokerService.saveBackgroundImage(broker);
		
		model.addAttribute("broker", broker);
		model.addAttribute("cbdExportList", cbdExportList);
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(6);
		String returnString = null;
		if ("2".equals(type)) {
			List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, HouseRent.class);
			CutPageBean pageBean = this.brokerService.findHouseRentByBrokerId(brokerId, cutPageBean, commonList);
			returnString = this.searchService.loadSearchMenuByModuleName("9");
			List<HouseRent> houserents = (List<HouseRent>) pageBean.getDataList();
			if (houserents != null) {
				for (HouseRent houseRent : houserents) {
					String[] furArr = houseRent.getShowFunitures().split(",");
					List<String> furIdList = new ArrayList<String>();
					for (int i = 1; i < furArr.length; ++i) {
						furIdList.add(furArr[i]);
					}
					houseRent.setFurIdList(furIdList);
				}
			}
			List<Furniture> furList = this.rentService.getFurList();
			model.addAttribute("furList", furList);
			HouseListPictureUtil.getCorrespondingRHPictures(houserents, this.houseSecondService);
			this.judgerent(request, (List<HouseRent>) pageBean.getDataList());
			model.addAttribute("pageBean", this.addRentPicUrl(pageBean));
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
		} else {
			List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, HouseSecond.class);
			CutPageBean pageBean = this.brokerService.findHouseSecondByBrokerId(brokerId, cutPageBean, commonList);
			returnString = this.searchService.loadSearchMenuByModuleName("8");
			List<HouseSecond> houseSeconds = (List<HouseSecond>) pageBean.getDataList();
			if (houseSeconds != null) {
				for (HouseSecond house : houseSeconds) {
					String tagsStr = house.getShowTags();
					String[] tagArr = tagsStr.split(",");
					List<String> tagIdList = new ArrayList<String>();
					for (int j = 1; j < tagArr.length; ++j) {
						tagIdList.add(tagArr[j]);
					}
					house.setTagIdList(tagIdList);
					
					//解析房源标签 
					house.setHouseTags(houseSecondService.parseHouseTag(house.getTags()));
				}
			}
			
			HouseListPictureUtil.getCorrespondingSHPictures(houseSeconds, this.houseSecondService);
			this.judgesecond(request, (List<HouseSecond>) pageBean.getDataList());
			model.addAttribute("pageBean", this.addSecondPicUrl(pageBean));
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
			if (comparedSHString == null) {
				comparedSHString = "";
			}
			model.addAttribute("secondHouseCompare", comparedSHString);
			String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
			if (historySHString == null) {
				historySHString = "";
			}
			model.addAttribute("secondHouseHistory", historySHString);
			model.addAttribute("showCompare", "S");
		}
		List<CommunityExpert> communityList = this.brokerService.getCommunityByBrokerId(brokerId);
		
		//Add by 20150617 
		//页面访问量
		PageAccessQuantity pageAccessQuantity = this.brokerService.getAccessQuantity(broker.getErpId());
		pageAccessQuantity.setAccessQuantity(pageAccessQuantity.getAccessQuantity()+1);
		String count = String.valueOf(pageAccessQuantity.getAccessQuantity());
		List<String> accessQuantity = new ArrayList<String>();
		//为了便于在经纪人详情页显示 店铺访问量 固定显示8位
		int location = 8 - count.length();
		location = location < 0 ? 0 : location;
		for(int i = 1;i <= location; i++){
			accessQuantity.add("0");
		}
		for(int i = 1 ; i <= count.length(); i++){
			accessQuantity.add(count.substring(i-1, i));
		}
		model.addAttribute("accessQuantityList",accessQuantity);
		
		//计数，被接受的条数
		int broderAnseredCount = this.brokerService.getBrokerAnsweredIsAccept(broker.getErpId());
		model.addAttribute("broderAnseredCount", broderAnseredCount);
		//经纪人问答
		List<BroderAnsered> broderAnseredList = this.brokerService.getBrokerAnswerConditionBrokerErpId(broker.getErpId());
		model.addAttribute("broderAnseredList",broderAnseredList);
		
		//返回经纪人 店址
		model.addAttribute("broderWebShop", "http://www.hshb.cn/broker/brokerDetail/" + broker.getErpId() + "/" + type);
		model.addAttribute("housetype", type);
		model.addAttribute("jsonString", returnString);
		model.addAttribute("communityList", communityList);
		return "broker.detail.show";
	}

	public CutPageBean addRentPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];
		List<HouseRent> houseRentList = new ArrayList<HouseRent>();
		houseRentList = (List<HouseRent>) pageBean.getDataList();
		if (houseRentList == null) { return pageBean; }
		for (int i = 0; i < houseRentList.size(); ++i) {
			ids[i] = houseRentList.get(i).getErpId();
		}
		List<HousePic> housePics = this.houseSecondService.getHousePicByIdsAndPicType(ids, 2, 1, 0);
		if (housePics == null) { return pageBean; }
		for (HouseRent house : houseRentList) {
			for (HousePic housepic : housePics) {
				if (house.getErpId().equals(housepic.getHouseId())) {
					house.setHouseUrl(housepic.getPicUrl());
				}
			}
		}
		pageBean.setDataList((List) houseRentList);
		return pageBean;
	}

	public CutPageBean addSecondPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];
		List<HouseSecond> houseSecondList = new ArrayList<HouseSecond>();
		houseSecondList = (List<HouseSecond>) pageBean.getDataList();
		if (houseSecondList == null) { return pageBean; }
		for (int i = 0; i < houseSecondList.size(); ++i) {
			ids[i] = houseSecondList.get(i).getErpId();
		}
		List<HousePic> housePics = this.houseSecondService.getHousePicByIdsAndPicType(ids, 1, 1, 0);
		if (housePics == null) { return pageBean; }
		for (HouseSecond house : houseSecondList) {
			for (HousePic housepic : housePics) {
				if (house.getErpId().equals(housepic.getHouseId())) {
					house.setHouseUrl(housepic.getPicUrl());
				}
			}
		}
		pageBean.setDataList((List) houseSecondList);
		return pageBean;
	}

	@RequestMapping(params = { "actionMethod=brokerHouse" })
	public String brokerHouse(@RequestParam("brokerId") String brokerId, @RequestParam("housetype") String type,
			Model model, HttpServletRequest request) {
		Broker broker = (Broker) this.brokerService.getObjectById(Broker.class, brokerId);
		model.addAttribute("broker", broker);
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(6);
		String returnString = null;
		if ("2".equals(type)) {
			List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, HouseRent.class);
			CutPageBean pageBean = this.brokerService.findHouseRentByBrokerId(brokerId, cutPageBean, commonList);
			returnString = this.searchService.loadSearchMenuByModuleName("9");
			HouseListPictureUtil
					.getCorrespondingRHPictures((List<HouseRent>) pageBean.getDataList(), this.houseSecondService);
			this.judgerent(request, (List<HouseRent>) pageBean.getDataList());
			model.addAttribute("pageBean", this.addRentPicUrl(pageBean));
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
		} else {
			List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, HouseSecond.class);
			CutPageBean pageBean = this.brokerService.findHouseSecondByBrokerId(brokerId, cutPageBean, commonList);
			returnString = this.searchService.loadSearchMenuByModuleName("8");
			HouseListPictureUtil.getCorrespondingSHPictures((List<HouseSecond>) pageBean.getDataList(),
					this.houseSecondService);
			this.judgesecond(request, (List<HouseSecond>) pageBean.getDataList());
			model.addAttribute("pageBean", this.addSecondPicUrl(pageBean));
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
			if (comparedSHString == null) {
				comparedSHString = "";
			}
			model.addAttribute("secondHouseCompare", comparedSHString);
			String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
			if (historySHString == null) {
				historySHString = "";
			}
			model.addAttribute("secondHouseHistory", historySHString);
			model.addAttribute("showCompare", "S");
		}
		model.addAttribute("jsonString", returnString);
		model.addAttribute("housetype", type);
		return "broker.house.search.show";
	}

	@RequestMapping(params = { "actionMethod=getBrokerAnswered" })
	public String getBrokerAnswered(@RequestParam("brokerid") String brokerid, HttpServletRequest request, Model model) {
		List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, BroderAnsered.class);
		CutPageBean pageBean = QueryCondition.getQueryPageBean(request);
		pageBean.setPageSize(5);
		model.addAttribute("brokerid", brokerid);
		CutPageBean cpageBean = this.brokerService.getBrokerAnswered(brokerid, pageBean, commonList);
		int acceptAnwsered = this.brokerService.getBrokerAnsweredIsAccept(brokerid);
		model.addAttribute("pageBean", cpageBean);
		model.addAttribute("isaccept", acceptAnwsered);
		return "broker.answered.show";
	}

	@RequestMapping(params = { "actionMethod=dimquery" })
	public String dimQuery(@RequestParam("brokerId") String brokerId, @RequestParam("housetype") String housetype,
			HttpServletRequest request, Model model) throws ParseException {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		String currentPageString = request.getParameter("currentPage");
		int currentPage = 1;
		if (currentPageString != null && !currentPageString.equals("")) {
			currentPage = Integer.parseInt(currentPageString);
		}
		cutPageBean.setPageSize(6);
		Map oneMap = new HashMap();
		Set<String> tagsSet = new LinkedHashSet<String>();
		Map<String, List> twoMap = new HashMap<String, List>();
		Enumeration<String> parameterNames = (Enumeration<String>) request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement().toString();
			if (name.startsWith("ddhb_one_")) {
				String fieldName = name.substring(9);
				String value = request.getParameter(name);
				if (!"".equals(value.trim())) {
					if ("tags".equals(fieldName)) {
						tagsSet.add(value);
					} else if ("shi".equals(fieldName) || fieldName.endsWith("haslift")) {
						oneMap.put(fieldName, Integer.valueOf(value));
					} else if (fieldName.endsWith("erpId")) {
						oneMap.put(fieldName, value);
					} else {
						oneMap.put(fieldName, Long.valueOf(value));
					}
				}
			}
			if (name.startsWith("ddhb_two_")) {
				String fieldName = name.substring(9);
				String value = request.getParameter(name);
				if ("".equals(value.trim())) {
					continue;
				}
				int index = value.indexOf("@");
				if (index <= 0) {
					continue;
				}
				String minValue = value.substring(0, index);
				String maxValue = value.substring(index + 1);
				List<Float> list = new ArrayList<Float>();
				list.add(Float.valueOf(minValue));
				list.add(Float.valueOf(maxValue));
				twoMap.put(fieldName, list);
			}
		}
		String sortfield = request.getParameter("sort");
		String orderStr = request.getParameter("order");
		if (sortfield == null) {
			sortfield = "sortIndex";
			orderStr = "Asc";
		}
		CutPageBean pageBean = this.brokerService.getSearchFiledList(cutPageBean, oneMap, twoMap, tagsSet, orderStr,
				sortfield, housetype, brokerId, currentPage);
		model.addAttribute("housetype", housetype);
		if ("1".equals(housetype)) {
			List<HouseSecond> houseSeconds = (List<HouseSecond>) pageBean.getDataList();
			if (houseSeconds != null) {
				for (HouseSecond house : houseSeconds) {
					String tagsStr = house.getShowTags();
					String[] tagArr = tagsStr.split(",");
					List<String> tagIdList = new ArrayList<String>();
					for (int i = 1; i < tagArr.length; ++i) {
						tagIdList.add(tagArr[i]);
					}
					house.setTagIdList(tagIdList);
					
					//解析房源标签
					house.setHouseTags(houseSecondService.parseHouseTag(house.getTags()));
				}
			}

			HouseListPictureUtil.getCorrespondingSHPictures(houseSeconds, this.houseSecondService);
			this.judgesecond(request, houseSeconds);
			model.addAttribute("pageBean", this.addSecondPicUrl(pageBean));
		} else {
			List<HouseRent> houserents = (List<HouseRent>) pageBean.getDataList();
			if (houserents != null) {
				for (HouseRent houseRent : houserents) {
					String[] furArr = houseRent.getShowFunitures().split(",");
					List<String> furIdList = new ArrayList<String>();
					for (int j = 1; j < furArr.length; ++j) {
						furIdList.add(furArr[j]);
					}
					houseRent.setFurIdList(furIdList);
				}
			}
			List<Furniture> furList = this.rentService.getFurList();
			model.addAttribute("furList", furList);
			HouseListPictureUtil.getCorrespondingRHPictures(houserents, this.houseSecondService);
			this.judgerent(request, houserents);
			model.addAttribute("pageBean", this.addRentPicUrl(pageBean));
		}
		return "broker.house.search.list";
	}

	private void judgesecond(HttpServletRequest request, List<HouseSecond> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			List<HouseAppraise> haList = this.houseSecondService.getHouseAppraisesByHouseType(EnumHouseType.SALE.value());
			Map<String, String> houseMap = new HashMap<String, String>();
			for (HouseAppraise house : haList) {
				if (house.getBroker() != null) {
					houseMap.put(String.valueOf(house.getHouseNo()) + "-" + house.getBroker().getErpId(), house.getSearchno());
				}
			}
			List<PlatCollection> list = this.platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			for (PlatCollection coll : list) {
				collectMap.put(String.valueOf(coll.getObjectId()) + "-" + coll.getCollectType(), coll.getId());
			}
			if (houseList != null) {
				for (HouseSecond house2 : houseList) {
					String searchNo = "";
					if (house2.getBroker() != null) {
						searchNo = houseMap.get(String.valueOf(house2.getHouseNo()) + "-" + house2.getBroker().getErpId());
					}
					Long collectId = null;
					collectId = collectMap.get(String.valueOf(searchNo) + "-" + 0);
					house2.setCollectId(collectId);
					house2.setObjectId(searchNo);
				}
			}
		}
	}

	private void judgerent(HttpServletRequest request, List<HouseRent> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			
			//查询房源评价
			List<HouseAppraise> haList = this.houseSecondService.getHouseAppraisesByHouseType(EnumHouseType.RENT.value());
			Map<String, String> houseMap = new HashMap<String, String>();
			for (HouseAppraise house : haList) {
				if (house.getBroker() != null) {
					houseMap.put(house.getHouseNo() + "-" + house.getBroker().getErpId(), house.getSearchno());
				}
			}
			
			//查询房源收藏记录
			List<PlatCollection> list = this.platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			for (PlatCollection coll : list) {
				collectMap.put(coll.getObjectId() + "-" + coll.getCollectType(), coll.getId());
			}
			if (houseList != null) {
				for (HouseRent h : houseList) {
					String searchNo = "";
					if (h.getBroker() != null) {
						searchNo = houseMap.get(h.getHourseNo() + "-" + h.getBroker().getErpId());
					}
					Long collectId = null;
					collectId = collectMap.get(searchNo + "-" + 1);
					h.setCollectId(collectId);
					h.setObjectId(searchNo);
				}
			}
		}
	}

	@RequestMapping(params = { "actionMethod=questionAndAnswer" })
	public String initRequestAndAnswer(Model model, HttpServletRequest request) {
		List<QuestionStrategyType> qestionStrategyTypeList = this.brokerService.getQuestionStrategyType();
		//盛经理：固定显示问题&攻略
		//1:房屋买卖,3:银行贷款,5:房屋租赁,6:房产知识,7:房屋装修,8:政策法规,9:其他问题
		String[][] questionStrategyTypes = {{"1","housingSaleList","房屋买卖"},{"3","bankLoanList","银行贷款"},{"5","housingLeaseList","房屋租赁"},
				{"6","realEstateKnowledgeList","房产知识"},{"7","housingDecorationList","房屋装修"},{"8","policyAndRegulationsList","政策法规"},
				{"9","otherQuestionsList","其他问题"}};
		//存放所有问题&攻略 
		Map<String,List<QuestionStategy>> mapQuestions = new HashMap<String, List<QuestionStategy>>();
		for(String[] q : questionStrategyTypes){
			mapQuestions.put(q[1], brokerService.getAllQuestionStategy(q[0]));
		}
		Set<Entry<String, List<QuestionStategy>>> set = mapQuestions.entrySet();
		Iterator<Entry<String, List<QuestionStategy>>> it = set.iterator();
		while(it.hasNext()){
			Map.Entry<String, List<QuestionStategy>> en = it.next();
			model.addAttribute(en.getKey(), en.getValue());
			System.out.println("en.getKey():" + en.getKey());
			System.out.println("en.getValue.size:" + en.getValue().size());
		}
		model.addAttribute("qestionStrategyTypeList", qestionStrategyTypeList);
		model.addAttribute("qestionStrategyListFirst", this.brokerService.getQuestionStategyByTypeId("1", 5));
		model.addAttribute("qestionStrategyListSecond", this.brokerService.getQuestionStategyByTypeId("5", 5));
		model.addAttribute("questionCount", this.brokerService.getAllQuestionWithOutType().get(0));
		model.addAttribute("brokerCount", this.brokerService.getAllBrokerWithOutType().get(0));
		return "question.Answer.show";
	}

	@RequestMapping(params = { "actionMethod=getQuestions" })
	public String getQuestions(@RequestParam("subTypeId") String subTypeId, Model model, HttpServletRequest request) {
		List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, QuestionStategy.class);
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(5);
		model.addAttribute("subTypeId", subTypeId);
		String keyWord = request.getParameter("keyword");
		if (keyWord == null) {
			keyWord = "";
		}
		model.addAttribute("pageBean",
				this.brokerService.getAllQuestionStategy(subTypeId, cutPageBean, commonList, keyWord));
		model.addAttribute("questionCount", this.brokerService.getAllQuestionWithOutType().get(0));
		model.addAttribute("brokerCount", this.brokerService.getAllBrokerWithOutType().get(0));
		return "question.list.show";
	}

	@RequestMapping(params = { "actionMethod=initQuestionDetail" })
	public String initQuestionDetail(@RequestParam("questionId") String questionId,
			@RequestParam("typeId") String typeId, @RequestParam("subTypeId") String subTypeId, Model model,
			HttpServletRequest request) {
		model.addAttribute("qestionStrategyTypeList", this.brokerService.getQuestionStrategyType());
		model.addAttribute("questionCount", this.brokerService.getAllQuestionWithOutType().get(0));
		model.addAttribute("brokerCount", this.brokerService.getAllBrokerWithOutType().get(0));
		model.addAttribute("qestionStrategyListFirst", this.brokerService.getQuestionStategyByTypeId("1", 5));
		model.addAttribute("qestionStrategyListSecond", this.brokerService.getQuestionStategyByTypeId("5", 5));
		String[] ids = { questionId };
		List<QuestionStategy> questionList = (List<QuestionStategy>) this.brokerService.findDatasByIds(
				QuestionStategy.class, ids);
		QuestionStategy questionStategy = new QuestionStategy();
		if (questionList.size() != 0) {
			model.addAttribute("questionContent", questionList.get(0));
			questionStategy = questionList.get(0);
			int browed = questionStategy.getBrowsed();
			questionStategy.setBrowsed(browed + 1);
			this.brokerService.update(questionStategy);
		}
		model.addAttribute("questionStrategyListBySubType", this.brokerService.getQuestionStategyBySubType(subTypeId, 5));
		model.addAttribute("typeId", typeId);
		List<BroderAnsered> broderAnseredList = this.brokerService.getBrokerAnswer(questionId);
		model.addAttribute("broderAnseredList", broderAnseredList);
		model.addAttribute("subTypeId", subTypeId);
		List<QuestionStrategySubtype> subtype = this.brokerService.getAllQuestionStrategySubType(typeId);
		List<Object> countList = this.brokerService.getQuestionStrategyCount(typeId);
		List<QuestionStrategySubtype> questionStrategySubtype = new ArrayList<QuestionStrategySubtype>();
		for (QuestionStrategySubtype subType : subtype) {
			for (Object obj : countList) {
				Object[] objArray = (Object[]) obj;
				if (objArray[0].equals(subType.getErpId())) {
					subType.setCountQuestion((Long) objArray[1]);
				}
			}
			questionStrategySubtype.add(subType);
		}
		model.addAttribute("questionStrategySubTypeList", questionStrategySubtype);
		return "question.detail.show";
	}

	@RequestMapping(params = { "actionMethod=getAllQuestion" })
	public String getAllQuestion(@RequestParam("typeId") String typeId, Model model, HttpServletRequest request) {
		model.addAttribute("questionCount", this.brokerService.getAllQuestionWithOutType().get(0));
		model.addAttribute("brokerCount", this.brokerService.getAllBrokerWithOutType().get(0));
		List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, QuestionStategy.class);
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(5);
		String questionStateType = request.getParameter("questionStateType");
		String questionStateSubType = request.getParameter("questionStateSubType");
		List<QuestionStrategySubtype> subtype = null;
		List<Object> countList = null;
		String keyWord = request.getParameter("keyWord");
		if (keyWord == null) {
			keyWord = "";
		}
		if ("" != questionStateType && questionStateType != null && "" != questionStateSubType
				&& questionStateSubType != null) {
			model.addAttribute("qestionStrategyTypeList", this.brokerService.getQuestionStrategyType());
			model.addAttribute("typeId", questionStateType);
			subtype = this.brokerService.getAllQuestionStrategySubType(questionStateType);
			countList = this.brokerService.getQuestionStrategyCount(questionStateType);
			model.addAttribute("pageBean",
					this.brokerService.getAllQuestionStategy(questionStateSubType, cutPageBean, commonList, keyWord));
			model.addAttribute("subTypeId", questionStateSubType);
		} else {
			model.addAttribute("qestionStrategyTypeList", this.brokerService.getQuestionStrategyType());
			model.addAttribute("typeId", typeId);
			//获取typeId所有问题内容
			countList = this.brokerService.getQuestionStrategyCount(typeId);
			//获取typeId所有的二级菜单
			subtype = this.brokerService.getAllQuestionStrategySubType(typeId);
			if (subtype.size() > 0) {
				String subTypeId = subtype.get(0).getErpId();
				model.addAttribute("pageBean",
						this.brokerService.getAllQuestionStategy(subTypeId, cutPageBean, commonList, keyWord));
				model.addAttribute("subTypeId", subTypeId);
			} else {
				model.addAttribute("pageBean", new CutPageBean());
				model.addAttribute("subTypeId", "");
			}
		}
		model.addAttribute("qestionStrategyListFirst", this.brokerService.getQuestionStategyByTypeId("1", 5));
		model.addAttribute("qestionStrategyListSecond", this.brokerService.getQuestionStategyByTypeId("5", 5));
		List<QuestionStrategySubtype> questionStrategySubtype = new ArrayList<QuestionStrategySubtype>();
		for (QuestionStrategySubtype subType : subtype) {
			for (Object obj : countList) {
				Object[] objArray = (Object[]) obj;
				if (objArray[0].equals(subType.getErpId())) {
					subType.setCountQuestion((Long) objArray[1]);
				}
			}
			questionStrategySubtype.add(subType);
		}
		model.addAttribute("questionStrategySubTypeList", questionStrategySubtype);
		return "question.allbytype.show";
	}

	@RequestMapping(params = { "actionMethod=getQuestionBySubType" })
	public String getQuestionBySubType(@RequestParam("subTypeId") String subTypeId, Model model,
			HttpServletRequest request) {
		List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, QuestionStategy.class);
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(5);
		model.addAttribute("subTypeId", subTypeId);
		String keyword = request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		model.addAttribute("pageBean",
				this.brokerService.getAllQuestionStategy(subTypeId, cutPageBean, commonList, keyword));
		return "question.list.show";
	}

	@RequestMapping(params = { "actionMethod=addQuestion" })
	public String addQuestion(@RequestParam("questionId") String questionId, Model model) {
		model.addAttribute("qestionStrategyListFirst", this.brokerService.getQuestionStategyByTypeId("1", 5));
		model.addAttribute("qestionStrategyListSecond", this.brokerService.getQuestionStategyByTypeId("5", 5));
		model.addAttribute("questionId", questionId);
		model.addAttribute("qestionStrategyTypeList", this.brokerService.getQuestionStrategyType());
		model.addAttribute(new QuestionStategy());
		model.addAttribute("typeList", this.brokerService.getQuestionStrategyType());
		model.addAttribute("questionCount", this.brokerService.getAllQuestionWithOutType().get(0));
		model.addAttribute("brokerCount", this.brokerService.getAllBrokerWithOutType().get(0));
		return "question.add.show";
	}

	@RequestMapping(params = { "actionMethod=addQuestionPost" })
	public String addQuestionPost(@RequestParam("questionId") String questionId,
			@ModelAttribute QuestionStategy questionStategy, BindingResult result, Model model, HttpServletRequest request) {
		String questionstategySubType = request.getParameter("questionStategySubType");
		this.validateQuestion(questionStategy, (Errors) result, request, questionstategySubType);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "questionStategyForm"));
			return "ddhb.out.error";
		}
		questionStategy.setQuestionType((QuestionStrategySubtype) this.brokerService.getObjectById(
				QuestionStrategySubtype.class, questionstategySubType));
		if (!questionId.equals("0")) {
			questionStategy.setParentQuestionId(questionId);
		}
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		questionStategy.setErpId(UUID.randomUUID().toString());
		questionStategy.setCreateTime(new Date());
		questionStategy.setAnsweredFlag(0);
		if (platMemberInfo == null) {
			questionStategy.setPublishName("");
		} else {
			questionStategy.setPublishName(platMemberInfo.getAccName());
			questionStategy.setUser(platMemberInfo);
		}
		this.brokerService.save(questionStategy);
		model.addAttribute("saveok", "saveok");
		return "ddhb.out.error";
	}

	@RequestMapping(params = { "actionMethod=getQuestionStategySubType" })
	public void getQuestionStategySubType(@RequestParam("typeId") String typeId, HttpServletResponse response) {
		List<QuestionStrategySubtype> questionStrategySubTypes = this.brokerService.getAllQuestionStrategySubType(typeId);
		response.setContentType("text/xml;charset=utf-8");
		String result = GsonUtil.getGsonInstanceWithExpose().toJson(questionStrategySubTypes);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(result);
			out.flush();
		} catch (IOException e) {
			BrokerAction.LOGGER.error(e.getMessage());
			return;
		} finally {
			if (out != null) {
				out.close();
			}
		}
		if (out != null) {
			out.close();
		}
	}

	@RequestMapping(params = { "actionMethod=getSearchQuestion" })
	public String getSearchQuestion(Model model, HttpServletRequest request, HttpServletResponse response) {
		String searchString = request.getParameter("searchString");
		String subtype = request.getParameter("subtype");
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(5);
		List<CommonBean> commonList = (List<CommonBean>) QueryCondition.getQueryCondition(request, QuestionStategy.class);
		CutPageBean pageBean = this.brokerService.searchQuestionStategy(cutPageBean, searchString, subtype, commonList);
		model.addAttribute("searchString", searchString);
		model.addAttribute("subtype", subtype);
		model.addAttribute("pageBean", pageBean);
		return "question.list.show";
	}

	private void validateQuestion(QuestionStategy questionStategy, Errors errors, HttpServletRequest request,
			String questionstategySubType) {
		if (CommonUtil.isZeroLengthTrimString(questionStategy.getTitle())) {
			errors.reject("\u6807\u9898\u4e3a\u5fc5\u586b\u9879");
		} else if (questionStategy.getTitle().trim().toString().getBytes(Charset.forName("GBK")).length > 80) {
			errors.reject("\u6807\u9898\u957f\u5ea6\u8bf7\u63a7\u5236\u572880\u4e2a\u5b57\u7b26\u4e4b\u5185");
		}
		if (CommonUtil.isZeroLengthTrimString(questionStategy.getContent())) {
			errors.reject("\u5185\u5bb9\u4e3a\u5fc5\u586b\u9879");
		} else if (questionStategy.getContent().trim().toString().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("\u5185\u5bb9\u957f\u5ea6\u8bf7\u63a7\u5236\u5728400\u4e2a\u5b57\u7b26\u4e4b\u5185");
		}
		if (questionstategySubType == null || questionstategySubType.equals("0")) {
			errors.reject("\u95ee\u9898\u7c7b\u578b\u4e3a\u5fc5\u9009\u9879");
		}
		if (CommonUtil.isZeroLengthTrimString(questionStategy.getVerifyCode())) {
			errors.reject("\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801");
		} else {
			boolean flag = Boolean.FALSE;
			String sessId = request.getSession().getId();
			flag = CaptchaServiceSingleton.getInstance().validateResponseForID(sessId,
					questionStategy.getVerifyCode().toLowerCase());
			if (!flag) {
				errors.reject("\u9a8c\u8bc1\u7801\u8f93\u5165\u6709\u8bef");
			}
		}
	}
}
