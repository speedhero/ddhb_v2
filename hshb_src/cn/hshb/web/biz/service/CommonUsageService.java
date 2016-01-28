/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonUsage;

/**
 * @author ShengYoufu
 *
 */
public interface CommonUsageService {
	/**
	 * 根据Id获得房源用途
	 * @return
	 */
	public CommonUsage getCommonUsage(String usageId);
	
	/**
	 * 载入所有房源用途数据
	 * @return
	 */
	public List<CommonUsage> getCommonUsages();

	/**
	 * 根据名称获取房源用途
	 * @param name
	 * @return
	 */
	public CommonUsage getCommonUsageByName(String name);
}
