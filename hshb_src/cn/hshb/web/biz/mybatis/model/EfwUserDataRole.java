package cn.hshb.web.biz.mybatis.model;

public class EfwUserDataRole extends EfwUserDataRoleKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_user_data_role.DR_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer drId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_user_data_role.USER_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_user_data_role.DR_ID
     *
     * @return the value of efw_user_data_role.DR_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getDrId() {
        return drId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_user_data_role.DR_ID
     *
     * @param drId the value for efw_user_data_role.DR_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_user_data_role.USER_ID
     *
     * @return the value of efw_user_data_role.USER_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_user_data_role.USER_ID
     *
     * @param userId the value for efw_user_data_role.USER_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}