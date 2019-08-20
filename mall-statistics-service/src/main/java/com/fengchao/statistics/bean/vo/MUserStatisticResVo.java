package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-8-6 下午7:01
 */
@Setter
@Getter
public class MUserStatisticResVo {

    /**
     * 下单人数
     */
    private Integer orderUserCount;

    /**
     * 退货人数
     */
    private Integer refundUserCount;

    /**
     * 统计数据日期 yyyy-MM-dd
     */
    private String statisticDate;

    public MUserStatisticResVo() {

    }

    public MUserStatisticResVo(Integer orderUserCount, Integer refundUserCount, String statisticDate) {
        this.orderUserCount = orderUserCount;
        this.refundUserCount = refundUserCount;
        this.statisticDate = statisticDate;
    }
}
