/**
 * 
 */
package cn.hshb.web.biz.service;

import cn.hshb.web.biz.mybatis.model.HouseNewBrowseAnalyse;

/**
 * @author ShengYoufu
 *
 */
public interface HouseNewBrowseAnalyseService {
	/**
	 * 保存新楼盘浏览记录
	 * @param record
	 * @return
	 */
	public Boolean save(HouseNewBrowseAnalyse record);
}
