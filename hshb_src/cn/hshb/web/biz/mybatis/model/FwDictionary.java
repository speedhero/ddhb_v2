package cn.hshb.web.biz.mybatis.model;

public class FwDictionary extends FwDictionaryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_dictionary.CATEGORY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_dictionary.NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_dictionary.CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_dictionary.ORDER_NUM
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String orderNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fw_dictionary.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_dictionary.CATEGORY_ID
     *
     * @return the value of fw_dictionary.CATEGORY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_dictionary.CATEGORY_ID
     *
     * @param categoryId the value for fw_dictionary.CATEGORY_ID
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_dictionary.NAME
     *
     * @return the value of fw_dictionary.NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_dictionary.NAME
     *
     * @param name the value for fw_dictionary.NAME
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_dictionary.CODE
     *
     * @return the value of fw_dictionary.CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_dictionary.CODE
     *
     * @param code the value for fw_dictionary.CODE
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_dictionary.ORDER_NUM
     *
     * @return the value of fw_dictionary.ORDER_NUM
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_dictionary.ORDER_NUM
     *
     * @param orderNum the value for fw_dictionary.ORDER_NUM
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fw_dictionary.STATUS
     *
     * @return the value of fw_dictionary.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fw_dictionary.STATUS
     *
     * @param status the value for fw_dictionary.STATUS
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}