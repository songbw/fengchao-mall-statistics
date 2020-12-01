package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-26 下午2:48
 */
@Setter
@Getter
public class AppOverviewResVo {

    /**
     * appId
     */
    private String appId;

    /**
     * 订单详情总金额
     */
    private String orderAmount;
}
