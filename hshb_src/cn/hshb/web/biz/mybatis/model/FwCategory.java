package cn.hshb.web.biz.mybatis.model;

public class FwCategory extends FwCategoryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_category.NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_category.CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_category.DISPLAY
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String display;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_category.SYS_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer sysId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_category.NAME
     *
     * @return the value of fw_category.NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_category.NAME
     *
     * @param name the value for fw_category.NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_category.CODE
     *
     * @return the value of fw_category.CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_category.CODE
     *
     * @param code the value for fw_category.CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_category.DISPLAY
     *
     * @return the value of fw_category.DISPLAY
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getDisplay() {
        return display;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_category.DISPLAY
     *
     * @param display the value for fw_category.DISPLAY
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_category.SYS_ID
     *
     * @return the value of fw_category.SYS_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_category.SYS_ID
     *
     * @param sysId the value for fw_category.SYS_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }
}