package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class CommonRentTypeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public CommonRentTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
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
     * This method corresponds to the database table common_rent_type
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
     * This method corresponds to the database table common_rent_type
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_rent_type
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
     * This class corresponds to the database table common_rent_type
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

        public Criteria andRentTypeNoIsNull() {
            addCriterion("rent_type_no is null");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoIsNotNull() {
            addCriterion("rent_type_no is not null");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoEqualTo(Integer value) {
            addCriterion("rent_type_no =", value, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoNotEqualTo(Integer value) {
            addCriterion("rent_type_no <>", value, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoGreaterThan(Integer value) {
            addCriterion("rent_type_no >", value, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_type_no >=", value, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoLessThan(Integer value) {
            addCriterion("rent_type_no <", value, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoLessThanOrEqualTo(Integer value) {
            addCriterion("rent_type_no <=", value, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoIn(List<Integer> values) {
            addCriterion("rent_type_no in", values, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoNotIn(List<Integer> values) {
            addCriterion("rent_type_no not in", values, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoBetween(Integer value1, Integer value2) {
            addCriterion("rent_type_no between", value1, value2, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNoNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_type_no not between", value1, value2, "rentTypeNo");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameIsNull() {
            addCriterion("rent_type_name is null");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameIsNotNull() {
            addCriterion("rent_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameEqualTo(String value) {
            addCriterion("rent_type_name =", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameNotEqualTo(String value) {
            addCriterion("rent_type_name <>", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameGreaterThan(String value) {
            addCriterion("rent_type_name >", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("rent_type_name >=", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameLessThan(String value) {
            addCriterion("rent_type_name <", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameLessThanOrEqualTo(String value) {
            addCriterion("rent_type_name <=", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameLike(String value) {
            addCriterion("rent_type_name like", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameNotLike(String value) {
            addCriterion("rent_type_name not like", value, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameIn(List<String> values) {
            addCriterion("rent_type_name in", values, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameNotIn(List<String> values) {
            addCriterion("rent_type_name not in", values, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameBetween(String value1, String value2) {
            addCriterion("rent_type_name between", value1, value2, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentTypeNameNotBetween(String value1, String value2) {
            addCriterion("rent_type_name not between", value1, value2, "rentTypeName");
            return (Criteria) this;
        }

        public Criteria andRentValueIsNull() {
            addCriterion("rent_value is null");
            return (Criteria) this;
        }

        public Criteria andRentValueIsNotNull() {
            addCriterion("rent_value is not null");
            return (Criteria) this;
        }

        public Criteria andRentValueEqualTo(Integer value) {
            addCriterion("rent_value =", value, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueNotEqualTo(Integer value) {
            addCriterion("rent_value <>", value, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueGreaterThan(Integer value) {
            addCriterion("rent_value >", value, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_value >=", value, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueLessThan(Integer value) {
            addCriterion("rent_value <", value, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueLessThanOrEqualTo(Integer value) {
            addCriterion("rent_value <=", value, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueIn(List<Integer> values) {
            addCriterion("rent_value in", values, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueNotIn(List<Integer> values) {
            addCriterion("rent_value not in", values, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueBetween(Integer value1, Integer value2) {
            addCriterion("rent_value between", value1, value2, "rentValue");
            return (Criteria) this;
        }

        public Criteria andRentValueNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_value not between", value1, value2, "rentValue");
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
     * This class corresponds to the database table common_rent_type
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
     * This class corresponds to the database table common_rent_type
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