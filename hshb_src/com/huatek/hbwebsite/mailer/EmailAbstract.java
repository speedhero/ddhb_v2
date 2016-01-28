package com.huatek.hbwebsite.mailer;

import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public abstract class EmailAbstract {
	public static final Long TEMPL_ID_FORGET_PASSWORD = 1L; // 密码找回邮件
	public static final Long TEMPL_ID_REDUCE_NOTICE = 2L; // 房源降价通知邮件
	public static final Long TEMPL_ID_NEWS_NOTICE = 3L; // 新闻订阅邮件
	public static final Long TEMPL_ID_EMAIL_VERIFICATION = 4L; // 邮件地址验证邮件
	public static final Long TEMPL_ID_NEWS_NOTICE_VERIFICATION = 5L; // 新闻订阅校验邮件

	protected String from;
	protected String subject;
	protected JavaMailSender sender;
	protected FreeMarkerConfigurer freeMarkerConfigurer = null;

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public JavaMailSender getSender() {
		return this.sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	/**
	 * @deprecated
	 * 
	 * @param var1
	 * @param var2
	 * @throws Exception
	 */
	public abstract void sendEmail(String[] var1, String var2) throws Exception;

	/**
	 * @deprecated
	 * 
	 * @param var1
	 * @param var2
	 * @throws Exception
	 */
	public abstract void sendForgetPasswordEmail(String[] var1, String var2) throws Exception;

	/**
	 * @deprecated
	 * 
	 * @param var1
	 * @param var2
	 * @throws Exception
	 */
	public abstract void sendReduceNoticeEmail(String[] var1, String var2) throws Exception;

	/**
	 * @deprecated
	 * 
	 * @param var1
	 * @param var2
	 * @throws Exception
	 */
	public abstract void sendNewsNoticeEmail(String[] var1, String var2) throws Exception;

	/**
	 * @deprecated
	 * 
	 * @param var1
	 * @param var2
	 * @throws Exception
	 */
	public abstract void sendReduceNoticeValidEmail(String[] var1, String var2) throws Exception;

	/**
	 * @deprecated
	 * 
	 * @param var1
	 * @param var2
	 * @throws Exception
	 */
	public abstract void sendNewsNoticeValidEmail(String[] var1, String var2) throws Exception;

	public abstract void sendForgetPasswordEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception;

	public abstract void sendReduceNoticeEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception;

	public abstract void sendNewsNoticeEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception;

	public abstract void sendReduceNoticeValidEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception;

	public abstract void sendNewsNoticeValidEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception;
	
	public abstract void sendHtmlEmail(String content, String subject, String to, String from) throws Exception;

	public abstract void sendTextEmail(String content, String subject, String to, String from) throws Exception;

	public abstract void sendEmail(String content, String subject, String to, String from, Boolean isHtmlMail) throws Exception;

	public abstract void sendEmail(String mailTemplate, Map<String, Object> dataMap, String subject, String to,
			String from, Boolean isHtmlMail) throws Exception;
}
