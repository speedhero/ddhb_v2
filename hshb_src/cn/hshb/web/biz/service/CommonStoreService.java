/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonStore;

/**
 * @author ShengYoufu
 *
 */
public interface CommonStoreService {
	/**
	 * 根据Id获得门店
	 * @return
	 */
	public CommonStore getCommonStore(String storeId);
	
	/**
	 * 载入所有门店信息
	 * @return
	 */
	public List<CommonStore> getCommonStores();

	/**
	 * 根据名称获取门店信息
	 * @param name
	 * @return
	 */
	public CommonStore getCommonStoreByName(String name);
	
	/**
	 * 根据门店ID列表获取多个门信息
	 * @param storeIdList 门店ID列表
	 * @return
	 */
	public List<CommonStore> getCommonStoresByIds(List<String> storeIdList);
}
