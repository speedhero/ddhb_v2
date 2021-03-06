package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FwSrcActionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public FwSrcActionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
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
     * This method corresponds to the database table fw_src_action
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
     * This method corresponds to the database table fw_src_action
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_src_action
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
     * This class corresponds to the database table fw_src_action
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

        public Criteria andActionIdIsNull() {
            addCriterion("ACTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andActionIdIsNotNull() {
            addCriterion("ACTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActionIdEqualTo(Integer value) {
            addCriterion("ACTION_ID =", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotEqualTo(Integer value) {
            addCriterion("ACTION_ID <>", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdGreaterThan(Integer value) {
            addCriterion("ACTION_ID >", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTION_ID >=", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdLessThan(Integer value) {
            addCriterion("ACTION_ID <", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdLessThanOrEqualTo(Integer value) {
            addCriterion("ACTION_ID <=", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdIn(List<Integer> values) {
            addCriterion("ACTION_ID in", values, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotIn(List<Integer> values) {
            addCriterion("ACTION_ID not in", values, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdBetween(Integer value1, Integer value2) {
            addCriterion("ACTION_ID between", value1, value2, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ACTION_ID not between", value1, value2, "actionId");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNull() {
            addCriterion("SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNotNull() {
            addCriterion("SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSourceIdEqualTo(Integer value) {
            addCriterion("SOURCE_ID =", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotEqualTo(Integer value) {
            addCriterion("SOURCE_ID <>", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThan(Integer value) {
            addCriterion("SOURCE_ID >", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_ID >=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThan(Integer value) {
            addCriterion("SOURCE_ID <", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_ID <=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdIn(List<Integer> values) {
            addCriterion("SOURCE_ID in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotIn(List<Integer> values) {
            addCriterion("SOURCE_ID not in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_ID between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_ID not between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNull() {
            addCriterion("ACTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNotNull() {
            addCriterion("ACTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualTo(String value) {
            addCriterion("ACTION_NAME =", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualTo(String value) {
            addCriterion("ACTION_NAME <>", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThan(String value) {
            addCriterion("ACTION_NAME >", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_NAME >=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThan(String value) {
            addCriterion("ACTION_NAME <", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualTo(String value) {
            addCriterion("ACTION_NAME <=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLike(String value) {
            addCriterion("ACTION_NAME like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotLike(String value) {
            addCriterion("ACTION_NAME not like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameIn(List<String> values) {
            addCriterion("ACTION_NAME in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotIn(List<String> values) {
            addCriterion("ACTION_NAME not in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameBetween(String value1, String value2) {
            addCriterion("ACTION_NAME between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotBetween(String value1, String value2) {
            addCriterion("ACTION_NAME not between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("METHOD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("METHOD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("METHOD_NAME =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("METHOD_NAME <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("METHOD_NAME >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("METHOD_NAME <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("METHOD_NAME like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("METHOD_NAME not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("METHOD_NAME in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("METHOD_NAME not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("METHOD_NAME between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("METHOD_NAME not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andLogLevelIsNull() {
            addCriterion("LOG_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andLogLevelIsNotNull() {
            addCriterion("LOG_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andLogLevelEqualTo(Integer value) {
            addCriterion("LOG_LEVEL =", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotEqualTo(Integer value) {
            addCriterion("LOG_LEVEL <>", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThan(Integer value) {
            addCriterion("LOG_LEVEL >", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOG_LEVEL >=", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThan(Integer value) {
            addCriterion("LOG_LEVEL <", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThanOrEqualTo(Integer value) {
            addCriterion("LOG_LEVEL <=", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelIn(List<Integer> values) {
            addCriterion("LOG_LEVEL in", values, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotIn(List<Integer> values) {
            addCriterion("LOG_LEVEL not in", values, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelBetween(Integer value1, Integer value2) {
            addCriterion("LOG_LEVEL between", value1, value2, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("LOG_LEVEL not between", value1, value2, "logLevel");
            return (Criteria) this;
        }

        public Criteria andActionMethodsIsNull() {
            addCriterion("ACTION_METHODS is null");
            return (Criteria) this;
        }

        public Criteria andActionMethodsIsNotNull() {
            addCriterion("ACTION_METHODS is not null");
            return (Criteria) this;
        }

        public Criteria andActionMethodsEqualTo(String value) {
            addCriterion("ACTION_METHODS =", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsNotEqualTo(String value) {
            addCriterion("ACTION_METHODS <>", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsGreaterThan(String value) {
            addCriterion("ACTION_METHODS >", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_METHODS >=", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsLessThan(String value) {
            addCriterion("ACTION_METHODS <", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsLessThanOrEqualTo(String value) {
            addCriterion("ACTION_METHODS <=", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsLike(String value) {
            addCriterion("ACTION_METHODS like", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsNotLike(String value) {
            addCriterion("ACTION_METHODS not like", value, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsIn(List<String> values) {
            addCriterion("ACTION_METHODS in", values, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsNotIn(List<String> values) {
            addCriterion("ACTION_METHODS not in", values, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsBetween(String value1, String value2) {
            addCriterion("ACTION_METHODS between", value1, value2, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionMethodsNotBetween(String value1, String value2) {
            addCriterion("ACTION_METHODS not between", value1, value2, "actionMethods");
            return (Criteria) this;
        }

        public Criteria andActionOrderIsNull() {
            addCriterion("ACTION_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andActionOrderIsNotNull() {
            addCriterion("ACTION_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andActionOrderEqualTo(Float value) {
            addCriterion("ACTION_ORDER =", value, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderNotEqualTo(Float value) {
            addCriterion("ACTION_ORDER <>", value, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderGreaterThan(Float value) {
            addCriterion("ACTION_ORDER >", value, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderGreaterThanOrEqualTo(Float value) {
            addCriterion("ACTION_ORDER >=", value, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderLessThan(Float value) {
            addCriterion("ACTION_ORDER <", value, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderLessThanOrEqualTo(Float value) {
            addCriterion("ACTION_ORDER <=", value, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderIn(List<Float> values) {
            addCriterion("ACTION_ORDER in", values, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderNotIn(List<Float> values) {
            addCriterion("ACTION_ORDER not in", values, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderBetween(Float value1, Float value2) {
            addCriterion("ACTION_ORDER between", value1, value2, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionOrderNotBetween(Float value1, Float value2) {
            addCriterion("ACTION_ORDER not between", value1, value2, "actionOrder");
            return (Criteria) this;
        }

        public Criteria andActionPathIsNull() {
            addCriterion("ACTION_PATH is null");
            return (Criteria) this;
        }

        public Criteria andActionPathIsNotNull() {
            addCriterion("ACTION_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andActionPathEqualTo(String value) {
            addCriterion("ACTION_PATH =", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathNotEqualTo(String value) {
            addCriterion("ACTION_PATH <>", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathGreaterThan(String value) {
            addCriterion("ACTION_PATH >", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_PATH >=", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathLessThan(String value) {
            addCriterion("ACTION_PATH <", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathLessThanOrEqualTo(String value) {
            addCriterion("ACTION_PATH <=", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathLike(String value) {
            addCriterion("ACTION_PATH like", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathNotLike(String value) {
            addCriterion("ACTION_PATH not like", value, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathIn(List<String> values) {
            addCriterion("ACTION_PATH in", values, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathNotIn(List<String> values) {
            addCriterion("ACTION_PATH not in", values, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathBetween(String value1, String value2) {
            addCriterion("ACTION_PATH between", value1, value2, "actionPath");
            return (Criteria) this;
        }

        public Criteria andActionPathNotBetween(String value1, String value2) {
            addCriterion("ACTION_PATH not between", value1, value2, "actionPath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fw_src_action
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
     * This class corresponds to the database table fw_src_action
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