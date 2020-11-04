package com.fengchao.statistics.service;

import com.fengchao.statistics.bean.QueryBean;
import com.fengchao.statistics.bean.vo.OverviewResVo;

public interface OverviewService {

    void add(QueryBean queryBean) ;

    /**
     * 获取总揽统计数据
     *
     * 1.获取订单支付总额
     * 2.用户总数
     * 3.(已支付)订单总量
     * 4.(已支付)下单人数
     * 5.退货人数
     * 6.客单价
     * 7.订单均价
     *
     * @return
     * @param renterId
     */
    OverviewResVo fetchOverviewStatistic(String renterId) ;

}
