/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.Date;

import cn.hshb.web.biz.mybatis.model.UserCompareHistory;

/**
 * @author ShengYoufu
 *
 */
public interface UserCompareHistoryService {

	/**
	 * 保存比较历史记录
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param SH
	 * @param RH
	 * @param community
	 * @param modifiedDate
	 */
	public void saveOrUpdateHistory(String clientFlag, String SH, String RH, String community, Date modifiedDate);
	
	/**
	 * 根据浏览历史主键查询浏览历史记录
	 * @param clientFlag	客户端标志，用作浏览历史主键，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @return
	 */
	public UserCompareHistory getUserCompareHistory(String clientFlag);
}
