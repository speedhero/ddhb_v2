package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.HouseReduceNotice;
import cn.hshb.web.biz.mybatis.model.HouseReduceNoticeExample;
import cn.hshb.web.biz.mybatis.model.HouseReduceNoticeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseReduceNoticeMapper extends HshbBaseMapper<HouseReduceNotice> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(HouseReduceNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(HouseReduceNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(HouseReduceNoticeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(HouseReduceNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(HouseReduceNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<HouseReduceNotice> selectByExample(HouseReduceNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    HouseReduceNotice selectByPrimaryKey(HouseReduceNoticeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") HouseReduceNotice record, @Param("example") HouseReduceNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") HouseReduceNotice record, @Param("example") HouseReduceNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(HouseReduceNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_reduce_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(HouseReduceNotice record);

    
    /******************以下自定义方法**************/
    /**
     * 查询需要发送降价通知的总数
     * @return
     */
    int selectNeedSendNoticeCount();

}