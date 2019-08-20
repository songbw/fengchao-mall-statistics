package com.fengchao.statistics.model;

import java.util.Date;

public class PeriodOverview {
    private Long id;

    private Long lateAtNight;

    private Long earlyMorning;

    private Long morning;

    private Long noon;

    private Long afternoon;

    private Long night;

    private Date statisticsDate;

    private Date statisticStartTime;

    private Date statisticEndTime;

    private Short periodType;

    private Short istatus;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLateAtNight() {
        return lateAtNight;
    }

    public void setLateAtNight(Long lateAtNight) {
        this.lateAtNight = lateAtNight;
    }

    public Long getEarlyMorning() {
        return earlyMorning;
    }

    public void setEarlyMorning(Long earlyMorning) {
        this.earlyMorning = earlyMorning;
    }

    public Long getMorning() {
        return morning;
    }

    public void setMorning(Long morning) {
        this.morning = morning;
    }

    public Long getNoon() {
        return noon;
    }

    public void setNoon(Long noon) {
        this.noon = noon;
    }

    public Long getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(Long afternoon) {
        this.afternoon = afternoon;
    }

    public Long getNight() {
        return night;
    }

    public void setNight(Long night) {
        this.night = night;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public Date getStatisticStartTime() {
        return statisticStartTime;
    }

    public void setStatisticStartTime(Date statisticStartTime) {
        this.statisticStartTime = statisticStartTime;
    }

    public Date getStatisticEndTime() {
        return statisticEndTime;
    }

    public void setStatisticEndTime(Date statisticEndTime) {
        this.statisticEndTime = statisticEndTime;
    }

    public Short getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Short periodType) {
        this.periodType = periodType;
    }

    public Short getIstatus() {
        return istatus;
    }

    public void setIstatus(Short istatus) {
        this.istatus = istatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}