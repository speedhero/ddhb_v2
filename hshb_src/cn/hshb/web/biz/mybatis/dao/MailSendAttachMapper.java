package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.MailSendAttach;
import cn.hshb.web.biz.mybatis.model.MailSendAttachExample;
import cn.hshb.web.biz.mybatis.model.MailSendAttachKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailSendAttachMapper extends HshbBaseMapper<MailSendAttach> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(MailSendAttachExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(MailSendAttachExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(MailSendAttachKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(MailSendAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(MailSendAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<MailSendAttach> selectByExample(MailSendAttachExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    MailSendAttach selectByPrimaryKey(MailSendAttachKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") MailSendAttach record, @Param("example") MailSendAttachExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") MailSendAttach record, @Param("example") MailSendAttachExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(MailSendAttach record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_attach
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(MailSendAttach record);
}