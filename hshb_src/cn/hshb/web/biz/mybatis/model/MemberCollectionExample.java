package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberCollectionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public MemberCollectionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
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
     * This method corresponds to the database table member_collection
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
     * This method corresponds to the database table member_collection
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_collection
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
     * This class corresponds to the database table member_collection
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

        public Criteria andCollectionIdIsNull() {
            addCriterion("collection_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIsNotNull() {
            addCriterion("collection_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionIdEqualTo(Integer value) {
            addCriterion("collection_id =", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotEqualTo(Integer value) {
            addCriterion("collection_id <>", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdGreaterThan(Integer value) {
            addCriterion("collection_id >", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_id >=", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdLessThan(Integer value) {
            addCriterion("collection_id <", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("collection_id <=", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIn(List<Integer> values) {
            addCriterion("collection_id in", values, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotIn(List<Integer> values) {
            addCriterion("collection_id not in", values, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdBetween(Integer value1, Integer value2) {
            addCriterion("collection_id between", value1, value2, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_id not between", value1, value2, "collectionId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIsNull() {
            addCriterion("collect_type is null");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIsNotNull() {
            addCriterion("collect_type is not null");
            return (Criteria) this;
        }

        public Criteria andCollectTypeEqualTo(Integer value) {
            addCriterion("collect_type =", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotEqualTo(Integer value) {
            addCriterion("collect_type <>", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeGreaterThan(Integer value) {
            addCriterion("collect_type >", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_type >=", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLessThan(Integer value) {
            addCriterion("collect_type <", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLessThanOrEqualTo(Integer value) {
            addCriterion("collect_type <=", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIn(List<Integer> values) {
            addCriterion("collect_type in", values, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotIn(List<Integer> values) {
            addCriterion("collect_type not in", values, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeBetween(Integer value1, Integer value2) {
            addCriterion("collect_type between", value1, value2, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_type not between", value1, value2, "collectType");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNull() {
            addCriterion("object_id is null");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNotNull() {
            addCriterion("object_id is not null");
            return (Criteria) this;
        }

        public Criteria andObjectIdEqualTo(String value) {
            addCriterion("object_id =", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotEqualTo(String value) {
            addCriterion("object_id <>", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThan(String value) {
            addCriterion("object_id >", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("object_id >=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThan(String value) {
            addCriterion("object_id <", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThanOrEqualTo(String value) {
            addCriterion("object_id <=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLike(String value) {
            addCriterion("object_id like", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotLike(String value) {
            addCriterion("object_id not like", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIn(List<String> values) {
            addCriterion("object_id in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotIn(List<String> values) {
            addCriterion("object_id not in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdBetween(String value1, String value2) {
            addCriterion("object_id between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotBetween(String value1, String value2) {
            addCriterion("object_id not between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedIsNull() {
            addCriterion("price_collected is null");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedIsNotNull() {
            addCriterion("price_collected is not null");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedEqualTo(Float value) {
            addCriterion("price_collected =", value, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedNotEqualTo(Float value) {
            addCriterion("price_collected <>", value, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedGreaterThan(Float value) {
            addCriterion("price_collected >", value, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedGreaterThanOrEqualTo(Float value) {
            addCriterion("price_collected >=", value, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedLessThan(Float value) {
            addCriterion("price_collected <", value, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedLessThanOrEqualTo(Float value) {
            addCriterion("price_collected <=", value, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedIn(List<Float> values) {
            addCriterion("price_collected in", values, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedNotIn(List<Float> values) {
            addCriterion("price_collected not in", values, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedBetween(Float value1, Float value2) {
            addCriterion("price_collected between", value1, value2, "priceCollected");
            return (Criteria) this;
        }

        public Criteria andPriceCollectedNotBetween(Float value1, Float value2) {
            addCriterion("price_collected not between", value1, value2, "priceCollected");
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

        public Criteria andBrokerIdIsNull() {
            addCriterion("broker_id is null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIsNotNull() {
            addCriterion("broker_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdEqualTo(Object value) {
            addCriterion("broker_id =", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotEqualTo(Object value) {
            addCriterion("broker_id <>", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThan(Object value) {
            addCriterion("broker_id >", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThanOrEqualTo(Object value) {
            addCriterion("broker_id >=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThan(Object value) {
            addCriterion("broker_id <", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThanOrEqualTo(Object value) {
            addCriterion("broker_id <=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIn(List<Object> values) {
            addCriterion("broker_id in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotIn(List<Object> values) {
            addCriterion("broker_id not in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdBetween(Object value1, Object value2) {
            addCriterion("broker_id between", value1, value2, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotBetween(Object value1, Object value2) {
            addCriterion("broker_id not between", value1, value2, "brokerId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table member_collection
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
     * This class corresponds to the database table member_collection
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