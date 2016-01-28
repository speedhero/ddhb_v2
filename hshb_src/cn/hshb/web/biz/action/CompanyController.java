/**
 * 
 */
package cn.hshb.web.biz.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.github.pagehelper.PageInfo;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.Parameter;

import cn.hshb.web.biz.mybatis.model.CompanyContactus;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerService;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceType;
import cn.hshb.web.biz.mybatis.model.CompanyIntroduce;
import cn.hshb.web.biz.mybatis.model.HouseArtificialReport;
import cn.hshb.web.biz.mybatis.model.NewsContentWithBLOBs;
import cn.hshb.web.biz.mybatis.model.NewsType;
import cn.hshb.web.biz.mybatis.model.RecruitCandidate;
import cn.hshb.web.biz.mybatis.model.RecruitPosition;
import cn.hshb.web.biz.mybatis.model.RecruitPositionType;
import cn.hshb.web.biz.service.CompanyService;
import cn.hshb.web.biz.service.NewsService;
import cn.hshb.web.biz.service.RecruitService;
import cn.hshb.web.biz.util.BaseEntityEditor;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Controller
@RequestMapping({ "/company" })
public class CompanyController {
	private static final Log log = LogFactory.getLog(CompanyController.class);
	private static final String PATH = "company";
	private static final int NEWS_COUNT = 10;	//每页新闻显示的数量
	private static final int EACH_TYPE_OF_NEWS_COUNT = 5;	//每种新闻在同一页面显示的数量
	private static final int SHOW_INVITE_LIST_SIZE = 10;	//招聘列表显示
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

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private RecruitService recruitService;
	
	@RequestMapping(value = { "/companyinfo" })
	public String showCompanyInfo(Model model) {
		CompanyIntroduce company = companyService.getCompanyInfo();
		model.addAttribute("company", company);
		model.addAttribute("flag", 1);
		return "company.info.show";
	}
	
	@RequestMapping(value = { "/showcontact" })
	public String showContact(Model model) {
		CompanyContactus contact = companyService.getContact();
		model.addAttribute("contact", contact);
		model.addAttribute("flag", 4);
		return "company.contact.show";
	}

	@RequestMapping(value = { "/showservice" })
	public String showService(Model model) {
		List<CompanyCustomerServiceType> typeList = companyService.getServiceType();
		model.addAttribute("typeList", typeList);
		
		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		model.addAttribute("platMemberInfo", platMemberInfo);
		model.addAttribute("flag", 3);
		return "company.service.show";
	}
	
	@RequestMapping(value = { "/shownews" })
	public String showNews(Model model, HttpServletRequest request) {
//		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		model.addAttribute("flag", 2);
		//typeList,共有两个值1:公司新闻,2:行业新闻
		List<NewsType> newsTypeList = newsService.getNewsTypeList();
		model.addAttribute("typeList", newsTypeList);
		model.addAttribute("newsContentList", newsService.getNewsContent(EACH_TYPE_OF_NEWS_COUNT, newsTypeList));
		return "company.news.show";
	}

	/**
	 * 新闻动态列表页面
	 * @param typeId	新闻类别
	 * @param model		
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/showNewsTypeList/{typeId}" })
	public String showNewsTypeList(@PathVariable Integer typeId ,Model model, HttpServletRequest request) {
		return showNewsTypeList(typeId, "n1", model, request);
	}
	/**
	 * 新闻动态列表页面
	 * @param typeId		新闻类别
	 * @param pageNumStr	新闻页码
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/showNewsTypeList/{typeId}/{pageNumStr}"})
	public String showNewsTypeList(@PathVariable Integer typeId, @PathVariable String pageNumStr, Model model, HttpServletRequest request){
		List<NewsContentWithBLOBs> newsContentWithBLOBsList = newsService.getNewsContent(NEWS_COUNT, pageNumStr, typeId); 
		PageInfo<NewsContentWithBLOBs> pageInfo = new PageInfo<NewsContentWithBLOBs>(newsContentWithBLOBsList);
		model.addAttribute("pageBean", pageInfo);
		model.addAttribute("newsContentList", newsContentWithBLOBsList);
		model.addAttribute("typeId", typeId);
		model.addAttribute("flag", 2);
		NewsType newsType = newsService.getNewsTypeDetail(typeId);
		model.addAttribute("newsType", newsType);
		
		String basePath = PATH + "/showNewsTypeList/" + typeId ;
		model.addAttribute("basePath", basePath);
		return "company.news.showNewsTypeList";
	}
	
	@RequestMapping(params = { "/newsTypeListCutPage" })
	public String showNewsTypeListCutPage(Model model, HttpServletRequest request) {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(10);
		model.addAttribute("pageBean", newsService.getNewsContent(cutPageBean.getPageSize(), 
				cutPageBean.getCurrentPage(), 
				Integer.valueOf(request.getParameter("typeId"))));
		model.addAttribute("typeId", request.getParameter("typeId"));
		model.addAttribute("flag", 2);
		NewsType newsType = newsService.getNewsTypeDetail(Integer.parseInt(request.getParameter("typeId")));
		model.addAttribute("newsType", newsType);
		return "company.news.newsTypeListCutPage";
	}

	@RequestMapping(value = { "/showNewsDetail/{newsId}" })
	public String showNewsDetail(@PathVariable Integer newsId, Model model, HttpServletRequest request) {
		NewsContentWithBLOBs newsBean = newsService.getNewsDetail(newsId);
		if (newsBean == null) {
			return showNews(model, request);
		} else {
			newsBean.setBrowsed(newsBean.getBrowsed() + 1);
			newsService.update(newsBean);
			model.addAttribute("news", newsBean);
			model.addAttribute("flag", Integer.valueOf(2));
			NewsType newsType = newsService.getNewsTypeDetail(newsBean.getNewsType());
			model.addAttribute("newsType", newsType);
			return "company.news.showNewsDetail";
		}
	}

	/**
	 * 招聘初始页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/showinvite" })
	public String showInvite(Model model, HttpServletRequest request) {
		return showInvite(null, model, request);
	}
	
	/**
	 * 带有招聘类型的招聘地址
	 * @param typeId	招聘类型 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/showinvite/{typeId}"})
	public String showInvite(@PathVariable Integer typeId, Model model, HttpServletRequest request){
		//默认页码 n1
		return showInvite(typeId, "n1", model, request);
	}
	/**
	 * 带有招聘类型和页码的招聘地址
	 * @param typeId	招聘类型
	 * @param pageNumStr页码 默认n1
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/showinvite/{typeId}/{pageNumStr}"})
	public String showInvite(@PathVariable Integer typeId, @PathVariable String pageNumStr, Model model, HttpServletRequest request){
		List<RecruitPositionType> recruitPositionTypes = recruitService.getAllRecruitPositionType();
		Integer _typeId = typeId == null?recruitPositionTypes.get(0).getId():typeId; 
		List<RecruitPosition> listRP = recruitService.getRecruitPositionByType(
				_typeId
				, SHOW_INVITE_LIST_SIZE, pageNumStr);
		PageInfo<RecruitPosition> pageInfo = new PageInfo<RecruitPosition>(listRP);
		model.addAttribute("pageName", "list");
		model.addAttribute("pageBean", pageInfo);
		model.addAttribute("RecruitPositionList", listRP);
		model.addAttribute("positionType", recruitPositionTypes);
		model.addAttribute("flag", Integer.valueOf(5));
		String basePath = PATH + "/showinvite/" + _typeId ;
		model.addAttribute("basePath", basePath);
		//样式
		model.addAttribute("changeCss", _typeId);
		return "company.invite.show";
	}
	
	/**
	 * 用于各种职位类型之间的切换
	 * @param type
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/cutPostype/{typeId}" })
	public String cutPostype(@PathVariable Integer typeId, Model model, HttpServletRequest request) {
		List<RecruitPosition> list = recruitService.getRecruitPositionByType(typeId, SHOW_INVITE_LIST_SIZE, 1);
		PageInfo<RecruitPosition> pageInfo = new PageInfo<RecruitPosition>(list);
		model.addAttribute("pageBean", pageInfo);
		model.addAttribute("RecruitPositionList", list);
		String basePath = PATH + "/showinvite/" + typeId ;
		model.addAttribute("basePath", basePath);
		return "company.invite.show.list";
	}

	@RequestMapping(value = { "/positionDetail/{positionId}" }) 
	public String positionDetail(@PathVariable Integer positionId, Model model, HttpServletRequest request) {
		return positionDetail(positionId, null, model, request);
	}
	@RequestMapping(value = {"/positionDetail/{positionId}/{isDo}"})
	public String positionDetail(@PathVariable Integer positionId, @PathVariable String isDo, Model model, HttpServletRequest request){
		RecruitPosition recruitPosition = recruitService.getPositionDetailByPositionId(positionId);
		Integer year = Calendar.getInstance().get(Calendar.YEAR); 
		model.addAttribute("pageName", "Detail");
		model.addAttribute("position", recruitPosition);
		model.addAttribute("flag", "5");
		model.addAttribute("backType", 1);
		model.addAttribute("currentYear", year);
		model.addAttribute("isDo", isDo);
		return "company.invite.show";
	}
	@RequestMapping(value = { "/dealResume" }, method = { RequestMethod.POST })
	public void dealWithResume(HttpServletRequest request, HttpServletResponse response) 
			throws IllegalStateException, IOException {
		String status = "{\"result\":\"error\"}";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
			Iterator<String> itFileName = multiReq.getFileNames();

			while (itFileName.hasNext()) {
				MultipartFile file = multiReq.getFile((String) itFileName.next());
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

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException ex) {
			log.error(ex);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = { "/saveResume" }, method = { RequestMethod.POST })
	public String saveResume(HttpServletRequest request, Model model, 
			@ModelAttribute RecruitCandidate recruitCandidate,
			BindingResult result) {
		Integer positionId = Integer.parseInt(request.getParameter("positionId"));
		if (recruitService.getIsExist(positionId, recruitCandidate.getName(), recruitCandidate.getTelephone())) {
			validateResume(recruitCandidate, result, request);
			if (result.hasErrors()) {
				model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "recruitCandidateFrom"));
				return "ddhb.out.error";
			} else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = recruitCandidate.getYear() + "-" + recruitCandidate.getMonth() + "-" + recruitCandidate.getDay();
				try {
					recruitCandidate.setBirthday(format.parse(dateStr));
				} catch (ParseException ex) {
					log.error(ex);
				}
				recruitCandidate.setApplyposition(positionId);
				recruitCandidate.setResume(pathUrl);
				recruitCandidate.setDeleteflag(0);
				recruitCandidate.setApplytime(new Date());
				recruitCandidate.setStatus("0");
				recruitService.save(recruitCandidate);
				pathUrl = "";
				model.addAttribute("recruitCandidate",recruitCandidate);
				model.addAttribute("submitResume", "submitResume");
				return "ddhb.out.error";
			}
		} else {
			model.addAttribute("savefalse", "savefalse");
			return "ddhb.out.error";
		}
	}

	@RequestMapping(params = { "/websitemap" })
	public String websiteMap(Model model) {
		model.addAttribute("flag", 6);
		return "company.websitemap.show";
	}

	/**
	 * 虚假举报
	 * @param shamInfo
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/subservice" })
	public String customerServiceSub(@ModelAttribute("shamInfo") HouseArtificialReport shamInfo, BindingResult result, Model model, HttpServletRequest request) {
		validate(shamInfo, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "shamInfoFrom"));
			return "ddhb.out.error";
		} else {
			shamInfo.setDeleteflag(0);
			shamInfo.setCreateTime(new Date());
			shamInfo.setStatus(1);
			companyService.save(shamInfo);
			model.addAttribute("issave", "success");
			return "ddhb.out.error";
		}
	}

	@RequestMapping(value = { "/service" })
	public String customerServiceSub(
			@ModelAttribute("customerService") CompanyCustomerService customerSer,
			BindingResult result, Model model, 
			HttpServletRequest request) {
		Integer serviceTypeId = Integer.valueOf(request.getParameter("serviceTypeId"));
		if (serviceTypeId != null) {
			customerSer.setServiceType(serviceTypeId);
		}

		this.validateService(customerSer, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "customerSerFrom"));
			return "ddhb.out.error";
		} else {
			if (request.getParameter("serviceTypeid") != null) {
				customerSer.setServiceType(Integer.valueOf(request.getParameter("serviceTypeid")));
			}

			PlatMemberInfo member = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
			if (member != null) {
				customerSer.setUserId(member.getId().intValue());
			}

			customerSer.setDeleteflag(0);
			customerSer.setCreateTime(new Date());
			customerSer.setStatus(1);
			companyService.save(customerSer);
			model.addAttribute("issave", "success");
			return "ddhb.out.error";
		}
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(
				CompanyCustomerServiceType.class, 
				"serviceType", 
				new BaseEntityEditor(CompanyCustomerServiceType.class));	//TODO: 这里的BaseEntityEditor还需要修改
	}
	
	private void validateResume(RecruitCandidate recruitCandidate, Errors errors, HttpServletRequest request) {
		if (StringUtil.isEmpty(recruitCandidate.getName())) {
			errors.reject("姓名是必填项");
		} else if (recruitCandidate.getName().trim().toString().getBytes(Charset.forName("GBK")).length > 50) {
			errors.reject("姓名的长度不能超过25个汉字");
		}

		if (StringUtil.isEmpty(recruitCandidate.getYear())
				|| StringUtil.isEmpty(recruitCandidate.getMonth())
				|| StringUtil.isEmpty(recruitCandidate.getDay())) {
			errors.reject("出生日期是必填项");
		}

		if (StringUtil.isEmpty(recruitCandidate.getYearsofworking())) {
			errors.reject("工作年限是必填项");
		}

		if (StringUtil.isEmpty(recruitCandidate.getDegree())) {
			errors.reject("学历是必填项");
		}

		validatePhone(recruitCandidate.getTelephone(), errors);
		validateEmail(recruitCandidate.getEmail(), errors);
	}


	private void validate(HouseArtificialReport shamInfo, Errors errors, HttpServletRequest request) {
		validateVerifyCode(shamInfo.getVerifyCode(), request.getSession().getId(), errors);
		validatePhone(shamInfo.getTelephone(), errors);
		validateEmail(shamInfo.getEmailAddress(), errors);

		if (StringUtil.isEmpty(shamInfo.getContent().trim())) {
			errors.reject("内容为必填项");
		} else if (shamInfo.getContent().trim().toString().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("内容的字段不能超过400个字符");
		}

		if (companyService.theRecordIsExit(shamInfo.getName(), shamInfo.getTelephone(), shamInfo.getContent())) {
			errors.reject("不能举报两次");
		}
	}

	
	private void validateService(CompanyCustomerService customerSer, Errors errors, HttpServletRequest request) {
		validateVerifyCode(customerSer.getVerifyCode(), request.getSession().getId(), errors);
		validatePhone(customerSer.getTelephoneNo(), errors);
		if(StringUtil.isNotEmpty(customerSer.getEmailAddress()))
			validateEmail(customerSer.getEmailAddress(), errors);

		if (StringUtil.isEmpty(customerSer.getContent().trim())) {
			errors.reject("内容为必填项");
		} else if (customerSer.getContent().trim().toString().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("内容的字段不能超过400个字符");
		}

		if (companyService.theRecordIsExit(customerSer.getUsername(), customerSer.getTelephoneNo(),
				customerSer.getContent())) {
			errors.rejectValue("", "shamInfo.twice");
		}
	}
	
	/**
	 * 校验图片校验码
	 * @param verifyCode
	 * @param errors
	 * @return
	 */
	private Boolean validateVerifyCode(String verifyCode, String sessId, Errors errors){
		if (StringUtil.isEmpty(verifyCode)) {
			errors.reject("验证码为必填项");
			return false;
		} else {
			Boolean flag = CaptchaServiceSingleton.getInstance().validateResponseForID(sessId, verifyCode.toLowerCase());
			if (!flag) {
				errors.reject("验证码错误");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验邮件地址格式 
	 * @param email
	 * @param errors
	 * @return
	 */
	private Boolean validateEmail(String email, Errors errors){
		if (StringUtil.isEmpty(email)) {
			errors.reject("邮箱是必填项");
			return false;
		} else if (!Util.getMatchResult(email,
				"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.reject("邮箱格式错误");
			return false;
		} else if (email.trim().length() > 50) {
			errors.reject("邮箱的长度不能超过50个字符");
			return false;
		}
		return true;
	}
	
	/**
	 * 校验电话号码
	 * @param phoneNum
	 * @param errors
	 * @return
	 */
	private Boolean validatePhone(String phoneNum, Errors errors){
		if (StringUtil.isEmpty(phoneNum)) {
			errors.reject("电话为必填项");
			return false;
		} else if (!Util.getMatchResult(phoneNum, "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或151xxxxxxxx");
			return false;
		}
		return true;
	}
}
