package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategy;

/**
 * 问题策略
 * @author hejianbo
 *	2015年10月8日
 */
public interface CommonHousequestionStrategyService {
	
	/**
	 * 返回满足一级菜单类别的所有问题攻略
	 * @param commonHousequestStrategyTypeId
	 * @return
	 */
	List<CommonHousequestionStrategy> getAllData(String commonHousequestStrategyTypeId);
}
