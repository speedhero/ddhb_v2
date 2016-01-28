package com.huatek.hbwebsite.util;

import com.huatek.framework.util.CommonUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FrontUtil {
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean isTelephone(String phonenumber) {
		String phone = "0\\d{2,3}\\d{7,8}";
		Pattern p = Pattern.compile(phone);
		Matcher m = p.matcher(phonenumber);
		return m.matches();
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static String getInitDate() throws ParseException {
		SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		Date tenDate = format.parse(monthFormat.format(nowDate) + "-10");
		Date twentyDate = format.parse(monthFormat.format(nowDate) + "-20");
		Date nowDateDay = format.parse(format.format(nowDate));
		if (nowDateDay.getTime() <= tenDate.getTime()) {
			return format.format(tenDate);
		} else if (nowDateDay.getTime() > tenDate.getTime() && nowDateDay.getTime() <= twentyDate.getTime()) {
			return format.format(twentyDate);
		} else if (nowDateDay.getTime() > twentyDate.getTime()) {
			int maxDay = getMaxDayFromDate(monthFormat.format(nowDate));
			return monthFormat.format(nowDate) + "-" + maxDay;
		} else {
			return "";
		}
	}

	public static boolean getMatchResult(String aimStr, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(aimStr);
		return matcher.matches();
	}

	public static int getMaxDayFromDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(sdf.parse(date));
		int monthDay = getMonthLastDay(calendar.get(1), calendar.get(2) + 1);
		return monthDay;
	}

	public static Date getInitDate(String date) throws ParseException {
		if (CommonUtil.isZeroLengthTrimString(date)) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date newDate = format.parse(date);
			return newDate;
		}
	}

	public static String getStringDate(Date date) throws ParseException {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String newDate = format.format(date);
			return newDate;
		}
	}

	public static String[] getDaysByMonth() {
		Date curDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(curDate);
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, Integer.parseInt(date.substring(0, 4)));
		calendar.set(2, Integer.parseInt(date.substring(5, 7)) - 1);
		int maxDay = calendar.getActualMaximum(5);
		String[] days = new String[maxDay];

		for (int i = 1; i <= maxDay; ++i) {
			String d = String.valueOf(i);
			days[i - 1] = d;
		}

		return days;
	}

	public static int countDays(String startDate, String endDate) throws ParseException {
		int days = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = df.parse(startDate);
		Date endsDate = df.parse(endDate);
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();
		c_b.setTime(beginDate);
		c_e.setTime(endsDate);

		while (c_b.before(c_e)) {
			++days;
			c_b.add(6, 1);
		}

		return days;
	}

	public static int getMonthLastDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(1, year);
		cal.set(2, month - 1);
		cal.set(5, 1);
		cal.roll(5, -1);
		int maxDate = cal.get(5);
		return maxDate;
	}

	public static BigDecimal getAmountByMapKey(Object obj, String key) {
		String value = "";
		BigDecimal returnVal = new BigDecimal(0.0D);
		if (obj != null) {
			HashMap<String, String> map = (HashMap<String, String>) obj;
			if (map.get(key) != null && !map.get(key).equals("")) {
				value = map.get(key).toString();
				returnVal = new BigDecimal(value);
			} else {
				value = "";
				returnVal = new BigDecimal("0");
			}
		}

		return returnVal.setScale(2, 4);
	}

	public static List getEveryMonthDays(int startYear, int startMonth, int endYear, int endMonth, int endInterestDate)
			throws ParseException {
		List<Integer> list = new ArrayList<Integer>();
		int differ_month = 12 * (endYear - startYear) + endMonth - startMonth;
		String date1 = startYear + "-" + startMonth + "-" + endInterestDate;
		String date2 = "";

		for (int i = 1; i <= differ_month; ++i) {
			if (startMonth + 1 > 12) {
				++startYear;
				startMonth = 1;
				date2 = startYear + "-" + startMonth + "-" + endInterestDate;
			} else {
				date2 = startYear + "-" + (startMonth + 1) + "-" + endInterestDate;
				++startMonth;
			}

			if (startMonth == endMonth + 1 && startYear == endYear) {
				break;
			}

			list.add(Integer.valueOf(countDays(date1, date2)));
			date1 = date2;
		}

		return list;
	}

	public static List getQuarterDays(int startYear, int startMonth, int endYear, int endMonth, int endInterestDate)
			throws ParseException {
		List<Integer> list = new ArrayList<Integer>();
		List daysList = getEveryMonthDays(startYear, startMonth, endYear, endMonth, endInterestDate);
		boolean result = false;
		int size = daysList.size();

		for (int i = 0; i < size; ++i) {
			if (size % 3 == 0) {
				int var10 = ((Integer) daysList.get(i)).intValue() + ((Integer) daysList.get(i + 1)).intValue()
						+ ((Integer) daysList.get(i + 2)).intValue();
				i += 3;
				list.add(Integer.valueOf(var10));
			}
		}

		return list;
	}

	public static List<Object> getQuarterValueFromNow() {
		List quaterValuesList = new ArrayList();
		Calendar c = Calendar.getInstance();
		int y = c.get(1);
		int m = c.get(2) + 1;

		for (int i = 0; i < 12; ++i) {
			int s = (m - 1) / 3 + 1;
			int em = s * 3;
			if (s > 4) {
				++y;
				s = 1;
			}

			if (em > 12) {
				em = 3;
			}

			m = em + 1;
			quaterValuesList.add(y + "年" + s + "季度");
		}

		return quaterValuesList;
	}

	public static Boolean checkReportDate(String startDate, String endDate) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		if (CommonUtil.isNotZeroLengthTrimString(startDate) && CommonUtil.isNotZeroLengthTrimString(endDate)) {
			calendar.setTime(getInitDate(endDate));
			calendar.add(1, -1);
			Date newStartDate = calendar.getTime();
			if (getInitDate(startDate).getTime() < newStartDate.getTime()) { return Boolean.valueOf(false); }
		}

		return Boolean.valueOf(true);
	}

	public static Date getLastYearDate() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(1, -1);
		Date lastYearDate = calendar.getTime();
		return lastYearDate;
	}
}
