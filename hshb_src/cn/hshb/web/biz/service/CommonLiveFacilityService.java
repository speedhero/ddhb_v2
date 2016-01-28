/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonLiveFacility;

/**
 * @author ShengYoufu
 *
 */
public interface CommonLiveFacilityService {

	
	/**
	 * 查询出租屋内设施
	 * 与 getHouseFurnitures功能相同
	 * @return
	 */
	public List<CommonLiveFacility> getHouseFacilitys();

	/**
	 * 查询出租屋内设施
	 * 与 getHouseFacility功能相同
	 * @return
	 */
	public List<CommonLiveFacility> getHouseFurnitures();
	
	/**
	 * 根据名称取出租屋内设施 
	 * @param name
	 * @return
	 */
	public CommonLiveFacility getHouseFurnitureByName(String name);
}
