package com.huatek.hbwebsite.company.show;

import com.huatek.ddhb.manage.news.entity.NewsBean;
import com.huatek.ddhb.manage.news.entity.NewsType;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.framework.validate.BaseEntityEditor;
import com.huatek.hbwebsite.company.entity.CompanyCustomerService;
import com.huatek.hbwebsite.company.entity.CompanyCustomerServiceType;
import com.huatek.hbwebsite.company.entity.CompanyIntroduce;
import com.huatek.hbwebsite.company.entity.Contact;
import com.huatek.hbwebsite.company.entity.ShamInfo;
import com.huatek.hbwebsite.company.service.CompanyService;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.news.service.NewsService;
import com.huatek.hbwebsite.recruit.entity.RecruitCandidate;
import com.huatek.hbwebsite.recruit.entity.RecruitPosition;
import com.huatek.hbwebsite.recruit.entity.RecruitPositionType;
import com.huatek.hbwebsite.recruit.service.RecruitService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.Parameter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping({ "/company.show" })
public class CompanyAction {
	@Autowired
	CompanyService companyService;
	@Autowired
	NewsService newsService;
	@Autowired
	RecruitService recruitService;
	private static final Logger LOGGER = Logger.getLogger(CompanyAction.class);
	String pathUrl = "";
	private static final Parameter PARAM = Parameter.getInstance();
	private static final String RESUME_LOCATION_PATH;

	static {
		if (PARAM == null) {
			throw new RuntimeException("can not init system properties util.");
		} else {
			RESUME_LOCATION_PATH = PARAM.getProperty("resumeLocationUrl");
		}
	}

	@RequestMapping(params = { "actionMethod=companyinfo" })
	public String showCompanyInfo(Model model) {
		CompanyIntroduce company = this.companyService.getCompanyInfo();
		model.addAttribute("company", company);
		model.addAttribute("flag", Integer.valueOf(1));
		return "company.info.show";
	}

	@RequestMapping(params = { "actionMethod=showcontact" })
	public String showContact(Model model) {
		Contact contact = this.companyService.getContact();
		model.addAttribute("contact", contact);
		model.addAttribute("flag", Integer.valueOf(4));
		return "company.contact.show";
	}

	@RequestMapping(params = { "actionMethod=showservice" })
	public String showService(Model model) {
		List typeList = this.companyService.getServiceType();
		model.addAttribute("typeList", typeList);
		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		model.addAttribute("platMemberInfo", platMemberInfo);
		model.addAttribute("flag", Integer.valueOf(3));
		return "company.service.show";
	}

	@RequestMapping(params = { "actionMethod=shownews" })
	public String showNews(Model model, HttpServletRequest request) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		model.addAttribute("flag", Integer.valueOf(2));
		ArrayList commonList = new ArrayList();
		model.addAttribute("pageBean", this.newsService.getCutPageBean(cutPageBean, commonList, 0));
		model.addAttribute("typeList", this.newsService.getNewsTypeList());
		return "company.news.show";
	}

	@RequestMapping(params = { "actionMethod=showNewsTypeList" })
	public String showNewsTypeList(Model model, HttpServletRequest request) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(10);
		ArrayList commonList = new ArrayList();
		model.addAttribute("pageBean", this.newsService.getCutPageBean(cutPageBean, commonList,
				Integer.valueOf(request.getParameter("typeId")).intValue()));
		model.addAttribute("typeId", request.getParameter("typeId"));
		model.addAttribute("flag", Integer.valueOf(2));
		NewsType newsType = this.newsService.getNewsTypeDetail(Long.parseLong(request.getParameter("typeId")));
		model.addAttribute("newsType", newsType);
		return "company.news.showNewsTypeList";
	}

	@RequestMapping(params = { "actionMethod=showNewsTypeListCutPage" })
	public String showNewsTypeListCutPage(Model model, HttpServletRequest request) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(10);
		ArrayList commonList = new ArrayList();
		model.addAttribute("pageBean", this.newsService.getCutPageBean(cutPageBean, commonList,
				Integer.valueOf(request.getParameter("typeId")).intValue()));
		model.addAttribute("typeId", request.getParameter("typeId"));
		model.addAttribute("flag", Integer.valueOf(2));
		NewsType newsType = this.newsService.getNewsTypeDetail(Long.parseLong(request.getParameter("typeId")));
		model.addAttribute("newsType", newsType);
		return "company.news.showNewsTypeListCutPage";
	}

	@RequestMapping(params = { "actionMethod=showNewsDetail" })
	public String showNewsDetail(Model model, HttpServletRequest request) {
		String id = request.getParameter("news_id");
		NewsBean newsBean = this.newsService.getNewsDetail(Long.parseLong(id));
		if (newsBean == null) {
			return "redirect:company.show?actionMethod=shownews";
		} else {
			newsBean.setBrowsed(newsBean.getBrowsed() + 1);
			this.newsService.update(newsBean);
			model.addAttribute("news", newsBean);
			model.addAttribute("flag", Integer.valueOf(2));
			return "company.news.showNewsDetail";
		}
	}

	@RequestMapping(params = { "actionMethod=showinvite" })
	public String showInvite(Model model, HttpServletRequest request) {
		CutPageBean cutpageBean = QueryCondition.getQueryPageBean(request);
		cutpageBean.setPageSize(10);
		List recruitPositionTypes = this.recruitService.getAllRecruitPositionType();
		CutPageBean pageBean = this.recruitService.getRecruitPositionByType(cutpageBean,
				((RecruitPositionType) recruitPositionTypes.get(0)).getId());
		model.addAttribute("pageName", "list");
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("positionType", recruitPositionTypes);
		model.addAttribute("flag", Integer.valueOf(5));
		return "company.invite.show";
	}

	@RequestMapping(params = { "actionMethod=cutPostype" })
	public String cutPositiontype(@RequestParam("type") Long type, Model model, HttpServletRequest request) {
		CutPageBean cutpageBean = QueryCondition.getQueryPageBean(request);
		cutpageBean.setPageSize(10);
		CutPageBean pageBean = this.recruitService.getRecruitPositionByType(cutpageBean, type);
		model.addAttribute("pageBean", pageBean);
		return "company.invite.show.list";
	}

	@RequestMapping(params = { "actionMethod=positionDetail" })
	public String positionDetail(@RequestParam("positionId") Long positionId, Model model, HttpServletRequest request) {
		RecruitPosition recruitPosition = this.recruitService.getPositionDetailByPositionId(positionId);
		String isDoString = request.getParameter("isDo");
		int year = Calendar.getInstance().get(1);
		model.addAttribute("pageName", "Detail");
		model.addAttribute("position", recruitPosition);
		model.addAttribute("flag", "5");
		model.addAttribute("backType", Integer.valueOf(1));
		model.addAttribute("currentYear", Integer.valueOf(year));
		model.addAttribute("isDo", isDoString);
		return "company.invite.show";
	}

	@RequestMapping(params = { "actionMethod=dealResume" }, method = { RequestMethod.POST })
	public void dealWithResume(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException,
			IOException {
		String status = "{\"result\":\"error\"}";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest out = (MultipartHttpServletRequest) request;
			Iterator e = out.getFileNames();

			while (e.hasNext()) {
				MultipartFile file = out.getFile((String) e.next());
				if (file != null) {
					String uuid = UUID.randomUUID().toString();
					String fileName = file.getOriginalFilename();
					String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					File resumePath = new File(RESUME_LOCATION_PATH);
					if (!resumePath.exists()) {
						resumePath.mkdirs();
					}

					File localFile = new File(RESUME_LOCATION_PATH + uuid + "." + fileEnd);
					file.transferTo(localFile);
					this.pathUrl = "/resumes/" + uuid + "." + fileEnd;
					status = "{\"result\":\"success\"}";
				}
			}
		}

		PrintWriter out1 = null;

		try {
			out1 = response.getWriter();
			out1.write(status);
			out1.flush();
		} catch (IOException var15) {
			LOGGER.error(var15.getMessage());
		} finally {
			if (out1 != null) {
				out1.close();
			}

		}

	}

	@RequestMapping(params = { "actionMethod=saveResume" }, method = { RequestMethod.POST })
	public String saveResume(HttpServletRequest request, Model model, @ModelAttribute RecruitCandidate recruitCandidate,
			BindingResult result) {
		long positionId = Long.parseLong(request.getParameter("positionId"));
		if (this.recruitService.getIsExist(positionId, recruitCandidate.getName(), recruitCandidate.getTelephone())) {
			this.validateResume(recruitCandidate, result, request);
			if (result.hasErrors()) {
				model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "recruitCandidateFrom"));
				return "ddhb.out.error";
			} else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = recruitCandidate.getYear() + "-" + recruitCandidate.getMonth() + "-"
						+ recruitCandidate.getDay();

				try {
					Date e = format.parse(dateStr);
					recruitCandidate.setBirthday(e);
				} catch (ParseException var10) {
					LOGGER.error(var10.getMessage());
				}

				recruitCandidate
						.setAppliedPosition(this.recruitService.getPositionDetailByPositionId(Long.valueOf(positionId)));
				recruitCandidate.setResume(this.pathUrl);
				recruitCandidate.setDeleteFlag(Integer.valueOf(0));
				recruitCandidate.setApplyTime(new Date());
				this.recruitService.save(recruitCandidate);
				this.pathUrl = "";
//				model.addAttribute("saveokMore", "saveokMore");
				model.addAttribute("recruitCandidate",recruitCandidate);
				model.addAttribute("submitResume", "submitResume");
				return "ddhb.out.error";
			}
		} else {
			model.addAttribute("savefalse", "savefalse");
			return "ddhb.out.error";
		}
	}

	private void validateResume(RecruitCandidate recruitCandidate, Errors errors, HttpServletRequest request) {
		if (CommonUtil.isZeroLengthTrimString(recruitCandidate.getName())) {
			errors.reject("姓名是必填项");
		} else if (recruitCandidate.getName().trim().toString().getBytes(Charset.forName("GBK")).length > 50) {
			errors.reject("姓名的长度不能超过25个汉字");
		}

		if (CommonUtil.isZeroLengthTrimString(recruitCandidate.getYear())
				|| CommonUtil.isZeroLengthTrimString(recruitCandidate.getMonth())
				|| CommonUtil.isZeroLengthTrimString(recruitCandidate.getDay())) {
			errors.reject("出生日期是必填项");
		}

		if (CommonUtil.isZeroLengthTrimString(recruitCandidate.getYearworking())) {
			errors.reject("工作年限是必填项");
		}

		if (CommonUtil.isZeroLengthTrimString(recruitCandidate.getDegree())) {
			errors.reject("学历是必填项");
		}

		if (CommonUtil.isZeroLengthTrimString(recruitCandidate.getTelephone())) {
			errors.reject("电话号码是必填项");
		} else if (!Util.getMatchResult(recruitCandidate.getTelephone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或1xxxxxxxxxx");
		}

		if (CommonUtil.isZeroLengthTrimString(recruitCandidate.getEmail())) {
			errors.reject("邮箱是必填项");
		} else if (!Util.getMatchResult(recruitCandidate.getEmail(),
				"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.reject("邮箱格式错误");
		} else if (recruitCandidate.getEmail().trim().length() > 50) {
			errors.reject("邮箱的长度不能超过50个字符");
		}

	}

	@RequestMapping(params = { "actionMethod=websitemap" })
	public String websiteMap(Model model) {
		model.addAttribute("flag", Integer.valueOf(6));
		return "company.websitemap.show";
	}

	@RequestMapping(params = { "actionMethod=subservice" })
	public String customerServiceSub(@ModelAttribute("shamInfo") ShamInfo shamInfo, BindingResult result, Model model,
			HttpServletRequest request) {
		this.validate(shamInfo, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "shamInfoFrom"));
			return "ddhb.out.error";
		} else {
			shamInfo.setDeleteFlag(0);
			shamInfo.setCreateDate(new Date());
			shamInfo.setStatu(1);
			this.companyService.save(shamInfo);
			model.addAttribute("issave", "success");
			return "ddhb.out.error";
		}
	}

	private void validate(ShamInfo shamInfo, Errors errors, HttpServletRequest request) {
		if (CommonUtil.isZeroLengthTrimString(shamInfo.getTelephone())) {
			errors.reject("电话为必填项");
		} else if (!Util.getMatchResult(shamInfo.getTelephone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或151xxxxxxxx");
		}

		if (!CommonUtil.isZeroLengthTrimString(shamInfo.getEmailAddr())) {
			if (!Util.getMatchResult(shamInfo.getEmailAddr(),
					"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
				errors.reject("邮箱格式有误");
			}

			if (shamInfo.getEmailAddr().length() > 50) {
				errors.reject("邮箱长度不能超过50个字符");
			}
		}

		if (CommonUtil.isZeroLengthTrimString(shamInfo.getContent().trim())) {
			errors.reject("内容为必填项");
		} else if (shamInfo.getContent().trim().toString().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("内容的字段不能超过400个字符");
		}

		if (this.companyService.theRecordIsExit(shamInfo.getReportName(), shamInfo.getTelephone(), shamInfo.getContent())) {
			errors.reject("不能举报两次");
		}

		if (CommonUtil.isZeroLengthTrimString(shamInfo.getVerifyCode())) {
			errors.reject("验证码为必填项");
		} else {
			boolean flag = Boolean.FALSE.booleanValue();
			String sessId = request.getSession().getId();
			flag = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(sessId, shamInfo.getVerifyCode().toLowerCase()).booleanValue();
			if (!flag) {
				errors.reject("验证码错误");
			}
		}
	}

	@RequestMapping(params = { "actionMethod=service" })
	public String customerServiceSub(@ModelAttribute("customerService") CompanyCustomerService customerSer,
			BindingResult result, Model model, HttpServletRequest request) {
		Long serviceTypeId = Long.valueOf(request.getParameter("serviceTypeId"));
		if (serviceTypeId != null) {
			customerSer.setServiceType((CompanyCustomerServiceType) this.companyService.getObjectById(
					CompanyCustomerServiceType.class, serviceTypeId));
		}

		this.validateService(customerSer, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "customerSerFrom"));
			return "ddhb.out.error";
		} else {
			if (request.getParameter("serviceTypeid") != null) {
				customerSer.setServiceType((CompanyCustomerServiceType) this.companyService.getObjectById(
						CompanyCustomerServiceType.class, Long.valueOf(request.getParameter("serviceTypeid"))));
			}

			PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (accountBean != null) {
				customerSer.setMember(accountBean);
			}

			customerSer.setDeleteFlag(0);
			customerSer.setCreateTime(new Date());
			customerSer.setStatu(1);
			this.companyService.save(customerSer);
			model.addAttribute("issave", "success");
			return "ddhb.out.error";
		}
	}

	private void validateService(CompanyCustomerService customerSer, Errors errors, HttpServletRequest request) {
		if (CommonUtil.isZeroLengthTrimString(customerSer.getTelephoneNo())) {
			errors.reject("电话为必填项");
		} else if (!Util.getMatchResult(customerSer.getTelephoneNo(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或151xxxxxxxx");
		}

		if (!CommonUtil.isZeroLengthTrimString(customerSer.getEmailAddress())) {
			if (!Util.getMatchResult(customerSer.getEmailAddress(),
					"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
				errors.reject("邮箱格式有误");
			} else if (customerSer.getEmailAddress().length() > 50) {
				errors.reject("邮件长度不能超过50个字符");
			}
		}

		if (CommonUtil.isZeroLengthTrimString(customerSer.getContent().trim())) {
			errors.reject("内容为必填项");
		} else if (customerSer.getContent().trim().toString().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("内容的字段不能超过400个字符");
		}

		if (this.companyService.theRecordIsExit(customerSer.getUsername(), customerSer.getTelephoneNo(),
				customerSer.getContent())) {
			errors.rejectValue("", "shamInfo.twice");
		}

		if (CommonUtil.isZeroLengthTrimString(customerSer.getVerifyCode())) {
			errors.reject("验证码为必填项");
		} else {
			boolean flag = Boolean.FALSE.booleanValue();
			String sessId = request.getSession().getId();
			flag = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(sessId, customerSer.getVerifyCode().toLowerCase()).booleanValue();
			if (!flag) {
				errors.reject("验证码错误");
			}
		}
	}

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(CompanyCustomerServiceType.class, "serviceType", new BaseEntityEditor(
				CompanyCustomerServiceType.class));
	}
}
