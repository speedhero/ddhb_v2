/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonFlag;

/**
 * @author Administrator
 *
 */
public interface CommonFlagService {
	
	/**
	 * 查询二手房源标签
	 * @return
	 */
	public List<CommonFlag> getHouseTags();
	
	
	/**
	 * 根据名称获取房源标签名称
	 * @param name
	 * @return
	 */
	public CommonFlag getHouseTagByName(String name);
}