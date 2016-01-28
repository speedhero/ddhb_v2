package com.huatek.hbwebsite.saveQuery.service;

import java.util.List;
import java.util.Map;

public interface SaveQueryConditionsService {
	/**
	 * 保存查询条件
	 * @param searchMap 
	 * @param type			类别：houseSecond , houseRent, community
	 */
	public void saveQueryConditions(Map<String, String> searchMap, String type);
}
