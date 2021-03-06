package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonHospitalTableExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public CommonHospitalTableExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
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
     * This method corresponds to the database table common_hospital_table
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
     * This method corresponds to the database table common_hospital_table
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_hospital_table
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
     * This class corresponds to the database table common_hospital_table
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

        public Criteria andHospitalNameIsNull() {
            addCriterion("hospital_name is null");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIsNotNull() {
            addCriterion("hospital_name is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalNameEqualTo(String value) {
            addCriterion("hospital_name =", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotEqualTo(String value) {
            addCriterion("hospital_name <>", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameGreaterThan(String value) {
            addCriterion("hospital_name >", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameGreaterThanOrEqualTo(String value) {
            addCriterion("hospital_name >=", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLessThan(String value) {
            addCriterion("hospital_name <", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLessThanOrEqualTo(String value) {
            addCriterion("hospital_name <=", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameLike(String value) {
            addCriterion("hospital_name like", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotLike(String value) {
            addCriterion("hospital_name not like", value, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameIn(List<String> values) {
            addCriterion("hospital_name in", values, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotIn(List<String> values) {
            addCriterion("hospital_name not in", values, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameBetween(String value1, String value2) {
            addCriterion("hospital_name between", value1, value2, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalNameNotBetween(String value1, String value2) {
            addCriterion("hospital_name not between", value1, value2, "hospitalName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIsNull() {
            addCriterion("hospital_type is null");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIsNotNull() {
            addCriterion("hospital_type is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeEqualTo(String value) {
            addCriterion("hospital_type =", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNotEqualTo(String value) {
            addCriterion("hospital_type <>", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeGreaterThan(String value) {
            addCriterion("hospital_type >", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeGreaterThanOrEqualTo(String value) {
            addCriterion("hospital_type >=", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeLessThan(String value) {
            addCriterion("hospital_type <", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeLessThanOrEqualTo(String value) {
            addCriterion("hospital_type <=", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeLike(String value) {
            addCriterion("hospital_type like", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNotLike(String value) {
            addCriterion("hospital_type not like", value, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIn(List<String> values) {
            addCriterion("hospital_type in", values, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNotIn(List<String> values) {
            addCriterion("hospital_type not in", values, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeBetween(String value1, String value2) {
            addCriterion("hospital_type between", value1, value2, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNotBetween(String value1, String value2) {
            addCriterion("hospital_type not between", value1, value2, "hospitalType");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("city_id like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("city_id not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNull() {
            addCriterion("region_id is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("region_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(String value) {
            addCriterion("region_id =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(String value) {
            addCriterion("region_id <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(String value) {
            addCriterion("region_id >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(String value) {
            addCriterion("region_id >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(String value) {
            addCriterion("region_id <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(String value) {
            addCriterion("region_id <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLike(String value) {
            addCriterion("region_id like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotLike(String value) {
            addCriterion("region_id not like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<String> values) {
            addCriterion("region_id in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<String> values) {
            addCriterion("region_id not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(String value1, String value2) {
            addCriterion("region_id between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(String value1, String value2) {
            addCriterion("region_id not between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andGpsLocationIsNull() {
            addCriterion("gps_location is null");
            return (Criteria) this;
        }

        public Criteria andGpsLocationIsNotNull() {
            addCriterion("gps_location is not null");
            return (Criteria) this;
        }

        public Criteria andGpsLocationEqualTo(String value) {
            addCriterion("gps_location =", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationNotEqualTo(String value) {
            addCriterion("gps_location <>", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationGreaterThan(String value) {
            addCriterion("gps_location >", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationGreaterThanOrEqualTo(String value) {
            addCriterion("gps_location >=", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationLessThan(String value) {
            addCriterion("gps_location <", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationLessThanOrEqualTo(String value) {
            addCriterion("gps_location <=", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationLike(String value) {
            addCriterion("gps_location like", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationNotLike(String value) {
            addCriterion("gps_location not like", value, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationIn(List<String> values) {
            addCriterion("gps_location in", values, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationNotIn(List<String> values) {
            addCriterion("gps_location not in", values, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationBetween(String value1, String value2) {
            addCriterion("gps_location between", value1, value2, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andGpsLocationNotBetween(String value1, String value2) {
            addCriterion("gps_location not between", value1, value2, "gpsLocation");
            return (Criteria) this;
        }

        public Criteria andSortindexIsNull() {
            addCriterion("sortindex is null");
            return (Criteria) this;
        }

        public Criteria andSortindexIsNotNull() {
            addCriterion("sortindex is not null");
            return (Criteria) this;
        }

        public Criteria andSortindexEqualTo(Integer value) {
            addCriterion("sortindex =", value, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexNotEqualTo(Integer value) {
            addCriterion("sortindex <>", value, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexGreaterThan(Integer value) {
            addCriterion("sortindex >", value, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortindex >=", value, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexLessThan(Integer value) {
            addCriterion("sortindex <", value, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexLessThanOrEqualTo(Integer value) {
            addCriterion("sortindex <=", value, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexIn(List<Integer> values) {
            addCriterion("sortindex in", values, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexNotIn(List<Integer> values) {
            addCriterion("sortindex not in", values, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexBetween(Integer value1, Integer value2) {
            addCriterion("sortindex between", value1, value2, "sortindex");
            return (Criteria) this;
        }

        public Criteria andSortindexNotBetween(Integer value1, Integer value2) {
            addCriterion("sortindex not between", value1, value2, "sortindex");
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
     * This class corresponds to the database table common_hospital_table
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
     * This class corresponds to the database table common_hospital_table
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