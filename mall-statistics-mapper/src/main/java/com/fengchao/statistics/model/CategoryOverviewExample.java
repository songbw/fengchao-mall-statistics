package com.fengchao.statistics.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CategoryOverviewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CategoryOverviewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeIsNull() {
            addCriterion("category_fcode is null");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeIsNotNull() {
            addCriterion("category_fcode is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeEqualTo(String value) {
            addCriterion("category_fcode =", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeNotEqualTo(String value) {
            addCriterion("category_fcode <>", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeGreaterThan(String value) {
            addCriterion("category_fcode >", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeGreaterThanOrEqualTo(String value) {
            addCriterion("category_fcode >=", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeLessThan(String value) {
            addCriterion("category_fcode <", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeLessThanOrEqualTo(String value) {
            addCriterion("category_fcode <=", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeLike(String value) {
            addCriterion("category_fcode like", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeNotLike(String value) {
            addCriterion("category_fcode not like", value, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeIn(List<String> values) {
            addCriterion("category_fcode in", values, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeNotIn(List<String> values) {
            addCriterion("category_fcode not in", values, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeBetween(String value1, String value2) {
            addCriterion("category_fcode between", value1, value2, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFcodeNotBetween(String value1, String value2) {
            addCriterion("category_fcode not between", value1, value2, "categoryFcode");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameIsNull() {
            addCriterion("category_fname is null");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameIsNotNull() {
            addCriterion("category_fname is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameEqualTo(String value) {
            addCriterion("category_fname =", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameNotEqualTo(String value) {
            addCriterion("category_fname <>", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameGreaterThan(String value) {
            addCriterion("category_fname >", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameGreaterThanOrEqualTo(String value) {
            addCriterion("category_fname >=", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameLessThan(String value) {
            addCriterion("category_fname <", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameLessThanOrEqualTo(String value) {
            addCriterion("category_fname <=", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameLike(String value) {
            addCriterion("category_fname like", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameNotLike(String value) {
            addCriterion("category_fname not like", value, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameIn(List<String> values) {
            addCriterion("category_fname in", values, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameNotIn(List<String> values) {
            addCriterion("category_fname not in", values, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameBetween(String value1, String value2) {
            addCriterion("category_fname between", value1, value2, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andCategoryFnameNotBetween(String value1, String value2) {
            addCriterion("category_fname not between", value1, value2, "categoryFname");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Long value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Long value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Long value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Long value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Long value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Long> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Long> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Long value1, Long value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Long value1, Long value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateIsNull() {
            addCriterion("statistics_date is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateIsNotNull() {
            addCriterion("statistics_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateEqualTo(Date value) {
            addCriterionForJDBCDate("statistics_date =", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("statistics_date <>", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateGreaterThan(Date value) {
            addCriterionForJDBCDate("statistics_date >", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("statistics_date >=", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateLessThan(Date value) {
            addCriterionForJDBCDate("statistics_date <", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("statistics_date <=", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateIn(List<Date> values) {
            addCriterionForJDBCDate("statistics_date in", values, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("statistics_date not in", values, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("statistics_date between", value1, value2, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("statistics_date not between", value1, value2, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeIsNull() {
            addCriterion("statistic_start_time is null");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeIsNotNull() {
            addCriterion("statistic_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeEqualTo(Date value) {
            addCriterion("statistic_start_time =", value, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeNotEqualTo(Date value) {
            addCriterion("statistic_start_time <>", value, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeGreaterThan(Date value) {
            addCriterion("statistic_start_time >", value, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("statistic_start_time >=", value, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeLessThan(Date value) {
            addCriterion("statistic_start_time <", value, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("statistic_start_time <=", value, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeIn(List<Date> values) {
            addCriterion("statistic_start_time in", values, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeNotIn(List<Date> values) {
            addCriterion("statistic_start_time not in", values, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeBetween(Date value1, Date value2) {
            addCriterion("statistic_start_time between", value1, value2, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("statistic_start_time not between", value1, value2, "statisticStartTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeIsNull() {
            addCriterion("statistic_end_time is null");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeIsNotNull() {
            addCriterion("statistic_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeEqualTo(Date value) {
            addCriterion("statistic_end_time =", value, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeNotEqualTo(Date value) {
            addCriterion("statistic_end_time <>", value, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeGreaterThan(Date value) {
            addCriterion("statistic_end_time >", value, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("statistic_end_time >=", value, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeLessThan(Date value) {
            addCriterion("statistic_end_time <", value, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("statistic_end_time <=", value, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeIn(List<Date> values) {
            addCriterion("statistic_end_time in", values, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeNotIn(List<Date> values) {
            addCriterion("statistic_end_time not in", values, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeBetween(Date value1, Date value2) {
            addCriterion("statistic_end_time between", value1, value2, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andStatisticEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("statistic_end_time not between", value1, value2, "statisticEndTime");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNull() {
            addCriterion("period_type is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNotNull() {
            addCriterion("period_type is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeEqualTo(Short value) {
            addCriterion("period_type =", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotEqualTo(Short value) {
            addCriterion("period_type <>", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThan(Short value) {
            addCriterion("period_type >", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("period_type >=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThan(Short value) {
            addCriterion("period_type <", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThanOrEqualTo(Short value) {
            addCriterion("period_type <=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIn(List<Short> values) {
            addCriterion("period_type in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotIn(List<Short> values) {
            addCriterion("period_type not in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeBetween(Short value1, Short value2) {
            addCriterion("period_type between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotBetween(Short value1, Short value2) {
            addCriterion("period_type not between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andIstatusIsNull() {
            addCriterion("istatus is null");
            return (Criteria) this;
        }

        public Criteria andIstatusIsNotNull() {
            addCriterion("istatus is not null");
            return (Criteria) this;
        }

        public Criteria andIstatusEqualTo(Short value) {
            addCriterion("istatus =", value, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusNotEqualTo(Short value) {
            addCriterion("istatus <>", value, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusGreaterThan(Short value) {
            addCriterion("istatus >", value, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusGreaterThanOrEqualTo(Short value) {
            addCriterion("istatus >=", value, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusLessThan(Short value) {
            addCriterion("istatus <", value, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusLessThanOrEqualTo(Short value) {
            addCriterion("istatus <=", value, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusIn(List<Short> values) {
            addCriterion("istatus in", values, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusNotIn(List<Short> values) {
            addCriterion("istatus not in", values, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusBetween(Short value1, Short value2) {
            addCriterion("istatus between", value1, value2, "istatus");
            return (Criteria) this;
        }

        public Criteria andIstatusNotBetween(Short value1, Short value2) {
            addCriterion("istatus not between", value1, value2, "istatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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