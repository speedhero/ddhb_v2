package cn.hshb.web.biz.mybatis.model;

public class EfwDataRole extends EfwDataRoleKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_data_role.DR_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String drName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_data_role.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_data_role.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_data_role.DR_NAME
     *
     * @return the value of efw_data_role.DR_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getDrName() {
        return drName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_data_role.DR_NAME
     *
     * @param drName the value for efw_data_role.DR_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDrName(String drName) {
        this.drName = drName == null ? null : drName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_data_role.STATUS
     *
     * @return the value of efw_data_role.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_data_role.STATUS
     *
     * @param status the value for efw_data_role.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_data_role.DESCRIPTION
     *
     * @return the value of efw_data_role.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_data_role.DESCRIPTION
     *
     * @param description the value for efw_data_role.DESCRIPTION
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}