package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class CommonAdandactivitybarExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public CommonAdandactivitybarExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
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
     * This method corresponds to the database table common_adandactivitybar
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
     * This method corresponds to the database table common_adandactivitybar
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_adandactivitybar
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
     * This class corresponds to the database table common_adandactivitybar
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

        public Criteria andBarIdIsNull() {
            addCriterion("bar_id is null");
            return (Criteria) this;
        }

        public Criteria andBarIdIsNotNull() {
            addCriterion("bar_id is not null");
            return (Criteria) this;
        }

        public Criteria andBarIdEqualTo(Integer value) {
            addCriterion("bar_id =", value, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdNotEqualTo(Integer value) {
            addCriterion("bar_id <>", value, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdGreaterThan(Integer value) {
            addCriterion("bar_id >", value, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bar_id >=", value, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdLessThan(Integer value) {
            addCriterion("bar_id <", value, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdLessThanOrEqualTo(Integer value) {
            addCriterion("bar_id <=", value, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdIn(List<Integer> values) {
            addCriterion("bar_id in", values, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdNotIn(List<Integer> values) {
            addCriterion("bar_id not in", values, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdBetween(Integer value1, Integer value2) {
            addCriterion("bar_id between", value1, value2, "barId");
            return (Criteria) this;
        }

        public Criteria andBarIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bar_id not between", value1, value2, "barId");
            return (Criteria) this;
        }

        public Criteria andStyleIsNull() {
            addCriterion("style is null");
            return (Criteria) this;
        }

        public Criteria andStyleIsNotNull() {
            addCriterion("style is not null");
            return (Criteria) this;
        }

        public Criteria andStyleEqualTo(String value) {
            addCriterion("style =", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotEqualTo(String value) {
            addCriterion("style <>", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThan(String value) {
            addCriterion("style >", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThanOrEqualTo(String value) {
            addCriterion("style >=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThan(String value) {
            addCriterion("style <", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThanOrEqualTo(String value) {
            addCriterion("style <=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLike(String value) {
            addCriterion("style like", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotLike(String value) {
            addCriterion("style not like", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleIn(List<String> values) {
            addCriterion("style in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotIn(List<String> values) {
            addCriterion("style not in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleBetween(String value1, String value2) {
            addCriterion("style between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotBetween(String value1, String value2) {
            addCriterion("style not between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andItemcountIsNull() {
            addCriterion("itemCount is null");
            return (Criteria) this;
        }

        public Criteria andItemcountIsNotNull() {
            addCriterion("itemCount is not null");
            return (Criteria) this;
        }

        public Criteria andItemcountEqualTo(Integer value) {
            addCriterion("itemCount =", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotEqualTo(Integer value) {
            addCriterion("itemCount <>", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountGreaterThan(Integer value) {
            addCriterion("itemCount >", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("itemCount >=", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountLessThan(Integer value) {
            addCriterion("itemCount <", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountLessThanOrEqualTo(Integer value) {
            addCriterion("itemCount <=", value, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountIn(List<Integer> values) {
            addCriterion("itemCount in", values, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotIn(List<Integer> values) {
            addCriterion("itemCount not in", values, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountBetween(Integer value1, Integer value2) {
            addCriterion("itemCount between", value1, value2, "itemcount");
            return (Criteria) this;
        }

        public Criteria andItemcountNotBetween(Integer value1, Integer value2) {
            addCriterion("itemCount not between", value1, value2, "itemcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountIsNull() {
            addCriterion("currentcount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentcountIsNotNull() {
            addCriterion("currentcount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentcountEqualTo(Integer value) {
            addCriterion("currentcount =", value, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountNotEqualTo(Integer value) {
            addCriterion("currentcount <>", value, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountGreaterThan(Integer value) {
            addCriterion("currentcount >", value, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("currentcount >=", value, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountLessThan(Integer value) {
            addCriterion("currentcount <", value, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountLessThanOrEqualTo(Integer value) {
            addCriterion("currentcount <=", value, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountIn(List<Integer> values) {
            addCriterion("currentcount in", values, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountNotIn(List<Integer> values) {
            addCriterion("currentcount not in", values, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountBetween(Integer value1, Integer value2) {
            addCriterion("currentcount between", value1, value2, "currentcount");
            return (Criteria) this;
        }

        public Criteria andCurrentcountNotBetween(Integer value1, Integer value2) {
            addCriterion("currentcount not between", value1, value2, "currentcount");
            return (Criteria) this;
        }

        public Criteria andPageflagIsNull() {
            addCriterion("pageFlag is null");
            return (Criteria) this;
        }

        public Criteria andPageflagIsNotNull() {
            addCriterion("pageFlag is not null");
            return (Criteria) this;
        }

        public Criteria andPageflagEqualTo(Integer value) {
            addCriterion("pageFlag =", value, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagNotEqualTo(Integer value) {
            addCriterion("pageFlag <>", value, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagGreaterThan(Integer value) {
            addCriterion("pageFlag >", value, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("pageFlag >=", value, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagLessThan(Integer value) {
            addCriterion("pageFlag <", value, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagLessThanOrEqualTo(Integer value) {
            addCriterion("pageFlag <=", value, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagIn(List<Integer> values) {
            addCriterion("pageFlag in", values, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagNotIn(List<Integer> values) {
            addCriterion("pageFlag not in", values, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagBetween(Integer value1, Integer value2) {
            addCriterion("pageFlag between", value1, value2, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPageflagNotBetween(Integer value1, Integer value2) {
            addCriterion("pageFlag not between", value1, value2, "pageflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagIsNull() {
            addCriterion("positionFlag is null");
            return (Criteria) this;
        }

        public Criteria andPositionflagIsNotNull() {
            addCriterion("positionFlag is not null");
            return (Criteria) this;
        }

        public Criteria andPositionflagEqualTo(Integer value) {
            addCriterion("positionFlag =", value, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagNotEqualTo(Integer value) {
            addCriterion("positionFlag <>", value, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagGreaterThan(Integer value) {
            addCriterion("positionFlag >", value, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("positionFlag >=", value, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagLessThan(Integer value) {
            addCriterion("positionFlag <", value, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagLessThanOrEqualTo(Integer value) {
            addCriterion("positionFlag <=", value, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagIn(List<Integer> values) {
            addCriterion("positionFlag in", values, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagNotIn(List<Integer> values) {
            addCriterion("positionFlag not in", values, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagBetween(Integer value1, Integer value2) {
            addCriterion("positionFlag between", value1, value2, "positionflag");
            return (Criteria) this;
        }

        public Criteria andPositionflagNotBetween(Integer value1, Integer value2) {
            addCriterion("positionFlag not between", value1, value2, "positionflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagIsNull() {
            addCriterion("deleteFlag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteflagIsNotNull() {
            addCriterion("deleteFlag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteflagEqualTo(Integer value) {
            addCriterion("deleteFlag =", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagNotEqualTo(Integer value) {
            addCriterion("deleteFlag <>", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagGreaterThan(Integer value) {
            addCriterion("deleteFlag >", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("deleteFlag >=", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagLessThan(Integer value) {
            addCriterion("deleteFlag <", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagLessThanOrEqualTo(Integer value) {
            addCriterion("deleteFlag <=", value, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagIn(List<Integer> values) {
            addCriterion("deleteFlag in", values, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagNotIn(List<Integer> values) {
            addCriterion("deleteFlag not in", values, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagBetween(Integer value1, Integer value2) {
            addCriterion("deleteFlag between", value1, value2, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andDeleteflagNotBetween(Integer value1, Integer value2) {
            addCriterion("deleteFlag not between", value1, value2, "deleteflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagIsNull() {
            addCriterion("styleFlag is null");
            return (Criteria) this;
        }

        public Criteria andStyleflagIsNotNull() {
            addCriterion("styleFlag is not null");
            return (Criteria) this;
        }

        public Criteria andStyleflagEqualTo(Integer value) {
            addCriterion("styleFlag =", value, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagNotEqualTo(Integer value) {
            addCriterion("styleFlag <>", value, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagGreaterThan(Integer value) {
            addCriterion("styleFlag >", value, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("styleFlag >=", value, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagLessThan(Integer value) {
            addCriterion("styleFlag <", value, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagLessThanOrEqualTo(Integer value) {
            addCriterion("styleFlag <=", value, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagIn(List<Integer> values) {
            addCriterion("styleFlag in", values, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagNotIn(List<Integer> values) {
            addCriterion("styleFlag not in", values, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagBetween(Integer value1, Integer value2) {
            addCriterion("styleFlag between", value1, value2, "styleflag");
            return (Criteria) this;
        }

        public Criteria andStyleflagNotBetween(Integer value1, Integer value2) {
            addCriterion("styleFlag not between", value1, value2, "styleflag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table common_adandactivitybar
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
     * This class corresponds to the database table common_adandactivitybar
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