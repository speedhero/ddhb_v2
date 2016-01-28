package com.huatek.hbwebsite.house.show;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.house.enums.EnumCompareHouseType;
import cn.hshb.web.house.enums.EnumHouseListType;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.house.enums.EnumPictureLayout;

import com.huatek.ddhb.manage.activityManage.service.ActivityManageService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.HouseSecondRecommendUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CommunityHospitalMapping;
import com.huatek.hbwebsite.common.entity.CommunitySchoolMapping;
import com.huatek.hbwebsite.common.entity.CommunityStationMapping;
import com.huatek.hbwebsite.common.entity.CommunitySubwayStationMapping;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.consignment.service.ConsignmentService;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseRentBrowseAnalyse;
import com.huatek.hbwebsite.house.entity.HouseRentPriceTrend;
import com.huatek.hbwebsite.house.entity.HouseType;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.MemberBrowseHistory;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.HouseReduceNoticeService;
import com.huatek.hbwebsite.member.service.MemberBrowseHistoryService;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import com.huatek.hbwebsite.member.service.SubscribeInfoService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.saveQuery.service.SaveQueryConditionsService;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.search.service.SharedSearchService;
import com.huatek.hbwebsite.util.BrowseCacheUtil;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.HouseListPictureUtil;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

@Controller
@RequestMapping({ "/rent.show" })
public class RentAction {
	@Autowired
	PlatMemberInfoService platMemberInfoService;
	@Autowired
	private SubscribeInfoService subScribeInfoService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private RentService rentService;
	@Autowired
	PlatCollectionService platCollectionService;
	@Autowired
	private HouseSecondService houseSecondService;
	@Autowired
	HouseReduceNoticeService houseReduceNoticeService;
	@Autowired
	MemberBrowseHistoryService memberBrowseHistoryService;
	@Autowired
	private ConsignmentService consignmentService;
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	SharedSearchService sharedSearchService;
	
	@Autowired
	CommunityService communityService;
	
	@Autowired
	SaveQueryConditionsService saveQueryConditionsService;
	
	private static final Log log = LogFactory.getLog(RentAction.class);
	private static final Integer isMember = Integer.valueOf(1);
	private static final Integer notMember = Integer.valueOf(0);
	
	private static final int HOUSE_TYPE_RENT = 2;		//房源类型，租赁
	private static final String COMPARE_TYPE_RENT = "R";  //房源比较类型，租赁
//	private static final String LIST_SHOW_TYPE_PIC = "0";  //列表显示类型_图片
//	private static final String LIST_SHOW_TYPE_TXT = "1";  //列表显示类型_文字
//	private static enum HouseListTypeEnum {IMAGE, TEXT};			//房源列表显示模式
	
	//<meta name="keywords" content="KEYWORDS" /> 关键字    搜索引擎预期查找
	private static final String KEYWORDS ="租房,杭州租房,杭州租房网,杭州房屋出租,杭州租房信息";			
	//<meta name="description" content="DESCRIPTION" /> 网页内容 不超过
	private static final String DESCRIPTION = "杭州豪世华邦出租房频道是最专业最真实的杭州出租房网,为您提供大量的杭州房屋出租信息,查找杭州出租信息,请到杭州豪世华邦。"; 		
	//<title>TITLECONTENT</title> 标题 	
	private static final String TITLECONTENT = "杭州租赁";	

	private void judgeLoginForCollect(HttpServletRequest request, List<HouseRent> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			List<PlatCollection> list = this.platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			Iterator<PlatCollection> it = list.iterator();

			while (it.hasNext()) {
				PlatCollection house = it.next();
				collectMap.put(house.getObjectId(), house.getId());
			}

			if (houseList != null) {
				Iterator<HouseRent> itHouse = houseList.iterator();
 
				while (it.hasNext()) {
					HouseRent house1 = itHouse.next();
					Long collectId = collectMap.get(house1.getShelvingID());
					house1.setCollectId(collectId);
					house1.setObjectId(house1.getErpId());
				}
			}
		}
	}

	@RequestMapping(params = { "actionMethod=houseRentCompare" })
	public String houseRentCompare(@RequestParam("houseNos") String houseNos, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		List<HouseRent> houseRentList = this.rentService.findRentHouseListByHouseNos(houseNos);
		if (houseRentList != null && houseRentList.size() > 0) {
			model.addAttribute("backType", Integer.valueOf(3));
			Iterator<HouseRent> itHouse = houseRentList.iterator();

			while (itHouse.hasNext()) {
				HouseRent ids = itHouse.next();
				String tagsStr = ids.getShowFunitures();
				String[] tagArr = tagsStr.split(",");
				List<String> tagIdList = new ArrayList<String>();

				for (int i = 0; i < tagArr.length; ++i) {
					if (!"".equals(tagArr[i])) {
						tagIdList.add(tagArr[i]);
					}
				}

				ids.setFurIdList(tagIdList);
			}

			this.judgeLoginForCollect(request, houseRentList);
			String[] shelvingIDs = new String[houseRentList.size()];

			for (int ii = 0; ii < houseRentList.size(); ++ii) {
				shelvingIDs[ii] = ((HouseRent) houseRentList.get(ii)).getShelvingID();
			}

			HouseListPictureUtil.getCorrespondingRHPictures(houseRentList, this.houseSecondService);
			List<Furniture> furList = this.rentService.getFurList();
			model.addAttribute("tagList", furList);		//配套设施列表 
			model.addAttribute("house", houseRentList);
			return "houseRent.compare";
		} else {
			return "redirect:houseSecond.show?actionMethod=showList";
		}
	}

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
		} else if (!Util.getMatchResult(email,
				"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			model.addAttribute("promptMessage", "邮件格式不正确，请重新输入");
			return "base.definition";
		} else {
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			this.subScribeInfoService.saveSubScribeInfo(email, platMemberInfo);
			model.addAttribute("promptMessage", "您已经订阅成功");
			return "common.definition";
		}
	}

	/**
	 * 租赁房源详情显示
	 * @param houseNo		房源编号
	 * @param brokerId	经纪人编号
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=houseRentDetail" })
	public String houseDetail(@RequestParam("houseNo") String houseNo, @RequestParam("brokerId") String brokerId,
			Model model, HttpServletRequest request) {
//		model.addAttribute("houseType", HOUSE_TYPE_RENT);
		model.addAttribute("houseType", EnumHouseType.RENT.getValue());
//		model.addAttribute("showCompare", COMPARE_TYPE_RENT);
		model.addAttribute("showCompare", EnumCompareHouseType.RENT.getValue());
		List<HouseAppraise> houseAppraiseList = this.rentService.findAppraiseListByHouseNo(houseNo);
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");

		HouseRent houseRent = null;
		if (houseAppraiseList.size() == 0) {
			houseRent = this.rentService.getHouseRentByHouseNo(houseNo);
		} else {
			houseRent = this.rentService.findHouseSecondByHouseId(houseAppraiseList.get(0).getHouseId());
			if (platMemberInfo != null) {
				int ifNotice = this.houseReduceNoticeService.ifHouseNotice(houseAppraiseList.get(0).getSearchno(), platMemberInfo.getId());
				if (ifNotice == 1) {
					model.addAttribute("noticeResult", "noticed");
				} else {
					model.addAttribute("noticeResult", "unnoticed");
				}
			}
		}

		if (houseRent == null) {
			model.addAttribute("message", "对不起，您点击的房源不存在。");
			return "house.none";
		} else {
			if(houseRent.getCommunity()==null){
				model.addAttribute("message", "对不起，您点击的房源所在小区信息缺失。");
				return "house.none";
			}
			
			model.addAttribute("brokerId", brokerId);
			String furStr = houseRent.getShowFunitures();
			String[] furArr = furStr.split(",");
			List<String> furIdList = new ArrayList<String>();

			for (int ii = 0; ii < furArr.length; ++ii) {
				if(StringUtils.isBlank(furArr[ii])) continue;
				furIdList.add(furArr[ii]);
				houseRent.setFurIdList(furIdList);
			}

			List<Furniture> furList = this.rentService.getFurList();
			if (houseRent.getFurIdList() != null) {
				for (int ii = 0; ii < furList.size(); ++ii) {
					for (int jj = 0; jj < houseRent.getFurIdList().size(); ++jj) {
						if (houseRent.getFurIdList().get(jj).equals(furList.get(ii).getErpId())) {
							furList.get(ii).setFlag(true);
						}
					}
				}
			}

			model.addAttribute("backType", 3);
			model.addAttribute("furList", furList);
			if (platMemberInfo != null) {
				List<MemberBrowseHistory> browseHisList = this.memberBrowseHistoryService.findMemberBrowseHistoryByMember(platMemberInfo.getId(), 1,
						houseRent.getErpId());
				if (browseHisList.size() > 0) {
					browseHisList.get(0).setModifiedTime(new Date());
					this.memberBrowseHistoryService.update(browseHisList.get(0));
				} else {
					MemberBrowseHistory browseHis = new MemberBrowseHistory();
					browseHis.setPlatMemberInfo(platMemberInfo);
					browseHis.setObjectId(houseRent.getErpId());
					browseHis.setObjectType(1);
					browseHis.setCreateTime(new Date());
					browseHis.setModifiedTime(new Date());
					this.memberBrowseHistoryService.save(browseHis);
				}
			}

			String[] param = new String[] { houseRent.getShelvingID() };
//			model.addAttribute("houseHeadPics", this.houseSecondService.getHousePicByIdsAndPicType(param, 2, 1, 0));
//			model.addAttribute("housePics", this.houseSecondService.getHousePicByIdsAndPicType(param, 2, 2, 0));
//			List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(param, 2, 1, 1);
//			if (housePicList != null && housePicList.size() > 0) {
//				HousePic housePic = housePicList.get(0);
//				houseRent.setHouseUrl(housePic.getPicUrl());
//			}
			//从SVN上对比下来的start
			List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(param, EnumHouseType.RENT.value(), EnumPictureLayout.ALL.value(), 0);
			List<HousePic> houseCoverPicList = this.houseSecondService.getHousePicByIdsAndPicType(param, EnumHouseType.RENT.value(), EnumPictureLayout.COVER.value(), 0);
			model.addAttribute("houseHeadPics", houseCoverPicList);
			//TODO 把户型图放在第一个位置
			toPhotosTop(housePicList);
			model.addAttribute("housePics", housePicList);
			if(houseCoverPicList.size()>0){
//				houseRent.setHouseUrl(houseCoverPicList.get(0).getPicUrl());
				houseRent.setPictureUrl(houseCoverPicList.get(0).getPicUrl());
			}else{
				//如果没有封面照片，则从其他图片中选一张
//				houseRent.setHouseUrl(housePicList.get(0).getPicUrl());
				if(housePicList.size()>0){
					houseRent.setPictureUrl(housePicList.get(0).getPicUrl());
					houseCoverPicList.add(housePicList.get(0));
				}else{
					log.warn("房源[houseNo="+houseNo+", ShelvingID="+param[0]+"]没有房源照片.");
				}
			}
			//END
			param[0] = houseRent.getCommunity().getErpId();
			model.addAttribute("communityPics", this.houseSecondService.getHousePicByIdsAndPicType(param, 3, 3, 0));
			
			//载入小区最近门店列表
			//Added by syf at 2015.02.06
			communityService.loadNearestStore(houseRent.getCommunity());

			for (int ii = 0; ii < houseAppraiseList.size(); ++ii) {
				if (houseAppraiseList.get(ii).getBroker().getErpId().equals(brokerId)) {
					HouseAppraise cStaMapping = houseAppraiseList.get(ii);
					houseAppraiseList.remove(ii);
					houseAppraiseList.add(0, cStaMapping);
				}

				if (houseAppraiseList.get(ii).getBroker() != null) {
					Long houseCount = this.houseSecondService.findHouseRentCountByBrokerId(houseAppraiseList.get(ii).getBroker().getErpId());
					houseAppraiseList.get(ii).getBroker().setHouseCount(houseCount.intValue());
				}
			}

			List<CommunitySubwayStationMapping> cSubMappingList = this.rentService.findCSubMapping(houseRent.getCommunity().getErpId());
			List<CommunityStationMapping> cStaMappingList = this.rentService.findCStaMapping(houseRent.getCommunity().getErpId());
			List<CommunityHospitalMapping> cHMappingList = this.rentService.findCHMapping(houseRent.getCommunity().getErpId());
			List<CommunitySchoolMapping> cSMappingList = this.rentService.findCSMapping(houseRent.getCommunity().getErpId());
			String tagsStr = houseRent.getShowTags();
			String[] tagArr = tagsStr.split(",");
			List<String> tagIdList = new ArrayList<String>();

			for (int ii = 1; ii < tagArr.length; ++ii) {
				tagIdList.add(tagArr[ii]);
				houseRent.setTagIdList(tagIdList);
			}

			HouseAppraise appraise = this.houseSecondService.findAppraiseByBrokerAndHouseNo(houseNo, brokerId);
			if (appraise == null) {
				appraise = new HouseAppraise();
				appraise.setBroker(houseRent.getBroker());
				appraise.setTitle(houseRent.getTitle());
			}

			BrowseCacheUtil browseCacheUtil = BrowseCacheUtil.getInstance();
			int browse = browseCacheUtil.getBrowsed(Integer.valueOf(2), houseRent.getHourseNo(), houseRent.getBrowsed());
			model.addAttribute("houseBrowsed", Integer.valueOf(browse));
			model.addAttribute("houseAppraise", appraise);
			model.addAttribute("appraiseList", houseAppraiseList);
			model.addAttribute("house", houseRent);
			model.addAttribute("cHMapping", cHMappingList);
			model.addAttribute("cSMapping", cSMappingList);
			model.addAttribute("cStaMapping", cStaMappingList);
			model.addAttribute("cSubMapping", cSubMappingList);
			HouseSecondRecommendUtil recommendUtil = (HouseSecondRecommendUtil) request.getSession().getAttribute("houseRentHistory");
			if (recommendUtil != null) {
				String trendList = recommendUtil.putPriceToQueue(houseRent.getRentPrice());
				String lastMonthPrice = recommendUtil.putAreaToQueue(houseRent.getArea());
				String thisMonthPriceCommunity = recommendUtil.putShiToQueue(houseRent.getShi());
				List<HouseRent> lastMonthPriceCommunity = this.rentService.getRecommandedHouseRent(trendList, lastMonthPrice,
						thisMonthPriceCommunity, houseRent.getErpId());
				HouseListPictureUtil.getCorrespondingRHPictures(lastMonthPriceCommunity, this.houseSecondService);
				List<Tag> thisMonthPrice = this.houseSecondService.getTagList();
				model.addAttribute("recommendList", lastMonthPriceCommunity);
				model.addAttribute("tagList", thisMonthPrice);
			}

			List<HouseRentPriceTrend> priceTrendList = this.rentService.getRentHouseTrendForSixMonth(houseRent.getErpId());
			model.addAttribute("trendList", priceTrendList);
			float lastMonthPrice = this.rentService.getRentHouseLastMonthPrice(houseRent.getErpId());
			model.addAttribute("lastMonthPrice", lastMonthPrice);
			float commThisMPrice = this.houseSecondService.getSecondHouseCommunityThisMonthPrice(houseRent.getCommunity().getErpId());
			model.addAttribute("thisMonthPriceCommunity", commThisMPrice);
			float commLastMPrice = this.houseSecondService.getSecondHouseCommunityLastMonthPrice(houseRent.getCommunity().getErpId());
			model.addAttribute("lastMonthPriceCommunity", commLastMPrice);
			if (commLastMPrice != 0.0D && commThisMPrice != 0.0D) {
				model.addAttribute("aroundLaseMonthCommunity", (commThisMPrice - commLastMPrice) / commLastMPrice * 100.0F);
			} else {
				model.addAttribute("aroundLaseMonthCommunity", 0);
			}

			float thisMPriceTrend = 0.0F;
			float lastMPrices = 0.0F;
			float lastYPrices = 0.0F;
			HouseRentPriceTrend houseRentPriceTrendthisMonth = this.rentService.getHousePriceThisMonth(houseRent.getErpId());
			HouseRentPriceTrend houseRentPriceTrendLaseMonth = this.rentService.getHousePriceLastMonth(houseRent.getErpId());
			HouseRentPriceTrend houseRentPriceTrendLastYear = this.rentService.getHousePriceLastYear(houseRent.getErpId());
			if (houseRentPriceTrendthisMonth != null) {
				thisMPriceTrend = houseRentPriceTrendthisMonth.getPrice();
				model.addAttribute("thisMonthPrice", thisMPriceTrend);
			} else {
				model.addAttribute("thisMonthPrice", 0.0D);
			}

			if (houseRentPriceTrendLaseMonth != null) {
				lastMPrices = houseRentPriceTrendLaseMonth.getPrice();
				model.addAttribute("laseMonthPrice", lastMPrices);
			} else {
				model.addAttribute("laseMonthPrice", 0.0D);
			}

			if (houseRentPriceTrendLastYear != null) {
				lastYPrices = houseRentPriceTrendLastYear.getPrice().floatValue();
				model.addAttribute("laseYearPrice", lastYPrices);
			} else {
				model.addAttribute("laseYearPrice", 0.0D);
			}

			if (thisMPriceTrend != 0.0D && lastMPrices != 0.0D) {
				model.addAttribute("aroundLaseMonth", (thisMPriceTrend - lastMPrices) / lastMPrices * 100.0F);
			} else {
				model.addAttribute("aroundLaseMonth", 0);
			}

			if (thisMPriceTrend != 0.0D && lastYPrices != 0.0D) {
				model.addAttribute("aroundLaseYear", (thisMPriceTrend - lastYPrices) / lastYPrices * 100.0F);
			} else {
				model.addAttribute("aroundLaseYear", Integer.valueOf(0));
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tmp = df.format(new Date()).toString();
			String shHistoryBespeak = houseRent.getErpId() + "," + Timestamp.valueOf(tmp);
//			Object queue = (Queue) request.getSession().getAttribute("rhHistoryBespeakQueue");
			Queue<String> queue = (Queue<String>) request.getSession().getAttribute("rhHistoryBespeakQueue");
			if (queue == null) queue = new LinkedList<String>();

			queue.add(shHistoryBespeak);
			if (queue.size() > 50) {
				queue.remove();
			}

			request.getSession().setAttribute("rhHistoryBespeakQueue", queue);
			model.addAttribute("showCompare", COMPARE_TYPE_RENT);
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
			HouseRentBrowseAnalyse houseAnalyse = new HouseRentBrowseAnalyse();
			houseAnalyse.setAnalyseId(UUID.randomUUID().toString());
			houseAnalyse.setHouseId(houseRent.getHourseNo());
			houseAnalyse.setBrowseTime(new Date());
			if(houseRent.getCommunity().getCbd()!=null){
				houseAnalyse.setCbdId(houseRent.getCommunity().getCbd().getErpId());
				houseAnalyse.setCbdName(houseRent.getCommunity().getCbd().getCbdName());
				houseAnalyse.setCountyId(houseRent.getCommunity().getCbd().getCounty().getErpId());
				houseAnalyse.setCountyName(houseRent.getCommunity().getCbd().getCounty().getCountyName());
			}
			houseAnalyse.setCommunityId(houseRent.getCommunity().getErpId());
			houseAnalyse.setCommunityName(houseRent.getCommunity().getCommunityName());
			houseAnalyse.setHouseArea(Float.valueOf(houseRent.getArea()));
			houseAnalyse.setHousePrice(Float.valueOf(houseRent.getRentPrice()));
			houseAnalyse.setClientFlag(clientFlag);
			if (platMemberInfo != null) {
				houseAnalyse.setIsMember(isMember);
				houseAnalyse.setMemberName(platMemberInfo.getAccName());
				houseAnalyse.setRegisterTime(platMemberInfo.getRegTime());
			} else {
				houseAnalyse.setIsMember(notMember);
			}
			this.rentService.save(houseAnalyse);
			
			//Add by 何剑波  
			//功能：在<head>标签添加<meta>的值
			//title内容房源站点地址
			model.addAttribute("titleContent","杭州出租房-"+houseRent.getCommunity().getRegion().getCountyName() + "-" + houseRent.getCommunity().getCommunityName());
			model.addAttribute("keywords",null);
			model.addAttribute("description","杭州豪世华邦为您提供最新杭州"+ houseRent.getCommunity().getRegion().getCountyName() +"出租房信息，找房、租房不再难");
			
			return "houseRent.detail";
		}
	}

	/**
	 * 设置图片路径
	 * @param pageBean
	 * @return
	 */
	public CutPageBean addPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];
		//new ArrayList();
		List<HouseRent> houseList = (List<HouseRent>)pageBean.getDataList();
		if (houseList == null)  return pageBean;
			
		for (int ii = 0; ii < houseList.size(); ++ii) {
				ids[ii] = houseList.get(ii).getShelvingID();
			}

		List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(ids, EnumHouseType.RENT.value(), 1, 0);
		if (housePicList == null) return pageBean;
		
		List<String> noPicHouseIdList = new ArrayList<String>();
		Iterator<HouseRent> itHouse = houseList.iterator();
		while (itHouse.hasNext()) {
			HouseRent house = itHouse.next();
			Iterator<HousePic> itHousePic = housePicList.iterator();
			
			Boolean hasCoverPic = false; 
			while (itHousePic.hasNext()) {
				HousePic housepic = itHousePic.next();
				if (house.getShelvingID().trim().equals(housepic.getShelvingID().trim())) {
					house.setPictureUrl(housepic.getPicUrl());
					hasCoverPic = true;
					break;
				}
			}

			String[] houseShelvingId = { house.getShelvingID() };
		      List<HousePic> list = this.houseSecondService.getHousePicByIdsAndPicType(houseShelvingId, 2, 2, 0);
		      if (list != null) 
		    	  house.setHousePicSize(Integer.valueOf(list.size()));

			//记下没有封面图片的房源
			if(!hasCoverPic) noPicHouseIdList.add(house.getShelvingID());
		}
		

		//对没有封面图片的房源，取其他图片作为封面图片
		//Added by Sheng Youfu at 2015.03.30
		if(noPicHouseIdList.size()>0){
			housePicList.clear();
			housePicList = this.houseSecondService.getHousePicByIdsAndPicType(noPicHouseIdList.toArray(new String[0]), EnumHouseType.RENT.value(), 0, 0);
			if(housePicList!=null){
				itHouse = houseList.iterator();
				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					if(house.getPictureUrl()!=null && !"".equals(house.getPictureUrl().trim())) continue;
					Iterator<HousePic> itHousePic = housePicList.iterator();
					while (itHousePic.hasNext()) {
						HousePic housepic = itHousePic.next();
						if (house.getShelvingID().trim().equals(housepic.getShelvingID().trim())) {
							house.setPictureUrl(housepic.getPicUrl());
							break;
						}
					}
				}
			}
		}

		pageBean.setDataList(houseList);
		return pageBean;
	}

	/**
	 * 根据条件搜索房源
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=dimquery" })
	public String dimQuery(HttpServletRequest request, Model model) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		String shortValue = request.getParameter("shortValue");
		Map<String, String> searchItemMap = new HashMap<String, String>();
		
		if(shortValue != null){
			//根据短码查询原始查询URL
			String sharedUrl = this.sharedSearchService.getSharedUrl(shortValue);
			model.addAttribute("sharedUrl", sharedUrl);
			searchItemMap = sharedSearchService.getParameterMap(sharedUrl);
		}else{
			String queryStr = request.getQueryString();
			searchItemMap = sharedSearchService.getParameterMap(queryStr);
			
			Map<String, String[]> paramMap = request.getParameterMap();
			for(Map.Entry<String, String[]> param: paramMap.entrySet()){
				String key = param.getKey();
				String[] val = param.getValue();
				String value = val[0];
				if(val.length>1){
					StringBuilder sb = new StringBuilder("");
					for(String v: val){
						sb.append(v).append(",");
					}
					sb.deleteCharAt(sb.length() -1 );
					value = sb.toString();
				}
				if(!searchItemMap.containsKey(key))
					searchItemMap.put(key, value);
			}

			if (request.getAttribute("houseNo") != null)
	    	searchItemMap.put("houseNo", (String)request.getAttribute("houseNo"));
			
      if (request.getAttribute("inputSearch") != null)
      	searchItemMap.put("inputSearch", (String)request.getAttribute("inputSearch"));
		}
		
		/**
		 * 给托管房查询条件 Key加前缀，因为在组装查询条件时，只取有前缀的参数
		 * Added by ShengYoufu at 2015.02.02
		 */
		if(searchItemMap.containsKey("exclusiveFlag")){
			searchItemMap.put("ddhb_one_exclusiveFlag", searchItemMap.get("exclusiveFlag"));
			searchItemMap.remove("exclusiveFlag");
		}
		
		//拼凑查询URL
//		StringBuilder sbSearchUrl = new StringBuilder("");
//		for(Map.Entry<String, String> item: searchItemMap.entrySet()){
//			sbSearchUrl.append(item.getKey()).append("=").append(item.getValue()).append("&");
//		}
//		sbSearchUrl.deleteCharAt(sbSearchUrl.length() -1 );
//		String searchUrl = sbSearchUrl.toString();

		//获取房源列表每页显示数量
		EnumHouseListType listType = EnumHouseListType.IMAGE;
		if("1".equals(searchItemMap.get("type")))
			listType = EnumHouseListType.TEXT;
		if (listType == EnumHouseListType.TEXT) {
			cutPageBean.setPageSize(FrontSystemSettingUtil.getInstance().getListTypeSize());
		} else {
			cutPageBean.setPageSize(FrontSystemSettingUtil.getInstance().getPicTypeSize());
		}

		//获取页面活动广告位 
		model.addAttribute("topADBar", activityManageService.getActivityByPageAndPosition(3, 1));
		model.addAttribute("bottomADBar", activityManageService.getActivityByPageAndPosition(3, 2));

		HashMap oneMap = new HashMap();
		HashMap twoMap = new HashMap();
		HashMap tagsMap = new HashMap();

		//提取实际查询参数
		for(Map.Entry<String, String> item: searchItemMap.entrySet()){
			getSearchMap(item.getKey(), item.getValue(), oneMap, tagsMap, twoMap);
		}
		if(searchItemMap.containsKey("houseNo"))
			oneMap.put("houseNo", searchItemMap.get("houseNo"));
		if(searchItemMap.containsKey("inputSearch"))
			oneMap.put("inputSearch", searchItemMap.get("inputSearch"));
		
		//拼接查询参数字符串
		StringBuilder sbSearchUrl = new StringBuilder("");
		for (Map.Entry<String, String> item : searchItemMap.entrySet()) {
			if ("actionMethod".equalsIgnoreCase(item.getKey())) continue;	//actionMethod参数不作为查询参数
			sbSearchUrl.append("&").append(item.getKey()).append("=").append(item.getValue());
		}
		if(sbSearchUrl.length()>0)
			sbSearchUrl.deleteCharAt(0);
		String searchUrl = sbSearchUrl.toString();
		
		//如果当前查询不是根据短码来查询的，则把当前查询条件放到model,以备分享时使用
		if(shortValue==null){
			String sharedUrl = "rent.show?actionMethod=dimquery" + (StringUtils.isEmpty(searchUrl)? "" : "&"+searchUrl);
			sharedUrl = sharedUrl.replaceAll("&actionMethod=dimquery", "");
			model.addAttribute("sharedUrl", sharedUrl);
		}

		model.addAttribute("backType", 3);

		String sortStr = searchItemMap.get("sort");
		String orderStr = searchItemMap.get("order");
		String ispage = searchItemMap.get("ispage");
		if (sortStr == null) {
			sortStr = "sortIndex";
			orderStr = "Asc";
		}
		CutPageBean searchBean = this.rentService.getSearchFiledList(cutPageBean, oneMap, twoMap, tagsMap, orderStr, sortStr);
		
		try{
			//存储查询的条件
			saveQueryConditionsService.saveQueryConditions(searchItemMap, "houseRent");
			}catch(Exception ex){
//				log.error("存储查询的条件报错",ex);
				ex.printStackTrace();
			}
		
		List<HouseRent> houserents = (List<HouseRent>)searchBean.getDataList();
		if (houserents != null) {
			for(HouseRent house: houserents){
				String[] furIDs = house.getShowFunitures().split(",");
				List<String> furIdList = Arrays.asList(furIDs);
				house.setFurIdList(furIdList);
			}
		}

		List<Furniture> furList = this.rentService.getFurList();
		model.addAttribute("furList", furList);
		this.judgeLoginForCollect(request, houserents);
		model.addAttribute("showCompare", COMPARE_TYPE_RENT);
		model.addAttribute("pageBean", this.addPicUrl(searchBean));
		String clientFlag = ClientFlagUtil.getClientFlag(request);

		String compareStr = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
		model.addAttribute("rentHouseCompare", compareStr == null ? "" : compareStr);

		String hisStr = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "rentHouseHistory");
		model.addAttribute("rentHouseHistory", hisStr == null ? "" : hisStr);

		//页面来源标志，主要用来从上一页面传递一个值到下一页面
		if(searchItemMap.containsKey("sourceflag"))
			model.addAttribute("sourceflag", searchItemMap.get("sourceflag"));

		//Add by 何剑波  
		//功能：在<head>标签添加<meta>的值
		model.addAttribute("titleContent",RentAction.TITLECONTENT);
		model.addAttribute("keywords",RentAction.KEYWORDS);
		model.addAttribute("description","杭州豪世华邦出租房频道是最专业最真实的杭州出租房网,为您提供【"+searchBean.getTotalRows()+"套】的杭州房屋出租信息,查找杭州出租信息,请到杭州豪世华邦。");
		
		if ("1".equals(ispage)) {
			return "houseRent.search.list";
		} else {
			
			String menuStr = null;
			if(shortValue != null)
				menuStr = this.searchService.loadSearchMenuByModuleNameSecond("3");
			else
				menuStr = this.searchService.loadSearchMenuByModuleName("3");
			model.addAttribute("jsonString", menuStr);
			return "rentHouse.List";
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=consignmentDetail" })
	public String query(HttpServletRequest request) {
		List<Furniture> furList = this.consignmentService.getFurList();
		request.setAttribute("furList", furList);
		List<Area> areas = this.consignmentService.findAllCounty();
		request.setAttribute("areas", areas);
		List<HouseType> houseType = this.consignmentService.getAllHouseType();
		request.setAttribute("Type", "3");
		request.setAttribute("houseType", houseType);
		request.setAttribute("pageName", "house_rent");
		return "consignment.house.rent.houserent";
	}

	@RequestMapping(params = { "actionMethod=startQuery" })
	public String startQuery(HttpServletRequest request, HttpServletResponse reponse) {
		String shortValue = request.getParameter("shortValue");
		return shortValue != null ? "forward:/rent.show?actionMethod=dimquery&shortValue=" + shortValue
				: "forward:/rent.show?actionMethod=dimquery";
	}
	
	/**
	 * 降价查询处理
	 * @param pageBean
	 * @return
	 */
	private CutPageBean PriceComparison(CutPageBean pageBean, float minValue, float maxValue){
		if(pageBean.getDataList().size() > 0){
			List<HouseRent> list = (List<HouseRent>) pageBean.getDataList();
			for(HouseRent houseRent : list ){
				
			}
		}
		
		return pageBean; 
	}
	
	private void getSearchMap(String name, String value, Map oneMap, Map<String, String> tagsMap, Map<String, List> twoMap) {
		String fieldName;
		if (name.startsWith("ddhb_one_")) {
			fieldName = name.substring(9);
			if (!"".equals(value.trim())) {
				if (!"tags".equalsIgnoreCase(fieldName) && !"furniture".equalsIgnoreCase(fieldName)) {
					if (!"shi".equalsIgnoreCase(fieldName) && !"vervicalLocation".equalsIgnoreCase(fieldName) 
							&& !"exclusiveFlag".equalsIgnoreCase(fieldName)) {
						if (fieldName.endsWith("erpId")) {
							oneMap.put(fieldName, value);
						}else if(fieldName.endsWith("hospitalName")){
							oneMap.put(fieldName, value);
						} else {
							try{
								oneMap.put(fieldName, Long.valueOf(value));
							}catch(Exception ex){
								log.warn("查询条件["+value+"]转换成长整型失败.", ex);
							}
						}
					} else {
						try{
							oneMap.put(fieldName, Integer.valueOf(value));
						}catch(Exception ex){
							log.warn("查询条件["+value+"]转换成整型失败.", ex);
						}
						
					}
				} else {
					tagsMap.put(fieldName, value);
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
					ArrayList list = new ArrayList();
					if ("community.startSaleDate".equals(fieldName)) {
						minValue = minValue + " 00:00:01";
						maxValue = maxValue + " 23:59:59";
						maxValue = maxValue.replace("-01-01", "-12-31");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date minDate = null;
						Date maxDate = null;

						try {
							minDate = sdf.parse(minValue);
							maxDate = sdf.parse(maxValue);
						} catch (ParseException var15) {
							minDate = new Date();
							maxDate = new Date();
						}

						list.add(minDate);
						list.add(maxDate);
						twoMap.put(fieldName, list);
					} else if ("storyCount".equals(fieldName)) {
						list.add(Integer.valueOf(minValue));
						list.add(Integer.valueOf(maxValue));
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
	
	/**
	 * 轻松毕业季专题 
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=graduationSeason" })
	public String graduationSeason(HttpServletRequest request, HttpServletResponse reponse, Model model) {

		//获取页面活动广告位 
		model.addAttribute("topADBar", activityManageService.getActivityByPageAndPosition(3, 1));
		model.addAttribute("bottomADBar", activityManageService.getActivityByPageAndPosition(3, 2));
		
		return "rentHouse.graduationSeason";
	}
	
	/**
	 * 从图片集中，把户型图放到第一个位置
	 * Adding to 20150624 hejianbo
	 * @param HousePic
	 */
	private void toPhotosTop(List<HousePic> housePics){
		HousePic housePic = new HousePic();
		for(int i=0; i<housePics.size(); i++){
			//pictureName == 户型图 
			if("户型图".equals(housePics.get(i).getPictureName())){
				if(i == 0)break;
				housePic = housePics.get(0);
				housePics.set(0, housePics.get(i));
				housePics.set(i, housePic);
				break;
			}
		}
	}
}
