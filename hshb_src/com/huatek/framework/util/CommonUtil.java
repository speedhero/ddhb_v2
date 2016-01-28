package com.huatek.framework.util;

import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.util.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class CommonUtil {
	public static boolean isZeroLengthTrimString(String value) {
		return value == null || value.trim().length() == 0;
	}

	public static boolean isNotZeroLengthTrimString(String value) {
		return !isZeroLengthTrimString(value);
	}

	public static String getParamValue(PageContext pageContext, String name) {
		String satrribute = pageContext.findAttribute(name).toString();
		String value = pageContext.getRequest().getParameter(satrribute);
		return value != null && value.trim().length() != 0 ? value : "";
	}

	public static String getParamValue(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		return isZeroLengthTrimString(value) ? "" : value;
	}

	public static Date getAddDate(String yyyy_MM_DD, int days) {
		if (yyyy_MM_DD == null) {
			return new Date();
		} else {
			String[] calendar = yyyy_MM_DD.split("-");
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(Integer.parseInt(calendar[0]), Integer.parseInt(calendar[1]) - 1, Integer.parseInt(calendar[2]), 0, 0, 0);
			gc.add(5, days);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return java.sql.Date.valueOf(sdf.format(gc.getTime()));
		}
	}

	public static String getIdSQLParam(Long[] ids) {
		if (ids != null && ids.length != 0) {
			StringBuffer param = new StringBuffer("");

			for (int i = 0; i < ids.length; ++i) {
				if (i > 0) {
					param.append(",");
				}

				param.append("?");
			}

			return param.toString();
		} else {
			return null;
		}
	}

	public static Date getDateValue(String value) {
		boolean isTime = value.trim().indexOf(" ") > 0;
		String formatStyle = "";
		if (isTime) {
			formatStyle = Parameter.getInstance().getProp().getProperty("format.time.style", "yyyy-MM-dd HH:mm:ss");
		} else {
			formatStyle = Parameter.getInstance().getProp().getProperty("format.date.style", "yyyy-MM-dd");
		}

		try {
			return getSampleDateFormat(formatStyle).parse(value);
		} catch (ParseException var4) {
			throw new BusinessRuntimeException(var4.getMessage(), var4);
		}
	}

	public static SimpleDateFormat getSampleDateFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf;
	}

	public static String getURL(String url, String menuId, String method) {
		return url + "?actionMethod=" + method + "&menuId=" + menuId;
	}
}
