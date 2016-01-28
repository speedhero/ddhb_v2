package com.huatek.framework.util;

import com.huatek.framework.security.ClientInfoBean;
import com.huatek.framework.security.ClientInformation;
import com.huatek.framework.security.ClientInformationImpl;
import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.sso.SSOServiceManagement;
import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.SystemTitleUtil;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

public class CommonFilter implements Filter {
	private static final Log log = LogFactory.getLog(CommonFilter.class);
	
	protected String charset = "utf-8";
	protected boolean ignore = true;
	private FilterConfig fConfig = null;
	private static final String PARAM_PORTAL_LOCALE_LANGUAGE = "language";
	private ClientInformation clientInformationImpl;

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
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
				log.warn(ex);
			} catch (IllegalAccessException ex) {
				log.warn(ex);
			} catch (ClassNotFoundException ex) {
				log.warn(ex);
			}
		} else {
			this.clientInformationImpl = new ClientInformationImpl();
		}
	}

	protected String selectEncoding(ServletRequest request) {
		return this.charset;
	}

	private void setLocalCookie(HttpServletRequest request, HttpServletResponse response) {
		String locale = request.getParameter("language");
		CookieLocaleResolver resover = (CookieLocaleResolver) SpringContext.getBean("localeResolver");
		if (locale != null) {
			String[] localeInfo = locale.split("_");
			Locale newLocale = new Locale(localeInfo[0], localeInfo[1]);
			resover.setLocale(request, response, newLocale);
		}

		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, resover);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (this.ignore || request.getCharacterEncoding() == null) {
			String hRequest = this.selectEncoding(request);
			if (hRequest != null) {
				request.setCharacterEncoding(hRequest);
			}
		}

		this.setLocalCookie((HttpServletRequest) request, (HttpServletResponse) response);
		ClientInfoBean clientInfoBean = this.clientInformationImpl.collectClientInfo((HttpServletRequest) request);
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

	public void destroy() {
	}

}
