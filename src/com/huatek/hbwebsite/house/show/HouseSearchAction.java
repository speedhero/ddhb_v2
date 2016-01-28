package com.huatek.hbwebsite.house.show;

import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.house.enums.EnumPictureLayout;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.QueryCondition;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HousePic;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.member.service.SubscribeInfoService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.search.service.SearchService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/houseSearch.show" })
public class HouseSearchAction {
	private static final Logger log = Logger.getLogger(HouseSearchAction.class);

	private static final String EMPTY_STRING = "";
	@Autowired
	private SearchService searchService;
	@Autowired
	HouseSecondService houseSecondService;
	@Autowired
	private RentService rentService;
	@Autowired
	private SubscribeInfoService subScribeInfoService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	PlatCollectionService platCollectionService;

	/**
	 * 根据条件搜索房源
	 * @param model
	 * @param searchInput
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=doSearch" })
	public String doSearch(Model model, @RequestParam("searchInput") String searchInput, HttpServletRequest request) {
		String searchKey = searchInput;

		if(StringUtils.isNotEmpty(searchKey))
			searchKey = searchKey.trim();
		else
			searchKey = "";

		HouseAppraise houseAppraise = subScribeInfoService.getAppraiseByHouseNo(searchKey);
		if (houseAppraise != null) {
			if (houseAppraise.getHouseType() == 1) {
				request.setAttribute("houseNo", searchKey);
				return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=1&houseNo="+searchKey;
			} else {
				request.setAttribute("hourseNo", searchKey);
				return "forward:rent.show?actionMethod=dimquery&ispage=0&type=1&houseNo="+searchKey;
			}
		} else {
			List<HouseSecond> houseSecondList = houseSecondService.findSecondHouseListByHouseNos(searchKey);
			if (houseSecondList.size() > 0) {
				request.setAttribute("houseNo", searchKey);
				return "forward:houseSecond.show?actionMethod=dimquery&ispage=0&type=1&houseNo="+searchKey;
			} else {
				List<HouseRent> housetype = rentService.findRentHouseListByHouseNos(searchKey);
				if (housetype.size() > 0) {
					request.setAttribute("hourseNo", searchKey);
					return "forward:rent.show?actionMethod=dimquery&ispage=0&type=1&houseNo="+searchKey;
				} else if (searchKey.toUpperCase().startsWith("C")) {
					request.setAttribute("communityNo", searchKey);
					return "forward:community.show?actionMethod=dimquery&ispage=0&type=1&houseNo="+searchKey;
				} else {
					String houseType = request.getParameter("houseType");
					String requesttype = request.getParameter("requesttype");
					String isCutPage = request.getParameter("isCutPage");
					if (houseType != null) {
						if (houseType.equals("2")) {
							CutPageBean conditionPageBean = QueryCondition.getQueryPageBean(request);
							List<CommonBean> conditionPageBean1 = QueryCondition.getQueryCondition(request, HouseRent.class);
							CutPageBean resultPageBean = rentService.searchRentHouse(searchKey, conditionPageBean, conditionPageBean1);
							model.addAttribute("pageBean", addRentPicUrl(resultPageBean));
							model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("3"));
							model.addAttribute("pageType", 2);
							model.addAttribute("isSearch", 1);
							model.addAttribute("searchinput", searchKey);
							request.setAttribute("globalUrl", "http://" + request.getServerName() + ":" + request.getServerPort()
									+ request.getContextPath());
							model.addAttribute("backType", 3);
							List<HouseRent> houseList = (List<HouseRent>)resultPageBean.getDataList();
							if (houseList != null) {
								Iterator<HouseRent> itHouse = houseList.iterator();
								while (itHouse.hasNext()) {
									HouseRent house = itHouse.next();
									String[] fursStr = house.getShowFunitures().split(",");
									house.setFurIdList(Arrays.asList(fursStr));
								}
							}
							model.addAttribute("furList", rentService.getFurList());
							
							this.judgeLoginForCollect(request, houseList);
							if (requesttype != null && requesttype.equals("1")) {
								if (isCutPage != null && isCutPage.equals("1")) { return "houseRent.search.list"; }
								return "rentHouse.List";
							}
						} else if (houseType.equals("3")) {
							CutPageBean conditionPageBean = QueryCondition.getQueryPageBean(request);
							List<CommonBean> conditionPageBean1 = QueryCondition.getQueryCondition(request, Community.class);
							CutPageBean resultPageBean = this.communityService.searchCommunity(searchKey, conditionPageBean, conditionPageBean1);
							model.addAttribute("pageBean", addCommunityPicUrl(resultPageBean));
							model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("4"));
							model.addAttribute("isSearch", 1);
							model.addAttribute("pageType", 3);
							model.addAttribute("searchinput", searchKey);
							request.setAttribute("globalUrl", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
							model.addAttribute("backType", 4);
							this.judgeCommunity(request, (List<Community>) conditionPageBean.getDataList());
							if (requesttype != null && requesttype.equals("1")) {
								if (isCutPage != null && isCutPage.equals("1")) { return "community.search.list"; }

								return "community.list.show";
							}
						} else {
							CutPageBean conditionPageBean = QueryCondition.getQueryPageBean(request);
							List<CommonBean> conditionPageBean1 = QueryCondition.getQueryCondition(request, HouseSecond.class);
							CutPageBean resultPageBean = this.houseSecondService.searchHouseSecond(searchKey, conditionPageBean, conditionPageBean1);
							model.addAttribute("pageBean", this.addHouseSecondPicUrl(resultPageBean));
							model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("2"));
							model.addAttribute("pageType", 1);
							model.addAttribute("isSearch", 1);
							model.addAttribute("searchinput", searchKey);
							request.setAttribute("globalUrl", "http://" + request.getServerName() + ":" + request.getServerPort()
									+ request.getContextPath());
							model.addAttribute("backType", 2);
							List<HouseSecond> houseList = (List<HouseSecond>)resultPageBean.getDataList();
							if (houseList != null) {
								Iterator<HouseSecond> itHouse = houseList.iterator();
								while (itHouse.hasNext()) {
									HouseSecond house = itHouse.next();
									String[] tags = house.getShowTags().split(",");
									house.setTagIdList(Arrays.asList(tags));
								}
							}

							model.addAttribute("tagList", houseSecondService.getTagList());
							this.judgeCollect(request, houseList);
							if (requesttype != null && requesttype.equals("1")) {
								if (isCutPage != null && isCutPage.equals("1")) { return "houseSecond.search.list"; }
								return "houseSecond.list";
							}
						}
					} else {
						CutPageBean conditionPageBean = QueryCondition.getQueryPageBean(request);
						List<CommonBean> conditionPageBean1 = QueryCondition.getQueryCondition(request, HouseSecond.class);
						CutPageBean resultPageBean = this.houseSecondService.searchHouseSecond(searchKey, conditionPageBean, conditionPageBean1);
						model.addAttribute("pageBean", this.addHouseSecondPicUrl(resultPageBean));
						model.addAttribute("jsonString", searchService.loadSearchMenuByModuleName("2"));
						model.addAttribute("pageType", Integer.valueOf(1));
						model.addAttribute("searchinput", searchKey);
						request.setAttribute("globalUrl", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
						model.addAttribute("backType", 2);
						List<HouseSecond> houseList = (List<HouseSecond>)resultPageBean.getDataList();
						if (houseList != null) {
							Iterator<HouseSecond> itHouse = houseList.iterator();
							while (itHouse.hasNext()) {
								HouseSecond house = itHouse.next();
								String[] tags = house.getShowTags().split(",");
								house.setTagIdList(Arrays.asList(tags));
							}
						}

						model.addAttribute("tagList", houseSecondService.getTagList());
						this.judgeCollect(request, houseList);
						if (requesttype != null && requesttype.equals("1")) {
							if (isCutPage != null && isCutPage.equals("1")) { return "houseSecond.search.list"; }
							return "houseSecond.list";
						}

						if (requesttype != null && requesttype.equals("1")) {
							if (isCutPage != null && isCutPage.equals("1")) { return "houseSecond.search.list"; }
							return "houseSecond.list";
						}
					}

					String isPage = request.getParameter("isPage");
//					return "1".equals(isPage) ? "house.second.search.list.show" : 
//						(isCutPage != null ? (isCutPage.equals("0") ? "house.search.list.show" : "house.search.list")	: 
//							"house.search.list");

					if("1".equals(isPage)){
						return "house.second.search.list.show";
					}else{
						if("0".equals(isCutPage)){
							return "house.search.list.show" ;
						}else{
							return "house.search.list";
						}
					}
				}
			}
		}
	}

	/**
	 * 设置二手房源照片URL
	 * @param pageBean
	 * @return
	 */
	public CutPageBean addHouseSecondPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];

		@SuppressWarnings("unchecked")
		List<HouseSecond> houseSecondList = (List<HouseSecond>)pageBean.getDataList();
		if (houseSecondList == null) {
			return pageBean;
		} else {
			for (int ii = 0; ii < houseSecondList.size(); ++ii) {
				ids[ii] = ((HouseSecond) houseSecondList.get(ii)).getErpId();
			}

			List<HousePic> picList = houseSecondService.getHousePicByIdsAndPicType(ids, EnumHouseType.SALE.value(), EnumPictureLayout.COVER.value(), 0);
			if (picList == null) {
				return pageBean;
			} else {
				Iterator<HouseSecond> itHouse = houseSecondList.iterator();
				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
					Iterator<HousePic> itPic = picList.iterator();

					while (itPic.hasNext()) {
						HousePic housepic = itPic.next();
						if (house.getErpId().equals(housepic.getHouseId())) {
							house.setHouseUrl(housepic.getPicUrl());
						}
					}
				}
				pageBean.setDataList(houseSecondList);
				return pageBean;
			}
		}
	}

	/**
	 * 设置租赁房源封面照片URL
	 * @param pageBean
	 * @return
	 */
	public CutPageBean addRentPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];

		@SuppressWarnings("unchecked")
		List<HouseRent> houseRentList = (List<HouseRent>)pageBean.getDataList();
		if (houseRentList == null) {
			return pageBean;
		} else {
			for (int ii = 0; ii < houseRentList.size(); ++ii) {
				ids[ii] = ((HouseRent) houseRentList.get(ii)).getErpId();
			}

			List<HousePic> picList = houseSecondService.getHousePicByIdsAndPicType(ids, EnumHouseType.RENT.value(), EnumPictureLayout.COVER.value(), 0);
			if (picList == null) {
				return pageBean;
			} else {
				Iterator<HouseRent> itHouse = houseRentList.iterator();
				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					Iterator<HousePic> itPic = picList.iterator();
					while (itPic.hasNext()) {
						HousePic housepic = itPic.next();
						if (house.getErpId().equals(housepic.getHouseId())) {
							house.setHouseUrl(housepic.getPicUrl());
						}
					}
				}
				pageBean.setDataList(houseRentList);
				return pageBean;
			}
		}
	}

	/**
	 * 设置小区照片数
	 * @param pageBean
	 * @return
	 */
	public CutPageBean addCommunityPicUrl(CutPageBean pageBean) {
		String[] ids = new String[pageBean.getPageSize()];
		@SuppressWarnings("unchecked")
		List<Community> communityList = (List<Community>)pageBean.getDataList();
		if (communityList == null) {
			return pageBean;
		} else {
			for (int ii = 0; ii < communityList.size(); ++ii) {
				ids[ii] = ((Community) communityList.get(ii)).getErpId();
			}

			List<HousePic> picList = this.houseSecondService.getHousePicByIdsAndPicType(ids, 3, EnumPictureLayout.COVER.value(), 0);
			List<Object> picCountList = this.communityService.findContPicPerCommunity(ids);	//查询房源(小区)图片数
			if (picList == null) {
				return pageBean;
			} else {
				Iterator<Community> itCommunity = communityList.iterator();

				while (itCommunity.hasNext()) {
					Community community = itCommunity.next();
					Iterator<HousePic> itHousePic = picList.iterator();
					while (itHousePic.hasNext()) {
						HousePic pic = itHousePic.next();
						if (community.getErpId().equals(pic.getHouseId())) {
							community.setCommunityUrl(pic.getPicUrl());
						}
					}

					Iterator<Object> itPicCount = picCountList.iterator();
					while (itPicCount.hasNext()) {
						Object[] objArray = (Object[]) itPicCount.next();
						if (community.getErpId().equals(objArray[0].toString())) {
							community.setCommunityPicCount(Integer.parseInt(objArray[1].toString()));
						}
					}
				}

				pageBean.setDataList(communityList);
				return pageBean;
			}
		}
	}

	/**
	 * 设置二手房源的收藏ID
	 * @param request
	 * @param houseList 二手房源列表
	 */
	private void judgeCollect(HttpServletRequest request, List<HouseSecond> houseList) {
		PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (memberInfo != null) {
			Long memberId = memberInfo.getId();
			List<HouseAppraise> haList = houseSecondService.getHouseAppraisesByHouseType(EnumHouseType.SALE.value());
			
			Map<String, String> houseMap = new HashMap<String, String>();
			Iterator<HouseAppraise> itAppraise = haList.iterator();

			while (itAppraise.hasNext()) {
				HouseAppraise houseAppraise = itAppraise.next();
				if (houseAppraise.getBroker() != null) {
					houseMap.put(houseAppraise.getHouseNo() + "-" + houseAppraise.getBroker().getErpId(), houseAppraise.getSearchno());
				}
			}

			List<PlatCollection> pcList = platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			Iterator<PlatCollection> itCollection = pcList.iterator();

			while (itCollection.hasNext()) {
				PlatCollection platCollection = itCollection.next();
				collectMap.put(platCollection.getObjectId() + "-" + platCollection.getCollectType(), platCollection.getId());
			}

			if (houseList != null) {
				Iterator<HouseSecond> itHouse = houseList.iterator();

				while (itHouse.hasNext()) {
					HouseSecond house = itHouse.next();
					String searchNo = "";
					if (house.getBroker() != null) {
						searchNo = (String) houseMap.get(house.getHouseNo() + "-" + house.getBroker().getErpId());
					}
					Long collectId = collectMap.get(searchNo + "-" + 0);
					house.setCollectId(collectId);
					house.setObjectId(searchNo);
				}
			}
		}

	}

	/**
	 * 设置租赁房源的收藏ID
	 * @param request
	 * @param houseList 租赁房源列表
	 */
	private void judgeLoginForCollect(HttpServletRequest request, List<HouseRent> houseList) {
		PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (memberInfo != null) {
			Long memberId = memberInfo.getId();
			List<HouseAppraise> haList = houseSecondService.getHouseAppraisesByHouseType(EnumHouseType.RENT.value());

			Map<String, String> houseMap = new HashMap<String, String>();
			Iterator<HouseAppraise> itAppraise = haList.iterator();
			while (itAppraise.hasNext()) {
				HouseAppraise houseAppraise = itAppraise.next();
				if (houseAppraise.getBroker() != null) {
					houseMap.put(houseAppraise.getHouseNo() + "-" + houseAppraise.getBroker().getErpId(), houseAppraise.getSearchno());
				}
			}

			List<PlatCollection> pcList = this.platCollectionService.getPlatCollectionListByMemberId(memberId);
			Map<String, Long> collectMap = new HashMap<String, Long>();
			Iterator<PlatCollection> itCollection = pcList.iterator();

			while (itCollection.hasNext()) {
				PlatCollection house = itCollection.next();
				collectMap.put(house.getObjectId() + "-" + house.getCollectType(), house.getId());
			}

			if (houseList != null) {
				Iterator<HouseRent> itHouse = houseList.iterator();

				while (itHouse.hasNext()) {
					HouseRent house = itHouse.next();
					String searchNo = "";
					if (house.getBroker() != null) {
						searchNo = (String) houseMap.get(house.getHourseNo() + "-" + house.getBroker().getErpId());
					}

					Long collectId = collectMap.get(searchNo + "-" + 1);
					house.setCollectId(collectId);
					house.setObjectId(searchNo);
				}
			}
		}

	}

	/**
	 * 根据当前会员ID，给小区信息设置收藏ID
	 * @param request
	 * @param communityList	小区列表
	 */
	public void judgeCommunity(HttpServletRequest request, List<Community> communityList) {
		PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (communityList != null && memberInfo != null) {
			Long memberId = memberInfo.getId();

			//根据会员ID查询会员收藏的房源或小区
			List<PlatCollection> platlist = platCollectionService.getPlatCollectionListByMemberId(memberId);
			Iterator<Community> itComm = communityList.iterator();
			while (itComm.hasNext()) {
				Community community = itComm.next();
				Iterator<PlatCollection> itPlat = platlist.iterator();
				while (itPlat.hasNext()) {
					PlatCollection platCollection = itPlat.next();
					if (platCollection.getObjectId().equals(community.getErpId())) {
//						community.setCollectId(1L);
						community.setCollectId(platCollection.getId());
					}
				}
			}
		}
	}
}
