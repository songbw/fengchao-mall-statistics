package com.fengchao.statistics.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MStatisticUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MStatisticUserExample() {
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

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Integer value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Integer value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Integer value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Integer value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Integer> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Integer> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("merchant_code is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("merchant_code is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("merchant_code =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("merchant_code <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("merchant_code >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_code >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("merchant_code <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("merchant_code <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("merchant_code like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("merchant_code not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("merchant_code in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("merchant_code not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("merchant_code between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("merchant_code not between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNull() {
            addCriterion("merchant_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNotNull() {
            addCriterion("merchant_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameEqualTo(String value) {
            addCriterion("merchant_name =", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotEqualTo(String value) {
            addCriterion("merchant_name <>", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThan(String value) {
            addCriterion("merchant_name >", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_name >=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThan(String value) {
            addCriterion("merchant_name <", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThanOrEqualTo(String value) {
            addCriterion("merchant_name <=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLike(String value) {
            addCriterion("merchant_name like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotLike(String value) {
            addCriterion("merchant_name not like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIn(List<String> values) {
            addCriterion("merchant_name in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotIn(List<String> values) {
            addCriterion("merchant_name not in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameBetween(String value1, String value2) {
            addCriterion("merchant_name between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotBetween(String value1, String value2) {
            addCriterion("merchant_name not between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountIsNull() {
            addCriterion("order_user_count is null");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountIsNotNull() {
            addCriterion("order_user_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountEqualTo(Integer value) {
            addCriterion("order_user_count =", value, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountNotEqualTo(Integer value) {
            addCriterion("order_user_count <>", value, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountGreaterThan(Integer value) {
            addCriterion("order_user_count >", value, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_user_count >=", value, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountLessThan(Integer value) {
            addCriterion("order_user_count <", value, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_user_count <=", value, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountIn(List<Integer> values) {
            addCriterion("order_user_count in", values, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountNotIn(List<Integer> values) {
            addCriterion("order_user_count not in", values, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountBetween(Integer value1, Integer value2) {
            addCriterion("order_user_count between", value1, value2, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andOrderUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_user_count not between", value1, value2, "orderUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountIsNull() {
            addCriterion("refund_user_count is null");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountIsNotNull() {
            addCriterion("refund_user_count is not null");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountEqualTo(Integer value) {
            addCriterion("refund_user_count =", value, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountNotEqualTo(Integer value) {
            addCriterion("refund_user_count <>", value, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountGreaterThan(Integer value) {
            addCriterion("refund_user_count >", value, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_user_count >=", value, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountLessThan(Integer value) {
            addCriterion("refund_user_count <", value, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("refund_user_count <=", value, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountIn(List<Integer> values) {
            addCriterion("refund_user_count in", values, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountNotIn(List<Integer> values) {
            addCriterion("refund_user_count not in", values, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountBetween(Integer value1, Integer value2) {
            addCriterion("refund_user_count between", value1, value2, "refundUserCount");
            return (Criteria) this;
        }

        public Criteria andRefundUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_user_count not between", value1, value2, "refundUserCount");
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