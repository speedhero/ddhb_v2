package cn.hshb.web.biz.mybatis.model;

public class MemberIntegrateRule extends MemberIntegrateRuleKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integrate_rule.rule_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String ruleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integrate_rule.rule_value
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String ruleValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integrate_rule.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member_integrate_rule.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integrate_rule.rule_name
     *
     * @return the value of member_integrate_rule.rule_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integrate_rule.rule_name
     *
     * @param ruleName the value for member_integrate_rule.rule_name
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integrate_rule.rule_value
     *
     * @return the value of member_integrate_rule.rule_value
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getRuleValue() {
        return ruleValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integrate_rule.rule_value
     *
     * @param ruleValue the value for member_integrate_rule.rule_value
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue == null ? null : ruleValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integrate_rule.comment
     *
     * @return the value of member_integrate_rule.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integrate_rule.comment
     *
     * @param comment the value for member_integrate_rule.comment
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member_integrate_rule.deleteflag
     *
     * @return the value of member_integrate_rule.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member_integrate_rule.deleteflag
     *
     * @param deleteflag the value for member_integrate_rule.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}