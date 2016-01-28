/**
 * 
 */
package cn.hshb.web.biz.service;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;

/**
 * @author ShengYoufu
 *
 */
public interface HouseSecondBrowseAnalyseService {
	
	/**
	 * 保存房源浏览分析对象
	 * @param house			房源对象
	 * @param member		会员对象
	 * @param clientFlag	客户端标志
	 */
	public void save(HouseSecondHandHouse house, PlatMemberInfo member, String clientFlag);
}
