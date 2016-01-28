package com.huatek.hbwebsite.show.memberfunc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.huatek.framework.forbiddentel.service.ForbiddenTelService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.GsonUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.contract.entity.ContractEntity;
import com.huatek.hbwebsite.contract.entity.ContractFlowEntity;
import com.huatek.hbwebsite.member.entity.IntegralHistory;
import com.huatek.hbwebsite.member.entity.IntegralTable;
import com.huatek.hbwebsite.member.entity.IntegrateRule;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.HouseReduceNoticeService;
import com.huatek.hbwebsite.member.service.MemberBespeakService;
import com.huatek.hbwebsite.member.service.MemberBrowseHistoryService;
import com.huatek.hbwebsite.member.service.MemberQuestionService;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import com.huatek.hbwebsite.member.service.ScoreManagerService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;

@Controller
@RequestMapping({ "/usercenter.do" })
public class MemberFunctionAction {
	private static final int BUFSIZE_10K = 10240;
	@Autowired
	private MemberBespeakService memberBespeakService;
	@Autowired
	private PlatCollectionService platCollectionService;
	@Autowired
	private MemberBrowseHistoryService memberBrowseHistoryService;
	@Autowired
	private MemberQuestionService memberQuestionService;
	@Autowired
	private ScoreManagerService scoreManagerService;
	@Autowired
	private HouseReduceNoticeService houseReduceNoticeService;
	@Autowired
	private PlatMemberInfoService platMemberInfoService;
	@Autowired
	private ForbiddenTelService forbiddenTelService;
	private static final int validatePhone = 1;
	private static final int noValidatePhone = 0;
	private static final Logger LOGGER = Logger.getLogger(MemberFunctionAction.class);
	private static HashMap<String, String> keyMessage = new HashMap<String, String>();

	@RequestMapping(params = { "actionMethod=appointment" })
	public String initRecruitInformation(Model model, HttpServletRequest request,
			@RequestParam("isCutPage") boolean isCutPage) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		CutPageBean pageBean = QueryCondition.getQueryPageBean(request);
		CutPageBean pages = this.memberBespeakService.getAppointmentHouse(pageBean, platMemberInfo.getId());
		model.addAttribute("pageName", "appointment");
		model.addAttribute("pageBean", pages);
		model.addAttribute("backType", Integer.valueOf(1));
		model.addAttribute("secondCount", this.memberBespeakService.getSecondHouseCount(platMemberInfo.getId()));
		model.addAttribute("rentCount", this.memberBespeakService.getRentHouseCount(platMemberInfo.getId()));
		model.addAttribute("flag", Integer.valueOf(1));
		return isCutPage ? "member.userinformation.appointment.list" : "member.userinformation.show";
	}

	@RequestMapping(params = { "actionMethod=browseHistory" })
	public String getBrowseHistory(Model model, HttpServletRequest request, @RequestParam("isCutPage") boolean isCutPage) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		CutPageBean cutpageBean = QueryCondition.getQueryPageBean(request);
		cutpageBean.setPageSize(5);
		model.addAttribute("backType", Integer.valueOf(1));
		String houseType = request.getParameter("houseType");
		model.addAttribute("houseType", houseType);
		CutPageBean pages = this.memberBrowseHistoryService.getBrowseHistory(cutpageBean, platMemberInfo.getId(), houseType);
		model.addAttribute("secondCount", 0);
		model.addAttribute("rentCount", 0);
		model.addAttribute("commCount", 0);
		List<Object> objList = this.memberBrowseHistoryService.getCountByObjectType(platMemberInfo.getId());
		Iterator<Object> itObj = objList.iterator();

		while (itObj.hasNext()) {
			Object obj = itObj.next();
			Object[] objs = (Object[]) obj;
			if (objs.length > 0) {
				Integer aInteger = (Integer) objs[0];
				Long bLong = (Long) objs[1];
				if (aInteger.intValue() == 0) {
					model.addAttribute("secondCount", bLong);
				} else if (aInteger.intValue() == 1) {
					model.addAttribute("rentCount", bLong);
				} else if (aInteger.intValue() == 2) {
					model.addAttribute("commCount", bLong);
				}
			}
		}

		model.addAttribute("pageName", "browseHistory");
		model.addAttribute("pageBean", pages);
		model.addAttribute("flag", Integer.valueOf(6));
		if (isCutPage) {
			return "member.userinformation.browseHistory.list";
		} else {
			return "member.userinformation.show";
		}
	}

	@RequestMapping(params = { "actionMethod=priceNotice" })
	public String priceNotice(Model model, HttpServletRequest request, @RequestParam("isCutPage") boolean isCutPage) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		CutPageBean cutpageBean = QueryCondition.getQueryPageBean(request);
		cutpageBean.setPageSize(5);
		CutPageBean cutPageBean = this.houseReduceNoticeService.getHouseReduceNotice(cutpageBean, platMemberInfo.getId());
		int noticeCount = this.houseReduceNoticeService.getNoticeCount(platMemberInfo.getId());
		model.addAttribute("pageName", "priceNotice");
		model.addAttribute("noticeCount", Integer.valueOf(noticeCount));
		model.addAttribute("pageBean", cutPageBean);
		model.addAttribute("flag", 4);
		model.addAttribute("backType", 1);
		return isCutPage ? "member.userinformation.priceNotice.list" : "member.userinformation.show";
	}

	@RequestMapping(params = { "actionMethod=cancelPriceNotice" }, method = { RequestMethod.POST })
	public void cancelPriceNotice(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mid") String mid) {
		String status = "{\"result\":\"error\"}";
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		boolean flag = this.houseReduceNoticeService.deletePriceNotice(mid, platMemberInfo.getId());
		if (flag) {
			status = "{\"result\":\"success\"}";
		} else {
			status = "{\"result\":\"error\"}";
		}
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(params = { "actionMethod=cancelAppointment" }, method = { RequestMethod.POST })
	public void cancelAppointment(HttpServletRequest request, HttpServletResponse response, @RequestParam("mid") Long mid) {
		String status = "{\"result\":\"error\"}";
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		boolean flag = this.memberBespeakService.deleteAppointment(mid, platMemberInfo.getId());
		if (flag) {
			status = "{\"result\":\"success\"}";
		} else {
			status = "{\"result\":\"error\"}";
		}

		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException var12) {
			LOGGER.error(var12.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}

		}

	}

	@RequestMapping(params = { "actionMethod=delCollect" }, method = { RequestMethod.POST })
	public void delCollect(HttpServletRequest request, HttpServletResponse response, @RequestParam("mid") Long mid) {
		String status = "{\"result\":\"error\"}";
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		boolean flag = this.memberBespeakService.deleteMemberCollect(mid, platMemberInfo.getId());
		if (flag) {
			status = "{\"result\":\"success\"}";
		} else {
			status = "{\"result\":\"error\"}";
		}

		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException var12) {
			LOGGER.error(var12.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}

		}

	}

	@RequestMapping(params = { "actionMethod=collectment" })
	public String recruitCollectInformation(Model model, HttpServletRequest request,
			@RequestParam("isCutPage") boolean isCutPage) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		CutPageBean pageBean = QueryCondition.getQueryPageBean(request);
		CutPageBean pages = this.platCollectionService.getCollectHouse(pageBean, platMemberInfo.getId());
		model.addAttribute("pageName", "collectment");
		model.addAttribute("pageBean", pages);
		model.addAttribute("backType", Integer.valueOf(1));
		model.addAttribute("secondCount", this.platCollectionService.getCountByHouseType(platMemberInfo.getId(), 0));
		model.addAttribute("rentCount", this.platCollectionService.getCountByHouseType(platMemberInfo.getId(), 1));
		model.addAttribute("communityCount", this.platCollectionService.getCountByHouseType(platMemberInfo.getId(), 2));
		model.addAttribute("flag", Integer.valueOf(3));
		return isCutPage ? "member.userinformation.collectment.list" : "member.userinformation.show";
	}

	@RequestMapping(params = { "actionMethod=scoreManager" })
	public String scoreManager(Model model, HttpServletRequest request, @RequestParam("isCutPage") boolean isCutPage) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		if (platMemberInfo == null) {
			return "redirect:login.show?actionMethod=loginCheck";
		} else {
			List<IntegralTable> tableList = this.scoreManagerService.getIntegralTableByUserId(platMemberInfo.getId());
			if (tableList != null) {
				model.addAttribute("myScore", tableList.get(0));
			}

			CutPageBean pageBean = QueryCondition.getQueryPageBean(request);
			pageBean.setPageSize(5);
			model.addAttribute("pageBean", this.scoreManagerService.getAllHistory(pageBean, platMemberInfo.getId()));
			model.addAttribute("pageName", "scoreManager");
			model.addAttribute("flag", Integer.valueOf(7));
			model.addAttribute("backType", Integer.valueOf(1));
			return isCutPage ? "member.score.list" : "member.userinformation.show";
		}
	}

	@RequestMapping(params = { "actionMethod=deliverScore" })
	public String deliverScore(Model model, HttpServletRequest request) {
		model.addAttribute("pageName", "scoreDeliver");
		model.addAttribute("flag", Integer.valueOf(7));
		model.addAttribute("backType", Integer.valueOf(1));
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		if (platMemberInfo == null) {
			return "redirect:login.show?actionMethod=loginCheck";
		} else {
			String[] ruls = new String[] { "maxChangeLimit" };
			List<IntegrateRule> integrateRules = this.platMemberInfoService.getAllRulesByRules(ruls);
			if (integrateRules != null && integrateRules.size() != 0) {
				model.addAttribute("maxChangeLimit", Integer.valueOf(((IntegrateRule) integrateRules.get(0)).getRuleValue()));
			}

			List<IntegralTable> tableList = this.scoreManagerService.getIntegralTableByUserId(platMemberInfo.getId());
			if (tableList != null) {
				model.addAttribute("myScore", tableList.get(0));
			}

			model.addAttribute("phoneNumber", platMemberInfo.getMobilephone());
			model.addAttribute("phoneFlag", Integer.valueOf(platMemberInfo.getMobilephoneValidatedFlag()));
			return "member.userinformation.show";
		}
	}

	@RequestMapping(params = { "actionMethod=deliverScoreAdd" })
	public String deliverScoreAdd(HttpServletRequest request, Model model,
			@ModelAttribute IntegralHistory integralHistory, BindingResult result) {
		String userId = request.getParameter("countName");
		String score = request.getParameter("scoreNum");
		String verifyCode = request.getParameter("verifyCode");
		String sessionId = request.getSession().getId();
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		if (platMemberInfo == null) {
			return "redirect:login.show?actionMethod=loginCheck";
		} else {
			List<IntegralTable> integralTableFroms = this.scoreManagerService.getIntegralTableByUserId(platMemberInfo.getId());
			IntegralTable integralTableFrom = (IntegralTable) integralTableFroms.get(0);
			List<PlatMemberInfo> platMemberInfos = this.scoreManagerService.getPlatMemberInfoByAccName(userId);
			String message = (String) keyMessage.get(sessionId + "|" + platMemberInfo.getMobilephone());
			if (message == null) {
				result.reject("验证码获取失败，请重试！");
				model.addAttribute("_out_data", Util.getErrorMsgInfo(result, ""));
				return "ddhb.out.error";
			} else if (!message.equals(verifyCode)) {
				result.reject("验证码错误");
				model.addAttribute("_out_data", Util.getErrorMsgInfo(result, ""));
				return "ddhb.out.error";
			} else {
				this.validate(result, request, platMemberInfos, userId, score, integralTableFrom.getChangedIntegral());
				if (result.hasErrors()) {
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, ""));
					return "ddhb.out.error";
				} else if (userId.trim().equals(platMemberInfo.getAccName())) {
					result.reject("不能将积分转赠给自己");
					model.addAttribute("_out_data", Util.getErrorMsgInfo(result, ""));
					return "ddhb.out.error";
				} else {
					List<IntegralTable> integralTableTos = this.scoreManagerService.getIntegralTableByUserId(((PlatMemberInfo) platMemberInfos
							.get(0)).getId());
					IntegralTable integralTableTo = (IntegralTable) integralTableTos.get(0);
					String[] ruls = new String[] { "maxReceivedLimit" };
					List<IntegrateRule> integrateRules = this.platMemberInfoService.getAllRulesByRules(ruls);
					if (integrateRules != null && integrateRules.size() != 0) {
						IntegrateRule scoreNum = (IntegrateRule) integrateRules.get(0);
						if (scoreNum.getRuleValue() - integralTableTo.getReceivedCount() <= 0) {
							result.reject("对方转入次数已达到上限，无法转入");
							model.addAttribute("_out_data", Util.getErrorMsgInfo(result, ""));
							return "ddhb.out.error";
						}
					}

					int scoreNum1 = Integer.parseInt(score);
					String[] ruls2 = new String[] { "maxChangeValueLimit" };
					List<IntegrateRule> integrateRules2 = this.platMemberInfoService.getAllRulesByRules(ruls2);
					if (integrateRules2 != null && integrateRules2.size() != 0) {
						IntegrateRule integralHistoryFrom = (IntegrateRule) integrateRules2.get(0);
						if (scoreNum1 > integralHistoryFrom.getRuleValue()) {
							result.reject("超过最大转赠数额");
							model.addAttribute("_out_data", Util.getErrorMsgInfo(result, ""));
							return "ddhb.out.error";
						}
					}

					integralTableFrom.setChangedIntegral(integralTableFrom.getChangedIntegral() - scoreNum1);
					integralTableFrom.setIntegral(integralTableFrom.getIntegral() - scoreNum1);
					integralTableFrom.setChangedCount(integralTableFrom.getChangedCount() + 1);
					this.scoreManagerService.update(integralTableFrom);
					IntegralHistory integralHistoryFrom1 = new IntegralHistory();
					integralHistoryFrom1.setIntegral(scoreNum1);
					integralHistoryFrom1.setPlatMemberInfo(platMemberInfo);
					integralHistoryFrom1.setUser((PlatMemberInfo) platMemberInfos.get(0));
					integralHistoryFrom1.setGettedTime(new Date());
					integralHistoryFrom1.setComment("转出");
					this.scoreManagerService.save(integralHistoryFrom1);
					integralTableTo.setReceivedCount(integralTableTo.getReceivedCount() + 1);
					integralTableTo.setIntegral(integralTableTo.getIntegral() + scoreNum1);
					integralTableTo.setChangedIntegral(integralTableTo.getChangedIntegral() + scoreNum1);
					this.scoreManagerService.update(integralTableTo);
					IntegralHistory integralHistoryTo = new IntegralHistory();
					integralHistoryTo.setIntegral(scoreNum1);
					integralHistoryTo.setPlatMemberInfo((PlatMemberInfo) platMemberInfos.get(0));
					integralHistoryTo.setUser(platMemberInfo);
					integralHistoryTo.setGettedTime(new Date());
					integralHistoryTo.setComment("转入");
					this.scoreManagerService.save(integralHistoryTo);
					model.addAttribute("saveok", "saveok");
					return "ddhb.out.error";
				}
			}
		}
	}

	@RequestMapping(params = { "actionMethod=cutQuestion" })
	public String cutQuestion(HttpServletRequest request) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		CutPageBean cutpageBean = QueryCondition.getQueryPageBean(request);
		cutpageBean.setPageSize(5);
		request.setAttribute("backType", Integer.valueOf(1));
		String type = request.getParameter("type");
		CutPageBean pageBean;
		if (type == null) {
			pageBean = this.memberQuestionService.getQuestionListByType(cutpageBean, platMemberInfo.getId(), 1);
		} else {
			pageBean = this.memberQuestionService.getQuestionListByType(cutpageBean, platMemberInfo.getId(),
					Integer.valueOf(type.trim()).intValue());
		}

		long resovedCount = this.memberQuestionService.getQuestionCountByType(platMemberInfo.getId(), 1).longValue();
		long unresovedCount = this.memberQuestionService.getQuestionCountByType(platMemberInfo.getId(), 0).longValue();
		request.setAttribute("anCount", Long.valueOf(resovedCount));
		request.setAttribute("qCount", Long.valueOf(unresovedCount));
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("flag", Integer.valueOf(5));
		if ("1".equals(type)) {
			return "member.userinformation.answerManage.list";
		} else if ("0".equals(type)) {
			return "member.userinformation.questionManage.list";
		} else {
			request.setAttribute("pageName", "answerdManage");
			return "member.userinformation.show";
		}
	}

	private void validate(Errors errors, HttpServletRequest request, List<PlatMemberInfo> platMemberInfos, String userId,
			String devCount, int totalCount) {
		if (userId.trim() == "") {
			errors.reject("请输入转入账户");
		} else if (platMemberInfos != null && platMemberInfos.size() != 0) {
			if (devCount.trim() == "") {
				errors.reject("请输入转出积分数额！");
			} else {
				try {
					int e = Integer.parseInt(devCount);
					if (e > totalCount) {
						errors.reject("可转赠的积分不够");
					} else if (e == 0) {
						errors.reject("转赠的积分不能为0");
					}
				} catch (Exception var8) {
					errors.reject("请输入数字");
					LOGGER.error(var8.getMessage());
				}
			}
		} else {
			errors.reject("该用户不存在！");
		}
	}

	@RequestMapping(params = { "actionMethod=memberInformatinManage" })
	public String getMemberInformatinManage(Model model, HttpServletRequest request,
			@RequestParam("isCutPage") boolean isCutPage) {
//		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		PlatMemberInfo platMemberInfo = (PlatMemberInfo)request.getSession().getAttribute("LoginMember");
		PlatMemberInfo tmpMember = (PlatMemberInfo) this.platMemberInfoService.getObjectById(PlatMemberInfo.class,
				Long.valueOf(platMemberInfo.getId().longValue()));
		if (platMemberInfo != null) {
			model.addAttribute("platMemberInfo", platMemberInfo);
			request.getSession().setAttribute("platMemberInfo", platMemberInfo);
		}

		model.addAttribute("pageName", "memberInformatinManage");
		model.addAttribute("tmpMember", tmpMember);
		model.addAttribute("flag", Integer.valueOf(8));
		model.addAttribute("backType", Integer.valueOf(1));
		return isCutPage ? "common.member.editMember" : "member.userinformation.show";
	}

	@RequestMapping(params = { "actionMethod=contactQuery" })
	public String contactQuery(Model model, HttpServletRequest request, @RequestParam("isCutPage") boolean isCutPage) {
		model.addAttribute("pageName", "contactQuery");
		model.addAttribute("flag", Integer.valueOf(2));
		model.addAttribute("backType", Integer.valueOf(1));
		return "member.userinformation.show";
	}

	@RequestMapping(params = { "actionMethod=doQuery" })
	public void doQuery(Model model, HttpServletRequest request, HttpServletResponse response) {
		String cardNo = request.getParameter("cardNo");
		String contractNo = request.getParameter("contractNo");
		String verifyCode = request.getParameter("verifyCode");
		Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		Pattern contractNoRegx = Pattern.compile("^[a-zA-Z0-9]{1,}$");
		boolean flagVerify = true;
		String returnString = null;
		String errorMessage = "";
		String printedBuf;
		if (CommonUtil.isZeroLengthTrimString(verifyCode)) {
			errorMessage = "验证码为必填项";
			flagVerify = false;
		} else {
			boolean bufs = Boolean.FALSE.booleanValue();
			printedBuf = request.getSession().getId();
			bufs = CaptchaServiceSingleton.getInstance().validateResponseForID(printedBuf, verifyCode.toLowerCase())
					.booleanValue();
			if (!bufs) {
				errorMessage = "验证码错误";
				flagVerify = false;
			}
		}

		if (CommonUtil.isZeroLengthTrimString(cardNo)) {
			errorMessage = "身份证号不能为空!";
			flagVerify = false;
		} else if (!idNumPattern.matcher(cardNo).matches()) {
			errorMessage = "身份证号无效!";
			flagVerify = false;
		} else if (CommonUtil.isZeroLengthTrimString(contractNo)) {
			errorMessage = "合同号不能为空!";
			flagVerify = false;
		} else if (!contractNoRegx.matcher(contractNo).matches()) {
			errorMessage = "合同号有非法字符!";
			flagVerify = false;
		}

		if (!flagVerify) {
			returnString = "error|" + errorMessage;
		} else {
			if (cardNo != null) {
				cardNo = cardNo.trim();
				contractNo = contractNo.trim();
			} else {
				cardNo = "1";
				contractNo = "1";
			}

			String bufs1 = this.createQueryXML(contractNo, cardNo);
			printedBuf = CallERPPublicCls.CallERPWebser(bufs1);
			if (cardNo != null && !cardNo.trim().equals("") && contractNo != null && !contractNo.trim().equals("")) {
				ContractEntity e = this.parseXmlToObject(printedBuf);
				if (e != null) {
					returnString = GsonUtil.getCommonGsonInstance().toJson(e);
				}
			}
		}

		byte[] bufs2 = returnString.getBytes();
		int printedBuf1 = 0;

		try {
			response.setContentType("text/text;charset=GBK");
			response.setHeader("Cache-Control", "no-cache");

			while (printedBuf1 < bufs2.length) {
				response.getOutputStream().write(bufs2, printedBuf1,
						bufs2.length - printedBuf1 >= 10240 ? 10240 : bufs2.length - printedBuf1);
				printedBuf1 += 10240;
			}

			response.getOutputStream().flush();
		} catch (IOException var15) {
			LOGGER.error(var15.getMessage());
		}

	}

	private ContractEntity parseXmlToObject(String objStr) {
		ContractEntity ce = new ContractEntity();
		SAXReader reader = new SAXReader();

		try {
			Document e = reader.read(new StringReader(objStr));
//			Element root = e.getRootElement();
			List contractList = e.selectNodes("/BasicDataSyncResult/Results/Item/ContractID");
			if (contractList.size() > 0) {
				Node idCardNO = (Node) contractList.get(0);
				ce.setContractID(idCardNO.getText());
			}

			List idCardNodeList = e.selectNodes("/BasicDataSyncResult/Results/Item/IDCardNo");
			if (idCardNodeList.size() > 0) {
				Node flowList = (Node) idCardNodeList.get(0);
				ce.setIDCardNo(flowList.getText());
			}

			List flowNodeList = e.selectNodes("/BasicDataSyncResult/Results/Item/Flow");
			List<ContractFlowEntity> flowList = new ArrayList<ContractFlowEntity>();
			ContractFlowEntity flow = null;

			for (int i = 0; i < flowNodeList.size(); ++i) {
				flow = new ContractFlowEntity();
				flow.setStep(((Node) flowNodeList.get(i)).selectSingleNode("Step").getText());
				flow.setStepName(((Node) flowNodeList.get(i)).selectSingleNode("StepName").getText());
				flow.setState(((Node) flowNodeList.get(i)).selectSingleNode("State").getText());
				flow.setStepRemarks(((Node) flowNodeList.get(i)).selectSingleNode("StepRemarks").getText());
				String completionDate = ((Node) flowNodeList.get(i)).selectSingleNode("CompletionDate").getText();
				if (!"".equals(completionDate)) {
					completionDate = completionDate.substring(0, 9);
				}

				if ("1".equals(flow.getState())) {
					completionDate = "【完成时间】" + completionDate;
				} else {
					completionDate = "【常规参考】" + completionDate;
				}

				flow.setCompletionDate(completionDate);
				flowList.add(flow);
			}

			ce.setFlows(flowList);
			return ce;
		} catch (DocumentException ex) {
			LOGGER.error(ex.getMessage());
			return null;
		}
	}

	private String createQueryXML(String ContractID, String IDCardNo) {
		String strXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		strXML = strXML + "<BasicData>";
		strXML = strXML + "<DataHeader>";
		strXML = strXML + "<DataSetID>" + UUID.randomUUID() + "</DataSetID>";
		strXML = strXML + "<DataType>ContractQuery</DataType>";
		strXML = strXML + "</DataHeader>";
		strXML = strXML + "<DataBody>";
		strXML = strXML + "<Item>";
		strXML = strXML + "<ItemID>" + UUID.randomUUID() + "</ItemID>";
		strXML = strXML + "<ContractID>" + ContractID + "</ContractID>";
		strXML = strXML + "<IDCardNo>" + IDCardNo + "</IDCardNo>";
		strXML = strXML + "</Item>";
		strXML = strXML + "</DataBody>";
		strXML = strXML + "</BasicData>";
		return strXML;
	}

	@RequestMapping(params = { "actionMethod=phoneCode" }, method = { RequestMethod.POST })
	public void phoneCode(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException,
			IOException {
		String status = "{\"result\": \"error\",\"time\": 0}";
		String phoneNumb = request.getParameter("phone");
		if (CommonUtil.isZeroLengthTrimString(phoneNumb)) {
			status = "{\"result\": \"empty\",\"time\": 0}";
		} else if (!Util.getMatchResult(phoneNumb, "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			status = "{\"result\": \"numbError\",\"time\": 0}";
		} else if (!this.validatePhone(phoneNumb)) {
			boolean out = true;
			byte e = 6;
			String codeNumb = createRandom(out, e);
			if (codeNumb != "") {
				String phoneNumber = phoneNumb;
				String sessionId = request.getSession().getId();
				String requestXML = this.createXml(sessionId, phoneNumb, codeNumb);
				String returnedXml = "";

				try {
					returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
					SAXReader e1 = new SAXReader();
					Document document = e1.read(new StringReader(returnedXml));
					List resultList = document.selectNodes("/BasicDataSyncResult/Results/Item/ResultCode");
					if (resultList.size() > 0) {
						Node node = (Node) resultList.get(0);
						if ("0".equals(node.getText())) {
							keyMessage.put(sessionId + "|" + phoneNumber, codeNumb);
							status = "{\"result\": \"success\",\"time\": "
									+ FrontSystemSettingUtil.getInstance().getMessageResetTime() + "}";
						}
					}
				} catch (DocumentException var20) {
					LOGGER.error(var20.getMessage());
				}
			}
		} else {
			status = "{\"result\": \"black\",\"time\": 0}";
		}

		PrintWriter out1 = null;

		try {
			out1 = response.getWriter();
			out1.write(status);
			out1.flush();
		} catch (IOException var19) {
			LOGGER.error(var19.getMessage());
		} finally {
			if (out1 != null) {
				out1.close();
			}

		}

	}

	public boolean validatePhone(String phone) {
		return this.forbiddenTelService.isInForbiddenList(phone);
	}

	public static String createRandom(boolean numberFlag, int length) {
		String resultStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;

		do {
			resultStr = "";
			int count = 0;

			for (int i = 0; i < length; ++i) {
				double doubleRel = Math.random() * (double) len;
				int intRel = (int) Math.floor(doubleRel);
				char c = strTable.charAt(intRel);
				if (48 <= c && c <= 57) {
					++count;
				}

				resultStr = resultStr + strTable.charAt(intRel);
			}

			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return resultStr;
	}

	private String createXml(String sessionId, String phoneNumber, String message) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = df.format(date);
		String requestXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BasicData><DataHeader><DataSetID>"
				+ UUID.randomUUID() + "</DataSetID>" + "<DataType>SMS</DataType>" + "</DataHeader>" + "<DataBody>" + "<Item>"
				+ "<ItemID>" + sessionId + "</ItemID>" + "<OperationID>1</OperationID>" + "<SMSID>1</SMSID>"
				+ "<MemberID></MemberID>" + "<SMSType>1</SMSType>" + "<Content>" + message + "</Content>" + "<MobilePhone>"
				+ phoneNumber + "</MobilePhone>" + "<CreateTime>" + dateString + "</CreateTime>" + "</Item>" + "</DataBody>"
				+ "</BasicData>";
		return requestXml;
	}

	private String createTestReturnXml() {
		String returnMessageXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><BasicDataSyncResult>  <ResultHeader>    <DataSetID>77dbcb9f-e389-4658-ac02-f2948822b0d2</DataSetID>    <DataType>SMS</DataType>    <SyncTime>2014-12-4 14:19:07</SyncTime>  </ResultHeader>  <Results>    <Item>      <ItemID>D5B0627C3BA649F191FD8EE3769A9F58</ItemID>      <ResultCode>0</ResultCode>      <ResultDetail>发送成功</ResultDetail>    </Item>  </Results></BasicDataSyncResult>";
		return returnMessageXml;
	}

	@RequestMapping(params = { "actionMethod=activePhoneNumber" }, method = { RequestMethod.POST })
	public void activePhoneNumber(Model model, SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;

		try {
			String phone = request.getParameter("phone");
			String memberId = request.getParameter("memberId");
			String errorMessage = "{\"result\": \"ok\"}";
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) this.platMemberInfoService.getObjectById(PlatMemberInfo.class,
					Long.valueOf(memberId));
			if (!"".equals(phone.trim())) {
				String saveStatus = request.getSession().getId();
				String integrateRules = (String) keyMessage.get(saveStatus + "|" + phone);
				String tmpTableList = request.getParameter("verifyCode");
				if (integrateRules == null || "".equals(integrateRules) || !integrateRules.equals(tmpTableList)) {
					errorMessage = "{\"result\": \"codeError\"}";
					out = response.getWriter();
					out.write(errorMessage);
					out.flush();
					return;
				}

				platMemberInfo.setMobilephoneValidatedFlag(1);
				platMemberInfo.setMobilephone(phone);
				keyMessage.remove(saveStatus + "|" + phone);
			}

			if (platMemberInfo.getIsFirst() == 0) {
				String[] saveStatus1 = new String[] { "phoneValidated" };
				List integrateRulesList = this.platMemberInfoService.getAllRulesByRules(saveStatus1);
				List tmpTableList1 = this.scoreManagerService.getIntegralTableByUserId(platMemberInfo.getId());
				IntegralTable tIntegralTable = new IntegralTable();
				if (tmpTableList1.size() > 0) {
					tIntegralTable = (IntegralTable) tmpTableList1.get(0);
				}

				IntegralHistory integralHistory = new IntegralHistory();
				integralHistory.setPlatMemberInfo(platMemberInfo);
				Iterator itRule = integrateRulesList.iterator();

				while (itRule.hasNext()) {
					IntegrateRule integrateRule = (IntegrateRule) itRule.next();
					if (integrateRule.getRuleName().equals("phoneValidated")) {
						integralHistory.setGettedTime(new Date());
						integralHistory.setComment(integrateRule.getComment());
						integralHistory.setIntegral(integrateRule.getRuleValue());
						integralHistory.setGetflag(integrateRule.getId().intValue());
						tIntegralTable.setIntegral(tIntegralTable.getIntegral() + integrateRule.getRuleValue());
					}
				}

				this.platMemberInfoService.save(integralHistory);
				this.platMemberInfoService.save(tIntegralTable);
				platMemberInfo.setIsFirst(1);
			}

			int saveStatus2 = this.platMemberInfoService.saveOrUpdatePlatMemberEditMember(platMemberInfo);
			if (saveStatus2 == 3) {
				status.setComplete();
				ThreadLocalClient.get().getOperator().setMobilephone(platMemberInfo.getMobilephone());
				ThreadLocalClient.get().getOperator().setMobilephoneValidatedFlag(platMemberInfo.getMobilephoneValidatedFlag());
				ThreadLocalClient.put(ThreadLocalClient.get());
			} else if (saveStatus2 == 1) {
				errorMessage = "{\"result\": \"phoneNumberExists\"}";
				out = response.getWriter();
				out.write(errorMessage);
				out.flush();
				return;
			}

			out = response.getWriter();
			out.write(errorMessage);
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}

	}

	@RequestMapping(params = { "actionMethod=removePhoneNumber" }, method = { RequestMethod.POST })
	public void removePhoneNumber(Model model, SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;

		try {
			String e = request.getParameter("phone");
			String memberId = request.getParameter("memberId");
			String errorMessage = "{\"result\": \"ok\"}";
			PlatMemberInfo platMemberInfo = (PlatMemberInfo) this.platMemberInfoService.getObjectById(PlatMemberInfo.class,
					Long.valueOf(memberId));
			if (!"".equals(e.trim())) {
				String saveStatus = request.getSession().getId();
				String message = (String) keyMessage.get(saveStatus + "|" + e);
				String verifyCode = request.getParameter("verifyCode");
				if (message == null || "".equals(message) || !message.equals(verifyCode)) {
					errorMessage = "{\"result\": \"codeError\"}";
					out = response.getWriter();
					out.write(errorMessage);
					out.flush();
					return;
				}

				platMemberInfo.setMobilephoneValidatedFlag(0);
				platMemberInfo.setMobilephone("");
				keyMessage.remove(saveStatus + "|" + e);
			}

			int saveStatus1 = this.platMemberInfoService.saveOrUpdatePlatMemberEditMember(platMemberInfo);
			if (saveStatus1 == 3) {
				status.setComplete();
				ThreadLocalClient.get().getOperator().setMobilephone(platMemberInfo.getMobilephone());
				ThreadLocalClient.get().getOperator().setMobilephoneValidatedFlag(platMemberInfo.getMobilephoneValidatedFlag());
				ThreadLocalClient.put(ThreadLocalClient.get());
			} else if (saveStatus1 == 1) {
				errorMessage = "{\"result\": \"phoneNumberExists\"}";
				out = response.getWriter();
				out.write(errorMessage);
				out.flush();
				return;
			}

			out = response.getWriter();
			out.write(errorMessage);
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}

	}
}
