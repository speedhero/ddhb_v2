package com.huatek.hbwebsite.show;

import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.basemanagement.entity.BaseRegion;
import com.huatek.hbwebsite.basemanagement.service.BaseRegionService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/common.show" })
public class EmailSmAction {
	@Autowired
	private BaseRegionService baseRegionService;

	@RequestMapping(params = { "actionMethod=getCitys" })
	public String getCityDropListById(Model model, HttpServletRequest request) throws IOException {
		String province = request.getParameter("selectedProvince");
		if ("".equals(province)) {
			return Util.printString("<option value=\'\'>---请选择---</option>");
		} else {
			String provinceCode = null;
			String[] provinceInfo = province.split(",");
			if (provinceInfo.length == 2) {
				provinceCode = provinceInfo[0];
			} else {
				provinceCode = province;
			}

			List cityList = this.baseRegionService.loadCityList(provinceCode);
			StringBuffer strBuffer = new StringBuffer("");
			strBuffer.append("<option value=\'\'>---请选择---</option>");

			for (int i = 0; i < cityList.size(); ++i) {
				BaseRegion baseRegion = (BaseRegion) cityList.get(i);
				strBuffer.append("<option value=\'" + Util.getString(baseRegion.getCityCode()) + "\'>"
						+ Util.getString(baseRegion.getCityName()) + "</option>");
			}

			return Util.printString(strBuffer.toString());
		}
	}

	@RequestMapping(params = { "actionMethod=getAreas" })
	public String getAreaDropListById(Model model, HttpServletRequest request) throws IOException {
		String city = request.getParameter("selectedCity");
		if ("".equals(city)) {
			return Util.printString("<option value=\'\'>---请选择---</option>");
		} else {
			List areaList = this.baseRegionService.loadAreaList(city);
			StringBuffer strBuffer = new StringBuffer("");
			strBuffer.append("<option value=\'\'>---请选择---</option>");

			for (int i = 0; i < areaList.size(); ++i) {
				BaseRegion baseRegion = (BaseRegion) areaList.get(i);
				strBuffer.append("<option value=\'" + Util.getString(baseRegion.getId()) + "\'>"
						+ Util.getString(baseRegion.getDistrictName()) + "</option>");
			}

			return Util.printString(strBuffer.toString());
		}
	}
}
