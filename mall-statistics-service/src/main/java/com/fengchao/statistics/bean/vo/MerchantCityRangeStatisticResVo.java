package com.fengchao.statistics.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tom
 * @Date 19-7-27 下午5:12
 */
@Setter
@Getter
public class MerchantCityRangeStatisticResVo {

    private String cityId;
    private String cityName;
    private String orderAmount; // 单位：元

    public MerchantCityRangeStatisticResVo() {

    }

    public MerchantCityRangeStatisticResVo(String cityId, String cityName, String orderAmount, String statisticDate) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.orderAmount = orderAmount;
    }
}
