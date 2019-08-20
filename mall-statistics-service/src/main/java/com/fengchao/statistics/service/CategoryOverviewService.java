package com.fengchao.statistics.service;

import com.fengchao.statistics.bean.vo.CategoryOverviewResVo;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;

import java.util.Date;
import java.util.List;

public interface CategoryOverviewService {

    /**
     * 每天 按照品类维度统计订单详情总额
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
    List<CategoryOverviewResVo> fetchStatisticDailyResult(String startDate, String endDate) throws Exception;
}
