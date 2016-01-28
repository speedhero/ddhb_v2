package com.huatek.hbwebsite.basemanagement.service;

import com.huatek.base.service.BaseService;
import com.huatek.hbwebsite.basemanagement.entity.BaseRegion;
import java.util.List;

public interface BaseRegionService extends BaseService {
	List<BaseRegion> getProvinceList();

	List<BaseRegion> loadCityList(String var1);

	List<BaseRegion> loadAreaList(String var1);

	BaseRegion getBaseRegion(String var1, String var2);

	BaseRegion getBaseRegion(String var1, String var2, String var3);

	List<BaseRegion> getAllShaanxiOfXianAreas();
}
