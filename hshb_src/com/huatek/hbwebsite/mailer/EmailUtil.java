package com.huatek.hbwebsite.mailer;

import com.huatek.hbwebsite.util.ReadXML;
import java.text.MessageFormat;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @deprecated
 * 
 * @TODO:本类未找到调用的地方，待确定无用后要删除
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 *
 */
public class EmailUtil {
	private JavaMailSender sender;
	private SimpleMailMessage simpleMailMessage;

	public void sendMail(String title, String content, String[] emailAddress) {
		String path = this.getClass().getResource("/emailModel.xml").getPath();
		String str = (new ReadXML()).read(path);
		String[] obj = content.split(";");
		str = MessageFormat.format(str, obj);

		try {
			MimeMessage mimeMessage = this.sender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			messageHelper.setFrom(this.simpleMailMessage.getFrom());
			messageHelper.setSubject(title);
			messageHelper.setText("降价房源通知信息" + str, true);
			messageHelper.setTo(emailAddress);
			this.sender.send(mimeMessage);
		} catch (Exception var9) {
			;
		}

	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public JavaMailSender getSender() {
		return this.sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}
}
