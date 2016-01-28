package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExpert;

/**
 * 小区专家
 * @author hejianbo
 *	2015年9月18日
 */
public interface HouseCommunityExpertService {

	/**
	 * 根据小区Id,回返小区专家
	 * @param CommunityErpId
	 * @return
	 */
	public List<HouseCommunityExpert> getList(String CommunityErpId);
	
	/**
	 * 加载小区专家
	 * @param houseCommunity
	 * @return
	 */
	public HouseCommunity loadHouseCOmmunityExpert(HouseCommunity houseCommunity);
}
