package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.RecruitDepartment;
import cn.hshb.web.biz.mybatis.model.RecruitDepartmentExample;
import cn.hshb.web.biz.mybatis.model.RecruitDepartmentKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitDepartmentMapper extends HshbBaseMapper<RecruitDepartment> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(RecruitDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(RecruitDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(RecruitDepartmentKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(RecruitDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(RecruitDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<RecruitDepartment> selectByExample(RecruitDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    RecruitDepartment selectByPrimaryKey(RecruitDepartmentKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") RecruitDepartment record, @Param("example") RecruitDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") RecruitDepartment record, @Param("example") RecruitDepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(RecruitDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_department
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(RecruitDepartment record);
}