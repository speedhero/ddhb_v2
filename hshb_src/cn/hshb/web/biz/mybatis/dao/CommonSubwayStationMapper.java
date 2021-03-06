package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonSubwayStation;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStationExample;
import cn.hshb.web.biz.mybatis.model.CommonSubwayStationKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CommonSubwayStationMapper extends HshbBaseMapper<CommonSubwayStation> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonSubwayStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonSubwayStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonSubwayStationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonSubwayStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonSubwayStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonSubwayStation> selectByExample(CommonSubwayStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonSubwayStation selectByPrimaryKey(CommonSubwayStationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonSubwayStation record, @Param("example") CommonSubwayStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonSubwayStation record, @Param("example") CommonSubwayStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonSubwayStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_subway_station
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonSubwayStation record);
    
    /* 自定义 */
    /**
     * 根据地铁号线搜索所属的站点
     * @return
     */
    List<CommonSubwayStation> selectBySubwayStation(String subwayErpId);
    
    /**
     * 查询所有的地铁站点
     * @return
     */
    List<CommonSubwayStation> selectAllSubwayStation();
}