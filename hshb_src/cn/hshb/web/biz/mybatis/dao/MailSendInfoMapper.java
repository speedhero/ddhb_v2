package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.MailSendInfo;
import cn.hshb.web.biz.mybatis.model.MailSendInfoExample;
import cn.hshb.web.biz.mybatis.model.MailSendInfoKey;
import cn.hshb.web.biz.mybatis.model.MailSendInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailSendInfoMapper extends HshbBaseMapper<MailSendInfo> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(MailSendInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(MailSendInfoWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(MailSendInfoWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<MailSendInfoWithBLOBs> selectByExampleWithBLOBs(MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<MailSendInfo> selectByExample(MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    MailSendInfoWithBLOBs selectByPrimaryKey(MailSendInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") MailSendInfoWithBLOBs record, @Param("example") MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleWithBLOBs(@Param("record") MailSendInfoWithBLOBs record, @Param("example") MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") MailSendInfo record, @Param("example") MailSendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(MailSendInfoWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeyWithBLOBs(MailSendInfoWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(MailSendInfo record);
}