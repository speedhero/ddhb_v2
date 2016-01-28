/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonCbdWebsite;
import cn.hshb.web.biz.mybatis.model.CommonCountyTwo;

/**
 * @author ShengYoufu
 *
 */
public interface CommonCountyTwoService {
	/**
	 * 随机查询租赁房热门商圈
	 * @param count 数量
	 * @return
	 */
	public List<CommonCbdWebsite> getRandomHotCommunityForRent(Integer count);
	
	/**
	 * 返回租赁列表,杭州租房查询条件
	 * @return
	 */
	public List<CommonCountyTwo> getCommonCountyTwoList();
	
	/**
	 * 根据区域Id 返回商圈id
	 * @param id 
	 * @return
	 */
	public List<Integer> getCommonCbdWebsiteIdList(String id);
}
