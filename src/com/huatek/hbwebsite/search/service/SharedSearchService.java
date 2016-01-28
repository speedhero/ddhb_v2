package com.huatek.hbwebsite.search.service;

import com.huatek.base.service.BaseService;
import java.util.Map;

public interface SharedSearchService extends BaseService {
	boolean isExist(String var1);

	String save(String var1);

	String getSharedUrl(String var1);

	String getSharedUrlShort(String var1);

	Map<String, String> getParameterMap(String var1);
}
