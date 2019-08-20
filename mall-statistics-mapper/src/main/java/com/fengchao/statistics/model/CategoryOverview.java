package com.fengchao.statistics.model;

import java.util.Date;

public class CategoryOverview {
    private Long id;

    private String categoryFcode;

    private String categoryFname;

    private Long orderAmount;

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

    public String getCategoryFcode() {
        return categoryFcode;
    }

    public void setCategoryFcode(String categoryFcode) {
        this.categoryFcode = categoryFcode == null ? null : categoryFcode.trim();
    }

    public String getCategoryFname() {
        return categoryFname;
    }

    public void setCategoryFname(String categoryFname) {
        this.categoryFname = categoryFname == null ? null : categoryFname.trim();
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
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