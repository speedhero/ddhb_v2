package com.huatek.hbwebsite.mail.show;

import com.huatek.framework.util.Util;
import com.huatek.mail.service.MailUtil;
import com.huatek.message.MessageFactory;
import com.huatek.message.MessageQueueBean;
import com.huatek.message.service.MessageService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/regeisterMailSend.show" })
public class RegisterMailSendCtroller {
	@Resource(name = "messageSendService")
	private MessageService mailMessageService;

	@RequestMapping(params = { "actionMethod=send" })
	public String sendMail(Long mailId, HttpServletRequest request) {
		MessageQueueBean messageQueueBean = new MessageQueueBean(mailId, (Object) null, this.mailMessageService,
				MailUtil.initMockClient(request));
		MessageFactory.getMessageProcess("mailSender").push(messageQueueBean);
		return Util.printString("success");
	}
}
