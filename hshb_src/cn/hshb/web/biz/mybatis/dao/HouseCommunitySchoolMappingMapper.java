package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMapping;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMappingExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunitySchoolMappingKey;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HouseCommunitySchoolMappingMapper extends HshbBaseMapper<HouseCommunitySchoolMapping> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HouseCommunitySchoolMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HouseCommunitySchoolMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HouseCommunitySchoolMappingKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HouseCommunitySchoolMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HouseCommunitySchoolMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HouseCommunitySchoolMapping> selectByExample(HouseCommunitySchoolMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HouseCommunitySchoolMapping selectByPrimaryKey(HouseCommunitySchoolMappingKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HouseCommunitySchoolMapping record, @Param("example") HouseCommunitySchoolMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HouseCommunitySchoolMapping record, @Param("example") HouseCommunitySchoolMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HouseCommunitySchoolMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_school_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HouseCommunitySchoolMapping record);
    
    List<HouseCommunitySchoolMapping> selectAssociateByMap(Map<String, String> map);
}