package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyType;

/**
 * 房产类型与攻略类型
 * @author hejianbo
 *	2015年9月30日
 */
public interface CommonQuestionStrategyTypeService {
	
	/**
	 * 给出所有"房产类型与攻略类型 "数据
	 * @return
	 */
	List<CommonQuestionStrategyType> getAllData();
}
