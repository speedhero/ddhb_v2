package cn.hshb.web.biz.mybatis.model;

public class FwSchedule extends FwScheduleKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.job_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String jobName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.job_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String jobGroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.job_status
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer jobStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.cron_expression
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String cronExpression;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.memos
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String memos;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.is_synch
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer isSynch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.execute_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer executeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.params
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String params;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.execute_time_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer executeTimeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_schedule.execute_time_value
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer executeTimeValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.job_name
     *
     * @return the value of fw_schedule.job_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.job_name
     *
     * @param jobName the value for fw_schedule.job_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.job_group
     *
     * @return the value of fw_schedule.job_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.job_group
     *
     * @param jobGroup the value for fw_schedule.job_group
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.job_status
     *
     * @return the value of fw_schedule.job_status
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getJobStatus() {
        return jobStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.job_status
     *
     * @param jobStatus the value for fw_schedule.job_status
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.cron_expression
     *
     * @return the value of fw_schedule.cron_expression
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.cron_expression
     *
     * @param cronExpression the value for fw_schedule.cron_expression
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.memos
     *
     * @return the value of fw_schedule.memos
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getMemos() {
        return memos;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.memos
     *
     * @param memos the value for fw_schedule.memos
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setMemos(String memos) {
        this.memos = memos == null ? null : memos.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.is_synch
     *
     * @return the value of fw_schedule.is_synch
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getIsSynch() {
        return isSynch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.is_synch
     *
     * @param isSynch the value for fw_schedule.is_synch
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setIsSynch(Integer isSynch) {
        this.isSynch = isSynch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.execute_type
     *
     * @return the value of fw_schedule.execute_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getExecuteType() {
        return executeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.execute_type
     *
     * @param executeType the value for fw_schedule.execute_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.params
     *
     * @return the value of fw_schedule.params
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getParams() {
        return params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.params
     *
     * @param params the value for fw_schedule.params
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.comment
     *
     * @return the value of fw_schedule.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.comment
     *
     * @param comment the value for fw_schedule.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.execute_time_type
     *
     * @return the value of fw_schedule.execute_time_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getExecuteTimeType() {
        return executeTimeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.execute_time_type
     *
     * @param executeTimeType the value for fw_schedule.execute_time_type
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setExecuteTimeType(Integer executeTimeType) {
        this.executeTimeType = executeTimeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_schedule.execute_time_value
     *
     * @return the value of fw_schedule.execute_time_value
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getExecuteTimeValue() {
        return executeTimeValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_schedule.execute_time_value
     *
     * @param executeTimeValue the value for fw_schedule.execute_time_value
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setExecuteTimeValue(Integer executeTimeValue) {
        this.executeTimeValue = executeTimeValue;
    }
}