package com.fengchao.statistics.service.impl;

import com.fengchao.statistics.bean.QueryBean;
import com.fengchao.statistics.bean.vo.OverviewResVo;
import com.fengchao.statistics.rpc.OrdersRpcService;
import com.fengchao.statistics.rpc.SsoRpcService;
import com.fengchao.statistics.rpc.WorkOrdersRpcService;
import com.fengchao.statistics.rpc.extmodel.DayStatisticsBean;
import com.fengchao.statistics.service.OverviewService;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OverviewServiceImpl implements OverviewService {

    @Autowired
    private OrdersRpcService ordersRpcService;

    @Autowired
    private WorkOrdersRpcService workOrdersRpcService;

    @Autowired
    private SsoRpcService ssoRpcService;

    @Override
    public void add(QueryBean queryBean) {
//        Overview overview = new Overview();
//        DayStatisticsBean statisticsBean = getStatistics(queryBean);
//
//        if (statisticsBean != null) {
//            overview.setCreatedAt(new Date());
//            overview.setOrderPaymentAmount(statisticsBean.getOrderPaymentAmount());
//            overview.setOrderCount(statisticsBean.getOrderCount());
//            overview.setOrderPeopleNum(statisticsBean.getOrderPeopleNum());
//            int refundCount = getRefundCount(queryBean) ;
//            overview.setOrderBackNum(refundCount);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
//            try {
//                Date date = formatter.parse(queryBean.getStartTime()) ;
//                overview.setStatisticsDate(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            mapper.insertSelective(overview) ;
//        }
    }

    @Override
    public OverviewResVo fetchOverviewStatistic() {
        // 1.获取订单支付总额
        // 3.(已支付)订单总量
        // 4.(已支付)下单人数
        DayStatisticsBean dayStatisticsBean = ordersRpcService.queryOrdersOverviewStatistic();
        if (dayStatisticsBean == null) {
            dayStatisticsBean = new DayStatisticsBean();
        }

        Float orderAmount = dayStatisticsBean.getOrderPaymentAmount(); // 订单支付总额
        Integer orderCount = dayStatisticsBean.getOrderCount(); // (已支付)订单总量
        Integer orderUserCount = dayStatisticsBean.getOrderPeopleNum(); // (已支付)下单人数

        // 2.用户总数
        int userCount = ssoRpcService.queryAllUsercount();

        // 5.退货单数
        int refundOrderCount = workOrdersRpcService.queryRefundOrdersCount();

        // 6.客单价
        BigDecimal perCustomPriceB = new BigDecimal(0);
        BigDecimal orderAmoutB = new BigDecimal(orderAmount);
        if (orderUserCount != null && orderUserCount > 0) {
            perCustomPriceB =
                    orderAmoutB.divide(new BigDecimal(orderUserCount), 2, BigDecimal.ROUND_HALF_UP);
        }

        // 7.订单均价
        BigDecimal avgOrderPriceB = new BigDecimal(0);
        if (orderCount != null && orderCount > 0) {
            avgOrderPriceB =
                    orderAmoutB.divide(new BigDecimal(orderCount), 2, BigDecimal.ROUND_HALF_UP);
        }

        OverviewResVo overviewResVo = new OverviewResVo();
        overviewResVo.setOrderAmount(orderAmoutB.toString()); // 订单支付总额
        overviewResVo.setUserCount(userCount); // 用户总数
        overviewResVo.setOrderCount(orderCount); // 订单总量
        overviewResVo.setOrderUserCount(orderUserCount); // 下单人数
        overviewResVo.setRefundOrderCount(refundOrderCount); // 退货单数
        overviewResVo.setPerCustomerPrice(perCustomPriceB.toString()); // 客单价
        overviewResVo.setAvgOrderPrice(avgOrderPriceB.toString()); // 订单均价

        log.info("获取统计数据总揽 OverviewServiceImpl#fetchOverviewStatistic 返回:{}", JSONUtil.toJsonString(overviewResVo));

        return overviewResVo ;
    }


}
