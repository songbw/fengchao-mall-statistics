package com.fengchao.statistics.model;

import java.util.Date;

public class PromotionOverview {
    private Long id;

    private String promotionType;

    private Long orderAmount;

    private Short periodType;

    private Date statisticsDate;

    private Date statisticStartTime;

    private Date statisticEndTime;

    private Short istatus;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType == null ? null : promotionType.trim();
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Short getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Short periodType) {
        this.periodType = periodType;
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