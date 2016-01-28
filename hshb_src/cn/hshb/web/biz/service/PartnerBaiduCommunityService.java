package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.PartnerBaiduCommunity;


public interface PartnerBaiduCommunityService {
	/**
	 * 返回小区数据
	 * @param maxNumber	上限条数
	 * @return
	 */
	List<PartnerBaiduCommunity> getCommunityList(int maxNumber);
	
	/**
	 * 返回数据
	 * @param communityList 
	 */
	void updateCommunityInformation(List<PartnerBaiduCommunity> communityList);
}
