package com.huatek.hbwebsite.util;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.huatek.framework.sso.SSOServiceManagement;
import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.security.ClientInfoBean;
import com.huatek.hbwebsite.security.ClientInformation;
import com.huatek.hbwebsite.security.ClientInformationImpl;
import com.huatek.hbwebsite.security.ThreadLocalClient;

public class CommonFilter implements Filter {
	private static final Logger log = Logger.getLogger(CommonFilter.class);

	protected String charset = "utf-8";
	protected boolean ignore = true;
//	private FilterConfig fConfig;
	private static final String PARAM_PORTAL_LOCALE_LANGUAGE = "language";
	private ClientInformation clientInformationImpl;

	public void init(FilterConfig fConfig) throws ServletException {
//		this.fConfig = fConfig;
		this.charset = fConfig.getInitParameter("charset");
		String value = fConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}

		if (fConfig.getInitParameter("clientInformationImpl") != null) {
			try {
				this.clientInformationImpl = (ClientInformation) Class.forName(fConfig.getInitParameter("clientInformationImpl")).newInstance();
			} catch (InstantiationException ex) {
				log.error(ex.getMessage());
			} catch (IllegalAccessException ex) {
				log.error(ex.getMessage());
			} catch (ClassNotFoundException ex) {
				log.error(ex.getMessage());
			}
		} else {
			this.clientInformationImpl = new ClientInformationImpl();
		}
	}

	protected String selectEncoding(ServletRequest request) {
		return this.charset;
	}

	private void setLocalCookie(HttpServletRequest request, HttpServletResponse response) {
		String locale = request.getParameter(PARAM_PORTAL_LOCALE_LANGUAGE);
		CookieLocaleResolver resover = (CookieLocaleResolver) SpringContext.getBean("localeResolver");
		if (locale != null) {
			String[] cookies = locale.split("_");
			Locale _locale = new Locale(cookies[0], cookies[1]);
			resover.setLocale(request, response, _locale);
		}

		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, resover);
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		
		if (clientFlag == null) {
			ClientFlagUtil.setClientFlag(response);
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		if (this.ignore || request.getCharacterEncoding() == null) {
			String httpRequest = this.selectEncoding(request);
			if (httpRequest != null) {
				request.setCharacterEncoding(httpRequest);
			}
		}

		HttpServletRequest httpRequest1 = (HttpServletRequest) request;
		String reqUrl = httpRequest1.getRequestURI().toString();
		if (reqUrl.trim().endsWith(".do") && reqUrl.indexOf("frontLogin.do") < 0
				&& httpRequest1.getSession().getAttribute("LoginMember") == null) {
			HttpServletResponse hRequest1 = (HttpServletResponse) response;
			hRequest1.sendRedirect("sso.show?actionMethod=logout");
		} else {
			this.setLocalCookie((HttpServletRequest) request, (HttpServletResponse) response);
			HttpServletRequest hRequest = (HttpServletRequest) request;
			ClientInfoBean clientInfoBean = this.clientInformationImpl.collectClientInfo(hRequest);
			ThreadLocalClient.put(clientInfoBean);
			request.setAttribute("ht_globalUrl", SSOServiceManagement.getSSOServerUrl((HttpServletRequest) request));
			HttpServletResponse hResponse = (HttpServletResponse) response;
			hResponse.setHeader("Pragma", "No-Cache");
			hResponse.setHeader("Cache-Control", "No-Cache");
			hResponse.setDateHeader("Expires", 0L);
			chain.doFilter(request, response);
			ThreadLocalClient.remove();
			clientInfoBean = null;
		}
	}

	public void destroy() {
	}

}
