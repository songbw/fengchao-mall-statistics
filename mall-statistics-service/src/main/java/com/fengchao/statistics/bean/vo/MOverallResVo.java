package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-8-6 下午3:16
 */
@Setter
@Getter
public class MOverallResVo {

    /**
     * 订单总额 单位元
     */
    private String orderAmount;

    /**
     * 订单总量
     */
    private Integer orderCount;

    /**
     * 下单人数
     */
    private Integer orderUserCount;

    /**
     * 退货人数
     */
    private Integer refundUserCount;
}
