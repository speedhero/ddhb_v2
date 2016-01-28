package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HouseSecondPriceTrend;
import cn.hshb.web.biz.mybatis.model.HouseSecondPriceTrendExample;
import cn.hshb.web.biz.mybatis.model.HouseSecondPriceTrendKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseSecondPriceTrendMapper extends HshbBaseMapper<HouseSecondPriceTrend> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HouseSecondPriceTrendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HouseSecondPriceTrendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HouseSecondPriceTrendKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HouseSecondPriceTrend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HouseSecondPriceTrend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HouseSecondPriceTrend> selectByExample(HouseSecondPriceTrendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HouseSecondPriceTrend selectByPrimaryKey(HouseSecondPriceTrendKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HouseSecondPriceTrend record, @Param("example") HouseSecondPriceTrendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HouseSecondPriceTrend record, @Param("example") HouseSecondPriceTrendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HouseSecondPriceTrend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_second_price_trend
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HouseSecondPriceTrend record);
}