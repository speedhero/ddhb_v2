package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonStore;
import cn.hshb.web.biz.mybatis.model.CommonStoreExample;
import cn.hshb.web.biz.mybatis.model.CommonStoreKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonStoreMapper extends HshbBaseMapper<CommonStore> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonStoreKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonStore> selectByExample(CommonStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonStore selectByPrimaryKey(CommonStoreKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonStore record, @Param("example") CommonStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonStore record, @Param("example") CommonStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonStore record);
}