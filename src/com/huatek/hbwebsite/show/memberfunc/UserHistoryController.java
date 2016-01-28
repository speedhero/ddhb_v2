package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.UserBrowsedHistoryUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/userBrowsedHistory.show" })
public class UserHistoryController {
	@RequestMapping(params = { "actionMethod=updateHistoryItem" }, method = { RequestMethod.POST })
	public void addCompareItem(HttpServletRequest request, HttpServletResponse response) {
		String cookieKeyStr = request.getParameter("cookieKeyStr");
		String contentStr = request.getParameter("contentStr");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		UserBrowsedHistoryUtil.getInstance().updateHistoryString(clientFlag, cookieKeyStr, contentStr);
	}
}
