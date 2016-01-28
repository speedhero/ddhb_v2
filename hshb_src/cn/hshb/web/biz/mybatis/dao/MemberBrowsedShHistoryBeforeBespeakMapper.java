package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.MemberBrowsedShHistoryBeforeBespeak;
import cn.hshb.web.biz.mybatis.model.MemberBrowsedShHistoryBeforeBespeakExample;
import cn.hshb.web.biz.mybatis.model.MemberBrowsedShHistoryBeforeBespeakKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberBrowsedShHistoryBeforeBespeakMapper extends HshbBaseMapper<MemberBrowsedShHistoryBeforeBespeak> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(MemberBrowsedShHistoryBeforeBespeakExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(MemberBrowsedShHistoryBeforeBespeakExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(MemberBrowsedShHistoryBeforeBespeakKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(MemberBrowsedShHistoryBeforeBespeak record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(MemberBrowsedShHistoryBeforeBespeak record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<MemberBrowsedShHistoryBeforeBespeak> selectByExample(MemberBrowsedShHistoryBeforeBespeakExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    MemberBrowsedShHistoryBeforeBespeak selectByPrimaryKey(MemberBrowsedShHistoryBeforeBespeakKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") MemberBrowsedShHistoryBeforeBespeak record, @Param("example") MemberBrowsedShHistoryBeforeBespeakExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") MemberBrowsedShHistoryBeforeBespeak record, @Param("example") MemberBrowsedShHistoryBeforeBespeakExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(MemberBrowsedShHistoryBeforeBespeak record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_browsed_sh_history_before_bespeak
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(MemberBrowsedShHistoryBeforeBespeak record);
}