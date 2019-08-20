package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-31 下午2:33
 */
@Getter
@Setter
public class PeriodOverviewResVo {

    /**
     * 凌晨
     */
    private String earlyMorning;

    /**
     * 上午
     */
    private String morning;

    /**
     * 中午
     */
    private String noon;

    /**
     * 下午
     */
    private String afternoon;

    /**
     * 晚上
     */
    private String night;

    /**
     * 深夜
     */
    private String lateAtNight;

    /**
     * 数据时间
     */
    private String statisticsDate;
}
