package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-30 下午6:06
 */
@Setter
@Getter
public class OverviewResVo {

    /**
     * 订单支付总额
     */
    private String orderAmount;

    /**
     * 用户总数
     */
    private Integer userCount;

    /**
     * 订单总量
     */
    private Integer orderCount;

    /**
     * 下单人数
     */
    private Integer orderUserCount;

    /**
     * 退货单数
     */
    private Integer refundOrderCount;

    /**
     * 客单价
     */
    private String perCustomerPrice;

    /**
     * 订单均价
     */
    private String avgOrderPrice;
}
