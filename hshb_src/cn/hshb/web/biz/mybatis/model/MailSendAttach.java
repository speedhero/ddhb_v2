package cn.hshb.web.biz.mybatis.model;

public class MailSendAttach extends MailSendAttachKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mail_send_attach.send_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer sendId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mail_send_attach.file_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mail_send_attach.file_path
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String filePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mail_send_attach.is_local
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer isLocal;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mail_send_attach.send_id
     *
     * @return the value of mail_send_attach.send_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getSendId() {
        return sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mail_send_attach.send_id
     *
     * @param sendId the value for mail_send_attach.send_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mail_send_attach.file_name
     *
     * @return the value of mail_send_attach.file_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mail_send_attach.file_name
     *
     * @param fileName the value for mail_send_attach.file_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mail_send_attach.file_path
     *
     * @return the value of mail_send_attach.file_path
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mail_send_attach.file_path
     *
     * @param filePath the value for mail_send_attach.file_path
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mail_send_attach.is_local
     *
     * @return the value of mail_send_attach.is_local
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getIsLocal() {
        return isLocal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mail_send_attach.is_local
     *
     * @param isLocal the value for mail_send_attach.is_local
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setIsLocal(Integer isLocal) {
        this.isLocal = isLocal;
    }
}