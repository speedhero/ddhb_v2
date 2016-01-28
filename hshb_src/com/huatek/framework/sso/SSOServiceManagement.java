package com.huatek.framework.sso;

import com.huatek.framework.entity.FwAccount;
import com.huatek.framework.entity.FwSystem;
import com.huatek.framework.entity.FwSystemIp;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.sso.HttpClientUtil;
import com.huatek.framework.sso.ResponseBean;
import com.huatek.framework.sso.SessionManagmentController;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.Parameter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class SSOServiceManagement {
	private static final Logger LOGGER = Logger.getLogger(SSOServiceManagement.class);
	private static Map<String, String> subSystemURLMap = new HashMap();
	private static Map<String, FwSystemIp> ssoServerIPMap = new HashMap();
	private static Map<String, FwSystemIp> subSystemIPMap = new HashMap();
	private static Map<Integer, String> ssoUrlMap = new HashMap();

	public static String getSSOServerUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + "/ddhbManage";
	}

	public static String getSubSystemUrl(HttpServletRequest request, FwSystem subFwSystem) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + "/ddhbManage";
	}

	public static String getSsoTokenURL(HttpServletRequest request) throws UnsupportedEncodingException {
		StringBuffer ssoServerUrl = new StringBuffer(getSSOServerUrl(request));
		StringBuffer localUrl = new StringBuffer("http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath());
		String url = request.getRequestURI();
		url = url.substring(url.lastIndexOf("/"));
		localUrl.append(url).append("?").append(request.getQueryString());
		ssoServerUrl.append("/sso.show?actionMethod=getToken&oldUrl=" + URLEncoder.encode(localUrl.toString(), "UTF-8"));
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("向SSO服务器:" + ssoServerUrl + "申请令牌。");
		}

		return ssoServerUrl.toString();
	}

	public static String getLocalUrl(HttpServletRequest request, String action) throws UnsupportedEncodingException {
		StringBuffer localUrl = new StringBuffer("http://");
		localUrl.append(request.getServerName()).append(":").append(request.getServerPort());
		localUrl.append(request.getContextPath());
		if (action != null) {
			localUrl.append(action);
		}

		return URLEncoder.encode(localUrl.toString(), "UTF-8");
	}

	public static void ssoLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mainSysSessionId = request.getParameter("tokenId");
		if (!CommonUtil.isZeroLengthTrimString(mainSysSessionId)) {
			String url = getSSOServerUrl(request);
			ArrayList nameValuePairList = new ArrayList();
			nameValuePairList.add(new BasicNameValuePair("mainSysSessionId", mainSysSessionId));
			setSubSysInformation(nameValuePairList, request);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("向SSO服务器：" + url + "请求登录验证");
			}

			String httpresponse = HttpClientUtil.postMethod(url + "/sso.show?actionMethod=ssoCheck&doLogin=true",
					nameValuePairList, mainSysSessionId);
			httpresponse = HttpClientUtil.trimJSONSata(httpresponse);
			ResponseBean responseBean = (ResponseBean) SessionManagmentController.GSON.fromJson(httpresponse,
					ResponseBean.class);
			if (responseBean.isSuccess()) {
				FwAccount account = (FwAccount) HttpClientUtil.unSerializeableEntity(responseBean.getRetData());
				if (account == null) { throw new BusinessRuntimeException("error.login.session.timeout"); }

				request.getSession().setAttribute("LoginAccount", account);
				request.getSession().setAttribute("SSO_TOKEN", mainSysSessionId);
				request.getSession().setAttribute("SSO_LOGIN_TIME", Long.valueOf(System.currentTimeMillis()));
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("SSO登录验证成功");
				}
			} else {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("SSO登录验证失败,重定向到平台登录界面。");
				}

				url = url + "/";
				response.sendRedirect(url);
			}

		}
	}

	private static void setSubSysInformation(List<NameValuePair> nameValuePairList, HttpServletRequest request)
			throws Exception {
		String subIp = request.getServerName();
		String subSessionId = request.getSession().getId();
		String port = String.valueOf(request.getServerPort());
		String path = request.getContextPath();
		String logoutUrl = "/Logout";
		nameValuePairList.add(new BasicNameValuePair("subIp", subIp));
		nameValuePairList.add(new BasicNameValuePair("port", port));
		nameValuePairList.add(new BasicNameValuePair("path", path));
		nameValuePairList.add(new BasicNameValuePair("logoutUrl", logoutUrl));
		nameValuePairList.add(new BasicNameValuePair("subSysSessionId", subSessionId));
	}

	public static boolean isSSOServer() {
		return Parameter.getInstance().getProp().getProperty("app.system.code", "").equals("default");
	}
}
