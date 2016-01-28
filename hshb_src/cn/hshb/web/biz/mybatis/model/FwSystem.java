package cn.hshb.web.biz.mybatis.model;

public class FwSystem extends FwSystemKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.SYS_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String sysName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.SYS_IP
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String sysIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.SYS_CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String sysCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.SYS_PORT
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer sysPort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_system.CONTEXT_PATH
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String contextPath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.SYS_NAME
     *
     * @return the value of fw_system.SYS_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.SYS_NAME
     *
     * @param sysName the value for fw_system.SYS_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.STATUS
     *
     * @return the value of fw_system.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.STATUS
     *
     * @param status the value for fw_system.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.DESCRIPTION
     *
     * @return the value of fw_system.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.DESCRIPTION
     *
     * @param description the value for fw_system.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.SYS_IP
     *
     * @return the value of fw_system.SYS_IP
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSysIp() {
        return sysIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.SYS_IP
     *
     * @param sysIp the value for fw_system.SYS_IP
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSysIp(String sysIp) {
        this.sysIp = sysIp == null ? null : sysIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.SYS_CODE
     *
     * @return the value of fw_system.SYS_CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.SYS_CODE
     *
     * @param sysCode the value for fw_system.SYS_CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.SYS_PORT
     *
     * @return the value of fw_system.SYS_PORT
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getSysPort() {
        return sysPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.SYS_PORT
     *
     * @param sysPort the value for fw_system.SYS_PORT
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSysPort(Integer sysPort) {
        this.sysPort = sysPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_system.CONTEXT_PATH
     *
     * @return the value of fw_system.CONTEXT_PATH
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getContextPath() {
        return contextPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_system.CONTEXT_PATH
     *
     * @param contextPath the value for fw_system.CONTEXT_PATH
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath == null ? null : contextPath.trim();
    }
}