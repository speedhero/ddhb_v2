package com.huatek.framework.util;

import cn.hshb.hbwebsite.util.Verification;

import com.huatek.framework.entity.FwAccount;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.security.ClientInfoBean;
import com.huatek.framework.security.IValidateUser;
import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.service.LoginService;
import com.huatek.framework.sso.HttpClientUtil;
import com.huatek.framework.sso.ResponseBean;
import com.huatek.framework.sso.SSOServiceManagement;
import com.huatek.framework.sso.SessionManagmentController;
import com.huatek.framework.util.HuatekSessionContext;
import com.huatek.framework.util.SpringContext;
import com.opensymphony.oscache.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginFilter implements Filter {
	public static final String ERRORS_LOGIN_SESSION_TIMEOUT = "error.login.session.timeout";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String ERROR_LABLE = "errorLable";
	public static final String VALIDATE_USER_SERVICE = "validateUserService";
	private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);
	private String userNameField = "userName";
	private String passwordField = "password";
	private String formNameField = "loginForm";
	private String welcomeAction = "/";
	private String verifyCode = "verifyCode"; // 验证码
	FwAccount user = null;
	long time = 0L;
	@Autowired
	private LoginService loginService;

	public void destroy() {
		ThreadLocalClient.remove();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		String clientIPAddress = hRequest.getRemoteAddr();
		if (request.getParameter("jsessionId") != null) {
			HttpSession cientInfo = HuatekSessionContext.getInstance().getSession(request.getParameter("jsessionId"));
			if (cientInfo != null) {
				hRequest.getSession().setAttribute("LoginAccount", cientInfo.getAttribute("LoginAccount"));
			}
		}
		if (hRequest.getSession().getAttribute("LoginAccount") != null
				&& request.getParameter(this.formNameField) == null) {
			if (hRequest.getSession().getAttribute("SSO_LOGIN_TIME") != null
					&& hRequest.getSession().getAttribute("SSO_TOKEN") != null) {
				long cientInfo3 = System.currentTimeMillis()
						- ((Long) hRequest.getSession().getAttribute("SSO_LOGIN_TIME")).longValue();
				if (cientInfo3 >= 1000000L) {
					try {
						ArrayList e = new ArrayList();
						String tokenId = hRequest.getSession().getAttribute("SSO_TOKEN").toString();
						e.add(new BasicNameValuePair("mainSysSessionId", tokenId));
						String httpresponse = HttpClientUtil.postMethod(SSOServiceManagement.getSSOServerUrl(hRequest)
								+ "/sso.show?actionMethod=ssoCheck", e, tokenId);
						httpresponse = HttpClientUtil.trimJSONSata(httpresponse);
						ResponseBean responseBean = (ResponseBean) SessionManagmentController.GSON.fromJson(
								httpresponse, ResponseBean.class);
						if (responseBean.isSuccess()) {
							hRequest.getSession().setAttribute("SSO_LOGIN_TIME",
									Long.valueOf(System.currentTimeMillis()));
						} else {
							hRequest.getSession().invalidate();
							request.setAttribute("errorMessage", "error.login.session.timeout");
						}
					} catch (Exception var13) {
						LOGGER.error("和SSO服务器保持SESSION会话时出错:" + var13.getMessage());
					}
				}
			}
		} else if (request.getParameter(this.formNameField) != null) {
			if (StringUtil.hasLength(request.getParameter("webUrl"))) {
				hRequest.setAttribute("webUrl", request.getParameter("webUrl"));
			}

			try {
				// 获取session.getId，给验证码用
				String sessionId = hRequest.getSession().getId();
				IValidateUser cientInfo1 = (IValidateUser) SpringContext.getBean("validateUserService");
				FwAccount user = (FwAccount) cientInfo1.getUser(request.getParameter(this.userNameField),
						request.getParameter(this.passwordField), clientIPAddress,
						request.getParameter(this.verifyCode), sessionId);
				hRequest.getSession().setAttribute("LoginAccount", user);
				HuatekSessionContext.getInstance().getSession(hRequest.getSession().getId())
						.setAttribute("LoginAccount", user);
			} catch (BusinessRuntimeException var16) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(var16.getMessage());
				}

				request.setAttribute("errorLable", var16.getErrorKey());
			} catch (Exception var17) {
				var17.printStackTrace();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(var17.getMessage());
				}

				request.setAttribute("errorMessage", var17.getMessage());
			}
		} else if (!SSOServiceManagement.isSSOServer()) {
			if (request.getParameter("tokenId") == null) {
				String cientInfo2 = SSOServiceManagement.getSsoTokenURL(hRequest);
				hResponse.sendRedirect(cientInfo2);
				return;
			}

			try {
				SSOServiceManagement.ssoLogin(hRequest, hResponse);
			} catch (BusinessRuntimeException var14) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(var14.getMessage());
				}

				request.setAttribute("errorLable", var14.getErrorKey());
			} catch (Exception var15) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(var15.getMessage());
				}

				request.setAttribute("errorMessage", var15.getMessage());
			}
		}

		if (hRequest.getAttribute("errorMessage") == null && hRequest.getAttribute("errorLable") == null) {
			if (ThreadLocalClient.get() != null) {
				ClientInfoBean cientInfo5 = ThreadLocalClient.get();
				cientInfo5.setOperator((FwAccount) hRequest.getSession().getAttribute("LoginAccount"));
			}

			chain.doFilter(request, response);
		} else {
			RequestDispatcher cientInfo4 = request.getRequestDispatcher(this.welcomeAction);
			if (hRequest.getAttribute("webUrl") != null) {
				hResponse.sendRedirect(hRequest.getAttribute("webUrl").toString() + "&" + "errorMessage" + "="
						+ hRequest.getAttribute("errorMessage") + "&" + "errorLable" + "="
						+ hRequest.getAttribute("errorLable"));
				return;
			}

			cientInfo4.forward(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		if (fConfig.getInitParameter("formNameField") != null) {
			this.formNameField = fConfig.getInitParameter("formNameField");
		}

		if (fConfig.getInitParameter("userNameField") != null) {
			this.userNameField = fConfig.getInitParameter("userNameField");
		}

		if (fConfig.getInitParameter("passwordField") != null) {
			this.passwordField = fConfig.getInitParameter("passwordField");
		}

		if (fConfig.getInitParameter("welcomeAction") != null) {
			this.welcomeAction = fConfig.getInitParameter("welcomeAction");
		}

	}
}
