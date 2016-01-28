package com.huatek.hbwebsite.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontLoginFilter implements Filter {
	public static final String PLATMEMBERINFO_SERVICE = "platMemberInfoService";
	public static final String ERROR_MESSAGE = "errorMessage";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		hResponse.setHeader("Pragma", "No-Cache");
		hResponse.setHeader("Cache-Control", "No-Cache");
		hResponse.setDateHeader("Expires", 0L);
		if (hRequest.getSession().getAttribute("LoginAccount") == null) {
			HttpServletRequest req = (HttpServletRequest) request;
			String requestURI = req.getRequestURI();
			int index = requestURI.lastIndexOf("/");
			String str = requestURI.substring(index + 1);
			if (!str.equals("login.show") && !str.equals("register.show") && !str.equals("error.show")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.show?actionMethod=loginCheck");
				dispatcher.forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
