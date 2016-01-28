package com.huatek.hbwebsite.util;

import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.ddhb.manage.frontsystemsetting.service.FrontSystemSettingService;
import com.huatek.framework.util.SpringContext;

public class CompanyInfo {
	private FrontSystemSettingService frontSystemSettingService = null;
	private static String companyInfoIntro = "";
	private static String companyInfoCopyRight = "";
	private static final CompanyInfo companyInfo = new CompanyInfo();

	public static CompanyInfo getInstance() {
		return companyInfo;
	}

	public synchronized String getCompanyInfoIntro() {
		if (!"".equals(companyInfoIntro) && companyInfoIntro != null) {
			return companyInfoIntro;
		} else {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("company_info");
			return frontSystemSetting.getSettingValue();
		}
	}

	public synchronized String getcompanyInfoCopyRight() {
		if (!"".equals(companyInfoCopyRight) && companyInfoCopyRight != null) {
			return companyInfoCopyRight;
		} else {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("company_copyright");
			return frontSystemSetting.getSettingValue();
		}
	}

	public static synchronized void clean() {
		companyInfoIntro = "";
		companyInfoCopyRight = "";
	}
}
