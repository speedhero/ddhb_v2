package com.huatek.hbwebsite.ad.show;

import com.huatek.ddhb.manage.activityManage.service.ActivityManageService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/adBrowsedCounter.show" })
public class ADBrowsedCounterControler {
	@Autowired
	private ActivityManageService activityManageService;

	@RequestMapping(params = { "actionMethod=counter" })
	public void counter(@RequestParam("adItemID") Long adItemID, HttpServletRequest request, HttpServletResponse response) {
		this.activityManageService.addBrosweredCounter(adItemID);

		try {
			response.getOutputStream().write(0);
			response.getOutputStream().flush();
		} catch (IOException var5) {
			;
		}

	}
}
