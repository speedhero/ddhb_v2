package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BBrokeransweredExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public BBrokeransweredExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
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
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andErpIdIsNull() {
            addCriterion("erp_id is null");
            return (Criteria) this;
        }

        public Criteria andErpIdIsNotNull() {
            addCriterion("erp_id is not null");
            return (Criteria) this;
        }

        public Criteria andErpIdEqualTo(String value) {
            addCriterion("erp_id =", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdNotEqualTo(String value) {
            addCriterion("erp_id <>", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdGreaterThan(String value) {
            addCriterion("erp_id >", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdGreaterThanOrEqualTo(String value) {
            addCriterion("erp_id >=", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdLessThan(String value) {
            addCriterion("erp_id <", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdLessThanOrEqualTo(String value) {
            addCriterion("erp_id <=", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdLike(String value) {
            addCriterion("erp_id like", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdNotLike(String value) {
            addCriterion("erp_id not like", value, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdIn(List<String> values) {
            addCriterion("erp_id in", values, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdNotIn(List<String> values) {
            addCriterion("erp_id not in", values, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdBetween(String value1, String value2) {
            addCriterion("erp_id between", value1, value2, "erpId");
            return (Criteria) this;
        }

        public Criteria andErpIdNotBetween(String value1, String value2) {
            addCriterion("erp_id not between", value1, value2, "erpId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIsNull() {
            addCriterion("broker_id is null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIsNotNull() {
            addCriterion("broker_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdEqualTo(String value) {
            addCriterion("broker_id =", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotEqualTo(String value) {
            addCriterion("broker_id <>", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThan(String value) {
            addCriterion("broker_id >", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThanOrEqualTo(String value) {
            addCriterion("broker_id >=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThan(String value) {
            addCriterion("broker_id <", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThanOrEqualTo(String value) {
            addCriterion("broker_id <=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLike(String value) {
            addCriterion("broker_id like", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotLike(String value) {
            addCriterion("broker_id not like", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIn(List<String> values) {
            addCriterion("broker_id in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotIn(List<String> values) {
            addCriterion("broker_id not in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdBetween(String value1, String value2) {
            addCriterion("broker_id between", value1, value2, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotBetween(String value1, String value2) {
            addCriterion("broker_id not between", value1, value2, "brokerId");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentIsNull() {
            addCriterion("answered_content is null");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentIsNotNull() {
            addCriterion("answered_content is not null");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentEqualTo(String value) {
            addCriterion("answered_content =", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentNotEqualTo(String value) {
            addCriterion("answered_content <>", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentGreaterThan(String value) {
            addCriterion("answered_content >", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentGreaterThanOrEqualTo(String value) {
            addCriterion("answered_content >=", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentLessThan(String value) {
            addCriterion("answered_content <", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentLessThanOrEqualTo(String value) {
            addCriterion("answered_content <=", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentLike(String value) {
            addCriterion("answered_content like", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentNotLike(String value) {
            addCriterion("answered_content not like", value, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentIn(List<String> values) {
            addCriterion("answered_content in", values, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentNotIn(List<String> values) {
            addCriterion("answered_content not in", values, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentBetween(String value1, String value2) {
            addCriterion("answered_content between", value1, value2, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andAnsweredContentNotBetween(String value1, String value2) {
            addCriterion("answered_content not between", value1, value2, "answeredContent");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(String value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(String value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(String value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(String value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLike(String value) {
            addCriterion("question_id like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotLike(String value) {
            addCriterion("question_id not like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<String> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<String> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(String value1, String value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(String value1, String value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeIsNull() {
            addCriterion("answered_time is null");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeIsNotNull() {
            addCriterion("answered_time is not null");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeEqualTo(Date value) {
            addCriterionForJDBCDate("answered_time =", value, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("answered_time <>", value, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("answered_time >", value, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("answered_time >=", value, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeLessThan(Date value) {
            addCriterionForJDBCDate("answered_time <", value, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("answered_time <=", value, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeIn(List<Date> values) {
            addCriterionForJDBCDate("answered_time in", values, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("answered_time not in", values, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("answered_time between", value1, value2, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andAnsweredTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("answered_time not between", value1, value2, "answeredTime");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNull() {
            addCriterion("isshow is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("isshow is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(Integer value) {
            addCriterion("isshow =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(Integer value) {
            addCriterion("isshow <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(Integer value) {
            addCriterion("isshow >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(Integer value) {
            addCriterion("isshow >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(Integer value) {
            addCriterion("isshow <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(Integer value) {
            addCriterion("isshow <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<Integer> values) {
            addCriterion("isshow in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<Integer> values) {
            addCriterion("isshow not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(Integer value1, Integer value2) {
            addCriterion("isshow between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(Integer value1, Integer value2) {
            addCriterion("isshow not between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsacceptedIsNull() {
            addCriterion("isaccepted is null");
            return (Criteria) this;
        }

        public Criteria andIsacceptedIsNotNull() {
            addCriterion("isaccepted is not null");
            return (Criteria) this;
        }

        public Criteria andIsacceptedEqualTo(Integer value) {
            addCriterion("isaccepted =", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedNotEqualTo(Integer value) {
            addCriterion("isaccepted <>", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedGreaterThan(Integer value) {
            addCriterion("isaccepted >", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedGreaterThanOrEqualTo(Integer value) {
            addCriterion("isaccepted >=", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedLessThan(Integer value) {
            addCriterion("isaccepted <", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedLessThanOrEqualTo(Integer value) {
            addCriterion("isaccepted <=", value, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedIn(List<Integer> values) {
            addCriterion("isaccepted in", values, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedNotIn(List<Integer> values) {
            addCriterion("isaccepted not in", values, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedBetween(Integer value1, Integer value2) {
            addCriterion("isaccepted between", value1, value2, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andIsacceptedNotBetween(Integer value1, Integer value2) {
            addCriterion("isaccepted not between", value1, value2, "isaccepted");
            return (Criteria) this;
        }

        public Criteria andUpdateflagIsNull() {
            addCriterion("updateflag is null");
            return (Criteria) this;
        }

        public Criteria andUpdateflagIsNotNull() {
            addCriterion("updateflag is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateflagEqualTo(Integer value) {
            addCriterion("updateflag =", value, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagNotEqualTo(Integer value) {
            addCriterion("updateflag <>", value, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagGreaterThan(Integer value) {
            addCriterion("updateflag >", value, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("updateflag >=", value, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagLessThan(Integer value) {
            addCriterion("updateflag <", value, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagLessThanOrEqualTo(Integer value) {
            addCriterion("updateflag <=", value, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagIn(List<Integer> values) {
            addCriterion("updateflag in", values, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagNotIn(List<Integer> values) {
            addCriterion("updateflag not in", values, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagBetween(Integer value1, Integer value2) {
            addCriterion("updateflag between", value1, value2, "updateflag");
            return (Criteria) this;
        }

        public Criteria andUpdateflagNotBetween(Integer value1, Integer value2) {
            addCriterion("updateflag not between", value1, value2, "updateflag");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIsNull() {
            addCriterion("lastmodified is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIsNotNull() {
            addCriterion("lastmodified is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedEqualTo(Date value) {
            addCriterionForJDBCDate("lastmodified =", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastmodified <>", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedGreaterThan(Date value) {
            addCriterionForJDBCDate("lastmodified >", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastmodified >=", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedLessThan(Date value) {
            addCriterionForJDBCDate("lastmodified <", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastmodified <=", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIn(List<Date> values) {
            addCriterionForJDBCDate("lastmodified in", values, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastmodified not in", values, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastmodified between", value1, value2, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastmodified not between", value1, value2, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastsyncIsNull() {
            addCriterion("lastsync is null");
            return (Criteria) this;
        }

        public Criteria andLastsyncIsNotNull() {
            addCriterion("lastsync is not null");
            return (Criteria) this;
        }

        public Criteria andLastsyncEqualTo(Date value) {
            addCriterionForJDBCDate("lastsync =", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastsync <>", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncGreaterThan(Date value) {
            addCriterionForJDBCDate("lastsync >", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastsync >=", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncLessThan(Date value) {
            addCriterionForJDBCDate("lastsync <", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastsync <=", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncIn(List<Date> values) {
            addCriterionForJDBCDate("lastsync in", values, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastsync not in", values, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastsync between", value1, value2, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastsync not between", value1, value2, "lastsync");
            return (Criteria) this;
        }

        public Criteria andDeleteflagIsNull() {
            addCriterion("deleteflag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteflagIsNotNull() {
            addCriterion("deleteflag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteflagEqualTo(Integer value) {
            addCriterion("deleteflag =", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagNotEqualTo(Integer value) {
            addCriterion("deleteflag <>", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagGreaterThan(Integer value) {
            addCriterion("deleteflag >", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("deleteflag >=", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagLessThan(Integer value) {
            addCriterion("deleteflag <", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagLessThanOrEqualTo(Integer value) {
            addCriterion("deleteflag <=", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagIn(List<Integer> values) {
            addCriterion("deleteflag in", values, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagNotIn(List<Integer> values) {
            addCriterion("deleteflag not in", values, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagBetween(Integer value1, Integer value2) {
            addCriterion("deleteflag between", value1, value2, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagNotBetween(Integer value1, Integer value2) {
            addCriterion("deleteflag not between", value1, value2, "deleteflag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_brokeranswered
     *
     * @mbggenerated do_not_delete_during_merge Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_brokeranswered
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
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