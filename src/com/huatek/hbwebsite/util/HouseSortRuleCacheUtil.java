package com.huatek.hbwebsite.util;

import com.huatek.ddhb.manage.frontsystemsetting.service.HouseDefaultSortRuleService;
import com.huatek.framework.util.SpringContext;
import java.util.List;

public class HouseSortRuleCacheUtil {
	private static List<String> sortFields = null;
	private static HouseSortRuleCacheUtil houseSortRuleCacheUtil = new HouseSortRuleCacheUtil();
	private HouseDefaultSortRuleService defaultSortRuleService;

	public static HouseSortRuleCacheUtil getInstance() {
		return houseSortRuleCacheUtil;
	}

	public synchronized List<String> getSortFields() {
		if (sortFields == null) {
			if (this.defaultSortRuleService == null) {
				this.defaultSortRuleService = (HouseDefaultSortRuleService) SpringContext.getBean("houseSortRuleCacheUtil");
			}

			sortFields = this.defaultSortRuleService.getColumnList();
		}

		return sortFields;
	}

	public synchronized void clean() {
		sortFields = null;
	}
}
