package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitem;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitemExample;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitemKey;
import cn.hshb.web.biz.mybatis.model.CommonAdandactivityitemWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonAdandactivityitemMapper extends HshbBaseMapper<CommonAdandactivityitem> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(CommonAdandactivityitemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(CommonAdandactivityitemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(CommonAdandactivityitemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonAdandactivityitemWithBLOBs> selectByExampleWithBLOBs(CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<CommonAdandactivityitem> selectByExample(CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    CommonAdandactivityitemWithBLOBs selectByPrimaryKey(CommonAdandactivityitemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") CommonAdandactivityitemWithBLOBs record, @Param("example") CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CommonAdandactivityitemWithBLOBs record, @Param("example") CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") CommonAdandactivityitem record, @Param("example") CommonAdandactivityitemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(CommonAdandactivityitemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeyWithBLOBs(CommonAdandactivityitemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivityitem
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(CommonAdandactivityitem record);
    
    
    /**********************************以下为自定义方法****************************/
    /**
     * 广告点击量加1 
     * @param itemId
     * @return
     */
    int addBrowsedByItemId(Integer itemId);
}