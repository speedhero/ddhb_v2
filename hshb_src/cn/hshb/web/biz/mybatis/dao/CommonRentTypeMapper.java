package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonRentType;
import cn.hshb.web.biz.mybatis.model.CommonRentTypeExample;
import cn.hshb.web.biz.mybatis.model.CommonRentTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonRentTypeMapper extends HshbBaseMapper<CommonRentType> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonRentTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonRentTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonRentTypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonRentType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonRentType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonRentType> selectByExample(CommonRentTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonRentType selectByPrimaryKey(CommonRentTypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonRentType record, @Param("example") CommonRentTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonRentType record, @Param("example") CommonRentTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonRentType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonRentType record);
}