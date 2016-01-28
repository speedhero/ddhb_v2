package com.huatek.framework.util;

import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.ddhb.manage.frontsystemsetting.service.FrontSystemSettingService;
import com.huatek.framework.util.SpringContext;

public class PictureHouseUrlUtil {
	private FrontSystemSettingService frontSystemSettingService = null;
	private static String backPicUrl = "";
	private static final PictureHouseUrlUtil pageSizeGetUtil = new PictureHouseUrlUtil();

	public static PictureHouseUrlUtil getInstance() {
		return pageSizeGetUtil;
	}

	public String getBackPicUrl() {
		if ("".equals(backPicUrl)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("front_pic_url");
			backPicUrl = frontSystemSetting.getSettingValue();
			return backPicUrl;
		} else {
			return backPicUrl;
		}
	}

	public static synchronized void clean() {
		backPicUrl = "";
	}
}
