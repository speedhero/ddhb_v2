package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.MemberBespeakService;
import com.huatek.hbwebsite.security.ThreadLocalClient;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping({ "/memberBespeak.do" })
@SessionAttributes(types = { MemberBespeakController.class })
public class MemberBespeakController {
	private static final String MEMBER_BESPEAK_QUERY_PAGE = "ddhb/front/member/platMemberInfo/bespeak/memberbespeaks.jsp";
	@Autowired
	private MemberBespeakService memberBespeakService;

	@RequestMapping(params = { "actionMethod=getMemberBespeakList" })
	public String query(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = ThreadLocalClient.get().getOperator();
		List memberBespeakList = this.memberBespeakService.getMemberBespeakItem(platMemberInfo.getId().longValue());
		if (memberBespeakList.size() > 0) {
			request.setAttribute("memberBespeaks", memberBespeakList);
		}

		return "ddhb/front/member/platMemberInfo/bespeak/memberbespeaks.jsp";
	}
}
