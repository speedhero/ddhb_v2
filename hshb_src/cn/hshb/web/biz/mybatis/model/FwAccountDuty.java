package cn.hshb.web.biz.mybatis.model;

public class FwAccountDuty extends FwAccountDutyKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_account_duty.DUTY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer dutyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_account_duty.ACCT_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer acctId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_account_duty.DUTY_ID
     *
     * @return the value of fw_account_duty.DUTY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDutyId() {
        return dutyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_account_duty.DUTY_ID
     *
     * @param dutyId the value for fw_account_duty.DUTY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_account_duty.ACCT_ID
     *
     * @return the value of fw_account_duty.ACCT_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getAcctId() {
        return acctId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_account_duty.ACCT_ID
     *
     * @param acctId the value for fw_account_duty.ACCT_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }
}