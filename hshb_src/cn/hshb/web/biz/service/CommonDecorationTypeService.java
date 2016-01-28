/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonDecorationType;

/**
 * @author ShengYoufu
 *
 */
public interface CommonDecorationTypeService {
	/**
	 * 载入所有装修类别数据
	 * @return
	 */
	public List<CommonDecorationType> getDecorationTypes();
	
	/**
	 * 载入指定ID的装修类别
	 * @return
	 */
	public CommonDecorationType getDecorationType(String decorationId);	
	
	
	/**
	 * 根据 ID获取装修类别
	 * @param typeId
	 * @return
	 */
	public CommonDecorationType getDecorationTypeById(String typeId);
	
	/**
	 * 根据名称获取装修类别
	 * @param name
	 * @return
	 */
	public CommonDecorationType getDecorationTypeByName(String name);	
}
