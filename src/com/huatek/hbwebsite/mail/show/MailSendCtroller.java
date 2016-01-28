package com.huatek.hbwebsite.mail.show;

import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.util.Util;
import com.huatek.message.MessageFactory;
import com.huatek.message.MessageQueueBean;
import com.huatek.message.service.MessageService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/mailSend.do" })
public class MailSendCtroller {
	@Resource(name = "messageSendService")
	private MessageService mailMessageService;

	@RequestMapping(params = { "actionMethod=send" })
	public String sendMail(Long mailId) {
		MessageQueueBean messageQueueBean = new MessageQueueBean(mailId, null, this.mailMessageService,
				ThreadLocalClient.get());
		MessageFactory.getMessageProcess("mailSender").push(messageQueueBean);
		return Util.printString("success");
	}
}
