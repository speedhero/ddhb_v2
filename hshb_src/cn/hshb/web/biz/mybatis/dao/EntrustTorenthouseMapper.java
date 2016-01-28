package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.EntrustTorenthouse;
import cn.hshb.web.biz.mybatis.model.EntrustTorenthouseExample;
import cn.hshb.web.biz.mybatis.model.EntrustTorenthouseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EntrustTorenthouseMapper extends HshbBaseMapper<EntrustTorenthouse> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(EntrustTorenthouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(EntrustTorenthouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(EntrustTorenthouseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(EntrustTorenthouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(EntrustTorenthouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<EntrustTorenthouse> selectByExample(EntrustTorenthouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    EntrustTorenthouse selectByPrimaryKey(EntrustTorenthouseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") EntrustTorenthouse record, @Param("example") EntrustTorenthouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") EntrustTorenthouse record, @Param("example") EntrustTorenthouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(EntrustTorenthouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table entrust_torenthouse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(EntrustTorenthouse record);
}