/**
 * 
 */
package cn.hshb.web.biz.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huatek.hbwebsite.member.entity.HistoryItem;

import cn.hshb.web.biz.mybatis.model.UserBrowsedHistory;
import cn.hshb.web.biz.service.UserBrowsedHistoryService;
import cn.hshb.web.common.helper.ApplicationContextProvider;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author ShengYoufu
 *
 */
public class UserBrowsedHistoryUtil {
	private static Map<String, HistoryItem> historyMap = new HashMap<String, HistoryItem>();
	private static UserBrowsedHistoryUtil userBrowseredUtil = new UserBrowsedHistoryUtil();
	private UserBrowsedHistoryService userBrowsedHistoryService = null;

	public static UserBrowsedHistoryUtil getInstance() {
		return userBrowseredUtil;
	}
	
	
	/**
	 * 获取缓存的房源浏览历史
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param type			房源类型
	 * @return
	 */
	public synchronized String getHistoryString(String clientFlag, EnumHouseType type) {
		HistoryItem historyItem = (HistoryItem) historyMap.get(clientFlag);
		if (historyItem == null) {
			getUserBrowsedHistoryService();

			UserBrowsedHistory userBrowsedHistory = userBrowsedHistoryService.getUserCompareHistory(clientFlag);
			if (userBrowsedHistory != null) {
				historyItem = new HistoryItem();
				historyItem.setCommunity(userBrowsedHistory.getCommunity());
				historyItem.setHouseRent(userBrowsedHistory.getRentHouse());
				historyItem.setHouseSecond(userBrowsedHistory.getSecondHandHouse());
				historyItem.setLastBrowsedDate(userBrowsedHistory.getLastBrowseTime());
				historyMap.put(clientFlag, historyItem);
			}
		}

		if (historyItem != null) {
			historyItem.setLastBrowsedDate(new Date());
			switch(type){
				case SALE:
					return historyItem.getHouseSecond();
				case RENT:
					historyItem.getHouseRent();
				case COMMUNITY:
					historyItem.getCommunity();
				default:
					return historyItem.getHouseSecond();
			}
		}

		return null;
	}

	/**
	 * 更新缓存的浏览历史	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param clientFlag
	 * @param type
	 * @param content
	 */
	public synchronized void updateHistoryString(String clientFlag, EnumHouseType type, String content) {
		HistoryItem historyItem = null;
		historyItem = (HistoryItem) historyMap.get(clientFlag);
		if (historyItem == null) {
			historyItem = new HistoryItem();
		}
		switch(type){
			case SALE:
				historyItem.setHouseSecond(content);
				break;
			case RENT:
				historyItem.setHouseRent(content);
				break;
			case COMMUNITY:
				historyItem.setCommunity(content);
				break;
			default:
				historyItem.setHouseSecond(content);
				break;
		}
		historyItem.setLastBrowsedDate(new Date());
		historyMap.put(clientFlag, historyItem);
	}

	/**
	 * 把浏览历史保存到数据库
	 */
	public void saveToDatabase() {
		getUserBrowsedHistoryService();
		
		Date currentDate = new Date();
		for(Map.Entry<String, HistoryItem> e: historyMap.entrySet()){
			String clientFlag = e.getKey();
			HistoryItem historyItem = e.getValue();
			if (historyItem != null && currentDate.getTime() - historyItem.getLastBrowsedDate().getTime() >= 720000L) {
				userBrowsedHistoryService.saveOrUpdateHistory(clientFlag, historyItem.getHouseSecond(),
						historyItem.getHouseRent(), historyItem.getCommunity(), historyItem.getLastBrowsedDate());
				historyMap.remove(clientFlag);
			}
		}
	}

	private UserBrowsedHistoryService getUserBrowsedHistoryService(){
		if (userBrowsedHistoryService == null) {
			Object obj = ApplicationContextProvider.getBeanOfType(UserBrowsedHistoryService.class);
			if(obj==null){
				obj = ApplicationContextProvider.getContext().getBean("userBrowsedHistoryService");
			}
			if(obj instanceof UserBrowsedHistoryService)
				userBrowsedHistoryService = (UserBrowsedHistoryService)obj;
		}
		return userBrowsedHistoryService;
	}	
}
