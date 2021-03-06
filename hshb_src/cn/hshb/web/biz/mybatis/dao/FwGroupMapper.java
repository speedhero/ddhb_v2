package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.FwGroup;
import cn.hshb.web.biz.mybatis.model.FwGroupExample;
import cn.hshb.web.biz.mybatis.model.FwGroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FwGroupMapper extends HshbBaseMapper<FwGroup> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(FwGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(FwGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(FwGroupKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(FwGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(FwGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<FwGroup> selectByExample(FwGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    FwGroup selectByPrimaryKey(FwGroupKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") FwGroup record, @Param("example") FwGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") FwGroup record, @Param("example") FwGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(FwGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(FwGroup record);
}