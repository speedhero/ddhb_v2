package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.BBrokerExample;
import cn.hshb.web.biz.mybatis.model.BBrokerKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBrokerMapper extends HshbBaseMapper<BBroker> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int countByExample(BBrokerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByExample(BBrokerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int deleteByPrimaryKey(BBrokerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insert(BBroker record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int insertSelective(BBroker record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    List<BBroker> selectByExample(BBrokerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    BBroker selectByPrimaryKey(BBrokerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") BBroker record, @Param("example") BBrokerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByExample(@Param("record") BBroker record, @Param("example") BBrokerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(BBroker record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_broker
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    int updateByPrimaryKey(BBroker record);
    
    /**
     * 根据 经纪人Id查找经纪人信息及门店信息
     * @param brokerId
     * @return
     */
    BBroker selectAssociateByBrokerId(String brokerId);
}