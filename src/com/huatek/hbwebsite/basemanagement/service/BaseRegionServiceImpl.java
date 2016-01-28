package com.huatek.hbwebsite.basemanagement.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.hbwebsite.basemanagement.entity.BaseRegion;
import com.huatek.hbwebsite.basemanagement.service.BaseRegionService;
import java.util.List;

public class BaseRegionServiceImpl extends BaseServiceImpl implements BaseRegionService {
	public List<BaseRegion> getProvinceList() {
		return super.hibernateTemplate.find("from BaseRegion t where t.status=\'A\' and t.regionType=1 order by t.provinceCode asc");
	}

	public List<BaseRegion> loadCityList(String provinceCode) {
		return super.hibernateTemplate.find(
				"from BaseRegion t where t.status=\'A\' and t.regionType=2 and t.provinceCode=? order by t.cityCode asc",
				provinceCode);
	}

	public List<BaseRegion> loadAreaList(String cityCode) {
		return super.hibernateTemplate.find(
				"from BaseRegion t where t.status=\'A\' and t.regionType=3 and t.cityCode=? order by t.districtCode asc",
				cityCode);
	}

	public BaseRegion getBaseRegion(String provinceCode, String cityCode) {
		List regionList = super.hibernateTemplate.find(
				"from BaseRegion t where t.status=\'A\' and t.regionType=2 and t.provinceCode=? and t.cityCode=?",
				new Object[] { provinceCode, cityCode });
		return regionList.size() == 1 ? (BaseRegion) regionList.get(0) : null;
	}

	public BaseRegion getBaseRegion(String provinceCode, String cityCode, String districtCode) {
		List regionList = super.hibernateTemplate
				.find(
						"from BaseRegion t where t.status=\'A\' and t.regionType=3 and t.provinceCode=? and t.cityCode=? and t.districtCode=?",
						new Object[] { provinceCode, cityCode, districtCode });
		return regionList.size() == 1 ? (BaseRegion) regionList.get(0) : null;
	}

	public List<BaseRegion> getAllShaanxiOfXianAreas() {
		String hsql = "from BaseRegion t where t.provinceCode=\'610000\' and t.cityCode=\'610100\' and t.regionType=\'3\'  and t.districtType=\'1\' order by t.districtCode asc ";
		return this.hibernateTemplate.find(hsql);
	}
}
