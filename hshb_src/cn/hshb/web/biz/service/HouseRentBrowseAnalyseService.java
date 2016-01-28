package cn.hshb.web.biz.service;

import cn.hshb.web.biz.mybatis.model.HouseRentHouse;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

public interface HouseRentBrowseAnalyseService {
	
	/**
	 * 保存房源浏览分析对象
	 * @param house			房源对象
	 * @param member		会员对象
	 * @param clientFlag	客户端标志
	 */
	public void save(HouseRentHouse house, PlatMemberInfo member, String clientFlag);
}
