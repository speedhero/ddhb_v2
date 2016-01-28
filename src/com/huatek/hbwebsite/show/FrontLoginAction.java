package com.huatek.hbwebsite.show;

import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.forbiddentel.service.ForbiddenTelService;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/frontLogin.do" })
public class FrontLoginAction {
	@Autowired
	PlatMemberInfoService platMemberInfoService;
	@Autowired
	private ForbiddenTelService forbiddenTelService;
	private String userNameField = "userName";
	private String passwordField = "password";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String ERROR_LABLE = "errorLable";
	private static final Logger LOGGER = Logger.getLogger(FrontLoginAction.class);

	@RequestMapping(params = { "actionMethod=doLogin" })
	public String processCheckUserAndPwd(Model model, @ModelAttribute PlatMemberInfo platMemberInfo,
			BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		PlatMemberInfo user = null;
		String erroLabelStr = null;
		String errMsg = null;
		String errKey = null;
		String ipaddress = request.getRemoteAddr();
		String target = request.getParameter("target");
//		Enumeration ee = request.getParameterNames();
		String verifyCode = request.getParameter("verifyCode");
		if (CommonUtil.isZeroLengthTrimString(verifyCode)) {
			model.addAttribute("verifyCodeNull", "verifyCodeNull");
			return "ddhb.out.error";
		} else {
//			boolean url = Boolean.FALSE.booleanValue();
			String sessId = request.getSession().getId();
			if (!CaptchaServiceSingleton.getInstance().validateResponseForID(sessId, verifyCode.toLowerCase())) {
				model.addAttribute("verifyCodeError", "verifyCodeError");
				return "ddhb.out.error";
			} else {
				ResourceBundle rb = ResourceBundle.getBundle("/MessageResources_en_US");;
				try {
					user = (PlatMemberInfo) this.platMemberInfoService.getUser(request.getParameter(this.userNameField),
							request.getParameter(this.passwordField), ipaddress);
					if (forbiddenTelService.isInForbiddenList(user.getMobilephone())) {
						errMsg = rb.getString("login.phone.block");
						model.addAttribute("phoneBlock", errMsg);
						return "ddhb.out.error";
					}

					request.getSession().setAttribute("LoginMember", user);
				} catch (BusinessRuntimeException ex) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(ex.getMessage());
					}
					errKey = ex.getErrorKey();
					erroLabelStr = rb.getString(errKey);
					if (!errKey.equals("login.lock.time.error")) {
						result.reject(erroLabelStr);
					}

					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
				} catch (Exception ex) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug(ex.getMessage());
					}

					errMsg = ex.getMessage();
					errMsg = rb.getString(errMsg);
					result.reject(errMsg);
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
				}

				if (erroLabelStr == null && errMsg == null) {
					ThreadLocalClient.get().setOperator(user);
					if (target != null && !"".equals(target)) {
						if ("collect".equals(target)) {
							model.addAttribute("targetUrl", request.getHeader("referer"));
						}

						if ("favourate".equals(target)) {
							model.addAttribute("targetUrl", "usercenter.do?actionMethod=collectment&isCutPage=false");
						}

						if ("browseHistory".equals(target)) {
							String targetUrl = "usercenter.do?actionMethod=browseHistory&isCutPage=false";
							String houseType = request.getParameter("housetype");
							if (!"".equals(houseType) && !"undefined".equals(houseType)) {
								if (!"S".equals(houseType) && !"0".equals(houseType)) {
									if (!"R".equals(houseType) && !"1".equals(houseType)) {
										targetUrl = targetUrl + "&houseType=2";
									} else {
										targetUrl = targetUrl + "&houseType=1";
									}
								} else {
									targetUrl = targetUrl + "&houseType=0";
								}
							}
							model.addAttribute("targetUrl", targetUrl);
						}

						if ("customerService".equals(target)) {
							model.addAttribute("targetUrl", "company.show?actionMethod=showservice");
						}

						if ("memberPoints".equals(target)) {
							model.addAttribute("targetUrl", "usercenter.do?actionMethod=scoreManager&isCutPage=false");
						}
					}

					model.addAttribute("saveok", "saveok");
					model.addAttribute("accName", user.getAccName());
					return "ddhb.out.error";
				} else {
					if (errKey.equals("login.lock.time.error")) {
						Calendar cal = Calendar.getInstance();
						user = this.platMemberInfoService.findPlatMemberInfoByUserName(request.getParameter(this.userNameField));
						FrontSystemSetting fss = this.platMemberInfoService.getFrontSystemSettingByName("count_lock_time");
						Long time = Long.valueOf(Long.valueOf(fss.getSettingValue()).longValue()
								- (cal.getTimeInMillis() / 1000L - user.getWrongLoginTime()));
						if (time.longValue() <= 0L) {
							time = Long.valueOf(0L);
						}

						erroLabelStr = erroLabelStr.replaceAll("\\{0\\}", time.toString());
						result.reject(erroLabelStr);
						model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "platMemberInfo"));
					}

					request.setAttribute(this.userNameField, request.getParameter(this.userNameField));
					model.addAttribute("errorLable", erroLabelStr);
					model.addAttribute("errorMessage", errMsg);
					return "ddhb.out.error";
				}
			}
		}
	}
}
