/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;
import java.util.Map;

import cn.hshb.web.biz.mybatis.model.MemberBrowseHistory;


/**
 * @author Administrator
 *
 */
public interface MemberBrowseHistoryService {

	/**
	 * 根据房源查询会员浏览记录
	 * @param memberId		会员ID
	 * @param houseType		房源类型，1：二手房源，2：租赁房源，3：小区
	 * @param houseId		房源ID
	 * @return
	 */
	public List<MemberBrowseHistory> findMemberBrowseHistoryByMember(Long memberId, Integer houseType, String houseId);
	
	/**
	 * 根据会员ID和房源类型查询浏览历史记录
	 * @param memberId		会员ID
	 * @param houseType		房源类型，1：二手房源，2：租赁房源，3：小区
	 * @param pageSize		分页大小
	 * @param pageNum		当前页数
	 * @return
	 */
	public List<MemberBrowseHistory> getBrowseHistory(Long memberId, Integer houseType, Integer pageSize, Integer pageNum);
	
	
	/**
	 * 根据会员ID按房源类型分组统计浏览数
	 * @param memberId	会员ID
	 * @return
	 */
	public List<Map> getCountByObjectType(Long memberId);
	
	/**
	 * 更新会员房源浏览记录
	 * @param mbh 
	 */
	public void update(MemberBrowseHistory mbh);
	
	/**
	 * 保存会员房源浏览记录
	 * @param mbh
	 */
	public void save(MemberBrowseHistory mbh);
	
	/**
	 * 保存会员浏览记录
	 * @param memberId		会员ID
	 * @param houseType		房源类型
	 * @param houseId		房源ID
	 */
	public void saveUserBrowseHistory(Long memberId, Integer houseType, String houseId);
}
