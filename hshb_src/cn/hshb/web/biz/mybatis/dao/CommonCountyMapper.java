package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonCounty;
import cn.hshb.web.biz.mybatis.model.CommonCountyExample;
import cn.hshb.web.biz.mybatis.model.CommonCountyKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonCountyMapper extends HshbBaseMapper<CommonCounty> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonCountyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonCountyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonCountyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonCounty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonCounty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonCounty> selectByExample(CommonCountyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonCounty selectByPrimaryKey(CommonCountyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonCounty record, @Param("example") CommonCountyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonCounty record, @Param("example") CommonCountyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonCounty record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_county
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonCounty record);
    
    /** 自定义 **/
    
    List<CommonCounty> selectByCounty(); 
    
    /**
     * 根据城区Id获取城区
     * @param erpId
     * @return
     */
    CommonCounty selectCountyIdbyCounty(String erpId);
}