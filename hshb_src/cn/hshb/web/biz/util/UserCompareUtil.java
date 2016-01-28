/**
 * 
 */
package cn.hshb.web.biz.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.huatek.framework.util.SpringContext;
import com.huatek.hbwebsite.member.entity.CompareItem;

import cn.hshb.web.biz.mybatis.model.UserCompareHistory;
import cn.hshb.web.biz.service.UserCompareHistoryService;
import cn.hshb.web.common.helper.ApplicationContextProvider;
import cn.hshb.web.house.enums.EnumHouseType;


/**
 * @author ShengYoufu
 *
 */
@Component
public class UserCompareUtil {
	private static Map<String, CompareItem> compareMap = new HashMap<String, CompareItem>();
	private static UserCompareUtil userCompareUtil = new UserCompareUtil();
	
	@Autowired
	private UserCompareHistoryService userCompareHistoryService = null;

	public static UserCompareUtil getInstance() {
		return userCompareUtil;
	}

	/**
	 * 根据客户端标志和类别获取房源比较对象
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param type
	 * @return
	 * @see 
	 */
	public synchronized String getCompareString(String clientFlag, EnumHouseType type) {
		CompareItem compareItem = null;
		compareItem = (CompareItem) compareMap.get(clientFlag);
		if (compareItem == null) {
			getUserCompareHistoryService();

			UserCompareHistory compareHistory = userCompareHistoryService.getUserCompareHistory(clientFlag);
			if (compareHistory != null) {
				compareItem = new CompareItem();
				compareItem.setCommunity(compareHistory.getCommunity());
				compareItem.setHouseRent(compareHistory.getRentHouse());
				compareItem.setHouseSecond(compareHistory.getSecondHandHouse());
				compareItem.setLastModifiedDate(compareHistory.getLastModifiedTime());
				compareMap.put(clientFlag, compareItem);
			}
		}

		if (compareItem != null) {
			compareItem.setLastModifiedDate(new Date());
			switch(type){
				case SALE:
					return compareItem.getHouseSecond();
				case RENT:
					return compareItem.getHouseRent();
				case COMMUNITY:
					return compareItem.getCommunity();
				default:
					return compareItem.getHouseSecond();
			}
		}

		return null;
	}

	/**
	 * 清除用户房源比较历史
	 * @param clientFlag	客户端标志，参见：com.huatek.hbwebsite.util.ClientFlagUtil类
	 * @param type
	 */
	public void clearCompareString(String clientFlag, EnumHouseType type) {
		this.saveOrUpdateCompareString(clientFlag, type, (String) null);
	}

	/**
	 * 保存比较信息
	 * @param clientFlag
	 * @param type
	 * @param content
	 */
	public synchronized void saveOrUpdateCompareString(String clientFlag, EnumHouseType type, String content) {
		CompareItem compareItem = null;
		compareItem = (CompareItem) compareMap.get(clientFlag);
		if (compareItem == null) {
			compareItem = new CompareItem();
		}

		switch(type){
			case SALE:
				compareItem.setHouseSecond(content);
				break;
			case RENT:
				compareItem.setHouseRent(content);
				break;
			case COMMUNITY:
				compareItem.setCommunity(content);
				break;
			default:
				compareItem.setHouseSecond(content);
				break;
		}

		compareItem.setLastModifiedDate(new Date());
		compareMap.put(clientFlag, compareItem);
	}

	/**
	 * 把比较字符串存入数据库
	 */
	public void saveToDatabase() {
		Date currentDate = new Date();
		CompareItem compareItem = null;
		getUserCompareHistoryService();
		
		for(Map.Entry<String, CompareItem> e: compareMap.entrySet()){
			String clientFlag = e.getKey();
			compareItem = e.getValue();
			if(compareItem!=null && currentDate.getTime() - compareItem.getLastModifiedDate().getTime() >= 10000L){
				userCompareHistoryService.saveOrUpdateHistory(clientFlag, compareItem.getHouseSecond(), compareItem.getHouseRent(),
						compareItem.getCommunity(), compareItem.getLastModifiedDate());
				compareMap.remove(clientFlag);
			}
		}
	}

	private UserCompareHistoryService getUserCompareHistoryService(){
		if (userCompareHistoryService == null) {
			Object obj = ApplicationContextProvider.getBeanOfType(UserCompareHistoryService.class);
			if(obj==null){
				obj = ApplicationContextProvider.getContext().getBean("userCompareHistoryServiceImpl");
			}
			if(obj instanceof UserCompareHistoryService)
				userCompareHistoryService = (UserCompareHistoryService)obj;
		}
		return userCompareHistoryService;
	}
}
