package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.FwSystem;
import cn.hshb.web.biz.mybatis.model.FwSystemExample;
import cn.hshb.web.biz.mybatis.model.FwSystemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FwSystemMapper extends HshbBaseMapper<FwSystem> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(FwSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(FwSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(FwSystemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(FwSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(FwSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<FwSystem> selectByExample(FwSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    FwSystem selectByPrimaryKey(FwSystemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") FwSystem record, @Param("example") FwSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") FwSystem record, @Param("example") FwSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(FwSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(FwSystem record);
}