package com.huatek.hbwebsite.broker.service;

import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.common.entity.BroderAnsered;
import com.huatek.hbwebsite.common.entity.CBDExport;
import com.huatek.hbwebsite.common.entity.CommunityExpert;
import com.huatek.hbwebsite.common.entity.PageAccessQuantity;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import com.huatek.hbwebsite.common.entity.QuestionStrategySubtype;
import com.huatek.hbwebsite.common.entity.QuestionStrategyType;
import com.huatek.hbwebsite.service.BaseServiceTo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BrokerService extends BaseServiceTo {
	CutPageBean findHouseSecondByBrokerId(String var1, CutPageBean var2, List<CommonBean> var3);

	CutPageBean findHouseRentByBrokerId(String var1, CutPageBean var2, List<CommonBean> var3);

	CutPageBean getBrokerAnswered(String var1, CutPageBean var2, List<CommonBean> var3);

	List<QuestionStrategyType> getQuestionStrategyType();

	CutPageBean getSearchFiledList(CutPageBean var1, Map var2, Map<String, List> var3, Set<String> var4, String var5,
			String var6, String var7, String var8, int var9);

	List<QuestionStategy> getQuestionStategyBySubType(String var1, int var2);

	List<QuestionStategy> getQuestionStategyByTypeId(String var1, int var2);

	CutPageBean getAllQuestionStategy(String var1, CutPageBean var2, List<CommonBean> var3, String var4);

	List<QuestionStrategySubtype> getAllQuestionStrategySubType(String var1);

	List<Object> getQuestionStrategyCount(String var1);

	List<BroderAnsered> getBrokerAnswer(String var1);

	List<Object> getAllQuestionWithOutType();

	List<Object> getAllBrokerWithOutType();

	CutPageBean searchQuestionStategy(CutPageBean var1, String var2, String var3, List<CommonBean> var4);

	List<CommunityExpert> getCommunityByBrokerId(String var1);

	int getBrokerAnsweredIsAccept(String var1);

	List<CBDExport> getCBDExportByBrokerId(String var1);
	
	/**
	 * 返回满足一级菜单类别的所有问题攻略
	 * @param questionStrategyType
	 * @return
	 */
	List<QuestionStategy> getAllQuestionStategy(String questionStrategyType);
	/**
	 * 根据经纪人的ErpId 查处所有该经纪人回答的信息
	 * @param erpId
	 * @return
	 */
	List<BroderAnsered> getBrokerAnswerConditionBrokerErpId(String erpId);
	
	/**
	 * 返回页面访问的数量
	 * @param erpId 经纪人Id
	 * @return
	 */
	PageAccessQuantity getAccessQuantity(String erpId);
	
	/**
	 * 保存经纪人详情页背景图，不替换已有经纪人背景图数据
	 * @param broker
	 */
	void saveBackgroundImage(Broker broker);
}
