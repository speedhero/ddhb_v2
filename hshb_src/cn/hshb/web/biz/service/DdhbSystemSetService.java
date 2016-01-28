/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.DdhbSystemSet;

/**
 * @author ShengYoufu
 *
 */
public interface DdhbSystemSetService {
	
	/**
	 * 载入系统所有配置信息
	 * @return
	 */
	List<DdhbSystemSet> getSystemSettings();
	
	/**
	 * 根据设置项目代码查询设置信息
	 * @param setId
	 * @return
	 */
	DdhbSystemSet getSystemSettings(Integer setId);
	
	/**
	 * 根据设置项名称查询设置信息
	 * @param setName
	 * @return
	 */
	List<DdhbSystemSet> getSystemSettings(String setName);
	
	/**
	 * 根据设置项名称查询设置信息
	 * @param setName
	 * @return
	 */
	DdhbSystemSet getSystemSetting(String setName);
	
}
