package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseCommunityHospitalMappingExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public HouseCommunityHospitalMappingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
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
     * This method corresponds to the database table house_community_hospital_mapping
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
     * This method corresponds to the database table house_community_hospital_mapping
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house_community_hospital_mapping
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
     * This class corresponds to the database table house_community_hospital_mapping
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

        public Criteria andCommunityIdIsNull() {
            addCriterion("community_id is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIsNotNull() {
            addCriterion("community_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdEqualTo(String value) {
            addCriterion("community_id =", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotEqualTo(String value) {
            addCriterion("community_id <>", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdGreaterThan(String value) {
            addCriterion("community_id >", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdGreaterThanOrEqualTo(String value) {
            addCriterion("community_id >=", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLessThan(String value) {
            addCriterion("community_id <", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLessThanOrEqualTo(String value) {
            addCriterion("community_id <=", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLike(String value) {
            addCriterion("community_id like", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotLike(String value) {
            addCriterion("community_id not like", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIn(List<String> values) {
            addCriterion("community_id in", values, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotIn(List<String> values) {
            addCriterion("community_id not in", values, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdBetween(String value1, String value2) {
            addCriterion("community_id between", value1, value2, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotBetween(String value1, String value2) {
            addCriterion("community_id not between", value1, value2, "communityId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIsNull() {
            addCriterion("hospital_id is null");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIsNotNull() {
            addCriterion("hospital_id is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalIdEqualTo(String value) {
            addCriterion("hospital_id =", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotEqualTo(String value) {
            addCriterion("hospital_id <>", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdGreaterThan(String value) {
            addCriterion("hospital_id >", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdGreaterThanOrEqualTo(String value) {
            addCriterion("hospital_id >=", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLessThan(String value) {
            addCriterion("hospital_id <", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLessThanOrEqualTo(String value) {
            addCriterion("hospital_id <=", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLike(String value) {
            addCriterion("hospital_id like", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotLike(String value) {
            addCriterion("hospital_id not like", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIn(List<String> values) {
            addCriterion("hospital_id in", values, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotIn(List<String> values) {
            addCriterion("hospital_id not in", values, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdBetween(String value1, String value2) {
            addCriterion("hospital_id between", value1, value2, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotBetween(String value1, String value2) {
            addCriterion("hospital_id not between", value1, value2, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(Float value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(Float value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(Float value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(Float value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(Float value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(Float value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<Float> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<Float> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(Float value1, Float value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(Float value1, Float value2) {
            addCriterion("distance not between", value1, value2, "distance");
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
            addCriterion("lastmodified =", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotEqualTo(Date value) {
            addCriterion("lastmodified <>", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedGreaterThan(Date value) {
            addCriterion("lastmodified >", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("lastmodified >=", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedLessThan(Date value) {
            addCriterion("lastmodified <", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedLessThanOrEqualTo(Date value) {
            addCriterion("lastmodified <=", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIn(List<Date> values) {
            addCriterion("lastmodified in", values, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotIn(List<Date> values) {
            addCriterion("lastmodified not in", values, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedBetween(Date value1, Date value2) {
            addCriterion("lastmodified between", value1, value2, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotBetween(Date value1, Date value2) {
            addCriterion("lastmodified not between", value1, value2, "lastmodified");
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
            addCriterion("lastsync =", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncNotEqualTo(Date value) {
            addCriterion("lastsync <>", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncGreaterThan(Date value) {
            addCriterion("lastsync >", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncGreaterThanOrEqualTo(Date value) {
            addCriterion("lastsync >=", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncLessThan(Date value) {
            addCriterion("lastsync <", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncLessThanOrEqualTo(Date value) {
            addCriterion("lastsync <=", value, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncIn(List<Date> values) {
            addCriterion("lastsync in", values, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncNotIn(List<Date> values) {
            addCriterion("lastsync not in", values, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncBetween(Date value1, Date value2) {
            addCriterion("lastsync between", value1, value2, "lastsync");
            return (Criteria) this;
        }

        public Criteria andLastsyncNotBetween(Date value1, Date value2) {
            addCriterion("lastsync not between", value1, value2, "lastsync");
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
     * This class corresponds to the database table house_community_hospital_mapping
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
     * This class corresponds to the database table house_community_hospital_mapping
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