package cn.hshb.web.biz.mybatis.model;

public class CommonDecorationType extends CommonDecorationTypeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_decoration_type.decoration_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String decorationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_decoration_type.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_decoration_type.decoration_name
     *
     * @return the value of common_decoration_type.decoration_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getDecorationName() {
        return decorationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_decoration_type.decoration_name
     *
     * @param decorationName the value for common_decoration_type.decoration_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDecorationName(String decorationName) {
        this.decorationName = decorationName == null ? null : decorationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_decoration_type.deleteflag
     *
     * @return the value of common_decoration_type.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_decoration_type.deleteflag
     *
     * @param deleteflag the value for common_decoration_type.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}