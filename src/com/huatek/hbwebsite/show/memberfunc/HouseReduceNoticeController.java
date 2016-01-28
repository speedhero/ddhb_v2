package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.framework.service.SystemService;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.HouseReduceNotice;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.mailer.EmailAbstract;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.HouseReduceNoticeService;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping({ "/notice.show" })
@SessionAttributes(types = { HouseReduceNoticeController.class })
public class HouseReduceNoticeController {
	@Autowired
	private EmailAbstract sendEmailService;
	@Autowired
	private HouseReduceNoticeService houseReduceNoticeService;
	@Autowired
	private SystemService systemService;
	private static final String HOUSE_NOTICE_SHOW = "house.notice.show";
	private static final String selectEmailType = "0";
	private static final String selectPhoneType = "1";
	String content = "";

	@RequestMapping(params = { "actionMethod=inputInfoPage" }, method = { RequestMethod.POST })
	public String inputInfoPage(Model model, HttpServletRequest request) {
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
		model.addAttribute("houseReduceNotice", new HouseReduceNotice());
		return "house.notice.show";
	}

	@RequestMapping(params = { "actionMethod=addNoticeWithOut" }, method = { RequestMethod.POST })
	public String addHouseNoticeWithOutLogin(Model model, @ModelAttribute HouseReduceNotice houseReduceNotice,
			BindingResult result, HttpServletRequest request) {
		boolean emailNoticeFlag = false;
		boolean phoneNoticeFlag = false;
		boolean emailFlag = false;
		boolean phoneFlag = false;
		String typeFlag = request.getParameter("typeFlag");
		String houseId = request.getParameter("houseId");
		String brokerId = request.getParameter("brokerId");
		String houseNo = request.getParameter("houseNo");
		String houseType = request.getParameter("housetype");
		if ("0".equals(typeFlag)) {
			emailFlag = true;
		}

		if ("1".equals(typeFlag)) {
			phoneFlag = true;
		}

		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		boolean valiExist = this.houseReduceNoticeService.valiDatas(platMemberInfo.getId(), Integer.valueOf(houseType),
				houseId);
		if (valiExist) {
			model.addAttribute("isExist", "isExist");
			return "ddhb.out.error";
		} else if (platMemberInfo != null) {
			houseReduceNotice.setPlatMemberInfo(platMemberInfo);
			if (emailFlag && platMemberInfo.getEmailValidationFlag() == 1) {
				emailNoticeFlag = true;
				houseReduceNotice.setNoticeEmail(platMemberInfo.getEmailAddress());
				houseReduceNotice.setEmailFlag(1);
			}

			if (phoneFlag) {
				if (platMemberInfo.getMobilephoneValidatedFlag() == 0) {
					model.addAttribute("noPhoneValid", "noPhoneValid");
					return "ddhb.out.error";
				}

				phoneNoticeFlag = true;
				houseReduceNotice.setNoticePhone(platMemberInfo.getMobilephone());
				houseReduceNotice.setPhoneFlag(1);
			}

			houseReduceNotice.setHouseId(houseId);
			houseReduceNotice.setHouseType(Integer.valueOf(houseType).intValue());
			if (houseReduceNotice.getHouseType() == 1) {
				HouseSecond noticeTime = (HouseSecond) this.houseReduceNoticeService.getObjectById(HouseSecond.class, houseId);
				houseReduceNotice.setCurrentPrice(noticeTime.getPrice().floatValue());
				houseReduceNotice.setHouseTitle(noticeTime.getTitle());
			} else if (houseReduceNotice.getHouseType() == 2) {
				HouseRent noticeTime1 = (HouseRent) this.houseReduceNoticeService.getObjectById(HouseRent.class, houseId);
				houseReduceNotice.setCurrentPrice(noticeTime1.getRentPrice());
				houseReduceNotice.setHouseTitle(noticeTime1.getTitle());
			}

			if (!"".equals(brokerId)) {
				Broker noticeTime2 = (Broker) this.houseReduceNoticeService.getObjectById(Broker.class, brokerId);
				houseReduceNotice.setBroker(noticeTime2);
			}

			Date noticeTime3 = Calendar.getInstance().getTime();
			houseReduceNotice.setCreateTime(noticeTime3);
			houseReduceNotice.setErpId(UUID.randomUUID().toString());
			houseReduceNotice.setHouseNo(houseNo);
			this.houseReduceNoticeService.save(houseReduceNotice);
			model.addAttribute("saveok", "saveok");
			if (emailFlag) {
				houseReduceNotice.setEmailFlag(1);
				this.houseReduceNoticeService.merge(houseReduceNotice);
			}

			if (phoneFlag) {
				if (!phoneNoticeFlag) {
					model.addAttribute("noPhoneValid", "noPhoneValid");
				} else {
					houseReduceNotice.setPhoneFlag(1);
					this.houseReduceNoticeService.merge(houseReduceNotice);
				}
			}

			return "ddhb.out.error";
		} else {
			model.addAttribute("sessionOut", "sessionOut");
			return "ddhb.out.error";
		}
	}

	private void validatehouseReduceNotice(HouseReduceNotice houseReduceNotice, Errors errors, HttpServletRequest request) {
		String noticeEmail = houseReduceNotice.getNoticeEmail().trim();
		if (!noticeEmail.equals("请输入邮箱地址...")
				&& noticeEmail != null
				&& !noticeEmail.equals("")
				&& !Util.getMatchResult(noticeEmail,
						"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.rejectValue("noticeEmail", "house.reduce.email");
		}

		String noticePhone = houseReduceNotice.getNoticePhone().trim();
		if (noticeEmail.equals("请输入邮箱地址...") && noticePhone.equals("请输入手机号码...")) {
			errors.rejectValue("noticePhone", "house.reduce.withoutconnection");
		}

	}

	@RequestMapping(params = { "actionMethod=acticeEmailAddress" })
	public String acticeEmailAddress(Model model, HttpServletRequest request) {
		String erpId = request.getParameter("erpId");
		this.houseReduceNoticeService.acticeEmail(erpId);
		return "valid.email.success";
	}
}
