package com.huatek.hbwebsite.security;

import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.security.ClientInfoBean;
import com.huatek.hbwebsite.security.ClientInformation;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.LocaleResolver;

public class ClientInformationImpl implements ClientInformation {
	private static final String SYSTEM_CODE = "SYSTEM_CODE";
	public static final String CREATE_GROUP = "createGroup";
	public static final String MENU_ID = "menuId";
	public static final String LOGIN_ACCOUNT = "LoginAccount";
	public static final String ACTION_URL = "actionURL";
	public static final String DEFAULT_METHOD = "query";
	public static final String ACTION_METHOD = "actionMethod";
	public static final String SSO_TOKEN = "SSO_TOKEN";
	public static final String SSO_LOGIN_TIME = "SSO_LOGIN_TIME";
	public static final int SSO_CHECK_TIME = 1000000;
	private static final Logger LOGGER = Logger.getLogger(ClientInformationImpl.class);

	public ClientInfoBean collectClientInfo(HttpServletRequest request) {
		ClientInfoBean frameBean = new ClientInfoBean();
		LocaleResolver localeResolver = (LocaleResolver) SpringContext.getBean("localeResolver");
		frameBean.setLocale(localeResolver.resolveLocale(request));
		String actionName = getActionName(request);
		frameBean.setHostIp(request.getRemoteHost());
		frameBean.setHostPort(request.getRemotePort());
		if (CommonUtil.isNotZeroLengthTrimString(request.getParameter("menuId"))) {
			frameBean.setMenuId(Long.valueOf(request.getParameter("menuId")));
			request.getSession().setAttribute("menuId", frameBean.getMenuId());
		} else if (request.getSession().getAttribute("menuId") != null) {
			frameBean.setMenuId((Long) request.getSession().getAttribute("menuId"));
		}

		frameBean.setContextPath(request.getContextPath());
		frameBean.setRequestURL(request.getRequestURI());
		String queryString = request.getQueryString();
		LOGGER.info("请求路径:" + frameBean.getRequestURL() + (queryString == null ? "" : "?" + queryString));
		frameBean.setServerPort(request.getServerPort());
		frameBean.setServerHost(request.getServerName());
		frameBean.setActionURL(actionName);
		frameBean.setActualActionPath(actionName);
		String actionMethod = request.getParameter("actionMethod");
		if (actionMethod == null) {
			actionMethod = "query";
		}

		frameBean.setRequestMethod(request.getMethod());
		frameBean.setActionMethod(actionMethod);
		frameBean.setAccessCalendar(Calendar.getInstance());
		HttpSession session = request.getSession();
		PlatMemberInfo accountBean = (PlatMemberInfo) session.getAttribute("LoginMember");
		frameBean.setOperator(accountBean);
		return frameBean;
	}

	protected static String getActionName(HttpServletRequest request) {
		String actionName = request.getParameter("actionURL");
		if (CommonUtil.isZeroLengthTrimString(actionName)) {
			String requestURL = request.getRequestURI();
			if (requestURL != null) {
				actionName = requestURL.substring(requestURL.lastIndexOf("/") + 1);
			}
		}

		return actionName;
	}
}
