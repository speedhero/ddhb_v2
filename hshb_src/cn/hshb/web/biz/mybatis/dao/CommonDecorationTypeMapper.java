package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonDecorationType;
import cn.hshb.web.biz.mybatis.model.CommonDecorationTypeExample;
import cn.hshb.web.biz.mybatis.model.CommonDecorationTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonDecorationTypeMapper extends HshbBaseMapper<CommonDecorationType> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonDecorationTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonDecorationTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonDecorationTypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonDecorationType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonDecorationType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonDecorationType> selectByExample(CommonDecorationTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonDecorationType selectByPrimaryKey(CommonDecorationTypeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonDecorationType record, @Param("example") CommonDecorationTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonDecorationType record, @Param("example") CommonDecorationTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonDecorationType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_decoration_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonDecorationType record);
}