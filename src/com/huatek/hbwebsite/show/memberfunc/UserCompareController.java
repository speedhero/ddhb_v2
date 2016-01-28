package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import com.huatek.hbwebsite.util.UserCompareUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/userCompare.show" })
public class UserCompareController {
	private static final String SUCCESS_STR = "{\"result\":\"success\"}";
	private static final String FAILED_STR = "{\"result\":\"failed\"}";
	private static final int SUCCESS_FLAG = 0;
	private static final int FAILED_FLAG = 1;
	private static final Logger LOGGER = Logger.getLogger(UserCompareController.class);

	@RequestMapping(params = { "actionMethod=addCompareItem" }, method = { RequestMethod.POST })
	public void addCompareItem(HttpServletRequest request, HttpServletResponse response) {
		String cookieKeyStr = request.getParameter("cookieKeyStr");
		String contentStr = request.getParameter("contentStr");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		UserCompareUtil.getInstance().saveOrUpdateCompareString(clientFlag, cookieKeyStr, contentStr);
		this.printToFront(response, 0);
	}

	@RequestMapping(params = { "actionMethod=clearCompareItem" }, method = { RequestMethod.POST })
	public void clearCompareItem(HttpServletRequest request, HttpServletResponse response) {
		String cookieKeyStr = request.getParameter("cookieKeyStr");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		UserCompareUtil.getInstance().clearCompareString(clientFlag, cookieKeyStr);
		this.printToFront(response, 0);
	}

	private void printToFront(HttpServletResponse response, int status) {
		String resultString = null;
		if (status == 0) {
			resultString = "{\"result\":\"success\"}";
		} else {
			resultString = "{\"result\":\"failed\"}";
		}

		PrintWriter writer = null;

		try {
			writer = response.getWriter();
			writer.print(resultString);
			writer.flush();
		} catch (IOException var9) {
			LOGGER.error(var9.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}

		}

	}

	@RequestMapping(params = { "actionMethod=getCompareItem" }, method = { RequestMethod.POST })
	private void getConpareItem(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		String cookieKeyStr = request.getParameter("cookieKeyStr");
		String comparedString = "";
		if (cookieKeyStr.equals("secondHouseCompare")) {
			comparedString = UserCompareUtil.getInstance().getCompareString(clientFlag, "secondHouseCompare");
		} else if (cookieKeyStr.equals("rentHouseCompare")) {
			comparedString = UserCompareUtil.getInstance().getCompareString(clientFlag, "rentHouseCompare");
		} else {
			comparedString = UserCompareUtil.getInstance().getCompareString(clientFlag, "communityCompare");
		}

		if (comparedString == null) {
			comparedString = "";
		}

		PrintWriter writer = null;

		try {
			writer = response.getWriter();
			writer.print(comparedString);
			writer.flush();
		} catch (IOException var11) {
			LOGGER.error(var11.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}

		}

	}

	@RequestMapping(params = { "actionMethod=getHistoryItem" }, method = { RequestMethod.POST })
	private void getHistoryItem(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		String cookieKeyStr = request.getParameter("cookieKeyStr");
		String historyString = "";
		if (cookieKeyStr.equals("secondHouseHistory")) {
			historyString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "secondHouseHistory");
		} else if (cookieKeyStr.equals("rentHouseHistory")) {
			historyString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "rentHouseHistory");
		} else {
			historyString = UserBrowsedHistoryUtil.getInstance().getHistoryString(clientFlag, "communityHistory");
		}

		if (historyString == null) {
			historyString = "";
		}

		PrintWriter writer = null;

		try {
			writer = response.getWriter();
			writer.print(historyString);
			writer.flush();
		} catch (IOException var11) {
			LOGGER.error(var11.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}

		}

	}
}
