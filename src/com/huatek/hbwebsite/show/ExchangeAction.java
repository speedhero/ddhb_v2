package com.huatek.hbwebsite.show;

import com.huatek.hbwebsite.member.service.SubscribeInfoService;
import com.huatek.hbwebsite.search.service.SearchService;

import cn.hshb.web.biz.service.PlatMemberInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/exchange.show" })
public class ExchangeAction {
	@Autowired
	PlatMemberInfoService platMemberInfoService;
	@Autowired
	private SubscribeInfoService subScribeInfoService;
	@Autowired
	private SearchService searchService;

	@RequestMapping(params = { "actionMethod=welcome" })
	public String processCheckUserAndPwd(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "common.definition";
	}
}
