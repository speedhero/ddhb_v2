/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.Date;

import cn.hshb.web.biz.mybatis.model.UserBrowsedHistory;

/**
 * @author ShengYoufu
 *
 */
public interface UserBrowsedHistoryService {
	
	/**
	 * 保存用户浏览历史
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param SH
	 * @param RH
	 * @param community
	 * @param lastBrowsedDate
	 */
	public void saveOrUpdateHistory(String clientFlag, String SH, String RH, String community, Date lastBrowsedDate);

	/**
	 * 根据 客户端标志查询用户浏览历史 
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @return
	 */
	public UserBrowsedHistory getUserCompareHistory(String clientFlag);
}
