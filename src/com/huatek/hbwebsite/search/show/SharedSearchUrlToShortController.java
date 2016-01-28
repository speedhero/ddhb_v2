package com.huatek.hbwebsite.search.show;

import com.huatek.hbwebsite.search.service.SharedSearchService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/toShortUrl.show" })
public class SharedSearchUrlToShortController {
	@Autowired
	private SharedSearchService sharedSearchService;
	private static final String ERROR_JSON = "{\"returnedUrl\":\"\", \"operationStatus\":\"false\"}";
	private static final String EMPTY_STRING = "";
	private static final Logger LOGGER = Logger.getLogger(SharedSearchUrlToShortController.class);

	@RequestMapping(params = { "actionMethod=shortUrl" })
	public void toShortUrl(Model model, HttpServletRequest request, HttpServletResponse response) {
		String sharedUrl = request.getParameter("sharedUrl");
		if (sharedUrl != null && !sharedUrl.trim().equals("")) {
			String shortIndex = "";
			if (this.sharedSearchService.isExist(sharedUrl)) {
				shortIndex = this.sharedSearchService.getSharedUrlShort(sharedUrl);
			} else {
				shortIndex = this.sharedSearchService.save(sharedUrl);
			}

			if (sharedUrl.indexOf("?") < 0) {
				this.printResult(response, "{\"returnedUrl\":\"\", \"operationStatus\":\"false\"}");
			} else {
				int firstEque = sharedUrl.indexOf("=");
				int firstAt = sharedUrl.indexOf("&");
				if (firstEque >= 0 && firstAt >= 0) {
					String tempUrl = sharedUrl.substring(0, firstAt);
					String finalUrl = tempUrl.substring(0, firstEque);
					finalUrl = finalUrl + "=startQuery&shortValue=" + shortIndex;
					this.printResult(response, "{\"returnedUrl\":\"" + finalUrl + "\", \"operationStatus\":\"true\"}");
				} else {
					this.printResult(response, "{\"returnedUrl\":\"\", \"operationStatus\":\"false\"}");
				}
			}
		} else {
			this.printResult(response, "{\"returnedUrl\":\"\", \"operationStatus\":\"false\"}");
		}
	}

	private void printResult(HttpServletResponse response, String returnedJson) {
		try {
			PrintWriter e = response.getWriter();
			e.write(returnedJson);
			e.flush();
		} catch (IOException var4) {
			LOGGER.error(var4.getMessage());
		}

	}
}
