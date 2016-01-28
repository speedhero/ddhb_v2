package com.huatek.hbwebsite.util;

import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.ddhb.manage.frontsystemsetting.service.FrontSystemSettingService;
import com.huatek.framework.util.SpringContext;

public class SystemTitleUtil {
	private FrontSystemSettingService frontSystemSettingService = null;
	private static String systemTitle = "";
	private static final SystemTitleUtil systemTitleUtil = new SystemTitleUtil();

	public static SystemTitleUtil getInstance() {
		return systemTitleUtil;
	}

	public synchronized String getSystemTitle() {
		if (!"".equals(systemTitle) && systemTitle != null) {
			return systemTitle;
		} else {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("FrontSystemTitle");
			return frontSystemSetting.getSettingValue();
		}
	}

	public static synchronized void clean() {
		systemTitle = "";
	}
}
