package com.fengchao.statistics.service;

import com.fengchao.statistics.bean.vo.PeriodOverviewResVo;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;

import java.util.Date;
import java.util.List;

public interface PeriodOverviewService {

    /**
     * 执行日统计
     *
     * @param orderDetailBeanList
     * @param startDateTime
     * @param endDateTime
     * @param statisticDate
     * @throws Exception
     */
    void doStatistic(List<OrderDetailBean> orderDetailBeanList,
                String startDateTime, String endDateTime, Date statisticDate) throws Exception;

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    List<PeriodOverviewResVo> fetchStatisticDailyResult(String startDate, String endDate) throws Exception;
}
