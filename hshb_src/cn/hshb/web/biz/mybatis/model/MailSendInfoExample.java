package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailSendInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public MailSendInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
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
     * This method corresponds to the database table mail_send_info
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
     * This method corresponds to the database table mail_send_info
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mail_send_info
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
     * This class corresponds to the database table mail_send_info
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

        public Criteria andSendIdIsNull() {
            addCriterion("send_id is null");
            return (Criteria) this;
        }

        public Criteria andSendIdIsNotNull() {
            addCriterion("send_id is not null");
            return (Criteria) this;
        }

        public Criteria andSendIdEqualTo(Integer value) {
            addCriterion("send_id =", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotEqualTo(Integer value) {
            addCriterion("send_id <>", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdGreaterThan(Integer value) {
            addCriterion("send_id >", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_id >=", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdLessThan(Integer value) {
            addCriterion("send_id <", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdLessThanOrEqualTo(Integer value) {
            addCriterion("send_id <=", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdIn(List<Integer> values) {
            addCriterion("send_id in", values, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotIn(List<Integer> values) {
            addCriterion("send_id not in", values, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdBetween(Integer value1, Integer value2) {
            addCriterion("send_id between", value1, value2, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotBetween(Integer value1, Integer value2) {
            addCriterion("send_id not between", value1, value2, "sendId");
            return (Criteria) this;
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

        public Criteria andMailTitleIsNull() {
            addCriterion("mail_title is null");
            return (Criteria) this;
        }

        public Criteria andMailTitleIsNotNull() {
            addCriterion("mail_title is not null");
            return (Criteria) this;
        }

        public Criteria andMailTitleEqualTo(String value) {
            addCriterion("mail_title =", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleNotEqualTo(String value) {
            addCriterion("mail_title <>", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleGreaterThan(String value) {
            addCriterion("mail_title >", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleGreaterThanOrEqualTo(String value) {
            addCriterion("mail_title >=", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleLessThan(String value) {
            addCriterion("mail_title <", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleLessThanOrEqualTo(String value) {
            addCriterion("mail_title <=", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleLike(String value) {
            addCriterion("mail_title like", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleNotLike(String value) {
            addCriterion("mail_title not like", value, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleIn(List<String> values) {
            addCriterion("mail_title in", values, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleNotIn(List<String> values) {
            addCriterion("mail_title not in", values, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleBetween(String value1, String value2) {
            addCriterion("mail_title between", value1, value2, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andMailTitleNotBetween(String value1, String value2) {
            addCriterion("mail_title not between", value1, value2, "mailTitle");
            return (Criteria) this;
        }

        public Criteria andRecevierIsNull() {
            addCriterion("recevier is null");
            return (Criteria) this;
        }

        public Criteria andRecevierIsNotNull() {
            addCriterion("recevier is not null");
            return (Criteria) this;
        }

        public Criteria andRecevierEqualTo(String value) {
            addCriterion("recevier =", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierNotEqualTo(String value) {
            addCriterion("recevier <>", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierGreaterThan(String value) {
            addCriterion("recevier >", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierGreaterThanOrEqualTo(String value) {
            addCriterion("recevier >=", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierLessThan(String value) {
            addCriterion("recevier <", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierLessThanOrEqualTo(String value) {
            addCriterion("recevier <=", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierLike(String value) {
            addCriterion("recevier like", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierNotLike(String value) {
            addCriterion("recevier not like", value, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierIn(List<String> values) {
            addCriterion("recevier in", values, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierNotIn(List<String> values) {
            addCriterion("recevier not in", values, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierBetween(String value1, String value2) {
            addCriterion("recevier between", value1, value2, "recevier");
            return (Criteria) this;
        }

        public Criteria andRecevierNotBetween(String value1, String value2) {
            addCriterion("recevier not between", value1, value2, "recevier");
            return (Criteria) this;
        }

        public Criteria andCopyToIsNull() {
            addCriterion("copy_to is null");
            return (Criteria) this;
        }

        public Criteria andCopyToIsNotNull() {
            addCriterion("copy_to is not null");
            return (Criteria) this;
        }

        public Criteria andCopyToEqualTo(String value) {
            addCriterion("copy_to =", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToNotEqualTo(String value) {
            addCriterion("copy_to <>", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToGreaterThan(String value) {
            addCriterion("copy_to >", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToGreaterThanOrEqualTo(String value) {
            addCriterion("copy_to >=", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToLessThan(String value) {
            addCriterion("copy_to <", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToLessThanOrEqualTo(String value) {
            addCriterion("copy_to <=", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToLike(String value) {
            addCriterion("copy_to like", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToNotLike(String value) {
            addCriterion("copy_to not like", value, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToIn(List<String> values) {
            addCriterion("copy_to in", values, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToNotIn(List<String> values) {
            addCriterion("copy_to not in", values, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToBetween(String value1, String value2) {
            addCriterion("copy_to between", value1, value2, "copyTo");
            return (Criteria) this;
        }

        public Criteria andCopyToNotBetween(String value1, String value2) {
            addCriterion("copy_to not between", value1, value2, "copyTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToIsNull() {
            addCriterion("hidden_to is null");
            return (Criteria) this;
        }

        public Criteria andHiddenToIsNotNull() {
            addCriterion("hidden_to is not null");
            return (Criteria) this;
        }

        public Criteria andHiddenToEqualTo(String value) {
            addCriterion("hidden_to =", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToNotEqualTo(String value) {
            addCriterion("hidden_to <>", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToGreaterThan(String value) {
            addCriterion("hidden_to >", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToGreaterThanOrEqualTo(String value) {
            addCriterion("hidden_to >=", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToLessThan(String value) {
            addCriterion("hidden_to <", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToLessThanOrEqualTo(String value) {
            addCriterion("hidden_to <=", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToLike(String value) {
            addCriterion("hidden_to like", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToNotLike(String value) {
            addCriterion("hidden_to not like", value, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToIn(List<String> values) {
            addCriterion("hidden_to in", values, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToNotIn(List<String> values) {
            addCriterion("hidden_to not in", values, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToBetween(String value1, String value2) {
            addCriterion("hidden_to between", value1, value2, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andHiddenToNotBetween(String value1, String value2) {
            addCriterion("hidden_to not between", value1, value2, "hiddenTo");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("send_status is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("send_status is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(Integer value) {
            addCriterion("send_status =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(Integer value) {
            addCriterion("send_status <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(Integer value) {
            addCriterion("send_status >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_status >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(Integer value) {
            addCriterion("send_status <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(Integer value) {
            addCriterion("send_status <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<Integer> values) {
            addCriterion("send_status in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<Integer> values) {
            addCriterion("send_status not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(Integer value1, Integer value2) {
            addCriterion("send_status between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("send_status not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendUserIsNull() {
            addCriterion("send_user is null");
            return (Criteria) this;
        }

        public Criteria andSendUserIsNotNull() {
            addCriterion("send_user is not null");
            return (Criteria) this;
        }

        public Criteria andSendUserEqualTo(Integer value) {
            addCriterion("send_user =", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotEqualTo(Integer value) {
            addCriterion("send_user <>", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserGreaterThan(Integer value) {
            addCriterion("send_user >", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_user >=", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLessThan(Integer value) {
            addCriterion("send_user <", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLessThanOrEqualTo(Integer value) {
            addCriterion("send_user <=", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserIn(List<Integer> values) {
            addCriterion("send_user in", values, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotIn(List<Integer> values) {
            addCriterion("send_user not in", values, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserBetween(Integer value1, Integer value2) {
            addCriterion("send_user between", value1, value2, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotBetween(Integer value1, Integer value2) {
            addCriterion("send_user not between", value1, value2, "sendUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNull() {
            addCriterion("error_msg is null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNotNull() {
            addCriterion("error_msg is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgEqualTo(String value) {
            addCriterion("error_msg =", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotEqualTo(String value) {
            addCriterion("error_msg <>", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThan(String value) {
            addCriterion("error_msg >", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThanOrEqualTo(String value) {
            addCriterion("error_msg >=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThan(String value) {
            addCriterion("error_msg <", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThanOrEqualTo(String value) {
            addCriterion("error_msg <=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLike(String value) {
            addCriterion("error_msg like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotLike(String value) {
            addCriterion("error_msg not like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIn(List<String> values) {
            addCriterion("error_msg in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotIn(List<String> values) {
            addCriterion("error_msg not in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgBetween(String value1, String value2) {
            addCriterion("error_msg between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotBetween(String value1, String value2) {
            addCriterion("error_msg not between", value1, value2, "errorMsg");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mail_send_info
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
     * This class corresponds to the database table mail_send_info
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