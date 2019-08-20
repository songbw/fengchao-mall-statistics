package com.fengchao.statistics.constants;

/**
 * 逻辑删除标识 1:有效 2：无效
 */
public enum IStatusEnum {

    VALID(1, "有效"), INVALID(2, "无效");

    private Integer value;

    private String desc;

    IStatusEnum(int value, String desc) {
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
