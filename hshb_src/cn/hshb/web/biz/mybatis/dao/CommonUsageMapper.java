package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonUsage;
import cn.hshb.web.biz.mybatis.model.CommonUsageExample;
import cn.hshb.web.biz.mybatis.model.CommonUsageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonUsageMapper extends HshbBaseMapper<CommonUsage> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonUsageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonUsageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonUsageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonUsage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonUsage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonUsage> selectByExample(CommonUsageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonUsage selectByPrimaryKey(CommonUsageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonUsage record, @Param("example") CommonUsageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonUsage record, @Param("example") CommonUsageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonUsage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_usage
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonUsage record);
}