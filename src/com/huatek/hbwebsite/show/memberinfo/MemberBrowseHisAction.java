package com.huatek.hbwebsite.show.memberinfo;

import com.huatek.hbwebsite.member.entity.MemberBrowseHistory;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping({ "/browseHistory.do" })
@SessionAttributes(types = { MemberBrowseHistory.class })
public class MemberBrowseHisAction {
	@Autowired
	PlatMemberInfoService platMemberInfoService;
	private static final String MEMBER_INFO_PAGE_PATH = "ddhb/front/member/platMemberInfo/editmemberinfo/edit_memberInfo.jsp";

	@RequestMapping(params = { "actionMethod=initMemberInfo" }, method = { RequestMethod.GET })
	public String initMemberInfo(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (platMemberInfo != null) {
			model.addAttribute("platMemberInfo", platMemberInfo);
		}

		return "pcenter.page.layout.information";
	}

	@RequestMapping(params = { "actionMethod=editMemberInfo" }, method = { RequestMethod.GET })
	public String editMemberInfo(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (platMemberInfo != null) {
			model.addAttribute("platMemberInfo", platMemberInfo);
		}

		return "ddhb/front/member/platMemberInfo/editmemberinfo/edit_memberInfo.jsp";
	}
}
