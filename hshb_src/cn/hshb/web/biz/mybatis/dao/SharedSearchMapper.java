package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.SharedSearch;
import cn.hshb.web.biz.mybatis.model.SharedSearchExample;
import cn.hshb.web.biz.mybatis.model.SharedSearchKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SharedSearchMapper extends HshbBaseMapper<SharedSearch> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(SharedSearchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(SharedSearchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(SharedSearchKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(SharedSearch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(SharedSearch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<SharedSearch> selectByExample(SharedSearchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    SharedSearch selectByPrimaryKey(SharedSearchKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") SharedSearch record, @Param("example") SharedSearchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") SharedSearch record, @Param("example") SharedSearchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(SharedSearch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(SharedSearch record);
}