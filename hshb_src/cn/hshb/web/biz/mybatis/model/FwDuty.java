package cn.hshb.web.biz.mybatis.model;

public class FwDuty extends FwDutyKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty.GROUP_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty.DUTY_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String dutyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty.DUTY_TYPE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String dutyType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty.GROUP_ID
     *
     * @return the value of fw_duty.GROUP_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty.GROUP_ID
     *
     * @param groupId the value for fw_duty.GROUP_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty.DUTY_NAME
     *
     * @return the value of fw_duty.DUTY_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getDutyName() {
        return dutyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty.DUTY_NAME
     *
     * @param dutyName the value for fw_duty.DUTY_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDutyName(String dutyName) {
        this.dutyName = dutyName == null ? null : dutyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty.STATUS
     *
     * @return the value of fw_duty.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty.STATUS
     *
     * @param status the value for fw_duty.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty.DESCRIPTION
     *
     * @return the value of fw_duty.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty.DESCRIPTION
     *
     * @param description the value for fw_duty.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty.DUTY_TYPE
     *
     * @return the value of fw_duty.DUTY_TYPE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getDutyType() {
        return dutyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty.DUTY_TYPE
     *
     * @param dutyType the value for fw_duty.DUTY_TYPE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDutyType(String dutyType) {
        this.dutyType = dutyType == null ? null : dutyType.trim();
    }
}