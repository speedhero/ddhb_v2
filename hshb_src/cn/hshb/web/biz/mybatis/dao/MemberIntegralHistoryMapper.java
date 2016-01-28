package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.MemberIntegralHistory;
import cn.hshb.web.biz.mybatis.model.MemberIntegralHistoryExample;
import cn.hshb.web.biz.mybatis.model.MemberIntegralHistoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberIntegralHistoryMapper extends HshbBaseMapper<MemberIntegralHistory> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(MemberIntegralHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(MemberIntegralHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(MemberIntegralHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(MemberIntegralHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(MemberIntegralHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<MemberIntegralHistory> selectByExample(MemberIntegralHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    MemberIntegralHistory selectByPrimaryKey(MemberIntegralHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") MemberIntegralHistory record, @Param("example") MemberIntegralHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") MemberIntegralHistory record, @Param("example") MemberIntegralHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(MemberIntegralHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral_history
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(MemberIntegralHistory record);
}