package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-27 上午11:10
 */
@Setter
@Getter
public class MerchantOverviewResVo {

    private Integer merchantId;
    private String merchantName;
    private String orderAmount; // 单位元
}
