package cn.hshb.web.biz.service;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

public interface MemberSavedSearchcontentService {

	/**
	 * 根据用户Id保存查询条件
	 * @param accountBean 用户信息
	 * @param savedUrl	  查询条件的URL
	 * @param fieldNames 查询条件的名称
	 * @param houseType  查询条件的类别 1:二手房 2:租赁 3:小区
	 * @return
	 */
	public String saveSearchFieldByMember(PlatMemberInfo accountBean, String savedUrl, String fieldNames, String houseType);
}
