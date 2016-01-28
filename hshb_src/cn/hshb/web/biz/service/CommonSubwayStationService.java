package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;

public interface CommonSubwayStationService {
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<CommonSubwayStation> getCommonSubwayStationByName(String name);
}
