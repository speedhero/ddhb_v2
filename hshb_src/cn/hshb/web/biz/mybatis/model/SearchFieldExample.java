package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class SearchFieldExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public SearchFieldExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
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
     * This method corresponds to the database table search_field
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
     * This method corresponds to the database table search_field
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table search_field
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
     * This class corresponds to the database table search_field
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSearchitemIsNull() {
            addCriterion("searchitem is null");
            return (Criteria) this;
        }

        public Criteria andSearchitemIsNotNull() {
            addCriterion("searchitem is not null");
            return (Criteria) this;
        }

        public Criteria andSearchitemEqualTo(Integer value) {
            addCriterion("searchitem =", value, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemNotEqualTo(Integer value) {
            addCriterion("searchitem <>", value, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemGreaterThan(Integer value) {
            addCriterion("searchitem >", value, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemGreaterThanOrEqualTo(Integer value) {
            addCriterion("searchitem >=", value, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemLessThan(Integer value) {
            addCriterion("searchitem <", value, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemLessThanOrEqualTo(Integer value) {
            addCriterion("searchitem <=", value, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemIn(List<Integer> values) {
            addCriterion("searchitem in", values, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemNotIn(List<Integer> values) {
            addCriterion("searchitem not in", values, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemBetween(Integer value1, Integer value2) {
            addCriterion("searchitem between", value1, value2, "searchitem");
            return (Criteria) this;
        }

        public Criteria andSearchitemNotBetween(Integer value1, Integer value2) {
            addCriterion("searchitem not between", value1, value2, "searchitem");
            return (Criteria) this;
        }

        public Criteria andFieldnameIsNull() {
            addCriterion("fieldname is null");
            return (Criteria) this;
        }

        public Criteria andFieldnameIsNotNull() {
            addCriterion("fieldname is not null");
            return (Criteria) this;
        }

        public Criteria andFieldnameEqualTo(String value) {
            addCriterion("fieldname =", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameNotEqualTo(String value) {
            addCriterion("fieldname <>", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameGreaterThan(String value) {
            addCriterion("fieldname >", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameGreaterThanOrEqualTo(String value) {
            addCriterion("fieldname >=", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameLessThan(String value) {
            addCriterion("fieldname <", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameLessThanOrEqualTo(String value) {
            addCriterion("fieldname <=", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameLike(String value) {
            addCriterion("fieldname like", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameNotLike(String value) {
            addCriterion("fieldname not like", value, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameIn(List<String> values) {
            addCriterion("fieldname in", values, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameNotIn(List<String> values) {
            addCriterion("fieldname not in", values, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameBetween(String value1, String value2) {
            addCriterion("fieldname between", value1, value2, "fieldname");
            return (Criteria) this;
        }

        public Criteria andFieldnameNotBetween(String value1, String value2) {
            addCriterion("fieldname not between", value1, value2, "fieldname");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueIsNull() {
            addCriterion("maxfieldvalue is null");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueIsNotNull() {
            addCriterion("maxfieldvalue is not null");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueEqualTo(String value) {
            addCriterion("maxfieldvalue =", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueNotEqualTo(String value) {
            addCriterion("maxfieldvalue <>", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueGreaterThan(String value) {
            addCriterion("maxfieldvalue >", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueGreaterThanOrEqualTo(String value) {
            addCriterion("maxfieldvalue >=", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueLessThan(String value) {
            addCriterion("maxfieldvalue <", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueLessThanOrEqualTo(String value) {
            addCriterion("maxfieldvalue <=", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueLike(String value) {
            addCriterion("maxfieldvalue like", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueNotLike(String value) {
            addCriterion("maxfieldvalue not like", value, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueIn(List<String> values) {
            addCriterion("maxfieldvalue in", values, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueNotIn(List<String> values) {
            addCriterion("maxfieldvalue not in", values, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueBetween(String value1, String value2) {
            addCriterion("maxfieldvalue between", value1, value2, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMaxfieldvalueNotBetween(String value1, String value2) {
            addCriterion("maxfieldvalue not between", value1, value2, "maxfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueIsNull() {
            addCriterion("minfieldvalue is null");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueIsNotNull() {
            addCriterion("minfieldvalue is not null");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueEqualTo(String value) {
            addCriterion("minfieldvalue =", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueNotEqualTo(String value) {
            addCriterion("minfieldvalue <>", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueGreaterThan(String value) {
            addCriterion("minfieldvalue >", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueGreaterThanOrEqualTo(String value) {
            addCriterion("minfieldvalue >=", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueLessThan(String value) {
            addCriterion("minfieldvalue <", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueLessThanOrEqualTo(String value) {
            addCriterion("minfieldvalue <=", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueLike(String value) {
            addCriterion("minfieldvalue like", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueNotLike(String value) {
            addCriterion("minfieldvalue not like", value, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueIn(List<String> values) {
            addCriterion("minfieldvalue in", values, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueNotIn(List<String> values) {
            addCriterion("minfieldvalue not in", values, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueBetween(String value1, String value2) {
            addCriterion("minfieldvalue between", value1, value2, "minfieldvalue");
            return (Criteria) this;
        }

        public Criteria andMinfieldvalueNotBetween(String value1, String value2) {
            addCriterion("minfieldvalue not between", value1, value2, "minfieldvalue");
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
     * This class corresponds to the database table search_field
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
     * This class corresponds to the database table search_field
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