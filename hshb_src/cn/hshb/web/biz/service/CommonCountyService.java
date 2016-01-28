/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonCounty;

/**
 * @author ShengYoufu
 *
 */
public interface CommonCountyService {
	/**
	 * 查询所有城区<br/>
	 * 与 getCommonCountyies()功能相同<br/>
	 * @return
	 */
	public List<CommonCounty> getCounties();
	
	/**
	 * 查询所有城区<br/>
	 * 与 getCountyies()功能相同<br/>
	 * @return
	 */
	public List<CommonCounty> getCommonCountyies();
	
	/**
	 * 查询所有城区
	 * @return
	 */
	public CommonCounty getCounty(String countyId);
}
