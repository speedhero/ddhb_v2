package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class FwForbiddenIp extends FwForbiddenIpKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_forbidden_ip.ip_address
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String ipAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_forbidden_ip.create_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_forbidden_ip.delete_flag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_forbidden_ip.ip_address
     *
     * @return the value of fw_forbidden_ip.ip_address
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_forbidden_ip.ip_address
     *
     * @param ipAddress the value for fw_forbidden_ip.ip_address
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_forbidden_ip.create_time
     *
     * @return the value of fw_forbidden_ip.create_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_forbidden_ip.create_time
     *
     * @param createTime the value for fw_forbidden_ip.create_time
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_forbidden_ip.delete_flag
     *
     * @return the value of fw_forbidden_ip.delete_flag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_forbidden_ip.delete_flag
     *
     * @param deleteFlag the value for fw_forbidden_ip.delete_flag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}