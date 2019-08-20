package com.fengchao.statistics.service;

import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PromotionOverviewService {

    /**
     * 每天 按照活动维度统计订单详情总额
     *
     * @param orderDetailBeanList 需要统计的原始数据-已支付的订单详情
     * @param startDateTime
     * @param endDateTime
     * @param statisticDate
     * @throws Exception
     */
    void doDailyStatistic(List<OrderDetailBean> orderDetailBeanList,
                          String startDateTime,
                          String endDateTime,
                          Date statisticDate) throws Exception;

    /**
     * 根据时间范围获取每日统计的数据
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> fetchStatisticDailyResult(String startDate, String endDate) throws Exception;
}
