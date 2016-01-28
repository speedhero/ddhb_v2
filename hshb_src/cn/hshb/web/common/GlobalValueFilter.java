package cn.hshb.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

import com.huatek.hbwebsite.util.CompanyInfo;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.SystemTitleUtil;

/**
 * Servlet Filter implementation class GlobalValueFilter
 */
// @WebFilter(description = "全局变量过滤器", urlPatterns = { "/*" })
public class GlobalValueFilter implements Filter {
	private FilterConfig filterConfig = null;

	/**
	 * Default constructor.
	 */
	public GlobalValueFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		// 在application中写入全局变量
		setGlobalProperties((HttpServletRequest) request, (HttpServletResponse) response);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * 在application中写入页面常用的变量
	 * 
	 * @param request
	 * @param response
	 */
	private void setGlobalProperties(HttpServletRequest request, HttpServletResponse response) {
		
		String serverName = request.getServerName();
		if(!(serverName.equalsIgnoreCase("localhost") || serverName.equalsIgnoreCase("127.0.0.1") || serverName.contains("hshb.cn")))
			serverName = "www.hshb.cn";
		// if(request.getAttribute("globalUrl") == null){
		String gUrl = "http://" + serverName;
		int port = request.getServerPort();
		if (port != 80) gUrl += ":" + port;
		gUrl += request.getContextPath();
		if (!gUrl.endsWith("/")) gUrl += "/";
		request.setAttribute("globalUrl", gUrl);
		// }

		ServletContext app = request.getServletContext();
		
		// 如果request未设置globalUrl，则jsp会从application中取
//		if (request.getAttribute("globalUrl") == null && app.getAttribute("globalUrl") == null) {
//			app.setAttribute("globalUrl", "http://www.hshb.cn/");
//		}

		if (app.getAttribute("pictureHost") == null) {
			try{
				app.setAttribute("pictureHost", FrontSystemSettingUtil.getInstance().getForntPicUrl());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("pictureHost", "http://218.108.238.162:80/frontImages");
			}
		}
		if (app.getAttribute("homePageLogo") == null) {
			try{
				app.setAttribute("homePageLogo", FrontSystemSettingUtil.getInstance().getLogoImgSrc());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("homePageLogo", "/homepage/512c604f-d7a9-4004-b5c3-057824851957.gif");
			}
		}
		if (app.getAttribute("homeLogoPc") == null) {
			try{
				app.setAttribute("homeLogoPc", FrontSystemSettingUtil.getInstance().getLogoPc());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("homeLogoPc", "/homepage/512c604f-d7a9-4004-b5c3-057824851957.gif");
			}
		}
		if (app.getAttribute("homeLogoPad") == null) {
			try{
				app.setAttribute("homeLogoPad", FrontSystemSettingUtil.getInstance().getLogoPad());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("homeLogoPad", "/homepage/05189d9e-a5ac-44ab-8104-0b57902e65bb.gif");
			}
		}
		if (app.getAttribute("homeLogoMb") == null) {
			try{
				app.setAttribute("homeLogoMb", FrontSystemSettingUtil.getInstance().getLogoMb());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("homeLogoMb", "/homepage/3a5b317d-e18c-4793-8a9e-0ba6d98b3db7.gif");
			}
		}
		if (app.getAttribute("otherpageHover") == null) {
			try{
				app.setAttribute("otherpageHover", FrontSystemSettingUtil.getInstance().getLogoImgHover());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("otherpageHover", "豪世华邦，专业因为懂你!");
			}
		}
		if (app.getAttribute("qqService") == null) {
			try{
				app.setAttribute("qqService", FrontSystemSettingUtil.getInstance().getQQService());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("qqService", "kfguin=\"4008966888\";eid=\"218808P8z8p8x8R8R8z8q\";ws=\"http://www.hshb.cn/ddhb\"; type=\"0\";wpadomain=\"b\";");
			}
		}
		if (app.getAttribute("title") == null) {
			try{
				app.setAttribute("title", SystemTitleUtil.getInstance().getSystemTitle());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("title", "豪世华邦，专业因为懂你");
			}
		}
		if (app.getAttribute("companyInfo") == null) {
			try{
				app.setAttribute("companyInfo", CompanyInfo.getInstance().getCompanyInfoIntro());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("companyInfo", "<p><span style=\"font-family: 微软雅黑; font-size: 12px; line-height: 25px; text-decoration: none; \"><a href=\"http://www.hshb.cn\" target=\"_blank\">豪世华邦房产中介</a>为您提供<a href=\"http://www.hshb.cn\" target=\"_blank\" style=\"color: text-decoration: none;padding:0;\">杭州二手房交易平台</a>，包括：<a href=\"http://www.hshb.cn\" target=\"_blank\" style=\"text-decoration: none;padding:0;\">杭州二手房房源</a>，<a href=\"http://www.hshb.cn\" target=\"_blank\" style=\"text-decoration: none;padding:0;\">杭州租房信息</a>，<a href=\"http://www.hshb.cn\" target=\"_blank\" style=\"text-decoration: none;padding:0;\">杭州全部小区房价走势</a>，<a href=\"http://www.hshb.cn\" target=\"_blank\" style=\"text-decoration: none; padding:0;\">杭州二手房价格</a>，<a href=\"http://www.hshb.cn\" target=\"_blank\" style=\"text-decoration: none;padding:0;\">杭州租房价格</a>等房产相关的信息。</span></p>");
			}
		}
		if (app.getAttribute("companyCopy") == null) {
			try{
				app.setAttribute("companyCopy", CompanyInfo.getInstance().getcompanyInfoCopyRight());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("companyCopy", "<p><a href=\"http://www.miibeian.gov.cn\" target=\"_blank\"><span style=\"color: #c0c0c0;\">浙ICP备07024618-2号</span></a>&nbsp; Copyright 2007-2015 hshb.cn All Rights Reserved.<br/></p>");
			}
		}
		if (app.getAttribute("backImg") == null) {
			try{
				app.setAttribute("backImg", FrontSystemSettingUtil.getInstance().getBackgroundImg());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("backImg", "/homepage/6b92ad48-4c53-4289-a5b6-987bceb3b5b8.jpg");
			}
		}
		if (app.getAttribute("backColor") == null) {
			try{
				app.setAttribute("backColor", FrontSystemSettingUtil.getInstance().getBackgroundColor());
			}catch(Exception ex){
				Log.error(ex);
				app.setAttribute("backColor", "#dcd3c2");
			}
		}
	}
}
