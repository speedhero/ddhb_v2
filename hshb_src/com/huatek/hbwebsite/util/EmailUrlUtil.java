package com.huatek.hbwebsite.util;

import com.huatek.framework.entity.FwSystem;
import com.huatek.framework.service.SystemService;
import com.huatek.framework.util.SpringContext;

public class EmailUrlUtil {
	private SystemService systemService = null;
	private static String emaiUrl = "";
	private static final EmailUrlUtil emailUrl = new EmailUrlUtil();

	public static EmailUrlUtil getInstance() {
		return emailUrl;
	}

	public String getEmailUrl() {
		if ("".equals(emaiUrl)) {
			this.systemService = (SystemService) SpringContext.getBean("systemService");
			FwSystem fwSystem = this.systemService.getFwSystemById(Long.valueOf(6L));
			emaiUrl = "http://" + fwSystem.getSysIp() + ":" + fwSystem.getSysPort() + "/" + fwSystem.getSysCode() + "/";
			return emaiUrl;
		} else {
			return emaiUrl;
		}
	}
}
