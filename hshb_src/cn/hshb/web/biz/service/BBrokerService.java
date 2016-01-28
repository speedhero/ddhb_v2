/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.BBrokeranswered;
import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategy;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategySubtype;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyType;
import cn.hshb.web.biz.mybatis.model.HouseCbdExpert;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExpert;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;

/**
 * @author ShengYoufu
 *
 */
public interface BBrokerService {
	/**
	 * 根据ID查询经纪人信息
	 * @param brokerId	经纪人ID
	 * @return
	 */
	public BBroker getBroker(String brokerId);
	
	/**
	 * 根据经纪人查询经纪人熟悉的小区
	 * @param brokerId	经纪人ID
	 * @return
	 */
	public List<HouseCommunityExpert> getCommunityExpertByBrokerId(String brokerId);
	
	/**
	 * 根据经纪人ID查询经纪人熟悉的商圈
	 * @param brokerId	经纪人ID
	 * @return
	 */
	public List<HouseCbdExpert> getCBDExpertByBrokerId(String brokerId);
	
	/**
	 * 查询经纪人持有的二手房源量
	 * @param brokerId	经纪人ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<HouseSecondHandHouse> findHouseSecondByBrokerId(String brokerId, Integer pageSize, Integer pageNum);

	/**
	 * 根据经纪人ID查询经纪人持有的出租房源
	 * @param brokerId	经纪人ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<HouseRentHouse> findHouseRentByBrokerId(String brokerId, Integer pageSize, Integer pageNum);

	/**
	 * 根据经纪人ID查询经纪人回答的问题
	 * @param brokerId	经纪人ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<BBrokeranswered> getBrokerAnswered(String brokerId, Integer pageSize, Integer pageNum);

	/**
	 * 查询问题分类
	 * @return
	 */
	public List<CommonQuestionStrategyType> getQuestionStrategyType();


	/**
	 * 根据问答子类查询问题
	 * @param subTypeId		问答子类
	 * @param displayNum	查询记录数
	 * @return
	 */
	public List<CommonHousequestionStrategy> getQuestionStategyBySubType(String subTypeId, int displayNum);

	/**
	 * 根据问答子类和关键词查询问答
	 * @param subTypeId	问答子类
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @param keyWord	标题关键词
	 * @return
	 */
	public List<CommonHousequestionStrategy> getAllQuestionStategy(String subTypeId, Integer pageSize, Integer pageNum, String keyWord);

	/**
	 * 根据问答大类查询子类
	 * @param typeId	问答大类
	 * @return
	 */
	public List<CommonQuestionStrategySubtype> getAllQuestionStrategySubType(String typeId);

	/**
	 * 按问题类型统计问题数
	 * @param typeId	问题类型
	 * @return
	 */
	public List<Object> getQuestionStrategyCount(String typeId);

	/**
	 * 根据问题ID查询经纪的回答答案
	 * @param questionId	问题ID
	 * @return
	 */
	public List<BBrokeranswered> getBrokerAnswer(String questionId);

	/**
	 * 根据大类查询问答攻略
	 * @param typeId	大类ID
	 * @param rowCount	查询记录数
	 * @return
	 */
	public List<CommonHousequestionStrategy> getQuestionStategyByTypeId(String typeId, int rowCount);
	
	/**
	 * 统计问题总数
	 * @return
	 */
	public Integer getQuestionCount();

	/**
	 * 统计有回答问题的经纪人数量
	 * @return
	 */
	public Integer getAnseredBrokerCount();
	
	/**
	 * 查询问答攻略内容
	 * @param searchFileds	查询内容
	 * @param subtype		小类ID
	 * @param pageSize		每页记录数
	 * @param pageNum		当前页码
	 * @return
	 */
	public List<CommonHousequestionStrategy> searchQuestionStategy(String searchFileds, String subtype, Integer pageSize, Integer pageNum);
	
	
	/**
	 * 给经纪人主页设置随机背景图，设置后就保存到数据库中，不再改变
	 * @param broker
	 * @return
	 */
	public Boolean saveBackgroundImage(BBroker broker);	
	
	/**
	 * 根据经纪人ID查询已被接受的经纪人回答的问题数
	 * @param brokerId	经纪人ID
	 * @return
	 */
	public Integer getAcceptedAnswereCountByBrokerId(String brokerId);

	
	/**
	 * 根据经纪人ID获取经纪人回答的答案
	 * @param erpId
	 * @return
	 */
	public List<BBrokeranswered> getBrokerAnswerConditionBrokerErpId(String erpId);	
}
