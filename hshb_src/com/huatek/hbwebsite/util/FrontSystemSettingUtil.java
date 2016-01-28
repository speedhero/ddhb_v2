package com.huatek.hbwebsite.util;

import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.ddhb.manage.frontsystemsetting.service.FrontSystemSettingService;
import com.huatek.framework.util.SpringContext;

public class FrontSystemSettingUtil {
	private FrontSystemSettingService frontSystemSettingService = null;
	private static Integer picTypeSize = Integer.valueOf(0);
	private static Integer listTypeSize = Integer.valueOf(0);
	private static String logoImgSrc = "";
	private static String qqService = "";
	private static String otherpageHover = "";
	private static String backImg = "";
	private static String backColor = "";
	private static String forntPicUrl = "";
	private static String emailAccount = "";
	private static String emailPassowrd = "";
	private static String emailHost = "";
	private static String emailPort = "";
	private static String regesterReason = "";
	private static String emailaddressReason = "";
	private static String privatePolicy = "";
	private static String messageResetTime = "";
	private static String logoPc = "";
	private static String logoPad = "";
	private static String logoMb = "";
	private static final FrontSystemSettingUtil pageSizeGetUtil = new FrontSystemSettingUtil();

	public static FrontSystemSettingUtil getInstance() {
		return pageSizeGetUtil;
	}

	public Integer getPicTypeSize() {
		if (picTypeSize.intValue() == 0) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService
					.loadSpecificSetting("picture_cutbean_size");
			picTypeSize = Integer.valueOf(frontSystemSetting.getSettingValue());
			return picTypeSize;
		} else {
			return picTypeSize;
		}
	}

	public Integer getListTypeSize() {
		if (listTypeSize.intValue() == 0) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("list_cutbean_size");
			listTypeSize = Integer.valueOf(frontSystemSetting.getSettingValue());
			return listTypeSize;
		} else {
			return listTypeSize;
		}
	}

	public String getLogoImgSrc() {
		if ("".equals(logoImgSrc)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("homepage_logo");
			logoImgSrc = frontSystemSetting.getSettingValue();
			return logoImgSrc;
		} else {
			return logoImgSrc;
		}
	}

	public String getQQService() {
		if ("".equals(qqService)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("system_qq");
			qqService = frontSystemSetting.getSettingValue();
			return qqService;
		} else {
			return qqService;
		}
	}

	public String getLogoImgHover() {
		if ("".equals(otherpageHover)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService
					.loadSpecificSetting("otherpage_logo_hover");
			otherpageHover = frontSystemSetting.getSettingValue();
			return otherpageHover;
		} else {
			return otherpageHover;
		}
	}

	public String getBackgroundImg() {
		if ("".equals(backImg)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("homepage_background");
			backImg = frontSystemSetting.getSettingValue();
			return backImg;
		} else {
			return backImg;
		}
	}

	public String getBackgroundColor() {
		if ("".equals(backColor)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("homeback_color");
			backColor = frontSystemSetting.getSettingValue();
			return backColor;
		} else {
			return backColor;
		}
	}

	public String getForntPicUrl() {
		if ("".equals(forntPicUrl)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("front_pic_url");
			forntPicUrl = frontSystemSetting.getSettingValue();
			return forntPicUrl;
		} else {
			return forntPicUrl;
		}
	}

	public String getEmailAccount() {
		if ("".equals(emailAccount)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("email_account");
			emailAccount = frontSystemSetting.getSettingValue();
			return emailAccount;
		} else {
			return emailAccount;
		}
	}

	public String getEmailPassowrd() {
		if ("".equals(emailPassowrd)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("email_password");
			emailPassowrd = frontSystemSetting.getSettingValue();
			return emailPassowrd;
		} else {
			return emailPassowrd;
		}
	}

	public String getEmailHost() {
		if ("".equals(emailHost)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("email_host");
			emailHost = frontSystemSetting.getSettingValue();
			return emailHost;
		} else {
			return emailHost;
		}
	}

	public String getRegesterReason() {
		if ("".equals(regesterReason)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("regester_reason");
			regesterReason = frontSystemSetting.getSettingValue();
			return regesterReason;
		} else {
			return regesterReason;
		}
	}

	public String getEmailaddressReason() {
		if ("".equals(emailaddressReason)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("emailaddress_reason");
			emailaddressReason = frontSystemSetting.getSettingValue();
			return emailaddressReason;
		} else {
			return emailaddressReason;
		}
	}

	public String getPrivatePolicy() {
		if ("".equals(privatePolicy)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("private_policy");
			privatePolicy = frontSystemSetting.getSettingValue();
			return privatePolicy;
		} else {
			return privatePolicy;
		}
	}

	public String getMessageResetTime() {
		if ("".equals(messageResetTime)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("message_reset_time");
			messageResetTime = frontSystemSetting.getSettingValue();
			return messageResetTime;
		} else {
			return messageResetTime;
		}
	}

	public String getEmailPort() {
		if ("".equals(emailPort)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("email_port");
			emailPort = frontSystemSetting.getSettingValue();
			return emailPort;
		} else {
			return emailPort;
		}
	}

	public String getLogoPc() {
		if ("".equals(logoPc)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("homepage_logo");
			logoPc = frontSystemSetting.getSettingValue();
			return logoPc;
		} else {
			return logoPc;
		}
	}

	public String getLogoPad() {
		if ("".equals(logoPad)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("homepage_logo_pad");
			logoPad = frontSystemSetting.getSettingValue();
			return logoPad;
		} else {
			return logoPad;
		}
	}

	public String getLogoMb() {
		if ("".equals(logoMb)) {
			this.frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = this.frontSystemSettingService.loadSpecificSetting("homepage_logo_mb");
			logoMb = frontSystemSetting.getSettingValue();
			return logoMb;
		} else {
			return logoMb;
		}
	}

	public static synchronized void clean() {
		picTypeSize = Integer.valueOf(0);
		listTypeSize = Integer.valueOf(0);
		logoImgSrc = "";
		otherpageHover = "";
		forntPicUrl = "";
		emailAccount = "";
		emailPassowrd = "";
		emailHost = "";
		emailPort = "";
		messageResetTime = "";
		privatePolicy = "";
		emailaddressReason = "";
		regesterReason = "";
		logoPc = "";
		logoPad = "";
		logoMb = "";
		backImg = "";
		backColor = "";
	}
}
