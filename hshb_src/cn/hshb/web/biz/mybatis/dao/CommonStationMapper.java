package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonStation;
import cn.hshb.web.biz.mybatis.model.CommonStationExample;
import cn.hshb.web.biz.mybatis.model.CommonStationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonStationMapper extends HshbBaseMapper<CommonStation> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonStationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonStation> selectByExample(CommonStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonStation selectByPrimaryKey(CommonStationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonStation record, @Param("example") CommonStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonStation record, @Param("example") CommonStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonStation record);
}