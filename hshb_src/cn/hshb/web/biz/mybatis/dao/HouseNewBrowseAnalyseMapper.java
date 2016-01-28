package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HouseNewBrowseAnalyse;
import cn.hshb.web.biz.mybatis.model.HouseNewBrowseAnalyseExample;
import cn.hshb.web.biz.mybatis.model.HouseNewBrowseAnalyseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseNewBrowseAnalyseMapper extends HshbBaseMapper<HouseNewBrowseAnalyse> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HouseNewBrowseAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HouseNewBrowseAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HouseNewBrowseAnalyseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HouseNewBrowseAnalyse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HouseNewBrowseAnalyse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HouseNewBrowseAnalyse> selectByExample(HouseNewBrowseAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HouseNewBrowseAnalyse selectByPrimaryKey(HouseNewBrowseAnalyseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HouseNewBrowseAnalyse record, @Param("example") HouseNewBrowseAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HouseNewBrowseAnalyse record, @Param("example") HouseNewBrowseAnalyseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HouseNewBrowseAnalyse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_new_browse_analyse
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HouseNewBrowseAnalyse record);
}