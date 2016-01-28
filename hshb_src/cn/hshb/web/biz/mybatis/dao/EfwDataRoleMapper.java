package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.EfwDataRole;
import cn.hshb.web.biz.mybatis.model.EfwDataRoleExample;
import cn.hshb.web.biz.mybatis.model.EfwDataRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EfwDataRoleMapper extends HshbBaseMapper<EfwDataRole> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(EfwDataRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(EfwDataRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(EfwDataRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(EfwDataRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(EfwDataRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<EfwDataRole> selectByExample(EfwDataRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    EfwDataRole selectByPrimaryKey(EfwDataRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") EfwDataRole record, @Param("example") EfwDataRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") EfwDataRole record, @Param("example") EfwDataRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(EfwDataRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_data_role
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(EfwDataRole record);
}