package com.fengchao.statistics.service;

import com.fengchao.statistics.bean.vo.MerchantOverviewResVo;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;

import java.util.Date;
import java.util.List;

public interface MerchantOverviewService {

    /**
     * 按商户维度统计订单支付总额
     *
     * @param payedOrderDetailBeanList 需要统计的原始数据-已支付的订单详情
     * @param startDateTime
     * @param endDateTime
     * @param statisticDate
     * @throws Exception
     */
    void doDailyStatistic(List<OrderDetailBean> payedOrderDetailBeanList,
                          String startDateTime,
                          String endDateTime,
                          Date statisticDate) throws Exception;

    /**
     * 根据时间范围获取每日统计的数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<MerchantOverviewResVo> fetchStatisticDailyResult(String startDate, String endDate) throws Exception;
}
