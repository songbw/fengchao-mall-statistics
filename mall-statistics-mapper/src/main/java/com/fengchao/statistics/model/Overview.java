package com.fengchao.statistics.model;

import java.util.Date;

public class Overview {
    private Long id;

    private float orderPaymentAmount;

    private Integer orderCount;

    private Integer orderPeopleNum;

    private Integer orderBackNum;

    private Integer userNum;

    private Date statisticsDate;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getOrderPaymentAmount() {
        return orderPaymentAmount;
    }

    public void setOrderPaymentAmount(float orderPaymentAmount) {
        this.orderPaymentAmount = orderPaymentAmount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getOrderPeopleNum() {
        return orderPeopleNum;
    }

    public void setOrderPeopleNum(Integer orderPeopleNum) {
        this.orderPeopleNum = orderPeopleNum;
    }

    public Integer getOrderBackNum() {
        return orderBackNum;
    }

    public void setOrderBackNum(Integer orderBackNum) {
        this.orderBackNum = orderBackNum;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}