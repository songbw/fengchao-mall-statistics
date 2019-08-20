package com.fengchao.statistics.rpc.extmodel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DayStatisticsBean {
    private float orderPaymentAmount;
    private int orderCount;
    private int orderPeopleNum;
    private int orderBackNum;
}
