/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonCity;

/**
 * @author ShengYoufu
 *
 */
public interface CommonCityService {
	/**
	 * 载入全部城市数据
	 * @return
	 */
	public List<CommonCity> getCommonCities();
	
	/**
	 * 根据城市ID取得城市记录
	 * @param cityId
	 * @return
	 */
	public CommonCity getCommonCity(String cityId);	
}
