package com.huatek.hbwebsite.show.memberinfo;

import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.EncryptService;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping({ "/memberInfo.do" })
@SessionAttributes(types = { PlatMemberInfo.class })
public class PlatMemberInfoAction {
	@Autowired
	EncryptService encryptService;
	@Autowired
	PlatMemberInfoService platMemberInfoService;
	private static final String MEMBER_INFO_PAGE_PATH = "ddhb/front/member/platMemberInfo/editmemberinfo/edit_memberInfo.jsp";
	private static final String MEMBER_PASSWORD_PAGE_PATH = "ddhb/front/member/platMemberInfo/changepassword/changePassword.jsp";
	private static final String MEMBER_PASSWORD_SUCCESS_PAGE_PATH = "ddhb/front/member/platMemberInfo/changepassword/finishChangePassword.jsp";
	private static final String MEMBER_INIT_HEAD_PAGE_PATH = "ddhb/front/member/platMemberInfo/modifyHeader/modifyHeader.jsp";
	private static final String MEMBER_INIT_HEAD_SUCCESS_PAGE_PATH = "ddhb/front/member/platMemberInfo/modifyHeader/finishModifyHeader.jsp";
	private static final Logger LOGGER = Logger.getLogger(PlatMemberInfoAction.class);

	@RequestMapping(params = { "actionMethod=initMemberInfo" }, method = { RequestMethod.GET })
	public String initMemberInfo(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (platMemberInfo != null) {
			model.addAttribute("platMemberInfo", platMemberInfo);
		}

		return "pcenter.page.layout.information";
	}

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		dataBinder.registerCustomEditor(Date.class, "birthday", dateEditor);
	}

	@RequestMapping(params = { "actionMethod=editMemberInfo" })
	public String processEditMemberInfo(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo2 = ThreadLocalClient.get().getOperator();
		String emailAccount = request.getParameter("emailAccount");
		String realname = "";

		try {
			realname = new String(request.getParameter("realName").getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException var10) {
			LOGGER.error(var10.getMessage());
		}

		String sex = request.getParameter("sex");
		String id = request.getParameter("id");
		String memberId = request.getParameter("memberId");
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) this.platMemberInfoService.getObjectById(PlatMemberInfo.class,
				Long.valueOf(memberId));
		if (platMemberInfo != null) {
			platMemberInfo.setEmailAddress(emailAccount);
			platMemberInfo.setRealName(realname);
			platMemberInfo.setSex(Integer.valueOf(sex));
			platMemberInfo.setIdentityCard(id);
		}

		this.platMemberInfoService.updatePlatMemberInfo(platMemberInfo);
		platMemberInfo2.setEmailAddress(emailAccount);
		platMemberInfo2.setRealName(realname);
		platMemberInfo2.setSex(Integer.valueOf(sex));
		platMemberInfo2.setIdentityCard(id);
		ThreadLocalClient.get().setOperator(platMemberInfo2);
		return "redirect:usercenter.do?actionMethod=memberInformatinManage&isCutPage=false";
	}

	@RequestMapping(params = { "actionMethod=changePassword" }, method = { RequestMethod.GET })
	String initModifyPassword(HttpServletRequest request, Model model) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (platMemberInfo != null) {
			model.addAttribute("platMemberInfo", platMemberInfo);
		}

		return "ddhb/front/member/platMemberInfo/changepassword/changePassword.jsp";
	}

	@RequestMapping(params = { "actionMethod=changePassword" }, method = { RequestMethod.POST })
	public String doChangePassword(Model model, @ModelAttribute PlatMemberInfo platMemberInfo, BindingResult result,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oldPasswd = this.encryptService.encrypt(platMemberInfo.getOldPasswd());
		this.validateForPassword(platMemberInfo, oldPasswd, result);
		if (result.hasErrors()) {
			return Util.printErrorString(Util.getErrorMsgInfo(result, "platmemberInfoForm"));
		} else {
			this.platMemberInfoService.updatePassword(platMemberInfo, platMemberInfo.getConfirmNewPasswd());
			return "ddhb/front/member/platMemberInfo/changepassword/finishChangePassword.jsp";
		}
	}

	private void validateForPassword(PlatMemberInfo platMemberInfo, String oldPwd, Errors errors) {
		if (CommonUtil.isZeroLengthTrimString(platMemberInfo.getOldPasswd())) {
			errors.rejectValue("oldPasswd", "required", "required");
		} else if (!platMemberInfo.getPassWd().equals(oldPwd)) {
			errors.rejectValue("oldPasswd", "oldpassword.inactive");
		}

		if (CommonUtil.isZeroLengthTrimString(platMemberInfo.getNewPasswd())) {
			errors.rejectValue("newPasswd", "required", "required");
		} else if (!Util.getMatchResult(platMemberInfo.getNewPasswd(), "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,}")) {
			errors.rejectValue("newPasswd", "error.input.value.password");
		} else if (CommonUtil.isNotZeroLengthTrimString(platMemberInfo.getConfirmNewPasswd())
				&& !platMemberInfo.getNewPasswd().equals(platMemberInfo.getConfirmNewPasswd())) {
			errors.rejectValue("newPasswd", "error.input.value.confirmPwd");
		}

		if (CommonUtil.isZeroLengthTrimString(platMemberInfo.getConfirmNewPasswd())) {
			errors.rejectValue("confirmNewPasswd", "required", "required");
		}

		if (CommonUtil.isNotZeroLengthTrimString(platMemberInfo.getNewPasswd())
				&& CommonUtil.isNotZeroLengthTrimString(oldPwd) && platMemberInfo.getNewPasswd().equals(oldPwd)) {
			errors.rejectValue("newPasswd", "error.newPassword.duplicate");
		}

	}

	@RequestMapping(params = { "actionMethod=initModifyHeader" }, method = { RequestMethod.GET })
	public String initModifyHeader(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		model.addAttribute("platMemberInfo", platMemberInfo);
		return "ddhb/front/member/platMemberInfo/modifyHeader/modifyHeader.jsp";
	}

	@RequestMapping(params = { "actionMethod=initModifyHeader" }, method = { RequestMethod.POST })
	public String doModifyHeader(Model model, @ModelAttribute PlatMemberInfo platMemberInfo, BindingResult result,
			SessionStatus status, HttpServletRequest request) {
		this.platMemberInfoService.modifyHeader(platMemberInfo);
		return "ddhb/front/member/platMemberInfo/modifyHeader/finishModifyHeader.jsp";
	}

	@RequestMapping(params = { "actionMethod=validEmailUserd" })
	public String validEmailUserd(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter e = response.getWriter();
			String emailAccount = request.getParameter("emailAccount");
			Long id = Long.valueOf(request.getParameter("memberId"));
			PlatMemberInfo validEmailObject = this.platMemberInfoService.getPlatMemberByEmail(emailAccount, id);
			if (validEmailObject != null) {
				e.print("email.userd.error");
				e.close();
			} else {
				e.print("email.userd.nouse");
				e.close();
			}
		} catch (IOException var8) {
			LOGGER.error(var8.getMessage());
		}

		return null;
	}
}
