/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.SearchItem;

/**
 * @author Administrator
 *
 */
public interface SearchItemService {
	
	/**
	 * 查询搜索条件
	 * @return
	 */
	List<SearchItem> getSearchItems();
	
	List<SearchItem> loadSearchItemByModuleId(String moduleId);
}
