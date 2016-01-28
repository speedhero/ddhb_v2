package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.EfwApplyScope;
import cn.hshb.web.biz.mybatis.model.EfwApplyScopeExample;
import cn.hshb.web.biz.mybatis.model.EfwApplyScopeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EfwApplyScopeMapper extends HshbBaseMapper<EfwApplyScope> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(EfwApplyScopeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(EfwApplyScopeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(EfwApplyScopeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(EfwApplyScope record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(EfwApplyScope record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<EfwApplyScope> selectByExample(EfwApplyScopeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    EfwApplyScope selectByPrimaryKey(EfwApplyScopeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") EfwApplyScope record, @Param("example") EfwApplyScopeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") EfwApplyScope record, @Param("example") EfwApplyScopeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(EfwApplyScope record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table efw_apply_scope
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(EfwApplyScope record);
}