package com.huatek.hbwebsite.mailer;

import com.huatek.hbwebsite.mailer.EmailAbstract;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.mail.entity.MailTemplate;
import com.huatek.mail.service.VelocityUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * 邮件发送服务实现类
 * @author Administrator
 *
 */
public class EmailServiceImpl extends EmailAbstract {
//	@Autowired
//	JavaMailSenderImpl javaMailSender;

	/**
	 * @deprecated
	 */
	public void sendEmail(String[] content, String address) throws Exception {
		sendEmail("registerActive.html", "豪世华邦会员【邮箱验证】", content, address);
	}

	/**
	 * @deprecated
	 */	
	public void sendForgetPasswordEmail(String[] content, String address) throws Exception {
		sendEmail("forgetPasswordActive.html", "豪世华邦会员【密码找回】", content, address);
	}

	/**
	 * @deprecated
	 */	
	public void sendReduceNoticeEmail(String[] content, String address) throws Exception {
		sendEmail("reduceNotice.html", "豪世华邦【房源降价通知】", content, address);
	}

	/**
	 * @deprecated
	 */	
	public void sendNewsNoticeEmail(String[] content, String address) throws Exception {
		sendEmail("newsNotice.html", "豪世华邦【新闻订阅】", content, address);
	}

	/**
	 * @deprecated
	 */	
	public void sendReduceNoticeValidEmail(String[] content, String address) throws Exception {
		sendEmail("validEmailAddress.html", "豪世华邦【邮箱验证】", content, address);
	}

	/**
	 * @deprecated
	 */	
	public void sendNewsNoticeValidEmail(String[] content, String address) throws Exception {
		sendEmail("validEmailNewsNotice.html", "豪世华邦【邮箱验证】", content, address);
	}

	/**
	 * 发送邮件
	 * @param templName 模板名称
	 * @param subject		邮件主题
	 * @param content		邮件内容(多部分构成的数组)
	 * @param address		接收人地址
	 * @throws Exception
	 * 
	 * @deprecated
	 */
	public void sendEmail(String templName, String subject, String[] content, String address) throws Exception {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setTo(address);
		helper.setFrom(FrontSystemSettingUtil.getInstance().getEmailAccount());
		helper.setSubject(subject);
		String htmlText = renderMailContent(content, templName);
		helper.setText(htmlText, true);
		sendMail(msg);
	}
	
	/**
	 * 根据内容的模板渲染邮件内容
	 * @param content		邮件内容
	 * @param templName	模板名称
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * 
	 * @deprecated
	 */
	private String renderMailContent(String[] content, String templName) throws IOException, TemplateException{
		String htmlText = "";
		Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("emailMessage", content);
		htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
		return htmlText;
	}
	
	public void sendForgetPasswordEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception {
		String subject = "豪世华邦会员【密码找回】";
		sendEmail(mailTemplate, dataMap, subject, to, null, true);
	}

	public void sendReduceNoticeEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception {
		String subject = "豪世华邦【房源降价通知】";
		sendEmail(mailTemplate, dataMap, subject, to, null, true);
	}

	public void sendNewsNoticeEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception {
		String subject = "豪世华邦【新闻订阅】";
		sendEmail(mailTemplate, dataMap, subject, to, null, true);
	}

	public void sendReduceNoticeValidEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception {
		String subject = "豪世华邦【邮箱验证】";
		sendEmail(mailTemplate, dataMap, subject, to, null, true);
	}

	public void sendNewsNoticeValidEmail(String mailTemplate, Map<String, Object> dataMap, String to) throws Exception {
		String subject = "豪世华邦【邮箱验证】";
		sendEmail(mailTemplate, dataMap, subject, to, null, true);
	}

	
	/**
	 * 发送HTML邮件
	 * @param content
	 * @param subject
	 * @param to
	 * @param from
	 * @throws Exception
	 */
	public void sendHtmlEmail(String content, String subject, String to, String from) throws Exception {
		sendEmail(content, subject, to, from, true);
	}
	/**
	 * 发送纯文本邮件
	 * @param content
	 * @param subject
	 * @param to
	 * @param from
	 * @throws Exception
	 */
	public void sendTextEmail(String content, String subject, String to, String from) throws Exception {
		sendEmail(content, subject, to, from, false);
	}
	/**
	 * 发送邮件
	 * @param content	邮件内容
	 * @param subject 邮件主题 
	 * @param to			收件人地址
	 * @param from		发件人地址
	 * @param isHtmlMail	是否 HTML邮件标志
	 * @throws Exception
	 */
	public void sendEmail(String content, String subject, String to, String from, Boolean isHtmlMail) throws Exception {
		from = from == null ? FrontSystemSettingUtil.getInstance().getEmailAccount(): from;
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setTo(to);
		helper.setFrom(from);
		helper.setSubject(subject);
		helper.setText(content, isHtmlMail);
		sendMail(msg);
	}

	/**
	 * 发送邮件
	 * @param mailTemplate	邮件模板
	 * @param dataMap				数据集
	 * @param subject				邮件主题
	 * @param to						收件人地址
	 * @param from					发件人地址
	 * @param isHtmlMail		是否HTML邮件标志
	 * @throws Exception
	 */
	public void sendEmail(String mailTemplate, Map<String, Object> dataMap, String subject, String to, String from, Boolean isHtmlMail) throws Exception {
		String content = renderMailContent(mailTemplate, dataMap);
		
		sendEmail(content, subject, to, from, isHtmlMail);
	}

	/**
	 * 根据Velocity模板生成邮件内容
	 * @param mailTemplate	邮件模板
	 * @param dataMap				数据集
	 * @return
	 */
	public String renderMailContent(String mailTemplate, Map<String, Object> dataMap) {
		VelocityContext context = new VelocityContext();

		Iterator<Entry<String, Object>> it = dataMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> entry = it.next();
			context.put(entry.getKey(), entry.getValue());
		}

		StringWriter writer = new StringWriter();
		VelocityEngine engine = new VelocityEngine(VelocityUtil.props);
		engine.evaluate(context, writer, "", mailTemplate);
		String mailContent = writer.toString();
		return mailContent;
	}

	/**
	 * 发送邮件
	 * @param msg
	 */
	private void sendMail(MimeMessage msg){
		//原来的逻辑有错，设置的发件服务器信息与实际发送邮件的对象不是同一个
		//Modified by syf at 2015.01.29
		//this.javaMailSender.setUsername(FrontSystemSettingUtil.getInstance().getEmailAccount());
		//this.javaMailSender.setPassword(FrontSystemSettingUtil.getInstance().getEmailPassowrd());
		//this.javaMailSender.setHost(FrontSystemSettingUtil.getInstance().getEmailHost());
		//this.javaMailSender.setPort(Integer.valueOf(FrontSystemSettingUtil.getInstance().getEmailPort()));
		if(sender instanceof JavaMailSenderImpl){
			((JavaMailSenderImpl)sender).setUsername(FrontSystemSettingUtil.getInstance().getEmailAccount());
			((JavaMailSenderImpl)sender).setPassword(FrontSystemSettingUtil.getInstance().getEmailPassowrd());
			((JavaMailSenderImpl)sender).setHost(FrontSystemSettingUtil.getInstance().getEmailHost());
			((JavaMailSenderImpl)sender).setPort(Integer.valueOf(FrontSystemSettingUtil.getInstance().getEmailPort()).intValue());
		}
		sender.send(msg);
	}
}
