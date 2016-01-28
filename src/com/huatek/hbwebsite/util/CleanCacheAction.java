package com.huatek.hbwebsite.util;

import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.CompanyInfo;
import com.huatek.hbwebsite.util.FrontSystemSettingUtil;
import com.huatek.hbwebsite.util.HouseSortRuleCacheUtil;
import com.huatek.hbwebsite.util.SystemTitleUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/cleanAction.show" })
public class CleanCacheAction {
	@RequestMapping(params = { "actionMethod=clean" })
	public void cleanCacheAction(Model model, HttpServletRequest request, HttpServletResponse response) {
		SystemTitleUtil.clean();
		CallERPPublicCls.clean();
		FrontSystemSettingUtil.clean();
		CompanyInfo.clean();
	}

	@RequestMapping(params = { "actionMethod=cleanSortFieldCache" })
	public void cleanSortFieldCache(Model model, HttpServletRequest request, HttpServletResponse response) {
		HouseSortRuleCacheUtil.getInstance().clean();
	}
}
