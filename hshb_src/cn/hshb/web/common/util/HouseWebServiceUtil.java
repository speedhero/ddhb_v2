/**
 * 
 */
package cn.hshb.web.common.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.ui.Model;

import com.huatek.hbwebsite.house.entity.Apartment;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.HouseLoadCalculator;
import com.huatek.hbwebsite.util.HouseOrientationUtil;

import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.CommonDecorationType;
import cn.hshb.web.biz.mybatis.model.CommonFlag;
import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;
import cn.hshb.web.biz.mybatis.model.CommonLoanRate;
import cn.hshb.web.biz.mybatis.model.CommonOrientations;
import cn.hshb.web.biz.mybatis.model.CommonRentType;
import cn.hshb.web.biz.mybatis.model.CommonUsage;
import cn.hshb.web.biz.mybatis.model.HouseAppraise;
import cn.hshb.web.biz.mybatis.model.HousePicture;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonDecorationTypeService;
import cn.hshb.web.biz.service.CommonFlagService;
import cn.hshb.web.biz.service.CommonLiveFacilityService;
import cn.hshb.web.biz.service.CommonLoanRateService;
import cn.hshb.web.biz.service.CommonUsageService;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HousePictureService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.common.helper.HouseHelper;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author ShengYoufu
 *
 */
public class HouseWebServiceUtil {
	private static final Log log = LogFactory.getLog(HouseWebServiceUtil.class);
	
	private BBrokerService bBrokerService;
	
	private HouseCommunityService houseCommunityService;
	
	private HouseSecondhandHouseService houseSecondhandHouseService;
	
	private HouseRentService houseRentService;
	
	private CommonLiveFacilityService commonLiveFacilityService;
	
	private CommonDecorationTypeService commonDecorationTypeService;
	
	private CommonUsageService commonUsageService;
	
	private CommonFlagService commonFlagService;
	
	private HousePictureService housePictureService;
	
	private CommonLoanRateService commonLoanRateService;
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param houseId
	 * @param brokerId
	 */
	public void loadHouseSecondHandHouseFromErp(Model model, String houseId, String brokerId){
		String requestXML = this.createQuerySecondHouseXML(houseId, brokerId);
		String returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
		HouseSecondHandHouse hs = parseHouseSecondXmlToObject(returnedXml);
		model.addAttribute("houseType", EnumHouseType.SALE.getValue());

		//解析房源标签
		List<CommonFlag> tagList = houseSecondhandHouseService.parseHouseTag(hs.getTags());
		hs.setHouseTags(tagList);

		List<HousePicture> picList = hs.getPictures();
		List<HousePicture> headPic = new ArrayList<HousePicture>();
		List<HousePicture> bodyPic = new ArrayList<HousePicture>();
		uncoupleHousePic(picList, headPic, bodyPic);
		model.addAttribute("houseHeadPics", headPic);
		model.addAttribute("housePics", bodyPic);

		housePictureService.loadPictureForCommunity(hs.getCommunity());
		model.addAttribute("communityPics", hs.getCommunity().getPictures());
		
		//载入小区最近门店列表
		houseCommunityService.loadNearestStores(hs.getCommunity());

		model.addAttribute("appraiseList", hs.getAppraises());
		model.addAttribute("houseId", houseId);
		model.addAttribute("agentId", brokerId);
		
		List<CommonLoanRate> hrList = commonLoanRateService.getCommonLoanRates();
		if (hrList != null && hrList.size()>0) {
			Double loanRate = StringUtil.parseDouble(hrList.get(0).getRateVal(), 0D);
			if(loanRate>0){
				Double loanAssets = HouseLoadCalculator.getInstance()
						.calculateMoneyPerMonth(
								hs.getPrice() * HouseLoadCalculator.LOAN_TO_VALUE_RATIO / 10D, 
								HouseLoadCalculator.LOAN_MONTHS, 
								loanRate / 100.0D / 12.0D);
				model.addAttribute("loanAssets", loanAssets);
			}else{
				model.addAttribute("loanAssets", 0);
			}
		} else {
			model.addAttribute("loanAssets", 0);
		}
		model.addAttribute("loanMonths",HouseLoadCalculator.LOAN_MONTHS);
		model.addAttribute("loanRatio",HouseLoadCalculator.LOAN_TO_VALUE_RATIO);

		model.addAttribute("cHMapping", houseSecondhandHouseService.findCHMapping(hs.getCommunity().getErpId()));
		model.addAttribute("cSMapping", houseSecondhandHouseService.findCSMapping(hs.getCommunity().getErpId()));
		model.addAttribute("cStaMapping", houseSecondhandHouseService.findCStaMapping(hs.getCommunity().getErpId()));
		model.addAttribute("cSubMapping", houseSecondhandHouseService.findCSubMapping(hs.getCommunity().getErpId()));
//		float thisMonthPriceCommunity = houseSecondService.getSecondHouseCommunityThisMonthPrice(hs.getCommunity().getErpId());
//		model.addAttribute("thisMonthPriceCommunity", thisMonthPriceCommunity);
//		float lastMonthPriceCommunity = houseSecondService.getSecondHouseCommunityLastMonthPrice(hs.getCommunity().getErpId());
//		model.addAttribute("lastMonthPriceCommunity", lastMonthPriceCommunity);
//		model.addAttribute("trendList", this.communityService.getPriceTrendForSixMonth(hs.getCommunity().getErpId()));
		model.addAttribute("house", hs);
	}

	/**
	 * 把买卖房源同步XML解析成对象
	 * @param objStr
	 * @return
	 */
	private HouseSecondHandHouse parseHouseSecondXmlToObject(String xml) {
		HouseSecondHandHouse hs = new HouseSecondHandHouse();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new StringReader(xml));
			String communityId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/SubdistrictID", "");
			String houseId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseID", "");
			String houseNo = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseNo", "");
			String releaserID = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/ReleaserID", "");
			String price = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Price",  "0");
			String area = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Area",  "0");
			String visitTime = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimes", "0");
			String visitTimeRange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimeRange", "");
			String houseType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseType", "");
			String floor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Floor", "0");
			String totalFloor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/TotalFloor", "0");
			String toward = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Toward", "");
			String decorating = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Decorating", "");
			String existingState = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/ExistingState", "");
			String propertyType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PropertyType", "");
			String commentTitle = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentTitle", "");
			String commentContent = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentContent", "");
			String priceChange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PriceChange", "");
			String tags = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Tag", "");

			String[] tagArr = tags.split("\\|");
			List<String> tagIdList = new ArrayList<String>();
			for (int ii = 0; ii < tagArr.length; ++ii) {
				if(StringUtil.isEmpty(tagArr[ii])) continue;
				CommonFlag tag = commonFlagService.getHouseTagByName(tagArr[ii]);
				if(tag!=null)
					tagIdList.add(tag.getErpId());
			}
			hs.setTagIds(tagIdList);

			hs.setHouseStatus(existingState);
			List<Node> picNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/PictureList/PictureItem");
			List<HousePicture> housePicList = new ArrayList<HousePicture>();
			for(Node node: picNodeList){
				HousePicture housePic = new HousePicture();

				String layoutFlag = getNodeAsString(node, "LayoutFlag", "0");
				housePic.setLayoutflag(StringUtil.parseInt(layoutFlag, 0));

				String orderNum = getNodeAsString(node, "OrderNum", "0");
//				housePic.setOrderNum(StringUtil.parseInt(orderNum, 0));

				housePic.setPictureFormat(getNodeAsString(node, "PictureFormat", ""));
				//改成只传图片URL
				//TODO: 这是再考虑一下是否有必要从ERP那边把图片数据拉过来
				String picData = getNodeAsString(node, "PictureData", "");
//				String picURL = ERPPictureUtil.GenerateImage(picData, EnumHouseType.SALE, housePic.getPicFormat());
//				housePic.setPicUrl(picURL);
				housePic.setPictureUri(picData);
				housePicList.add(housePic);
			}
			hs.setPictures(housePicList);
		
			List<HouseAppraise> appraiseList = new ArrayList<HouseAppraise>();
			List<Node> evaNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/EvaluationList/EvaluationItem");
			for (int ii = 0; ii < evaNodeList.size(); ++ii) {
				HouseAppraise eva = new HouseAppraise();
				BBroker _broker = bBrokerService.getBroker(((Node) evaNodeList.get(ii)).selectSingleNode("ValuatorID").getText());
				eva.setPublisher(_broker);
				if(((Node) evaNodeList.get(ii)).selectSingleNode("Title")!=null)
					eva.setTitle(((Node) evaNodeList.get(ii)).selectSingleNode("Title").getText());
				if(((Node) evaNodeList.get(ii)).selectSingleNode("Content")!=null)
					eva.setContent(((Node) evaNodeList.get(ii)).selectSingleNode("Content").getText());
				appraiseList.add(eva);
			}
			hs.setAppraises(appraiseList);

			hs.setCommunity(houseCommunityService.findCommunityByCommunityId(communityId));
			hs.setErpId(houseId);
			hs.setHouseId(houseNo);
			hs.setPublisher(bBrokerService.getBroker(releaserID));
			hs.setPrice(StringUtil.parseFloat(price, 0f));
			hs.setArea(StringUtil.parseFloat(area, 0f));
			hs.setTimeToSee(visitTimeRange);
			Apartment apartment = HouseOrientationUtil.getInstance().splitApartment(houseType);
			hs.setShi(apartment.getShi());
			hs.setTing(apartment.getTing());
			hs.setWei(apartment.getWei());
			hs.setYangtai(apartment.getBalcony());
			hs.setStoreyCount(StringUtil.parseInt(totalFloor, 0));

			Float unitPrice = hs.getArea()!=0 ? hs.getPrice() / hs.getArea() : 0f;
			hs.setUnitPrice(unitPrice);
			
			//计算房源所在楼层位于总楼层的上、中或下部
			int houseVervicalLocation = HouseHelper.funFloorPoint( Integer.parseInt(floor), Integer.parseInt(totalFloor));
			hs.setHouseVervicalLocation(houseVervicalLocation);
			
			CommonOrientations orientations = new CommonOrientations();
			orientations.setOrientationName(toward);
			hs.setOrientation(orientations);
			
			CommonDecorationType decoration = commonDecorationTypeService.getDecorationTypeByName(decorating);
			hs.setDecoration(decoration);
			hs.setDaikan(StringUtil.parseInt(visitTime, 0));
			hs.setProperty(propertyType);
			
			CommonUsage usage = commonUsageService.getCommonUsageByName(propertyType);
			hs.setUsage(usage);
			hs.setTitle(commentTitle);
			hs.setContent(commentContent);
			//最近30天价格变动率
//			hs.setLastedThirtyPriceRatio(priceChange);

			return hs;
		} catch (DocumentException ex) {
			log.error("解析二手房源出错。XML文件是："+ xml, ex);
			return null;
		}catch(Exception ex){
			log.error("解析二手房源失败。XML文件是："+xml, ex);
			return null;
		}
	}
	
	/**
	 * 从ERP 获取出租房源信息
	 * @param model
	 * @param houseId
	 * @param brokerId
	 */
	public void loadHouseRentHouseFromErp(Model model, String houseId, String brokerId){
		String requestXML = this.createQueryRentHouseXML(houseId, brokerId);
		String returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
		HouseRentHouse hr = this.parseHouseRentXmlToObject(returnedXml);
		model.addAttribute("houseType", EnumHouseType.RENT.getValue());
		List<CommonLiveFacility> furList = commonLiveFacilityService.getHouseFacilitys();
		if (hr.getFurnitureIds() != null) {
			for(CommonLiveFacility fur : furList){
				for(String furId : hr.getFurnitureIds()){
					if(fur.getErpId().equals(furId)){
						try {
							hr.getFurnitures().add((CommonLiveFacility)DeepCloneUtil.cloneObject(fur));
						} catch (IllegalArgumentException ex) {
							log.error(ex);
						} catch (IllegalAccessException ex) {
							log.error(ex);
						} catch (InstantiationException ex) {
							log.error(ex);
						}
						continue;
					}
				}
			}
		}
		model.addAttribute("furList", furList);
		
		//出租房源没有房源标签 
//		model.addAttribute("tagList", rentService.getTagList());

		List<HousePicture> picList = hr.getPictures();
		List<HousePicture> headPic = new ArrayList<HousePicture>();
		List<HousePicture> bodyPic = new ArrayList<HousePicture>();
		uncoupleHousePic(picList, headPic, bodyPic);
		model.addAttribute("houseHeadPics", headPic);
		model.addAttribute("housePics", bodyPic);

		housePictureService.loadPictureForCommunity(hr.getCommunity());
		model.addAttribute("communityPics", hr.getCommunity().getPictures());

		//载入小区最近门店列表
		houseCommunityService.loadNearestStores(hr.getCommunity());

		model.addAttribute("appraiseList", hr.getAppraises());
		model.addAttribute("cHMapping", houseSecondhandHouseService.findCHMapping(hr.getCommunity().getErpId()));
		model.addAttribute("cSMapping", houseSecondhandHouseService.findCSMapping(hr.getCommunity().getErpId()));
		model.addAttribute("cStaMapping", houseSecondhandHouseService.findCStaMapping(hr.getCommunity().getErpId()));
		model.addAttribute("cSubMapping", houseSecondhandHouseService.findCSubMapping(hr.getCommunity().getErpId()));
//		model.addAttribute("trendList", communityService.getPriceTrendForSixMonth(hr.getCommunity().getErpId()));
		model.addAttribute("house", hr);

	}
	
	
	/**
	 * 把租赁房源XML解析成对象
	 * @param objStr
	 * @return
	 */
	public HouseRentHouse parseHouseRentXmlToObject(String xml) {
		HouseRentHouse hr = new HouseRentHouse();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new StringReader(xml));
			String communityId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/SubdistrictID", "");
			String houseId = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseID", "");
			String houseNo = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseNo", "");
			String releaserID = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/ReleaserID", "");
			String price = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/RentPrice", "0");
			String area = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Area", "0");
			String rentMode = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/RentMode", "");
			String paymentType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PaymentType", "");
			String visitTime = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimes", "0");
			String visitTimeRange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/VisitTimeRange", "");
			String houseType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/HouseType", "");
			String floor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Floor", "0");
			String totalFloor = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/TotalFloor", "0");
			String toward = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Toward", "");
			String decorating = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Decorating", "");
			String propertyType = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PropertyType", "");
			String commentTitle = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentTitle", "");
			String commentContent = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/CommentContent", "");
			String priceChange = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/PriceChange", "");
			String tags = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Tag", "");
			String facilities = getNodeAsString(doc, "/BasicDataSyncResult/Results/Item/Facilities", "");
			List<String> furIdList = new ArrayList<String>();
			if (StringUtil.isNotBlank(facilities)) {
				String[] furArr = facilities.split("\\|");
				for(String furId : furArr){
					CommonLiveFacility fur = commonLiveFacilityService.getHouseFurnitureByName(furId);
					furIdList.add(fur.getErpId());
				}
			}
			hr.setFurnitureIds(furIdList);

			List<Node> picNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/PictureList/PictureItem");
			List<HousePicture> housePicList = new ArrayList<HousePicture>();
			for(Node node: picNodeList){
				HousePicture housePic = new HousePicture();
				String layoutFlag = node.selectSingleNode("LayoutFlag").getText();
				layoutFlag = StringUtil.isEmpty(layoutFlag)? "0" :layoutFlag;
				housePic.setLayoutflag(StringUtil.parseInt(layoutFlag, 0));
				
				String orderNum = node.selectSingleNode("OrderNum").getText();
				orderNum = StringUtil.isBlank(orderNum)? "0" :orderNum;
//				housePic.setOrderNum(StringUtil.parseInt(orderNum, 0));

				housePic.setPictureFormat(node.selectSingleNode("PictureFormat").getText());

				//同步接口改成图片只传连接
				//TODO: 这是再考虑一下是否有必要从ERP那边把图片数据拉过来
				String picData = node.selectSingleNode("PictureData").getText();
//				String imgPath = ERPPictureUtil.GenerateImage(picData, EnumHouseType.RENT, housePic.getPicFormat());
//				housePic.setPicUrl(imgPath);
				housePic.setPictureUri(picData);
				
				housePicList.add(housePic);
			}
			hr.setPictures(housePicList);
			
			//解析房源评价
			List<HouseAppraise> appraiseList = new ArrayList<HouseAppraise>();
			List<Node> evaNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/EvaluationList/EvaluationItem");
			for(Node node : evaNodeList){
				HouseAppraise appraise = new HouseAppraise();
				BBroker _broker = bBrokerService.getBroker(node.selectSingleNode("ValuatorID").getText());
				appraise.setPublisher(_broker);
				if(node.selectSingleNode("Title")!=null)
					appraise.setTitle(node.selectSingleNode("Title").getText());
				if(node.selectSingleNode("Content")!=null)
				appraise.setContent(node.selectSingleNode("Content").getText());
				appraiseList.add(appraise);
			}
			hr.setAppraises(appraiseList);

			hr.setCommunity(houseCommunityService.findCommunityByCommunityId(communityId));
			hr.setErpId(houseId);
			hr.setHouseId(houseNo);
			hr.setPublisher(bBrokerService.getBroker(releaserID));
			hr.setRentPrice(StringUtil.parseFloat(price, 0f));
			hr.setArea(StringUtil.parseFloat(area, 0f));
			
			CommonRentType rentType = new CommonRentType();
			rentType.setRentTypeName(rentMode);
			hr.setRentType(rentType);
			
			hr.setPayforWay(paymentType);
			hr.setTimeToSee(visitTimeRange);
			
			Apartment apartment = HouseOrientationUtil.getInstance().splitApartment(houseType);
			hr.setShi(apartment.getShi());
			hr.setTing(apartment.getTing());
			hr.setWei(apartment.getWei());
			hr.setYangtai(apartment.getBalcony());
			hr.setStoreyCount(StringUtil.parseInt(floor, 1));
			
			CommonOrientations orientations = new CommonOrientations();
			orientations.setOrientationName(toward);
			hr.setOrientations(orientations);

			CommonDecorationType decoration = commonDecorationTypeService.getDecorationTypeByName(decorating);
			hr.setDecoration(decoration);
			hr.setDaikan(StringUtil.parseInt(visitTime, 0));
			hr.setProperty(propertyType);
			
			CommonUsage usage = commonUsageService.getCommonUsageByName(propertyType);
			hr.setUsage(usage);
			hr.setTitle(commentTitle);
			hr.setContent(commentContent);

			//最近30天房源价格变动率
//			hr.setLastedThirtyPriceRatio(priceChange);

			return hr;
		} catch (DocumentException ex) {
			log.error("解析出租房源失败。XML文件是："+xml, ex);
			return null;
		}catch(Exception ex){
			log.error("解析出租房源失败。XML文件是："+xml, ex);
			return null;
		}
	}
	

	/**
	 * 创建查询租赁房源请求XML
	 * 
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	public String createQueryRentHouseXML(String houseId, String agentId) {
		return createQueryHouseRequestXML("GetERPHouseForLease", houseId, agentId);
	}

	/**
	 * 创建查询买卖房源请求XML
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	public String createQuerySecondHouseXML(String houseId, String agentId) {
		return createQueryHouseRequestXML("GetERPHouseForSale", houseId, agentId);
	}

	/**
	 * 创建查询房源请求XML
	 * @param dataType
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	public String createQueryHouseRequestXML(String dataType, String houseId, String agentId){
		String strXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		strXML = strXML + "<BasicData>";
		strXML = strXML + "<DataHeader>";
		strXML = strXML + "<DataSetID>" + UUID.randomUUID() + "</DataSetID>";
		strXML = strXML + "<DataType>"+dataType+"</DataType>";
		strXML = strXML + "</DataHeader>";
		strXML = strXML + "<DataBody>";
		strXML = strXML + "<Item>";
		strXML = strXML + "<ItemID>" + UUID.randomUUID() + "</ItemID>";
		strXML = strXML + "<HouseID>" + houseId + "</HouseID>";
		strXML = strXML + "<AgentID>" + agentId + "</AgentID>";
		strXML = strXML + "</Item>";
		strXML = strXML + "</DataBody>";
		strXML = strXML + "</BasicData>";
		return strXML;
	}
	
	/**
	 * 从XML节点取值，如果为空则设为默认值
	 * @param doc			XML Document
	 * @param xmlPath		节点路径
	 * @param defaultVal	默认值
	 * @return
	 */
	private String getNodeAsString(Document doc, String xmlPath, String defaultVal){
		String val = doc.selectSingleNode(xmlPath).getText();
		val = StringUtil.isBlank(val)?defaultVal:val;
		return val;
	}
	
	/**
	 * 从XML节点取值，如果为空则设为默认值
	 * @param doc			XML Document
	 * @param xmlPath		节点路径
	 * @param defaultVal	默认值
	 * @return
	 */
	private String getNodeAsString(Node node, String xmlPath, String defaultVal){
		String val = node.selectSingleNode(xmlPath).getText();
		val = StringUtil.isBlank(val)?defaultVal:val;
		return val;
	}
	

	/**
	 * 把房源图片分成封面和其他图片
	 * @param picList
	 * @param headPic
	 * @param bodyPic
	 */
	private void uncoupleHousePic(List<HousePicture> picList, List<HousePicture> headPic, List<HousePicture> bodyPic){
		for (int ii = 0; ii < picList.size(); ++ii) {
			HousePicture housePic = picList.get(ii);
			if (housePic.getLayoutflag() == 1) {
				headPic.add(housePic);
			} else {
				bodyPic.add(housePic);
			}
		}
		//当没有封面图片时，从其他图片中随机选取一张作为封面图片
		if(headPic.size()<1 && bodyPic.size()>0){
			Random rnd = new Random();
			int idx = rnd.nextInt(bodyPic.size()); 
			headPic.add( bodyPic.get(idx));
		}
	}


	public BBrokerService getbBrokerService() {
		return bBrokerService;
	}


	public void setbBrokerService(BBrokerService bBrokerService) {
		this.bBrokerService = bBrokerService;
	}


	public HouseCommunityService getHouseCommunityService() {
		return houseCommunityService;
	}


	public void setHouseCommunityService(HouseCommunityService houseCommunityService) {
		this.houseCommunityService = houseCommunityService;
	}


	public HouseSecondhandHouseService getHouseSecondhandHouseService() {
		return houseSecondhandHouseService;
	}


	public void setHouseSecondhandHouseService(HouseSecondhandHouseService houseSecondhandHouseService) {
		this.houseSecondhandHouseService = houseSecondhandHouseService;
	}


	public HouseRentService getHouseRentService() {
		return houseRentService;
	}


	public void setHouseRentService(HouseRentService houseRentService) {
		this.houseRentService = houseRentService;
	}


	public CommonLiveFacilityService getCommonLiveFacilityService() {
		return commonLiveFacilityService;
	}


	public void setCommonLiveFacilityService(CommonLiveFacilityService commonLiveFacilityService) {
		this.commonLiveFacilityService = commonLiveFacilityService;
	}


	public CommonDecorationTypeService getCommonDecorationTypeService() {
		return commonDecorationTypeService;
	}


	public void setCommonDecorationTypeService(CommonDecorationTypeService commonDecorationTypeService) {
		this.commonDecorationTypeService = commonDecorationTypeService;
	}


	public CommonUsageService getCommonUsageService() {
		return commonUsageService;
	}


	public void setCommonUsageService(CommonUsageService commonUsageService) {
		this.commonUsageService = commonUsageService;
	}


	public CommonFlagService getCommonFlagService() {
		return commonFlagService;
	}


	public void setCommonFlagService(CommonFlagService commonFlagService) {
		this.commonFlagService = commonFlagService;
	}


	public HousePictureService getHousePictureService() {
		return housePictureService;
	}


	public void setHousePictureService(HousePictureService housePictureService) {
		this.housePictureService = housePictureService;
	}


	public CommonLoanRateService getCommonLoanRateService() {
		return commonLoanRateService;
	}


	public void setCommonLoanRateService(CommonLoanRateService commonLoanRateService) {
		this.commonLoanRateService = commonLoanRateService;
	}
}
