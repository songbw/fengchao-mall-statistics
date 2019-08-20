package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-26 下午2:48
 */
@Setter
@Getter
public class CategoryOverviewResVo {

    /**
     * 品类id
     */
    private Integer categoryId;

    /**
     * 品类名称
     */
    private String categoryName;

    /**
     * 订单详情总金额
     */
    private String orderAmount;
}
