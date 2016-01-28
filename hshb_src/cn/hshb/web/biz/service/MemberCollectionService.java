/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.MemberCollection;

/**
 * @author Administrator
 *
 */
public interface MemberCollectionService {

	/**
	 * 根据用户账号ID，获取用户收藏的房源列表
	 * @param memberId
	 * @return
	 */
	List<MemberCollection> getCollectionsByMemberId(Integer memberId);
}
