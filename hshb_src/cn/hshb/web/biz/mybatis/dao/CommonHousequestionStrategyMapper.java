package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategy;
import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategyExample;
import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategyKey;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CommonHousequestionStrategyMapper extends HshbBaseMapper<CommonHousequestionStrategy> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonHousequestionStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonHousequestionStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonHousequestionStrategyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonHousequestionStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonHousequestionStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonHousequestionStrategy> selectByExample(CommonHousequestionStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonHousequestionStrategy selectByPrimaryKey(CommonHousequestionStrategyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonHousequestionStrategy record, @Param("example") CommonHousequestionStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonHousequestionStrategy record, @Param("example") CommonHousequestionStrategyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonHousequestionStrategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_housequestion_strategy
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonHousequestionStrategy record);
    
    /* 自定义 */
    /**
     * 返回满足一级菜单类别的所有问题攻略
     * @param StrategyTypeId
     * @return
     */
    List<CommonHousequestionStrategy> selectDataByStrategyTypeId(Map<String, Object> StrategyTypeId);
    
}