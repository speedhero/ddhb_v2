/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.UserCompareHistoryMapper;
import cn.hshb.web.biz.mybatis.model.UserCompareHistory;
import cn.hshb.web.biz.mybatis.model.UserCompareHistoryKey;
import cn.hshb.web.biz.service.UserCompareHistoryService;

/**
 * @author ShengYoufu
 *
 */
@Service
public class UserCompareHistoryServiceImpl implements UserCompareHistoryService {
	private static final Log log = LogFactory.getLog(UserCompareHistoryServiceImpl.class);
	
	@Autowired
	private UserCompareHistoryMapper userCompareHistoryMapper;
	
	/**
	 * 保存比较历史记录
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param SH
	 * @param RH
	 * @param community
	 * @param modifiedDate
	 */
	@Override
	public void saveOrUpdateHistory(String clientFlag, String SH, String RH, String community, Date modifiedDate) {
		UserCompareHistory compareHistory = getUserCompareHistory(clientFlag);
		if (compareHistory == null) {
			compareHistory = new UserCompareHistory();
			compareHistory.setId(clientFlag);
			compareHistory.setSecondHandHouse(SH);
			compareHistory.setRentHouse(RH);
			compareHistory.setCommunity(community);
			compareHistory.setLastModifiedTime(modifiedDate);
			userCompareHistoryMapper.insert(compareHistory);
		} else {
			compareHistory.setSecondHandHouse(SH);
			compareHistory.setRentHouse(RH);
			compareHistory.setCommunity(community);
			compareHistory.setLastModifiedTime(modifiedDate);
			userCompareHistoryMapper.updateByPrimaryKeySelective(compareHistory);
		}
	}

	/**
	 * 根据浏览历史主键查询浏览历史记录
	 * @param clientFlag	客户端标志，用作浏览历史主键，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @return
	 */
	@Override
	public UserCompareHistory getUserCompareHistory(String clientFlag) {
		if (clientFlag == null) {
			return null;
		} else {
			UserCompareHistoryKey key = new UserCompareHistoryKey();
			key.setId(clientFlag);
			return userCompareHistoryMapper.selectByPrimaryKey(key);
		}
	}
}
