package com.huatek.framework.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

public class DisableUrlSessionFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
		} else {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			if (httpRequest.isRequestedSessionIdFromURL()) {
				HttpSession wrappedResponse = httpRequest.getSession();
				if (wrappedResponse != null) {
					wrappedResponse.invalidate();
				}
			}

			HttpServletResponseWrapper wrappedResponse1 = new HttpServletResponseWrapper(httpResponse) {
				public String encodeRedirectUrl(String url) {
					return url;
				}

				public String encodeRedirectURL(String url) {
					return url;
				}

				public String encodeUrl(String url) {
					return url;
				}

				public String encodeURL(String url) {
					return url;
				}
			};
			chain.doFilter(request, wrappedResponse1);
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}
