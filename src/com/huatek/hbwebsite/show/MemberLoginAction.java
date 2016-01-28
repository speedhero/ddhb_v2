package com.huatek.hbwebsite.show;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping({ "/login.show" })
@SessionAttributes(types = { PlatMemberInfo.class })
public class MemberLoginAction {
	@RequestMapping(params = { "actionMethod=loginCheck" }, method = { RequestMethod.GET })
	public String checkUserAndPwd(HttpServletRequest request) {
		request.setAttribute("errorLable", request.getParameter("errorLable"));
		request.setAttribute("errorMessage", request.getParameter("errorMessage"));
		request.setAttribute("target", request.getParameter("target"));
		request.setAttribute("housetype", request.getParameter("housetype"));
		return "page.platmember.login";
	}
}
