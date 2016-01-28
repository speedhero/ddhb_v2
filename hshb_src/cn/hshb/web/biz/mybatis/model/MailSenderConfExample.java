package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class MailSenderConfExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public MailSenderConfExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andConfigIdIsNull() {
            addCriterion("config_id is null");
            return (Criteria) this;
        }

        public Criteria andConfigIdIsNotNull() {
            addCriterion("config_id is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIdEqualTo(Integer value) {
            addCriterion("config_id =", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotEqualTo(Integer value) {
            addCriterion("config_id <>", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThan(Integer value) {
            addCriterion("config_id >", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("config_id >=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThan(Integer value) {
            addCriterion("config_id <", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThanOrEqualTo(Integer value) {
            addCriterion("config_id <=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdIn(List<Integer> values) {
            addCriterion("config_id in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotIn(List<Integer> values) {
            addCriterion("config_id not in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdBetween(Integer value1, Integer value2) {
            addCriterion("config_id between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotBetween(Integer value1, Integer value2) {
            addCriterion("config_id not between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlIsNull() {
            addCriterion("smtp_url is null");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlIsNotNull() {
            addCriterion("smtp_url is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlEqualTo(String value) {
            addCriterion("smtp_url =", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlNotEqualTo(String value) {
            addCriterion("smtp_url <>", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlGreaterThan(String value) {
            addCriterion("smtp_url >", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlGreaterThanOrEqualTo(String value) {
            addCriterion("smtp_url >=", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlLessThan(String value) {
            addCriterion("smtp_url <", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlLessThanOrEqualTo(String value) {
            addCriterion("smtp_url <=", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlLike(String value) {
            addCriterion("smtp_url like", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlNotLike(String value) {
            addCriterion("smtp_url not like", value, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlIn(List<String> values) {
            addCriterion("smtp_url in", values, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlNotIn(List<String> values) {
            addCriterion("smtp_url not in", values, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlBetween(String value1, String value2) {
            addCriterion("smtp_url between", value1, value2, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andSmtpUrlNotBetween(String value1, String value2) {
            addCriterion("smtp_url not between", value1, value2, "smtpUrl");
            return (Criteria) this;
        }

        public Criteria andMailAccountIsNull() {
            addCriterion("mail_account is null");
            return (Criteria) this;
        }

        public Criteria andMailAccountIsNotNull() {
            addCriterion("mail_account is not null");
            return (Criteria) this;
        }

        public Criteria andMailAccountEqualTo(String value) {
            addCriterion("mail_account =", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotEqualTo(String value) {
            addCriterion("mail_account <>", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountGreaterThan(String value) {
            addCriterion("mail_account >", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountGreaterThanOrEqualTo(String value) {
            addCriterion("mail_account >=", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountLessThan(String value) {
            addCriterion("mail_account <", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountLessThanOrEqualTo(String value) {
            addCriterion("mail_account <=", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountLike(String value) {
            addCriterion("mail_account like", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotLike(String value) {
            addCriterion("mail_account not like", value, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountIn(List<String> values) {
            addCriterion("mail_account in", values, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotIn(List<String> values) {
            addCriterion("mail_account not in", values, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountBetween(String value1, String value2) {
            addCriterion("mail_account between", value1, value2, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailAccountNotBetween(String value1, String value2) {
            addCriterion("mail_account not between", value1, value2, "mailAccount");
            return (Criteria) this;
        }

        public Criteria andMailPasswordIsNull() {
            addCriterion("mail_password is null");
            return (Criteria) this;
        }

        public Criteria andMailPasswordIsNotNull() {
            addCriterion("mail_password is not null");
            return (Criteria) this;
        }

        public Criteria andMailPasswordEqualTo(String value) {
            addCriterion("mail_password =", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotEqualTo(String value) {
            addCriterion("mail_password <>", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordGreaterThan(String value) {
            addCriterion("mail_password >", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("mail_password >=", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordLessThan(String value) {
            addCriterion("mail_password <", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordLessThanOrEqualTo(String value) {
            addCriterion("mail_password <=", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordLike(String value) {
            addCriterion("mail_password like", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotLike(String value) {
            addCriterion("mail_password not like", value, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordIn(List<String> values) {
            addCriterion("mail_password in", values, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotIn(List<String> values) {
            addCriterion("mail_password not in", values, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordBetween(String value1, String value2) {
            addCriterion("mail_password between", value1, value2, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andMailPasswordNotBetween(String value1, String value2) {
            addCriterion("mail_password not between", value1, value2, "mailPassword");
            return (Criteria) this;
        }

        public Criteria andFromMailIsNull() {
            addCriterion("from_mail is null");
            return (Criteria) this;
        }

        public Criteria andFromMailIsNotNull() {
            addCriterion("from_mail is not null");
            return (Criteria) this;
        }

        public Criteria andFromMailEqualTo(String value) {
            addCriterion("from_mail =", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailNotEqualTo(String value) {
            addCriterion("from_mail <>", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailGreaterThan(String value) {
            addCriterion("from_mail >", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailGreaterThanOrEqualTo(String value) {
            addCriterion("from_mail >=", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailLessThan(String value) {
            addCriterion("from_mail <", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailLessThanOrEqualTo(String value) {
            addCriterion("from_mail <=", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailLike(String value) {
            addCriterion("from_mail like", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailNotLike(String value) {
            addCriterion("from_mail not like", value, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailIn(List<String> values) {
            addCriterion("from_mail in", values, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailNotIn(List<String> values) {
            addCriterion("from_mail not in", values, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailBetween(String value1, String value2) {
            addCriterion("from_mail between", value1, value2, "fromMail");
            return (Criteria) this;
        }

        public Criteria andFromMailNotBetween(String value1, String value2) {
            addCriterion("from_mail not between", value1, value2, "fromMail");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIsNull() {
            addCriterion("smtp_port is null");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIsNotNull() {
            addCriterion("smtp_port is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpPortEqualTo(Integer value) {
            addCriterion("smtp_port =", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotEqualTo(Integer value) {
            addCriterion("smtp_port <>", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortGreaterThan(Integer value) {
            addCriterion("smtp_port >", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("smtp_port >=", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLessThan(Integer value) {
            addCriterion("smtp_port <", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLessThanOrEqualTo(Integer value) {
            addCriterion("smtp_port <=", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIn(List<Integer> values) {
            addCriterion("smtp_port in", values, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotIn(List<Integer> values) {
            addCriterion("smtp_port not in", values, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortBetween(Integer value1, Integer value2) {
            addCriterion("smtp_port between", value1, value2, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotBetween(Integer value1, Integer value2) {
            addCriterion("smtp_port not between", value1, value2, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Integer value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Integer value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Integer value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Integer value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Integer value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Integer> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Integer> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Integer value1, Integer value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("is_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("is_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Integer value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Integer value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Integer value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Integer value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Integer value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Integer> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Integer> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Integer value1, Integer value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_enable not between", value1, value2, "isEnable");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mail_sender_conf
     *
     * @mbggenerated do_not_delete_during_merge Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mail_sender_conf
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}