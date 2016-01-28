package com.huatek.hbwebsite.news.show;

import cn.hshb.web.common.exception.HshbException;

import com.huatek.ddhb.manage.news.entity.NewsBean;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.hbwebsite.house.service.HouseSecondServiceImpl;
import com.huatek.hbwebsite.mailer.EmailAbstract;
import com.huatek.hbwebsite.mailer.EmailServiceImpl;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.news.entity.NewsNotice;
import com.huatek.hbwebsite.news.service.NewsService;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.mail.service.MailSendService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/news.show" })
public class NewsAction {
	@Autowired
	private NewsService newsService;
	@Autowired
	private EmailAbstract mailUtil;
	@Autowired
	private MailSendService mailSendService;

	private static final Logger LOGGER = Logger.getLogger(NewsAction.class);
	@RequestMapping(params = { "actionMethod=query" })
	public String query(HttpServletRequest request, Model model) {
		List commonList = QueryCondition.getQueryCondition(request, NewsBean.class);
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		model.addAttribute("pageBean", this.newsService.getCutPageBean(cutPageBean, commonList, 0));
		List typeList = this.newsService.getNewsTypeList();
		model.addAttribute("newTypeList", typeList);
		return "news.list";
	}

	@RequestMapping(params = { "actionMethod=noticeNews" })
	public String noticeNews(HttpServletRequest request, Model model) {
		String housetype = request.getParameter("housetype");
		String searchno = request.getParameter("searchno");
		String brokerId = request.getParameter("brokerId");
		String houseId = request.getParameter("houseId");
		model.addAttribute("brokerId", brokerId);
		model.addAttribute("houseId", houseId);
		model.addAttribute("housetype", housetype);
		model.addAttribute("searchno", searchno);
		model.addAttribute("newsNotice", new NewsNotice());
		return "news.notice";
	}

	@RequestMapping(params = { "actionMethod=saveNoticeNews" })
	public String saveNoticeNews(HttpServletRequest request, Model model) throws HshbException {
		final String emailAddress = request.getParameter("emailAddress");
		String verifyCode = request.getParameter("verifyCode");
		boolean flag = this.newsService.isExist(emailAddress);
		boolean emailNoticeFlag = false;
		if (flag) {
			model.addAttribute("exist", "exist");
			return "ddhb.out.error";
		} else if (CommonUtil.isZeroLengthTrimString(verifyCode)) {
			model.addAttribute("verifyCodeNull", "verifyCodeNull");
			return "ddhb.out.error";
		} else {
			boolean verifyCodeResult = false;
			String sessId = request.getSession().getId();
			verifyCodeResult = CaptchaServiceSingleton.getInstance().validateResponseForID(sessId, verifyCode.toLowerCase()).booleanValue();
			if (!verifyCodeResult) {
				model.addAttribute("verifyCodeError", "verifyCodeError");
				return "ddhb.out.error";
			} else {
				PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
				if (memberInfo != null && emailAddress.equals(memberInfo.getEmailAddress()) && memberInfo.getEmailValidationFlag() == 1) {
					emailNoticeFlag = true;
				}

				NewsNotice newsNotice = new NewsNotice();
				newsNotice.setEmailAddress(emailAddress);
				newsNotice.setCreateTime(new Date());
				newsNotice.setDeleteFlag(0);
				newsNotice.setEmailFlag(0);
				this.newsService.save(newsNotice);
				model.addAttribute("saveok", "saveok");
				if (!emailNoticeFlag) {
					model.addAttribute("noValidEmail", "noValidEmail");
					model.addAttribute("email", emailAddress);
//					final String[] emailMessage = new String[1];
					String url = request.getRequestURL().toString();
					final Map<String, Object> mailDataMap = new HashMap<String, Object>();
//					emailMessage[0] = url + "?actionMethod=activeNewsNoticeEmailAddress&id=" + newsNotice1.getId();
					mailDataMap.put("ValidationLink", url + "?actionMethod=activeNewsNoticeEmailAddress&id=" + newsNotice.getId());
					//返回邮件名称、邮件内容、邮件ID的集合
					//Modified by He JianBo at 2015_03_05
					final Map<String, String> emailMap = this.mailSendService.saveMailSendInfo(emailAddress, EmailAbstract.TEMPL_ID_NEWS_NOTICE_VERIFICATION, mailDataMap);
					(new Thread() {
						public void run() {
							String errorMsg = "";
							try {
//								NewsAction.this.sendEmailService.sendNewsNoticeValidEmail(emailMessage, emailAddress);
								// String mailTempl =
								// NewsAction.this.mailSendService.getTemplateContentStr(EmailServiceImpl.TEMPL_ID_NEWS_NOTICE_VERIFICATION);
								// NewsAction.this.mailUtil.sendEmail(mailTempl, mailDataMap, "豪世华邦【邮箱验证】", emailAddress, null, true);
								NewsAction.this.mailUtil.sendHtmlEmail(emailMap.get(MailSendService.KEY_MAIL_RESULT_CONTENT), emailMap.get(MailSendService.KEY_MAIL_RESULT_NAME), emailAddress, null);
							} catch (Exception ex) {
								errorMsg = "新闻订阅校验邮件发送失败！";
								LOGGER.error(ex.getMessage());
								throw new BusinessRuntimeException("验证邮件发送失败！", ex);
							}finally{
								NewsAction.this.mailSendService.saveErrorMsg(Long.valueOf(emailMap.get(MailSendService.KEY_MAIL_RESULT_MAIL_ID)), errorMsg);
							}
						}
					}).start();
				} else {
					newsNotice.setEmailFlag(1);
					this.newsService.merge(newsNotice);
				}
				return "ddhb.out.error";
			}
		}
	}

	@RequestMapping(params = { "actionMethod=activeNewsNoticeEmailAddress" })
	public String activeNewsNoticeEmailAddress(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		this.newsService.acticeEmail(id);
		return "valid.email.success";
	}
}
