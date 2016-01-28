package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class SharedSearchExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public SharedSearchExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
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
     * This method corresponds to the database table shared_search
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
     * This method corresponds to the database table shared_search
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shared_search
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
     * This class corresponds to the database table shared_search
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

        public Criteria andSearchIdIsNull() {
            addCriterion("search_id is null");
            return (Criteria) this;
        }

        public Criteria andSearchIdIsNotNull() {
            addCriterion("search_id is not null");
            return (Criteria) this;
        }

        public Criteria andSearchIdEqualTo(String value) {
            addCriterion("search_id =", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdNotEqualTo(String value) {
            addCriterion("search_id <>", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdGreaterThan(String value) {
            addCriterion("search_id >", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdGreaterThanOrEqualTo(String value) {
            addCriterion("search_id >=", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdLessThan(String value) {
            addCriterion("search_id <", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdLessThanOrEqualTo(String value) {
            addCriterion("search_id <=", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdLike(String value) {
            addCriterion("search_id like", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdNotLike(String value) {
            addCriterion("search_id not like", value, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdIn(List<String> values) {
            addCriterion("search_id in", values, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdNotIn(List<String> values) {
            addCriterion("search_id not in", values, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdBetween(String value1, String value2) {
            addCriterion("search_id between", value1, value2, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchIdNotBetween(String value1, String value2) {
            addCriterion("search_id not between", value1, value2, "searchId");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerIsNull() {
            addCriterion("search_item_finger is null");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerIsNotNull() {
            addCriterion("search_item_finger is not null");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerEqualTo(String value) {
            addCriterion("search_item_finger =", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerNotEqualTo(String value) {
            addCriterion("search_item_finger <>", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerGreaterThan(String value) {
            addCriterion("search_item_finger >", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerGreaterThanOrEqualTo(String value) {
            addCriterion("search_item_finger >=", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerLessThan(String value) {
            addCriterion("search_item_finger <", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerLessThanOrEqualTo(String value) {
            addCriterion("search_item_finger <=", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerLike(String value) {
            addCriterion("search_item_finger like", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerNotLike(String value) {
            addCriterion("search_item_finger not like", value, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerIn(List<String> values) {
            addCriterion("search_item_finger in", values, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerNotIn(List<String> values) {
            addCriterion("search_item_finger not in", values, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerBetween(String value1, String value2) {
            addCriterion("search_item_finger between", value1, value2, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchItemFingerNotBetween(String value1, String value2) {
            addCriterion("search_item_finger not between", value1, value2, "searchItemFinger");
            return (Criteria) this;
        }

        public Criteria andSearchContentIsNull() {
            addCriterion("search_content is null");
            return (Criteria) this;
        }

        public Criteria andSearchContentIsNotNull() {
            addCriterion("search_content is not null");
            return (Criteria) this;
        }

        public Criteria andSearchContentEqualTo(String value) {
            addCriterion("search_content =", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentNotEqualTo(String value) {
            addCriterion("search_content <>", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentGreaterThan(String value) {
            addCriterion("search_content >", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentGreaterThanOrEqualTo(String value) {
            addCriterion("search_content >=", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentLessThan(String value) {
            addCriterion("search_content <", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentLessThanOrEqualTo(String value) {
            addCriterion("search_content <=", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentLike(String value) {
            addCriterion("search_content like", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentNotLike(String value) {
            addCriterion("search_content not like", value, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentIn(List<String> values) {
            addCriterion("search_content in", values, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentNotIn(List<String> values) {
            addCriterion("search_content not in", values, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentBetween(String value1, String value2) {
            addCriterion("search_content between", value1, value2, "searchContent");
            return (Criteria) this;
        }

        public Criteria andSearchContentNotBetween(String value1, String value2) {
            addCriterion("search_content not between", value1, value2, "searchContent");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Integer value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Integer value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Integer value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Integer value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Integer value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Integer> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Integer> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Integer value1, Integer value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table shared_search
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
     * This class corresponds to the database table shared_search
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