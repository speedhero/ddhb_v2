package com.huatek.hbwebsite.house.show;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;
import com.huatek.ddhb.manage.activityManage.service.ActivityManageService;
import com.huatek.ddhb.manage.frontMemberBrowsed.service.FrontMemberBrowsedService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.HouseSecondRecommendUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.HouseRate;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.consignment.service.ConsignmentService;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.entity.HouseSecondBrowseAnalyse;
import com.huatek.hbwebsite.house.entity.HouseSecondPriceTrend;
import com.huatek.hbwebsite.house.entity.HouseType;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.MemberBespeak;
import com.huatek.hbwebsite.member.entity.MemberBrowseHistory;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.HouseReduceNoticeService;
import com.huatek.hbwebsite.member.service.MemberBrowseHistoryService;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.saveQuery.service.SaveQueryConditionsService;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.search.service.SharedSearchService;
import com.huatek.hbwebsite.util.BrowseCacheUtil;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.HouseListPictureUtil;
import com.huatek.hbwebsite.util.HouseLoadCalculator;
import com.huatek.hbwebsite.util.TwoDimensionMaker;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;

import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.house.enums.EnumCompareHouseType;
import cn.hshb.web.house.enums.EnumHouseListType;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.house.enums.EnumPictureLayout;


@Controller
@RequestMapping({ "/houseSecond.show" })
public class HouseSecondAction {
	@Autowired
	HouseSecondService houseSecondService;
	@Autowired
	private SearchService searchService;
	@Autowired
	PlatCollectionService platCollectionService;
	@Autowired
	HouseReduceNoticeService houseReduceNoticeService;
	@Autowired
	MemberBrowseHistoryService memberBrowseHistoryService;
	@Autowired
	private ConsignmentService consignmentService;
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	FrontMemberBrowsedService frontMemberBrowsedService;
	@Autowired
	SharedSearchService sharedSearchService;
	
	@Autowired
	CommunityService communityService;
	
	@Autowired
	SaveQueryConditionsService saveQueryConditionsService;
	
	
	private static final Log log = LogFactory.getLog(HouseSecondAction.class);
	private static final Integer isMember = 1;
	private static final Integer notMember = 0;
	
	//<meta name="keywords" content="KEYWORDS" /> 关键字    搜索引擎预期查找
	private static final String KEYWORDS ="二手房,杭州二手房,二手房信息,杭州买房,二手房买卖";			
	//<meta name="description" content="DESCRIPTION" /> 网页内容 不超过
	private static final String DESCRIPTION = "杭州豪世华邦二手房频道是最专业最真实的杭州二手房网,为您提供大量的杭州房屋出售信息,查找杭州二手房信息,请到杭州豪世华邦。"; 		
	//<title>TITLECONTENT</title> 标题 	
	private static final String TITLECONTENT = "二手房 ";						
//	private static final int HOUSE_TYPE_SALE = 2;		//房源类型，买卖
//	private static final String COMPARE_TYPE_SALE = "S";  //房源比较类型，买卖
//	private static enum HouseListTypeEnum {IMAGE, TEXT};			//房源列表显示模式
	
	private void judgeCollect(HttpServletRequest request, List<HouseSecond> houseList) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			List<PlatCollection> list = platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			Iterator<PlatCollection> itPC = list.iterator();

			while (itPC.hasNext()) {
				PlatCollection house = itPC.next();
				collectMap.put(house.getObjectId(), house.getId());
			}

			if (houseList != null) {
				Iterator<HouseSecond> itHouse = houseList.iterator();

				while (itHouse.hasNext()) {
					HouseSecond house1 = itHouse.next();
					Long collectId = null;
					collectId = collectMap.get(house1.getShelvingID());
					house1.setCollectId(collectId);
					house1.setObjectId(house1.getErpId());
				}
			}
		}
	}

	/**
	 * 买卖房源对比
	 * @param houseNos	房源编号
	 * @param model
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=houseSecondCompare" })
	public String houseCompare(@RequestParam("houseNos") String houseNos, Model model) {
		List<HouseSecond> houseSecondList = houseSecondService.findSecondHouseListByHouseNos(houseNos);
		if (houseSecondList != null && houseSecondList.size() > 0) {
			List<HouseRate> houseRateList = houseSecondService.getRate();
			Iterator<HouseSecond> itHouse = houseSecondList.iterator();

			while (itHouse.hasNext()) {
				HouseSecond house = itHouse.next();
				List<HouseAppraise> browseCacheUtil = houseSecondService.findAppraiseListByHouseNo(house.getHouseNo());
				house.setAppCount(Integer.valueOf(browseCacheUtil.size()));
				if (houseRateList != null && houseRateList.size()>0 ) {
					Double loanAssets = HouseLoadCalculator.getInstance().calculateMoneyPerMonth(
							house.getPrice() * HouseLoadCalculator.LOAN_TO_VALUE_RATIO / 10D, 
							HouseLoadCalculator.LOAN_MONTHS,
							houseRateList.get(0).getRateVal() / 100.0D / 12.0D);
					house.setLoanAssets(loanAssets);
				} else {
					house.setLoanAssets(0.0D);
				}
				house.setLoanMonths(HouseLoadCalculator.LOAN_MONTHS);			//贷款月数
				house.setLoanRatios(HouseLoadCalculator.LOAN_TO_VALUE_RATIO);	//贷款成数
			}

			model.addAttribute("houseSecondList", houseSecondList);
			model.addAttribute("backType", 2 );
			List<Integer> browseList = new ArrayList<Integer>();
			BrowseCacheUtil bcUtil = BrowseCacheUtil.getInstance();
			itHouse = houseSecondList.iterator();
			
			while (itHouse.hasNext()) {
				HouseSecond house = itHouse.next();
				String tagStr = house.getShowTags();
				String[] tagArr = tagStr.split(",");
				List<String> tagIdList = new ArrayList<String>();

				for(String id: tagArr)
					tagIdList.add(id);
				house.setTagIdList(tagIdList);
				
				//解析房源标签
				house.setHouseTags(houseSecondService.parseHouseTag(house.getTags()));
				
				int browseCount = bcUtil.getBrowsed(Integer.valueOf(1), house.getHouseNo(), house.getBrowsed()) - 1;
				browseList.add(browseCount);
			}
			
			model.addAttribute("browseList", browseList);
			String[] houseIDs = new String[houseSecondList.size()];
			int idx = 0;
			for(HouseSecond house: houseSecondList){
				houseIDs[idx++] = house.getErpId();
			}

			HouseListPictureUtil.getCorrespondingSHPictures(houseSecondList, houseSecondService);
			return "houseSecond.compare";
		} else {
			return "redirect:houseSecond.show?actionMethod=showList";
		}
	}

	/**
	 * 显示买卖房源详情
	 * @param houseNo	房源编号
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=houseSecondDetail" })
	public String houseDetail(@RequestParam("houseNo") String houseNo, Model model, HttpServletRequest request) {
//		model.addAttribute("houseType", Integer.valueOf(1));
		model.addAttribute("houseType", EnumHouseType.SALE.getValue());
//		model.addAttribute("showCompare", "S");
		model.addAttribute("showCompare", EnumCompareHouseType.SALE.getValue());

		String brokerId = request.getParameter("brokerId");
		List<HouseAppraise> houseAppraiseList = this.houseSecondService.findAppraiseListByHouseNo(houseNo);
		HouseSecond houseSecond = null;
		if (houseAppraiseList.size() == 0) {
			houseSecond = this.houseSecondService.getHouseSecondByHouseNo(houseNo);
		} else {
			houseSecond = this.houseSecondService.findHouseSecondByHouseId(houseAppraiseList.get(0).getHouseId());
		}

		if (houseSecond == null) {
			model.addAttribute("message", "对不起，您点击的房源不存在。");
			return "house.none";
		} else {
			if(houseSecond.getCommunity()==null){
				model.addAttribute("message", "对不起，您点击的房源所在小区信息缺失。");
				return "house.none";
			}
			
			model.addAttribute("brokerId", brokerId);
			if (houseAppraiseList != null && houseAppraiseList.size() > 0) {
				for (int ii = 0; ii < houseAppraiseList.size(); ++ii) {
					//查询经纪人的持有房源量
					if (houseAppraiseList.get(ii).getBroker() != null) {
						String _brokerId = houseAppraiseList.get(ii).getBroker().getErpId();
						Long _houseCount = this.houseSecondService.findHouseSecondCountByBrokerId(_brokerId);
						houseAppraiseList.get(ii).getBroker().setHouseCount(_houseCount.intValue());
						
						//把发布人的评价排在第一位
						if (houseAppraiseList.get(ii).getBroker().getErpId().equals(brokerId)) {
							HouseAppraise appraise = houseAppraiseList.get(ii);
							houseAppraiseList.remove(ii);
							houseAppraiseList.add(0, appraise );
						}
					}
				}
			}
			model.addAttribute("appraiseList", houseAppraiseList);
			
			model.addAttribute("backType", 2);
			String[] params = new String[] { houseSecond.getShelvingID() };
			
//			model.addAttribute("houseHeadPics", this.houseSecondService.getHousePicByIdsAndPicType(params, 1, 1, 0));
//			model.addAttribute("housePics", this.houseSecondService.getHousePicByIdsAndPicType(params, 1, 2, 0));
//			List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(params, EnumHouseType.SALE.getValue(), 1, 1);
//			if (housePicList.size() > 0) {
//				houseSecond.setHouseUrl(housePicList.get(0).getPicUrl());
//			}
			List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(params, EnumHouseType.SALE.value(), EnumPictureLayout.ALL.value(), 0);
			List<HousePic> houseCoverPicList = this.houseSecondService.getHousePicByIdsAndPicType(params, EnumHouseType.SALE.value(), EnumPictureLayout.COVER.value(), 0);
			model.addAttribute("houseHeadPics", houseCoverPicList);
			// 把户型图放到第一个
			toPhotosTop(housePicList);
			model.addAttribute("housePics", housePicList);
			if (houseCoverPicList.size() > 0) {
//				houseSecond.setHouseUrl(houseCoverPicList.get(0).getPicUrl());
				houseSecond.setPictureUrl(houseCoverPicList.get(0).getPicUrl());
			}else{
				if(housePicList.size()>0){
					houseSecond.setPictureUrl(housePicList.get(0).getPicUrl());
					houseCoverPicList.add(housePicList.get(0));
				}else{
					log.warn("房源[houseNo="+houseNo+", ShelvingID="+params[0]+"]没有房源照片.");
				}
			}
			params[0] = houseSecond.getCommunity().getErpId();
			model.addAttribute("communityPics", houseSecondService.getHousePicByIdsAndPicType(params, 3, 3, 0));

			//载入小区最近门店列表
			//Added by syf at 2015.02.04
			communityService.loadNearestStore(houseSecond.getCommunity());

			model.addAttribute("cHMapping", houseSecondService.findCHMapping(houseSecond.getCommunity().getErpId()));
			model.addAttribute("cSMapping", houseSecondService.findCSMapping(houseSecond.getCommunity().getErpId()));
			model.addAttribute("cStaMapping", houseSecondService.findCStaMapping(houseSecond.getCommunity().getErpId()));
			model.addAttribute("cSubMapping", houseSecondService.findCSubMapping(houseSecond.getCommunity().getErpId()));

			//查询所有房源标签，用以与房源信息中的标签代码对照
			//TODO: 以查房源标签对照表的标签处理模式以后将不再使用，现在保留是为了兼容处理
			model.addAttribute("tagList", houseSecondService.getTagList());

			String tagsStr = houseSecond.getShowTags();
			String[] tagArr = tagsStr.split(",");
			List<String> tagIdList = new ArrayList<String>();
			for(String tagId: tagArr)
				tagIdList.add(tagId);
			houseSecond.setTagIdList(tagIdList);

			//解析房源的标签列表
			List<Tag> tagList = houseSecondService.parseHouseTag(houseSecond.getShowTags());
			houseSecond.setHouseTags(tagList);
			
			HouseAppraise houseAppraise = houseSecondService.findAppraiseByBrokerAndHouseNo(houseNo, brokerId);
			if (houseAppraise == null) {
				houseAppraise = new HouseAppraise();
				houseAppraise.setBroker(houseSecond.getBroker());
				houseAppraise.setTitle(houseSecond.getTitle());
			}
			model.addAttribute("houseAppraise", houseAppraise);

			BrowseCacheUtil browseCacheUtil = BrowseCacheUtil.getInstance();
//			int browse = browseCacheUtil.getBrowsed( 1 , houseSecond.getHouseNo(), houseSecond.getBrowsed());
			int browse = browseCacheUtil.getBrowsed( EnumHouseType.SALE.getValue() , houseSecond.getHouseNo(), houseSecond.getBrowsed());
			model.addAttribute("houseBrowsed", browse);

			model.addAttribute("house", houseSecond);

			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (platMemberInfo != null) {
				List<MemberBrowseHistory> browseHisList = memberBrowseHistoryService.findMemberBrowseHistoryByMember(
						platMemberInfo.getId(), 0, houseSecond.getErpId());
				if (browseHisList.size() > 0) {
					browseHisList.get(0).setModifiedTime(new Date());
					this.memberBrowseHistoryService.update(browseHisList.get(0));
				} else {
					MemberBrowseHistory trendList = new MemberBrowseHistory();
					trendList.setPlatMemberInfo(platMemberInfo);
					trendList.setObjectId(houseSecond.getErpId());
					trendList.setObjectType(0);
					trendList.setCreateTime(new Date());
					trendList.setModifiedTime(new Date());
					this.memberBrowseHistoryService.save(trendList);
				}

				if (houseAppraise != null) {
					int ifNoticed = houseReduceNoticeService.ifHouseNotice(houseAppraise.getSearchno(), platMemberInfo.getId());
					if (ifNoticed == 1) {
						model.addAttribute("noticeResult", "noticed");
					} else {
						model.addAttribute("noticeResult", "unnoticed");
					}
				}
			}

			HouseSecondRecommendUtil recommandUtil = (HouseSecondRecommendUtil) request.getSession().getAttribute("houseSecondHistory");
			if (recommandUtil != null) {
				String priceRange = recommandUtil.putPriceToQueue(houseSecond.getPrice());
				String areaRange = recommandUtil.putAreaToQueue(houseSecond.getArea());
				String shiRange = recommandUtil.putShiToQueue(houseSecond.getShi());
				List<HouseSecond> recommandHouseList = houseSecondService.getRecommandedHouseSecond(priceRange, areaRange,
						shiRange, houseSecond.getErpId());
				HouseListPictureUtil.getCorrespondingSHPictures(recommandHouseList, houseSecondService);
				List<Tag> _tagList = this.houseSecondService.getTagList();
				model.addAttribute("recommendList", recommandHouseList);
				model.addAttribute("tagList", _tagList);
			}

			model.addAttribute("trendList", houseSecondService.getSecondHouseTrendForSixMonth(houseSecond.getErpId()));
			model.addAttribute("lastMonthPrice", houseSecondService.getSecondHouseLastMonthPrice(houseSecond.getErpId()));
			float communityThisMonPrice = this.houseSecondService.getSecondHouseCommunityThisMonthPrice(houseSecond.getCommunity().getErpId());
			model.addAttribute("thisMonthPriceCommunity", communityThisMonPrice);
			float communityLastMonPrice = this.houseSecondService.getSecondHouseCommunityLastMonthPrice(houseSecond.getCommunity().getErpId());
			model.addAttribute("lastMonthPriceCommunity", communityLastMonPrice);
			if (communityLastMonPrice != 0.0D && communityThisMonPrice != 0.0D) {
				model.addAttribute("aroundLaseMonthCommunity", (communityThisMonPrice - communityLastMonPrice) / communityLastMonPrice * 100.0F);
			} else {
				model.addAttribute("aroundLaseMonthCommunity", Integer.valueOf(0));
			}

			List<HouseRate> rateList = this.houseSecondService.getRate();
			if (rateList != null && rateList.size() != 0) {
				double loanAssets = HouseLoadCalculator.getInstance().calculateMoneyPerMonth(
						houseSecond.getPrice() * HouseLoadCalculator.LOAN_TO_VALUE_RATIO/10D, 
						HouseLoadCalculator.LOAN_MONTHS, 
						rateList.get(0).getRateVal() / 100.0D / 12.0D);
				model.addAttribute("loanAssets", loanAssets);
			} else {
				model.addAttribute("loanAssets", "暂无");
			}
			model.addAttribute("loanMonths", HouseLoadCalculator.LOAN_MONTHS);
			model.addAttribute("loanRatios", HouseLoadCalculator.LOAN_TO_VALUE_RATIO);

			float thisMonthPrice = 0.0F;
			float lastMonthPrices = 0.0F;
			float lastYearPrices = 0.0F;
			String _houseErpId = houseSecond.getErpId();
			HouseSecondPriceTrend houseSecondPriceTrendthisMonth = this.houseSecondService.getHousePriceThisMonth(_houseErpId);
			HouseSecondPriceTrend houseSecondPriceTrendLastMonth = this.houseSecondService.getHousePriceLastMonth(_houseErpId);
			HouseSecondPriceTrend houseSecondPriceTrendLastYear = this.houseSecondService.getHousePriceLastYear(_houseErpId);
			if (houseSecondPriceTrendthisMonth != null) {
				thisMonthPrice = houseSecondPriceTrendthisMonth.getUnitPrice().floatValue();
				model.addAttribute("thisMonthPrice", Float.valueOf(thisMonthPrice));
			} else {
				model.addAttribute("thisMonthPrice", Double.valueOf(0.0D));
			}

			if (houseSecondPriceTrendLastMonth != null) {
				lastMonthPrices = houseSecondPriceTrendLastMonth.getUnitPrice().floatValue();
				model.addAttribute("laseMonthPrice", Float.valueOf(lastMonthPrices));
			} else {
				model.addAttribute("laseMonthPrice", Double.valueOf(0.0D));
			}

			if (houseSecondPriceTrendLastYear != null) {
				lastYearPrices = houseSecondPriceTrendLastYear.getUnitPrice().floatValue();
				model.addAttribute("laseYearPrice", Float.valueOf(lastYearPrices));
			} else {
				model.addAttribute("laseYearPrice", Double.valueOf(0.0D));
			}

			if ((double) thisMonthPrice != 0.0D && (double) lastMonthPrices != 0.0D) {
				model.addAttribute("aroundLaseMonth",
						Float.valueOf((thisMonthPrice - lastMonthPrices) / lastMonthPrices * 100.0F));
			} else {
				model.addAttribute("aroundLaseMonth", Integer.valueOf(0));
			}

			if ((double) thisMonthPrice != 0.0D && (double) lastYearPrices != 0.0D) {
				model.addAttribute("aroundLaseYear", Float.valueOf((thisMonthPrice - lastYearPrices) / lastYearPrices * 100.0F));
			} else {
				model.addAttribute("aroundLaseYear", Integer.valueOf(0));
			}

//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String tmp = df.format(new Date()).toString();
//			String shHistoryBespeak = houseSecond.getErpId() + "," + Timestamp.valueOf(tmp);
			String shHistoryBespeak = houseSecond.getErpId() + "," + new Timestamp(Calendar.getInstance().getTimeInMillis());
			Object queue = (Queue) request.getSession().getAttribute("shHistoryBespeakQueue");
			if (queue == null) {
				queue = new LinkedList();
			}

			((Queue) queue).add(shHistoryBespeak);
			if (((Queue) queue).size() > 50) {
				((Queue) queue).remove();
			}

			request.getSession().setAttribute("shHistoryBespeakQueue", queue);
			String clientFlag = ClientFlagUtil.getClientFlag(request);
			String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
			comparedSHString = comparedSHString==null?"":comparedSHString;
			model.addAttribute("secondHouseCompare", comparedSHString);
			
			String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
			historySHString = historySHString==null?"":historySHString;
			model.addAttribute("secondHouseHistory", historySHString);

			HouseSecondBrowseAnalyse houseAnalyse = new HouseSecondBrowseAnalyse();
			houseAnalyse.setAnalyseId(UUID.randomUUID().toString());
			houseAnalyse.setHouseId(houseSecond.getHouseNo());
			houseAnalyse.setBrowseTime(new Date());
			if(houseSecond.getCommunity().getCbd()!=null){
				houseAnalyse.setCbdId(houseSecond.getCommunity().getCbd().getErpId());
				houseAnalyse.setCbdName(houseSecond.getCommunity().getCbd().getCbdName());
				houseAnalyse.setCountyId(houseSecond.getCommunity().getCbd().getCounty().getErpId());
				houseAnalyse.setCountyName(houseSecond.getCommunity().getCbd().getCounty().getCountyName());
			}
			houseAnalyse.setCommunityId(houseSecond.getCommunity().getErpId());
			houseAnalyse.setCommunityName(houseSecond.getCommunity().getCommunityName());
			houseAnalyse.setHouseArea(houseSecond.getArea());
			houseAnalyse.setHousePrice(houseSecond.getPrice());
			houseAnalyse.setClientFlag(clientFlag);
			if (platMemberInfo != null) {
				houseAnalyse.setIsMember(isMember);
				houseAnalyse.setMemberName(platMemberInfo.getAccName());
				houseAnalyse.setRegisterTime(platMemberInfo.getRegTime());
			} else {
				houseAnalyse.setIsMember(notMember);
			}

			this.houseSecondService.save(houseAnalyse);
			//Add by 何剑波  
			//功能：在<head>标签添加<meta>的值
			//title内容房源站点地址
			model.addAttribute("titleContent","杭州二手房-"+ houseSecond.getCommunity().getRegion().getCountyName() + "-" + houseSecond.getCommunity().getCommunityName());
			model.addAttribute("keywords",null);
			model.addAttribute("description","杭州豪世华邦为您提供最新杭州" + houseSecond.getCommunity().getRegion().getCountyName() + "二手房信息，找房、买二手房不再难");
			
			return "houseSecond.detail";
		}
	}

	/**
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=bespeak" }, method = { RequestMethod.POST })
	public String goBespeak(HttpServletRequest request, HttpSession session, Model model) {
		String housetype = request.getParameter("housetype");
		String searchno = request.getParameter("searchno");
		String brokerId = request.getParameter("brokerId");
		String houseId = request.getParameter("houseId");
		String houseArea = request.getParameter("houseArea");
		String houseShi = request.getParameter("houseShi");
		String houseTing = request.getParameter("houseTing");
		String houseWei = request.getParameter("houseWei");
		String communityName = request.getParameter("communityName");
		String housePrice = request.getParameter("housePrice");
		PlatMemberInfo accountBean = (PlatMemberInfo) session.getAttribute("LoginMember");
		if (accountBean != null) {
			Long memberId = accountBean.getId();
			MemberBespeak memberBespeak = this.houseSecondService.findAppraiseByBrokerAndMember(memberId, brokerId);
			model.addAttribute("memberBespeak", memberBespeak);
		}

		model.addAttribute("brokerId", brokerId);
		model.addAttribute("houseId", houseId);
		model.addAttribute("housetype", housetype);
		model.addAttribute("searchno", searchno);
		model.addAttribute("houseArea", houseArea);
		model.addAttribute("houseTing", houseTing);
		model.addAttribute("houseShi", houseShi);
		model.addAttribute("houseWei", houseWei);
		model.addAttribute("communityName", communityName);
		model.addAttribute("housePrice", housePrice);
		return "houseSecond.bespeak";
	}

	/**
	 * 
	 * @param housetype
	 * @param request
	 * @param model
	 * @param memberBespeak
	 * @param result
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=subBespeak" })
	public String bespeakSub(@RequestParam("housetype") String housetype, HttpServletRequest request, Model model,
			@ModelAttribute MemberBespeak memberBespeak, BindingResult result) {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		this.validateBespeak(memberBespeak, result, request, accountBean);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "memberBespeakFrom"));
			return "ddhb.out.error";
		} else {
			if (!"".equals(memberBespeak.getDate())) {
				memberBespeak.setBeginEnd();
			}

			if (accountBean != null) {
				this.validateBeginEnd(memberBespeak, result, request, accountBean);
			}

			if (result.hasErrors()) {
				model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "memberBespeakFrom"));
				return "ddhb.out.error";
			} else {
				if (accountBean != null) {
					memberBespeak.setPlatMemberInfo(accountBean);
				}

				memberBespeak.setHouseType(Long.valueOf(housetype));
				String brokerId = request.getParameter("brokerId");
				Broker broker = null;
				if (brokerId != "") {
					broker = (Broker) this.houseSecondService.getObjectById(Broker.class, brokerId);
				}

				memberBespeak.setPlatBroker(broker);
				memberBespeak.setDeleteFlag(Integer.valueOf(0));
				Date date = new Date();
				memberBespeak.setCreatedTime(date);
				this.houseSecondService.saveOrupdate(memberBespeak);
				Object queue;
				SimpleDateFormat df;
				String tmp;
				String shHistoryBespeak;
				if ("1".equals(housetype)) {
					queue = (Queue) request.getSession().getAttribute("shHistoryBespeakQueue");
					if (queue == null) {
						df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						tmp = df.format(new Date()).toString();
						shHistoryBespeak = memberBespeak.getHouseId() + "," + Timestamp.valueOf(tmp);
						queue = new LinkedList();
						((Queue) queue).add(shHistoryBespeak);
					}

					this.frontMemberBrowsedService.addMemberBespeakStore((Queue) queue, memberBespeak);
					request.getSession().removeAttribute("shHistoryBespeakQueue");
				}

				if ("2".equals(housetype)) {
					queue = (Queue) request.getSession().getAttribute("rhHistoryBespeakQueue");
					if (queue == null) {
						df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						tmp = df.format(new Date()).toString();
						shHistoryBespeak = memberBespeak.getHouseId() + "," + Timestamp.valueOf(tmp);
						queue = new LinkedList();
						((Queue) queue).add(shHistoryBespeak);
					}

					this.frontMemberBrowsedService.addMemberBespeakStore((Queue) queue, memberBespeak);
					request.getSession().removeAttribute("rhHistoryBespeakQueue");
				}

				model.addAttribute("showCompare", "S");
				model.addAttribute("bespeakok", "bespeakok");
				return "ddhb.out.error";
			}
		}
	}

	/**
	 * 校验预约信息
	 * @param memberBespeak
	 * @param errors
	 * @param request
	 * @param accountBean
	 */
	private void validateBespeak(MemberBespeak memberBespeak, Errors errors, HttpServletRequest request,
			PlatMemberInfo accountBean) {
		if (CommonUtil.isZeroLengthTrimString(memberBespeak.getDate())) {
			errors.reject("日期为必填项");
		} else if (CommonUtil.isZeroLengthTrimString(memberBespeak.getTimeBespeak())) {
			errors.reject("到店时间为必填项");
		} else {
			memberBespeak.setBeginEnd();
			Date now = new Date();
			if (now.after(memberBespeak.getBespeakTimeStart())) {
				errors.reject("已经过了该时间段");
			} else if (CommonUtil.isZeroLengthTrimString(memberBespeak.getContactsPhone())) {
				errors.reject("手机号码为必填项");
			} else if (!Util.getMatchResult(memberBespeak.getContactsPhone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
				errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或xxxxxxxxxxx");
			} else if (CommonUtil.isZeroLengthTrimString(memberBespeak.getContacts())) {
				errors.reject("联系人为必填项");
			} else if (memberBespeak.getContacts().toString().getBytes(Charset.forName("GBK")).length > 50) {
				errors.reject("联系人必须小于25个汉字");
			} else if (CommonUtil.isZeroLengthTrimString(memberBespeak.getVerifyCode())) {
				errors.reject("验证码为必填项");
			} else {
				boolean flag = Boolean.FALSE.booleanValue();
				String sessId = request.getSession().getId();
				flag = CaptchaServiceSingleton.getInstance()
						.validateResponseForID(sessId, memberBespeak.getVerifyCode().toLowerCase()).booleanValue();
				if (!flag) {
					errors.reject("验证码错误");
				}
			}
		}
	}

	private void validateBeginEnd(MemberBespeak memberBespeak, Errors errors, HttpServletRequest request,
			PlatMemberInfo accountBean) {
		List list = this.houseSecondService.findAppraiseByMember(accountBean.getId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = memberBespeak.getTimeBespeak();
		String[] times = time.split(",");
		String dateBiginStr = memberBespeak.getDate().toString() + " " + times[0] + ":00:00";

		try {
			Date e = format.parse(dateBiginStr);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); ++i) {
					MemberBespeak temp = (MemberBespeak) list.get(i);
					if (e.equals(temp.getBespeakTimeStart())) {
						errors.reject("您已经预约过该时间段！");
						return;
					}
				}
			}
		} catch (ParseException var13) {
			log.error(var13.getMessage());
		}

	}

	/**
	 * 根据条件查询房源
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = { "actionMethod=dimquery" })
	public String dimQuery(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		String shortValue = request.getParameter("shortValue");
		Map<String, String> searchItemMap = new HashMap<String, String>();

		if (shortValue != null) {
			//根据短码从数据库中查询原始查询URL
			String sharedUrl = this.sharedSearchService.getSharedUrl(shortValue);
			model.addAttribute("sharedUrl", sharedUrl);
			searchItemMap = sharedSearchService.getParameterMap(sharedUrl);
		} else {
			String queryStr = request.getQueryString();
			searchItemMap = sharedSearchService.getParameterMap(queryStr);

			Map<String, String[]> paramMap = request.getParameterMap();
			for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
				String key = param.getKey();
				String[] val = param.getValue();
				String value = val[0];
				if (val.length > 1) {
					StringBuilder sb = new StringBuilder("");
					for (String v : val) {
						sb.append(v).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					value = sb.toString();
				}
				if (!searchItemMap.containsKey(key))
					searchItemMap.put(key, value);
			}

			if (request.getAttribute("houseNo") != null)
	    	searchItemMap.put("houseNo", (String)request.getAttribute("houseNo"));
			
      if (request.getAttribute("inputSearch") != null)
      		searchItemMap.put("inputSearch", (String)request.getAttribute("inputSearch"));
		}

		/**
		 * 给独家房查询条件 Key加前缀，因为在组装查询条件时，只取有前缀的参数
		 * Added by ShengYoufu at 2015.02.02
		 */
		if (searchItemMap.containsKey("exclusiveFlag")) {
			searchItemMap.put("ddhb_one_exclusiveFlag", searchItemMap.get("exclusiveFlag"));
			searchItemMap.remove("exclusiveFlag");
		}

		//获取列表分页每页显示数量
		EnumHouseListType listType = EnumHouseListType.IMAGE;
		if ("1".equals(searchItemMap.get("type")))
			listType = EnumHouseListType.TEXT;
		if (listType == EnumHouseListType.TEXT) {
			cutPageBean.setPageSize(FrontSystemSettingUtil.getInstance().getListTypeSize());
		} else {
			cutPageBean.setPageSize(FrontSystemSettingUtil.getInstance().getPicTypeSize());
		}

		Map<String, Object> oneMap = new HashMap<String, Object>();
		Map<String, List> twoMap = new HashMap<String, List>();
		Map<String, String> tagsMap = new HashMap<String, String>();

		//把查询参数放入Map
		for (Map.Entry<String, String> item : searchItemMap.entrySet()) {
			getSearchMap(item.getKey(), item.getValue(), oneMap, twoMap, tagsMap);
		}
		if(searchItemMap.containsKey("houseNo"))
			oneMap.put("houseNo", searchItemMap.get("houseNo"));
		if(searchItemMap.containsKey("inputSearch"))
			oneMap.put("inputSearch", searchItemMap.get("inputSearch"));

		String firstpay = searchItemMap.get("firstpay");
		String monthpay = searchItemMap.get("monthpay");
		String yearcount = searchItemMap.get("yearcount");
		
		if (firstpay != null || monthpay != null || yearcount != null) {
			if (StringUtils.isBlank(firstpay)) {
				firstpay = "15000@300000";
			}
			if (StringUtils.isBlank(monthpay)) {
				monthpay = "2000@3500";
			}
			if (StringUtils.isBlank(yearcount)) {
				yearcount = "20@30";
			}
			String[] farr = firstpay.split("@");
			int fmin = Integer.valueOf(farr[0]);
			int fmax = Integer.valueOf(farr[1]);
			String[] marr = monthpay.split("@");
			int mmin = Integer.valueOf(marr[0]);
			int mmax = Integer.valueOf(marr[1]);
			String[] yarr = yearcount.split("@");
			int ymin = Integer.valueOf(yarr[0]);
			int ymax = Integer.valueOf(yarr[1]);

			Map<String, Integer> valueMap = getMinAndMax(mmin, mmax, ymin * 12, ymax * 12, 
					this.houseSecondService.getRate().get(0).getRateVal() / 100.0D / 12.0D);
			double borrowMin = valueMap.get("minValue");
			double borrowMax = valueMap.get("maxValue");

			float pricemin = (float) borrowMin + fmin;
			float pricemax = (float) borrowMax + fmax;
			List<Float> list = new ArrayList<Float>();
			list.add(Float.valueOf(pricemin / 10000.0F));
			list.add(Float.valueOf(pricemax / 10000.0F));
			twoMap.put("price", list);
		}

		//拼接查询参数字符串
		StringBuilder sbSearchUrl = new StringBuilder("");
		for (Map.Entry<String, String> item : searchItemMap.entrySet()) {
			if ("actionMethod".equalsIgnoreCase(item.getKey())) continue;	//actionMethod参数不作为查询参数
			sbSearchUrl.append(item.getKey()).append("=").append(item.getValue()).append("&");
		}
		sbSearchUrl.deleteCharAt(sbSearchUrl.length() - 1);
		String searchUrl = sbSearchUrl.toString();

		//对于指定房源编号的查询，去掉其他查询条件
		if (searchItemMap.containsKey("houseNo")) {
			String _houseNo = searchItemMap.get("houseNo");
			searchItemMap.clear();
			searchItemMap.put("houseNo", _houseNo);
		}
		if (oneMap.containsKey("houseNo")) {
			Object _houseNo = oneMap.get("houseNo");
			oneMap.clear();
			oneMap.put("houseNo", _houseNo);
		}
		if (twoMap.containsKey("houseNo")) {
			List _houseNo = twoMap.get("houseNo");
			twoMap.clear();
			twoMap.put("houseNo", _houseNo);
		}
		if (tagsMap.containsKey("houseNo")) {
			String _houseNo = tagsMap.get("houseNo");
			tagsMap.clear();
			tagsMap.put("houseNo", _houseNo);
		}
		
		//对于不是通过短码查询的，则把查询地址放入model，以便分享
		if (shortValue == null) {
			String url = "houseSecond.show?actionMethod=dimquery&" + searchUrl;
			model.addAttribute("sharedUrl", url);
		}

		String sortStr = searchItemMap.get("sort");
		String orderStr = searchItemMap.get("order");
		String ispage = searchItemMap.get("ispage");

		if (sortStr == null) {
			sortStr = "sortIndex";
			orderStr = "desc";
		}
		CutPageBean pageBean = this.houseSecondService.getSearchFiledList(cutPageBean, oneMap, twoMap, tagsMap, orderStr, sortStr);

		try {
			// 存储查询的条件
			saveQueryConditionsService.saveQueryConditions(searchItemMap, "houseSecond");
		} catch (Exception ex) {
			log.error("存储查询的条件报错", ex);
		}
		
		@SuppressWarnings("unchecked")
		List<HouseSecond> houseSeconds = (List<HouseSecond>) pageBean.getDataList();
		if (houseSeconds != null) {
			for (HouseSecond house : houseSeconds) {
				List<Tag> tags = houseSecondService.parseHouseTag(house.getShowTags());
				house.setHouseTags(tags);
				
				String[] tagArr = house.getShowTags().split(",");
				List<String> tagIdList = Arrays.asList(tagArr);
				house.setTagIdList(tagIdList);
			}
		}
		model.addAttribute("backType", 2);

		//查询活动广告
		model.addAttribute("topADBar", activityManageService.getActivityByPageAndPosition(2, 1));
		model.addAttribute("bottomADBar", activityManageService.getActivityByPageAndPosition(2, 2));

//		List<Tag> tagList = this.houseSecondService.getTagList();
//		model.addAttribute("tagList", tagList);

		judgeCollect(request, houseSeconds);
//		model.addAttribute("showCompare", COMPARE_TYPE_SALE);
		model.addAttribute("showCompare", EnumCompareHouseType.SALE.getValue());
		model.addAttribute("pageBean", addPicUrl(pageBean));

		String clientFlag = ClientFlagUtil.getClientFlag(request);
		String comparedSHString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
		model.addAttribute("secondHouseCompare", comparedSHString == null ? "" : comparedSHString);

		String historySHString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
		model.addAttribute("secondHouseHistory", historySHString == null ? "" : historySHString);
		if ("1".equals(ispage)) { return "houseSecond.search.list"; }

		String returnSring = this.searchService.loadSearchMenuByModuleName("2");
		model.addAttribute("jsonString", returnSring);
		
		//Add by 何剑波  
		//功能：在<head>标签添加<meta>的值
		model.addAttribute("titleContent",HouseSecondAction.TITLECONTENT);
		model.addAttribute("keywords",HouseSecondAction.KEYWORDS);
		model.addAttribute("description","杭州豪世华邦二手房频道是最专业最真实的杭州二手房网,为您提供【"+pageBean.getTotalRows()+"套】的杭州房屋出售信息,查找杭州二手房信息,请到杭州豪世华邦。");
		
		return "houseSecond.list";
	}

	/**
	 * 在房源对象中加上房源图片
	 * 
	 * @param pageBean
	 * @return
	 */
	public CutPageBean addPicUrl(CutPageBean pageBean) {
		List<String> idsList = new ArrayList<String>();
		List<HouseSecond> houseList = (List<HouseSecond>) pageBean.getDataList();
		if (houseList == null) return pageBean;
		for (int i = 0; i < houseList.size(); i++) {
			idsList.add(((HouseSecond) houseList.get(i)).getShelvingID());
		}
		String[] ids = (String[]) idsList.toArray(new String[idsList.size()]);
		
		List<HousePic> housePicList = this.houseSecondService.getHousePicByIdsAndPicType(ids, 1, 1, 0);
		if (housePicList == null) return pageBean;

		List<String> noPicHouseIdList = new ArrayList<String>();
		Iterator<HouseSecond> itHouse = houseList.iterator();
		while (itHouse.hasNext()) {
			HouseSecond house = itHouse.next();
			Iterator<HousePic> itHousePic = housePicList.iterator();
			
			Boolean hasCoverPic = false; 
			while (itHousePic.hasNext()) {
				HousePic housepic = itHousePic.next();
				if (house.getErpId().equals(housepic.getHouseId())) {
//					house.setHouseUrl(housepic.getPicUrl());
					house.setPictureUrl(housepic.getPicUrl());
					hasCoverPic = true;
					break;
				}
			}
			String[] houseShelvingId = { house.getShelvingID() };
		      List<HousePic> list = this.houseSecondService.getHousePicByIdsAndPicType(houseShelvingId, 1, 2, 0);
		      if (list != null) {
		        house.setHousePicSize(Integer.valueOf(list.size()));
		      }
			//记下没有封面图片的房源
			if(!hasCoverPic) noPicHouseIdList.add(house.getShelvingID());
		}
		
		//对没有封面图片的房源，取其他图片作为封面图片
		//Added by Sheng Youfu at 2015.03.30
		if(noPicHouseIdList.size()>0){
			housePicList.clear();
			housePicList = this.houseSecondService.getHousePicByIdsAndPicType(noPicHouseIdList.toArray(new String[0]), EnumHouseType.SALE.value(), 0, 0);
			if(housePicList!=null){
				itHouse = houseList.iterator();
				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
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
	 * 生成房源二维码
	 * @param id
	 * @param houseType
	 * @param houseId
	 * @param pictureType
	 * @param request
	 * @param response
	 * @throws WriterException
	 */
	@RequestMapping(params = { "actionMethod=drawcode" })
	public void drawCode(@RequestParam("id") String id, @RequestParam("houseType") String houseType,
			@RequestParam("houseId") String houseId, @RequestParam("pictureType") String pictureType,
			HttpServletRequest request, HttpServletResponse response) throws WriterException {
		String searchNo = "";
		String houseNo = houseId;
		String brokerid = "";
		String s = request.getParameter("fromERP");
		String brokerId = request.getParameter("brokerId");
		if(StringUtil.isNotEmpty(s))
			brokerId = "_"+brokerId;
		else brokerId = "";
		int type = Integer.valueOf(houseType);
		if (!"".equals(id)) {
			HouseAppraise urlBuilder = (HouseAppraise) this.houseSecondService.getObjectById(HouseAppraise.class, id);
			searchNo = urlBuilder.getSearchno();
			houseNo = urlBuilder.getHouseNo();
			brokerid = urlBuilder.getBroker().getErpId();
			type = urlBuilder.getHouseType().intValue();
		}

		StringBuilder urlBuilder1 = new StringBuilder();
		urlBuilder1.append("http://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
		if (type == 1) {
			//urlBuilder1.append("/houseSecond.show?actionMethod=houseSecondDetail&houseNo=" + houseNo + "&brokerId=" + brokerid);
			urlBuilder1.append("/chushou/" + houseNo +brokerId+ ".html" );
		} else {
//			urlBuilder1.append("/rent.show?actionMethod=houseRentDetail&houseNo=" + houseNo + "&brokerId=" + brokerid);
			urlBuilder1.append("/chuzu/" + houseNo +brokerId+ ".html");
		}

		String format = "JPEG";
		BufferedImage image = null;
		if (pictureType.trim().equals("large")) {
			image = TwoDimensionMaker.getFinalPic(urlBuilder1.toString(), searchNo, houseNo, 135, 135);
		} else if (pictureType.trim().equals("mini")) {
			image = TwoDimensionMaker.getFinalPic(urlBuilder1.toString(), searchNo, houseNo, 81, 81);
		} else if (pictureType.trim().equals("alert")) {
			image = TwoDimensionMaker.getQRImage(urlBuilder1.toString(), 280, 280);
		} else {
			image = TwoDimensionMaker.getFinalPic(urlBuilder1.toString(), searchNo, houseNo, 135, 135);
		}

		ServletOutputStream sos = null;

		try {
			sos = response.getOutputStream();
			ImageIO.write(image, format, sos);
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (sos != null) {
				try {
					sos.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	@RequestMapping(params = { "actionMethod=getCalculator" })
	public String getCalculator(HttpServletRequest request, HttpSession session, Model model) {
		String unitPrice = request.getParameter("unitPrice");
		String area = request.getParameter("area");
		model.addAttribute("houseSecRate", this.houseSecondService.getRate().get(0).getRateVal());
		model.addAttribute("unitPrice", unitPrice);
		model.addAttribute("area", area);
		return "calculator.show";
	}

	@RequestMapping(params = { "actionMethod=consignmentDetail" })
	public String query(HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		request.setAttribute("platMemberInfo", platMemberInfo);
		List<Area> areas = this.consignmentService.findAllCounty();
		request.setAttribute("areas", areas);
		List<HouseType> houseType = this.consignmentService.getAllHouseType();
		request.setAttribute("Type", "1");
		request.setAttribute("houseType", houseType);
		request.setAttribute("pageName", "house_buy");
		return "consignment.house.buy.housesecond";
	}

	private Map<String, Integer> getMinAndMax(double ygMin, double ygMax, int yueMin, int yueMax, double yll) {
		int[] resultArray = new int[4];
		HouseLoadCalculator houseLoadCalculator = HouseLoadCalculator.getInstance();
		resultArray[0] = houseLoadCalculator.calculateBJ(ygMin, yueMin, yll);
		resultArray[1] = houseLoadCalculator.calculateBJ(ygMin, yueMax, yll);
		resultArray[2] = houseLoadCalculator.calculateBJ(ygMax, yueMin, yll);
		resultArray[3] = houseLoadCalculator.calculateBJ(ygMax, yueMax, yll);
		int maxValue = resultArray[0];
		int minValue = resultArray[0];
		for (int i = 0; i < 4; i++) {
			if (resultArray[i] > maxValue) {
				maxValue = resultArray[i];
			}
			if (resultArray[i] < minValue) {
				minValue = resultArray[i];
			}
		}
		Map<String, Integer> minAndMaxMap = new HashMap<String, Integer>();
		minAndMaxMap.put("maxValue", Integer.valueOf(maxValue));
		minAndMaxMap.put("minValue", Integer.valueOf(minValue));
		return minAndMaxMap;
	}

	@RequestMapping(params = { "actionMethod=startQuery" })
	public String startQuery(HttpServletRequest request, HttpServletResponse reponse) {
		String shortValue = request.getParameter("shortValue");
		// return shortValue != null ?
		// "forward:/houseSecond.show?actionMethod=dimquery&shortValue=" +
		// shortValue
		// : "forward:/houseSecond.show?actionMethod=dimquery";

		String result = "forward:/houseSecond.show?actionMethod=dimquery";
		if (shortValue != null)
			result += "&shortValue=" + shortValue;
		String isSole = request.getParameter("issole");
		if (isSole != null)
			result += "&issole=" + isSole;

		return result;
	}

	/**
	 * 提取搜索条件
	 * @param name
	 * @param value
	 * @param oneMap
	 * @param twoMap
	 * @param tagsMap
	 */
	private void getSearchMap(String name, String value, Map<String, Object> oneMap, Map<String, List> twoMap, Map<String, String> tagsMap) {
		if (name.startsWith("ddhb_one_")) {
			String fieldName = name.substring(9);
			if (!StringUtils.isBlank(value)) {
				if ("tags".equals(fieldName)) {
					tagsMap.put(fieldName, value);
				} else if ("shi".equals(fieldName) || "vervicalLocation".equals(fieldName) || "exclusiveFlag".equalsIgnoreCase(fieldName)) {
					oneMap.put(fieldName, Integer.valueOf(value));
				} else if (fieldName.endsWith("erpId")) {
					oneMap.put(fieldName, value);
				} else {
					if(StringUtil.isNumeric(value))
						oneMap.put(fieldName, Long.valueOf(value));
					else
						oneMap.put(fieldName, value);
				}
			}
		}
		if (name.startsWith("ddhb_two_")) {
			String fieldName = name.substring(9);
			if (!"".equals(value.trim())) {
				int index = value.indexOf("@");
				if (index > 0) {
					String minValue = value.substring(0, index);
					String maxValue = value.substring(index + 1);
					List<Object> list = new ArrayList<Object>();
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
						} catch (ParseException e) {
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
	 * 从图片集中，把户型图放到第一个位置
	 * Adding to 20150624 hejianbo
	 * @param HousePic
	 */
	private void toPhotosTop(List<HousePic> housePics){
		HousePic housePic = new HousePic();
		for(int i=0; i<housePics.size(); i++){
			//pictureName == 户型图 
			if("户型图".equals(housePics.get(i).getPictureName())){
				if(i == 0) break;
				housePic = housePics.get(0);
				housePics.set(0, housePics.get(i));
				housePics.set(i, housePic);
				break;
			}
		}
	}
	
}
