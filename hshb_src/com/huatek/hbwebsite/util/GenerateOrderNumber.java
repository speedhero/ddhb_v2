package com.huatek.hbwebsite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateOrderNumber {
	public static String getOrderNo(Long currentDayOrderCount) {
		long orderNumber = 0L;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowdate = sdf.format(new Date());
		orderNumber = Long.parseLong(nowdate) * 10000L;
		orderNumber += getNewOrderID(currentDayOrderCount).longValue();
		return String.valueOf(orderNumber);
	}

	public static Long getNewOrderID(Long currentDayOrderCount) {
		return Long.valueOf(currentDayOrderCount.longValue() + 1L);
	}
}
