package com.huatek.hbwebsite.house.show;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.Decorations;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.common.entity.HouseRate;
import com.huatek.hbwebsite.common.entity.Orientations;
import com.huatek.hbwebsite.common.entity.RentType;
import com.huatek.hbwebsite.common.entity.Tag;
import com.huatek.hbwebsite.common.entity.Usage;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.house.entity.Apartment;
import com.huatek.hbwebsite.house.entity.ERPHousePicture;
import com.huatek.hbwebsite.house.entity.Evaluation;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.ERPPictureUtil;
import com.huatek.hbwebsite.util.HouseLoadCalculator;
import com.huatek.hbwebsite.util.HouseOrientationUtil;
import com.huatek.hbwebsite.util.TwoDimensionMaker;

import cn.hshb.web.common.helper.HouseHelper;
import cn.hshb.web.house.enums.EnumHouseType;
import cn.hshb.web.partner.baidu.common.StringUtil;

@Controller
@RequestMapping({ "/houseSpecial.show" })
public class HouseFromErpAction {
	private static final Log log = LogFactory.getLog(HouseFromErpAction.class);
	
	@Autowired
	CommunityService communityService;
	
	@Autowired
	RentService rentService;
	
	@Autowired
	private HouseSecondService houseSecondService;
	
	@RequestMapping(params = { "actionMethod=showSHHouse" })
	public String showSHHouse(Model model, HttpServletRequest request) {
		String houseId = request.getParameter("HouseID");
		String agentId = request.getParameter("AgentID");
		@SuppressWarnings("unchecked")
		List<HouseSecond> houseList = this.houseSecondService.getHouseByShelvIngIdAndBrokerId(houseId, agentId, 1);
		if (houseList != null && houseList.size() > 0) {
			HouseSecond house = houseList.get(0);
			return "forward:/houseSecond.show?actionMethod=houseSecondDetail&houseNo=" + house.getHouseNo() 
					+ "&brokerId=" + agentId;
		} else {
			String requestXML = this.createQuerySecondHouseXML(houseId, agentId);
			String returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
			HouseSecond hs = this.parseHouseSecondXmlToObject(returnedXml);
//			model.addAttribute("houseType", 1);
			model.addAttribute("houseType", EnumHouseType.SALE.getValue());

			//解析房源标签
			hs.setHouseTags(houseSecondService.parseHouseTag(hs.getTags()));

			List<ERPHousePicture> picList = hs.getErpHousePicList();
			List<ERPHousePicture> headPic = new ArrayList<ERPHousePicture>();
			List<ERPHousePicture> bodyPic = new ArrayList<ERPHousePicture>();
			uncoupleHousePic(picList, headPic, bodyPic);
			model.addAttribute("houseHeadPics", headPic);
			model.addAttribute("housePics", bodyPic);

			String[] params = new String[] { hs.getCommunity().getErpId() };
			model.addAttribute("communityPics", this.houseSecondService.getHousePicByIdsAndPicType(params, 3, 3, 0));
			//载入小区最近门店列表
			//Added by syf at 2015.02.06
			communityService.loadNearestStore(hs.getCommunity());
			
			model.addAttribute("appraiseList", hs.getEvaluationList());
			model.addAttribute("houseId", houseId);
			model.addAttribute("agentId", agentId);
			List<HouseRate> hrList = this.houseSecondService.getRate();
			if (hrList != null && hrList.size()>0) {
				Double loanAssets = HouseLoadCalculator.getInstance()
						.calculateMoneyPerMonth(hs.getPrice() * HouseLoadCalculator.LOAN_TO_VALUE_RATIO / 10D, 
								HouseLoadCalculator.LOAN_MONTHS, 
								hrList.get(0).getRateVal() / 100.0D / 12.0D);
				model.addAttribute("loanAssets",loanAssets);
			} else {
				model.addAttribute("loanAssets", "暂无");
			}
			model.addAttribute("loanMonths",HouseLoadCalculator.LOAN_MONTHS);
			model.addAttribute("loanRatio",HouseLoadCalculator.LOAN_TO_VALUE_RATIO);

			model.addAttribute("cHMapping", rentService.findCHMapping(hs.getCommunity().getErpId()));
			model.addAttribute("cSMapping", rentService.findCSMapping(hs.getCommunity().getErpId()));
			model.addAttribute("cStaMapping", rentService.findCStaMapping(hs.getCommunity().getErpId()));
			model.addAttribute("cSubMapping", rentService.findCSubMapping(hs.getCommunity().getErpId()));
			float thisMonthPriceCommunity = houseSecondService.getSecondHouseCommunityThisMonthPrice(hs.getCommunity().getErpId());
			model.addAttribute("thisMonthPriceCommunity", thisMonthPriceCommunity);
			float lastMonthPriceCommunity = houseSecondService.getSecondHouseCommunityLastMonthPrice(hs.getCommunity().getErpId());
			model.addAttribute("lastMonthPriceCommunity", lastMonthPriceCommunity);
			model.addAttribute("trendList", this.communityService.getPriceTrendForSixMonth(hs.getCommunity().getErpId()));
			model.addAttribute("house", hs);
			return "houseSecond.detail.push";
		}
	}

	/**
	 * 把房源图片分成封面和其他图片
	 * @param picList
	 * @param headPic
	 * @param bodyPic
	 */
	private void uncoupleHousePic(List<ERPHousePicture> picList, List<ERPHousePicture> headPic, List<ERPHousePicture> bodyPic){
		for (int ii = 0; ii < picList.size(); ++ii) {
			ERPHousePicture housePic = picList.get(ii);
			if (housePic.getLayoutFlag() == 1) {
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
	
	/**
	 * 从XML节点取值，如果为空则设为默认值
	 * @param doc			XML Document
	 * @param xmlPath		节点路径
	 * @param defaultVal	默认值
	 * @return
	 */
	private String getNodeAsString(Document doc, String xmlPath, String defaultVal){
		String val = doc.selectSingleNode(xmlPath).getText();
		val = StringUtils.isBlank(val)?defaultVal:val;
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
		val = StringUtils.isBlank(val)?defaultVal:val;
		return val;
	}
	
	/**
	 * 把买卖房源同步XML解析成对象
	 * @param objStr
	 * @return
	 */
	private HouseSecond parseHouseSecondXmlToObject(String objStr) {
		HouseSecond hs = new HouseSecond();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new StringReader(objStr));
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
				Tag tag = this.rentService.getTagListByName(tagArr[ii]);
				tagIdList.add(tag.getErpId());
			}
			hs.setTagIdList(tagIdList);

			hs.setUseageStatus(existingState);
			List<Node> picNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/PictureList/PictureItem");
			List<ERPHousePicture> housePicList = new ArrayList<ERPHousePicture>();
			for(Node node: picNodeList){
				ERPHousePicture housePic = new ERPHousePicture();

				String layoutFlag = getNodeAsString(node, "LayoutFlag", "0");
				housePic.setLayoutFlag(Integer.valueOf(layoutFlag));
				
				String orderNum = getNodeAsString(node, "OrderNum", "0");
				housePic.setOrderNum(Integer.valueOf(orderNum));

				housePic.setPicFormat(getNodeAsString(node, "PictureFormat", ""));
				String picData = getNodeAsString(node, "PictureData", "");
				String picURL = ERPPictureUtil.GenerateImage(picData, EnumHouseType.SALE, housePic.getPicFormat());
				housePic.setPicUrl(picURL);
				housePicList.add(housePic);
			}
		
			List<Evaluation> evaList = new ArrayList<Evaluation>();
			List<Node> evaNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/EvaluationList/EvaluationItem");

			for (int ii = 0; ii < evaNodeList.size(); ++ii) {
				Evaluation eva = new Evaluation();
				Broker _broker = this.rentService.getBrokerById(((Node) evaNodeList.get(ii)).selectSingleNode("ValuatorID").getText());
				eva.setBroker(_broker);
				eva.setTitle(((Node) evaNodeList.get(ii)).selectSingleNode("Title").getText());
				eva.setContent(((Node) evaNodeList.get(ii)).selectSingleNode("Content").getText());
				evaList.add(eva);
			}

			hs.setCommunity(communityService.getCommunityListByErpId(communityId));
			hs.setErpId(houseId);
			hs.setHouseNo(houseNo);
			hs.setBroker(rentService.getBrokerById(releaserID));
			hs.setPrice(Float.valueOf(price));
			hs.setArea(Float.valueOf(area));
			hs.setTimeToSee(visitTimeRange);
			Apartment apartment = HouseOrientationUtil.getInstance().splitApartment(houseType);
			hs.setShi(Integer.valueOf(apartment.getShi()));
			hs.setTing(Integer.valueOf(apartment.getTing()));
			hs.setWei(Integer.valueOf(apartment.getWei()));
			hs.setBalcony(Integer.valueOf(apartment.getBalcony()));
			
//			hs.setStoryCount(Integer.valueOf(floor));
			hs.setStoryCount(Integer.valueOf(totalFloor));

			//计算房源所在楼层位于总楼层的上、中或下部
			int houseVervicalLocation = HouseHelper.funFloorPoint( Integer.parseInt(floor), Integer.parseInt(totalFloor));
			hs.setVervicalLocation(houseVervicalLocation);
			
			Orientations orient = new Orientations();
			orient.setOrientationName(toward);
			hs.setOrientations(orient);
			Decorations decoration = this.rentService.getDecorationsById(String.valueOf(HouseOrientationUtil.getInstance()
					.convertDecoration(decorating)));
			hs.setDecoration(decoration);
			hs.setDaikan(Integer.valueOf(visitTime));
			hs.setProperty(propertyType);
			Usage usage = this.rentService.getUsageById(String.valueOf(HouseOrientationUtil.getInstance().convertUsage(
					propertyType)));
			hs.setUsage(usage);
			hs.setTitle(commentTitle);
			hs.setContent(commentContent);
			hs.setLastedThirtyPriceRatio(priceChange);
			hs.setErpHousePicList(housePicList);
			hs.setEvaluationList(evaList);
			Float unitPrice = hs.getArea()!=0 ? hs.getPrice() / hs.getArea() : 0f;
			hs.setUnitPrice(unitPrice);
			return hs;
		} catch (DocumentException ex) {
			log.error("解析房源同步XML出错。XML文件是："+ objStr, ex);
			return null;
		}
	}

	/**
	 * 显示租赁房源
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=showRHHouse" })
	public String showRHHouse(Model model, HttpServletRequest request) {
		String houseId = request.getParameter("HouseID");
		String agentId = request.getParameter("AgentID");
		
		@SuppressWarnings("rawtypes")
		List houseList = this.houseSecondService.getHouseByShelvIngIdAndBrokerId(houseId, agentId, 2);
		
		if (houseList != null && houseList.size() > 0) {
			HouseRent house = (HouseRent) houseList.get(0);
			return "forward:/rent.show?actionMethod=houseRentDetail&houseNo=" + house.getHourseNo() + "&brokerId=" + agentId;
		} else {
			String requestXML = this.createQueryRentHouseXML(houseId, agentId);
			String returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
			HouseRent hr = this.parseHouseRentXmlToObject(returnedXml);
//			model.addAttribute("houseType", 2);
			model.addAttribute("houseType", EnumHouseType.RENT.getValue());

			List<Furniture> furList = this.rentService.getFurList();
			if (hr.getFurIdList() != null) {
				for(Furniture fur : furList){
					for(String furId: hr.getFurIdList()){
						if(fur.getErpId().equals(furId)){
							fur.setFlag(true);
							continue;
						}
					}
				}
			}
			model.addAttribute("furList", furList);

//			//取所有房源标签
//			//NOTICE: 对于租赁房源，没有房源标签，只有屋内配套设施
//			model.addAttribute("tagList", rentService.getTagList());
			
			List<ERPHousePicture> picList = hr.getErpHousePicList();
			List<ERPHousePicture> headPic = new ArrayList<ERPHousePicture>();
			List<ERPHousePicture> bodyPic = new ArrayList<ERPHousePicture>();
			uncoupleHousePic(picList, headPic, bodyPic);
			model.addAttribute("houseHeadPics", headPic);
			model.addAttribute("housePics", bodyPic);

			String[] params = new String[] { hr.getCommunity().getErpId() };
			model.addAttribute("communityPics", this.houseSecondService.getHousePicByIdsAndPicType(params, 3, 3, 0));
			//载入小区最近门店列表
			//Added by syf at 2015.02.06
			communityService.loadNearestStore(hr.getCommunity());
			
			model.addAttribute("appraiseList", hr.getEvaluationList());
			model.addAttribute("cHMapping", rentService.findCHMapping(hr.getCommunity().getErpId()));
			model.addAttribute("cSMapping", rentService.findCSMapping(hr.getCommunity().getErpId()));
			model.addAttribute("cStaMapping", rentService.findCStaMapping(hr.getCommunity().getErpId()));
			model.addAttribute("cSubMapping", rentService.findCSubMapping(hr.getCommunity().getErpId()));
			model.addAttribute("trendList", communityService.getPriceTrendForSixMonth(hr.getCommunity().getErpId()));
			model.addAttribute("house", hr);
			return "houseRent.ERP.detail";
		}
	}

	/**
	 * 创建查询租赁房源请求XML
	 * 
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	private String createQueryRentHouseXML(String houseId, String agentId) {
		return createQueryHouseRequestXML("GetERPHouseForLease", houseId, agentId);
	}

	/**
	 * 创建查询买卖房源请求XML
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	private String createQuerySecondHouseXML(String houseId, String agentId) {
		return createQueryHouseRequestXML("GetERPHouseForSale", houseId, agentId);
	}

	/**
	 * 创建查询房源请求XML
	 * @param dataType
	 * @param houseId
	 * @param agentId
	 * @return
	 */
	private String createQueryHouseRequestXML(String dataType, String houseId, String agentId){
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
	 * 把租赁房源XML解析成对象
	 * @param objStr
	 * @return
	 */
	private HouseRent parseHouseRentXmlToObject(String objStr) {
		HouseRent hr = new HouseRent();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new StringReader(objStr));
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
					Furniture fur = this.rentService.getFurListByName(furId);
					furIdList.add(fur.getErpId());
				}
			}
			hr.setFurIdList(furIdList);

			String[] tagArr = tags.split("\\|");
			List<String> tagIdList = new ArrayList<String>();
			for(String tagId: tagArr){
				tagIdList.add(rentService.getTagListByName(tagId).getErpId());
			}
			hr.setTagIdList(tagIdList);

			List<Node> picNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/PictureList/PictureItem");
			List<ERPHousePicture> housePicList = new ArrayList<ERPHousePicture>();
			for(Node node: picNodeList){
				ERPHousePicture housePic = new ERPHousePicture();
				String layoutFlag = node.selectSingleNode("LayoutFlag").getText();
				layoutFlag = StringUtils.isBlank(layoutFlag)? "0" :layoutFlag;
				housePic.setLayoutFlag(StringUtil.parseInt(layoutFlag, 0));
				
				String orderNum = node.selectSingleNode("OrderNum").getText();
				orderNum = StringUtils.isBlank(orderNum)? "0" :orderNum;
				housePic.setOrderNum(StringUtil.parseInt(orderNum, 0));

				housePic.setPicFormat(node.selectSingleNode("PictureFormat").getText());
				
				String picData = node.selectSingleNode("PictureData").getText();
				String imgPath = ERPPictureUtil.GenerateImage(picData, EnumHouseType.RENT, housePic.getPicFormat());
				housePic.setPicUrl(imgPath);
				
				housePicList.add(housePic);
			}

			List<Evaluation> evaList = new ArrayList<Evaluation>();
			List<Node> evaNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/EvaluationList/EvaluationItem");
			for(Node node : evaNodeList){
				Evaluation eva = new Evaluation();
				Broker _broker = this.rentService.getBrokerById(node.selectSingleNode("ValuatorID").getText());
				eva.setBroker(_broker);
				eva.setTitle(node.selectSingleNode("Title").getText());
				eva.setContent(node.selectSingleNode("Content").getText());
				evaList.add(eva);
			}

			hr.setCommunity(communityService.getCommunityListByErpId(communityId));
			hr.setErpId(houseId);
			hr.setHourseNo(houseNo);
			hr.setBroker(rentService.getBrokerById(releaserID));
			hr.setRentPrice(StringUtil.parseFloat(price, 0f));
			hr.setArea(StringUtil.parseFloat(area, 0f));
			
			RentType rentType = new RentType();
			rentType.setRentTypeName(rentMode);
			hr.setRentType(rentType);
			
			hr.setPayforWay(paymentType);
			hr.setTimeToSee(visitTimeRange);
			
			Apartment apartment = HouseOrientationUtil.getInstance().splitApartment(houseType);
			hr.setShi(apartment.getShi());
			hr.setTing(apartment.getTing());
			hr.setWei(apartment.getWei());
			hr.setYangtai(apartment.getBalcony());
			hr.setStoryCount(StringUtil.parseInt(floor, 1));
			
			Orientations orientations = new Orientations();
			orientations.setOrientationName(toward);
			hr.setOrientations(orientations);

			Decorations decoration = this.rentService.getDecorationsById(String.valueOf(HouseOrientationUtil.getInstance().convertDecoration(decorating)));
			hr.setDecoration(decoration);
			hr.setDaikan(StringUtil.parseInt(visitTime, 0));
			hr.setProperty(propertyType);
			Usage usage = this.rentService.getUsageById(String.valueOf(HouseOrientationUtil.getInstance().convertUsage(propertyType)));
			hr.setUsage(usage);
			hr.setTitle(commentTitle);
			hr.setContent(commentContent);
			hr.setLastedThirtyPriceRatio(priceChange);
			hr.setErpHousePicList(housePicList);
			hr.setEvaluationList(evaList);
			return hr;
		} catch (DocumentException ex) {
			log.error("解析出租房源失败。XML文件是："+objStr, ex);
			return null;
		}catch(Exception ex){
			log.error("解析出租房源失败。XML文件是："+objStr, ex);
			return null;
		}
	}


	/**
	 * 生成二维码
	 * @param agentId		经纪人ID
	 * @param houseType	房源类型
	 * @param houseNo		房源编号 
	 * @param houseId		房源ID
	 * @param pictureType	图片类型
	 * @param request	
	 * @param response
	 * @throws WriterException
	 */
	@RequestMapping(params = { "actionMethod=drawcode" })
	public void drawCode(@RequestParam("agentId") String agentId, @RequestParam("houseType") String houseType,
			@RequestParam("houseNo") String houseNo, @RequestParam("houseId") String houseId,
			@RequestParam("pictureType") String pictureType, HttpServletRequest request, HttpServletResponse response)
			throws WriterException {
		String searchNo = "";
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://").append(request.getServerName()).append(":").append(request.getServerPort())
		.append(request.getContextPath());

		int hType = Integer.valueOf(houseType);
		//if (type == 1) {
		if(hType == EnumHouseType.SALE.getValue()){
			urlBuilder.append("/houseSpecial.show?actionMethod=showSHHouse&HouseID=" + houseId + "&AgentID=" + agentId);
		} else {
			urlBuilder.append("/houseSpecial.show?actionMethod=houseRentDetail&HouseID=" + houseId + "&AgentID=" + agentId);
		}

		String picFormat = "JPEG";
		BufferedImage image = null;
		if ("large".equalsIgnoreCase(pictureType.trim())) {
			image = TwoDimensionMaker.getFinalPic(urlBuilder.toString(), searchNo, houseNo, 135, 135);
		} else if (pictureType.trim().equals("mini")) {
			image = TwoDimensionMaker.getFinalPic(urlBuilder.toString(), searchNo, houseNo, 81, 81);
		} else if (pictureType.trim().equals("alert")) {
			image = TwoDimensionMaker.getQRImage(urlBuilder.toString(), 280, 280);
		} else {
			image = TwoDimensionMaker.getFinalPic(urlBuilder.toString(), searchNo, houseNo, 135, 135);
		}

		ServletOutputStream sos = null;

		try {
			sos = response.getOutputStream();
			ImageIO.write(image, picFormat, sos);
		} catch (IOException ex) {
			log.error("给客户端生成二维码失败.", ex);
		} finally {
			if (sos != null) {
				try {
					sos.close();
				} catch (IOException ex) {
					log.warn(ex);
				}
			}
		}
	}
	
	/**
	 * 构造测试用租赁房源数据
	 * @return
	 */
	public String tmpHouseRentResultXML() {
		String xml = "";
		xml = xml + "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml = xml + "<BasicDataSyncResult>";
		xml = xml + "<ResultHeader>";
		xml = xml + "<DataSetID>86afec6e-a6c6-4d57-838d-1f8bc66178a9</DataSetID>";
		xml = xml + "<DataType> GetERPHouseForSale </DataType>";
		xml = xml + "<SyncTime>2014-05-14 10:30:01</SyncTime>";
		xml = xml + "</ResultHeader>";
		xml = xml + "<Results>";
		xml = xml + "<ResultInformation>";
		xml = xml + "<ResultCode>1</ResultCode>";
		xml = xml + "<ResultDescription>";
		xml = xml + "<![CDATA[结果描述信息]]>";
		xml = xml + "</ResultDescription>";
		xml = xml + "</ResultInformation>";
		xml = xml + "<Item>";
		xml = xml + "<ItemID>86afec6e-a6c6-4d57-838d-1f8bc6615425</ItemID>";
		xml = xml + "<SubdistrictID>1</SubdistrictID>";
		xml = xml + "<HouseID>1</HouseID>";
		xml = xml + "<HouseNo>983839283</HouseNo>";
		xml = xml + "<ReleaserID>3</ReleaserID>";
		xml = xml + "<RentPrice>5000</RentPrice>";
		xml = xml + "<Area>135</Area>";
		xml = xml + "<RentMode>整租、合租</RentMode>";
		xml = xml + "<PaymentType>季付</PaymentType>";
		xml = xml + "<VisitTimeRange>随时看房</VisitTimeRange >";
		xml = xml + "<HouseType>2房2厅1卫1阳台</HouseType>";
		xml = xml + "<Floor>5</Floor>";
		xml = xml + "<TotalFloor>7</TotalFloor>";
		xml = xml + "<Toward>南</Toward>";
		xml = xml + "<Decorating>精装</Decorating>";
		xml = xml + "<DecorateYear>2001</DecorateYear >";
		xml = xml + "<ExistingState>空</ExistingState>";
		xml = xml + "<VisitTimes>7</VisitTimes>";
		xml = xml + "<Tag>学区</Tag>";
		xml = xml + "<Facilities><![CDATA[电视|冰箱|空调]]></Facilities>";
		xml = xml + "<PropertyType>住宅</PropertyType>";
		xml = xml + "<PropertyRight>商品房</PropertyRight>\t";
		xml = xml + "<CommentTitle>XXXXXX</CommentTitle>";
		xml = xml + "<CommentContent>YYYYYYYYYYYYYYYYYY</CommentContent>";
		xml = xml + "<PrimaryFlag>1</PrimaryFlag>";
		xml = xml + "<FocusFlag>1</FocusFlag>";
		xml = xml + "<Quality>1</Quality>";
		xml = xml + "<HasKey>1</HasKey>";
		xml = xml + "<HasElevator>1</HasElevator>";
		xml = xml + "<SchoolRegionFlag>1</SchoolRegionFlag>";
		xml = xml + "<SubwayRegionFlag>1</SubwayRegionFlag>";
		xml = xml + "<PriceChange>-0.0385</PriceChange>";
		xml = xml + "<LastUpdateContent>dsafdsaffdafdsafdsafdsa</LastUpdateContent>";
		xml = xml + "<PictureList>";
		xml = xml + "<PictureItem>";
		xml = xml + "<PictureID>1</PictureID>";
		xml = xml + "<PictureType>户型图</PictureType>";
		xml = xml + "<PictureName>XXX房源客厅主图</PictureName><!--图片名称-->";
		xml = xml + "<Comment><![CDATA[ 这是从朝南窗的主图 ]]></Comment><!--图片描述-->";
		xml = xml + "<OrderNum>1</OrderNum>";
		xml = xml + "<PictureData>";
		xml = xml + this.readString();
		xml = xml + "</PictureData>";
		xml = xml + "<PictureFormat>JPG</PictureFormat>";
		xml = xml + "<LayoutFlag>2</LayoutFlag>";
		xml = xml + "</PictureItem>";
		xml = xml + "</PictureList>";
		xml = xml + "<EvaluationList>";
		xml = xml + "<EvaluationItem>";
		xml = xml + "<ValuatorID>1</ValuatorID>";
		xml = xml + "<Title>XXXXXXXXXXX</Title>";
		xml = xml + "<Content>ZZZZZZZZZZZZ</Content>";
		xml = xml + "<EvaluateTime>2014-05-14 13:01:11</EvaluateTime>";
		xml = xml + "</EvaluationItem>";
		xml = xml + "</EvaluationList>";
		xml = xml + "</Item>";
		xml = xml + "</Results>";
		xml = xml + "</BasicDataSyncResult>";
		return xml;
	}

	/**
	 * 构造测试用买卖房源数据
	 * @return
	 */
	private String tmpHouseSecondResultXML() {
		String strXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BasicDataSyncResult><ResultHeader><DataSetID>86afec6e-a6c6-4d57-838d-1f8bc66178a9</DataSetID><DataType> GetERPHouseForSale </DataType><SyncTime>2014-05-14 10:30:01</SyncTime></ResultHeader><Results><ResultInformation><ResultCode>1</ResultCode><ResultDescription></ResultDescription></ResultInformation><Item><ItemID>86afec6e-a6c6-4d57-838d-1f8bc6615425</ItemID><SubdistrictID>1</SubdistrictID><HouseID>1</HouseID><HouseNo>983839283</HouseNo><ReleaserID>1</ReleaserID><Price>5000000</Price><Area>135</Area><HouseType>2房2厅2卫1阳台</HouseType><Floor>5</Floor><TotalFloor>7</TotalFloor><Toward>南</Toward><Decorating>精装</Decorating><DecorateYear>2001</DecorateYear><ExistingState>空闲</ExistingState><VisitTimes>7</VisitTimes><PropertyType>住宅</PropertyType><PropertyRight>商品房</PropertyRight><CommentTitle>XXXXXX</CommentTitle><CommentContent>YYYYYYYYYYYYYYYYYY</CommentContent><PrimaryFlag>1</PrimaryFlag><ExclusiveFlag>1</ExclusiveFlag><FocusFlag>1</FocusFlag><Quality>1</Quality><HasKey>1</HasKey><HasElevator>1</HasElevator><SchoolRegionFlag>1</SchoolRegionFlag><SubwayRegionFlag>1</SubwayRegionFlag><VisitTimeRange>随时看房</VisitTimeRange><PriceChange>-0.0385</PriceChange><LastUpdateContent>dsafdsaffdafdsafdsafdsa</LastUpdateContent><PictureList><PictureItem><PictureID>1</PictureID><PictureType>户型图</PictureType><PictureName>XXX房源客厅主图</PictureName><Comment></Comment><OrderNum>1</OrderNum><PictureData>"
				+ this.readString()
				+ "</PictureData>"
				+ "<PictureFormat>JPG</PictureFormat>"
				+ "<LayoutFlag>1</LayoutFlag>"
				+ "</PictureItem>"
				+ "</PictureList>"
				+ "<EvaluationList>"
				+ "<EvaluationItem>"
				+ "<ValuatorID>1</ValuatorID>"
				+ "<Title>XXXXXXXXXXX</Title>"
				+ "<Content>ZZZZZZZZZZZZ</Content>"
				+ "<EvaluateTime>2014-05-14 13:01:11</EvaluateTime>"
				+ "</EvaluationItem>"
				+ "</EvaluationList>"
				+ "<Tag>无个税|地铁</Tag>" + "</Item>" + "</Results>" + "</BasicDataSyncResult>";
		return strXML;
	}

	/**
	 * 读取测试用图片数据
	 * @return
	 */
	public String readString() {
		String str = "";
		File file = new File("D:\\erppic.txt");

		try {
			FileInputStream fis = new FileInputStream(file);
			int size = fis.available();
			byte[] buffer = new byte[size];
			fis.read(buffer);
			fis.close();
			str = new String(buffer, "GB2312");
			return str;
		} catch (IOException var6) {
			return null;
		}
	}

}
