package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FwActionCntExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public FwActionCntExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
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
     * This method corresponds to the database table fw_action_cnt
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
     * This method corresponds to the database table fw_action_cnt
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_action_cnt
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
     * This class corresponds to the database table fw_action_cnt
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

        public Criteria andActCntIdIsNull() {
            addCriterion("ACT_CNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andActCntIdIsNotNull() {
            addCriterion("ACT_CNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActCntIdEqualTo(Integer value) {
            addCriterion("ACT_CNT_ID =", value, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdNotEqualTo(Integer value) {
            addCriterion("ACT_CNT_ID <>", value, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdGreaterThan(Integer value) {
            addCriterion("ACT_CNT_ID >", value, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACT_CNT_ID >=", value, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdLessThan(Integer value) {
            addCriterion("ACT_CNT_ID <", value, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdLessThanOrEqualTo(Integer value) {
            addCriterion("ACT_CNT_ID <=", value, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdIn(List<Integer> values) {
            addCriterion("ACT_CNT_ID in", values, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdNotIn(List<Integer> values) {
            addCriterion("ACT_CNT_ID not in", values, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdBetween(Integer value1, Integer value2) {
            addCriterion("ACT_CNT_ID between", value1, value2, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActCntIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ACT_CNT_ID not between", value1, value2, "actCntId");
            return (Criteria) this;
        }

        public Criteria andActLogIdIsNull() {
            addCriterion("ACT_LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andActLogIdIsNotNull() {
            addCriterion("ACT_LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActLogIdEqualTo(Integer value) {
            addCriterion("ACT_LOG_ID =", value, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdNotEqualTo(Integer value) {
            addCriterion("ACT_LOG_ID <>", value, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdGreaterThan(Integer value) {
            addCriterion("ACT_LOG_ID >", value, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACT_LOG_ID >=", value, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdLessThan(Integer value) {
            addCriterion("ACT_LOG_ID <", value, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("ACT_LOG_ID <=", value, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdIn(List<Integer> values) {
            addCriterion("ACT_LOG_ID in", values, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdNotIn(List<Integer> values) {
            addCriterion("ACT_LOG_ID not in", values, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdBetween(Integer value1, Integer value2) {
            addCriterion("ACT_LOG_ID between", value1, value2, "actLogId");
            return (Criteria) this;
        }

        public Criteria andActLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ACT_LOG_ID not between", value1, value2, "actLogId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("BUSINESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("BUSINESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(Integer value) {
            addCriterion("BUSINESS_ID =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(Integer value) {
            addCriterion("BUSINESS_ID <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(Integer value) {
            addCriterion("BUSINESS_ID >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUSINESS_ID >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(Integer value) {
            addCriterion("BUSINESS_ID <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(Integer value) {
            addCriterion("BUSINESS_ID <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<Integer> values) {
            addCriterion("BUSINESS_ID in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<Integer> values) {
            addCriterion("BUSINESS_ID not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(Integer value1, Integer value2) {
            addCriterion("BUSINESS_ID between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BUSINESS_ID not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleIsNull() {
            addCriterion("BUSINESS_MODULE is null");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleIsNotNull() {
            addCriterion("BUSINESS_MODULE is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleEqualTo(Integer value) {
            addCriterion("BUSINESS_MODULE =", value, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleNotEqualTo(Integer value) {
            addCriterion("BUSINESS_MODULE <>", value, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleGreaterThan(Integer value) {
            addCriterion("BUSINESS_MODULE >", value, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUSINESS_MODULE >=", value, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleLessThan(Integer value) {
            addCriterion("BUSINESS_MODULE <", value, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleLessThanOrEqualTo(Integer value) {
            addCriterion("BUSINESS_MODULE <=", value, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleIn(List<Integer> values) {
            addCriterion("BUSINESS_MODULE in", values, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleNotIn(List<Integer> values) {
            addCriterion("BUSINESS_MODULE not in", values, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleBetween(Integer value1, Integer value2) {
            addCriterion("BUSINESS_MODULE between", value1, value2, "businessModule");
            return (Criteria) this;
        }

        public Criteria andBusinessModuleNotBetween(Integer value1, Integer value2) {
            addCriterion("BUSINESS_MODULE not between", value1, value2, "businessModule");
            return (Criteria) this;
        }

        public Criteria andActCntIsNull() {
            addCriterion("ACT_CNT is null");
            return (Criteria) this;
        }

        public Criteria andActCntIsNotNull() {
            addCriterion("ACT_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andActCntEqualTo(String value) {
            addCriterion("ACT_CNT =", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntNotEqualTo(String value) {
            addCriterion("ACT_CNT <>", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntGreaterThan(String value) {
            addCriterion("ACT_CNT >", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntGreaterThanOrEqualTo(String value) {
            addCriterion("ACT_CNT >=", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntLessThan(String value) {
            addCriterion("ACT_CNT <", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntLessThanOrEqualTo(String value) {
            addCriterion("ACT_CNT <=", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntLike(String value) {
            addCriterion("ACT_CNT like", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntNotLike(String value) {
            addCriterion("ACT_CNT not like", value, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntIn(List<String> values) {
            addCriterion("ACT_CNT in", values, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntNotIn(List<String> values) {
            addCriterion("ACT_CNT not in", values, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntBetween(String value1, String value2) {
            addCriterion("ACT_CNT between", value1, value2, "actCnt");
            return (Criteria) this;
        }

        public Criteria andActCntNotBetween(String value1, String value2) {
            addCriterion("ACT_CNT not between", value1, value2, "actCnt");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fw_action_cnt
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
     * This class corresponds to the database table fw_action_cnt
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