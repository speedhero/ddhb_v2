package com.huatek.hbwebsite.show.memberinfo;

import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.QueryCondition;
import com.huatek.hbwebsite.member.entity.MemberSavedSearchContent;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.MemberSaveSearchFieldService;
import com.huatek.hbwebsite.search.service.SharedSearchService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/saveSearchField.show" })
public class MemberSaveSearchFieldsAction {
	private static final String SAVE_SUCCESS = "{\"resultCode\":\"1\"}";
	private static final String SAVE_EXIST = "{\"resultCode\":\"2\"}";
	private static final String SAVE_NOTlOGIN = "{\"resultCode\":\"3\"}";
	private static final int BUFSIZE_10K = 10240;
	private static final Logger LOGGER = Logger.getLogger(MemberSaveSearchFieldsAction.class);
	@Autowired
	private MemberSaveSearchFieldService memberSaveSearchFieldService;
	@Autowired
	SharedSearchService sharedSearchService;

	@RequestMapping(params = { "actionMethod=saveSearchItem" })
	public void saveSearchItem(Model model, HttpServletRequest request, HttpServletResponse response) {
		String resultString = "";
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (accountBean == null) {
			resultString = "{\"resultCode\":\"3\"}";
		} else {
			String bufs = request.getParameter("savedUrl");
			String printedBuf = bufs.replaceAll("\\|\\{\\}\\|", "&");
			String e = request.getParameter("fieldName");
			if (e.length() > 0) {
				e = e.substring(0, e.length() - 1);
			}

			e.replaceAll("<span>", "");
			e.replaceAll("</span>", "");
			printedBuf = printedBuf.replace("&ispage=1", "&ispage=0");
			byte type = 0;
			String param = "";
			String[] paramArray = new String[0];
			if (printedBuf.indexOf("?") < printedBuf.length()) {
				param = printedBuf.substring(printedBuf.indexOf("&") + 1, printedBuf.length());
				paramArray = param.split("&");
			}

			List fieldList = Arrays.asList(paramArray);
			String actionName = printedBuf.substring(printedBuf.lastIndexOf("/") + 1, printedBuf.indexOf("&"));
			Collections.sort(fieldList);
			StringBuffer sortedUrl = new StringBuffer(actionName);
			Iterator sortedUrlString = fieldList.iterator();

			String shortIndex;
			while (sortedUrlString.hasNext()) {
				shortIndex = (String) sortedUrlString.next();
				sortedUrl.append("&");
				sortedUrl.append(shortIndex);
			}

			if (actionName.indexOf("houseSecond.show") >= 0) {
				type = 1;
			} else if (actionName.indexOf("rent.show") >= 0) {
				type = 2;
			} else if (actionName.indexOf("community.show") >= 0) {
				type = 3;
			}

			shortIndex = "";
			String sortedUrlString1 = sortedUrl.toString();
			if (this.sharedSearchService.isExist(sortedUrlString1)) {
				shortIndex = this.sharedSearchService.getSharedUrlShort(sortedUrlString1);
			} else {
				shortIndex = this.sharedSearchService.save(sortedUrlString1);
			}

			int firstEque = sortedUrlString1.indexOf("=");
			int firstAt = sortedUrlString1.indexOf("&");
			if (firstEque < 0 || firstAt < 0) { return; }

			String tempUrl = sortedUrlString1.substring(0, firstAt);
			String finalUrl = tempUrl.substring(0, firstEque);
			finalUrl = finalUrl + "=startQuery&shortValue=" + shortIndex;
			boolean exist = this.memberSaveSearchFieldService.isUrlExist(finalUrl, accountBean.getId(), type);
			if (exist) {
				resultString = "{\"resultCode\":\"2\"}";
			} else {
				MemberSavedSearchContent memberSavedSearchContent = new MemberSavedSearchContent();
				memberSavedSearchContent.setNo(UUID.randomUUID().toString());
				memberSavedSearchContent.setType(type);
				memberSavedSearchContent.setMemberId(accountBean.getId());
				memberSavedSearchContent.setDeleteFlag(0);
				memberSavedSearchContent.setUrl(finalUrl);
				memberSavedSearchContent.setSavedField(e);
				memberSavedSearchContent.setCreateDate(new Date());
				this.memberSaveSearchFieldService.save(memberSavedSearchContent);
				resultString = "{\"resultCode\":\"1\"}";
			}
		}

		byte[] bufs1 = resultString.getBytes();
		int printedBuf1 = 0;

		try {
			while (printedBuf1 < bufs1.length) {
				response.getOutputStream().write(bufs1, printedBuf1,
						bufs1.length - printedBuf1 >= 10240 ? 10240 : bufs1.length - printedBuf1);
				printedBuf1 += 10240;
			}

			response.getOutputStream().flush();
		} catch (IOException var23) {
			LOGGER.error(var23.getMessage());
		}

	}

	@RequestMapping(params = { "actionMethod=listAll" })
	public String listAllSelectedFiel(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("isCutPage") boolean isCutPage) {
		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		CutPageBean pageBean = QueryCondition.getQueryPageBean(request);
		CutPageBean pages = this.memberSaveSearchFieldService.getCutPageBean(pageBean, platMemberInfo.getId());
		model.addAttribute("pageBean", pages);
		model.addAttribute("pageName", "savedField");
		model.addAttribute("backType", Integer.valueOf(1));
		model.addAttribute("flag", Integer.valueOf(9));
		return isCutPage ? "member.userinformation.savedFieldList" : "member.userinformation.show";
	}

	@RequestMapping(params = { "actionMethod=deleteSavedFiel" }, method = { RequestMethod.POST })
	public void deleteSavedFiel(HttpServletRequest request, HttpServletResponse response, @RequestParam("mid") String mid) {
		String status = "{\"result\":\"error\"}";
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		boolean flag = this.memberSaveSearchFieldService.deleteSavedFieldList(mid);
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
}
