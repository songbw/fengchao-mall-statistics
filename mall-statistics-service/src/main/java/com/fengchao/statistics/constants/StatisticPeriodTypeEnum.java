package com.fengchao.statistics.constants;

/**
 * @Author tom
 * @Date 19-7-26 上午9:51
 */
public enum StatisticPeriodTypeEnum {

    DAY(1, "天"), MONTH(2, "月");

    private Integer value;

    private String desc;

    StatisticPeriodTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
