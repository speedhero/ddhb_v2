package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FwSystemExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public FwSystemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
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
     * This method corresponds to the database table fw_system
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
     * This method corresponds to the database table fw_system
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system
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
     * This class corresponds to the database table fw_system
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

        public Criteria andSysIdIsNull() {
            addCriterion("SYS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSysIdIsNotNull() {
            addCriterion("SYS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSysIdEqualTo(Integer value) {
            addCriterion("SYS_ID =", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdNotEqualTo(Integer value) {
            addCriterion("SYS_ID <>", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdGreaterThan(Integer value) {
            addCriterion("SYS_ID >", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SYS_ID >=", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdLessThan(Integer value) {
            addCriterion("SYS_ID <", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdLessThanOrEqualTo(Integer value) {
            addCriterion("SYS_ID <=", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdIn(List<Integer> values) {
            addCriterion("SYS_ID in", values, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdNotIn(List<Integer> values) {
            addCriterion("SYS_ID not in", values, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdBetween(Integer value1, Integer value2) {
            addCriterion("SYS_ID between", value1, value2, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SYS_ID not between", value1, value2, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNull() {
            addCriterion("SYS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNotNull() {
            addCriterion("SYS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSysNameEqualTo(String value) {
            addCriterion("SYS_NAME =", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotEqualTo(String value) {
            addCriterion("SYS_NAME <>", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThan(String value) {
            addCriterion("SYS_NAME >", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_NAME >=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThan(String value) {
            addCriterion("SYS_NAME <", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThanOrEqualTo(String value) {
            addCriterion("SYS_NAME <=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLike(String value) {
            addCriterion("SYS_NAME like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotLike(String value) {
            addCriterion("SYS_NAME not like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameIn(List<String> values) {
            addCriterion("SYS_NAME in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotIn(List<String> values) {
            addCriterion("SYS_NAME not in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameBetween(String value1, String value2) {
            addCriterion("SYS_NAME between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotBetween(String value1, String value2) {
            addCriterion("SYS_NAME not between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andSysIpIsNull() {
            addCriterion("SYS_IP is null");
            return (Criteria) this;
        }

        public Criteria andSysIpIsNotNull() {
            addCriterion("SYS_IP is not null");
            return (Criteria) this;
        }

        public Criteria andSysIpEqualTo(String value) {
            addCriterion("SYS_IP =", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotEqualTo(String value) {
            addCriterion("SYS_IP <>", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpGreaterThan(String value) {
            addCriterion("SYS_IP >", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_IP >=", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpLessThan(String value) {
            addCriterion("SYS_IP <", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpLessThanOrEqualTo(String value) {
            addCriterion("SYS_IP <=", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpLike(String value) {
            addCriterion("SYS_IP like", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotLike(String value) {
            addCriterion("SYS_IP not like", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpIn(List<String> values) {
            addCriterion("SYS_IP in", values, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotIn(List<String> values) {
            addCriterion("SYS_IP not in", values, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpBetween(String value1, String value2) {
            addCriterion("SYS_IP between", value1, value2, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotBetween(String value1, String value2) {
            addCriterion("SYS_IP not between", value1, value2, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNull() {
            addCriterion("SYS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSysCodeIsNotNull() {
            addCriterion("SYS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSysCodeEqualTo(String value) {
            addCriterion("SYS_CODE =", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotEqualTo(String value) {
            addCriterion("SYS_CODE <>", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThan(String value) {
            addCriterion("SYS_CODE >", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_CODE >=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThan(String value) {
            addCriterion("SYS_CODE <", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLessThanOrEqualTo(String value) {
            addCriterion("SYS_CODE <=", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeLike(String value) {
            addCriterion("SYS_CODE like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotLike(String value) {
            addCriterion("SYS_CODE not like", value, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeIn(List<String> values) {
            addCriterion("SYS_CODE in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotIn(List<String> values) {
            addCriterion("SYS_CODE not in", values, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeBetween(String value1, String value2) {
            addCriterion("SYS_CODE between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysCodeNotBetween(String value1, String value2) {
            addCriterion("SYS_CODE not between", value1, value2, "sysCode");
            return (Criteria) this;
        }

        public Criteria andSysPortIsNull() {
            addCriterion("SYS_PORT is null");
            return (Criteria) this;
        }

        public Criteria andSysPortIsNotNull() {
            addCriterion("SYS_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andSysPortEqualTo(Integer value) {
            addCriterion("SYS_PORT =", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortNotEqualTo(Integer value) {
            addCriterion("SYS_PORT <>", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortGreaterThan(Integer value) {
            addCriterion("SYS_PORT >", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("SYS_PORT >=", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortLessThan(Integer value) {
            addCriterion("SYS_PORT <", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortLessThanOrEqualTo(Integer value) {
            addCriterion("SYS_PORT <=", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortIn(List<Integer> values) {
            addCriterion("SYS_PORT in", values, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortNotIn(List<Integer> values) {
            addCriterion("SYS_PORT not in", values, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortBetween(Integer value1, Integer value2) {
            addCriterion("SYS_PORT between", value1, value2, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortNotBetween(Integer value1, Integer value2) {
            addCriterion("SYS_PORT not between", value1, value2, "sysPort");
            return (Criteria) this;
        }

        public Criteria andContextPathIsNull() {
            addCriterion("CONTEXT_PATH is null");
            return (Criteria) this;
        }

        public Criteria andContextPathIsNotNull() {
            addCriterion("CONTEXT_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andContextPathEqualTo(String value) {
            addCriterion("CONTEXT_PATH =", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathNotEqualTo(String value) {
            addCriterion("CONTEXT_PATH <>", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathGreaterThan(String value) {
            addCriterion("CONTEXT_PATH >", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathGreaterThanOrEqualTo(String value) {
            addCriterion("CONTEXT_PATH >=", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathLessThan(String value) {
            addCriterion("CONTEXT_PATH <", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathLessThanOrEqualTo(String value) {
            addCriterion("CONTEXT_PATH <=", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathLike(String value) {
            addCriterion("CONTEXT_PATH like", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathNotLike(String value) {
            addCriterion("CONTEXT_PATH not like", value, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathIn(List<String> values) {
            addCriterion("CONTEXT_PATH in", values, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathNotIn(List<String> values) {
            addCriterion("CONTEXT_PATH not in", values, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathBetween(String value1, String value2) {
            addCriterion("CONTEXT_PATH between", value1, value2, "contextPath");
            return (Criteria) this;
        }

        public Criteria andContextPathNotBetween(String value1, String value2) {
            addCriterion("CONTEXT_PATH not between", value1, value2, "contextPath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fw_system
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
     * This class corresponds to the database table fw_system
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