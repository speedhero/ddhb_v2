package cn.hshb.web.biz.mybatis.model;

public class FwDutyAction extends FwDutyActionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty_action.ACTION_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer actionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_duty_action.DUTY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer dutyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty_action.ACTION_ID
     *
     * @return the value of fw_duty_action.ACTION_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getActionId() {
        return actionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty_action.ACTION_ID
     *
     * @param actionId the value for fw_duty_action.ACTION_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_duty_action.DUTY_ID
     *
     * @return the value of fw_duty_action.DUTY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDutyId() {
        return dutyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_duty_action.DUTY_ID
     *
     * @param dutyId the value for fw_duty_action.DUTY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }
}