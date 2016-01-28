package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FwSystemIpExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public FwSystemIpExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
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
     * This method corresponds to the database table fw_system_ip
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
     * This method corresponds to the database table fw_system_ip
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_system_ip
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
     * This class corresponds to the database table fw_system_ip
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

        public Criteria andIpIdIsNull() {
            addCriterion("ip_id is null");
            return (Criteria) this;
        }

        public Criteria andIpIdIsNotNull() {
            addCriterion("ip_id is not null");
            return (Criteria) this;
        }

        public Criteria andIpIdEqualTo(Integer value) {
            addCriterion("ip_id =", value, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdNotEqualTo(Integer value) {
            addCriterion("ip_id <>", value, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdGreaterThan(Integer value) {
            addCriterion("ip_id >", value, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ip_id >=", value, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdLessThan(Integer value) {
            addCriterion("ip_id <", value, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdLessThanOrEqualTo(Integer value) {
            addCriterion("ip_id <=", value, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdIn(List<Integer> values) {
            addCriterion("ip_id in", values, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdNotIn(List<Integer> values) {
            addCriterion("ip_id not in", values, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdBetween(Integer value1, Integer value2) {
            addCriterion("ip_id between", value1, value2, "ipId");
            return (Criteria) this;
        }

        public Criteria andIpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ip_id not between", value1, value2, "ipId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(Integer value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(Integer value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(Integer value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(Integer value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(Integer value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<Integer> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<Integer> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(Integer value1, Integer value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSysIpIsNull() {
            addCriterion("sys_ip is null");
            return (Criteria) this;
        }

        public Criteria andSysIpIsNotNull() {
            addCriterion("sys_ip is not null");
            return (Criteria) this;
        }

        public Criteria andSysIpEqualTo(String value) {
            addCriterion("sys_ip =", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotEqualTo(String value) {
            addCriterion("sys_ip <>", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpGreaterThan(String value) {
            addCriterion("sys_ip >", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpGreaterThanOrEqualTo(String value) {
            addCriterion("sys_ip >=", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpLessThan(String value) {
            addCriterion("sys_ip <", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpLessThanOrEqualTo(String value) {
            addCriterion("sys_ip <=", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpLike(String value) {
            addCriterion("sys_ip like", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotLike(String value) {
            addCriterion("sys_ip not like", value, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpIn(List<String> values) {
            addCriterion("sys_ip in", values, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotIn(List<String> values) {
            addCriterion("sys_ip not in", values, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpBetween(String value1, String value2) {
            addCriterion("sys_ip between", value1, value2, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysIpNotBetween(String value1, String value2) {
            addCriterion("sys_ip not between", value1, value2, "sysIp");
            return (Criteria) this;
        }

        public Criteria andSysPortIsNull() {
            addCriterion("sys_port is null");
            return (Criteria) this;
        }

        public Criteria andSysPortIsNotNull() {
            addCriterion("sys_port is not null");
            return (Criteria) this;
        }

        public Criteria andSysPortEqualTo(Integer value) {
            addCriterion("sys_port =", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortNotEqualTo(Integer value) {
            addCriterion("sys_port <>", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortGreaterThan(Integer value) {
            addCriterion("sys_port >", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_port >=", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortLessThan(Integer value) {
            addCriterion("sys_port <", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortLessThanOrEqualTo(Integer value) {
            addCriterion("sys_port <=", value, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortIn(List<Integer> values) {
            addCriterion("sys_port in", values, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortNotIn(List<Integer> values) {
            addCriterion("sys_port not in", values, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortBetween(Integer value1, Integer value2) {
            addCriterion("sys_port between", value1, value2, "sysPort");
            return (Criteria) this;
        }

        public Criteria andSysPortNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_port not between", value1, value2, "sysPort");
            return (Criteria) this;
        }

        public Criteria andIpNoIsNull() {
            addCriterion("ip_no is null");
            return (Criteria) this;
        }

        public Criteria andIpNoIsNotNull() {
            addCriterion("ip_no is not null");
            return (Criteria) this;
        }

        public Criteria andIpNoEqualTo(Byte value) {
            addCriterion("ip_no =", value, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoNotEqualTo(Byte value) {
            addCriterion("ip_no <>", value, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoGreaterThan(Byte value) {
            addCriterion("ip_no >", value, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoGreaterThanOrEqualTo(Byte value) {
            addCriterion("ip_no >=", value, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoLessThan(Byte value) {
            addCriterion("ip_no <", value, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoLessThanOrEqualTo(Byte value) {
            addCriterion("ip_no <=", value, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoIn(List<Byte> values) {
            addCriterion("ip_no in", values, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoNotIn(List<Byte> values) {
            addCriterion("ip_no not in", values, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoBetween(Byte value1, Byte value2) {
            addCriterion("ip_no between", value1, value2, "ipNo");
            return (Criteria) this;
        }

        public Criteria andIpNoNotBetween(Byte value1, Byte value2) {
            addCriterion("ip_no not between", value1, value2, "ipNo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fw_system_ip
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
     * This class corresponds to the database table fw_system_ip
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