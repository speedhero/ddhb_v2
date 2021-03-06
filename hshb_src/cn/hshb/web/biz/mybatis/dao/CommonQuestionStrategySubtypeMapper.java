package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategySubtype;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategySubtypeExample;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategySubtypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonQuestionStrategySubtypeMapper extends HshbBaseMapper<CommonQuestionStrategySubtype> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonQuestionStrategySubtypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonQuestionStrategySubtypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonQuestionStrategySubtypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonQuestionStrategySubtype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonQuestionStrategySubtype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonQuestionStrategySubtype> selectByExample(CommonQuestionStrategySubtypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonQuestionStrategySubtype selectByPrimaryKey(CommonQuestionStrategySubtypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonQuestionStrategySubtype record, @Param("example") CommonQuestionStrategySubtypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonQuestionStrategySubtype record, @Param("example") CommonQuestionStrategySubtypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonQuestionStrategySubtype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_question_strategy_subtype
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonQuestionStrategySubtype record);
}