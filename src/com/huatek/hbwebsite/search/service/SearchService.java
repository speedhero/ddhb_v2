package com.huatek.hbwebsite.search.service;

import com.huatek.base.entity.BaseEntity;
import com.huatek.hbwebsite.search.entity.SearchField;
import com.huatek.hbwebsite.search.entity.SearchItem;
import com.huatek.hbwebsite.service.BaseServiceTo;
import java.util.List;

public interface SearchService extends BaseServiceTo {
	List<SearchItem> loadFirstLevelSearchItem(String var1);

	List<SearchItem> loadSublevelSearchItem(long var1);

	List<SearchField> loadSearchFields(long var1);

	List<BaseEntity> loadQuery(String var1, String var2, String var3);

	String loadInitSearchMenu(String var1);

	String loadSearchMenuByModuleName(String var1);
	
	/**
	 * 何剑波
	 * 租赁SEO分类信息菜单编写
	 * @param moduleName  种类
	 * @return
	 */
	String loadSearchMenuByModuleNameSecond(String moduleName);
}
