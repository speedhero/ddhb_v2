package com.huatek.hbwebsite.security;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ClientInfoBean implements Serializable {
	private static final long serialVersionUID = 8810206692236045600L;
	private String requestURL;
	private String actionURL;
	private String actionMethod;
	private String contextPath;
	private String serverHost;
	private int serverPort;
	private Calendar accessCalendar;
	private String actionId;
	private String hostIp;
	private int hostPort;
	private Long menuId;
	private boolean isLogged;
	private String requestMethod;
	public Map<String, Object> envParamMap = new HashMap();
	Locale locale;
	private PlatMemberInfo operator;
	private String actualActionPath;

	public String getRequestMethod() {
		return this.requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getActualActionPath() {
		return this.actualActionPath;
	}

	public void setActualActionPath(String actualActionPath) {
		this.actualActionPath = actualActionPath;
	}

	public PlatMemberInfo getOperator() {
		return this.operator;
	}

	public void setOperator(PlatMemberInfo operator) {
		this.operator = operator;
	}

	public int getHostPort() {
		return this.hostPort;
	}

	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	public Calendar getAccessCalendar() {
		return this.accessCalendar;
	}

	public void setAccessCalendar(Calendar accessCalendar) {
		this.accessCalendar = accessCalendar;
	}

	public String getActionId() {
		return this.actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionURL() {
		return this.actionURL;
	}

	public void setActionURL(String actionName) {
		this.actionURL = actionName;
	}

	public String getHostIp() {
		return this.hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String toString() {
		Date time = Calendar.getInstance().getTime();
		if (time == null) {
			time = new Date();
		}

		return "actionURL=" + this.actionURL + "\n" + "accessCalendar=" + time + "\n" + "actionId=" + this.actionId + "\n"
				+ "hostIp=" + this.hostIp;
	}

	public String getActionMethod() {
		return this.actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getContextPath() {
		return this.contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public int getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerHost() {
		return this.serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public boolean isLogged() {
		return this.isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public String getRequestURL() {
		return this.requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
