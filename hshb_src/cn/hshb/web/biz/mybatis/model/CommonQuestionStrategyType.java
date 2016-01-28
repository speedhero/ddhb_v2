package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class CommonQuestionStrategyType extends CommonQuestionStrategyTypeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_question_strategy_type.type_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String typeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_question_strategy_type.icon_url
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String iconUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_question_strategy_type.hover_icon_url
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String hoverIconUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_question_strategy_type.create_time
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_question_strategy_type.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_question_strategy_type.type_name
     *
     * @return the value of common_question_strategy_type.type_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_question_strategy_type.type_name
     *
     * @param typeName the value for common_question_strategy_type.type_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_question_strategy_type.icon_url
     *
     * @return the value of common_question_strategy_type.icon_url
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_question_strategy_type.icon_url
     *
     * @param iconUrl the value for common_question_strategy_type.icon_url
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_question_strategy_type.hover_icon_url
     *
     * @return the value of common_question_strategy_type.hover_icon_url
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getHoverIconUrl() {
        return hoverIconUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_question_strategy_type.hover_icon_url
     *
     * @param hoverIconUrl the value for common_question_strategy_type.hover_icon_url
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setHoverIconUrl(String hoverIconUrl) {
        this.hoverIconUrl = hoverIconUrl == null ? null : hoverIconUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_question_strategy_type.create_time
     *
     * @return the value of common_question_strategy_type.create_time
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_question_strategy_type.create_time
     *
     * @param createTime the value for common_question_strategy_type.create_time
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_question_strategy_type.deleteflag
     *
     * @return the value of common_question_strategy_type.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_question_strategy_type.deleteflag
     *
     * @param deleteflag the value for common_question_strategy_type.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}