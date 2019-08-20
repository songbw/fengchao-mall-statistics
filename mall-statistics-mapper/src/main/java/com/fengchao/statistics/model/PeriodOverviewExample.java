package com.fengchao.statistics.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PeriodOverviewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PeriodOverviewExample() {
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

        public Criteria andLateAtNightIsNull() {
            addCriterion("late_at_night is null");
            return (Criteria) this;
        }

        public Criteria andLateAtNightIsNotNull() {
            addCriterion("late_at_night is not null");
            return (Criteria) this;
        }

        public Criteria andLateAtNightEqualTo(Long value) {
            addCriterion("late_at_night =", value, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightNotEqualTo(Long value) {
            addCriterion("late_at_night <>", value, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightGreaterThan(Long value) {
            addCriterion("late_at_night >", value, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightGreaterThanOrEqualTo(Long value) {
            addCriterion("late_at_night >=", value, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightLessThan(Long value) {
            addCriterion("late_at_night <", value, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightLessThanOrEqualTo(Long value) {
            addCriterion("late_at_night <=", value, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightIn(List<Long> values) {
            addCriterion("late_at_night in", values, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightNotIn(List<Long> values) {
            addCriterion("late_at_night not in", values, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightBetween(Long value1, Long value2) {
            addCriterion("late_at_night between", value1, value2, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andLateAtNightNotBetween(Long value1, Long value2) {
            addCriterion("late_at_night not between", value1, value2, "lateAtNight");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningIsNull() {
            addCriterion("early_morning is null");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningIsNotNull() {
            addCriterion("early_morning is not null");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningEqualTo(Long value) {
            addCriterion("early_morning =", value, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningNotEqualTo(Long value) {
            addCriterion("early_morning <>", value, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningGreaterThan(Long value) {
            addCriterion("early_morning >", value, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningGreaterThanOrEqualTo(Long value) {
            addCriterion("early_morning >=", value, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningLessThan(Long value) {
            addCriterion("early_morning <", value, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningLessThanOrEqualTo(Long value) {
            addCriterion("early_morning <=", value, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningIn(List<Long> values) {
            addCriterion("early_morning in", values, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningNotIn(List<Long> values) {
            addCriterion("early_morning not in", values, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningBetween(Long value1, Long value2) {
            addCriterion("early_morning between", value1, value2, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andEarlyMorningNotBetween(Long value1, Long value2) {
            addCriterion("early_morning not between", value1, value2, "earlyMorning");
            return (Criteria) this;
        }

        public Criteria andMorningIsNull() {
            addCriterion("morning is null");
            return (Criteria) this;
        }

        public Criteria andMorningIsNotNull() {
            addCriterion("morning is not null");
            return (Criteria) this;
        }

        public Criteria andMorningEqualTo(Long value) {
            addCriterion("morning =", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotEqualTo(Long value) {
            addCriterion("morning <>", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningGreaterThan(Long value) {
            addCriterion("morning >", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningGreaterThanOrEqualTo(Long value) {
            addCriterion("morning >=", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningLessThan(Long value) {
            addCriterion("morning <", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningLessThanOrEqualTo(Long value) {
            addCriterion("morning <=", value, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningIn(List<Long> values) {
            addCriterion("morning in", values, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotIn(List<Long> values) {
            addCriterion("morning not in", values, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningBetween(Long value1, Long value2) {
            addCriterion("morning between", value1, value2, "morning");
            return (Criteria) this;
        }

        public Criteria andMorningNotBetween(Long value1, Long value2) {
            addCriterion("morning not between", value1, value2, "morning");
            return (Criteria) this;
        }

        public Criteria andNoonIsNull() {
            addCriterion("noon is null");
            return (Criteria) this;
        }

        public Criteria andNoonIsNotNull() {
            addCriterion("noon is not null");
            return (Criteria) this;
        }

        public Criteria andNoonEqualTo(Long value) {
            addCriterion("noon =", value, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonNotEqualTo(Long value) {
            addCriterion("noon <>", value, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonGreaterThan(Long value) {
            addCriterion("noon >", value, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonGreaterThanOrEqualTo(Long value) {
            addCriterion("noon >=", value, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonLessThan(Long value) {
            addCriterion("noon <", value, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonLessThanOrEqualTo(Long value) {
            addCriterion("noon <=", value, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonIn(List<Long> values) {
            addCriterion("noon in", values, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonNotIn(List<Long> values) {
            addCriterion("noon not in", values, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonBetween(Long value1, Long value2) {
            addCriterion("noon between", value1, value2, "noon");
            return (Criteria) this;
        }

        public Criteria andNoonNotBetween(Long value1, Long value2) {
            addCriterion("noon not between", value1, value2, "noon");
            return (Criteria) this;
        }

        public Criteria andAfternoonIsNull() {
            addCriterion("afternoon is null");
            return (Criteria) this;
        }

        public Criteria andAfternoonIsNotNull() {
            addCriterion("afternoon is not null");
            return (Criteria) this;
        }

        public Criteria andAfternoonEqualTo(Long value) {
            addCriterion("afternoon =", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotEqualTo(Long value) {
            addCriterion("afternoon <>", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonGreaterThan(Long value) {
            addCriterion("afternoon >", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonGreaterThanOrEqualTo(Long value) {
            addCriterion("afternoon >=", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonLessThan(Long value) {
            addCriterion("afternoon <", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonLessThanOrEqualTo(Long value) {
            addCriterion("afternoon <=", value, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonIn(List<Long> values) {
            addCriterion("afternoon in", values, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotIn(List<Long> values) {
            addCriterion("afternoon not in", values, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonBetween(Long value1, Long value2) {
            addCriterion("afternoon between", value1, value2, "afternoon");
            return (Criteria) this;
        }

        public Criteria andAfternoonNotBetween(Long value1, Long value2) {
            addCriterion("afternoon not between", value1, value2, "afternoon");
            return (Criteria) this;
        }

        public Criteria andNightIsNull() {
            addCriterion("night is null");
            return (Criteria) this;
        }

        public Criteria andNightIsNotNull() {
            addCriterion("night is not null");
            return (Criteria) this;
        }

        public Criteria andNightEqualTo(Long value) {
            addCriterion("night =", value, "night");
            return (Criteria) this;
        }

        public Criteria andNightNotEqualTo(Long value) {
            addCriterion("night <>", value, "night");
            return (Criteria) this;
        }

        public Criteria andNightGreaterThan(Long value) {
            addCriterion("night >", value, "night");
            return (Criteria) this;
        }

        public Criteria andNightGreaterThanOrEqualTo(Long value) {
            addCriterion("night >=", value, "night");
            return (Criteria) this;
        }

        public Criteria andNightLessThan(Long value) {
            addCriterion("night <", value, "night");
            return (Criteria) this;
        }

        public Criteria andNightLessThanOrEqualTo(Long value) {
            addCriterion("night <=", value, "night");
            return (Criteria) this;
        }

        public Criteria andNightIn(List<Long> values) {
            addCriterion("night in", values, "night");
            return (Criteria) this;
        }

        public Criteria andNightNotIn(List<Long> values) {
            addCriterion("night not in", values, "night");
            return (Criteria) this;
        }

        public Criteria andNightBetween(Long value1, Long value2) {
            addCriterion("night between", value1, value2, "night");
            return (Criteria) this;
        }

        public Criteria andNightNotBetween(Long value1, Long value2) {
            addCriterion("night not between", value1, value2, "night");
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