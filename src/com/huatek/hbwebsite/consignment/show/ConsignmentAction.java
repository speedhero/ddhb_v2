package com.huatek.hbwebsite.consignment.show;

import cn.hshb.web.house.enums.EnumEntrustType;

import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CBDWebsite;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.consignment.entity.Entrust;
import com.huatek.hbwebsite.consignment.entity.EntrustBhouse;
import com.huatek.hbwebsite.consignment.entity.EntrustRenthouse;
import com.huatek.hbwebsite.consignment.entity.EntrustRentinghouse;
import com.huatek.hbwebsite.consignment.entity.EntrustSalehouse;
import com.huatek.hbwebsite.consignment.service.ConsignmentService;
import com.huatek.hbwebsite.house.entity.HouseType;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.GsonUtil;
import com.opensymphony.oscache.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 我要买房/我要卖房/我要租房/我要出租 等信息反馈功能
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 *
 */
@Controller
@RequestMapping({ "/consignment.show" })
public class ConsignmentAction {
	private static final String HOUSE_BUY = "consignment.house.buy";
	private static final String HOUSE_SELL = "consignment.house.sell";
	private static final String HOUSE_RENT = "consignment.house.rent";
	private static final String HOUSE_RENTING = "consignment.house.renting";
	private static final Logger LOGGER = Logger.getLogger(ConsignmentAction.class);
	private static final String OTHER = "请输入其他需要说明的要求...";

	@Autowired
	private ConsignmentService consignmentService;

	/**
	 * 预约表单
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=consignmentDetail" })
	public String query(@RequestParam("type") int type, HttpServletRequest request, Model model) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		request.setAttribute("platMemberInfo", platMemberInfo);
		model.addAttribute("Type", String.valueOf(type));
		model.addAttribute("houseType", consignmentService.getAllHouseType());

		if (type == EnumEntrustType.BUY.value()) {
			model.addAttribute("title", EnumEntrustType.BUY.caption());
			model.addAttribute("areas", consignmentService.findAllCounty());
			model.addAttribute("pageName", "house_buy");
		} else if (type == EnumEntrustType.SALE.value()) {
			model.addAttribute("title", EnumEntrustType.SALE.caption());
			model.addAttribute("pageName", "house_sell");
		} else if (type == EnumEntrustType.RENT.value()) {
			model.addAttribute("title", EnumEntrustType.RENT.caption());
			model.addAttribute("furList", consignmentService.getFurList());
			model.addAttribute("areas", consignmentService.findAllCounty());
			model.addAttribute("pageName", "house_rent");
		} else {
			model.addAttribute("title", EnumEntrustType.FOR_RENT.caption());
			model.addAttribute("furList", consignmentService.getFurList());
			model.addAttribute("pageName", "house_renting");
		}
		return "consignment.service.show";
	}

	/**
	 * 保存预约信息
	 * @param request
	 * @param model
	 * @param entrustBhouse
	 * @param result
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=addConsignment" }, method = { RequestMethod.POST })
	public String addConsignment(HttpServletRequest request, Model model, @ModelAttribute EntrustBhouse entrust,
			BindingResult result) {
		this.validateEntrustBuy(entrust, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "EntrustFrom"));
			return "ddhb.out.error";
		} else {
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (platMemberInfo != null) {
				entrust.setUserNo(platMemberInfo.getId());
			}

			if (entrust.getOther() == null && entrust.getOther().trim().equals(OTHER)) {
				entrust.setOther("");
			}
			if(entrust.getGender() == 0)
				entrust.setName("先生");
			else
				entrust.setName("女士");
			entrust.setDeleteFlag(Integer.valueOf(0));
			entrust.setSynchronizedFlag(Integer.valueOf(0));
			entrust.setLastModified(new Date());
			this.consignmentService.save(entrust);
			model.addAttribute("saveok", "saveok");
			return "ddhb.out.error";
		}
	}

	private void validateEntrustBuy(Entrust entrust, Errors errors, HttpServletRequest request) {
		if (StringUtil.isEmpty(entrust.getVerifyCode())) {
			errors.reject("验证码为必填项");
		} else {
			String sessId = request.getSession().getId();
			if (!CaptchaServiceSingleton.getInstance().validateResponseForID(sessId, entrust.getVerifyCode().toLowerCase())) {
				errors.reject("验证码有误");
			}
		}
//		Modify by 何剑波
//		if (entrust.getLocFlag().intValue() == 2) {
//			if (CommonUtil.isZeroLengthTrimString(entrust.getCbdNo())) {
//				errors.reject("请选择商圈");
//			}
//		} else 
//			if (entrust.getLocFlag().intValue() == 3) {
		if("请输入其他需要说明的要求...".equals(entrust.getOther())){
			entrust.setOther("");
		}
			if (!CommonUtil.isZeroLengthTrimString(entrust.getCommunityName()) && !entrust.getCommunityName().trim().equals("请输入小区名称...")) {
				if (entrust.getCommunityName().toString().getBytes(Charset.forName("GBK")).length > 32) {
					errors.reject("小区名称超长");
				} else {
					List<Community> listCommunity = consignmentService.getCommunityIdByCommName(entrust.getCommunityName());
					if (listCommunity != null && listCommunity.size() > 0) {
						entrust.setCommunityId(listCommunity.get(0).getErpId());
					} else {
						errors.reject("未找到该小区");
					}
				}
			} else {
				if(entrust.getLocFlag() != null){
					if(entrust.getLocFlag().intValue() != 1 && entrust.getLocFlag().intValue() != 2)
						errors.reject("请填写小区名称");
					if(entrust.getLocFlag().intValue() == 2){
						if (CommonUtil.isZeroLengthTrimString(entrust.getCbdNo())) {
							errors.reject("请选择商圈");
						}
					}
				}else{
					errors.reject("请填写小区名称");
				}
			}
//		}

//		if (!CommonUtil.isZeroLengthTrimString(String.valueOf(entrust.getMinPrice()))
//				&& !CommonUtil.isZeroLengthTrimString(String.valueOf(entrust.getMaxPrice()))) {
//			if (!Util.getMatchResult(String.valueOf(entrust.getMinPrice()), "^\\d+$")) {
//				errors.reject("请输入正确的预算价格");
//			}
//		} else {
//			errors.reject("预算价格为必填项");
//		}

//		int minShi1 = entrust.getMinShi().intValue();
//		int maxShi = entrust.getMaxShi().intValue();
//		if (minShi1 > maxShi) {
//			errors.reject("请选择正确户型范围");
//		}
//
//		if (entrust.getOtherContect().getBytes(Charset.forName("GBK")).length > 200) {
//			errors.reject("其它信息输入超长");
//		}

//		if (CommonUtil.isZeroLengthTrimString(entrust.getName())) {
//			errors.reject("联系人为必填项");
//		}
//
//		if (entrust.getName().getBytes(Charset.forName("GBK")).length > 32) {
//			errors.reject("联系人输入超长");
//		}

		if (CommonUtil.isZeroLengthTrimString(entrust.getPhone())) {
			errors.reject("联系电话为必填项");
		} else if (!Util.getMatchResult(entrust.getPhone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式有误");
		}
	}

	@RequestMapping(params = { "actionMethod=addRenthouse" }, method = { RequestMethod.POST })
	public String addToEntrustRenthouse(HttpServletRequest request, Model model,
			@ModelAttribute EntrustRenthouse entrust, BindingResult result) {
		this.validateEntrustBuy(entrust, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "EntrustFrom"));
			return "ddhb.out.error";
		} else {
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (platMemberInfo != null) {
				entrust.setUserNo(platMemberInfo.getId());
			}
//			Modify By 何剑波
//			if (entrust.getMinPrice().intValue() > entrust.getMinPrice().intValue()) {
//				int minPrice = entrust.getMinPrice().intValue();
//				int maxPrice = entrust.getMaxPrice().intValue();
//				entrust.setMaxPrice(Integer.valueOf(minPrice));
//				entrust.setMinPrice(Integer.valueOf(maxPrice));
//			}

//			if (entrust.getOther() == null && entrust.getOther().trim().equals("请输入其他需要说明的要求...")) {
//				entrust.setOther("");
//			}

			if(entrust.getGender() == 0)
				entrust.setName("先生");
			else
				entrust.setName("女士");
			entrust.setDeleteFlag(Integer.valueOf(0));
			entrust.setSynchronizedFlag(Integer.valueOf(0));
			entrust.setLastModified(new Date());
			this.consignmentService.save(entrust);
			model.addAttribute("saveok", "saveok");
			return "ddhb.out.error";
		}
	}

	@RequestMapping(params = { "actionMethod=addRentinghouse" }, method = { RequestMethod.POST })
	public String addToEntrustRentinghouse(HttpServletRequest request, Model model,
			@ModelAttribute EntrustRentinghouse entrust, BindingResult result) {
		this.validateEntrustRenting(entrust, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "entrustRenthouse"));
			return "ddhb.out.error";
		} else {
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (platMemberInfo != null) {
				entrust.setUserNo(platMemberInfo.getId());
			}

			if (entrust.getOtherRequirement() == null
					&& entrust.getOtherRequirement().trim().equals(OTHER)) {
				entrust.setOtherRequirement("");
			}
			if("请输入其他需要说明的要求...".equals(entrust.getOtherRequirement())){
				entrust.setOtherRequirement("");
			}
			if(entrust.getGender() == 0)
				entrust.setUserName("先生");
			else
				entrust.setUserName("女士");
			entrust.setDeleteFlag(Integer.valueOf(0));
			entrust.setSynchronizedFlag(Integer.valueOf(0));
			entrust.setLastModified(new Date());
			this.consignmentService.save(entrust);
			model.addAttribute("saveok", "saveok");
			return "ddhb.out.error";
		}
	}

	/**
	 * 我要出租预约
	 * @param entrust
	 * @param errors
	 * @param request
	 */
	private void validateEntrustRenting(EntrustRentinghouse entrust, Errors errors, HttpServletRequest request) {
		if (!CommonUtil.isZeroLengthTrimString(entrust.getCommunityName())
				&& !entrust.getCommunityName().trim().equals("请输入小区名称...")) {
			if (entrust.getCommunityName().getBytes(Charset.forName("GBK")).length > 32) {
				errors.reject("小区名称超长");
			}
		} else {
			errors.reject("请填写小区名称");
		}

		Integer ar = entrust.getArea();
//		Modify by 何剑波
//		if (ar == null) {
//			errors.reject("建筑面积为必填项");
//		} else if (!Util.getMatchResult(String.valueOf(entrust.getArea()), "^[0-9]*[1-9][0-9]*$")) {
//			errors.reject("建筑面积输入有误");
//		}
//
//		if (entrust.getPrice() == null) {
//			errors.reject("价格为必填项");
//		} else if (!Util.getMatchResult(String.valueOf(entrust.getPrice()), "^[0-9]+(.[0-9]{0,2})?$")) {
//			errors.reject("价格输入有误");
//		}
//
//		if (entrust.getBuildingNo() == null) {
//			errors.reject("楼号为必填项");
//		} else if (!Util.getMatchResult(String.valueOf(entrust.getBuildingNo()), "^[0-9]*[1-9][0-9]*$")) {
//			errors.reject("楼号输入有误");
//		}
//
//		if (entrust.getUnitNo() == null) {
//			errors.reject("单元号为必填项");
//		} else if (!Util.getMatchResult(String.valueOf(entrust.getUnitNo()), "^[0-9]*[1-9][0-9]*$")) {
//			errors.reject("单元号输入有误");
//		}
//
//		if (entrust.getRoomNo() == null) {
//			errors.reject("户型为必选项");
//		} else if (!Util.getMatchResult(String.valueOf(entrust.getRoomNo()), "^[0-9]*[1-9][0-9]*$")) {
//			errors.reject("户型有误");
//		}
//
//		if (entrust.getOtherRequirement().getBytes(Charset.forName("GBK")).length > 200) {
//			errors.reject("其它信息输入超长");
//		}
//
//		if (CommonUtil.isZeroLengthTrimString(entrust.getUserName())) {
//			errors.reject("联系人为必填项");
//		}
//
//		if (entrust.getUserName().getBytes(Charset.forName("GBK")).length > 32) {
//			errors.reject("联系人输入超长");
//		}
		if("请输入其他需要说明的要求...".equals(entrust.getOtherRequirement())){
			entrust.setOtherRequirement("");
		}
		if (CommonUtil.isZeroLengthTrimString(entrust.getTelePhone())) {
			errors.reject("联系电话为必填项");
		} else if (!Util.getMatchResult(entrust.getTelePhone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话输入有误");
		}

		if (CommonUtil.isZeroLengthTrimString(entrust.getVerifyCode())) {
			errors.reject("验证码为必填项");
		} else {
			boolean flag = Boolean.FALSE.booleanValue();
			String sessId = request.getSession().getId();
			flag = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(sessId, entrust.getVerifyCode().toLowerCase()).booleanValue();
			if (!flag) {
				errors.reject("验证码有误");
			}
		}

	}

	@RequestMapping(params = { "actionMethod=addtoSale" }, method = { RequestMethod.POST })
	public String addToEntrustsell(HttpServletRequest request, Model model,
			@ModelAttribute EntrustSalehouse entrust, BindingResult result) {
		this.validateBespeak(entrust, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "entrustSalehouse"));
			return "ddhb.out.error";
		} else {
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (platMemberInfo != null) {
				entrust.setUserNo(platMemberInfo.getId());
			}

			if (entrust.getOtherRequirement() == null && entrust.getOtherRequirement().trim().equals(OTHER)) {
				entrust.setOtherRequirement("");
			}
			if("请输入其他需要说明的要求...".equals(entrust.getOtherRequirement())){
				entrust.setOtherRequirement("");
			}
			if(entrust.getGender() == 0)
				entrust.setUserName("先生");
			else
				entrust.setUserName("女士");
			entrust.setDeleteFlag(Integer.valueOf(0));
			entrust.setSynchronizedFlag(Integer.valueOf(0));
			entrust.setLastModified(new Date());
			consignmentService.saveOrupdate(entrust);
			model.addAttribute("saveok", "saveok");
			return "ddhb.out.error";
		}
	}

	/**
	 * 预约录入校验
	 * @param entrustSalehouse
	 * @param errors
	 * @param request
	 */
	private void validateBespeak(EntrustSalehouse entrust, Errors errors, HttpServletRequest request) {
		if (!CommonUtil.isZeroLengthTrimString(entrust.getCommunityName())
				&& !entrust.getCommunityName().trim().equals("请输入小区名称...")) {
			if (entrust.getCommunityName().getBytes(Charset.forName("GBK")).length > 32) {
				errors.reject("小区名称超长");
			}
		} else {
			errors.reject("请填写小区名称");
		}
//		Modify By何剑波
//		Integer ar = entrust.getArea();
//		if (ar == null) {
//			errors.reject("建筑面积为必填项");
//		} else if (!Util.getMatchResult(entrust.getArea().toString(), "^[0-9]*[1-9][0-9]*$")) {
//			errors.reject("建筑面积输入有误");
//		}

//		if (entrust.getBuildingNo() != null && entrust.getUnitNo() != null) {
//			if (!Util.getMatchResult(entrust.getBuildingNo().toString(), "^[0-9]*[1-9][0-9]*$")
//					&& Util.getMatchResult(entrust.getUnitNo().toString(), "^[0-9]*[1-9][0-9]*$")) {
//				errors.reject("楼号输入有误");
//			}
//		} else {
//			errors.reject("楼号为必填项");
//		}

//		if (entrust.getRoomNo() == null) {
//			errors.reject("户型为必选项");
//		} else if (!Util.getMatchResult(entrust.getRoomNo().toString(), "^[0-9]*[1-9][0-9]*$")) {
//			errors.reject("户型有误");
//		}
//
//		if (entrust.getPrice() == null) {
//			errors.reject("价格为必填项");
//		} else if (!Util.getMatchResult(entrust.getPrice().toString(), "^[0-9]+(.[0-9]{0,2})?$")) {
//			errors.reject("价格输入有误");
//		}

		if (entrust.getOtherRequirement().getBytes(Charset.forName("GBK")).length > 200) {
			errors.reject("其它信息输入超长");
		}
		if("请输入其他需要说明的要求...".equals(entrust.getOtherRequirement())){
			entrust.setOtherRequirement("");
		}
//		if (CommonUtil.isZeroLengthTrimString(entrust.getUserName())) {
//			errors.reject("联系人为必填项");
//		}
//
//		if (entrust.getUserName().getBytes(Charset.forName("GBK")).length > 32) {
//			errors.reject("联系人输入超长");
//		}

		if (CommonUtil.isZeroLengthTrimString(entrust.getTelePhone())) {
			errors.reject("联系电话为必填项");
		} else if (!Util.getMatchResult(entrust.getTelePhone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话输入有误");
		}

		if (CommonUtil.isZeroLengthTrimString(entrust.getVerifyCode())) {
			errors.reject("验证码为必填项");
		} else {
			boolean flag = Boolean.FALSE.booleanValue();
			String sessId = request.getSession().getId();
			flag = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(sessId, entrust.getVerifyCode().toLowerCase());
			if (!flag) {
				errors.reject("验证码有误");
			}
		}

	}

	/**
	 * 获取网站商圈
	 * @param cId
	 * @param model
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = { "actionMethod=getcdb" })
	public void getCdb(@RequestParam("cid") String cId, Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<CBDWebsite> cbdList = this.consignmentService.findCbd(cId);

//		String result = GsonUtil.getGsonInstanceWithExpose().toJson(cbdList);
		StringBuilder jsonStr = new StringBuilder("");
		for(CBDWebsite cbd: cbdList){
			jsonStr.append(",").append("{\"name\":\"").append(cbd.getName()).append("\",\"websiteId\":\"").append(cbd.getWebsiteId()).append("\" }");
		}
		if(jsonStr.length()>0)
			jsonStr.deleteCharAt(0);
		jsonStr.insert(0, "[");
		jsonStr.append("]");
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(jsonStr.toString());
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(params = { "actionMethod=getcbdjson" })
	public void getcdbjson(HttpServletRequest request, HttpServletResponse response) {
		List<Community> communityList = this.consignmentService.getAllCommunity();
		String result = GsonUtil.getGsonInstanceWithExpose().toJson(communityList);

		response.setContentType("text/xml;charset-utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(result);
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(params = { "actionMethod=getPageData" })
	public String getPageData(@RequestParam("type") int type, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Area> areas = consignmentService.findAllCounty();
		List<HouseType> houseType = consignmentService.getAllHouseType();
		List<Furniture> furList = consignmentService.getFurList();
		switch (type) {
			case 1:
				model.addAttribute("areas", areas);
				model.addAttribute("houseType", houseType);
				return HOUSE_BUY;
			case 2:
				model.addAttribute("houseType", houseType);
				return HOUSE_SELL;
			case 3:
				model.addAttribute("furList", furList);
				model.addAttribute("areas", areas);
				model.addAttribute("houseType", houseType);
				return HOUSE_RENT;
			case 4:
				model.addAttribute("furList", furList);
				return HOUSE_RENTING;
			default:
				return HOUSE_BUY;
		}
	}
}
