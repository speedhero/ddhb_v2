package cn.hshb.web.biz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.forbiddentel.service.ForbiddenTelService;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.EncryptService;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.mailer.EmailAbstract;

import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.MailConstant;

import cn.hshb.web.biz.mybatis.model.MemberInfo;
import cn.hshb.web.biz.mybatis.model.MemberIntegralHistory;
import cn.hshb.web.biz.mybatis.model.MemberIntegralTable;
import cn.hshb.web.biz.mybatis.model.MemberIntegrateRule;
import cn.hshb.web.biz.mybatis.model.PlatMemberInfo;
import cn.hshb.web.biz.service.PlatMemberInfoService;
import cn.hshb.web.common.util.StringUtil;

@Controller
@RequestMapping("/register_member" )
@SessionAttributes(types = { MemberInfo.class })
public class MemberRegisterController {
	@Autowired
	private PlatMemberInfoService platMemberInfoService;
	@Autowired
	EncryptService encryptServiceImpl;
	@Autowired
	private EmailAbstract sendEmailService;
	@Autowired
	private ForbiddenTelService forbiddenTelService;
	private static final Logger LOGGER = Logger.getLogger(MemberRegisterController.class);
	private static Map<String, String> keyMessage = new HashMap<String, String>();
	private static final int validatePhone = 1;

	/**
	 * 初始化会员注册信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/register" }, method = { RequestMethod.GET })
	public String initRegisterMemberInfo(Model model, HttpServletRequest request) {
		model.addAttribute(new MemberInfo());
		model.addAttribute("mamagerMail", FrontSystemSettingUtil.getInstance().getEmailAccount());
		model.addAttribute("registerReason", FrontSystemSettingUtil.getInstance().getRegesterReason());
		model.addAttribute("emailAddressReason", FrontSystemSettingUtil.getInstance().getEmailaddressReason());
		model.addAttribute("privatePolicy", FrontSystemSettingUtil.getInstance().getPrivatePolicy());
		return "member.register";
	}

	/**
	 * 打开注册界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/preRegist" }, method = { RequestMethod.GET })
	public String checkRegister(HttpServletRequest request) {
		String referer = request.getHeader("referer");
		request.getSession().setAttribute("previousUrl", referer);
		return "redirect:/register_member/register";
	}

	@RequestMapping(value = { "/register" }, method = { RequestMethod.POST })
	public String processRegister(Model model, @ModelAttribute final PlatMemberInfo platMemberInfo, BindingResult result,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) {
		String checked = request.getParameter("checkbox");
		platMemberInfo.setCheckbox(checked);
		this.validate(platMemberInfo, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
			return "ddhb.out.error";
		} else {
			String regIp = request.getRemoteAddr();
			platMemberInfo.setRegIp(regIp);
			String phoneNumber = request.getParameter("mobilephone");
			String email;
			if (!"".equals(phoneNumber.trim())) {
				String saveStatus = request.getSession().getId();
				email = (String) keyMessage.get(saveStatus + "|" + phoneNumber);
				String emailMessage = request.getParameter("verifyCode");
				if (email == null || "".equals(email) || !email.equals(emailMessage)) {
					result.reject("验证码不正确");
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
					return "ddhb.out.error";
				}

				platMemberInfo.setMobilephoneValidatedFlag(1);
				keyMessage.remove(saveStatus + "|" + phoneNumber);
			}

			int saveStatus1 = this.platMemberInfoService.saveOrUpdatePlatMember(platMemberInfo);
			if (saveStatus1 == 3) {
				status.setComplete();
			} else {
				if (saveStatus1 == 0) {
					result.reject("该用户名已经存在");
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
					return "ddhb.out.error";
				}

				if (saveStatus1 == 1) {
					result.reject("该手机号码已经注册");
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
					return "ddhb.out.error";
				}

				if (saveStatus1 == 2) {
					result.reject("该邮箱已经注册");
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
					return "ddhb.out.error";
				}
			}

			email = platMemberInfo.getEmailAddress();
			model.addAttribute("email", email);
			final String[] emailMessage1 = new String[] { platMemberInfo.getAccName(), platMemberInfo.getUuid(), null, null };
			String url = request.getRequestURL().toString();
			emailMessage1[2] = url;
			emailMessage1[3] = platMemberInfo.getEmailAddress();
			(new Thread() {
				public void run() {
					try {
						MemberRegisterController.this.sendEmailService.sendEmail(emailMessage1, platMemberInfo.getEmailAddress());
					} catch (Exception var2) {
						MemberRegisterController.LOGGER.error(var2.getMessage());
						throw new BusinessRuntimeException("激活邮件发送失败！");
					}
				}
			}).start();
			model.addAttribute("saveok", "saveok");
			return "ddhb.out.error";
		}
	}

	@RequestMapping(value = { "/phoneCode" }, method = { RequestMethod.POST })
	public void phoneCode(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException,
			IOException {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		String status = "{\"result\": \"error\",\"time\": 0}";
		String phoneNumb = request.getParameter("phone");
		if (StringUtil.isEmpty(phoneNumb)) {
			status = "{\"result\": \"empty\",\"time\": 0}";
		} else if (!Util.getMatchResult(phoneNumb, "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			status = "{\"result\": \"numbError\",\"time\": 0}";
		} else if (!this.validatePhone(phoneNumb)) {
			boolean out = true;
			byte e = 6;
			String codeNumb = createRandom(out, e);
			if (codeNumb != "") {
				String phoneNumber = phoneNumb;
				String sessionId = request.getSession().getId();
				String requestXML = this.createXml(sessionId, phoneNumb, accountBean, codeNumb);
				String returnedXml = "";

				try {
					returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
					SAXReader e1 = new SAXReader();
					Document document = e1.read(new StringReader(returnedXml));
					List resultList = document.selectNodes("/BasicDataSyncResult/Results/Item/ResultCode");
					if (resultList.size() > 0) {
						Node node = (Node) resultList.get(0);
						if ("0".equals(node.getText())) {
							keyMessage.put(sessionId + "|" + phoneNumber, codeNumb);
							status = "{\"result\": \"success\",\"time\": "
									+ FrontSystemSettingUtil.getInstance().getMessageResetTime() + "}";
						}
					}
				} catch (DocumentException var21) {
					LOGGER.error(var21.getMessage());
				}
			}
		} else {
			status = "{\"result\": \"black\",\"time\": 0}";
		}

		PrintWriter out1 = null;

		try {
			out1 = response.getWriter();
			out1.write(status);
			out1.flush();
		} catch (IOException var20) {
			LOGGER.error(var20.getMessage());
		} finally {
			if (out1 != null) {
				out1.close();
			}

		}

	}

	public boolean validatePhone(String phone) {
		return this.forbiddenTelService.isInForbiddenList(phone);
	}

	public static String createRandom(boolean numberFlag, int length) {
		String resultStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;

		do {
			resultStr = "";
			int count = 0;

			for (int i = 0; i < length; ++i) {
				double doubleRel = Math.random() * (double) len;
				int intRel = (int) Math.floor(doubleRel);
				char c = strTable.charAt(intRel);
				if (48 <= c && c <= 57) {
					++count;
				}

				resultStr = resultStr + strTable.charAt(intRel);
			}

			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return resultStr;
	}

	@RequestMapping(value = { "/activity" })
	public String activityRegiste(HttpServletRequest request) {
		String eamil = request.getParameter("email");
		String mamagerMail = request.getParameter("mamagerMail");
		String mailDomain = request.getParameter("mailDomain");
		request.setAttribute("email", eamil);
		request.setAttribute("mamagerMail", mamagerMail);
		request.setAttribute("mailDomain", mailDomain);
		return "page.platmember.register.active";
	}

	private void validate(PlatMemberInfo platMemberInfo, Errors errors, HttpServletRequest request) {
		if (StringUtil.isEmpty(platMemberInfo.getEmailAddress())) {
			errors.reject("请输入邮箱地址");
		} else if (!Util.getMatchResult(platMemberInfo.getEmailAddress(),
				"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.reject("邮箱格式不正确");
		} else if (platMemberInfo.getEmailAddress().toString().getBytes(Charset.forName("GBK")).length > 50) {
			errors.reject("邮件地址不能超过50个字符");
		}

		if (StringUtil.isEmpty(platMemberInfo.getAccName())) {
			errors.reject("请输入用户名");
		}

		if (platMemberInfo.getAccName().toString().getBytes(Charset.forName("GBK")).length > 50) {
			errors.reject("用户名过长");
		}

		if (StringUtil.isEmpty(platMemberInfo.getPassword())) {
			errors.reject("请输入密码");
		} else if(platMemberInfo.getPassword().toString().getBytes(Charset.forName("GBK")).length < 6){
			errors.reject("密码长度必须大于等于6位");
		}
//		else if (!Util.getMatchResult(platMemberInfo.getPassWd(), "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,}")) {
//			errors.reject("请输入8-16位密码，密码由数字和字母组合");
//		} else if (platMemberInfo.getPassWd().toString().getBytes(Charset.forName("GBK")).length > 16
//				|| platMemberInfo.getPassWd().toString().getBytes(Charset.forName("GBK")).length < 8) {
//			errors.reject("密码由8-16位的字母数字组成");
//		}

		if (StringUtil.isEmpty(platMemberInfo.getConfirmPassWd())) {
			errors.reject("请输入确认密码");
		} 
//		else if (Util.getMatchResult(platMemberInfo.getConfirmPassWd(), "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,}")
//				&& (platMemberInfo.getConfirmPassWd().toString().getBytes(Charset.forName("GBK")).length > 16 || platMemberInfo
//						.getConfirmPassWd().toString().getBytes(Charset.forName("GBK")).length < 8)) {
//			errors.reject("密码由8-16位的字母数字组成");
//		}

		String passWord = platMemberInfo.getPassword();
		String confirmPwd = platMemberInfo.getConfirmPassWd();
		if (CommonUtil.isNotZeroLengthTrimString(passWord) && CommonUtil.isNotZeroLengthTrimString(confirmPwd)
				&& !confirmPwd.equals(passWord)) {
			errors.reject("两次输入密码不正确");
		}

		String checkbox = platMemberInfo.getCheckbox();
		if (checkbox == null) {
			errors.reject("您还未同意华邦协议");
		}

	}

	@RequestMapping(value = { "/activeAccount" })
	public String activeMember(Model model, HttpServletRequest request) {
		String uuId = request.getParameter("uuid");
		if (!CommonUtil.isNotZeroLengthTrimString(uuId)) {
			model.addAttribute("uuidInvalidate", "true");
			return "page.platmember.register.success";
		} else {
			PlatMemberInfo platMemberInfo = this.platMemberInfoService.getAndActiveAccountByUUID(uuId);
			if (platMemberInfo != null) {
				HttpSession session = request.getSession();
				String[] rules = new String[] { "registed", "maxChangeLimit", "maxReceivedLimit" };
				List integrateRules = this.platMemberInfoService.getAllRulesByRules(rules);
				MemberIntegralTable tIntegralTable = new MemberIntegralTable();
				MemberIntegralHistory integralHistory = new MemberIntegralHistory();
				integralHistory.setPlatMemberInfo(platMemberInfo);
				tIntegralTable.setPlatMemberInfo(platMemberInfo);
				Iterator integrateRulesPhone = integrateRules.iterator();

				while (integrateRulesPhone.hasNext()) {
					MemberIntegrateRule rulesPhone = (MemberIntegrateRule) integrateRulesPhone.next();
					if (rulesPhone.getRuleName().equals("registed")) {
						integralHistory.setGettedtime(new Date());
						integralHistory.setComment(rulesPhone.getComment());
						integralHistory.setIntegral(Integer.parseInt(rulesPhone.getRuleValue()));
						integralHistory.setGetFlag(rulesPhone.getRuleNo());
						tIntegralTable.setIntegral(Integer.parseInt(rulesPhone.getRuleValue()));
					} else if (rulesPhone.getRuleName().equals("maxChangeLimit")) {
						tIntegralTable.setChangecount(0);
					} else if (rulesPhone.getRuleName().equals("maxReceivedLimit")) {
						tIntegralTable.setReceviedcount(0);
					}
				}

				this.platMemberInfoService.saveIntegralHistory(integralHistory);
				if (platMemberInfo.getMobilephoneValidatedFlag() == 1) {
					String[] rulesPhone1 = new String[] { "phoneValidated" };
					List integrateRulesPhone1 = this.platMemberInfoService.getAllRulesByRules(rulesPhone1);
					MemberIntegralHistory integralHistoryPhone = new MemberIntegralHistory();
					integralHistoryPhone.setPlatMemberInfo(platMemberInfo);
					Iterator var14 = integrateRulesPhone1.iterator();

					while (var14.hasNext()) {
						MemberIntegrateRule integrateRule = (MemberIntegrateRule) var14.next();
						if (integrateRule.getRuleName().equals("phoneValidated")) {
							integralHistoryPhone.setGettedtime(new Date());
							integralHistoryPhone.setComment(integrateRule.getComment());
							integralHistoryPhone.setIntegral(Integer.parseInt(integrateRule.getRuleValue()));
							integralHistoryPhone.setGetFlag(integrateRule.getRuleNo().intValue());
							tIntegralTable.setIntegral(tIntegralTable.getIntegral() + Integer.parseInt(integrateRule.getRuleValue()));
						}
					}

					this.platMemberInfoService.saveIntegralHistory(integralHistoryPhone);
				}

				this.platMemberInfoService.saveIntegralTable(tIntegralTable);
				session.setAttribute("LoginMember", platMemberInfo);
			}

			return "page.platmember.register.success";
		}
	}

	@RequestMapping(value = { "/forgetPassword" }, method = { RequestMethod.GET })
	public String forgetPassword(Model model, HttpServletRequest request) {
		model.addAttribute(new PlatMemberInfo());
		String userName = request.getParameter("userName");
		if (CommonUtil.isNotZeroLengthTrimString(userName)) {
			model.addAttribute("userName", userName);
		}

		return "page.forgetPassword.list";
	}

	@RequestMapping(value = { "/forgetPassword" }, method = { RequestMethod.POST })
	public String doForgetPassword(Model model, @ModelAttribute PlatMemberInfo platMemberInfo, BindingResult result,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		final PlatMemberInfo platMemberInfoObj = this.platMemberInfoService.findPlatMemberInfoByUserName(platMemberInfo
				.getAccName());
		this.validateForFindPassword(platMemberInfo, result, platMemberInfoObj);
		if (result.hasErrors()) {
			return "page.forgetPassword.list";
		} else {
			String email = platMemberInfoObj.getEmailAddress();
			model.addAttribute("email", email);
			model.addAttribute("mamagerMail", FrontSystemSettingUtil.getInstance().getEmailAccount());
			String mailDomain = email.substring(email.indexOf("@") + 1, email.lastIndexOf(46));
			model.addAttribute("mailDomain", mailDomain);
			this.platMemberInfoService.saveMailSendInfoByTemplate(platMemberInfoObj, request.getRequestURL().toString(),
					MailConstant.MAIL_FORGET_PASSWORD_MODIFY_NEW);
			final String[] emailMessage = new String[] { platMemberInfoObj.getAccName(), platMemberInfoObj.getUuid(), null };
			String url = request.getRequestURL().toString();
			emailMessage[2] = url;
			(new Thread() {
				@SuppressWarnings("deprecation")
				public void run() {
					try {
						MemberRegisterController.this.sendEmailService.sendForgetPasswordEmail(emailMessage,
								platMemberInfoObj.getEmailAddress());
					} catch (Exception var2) {
						MemberRegisterController.LOGGER.error(var2.getMessage());
						throw new BusinessRuntimeException("找回密码邮件发送失败！");
					}
				}
			}).start();
			return "page.forgetPasswordActive.list";
		}
	}

	private void validateForFindPassword(PlatMemberInfo platMemberInfo, Errors errors, PlatMemberInfo platMemberInfoObj) {
		if (StringUtil.isEmpty(platMemberInfo.getAccName())) {
			errors.rejectValue("accName", "required", "required");
		} else if (platMemberInfoObj == null) {
			errors.rejectValue("accName", "error.input.value.currentMember");
		}

	}

	@RequestMapping(value = { "/modifyPassword" }, method = { RequestMethod.GET })
	public String modifyPassword(HttpServletRequest request, Model model) {
		String uuId = request.getParameter("uuid");
		PlatMemberInfo platMemberInfo = null;
		if (CommonUtil.isNotZeroLengthTrimString(uuId)) {
			platMemberInfo = this.platMemberInfoService.getPlatMemberInfoByUUID(uuId);
			if (platMemberInfo != null) {
				model.addAttribute("platMemberInfo", platMemberInfo);
			} else {
				model.addAttribute("uuidInvalidate", "true");
			}
		}

		return "page.forget.changePassword.init";
	}

	@RequestMapping(value = { "/modifyPassword" }, method = { RequestMethod.POST })
	public String doChangePassword(Model model, @ModelAttribute PlatMemberInfo platMemberInfo, BindingResult result,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatMemberInfo platMemberInfoSession = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		String oldPassword = platMemberInfoSession.getPassword();
		this.validateForPassword(platMemberInfo, result, oldPassword);
		if (!result.hasErrors() && platMemberInfo != null) {
			PlatMemberInfo member = this.platMemberInfoService.changePassword(platMemberInfo,
					platMemberInfo.getConfirmNewPasswd());
			HttpSession session = request.getSession();
			session.setAttribute("LoginMember", member);
			
			//TODO: 这里的邮件地址改成从数据库取
			//model.addAttribute("mamagerMail", "bpm_system@huatek.com");
			model.addAttribute("mamagerMail", "services@hshb.cn");
			return "page.forget.changePassword.success";
		} else {
			return "page.forget.changePassword.init";
		}
	}

	private void validateForPassword(PlatMemberInfo platMemberInfo, Errors errors, String oldPassword) {
		String oldPasswordrequest = this.encryptServiceImpl.encrypt(platMemberInfo.getOldPasswd());
		if (StringUtil.isEmpty(platMemberInfo.getOldPasswd())) {
			errors.rejectValue("confirmNewPasswd", "requiredd", "requiredd");
		} else if (!oldPasswordrequest.equals(oldPassword)) {
			errors.rejectValue("oldPasswd", "error.input.value.oldpassword");
		} else if (StringUtil.isEmpty(platMemberInfo.getNewPasswd())) {
			errors.rejectValue("newPasswd", "requiredd", "requiredd");
		} else if (!Util.getMatchResult(platMemberInfo.getNewPasswd(), "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,}")) {
			errors.rejectValue("newPasswd", "error.input.value.password");
		} else if (!platMemberInfo.getNewPasswd().equals(platMemberInfo.getConfirmNewPasswd())) {
			errors.rejectValue("newPasswd", "error.input.value.confirmPwd");
		} else if (StringUtil.isEmpty(platMemberInfo.getConfirmNewPasswd())) {
			errors.rejectValue("confirmNewPasswd", "requiredd", "requiredd");
		}
	}

	@RequestMapping(value = { "/findPassword" }, method = { RequestMethod.GET })
	public String findPassword(HttpServletRequest request, Model model) {
		String uuId = request.getParameter("uuid");
		PlatMemberInfo platMemberInfo = null;
		if (CommonUtil.isNotZeroLengthTrimString(uuId)) {
			platMemberInfo = this.platMemberInfoService.getPlatMemberInfoByUUID(uuId);
			if (platMemberInfo != null) {
				model.addAttribute("platMemberInfo", platMemberInfo);
			} else {
				model.addAttribute("uuidInvalidate", "true");
			}
		}

		return "page.forget.findPassword.init";
	}

	@RequestMapping(value = { "/findPassword" }, method = { RequestMethod.POST })
	public String dofindPassword(Model model, @ModelAttribute PlatMemberInfo platMemberInfo, BindingResult result,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.validateForFindPassword(platMemberInfo, result);
		if (!result.hasErrors() && platMemberInfo != null) {
			PlatMemberInfo member = this.platMemberInfoService.changePassword(platMemberInfo,
					platMemberInfo.getConfirmNewPasswd());
			HttpSession session = request.getSession();
			session.setAttribute("LoginMember", member);
			
			//TODO: 这里的邮件地址改成从数据库取
			//model.addAttribute("mamagerMail", "bpm_system@huatek.com");
			model.addAttribute("mamagerMail", "services@hshb.cn");
			return "page.forget.changePassword.success";
		} else {
			return "page.forget.findPassword.init";
		}
	}

	private void validateForFindPassword(PlatMemberInfo platMemberInfo, Errors errors) {
		if (StringUtil.isEmpty(platMemberInfo.getNewPasswd())) {
			errors.rejectValue("newPasswd", "requiredd", "requiredd");
		} else if (!Util.getMatchResult(platMemberInfo.getNewPasswd(), "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,}")) {
			errors.rejectValue("newPasswd", "error.input.value.password");
		} else if (!platMemberInfo.getNewPasswd().equals(platMemberInfo.getConfirmNewPasswd())) {
			errors.rejectValue("newPasswd", "error.input.value.confirmPwd");
		} else if (StringUtil.isEmpty(platMemberInfo.getConfirmNewPasswd())) {
			errors.rejectValue("confirmNewPasswd", "requiredd", "requiredd");
		}
	}

	@RequestMapping(value = { "/getPhoneMessage" }, method = { RequestMethod.POST })
	public void getPhoneMessage(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		String phoneNumber = request.getParameter("phoneNumber");
		String sessionId = request.getSession().getId();
		Random random = new Random();
		int x = random.nextInt(899999) + 100000;
		String requestXML = this.createXml(sessionId, phoneNumber, accountBean, String.valueOf(x));
		String returnedXml = "";
		returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(returnedXml));
		List resultList = document.selectNodes("/BasicDataSyncResult/Results/Item/ResultCode");
		if (resultList.size() > 0) {
			Node node = (Node) resultList.get(0);
			"1".equals(node.getText());
		}

	}


	private String createXml(String sessionId, String phoneNumber, PlatMemberInfo member, String message) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = df.format(date);
		StringBuilder reqXml = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append("<BasicData>")
		.append("  <DataHeader>")
		.append("    <DataSetID>").append(UUID.randomUUID()).append("</DataSetID>")
		.append("    <DataType>SMS</DataType>")
		.append("  </DataHeader>")
		.append("  <DataBody>")
		.append("    <Item>")
		.append("      <ItemID>").append(sessionId).append("</ItemID>")
		.append("      <OperationID>1</OperationID>")
		.append("      <SMSID>1</SMSID>");
		if (member == null) {
			reqXml.append("<MemberID></MemberID>");
			reqXml.append("<MemberName></MemberName>");
		} else {
			if (member.getMemberId() == null) {
				reqXml.append("<MemberID></MemberID>");
			} else {
				reqXml.append("<MemberID>").append(member.getMemberId()).append("</MemberID>");
			}

			if (member.getAccName() == null) {
				reqXml.append("<MemberName></MemberName>");
			} else {
				reqXml.append("<MemberName>").append(member.getAccName()).append("</MemberName>");
			}
		}

		//TODO: 这里短信内容可以改成用配置文件或在数据库中配置
		message = "感谢注册豪世华邦官方网站，您的手机验证码为"+message+"，如不是您本人注册，请忽略。";
		
		reqXml.append("     <MemberID></MemberID>\t<!--会员ID-->")
		.append("      <SMSType>1</SMSType>\t<!--短信类别: 1验证手机2价格定阅、3、其它-->")
		.append("      <Content>").append(message).append("</Content>\t<!--短信内容-->")
		.append("      <MobilePhone>").append(phoneNumber).append("</MobilePhone>")
		.append("      <CreateTime>").append(dateString).append("</CreateTime>")
		.append("    </Item>")
		.append("  </DataBody>")
		.append("</BasicData>");
		return reqXml.toString();
	}

	private String createTestReturnXml() {
		StringBuilder sbXML = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append("<BasicDataSyncResult>")
		.append("  <ResultHeader>")
		.append("    <DataSetID>77dbcb9f-e389-4658-ac02-f2948822b0d2</DataSetID>")
		.append("    <DataType>SMS</DataType>")
		.append("    <SyncTime>2014-12-4 14:19:07</SyncTime>")
		.append("  </ResultHeader>")
		.append("  <Results>")
		.append("    <Item>")
		.append("      <ItemID>D5B0627C3BA649F191FD8EE3769A9F58</ItemID>")
		.append("      <ResultCode>0</ResultCode>")
		.append("      <ResultDetail>发送成功</ResultDetail>")
		.append("    </Item>")
		.append("  </Results>")
		.append("</BasicDataSyncResult>");
		return sbXML.toString();
	}
}
