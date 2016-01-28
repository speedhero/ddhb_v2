package cn.hshb.web.biz.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FwScheduleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public FwScheduleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
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
     * This method corresponds to the database table fw_schedule
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
     * This method corresponds to the database table fw_schedule
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fw_schedule
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
     * This class corresponds to the database table fw_schedule
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

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(String value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(String value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(String value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(String value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(String value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLike(String value) {
            addCriterion("job_id like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotLike(String value) {
            addCriterion("job_id not like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<String> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<String> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(String value1, String value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(String value1, String value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("job_name is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("job_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("job_name =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("job_name <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("job_name >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_name >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("job_name <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("job_name <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("job_name like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("job_name not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("job_name in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("job_name not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("job_name between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("job_name not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobGroupIsNull() {
            addCriterion("job_group is null");
            return (Criteria) this;
        }

        public Criteria andJobGroupIsNotNull() {
            addCriterion("job_group is not null");
            return (Criteria) this;
        }

        public Criteria andJobGroupEqualTo(String value) {
            addCriterion("job_group =", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotEqualTo(String value) {
            addCriterion("job_group <>", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupGreaterThan(String value) {
            addCriterion("job_group >", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupGreaterThanOrEqualTo(String value) {
            addCriterion("job_group >=", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLessThan(String value) {
            addCriterion("job_group <", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLessThanOrEqualTo(String value) {
            addCriterion("job_group <=", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupLike(String value) {
            addCriterion("job_group like", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotLike(String value) {
            addCriterion("job_group not like", value, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupIn(List<String> values) {
            addCriterion("job_group in", values, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotIn(List<String> values) {
            addCriterion("job_group not in", values, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupBetween(String value1, String value2) {
            addCriterion("job_group between", value1, value2, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobGroupNotBetween(String value1, String value2) {
            addCriterion("job_group not between", value1, value2, "jobGroup");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNull() {
            addCriterion("job_status is null");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNotNull() {
            addCriterion("job_status is not null");
            return (Criteria) this;
        }

        public Criteria andJobStatusEqualTo(Integer value) {
            addCriterion("job_status =", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotEqualTo(Integer value) {
            addCriterion("job_status <>", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThan(Integer value) {
            addCriterion("job_status >", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_status >=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThan(Integer value) {
            addCriterion("job_status <", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThanOrEqualTo(Integer value) {
            addCriterion("job_status <=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusIn(List<Integer> values) {
            addCriterion("job_status in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotIn(List<Integer> values) {
            addCriterion("job_status not in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusBetween(Integer value1, Integer value2) {
            addCriterion("job_status between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("job_status not between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNull() {
            addCriterion("cron_expression is null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNotNull() {
            addCriterion("cron_expression is not null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionEqualTo(String value) {
            addCriterion("cron_expression =", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotEqualTo(String value) {
            addCriterion("cron_expression <>", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThan(String value) {
            addCriterion("cron_expression >", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("cron_expression >=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThan(String value) {
            addCriterion("cron_expression <", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("cron_expression <=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLike(String value) {
            addCriterion("cron_expression like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotLike(String value) {
            addCriterion("cron_expression not like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIn(List<String> values) {
            addCriterion("cron_expression in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotIn(List<String> values) {
            addCriterion("cron_expression not in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionBetween(String value1, String value2) {
            addCriterion("cron_expression between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotBetween(String value1, String value2) {
            addCriterion("cron_expression not between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andMemosIsNull() {
            addCriterion("memos is null");
            return (Criteria) this;
        }

        public Criteria andMemosIsNotNull() {
            addCriterion("memos is not null");
            return (Criteria) this;
        }

        public Criteria andMemosEqualTo(String value) {
            addCriterion("memos =", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosNotEqualTo(String value) {
            addCriterion("memos <>", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosGreaterThan(String value) {
            addCriterion("memos >", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosGreaterThanOrEqualTo(String value) {
            addCriterion("memos >=", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosLessThan(String value) {
            addCriterion("memos <", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosLessThanOrEqualTo(String value) {
            addCriterion("memos <=", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosLike(String value) {
            addCriterion("memos like", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosNotLike(String value) {
            addCriterion("memos not like", value, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosIn(List<String> values) {
            addCriterion("memos in", values, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosNotIn(List<String> values) {
            addCriterion("memos not in", values, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosBetween(String value1, String value2) {
            addCriterion("memos between", value1, value2, "memos");
            return (Criteria) this;
        }

        public Criteria andMemosNotBetween(String value1, String value2) {
            addCriterion("memos not between", value1, value2, "memos");
            return (Criteria) this;
        }

        public Criteria andIsSynchIsNull() {
            addCriterion("is_synch is null");
            return (Criteria) this;
        }

        public Criteria andIsSynchIsNotNull() {
            addCriterion("is_synch is not null");
            return (Criteria) this;
        }

        public Criteria andIsSynchEqualTo(Integer value) {
            addCriterion("is_synch =", value, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchNotEqualTo(Integer value) {
            addCriterion("is_synch <>", value, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchGreaterThan(Integer value) {
            addCriterion("is_synch >", value, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_synch >=", value, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchLessThan(Integer value) {
            addCriterion("is_synch <", value, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchLessThanOrEqualTo(Integer value) {
            addCriterion("is_synch <=", value, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchIn(List<Integer> values) {
            addCriterion("is_synch in", values, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchNotIn(List<Integer> values) {
            addCriterion("is_synch not in", values, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchBetween(Integer value1, Integer value2) {
            addCriterion("is_synch between", value1, value2, "isSynch");
            return (Criteria) this;
        }

        public Criteria andIsSynchNotBetween(Integer value1, Integer value2) {
            addCriterion("is_synch not between", value1, value2, "isSynch");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIsNull() {
            addCriterion("execute_type is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIsNotNull() {
            addCriterion("execute_type is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeEqualTo(Integer value) {
            addCriterion("execute_type =", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotEqualTo(Integer value) {
            addCriterion("execute_type <>", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeGreaterThan(Integer value) {
            addCriterion("execute_type >", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("execute_type >=", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLessThan(Integer value) {
            addCriterion("execute_type <", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLessThanOrEqualTo(Integer value) {
            addCriterion("execute_type <=", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIn(List<Integer> values) {
            addCriterion("execute_type in", values, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotIn(List<Integer> values) {
            addCriterion("execute_type not in", values, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeBetween(Integer value1, Integer value2) {
            addCriterion("execute_type between", value1, value2, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("execute_type not between", value1, value2, "executeType");
            return (Criteria) this;
        }

        public Criteria andParamsIsNull() {
            addCriterion("params is null");
            return (Criteria) this;
        }

        public Criteria andParamsIsNotNull() {
            addCriterion("params is not null");
            return (Criteria) this;
        }

        public Criteria andParamsEqualTo(String value) {
            addCriterion("params =", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotEqualTo(String value) {
            addCriterion("params <>", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThan(String value) {
            addCriterion("params >", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThanOrEqualTo(String value) {
            addCriterion("params >=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThan(String value) {
            addCriterion("params <", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThanOrEqualTo(String value) {
            addCriterion("params <=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLike(String value) {
            addCriterion("params like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotLike(String value) {
            addCriterion("params not like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsIn(List<String> values) {
            addCriterion("params in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotIn(List<String> values) {
            addCriterion("params not in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsBetween(String value1, String value2) {
            addCriterion("params between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotBetween(String value1, String value2) {
            addCriterion("params not between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeIsNull() {
            addCriterion("execute_time_type is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeIsNotNull() {
            addCriterion("execute_time_type is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeEqualTo(Integer value) {
            addCriterion("execute_time_type =", value, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeNotEqualTo(Integer value) {
            addCriterion("execute_time_type <>", value, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeGreaterThan(Integer value) {
            addCriterion("execute_time_type >", value, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("execute_time_type >=", value, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeLessThan(Integer value) {
            addCriterion("execute_time_type <", value, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("execute_time_type <=", value, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeIn(List<Integer> values) {
            addCriterion("execute_time_type in", values, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeNotIn(List<Integer> values) {
            addCriterion("execute_time_type not in", values, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeBetween(Integer value1, Integer value2) {
            addCriterion("execute_time_type between", value1, value2, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("execute_time_type not between", value1, value2, "executeTimeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueIsNull() {
            addCriterion("execute_time_value is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueIsNotNull() {
            addCriterion("execute_time_value is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueEqualTo(Integer value) {
            addCriterion("execute_time_value =", value, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueNotEqualTo(Integer value) {
            addCriterion("execute_time_value <>", value, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueGreaterThan(Integer value) {
            addCriterion("execute_time_value >", value, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("execute_time_value >=", value, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueLessThan(Integer value) {
            addCriterion("execute_time_value <", value, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueLessThanOrEqualTo(Integer value) {
            addCriterion("execute_time_value <=", value, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueIn(List<Integer> values) {
            addCriterion("execute_time_value in", values, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueNotIn(List<Integer> values) {
            addCriterion("execute_time_value not in", values, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueBetween(Integer value1, Integer value2) {
            addCriterion("execute_time_value between", value1, value2, "executeTimeValue");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeValueNotBetween(Integer value1, Integer value2) {
            addCriterion("execute_time_value not between", value1, value2, "executeTimeValue");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fw_schedule
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
     * This class corresponds to the database table fw_schedule
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