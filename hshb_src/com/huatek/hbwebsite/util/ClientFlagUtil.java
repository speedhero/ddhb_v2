package com.huatek.hbwebsite.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientFlagUtil {
	private static final String CLIENT_FLAG = "clientFlag";
	private static final Integer CLIENT_MAX_AGE = 3600000;
	
	/**
	 * 从Cookie中获取客户端标志
	 * @param request
	 * @return
	 */
	public static synchronized String getClientFlag(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String clientFlag = null;
		if (cookies == null) {
			return null;
		} else {
			for(Cookie cookie: cookies){
				if (cookie.getName().equals(CLIENT_FLAG)) {
					clientFlag = cookie.getValue();
					break;
				}
			}
			return clientFlag;
		}
	}
	
	/**
	 * 生成客户端标志
	 * @param response
	 */
	public static void setClientFlag(HttpServletResponse response){
		String uuid = UUID.randomUUID().toString();
		Cookie cookie = new Cookie(CLIENT_FLAG, uuid);
		cookie.setMaxAge(CLIENT_MAX_AGE);
		response.addCookie(cookie);
	}
}
