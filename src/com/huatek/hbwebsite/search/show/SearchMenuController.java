package com.huatek.hbwebsite.search.show;

import com.huatek.hbwebsite.search.service.SearchService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/searchMenu.show" })
public class SearchMenuController {
	@Autowired
	private SearchService searchService;

	@RequestMapping(params = { "actionMethod=searchTest" })
	public String searchTest(Model model, HttpServletRequest request, HttpServletResponse response) {
		String returnSring = this.searchService.loadSearchMenuByModuleName("1");
		model.addAttribute("jsonString", returnSring);
		return "test1.page";
	}
}
