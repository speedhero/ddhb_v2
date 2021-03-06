package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HouseCbdExpert;
import cn.hshb.web.biz.mybatis.model.HouseCbdExpertExample;
import cn.hshb.web.biz.mybatis.model.HouseCbdExpertKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseCbdExpertMapper extends HshbBaseMapper<HouseCbdExpert> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HouseCbdExpertExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HouseCbdExpertExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HouseCbdExpertKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HouseCbdExpert record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HouseCbdExpert record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HouseCbdExpert> selectByExample(HouseCbdExpertExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HouseCbdExpert selectByPrimaryKey(HouseCbdExpertKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HouseCbdExpert record, @Param("example") HouseCbdExpertExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HouseCbdExpert record, @Param("example") HouseCbdExpertExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HouseCbdExpert record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_cbd_expert
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HouseCbdExpert record);
    
    /*自定义*/
    /**
     * 根据经纪人ID查询经纪人熟悉的商圈
     * @return
     */
    List<HouseCbdExpert> selectCbdExpertByBorkerErpId(String brokerId);
}