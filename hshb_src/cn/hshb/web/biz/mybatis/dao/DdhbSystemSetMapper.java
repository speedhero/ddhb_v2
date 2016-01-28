package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.DdhbSystemSet;
import cn.hshb.web.biz.mybatis.model.DdhbSystemSetExample;
import cn.hshb.web.biz.mybatis.model.DdhbSystemSetKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DdhbSystemSetMapper extends HshbBaseMapper<DdhbSystemSet> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(DdhbSystemSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(DdhbSystemSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(DdhbSystemSetKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(DdhbSystemSet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(DdhbSystemSet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<DdhbSystemSet> selectByExample(DdhbSystemSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    DdhbSystemSet selectByPrimaryKey(DdhbSystemSetKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") DdhbSystemSet record, @Param("example") DdhbSystemSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") DdhbSystemSet record, @Param("example") DdhbSystemSetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(DdhbSystemSet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ddhb_system_set
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(DdhbSystemSet record);
}