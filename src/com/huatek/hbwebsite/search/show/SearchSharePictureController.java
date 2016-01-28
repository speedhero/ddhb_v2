package com.huatek.hbwebsite.search.show;

import com.google.zxing.WriterException;
import com.huatek.hbwebsite.search.service.SharedSearchService;
import com.huatek.hbwebsite.util.TwoDimensionMaker;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/searchPicture.show" })
public class SearchSharePictureController {
	private static final Logger LOGGER = Logger.getLogger(SearchSharePictureController.class);
	private static final String EMPTY_STRING = "";
	@Autowired
	private SharedSearchService sharedSearchService;

	@RequestMapping(params = { "actionMethod=getSharePicture" })
	public void getSubItemMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
		String savedUrl = request.getParameter("savedUrl");
		String finalSavedUrl = savedUrl.replaceAll("\\|\\{\\}\\|", "&");
		finalSavedUrl = finalSavedUrl.replace("&ispage=1", "&ispage=0");
		if (finalSavedUrl != null && !finalSavedUrl.trim().equals("")) {
			String shortIndex = "";
			if (this.sharedSearchService.isExist(finalSavedUrl)) {
				shortIndex = this.sharedSearchService.getSharedUrlShort(finalSavedUrl);
			} else {
				shortIndex = this.sharedSearchService.save(finalSavedUrl);
			}

			if (finalSavedUrl.indexOf("?") >= 0) {
				int firstEque = finalSavedUrl.indexOf("=");
				int firstAt = finalSavedUrl.indexOf("&");
				if (firstEque >= 0 && firstAt >= 0) {
					String tempUrl = finalSavedUrl.substring(0, firstAt);
					String finalUrl = tempUrl.substring(0, firstEque);
					finalUrl = finalUrl + "=startQuery&shortValue=" + shortIndex;
					String format = "JPEG";

					try {
						BufferedImage image = TwoDimensionMaker.getFinalPic(finalUrl);
						ServletOutputStream e = response.getOutputStream();
						ImageIO.write(image, format, e);
						e.close();
					} catch (WriterException var14) {
						LOGGER.error(var14.getMessage());
					} catch (IOException var15) {
						LOGGER.error(var15.getMessage());
					}

				}
			}
		}
	}
}
