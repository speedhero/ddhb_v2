package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonStoreExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public CommonStoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
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
     * This method corresponds to the database table common_store
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
     * This method corresponds to the database table common_store
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_store
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
     * This class corresponds to the database table common_store
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

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNull() {
            addCriterion("store_address is null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNotNull() {
            addCriterion("store_address is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressEqualTo(String value) {
            addCriterion("store_address =", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotEqualTo(String value) {
            addCriterion("store_address <>", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThan(String value) {
            addCriterion("store_address >", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThanOrEqualTo(String value) {
            addCriterion("store_address >=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThan(String value) {
            addCriterion("store_address <", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThanOrEqualTo(String value) {
            addCriterion("store_address <=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLike(String value) {
            addCriterion("store_address like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotLike(String value) {
            addCriterion("store_address not like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIn(List<String> values) {
            addCriterion("store_address in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotIn(List<String> values) {
            addCriterion("store_address not in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressBetween(String value1, String value2) {
            addCriterion("store_address between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotBetween(String value1, String value2) {
            addCriterion("store_address not between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoIsNull() {
            addCriterion("telephone_no is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoIsNotNull() {
            addCriterion("telephone_no is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoEqualTo(String value) {
            addCriterion("telephone_no =", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoNotEqualTo(String value) {
            addCriterion("telephone_no <>", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoGreaterThan(String value) {
            addCriterion("telephone_no >", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoGreaterThanOrEqualTo(String value) {
            addCriterion("telephone_no >=", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoLessThan(String value) {
            addCriterion("telephone_no <", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoLessThanOrEqualTo(String value) {
            addCriterion("telephone_no <=", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoLike(String value) {
            addCriterion("telephone_no like", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoNotLike(String value) {
            addCriterion("telephone_no not like", value, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoIn(List<String> values) {
            addCriterion("telephone_no in", values, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoNotIn(List<String> values) {
            addCriterion("telephone_no not in", values, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoBetween(String value1, String value2) {
            addCriterion("telephone_no between", value1, value2, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andTelephoneNoNotBetween(String value1, String value2) {
            addCriterion("telephone_no not between", value1, value2, "telephoneNo");
            return (Criteria) this;
        }

        public Criteria andCbdIdIsNull() {
            addCriterion("cbd_id is null");
            return (Criteria) this;
        }

        public Criteria andCbdIdIsNotNull() {
            addCriterion("cbd_id is not null");
            return (Criteria) this;
        }

        public Criteria andCbdIdEqualTo(String value) {
            addCriterion("cbd_id =", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdNotEqualTo(String value) {
            addCriterion("cbd_id <>", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdGreaterThan(String value) {
            addCriterion("cbd_id >", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdGreaterThanOrEqualTo(String value) {
            addCriterion("cbd_id >=", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdLessThan(String value) {
            addCriterion("cbd_id <", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdLessThanOrEqualTo(String value) {
            addCriterion("cbd_id <=", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdLike(String value) {
            addCriterion("cbd_id like", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdNotLike(String value) {
            addCriterion("cbd_id not like", value, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdIn(List<String> values) {
            addCriterion("cbd_id in", values, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdNotIn(List<String> values) {
            addCriterion("cbd_id not in", values, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdBetween(String value1, String value2) {
            addCriterion("cbd_id between", value1, value2, "cbdId");
            return (Criteria) this;
        }

        public Criteria andCbdIdNotBetween(String value1, String value2) {
            addCriterion("cbd_id not between", value1, value2, "cbdId");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
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
     * This class corresponds to the database table common_store
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
     * This class corresponds to the database table common_store
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