/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonActivity;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivitybar;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitem;

/**
 * @author ShengYoufu
 *
 */
public interface CommonActivityService {

	/**
	 * 根据模块ID载入活动列表
	 * @param moduleId
	 * @return
	 */
	public List<CommonActivity> loadActivity(String moduleId);
	
	
	/**
	 * 根据页面标志和位置标志获取广告条信息
	 * @param pageFlag			页面标志
	 * @param positionFlag		位置标志
	 * @return
	 */
	public CommonAdandactivitybar getActivityByPageAndPosition(Integer pageFlag, Integer positionFlag);
	
	/**
	 * 根据广告条ID获取广告项目
	 * @param barId	广告条ID
	 * @return
	 */
	public List<CommonAdandactivityitem> getActivityItemByBarId(String barId);

	/**
	 * 广告点击量加1
	 * @param adItemId	广告项ID
	 */
	public void addBrosweredCounter(Integer adItemId);
}
