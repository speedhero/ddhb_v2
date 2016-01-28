package com.huatek.hbwebsite.mail.service;

import java.util.Date;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.mailer.EmailServiceImpl;
import com.huatek.mail.entity.MailSendInfo;
import com.huatek.mail.service.SimpleMailSender;
import com.huatek.message.MessageQueueBean;
import com.huatek.message.service.MessageService;

public class MessageMailServiceImpl extends BaseServiceImpl implements MessageService {
	
	public void execute(MessageQueueBean messageQueueBean) {
		Long mailId = messageQueueBean.getBusinessID();
		MailSendInfo mailSendInfo = (MailSendInfo) super.getObjectById(MailSendInfo.class, mailId);
		if (mailSendInfo.getSendStatus().intValue() != 1) {
			
			//把邮件发送统一改成由 EmailServiceImpl 来发送
			//Modified by Sheng Youfu at 2015.03.04
//			SimpleMailSender.sendHtmlMail(mailSendInfo);

			EmailServiceImpl mailer = new EmailServiceImpl();
			String content = mailSendInfo.getMailContent();
			String subject = mailSendInfo.getMailTitle();
			String to = mailSendInfo.getRecevier();
			String from = mailSendInfo.getConfig().getFromMail();
			try{
				mailer.sendHtmlEmail(content, subject, to, from);
				mailSendInfo.setSendStatus(1);
			}catch(Exception ex){
				mailSendInfo.setSendStatus(2);
				mailSendInfo.setErrorMsg(Util.getAssignedLengthStr(ex.getMessage(), 300));
			}
			mailSendInfo.setSendTime(new Date());
			
			mailSendInfo.setSendUser(messageQueueBean.getClientInfoBean().getOperator());
			super.update(mailSendInfo);
		}
	}
}
