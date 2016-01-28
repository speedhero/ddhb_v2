/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.UserBrowsedHistoryMapper;
import cn.hshb.web.biz.mybatis.model.UserBrowsedHistory;
import cn.hshb.web.biz.mybatis.model.UserBrowsedHistoryKey;
import cn.hshb.web.biz.service.UserBrowsedHistoryService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class UserBrowsedHistoryServiceImpl implements UserBrowsedHistoryService {
	private static final Log log = LogFactory.getLog(UserBrowsedHistoryServiceImpl.class);
	
	@Autowired
	private UserBrowsedHistoryMapper userBrowsedHistoryMapper;
	
	
	/**
	 * 保存用户浏览历史
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param SH
	 * @param RH
	 * @param community
	 * @param lastBrowsedDate
	 */
	@Override
	public void saveOrUpdateHistory(String clientFlag, String SH, String RH, String community, Date lastBrowsedDate) {
		UserBrowsedHistory userBrowsedHistory = this.getUserCompareHistory(clientFlag);
		if (userBrowsedHistory == null) {
			userBrowsedHistory = new UserBrowsedHistory();
			userBrowsedHistory.setId(clientFlag);
			userBrowsedHistory.setSecondHandHouse(SH);
			userBrowsedHistory.setRentHouse(RH);
			userBrowsedHistory.setCommunity(community);
			userBrowsedHistory.setLastBrowseTime(lastBrowsedDate);
			userBrowsedHistoryMapper.insert(userBrowsedHistory);
		} else {
			userBrowsedHistory.setSecondHandHouse(SH);
			userBrowsedHistory.setRentHouse(RH);
			userBrowsedHistory.setCommunity(community);
			userBrowsedHistory.setLastBrowseTime(lastBrowsedDate);
			userBrowsedHistoryMapper.updateByPrimaryKeySelective(userBrowsedHistory);
		}
	}

	/**
	 * 根据 客户端标志查询用户浏览历史 
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @return
	 */
	@Override
	public UserBrowsedHistory getUserCompareHistory(String clientFlag) {
		if (clientFlag == null) {
			return null;
		} else {
			UserBrowsedHistoryKey key = new UserBrowsedHistoryKey();
			key.setId(clientFlag);
			return userBrowsedHistoryMapper.selectByPrimaryKey(key);
		}
	}
}
