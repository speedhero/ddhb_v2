package com.huatek.framework.security;

import com.huatek.framework.entity.FwAccount;
import com.huatek.framework.entity.FwActionCnt;
import com.huatek.framework.entity.FwSrcAction;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
	private List<FwSrcAction> permitAction;
	private FwAccount operator;
	private Set<FwActionCnt> auditContents;
	private String actualActionPath;
	private FwSrcAction srcAction;

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

	public List<FwSrcAction> getPermitAction() {
		return this.permitAction;
	}

	public void setPermitAction(List<FwSrcAction> permitAction) {
		this.permitAction = permitAction;
	}

	public Set<FwActionCnt> getAuditContents() {
		return this.auditContents;
	}

	public void setAuditContents(Set<FwActionCnt> auditContents) {
		this.auditContents = auditContents;
	}

	public String getActualActionPath() {
		return this.actualActionPath;
	}

	public void setActualActionPath(String actualActionPath) {
		this.actualActionPath = actualActionPath;
	}

	public FwAccount getOperator() {
		return this.operator;
	}

	public void setOperator(FwAccount operator) {
		this.operator = operator;
	}

	public FwSrcAction getSrcAction() {
		return this.srcAction;
	}

	public void setSrcAction(FwSrcAction srcAction) {
		this.srcAction = srcAction;
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
