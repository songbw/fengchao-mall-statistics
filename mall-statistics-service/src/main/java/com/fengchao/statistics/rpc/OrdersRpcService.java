package com.fengchao.statistics.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengchao.statistics.bean.*;
import com.fengchao.statistics.feign.OrderServiceClient;
import com.fengchao.statistics.rpc.extmodel.DayStatisticsBean;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author tom
 * @Date 19-7-25 下午2:29
 */
@Service
@Slf4j
public class OrdersRpcService {

    private OrderServiceClient orderServiceClient;

    @Autowired
    public OrdersRpcService(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    /**
     * 查询已支付的子订单
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public List<OrderDetailBean> queryPayedOrderDetailList(String startDateTime, String endDateTime) {
        // 返回值
        List<OrderDetailBean> orderDetailBeanList = new ArrayList<>();

        // 执行rpc调用
        log.info("查询已支付的子订单 调用order rpc服务 入参:startDateTime{}, endDateTime:{}", startDateTime, endDateTime);
        OperaResponse<List<OrderDetailBean>> operaResult = orderServiceClient.queryPayedOrderDetailList(startDateTime, endDateTime);
        log.info("查询已支付的子订单 调用order rpc服务 返回:{}", JSONUtil.toJsonString(operaResult));

        // 处理返回
        if (operaResult.getCode() == 200) {
            orderDetailBeanList = operaResult.getData();
        } else {
            log.warn("查询已支付的子订单 调用order rpc服务 错误!");
        }

        log.info("OrdersRpcService#statisticOrdersAmountByCategory 调用order rpc服务 返回:{}",
                JSONUtil.toJsonString(orderDetailBeanList));

        return orderDetailBeanList;
    }

    /**
     * 获取平台订单相关的整体运营数据
     *  1.获取订单支付总额
     *  3.(已支付)订单总量
     *  4.(已支付)下单人数
     *
     * @return
     */
    public DayStatisticsBean queryOrdersOverviewStatistic() {
        // 返回值
        DayStatisticsBean dayStatisticsBean = null;

        // 执行rpc调用
        log.info("获取订单相关的统计数据 调用order rpc服务 入参:无");
        OperaResponse<DayStatisticsBean> operaResponse = orderServiceClient.statistics();
        log.info("获取订单相关的统计数据 调用order rpc服务 返回:{}", JSONUtil.toJsonString(operaResponse));

        // 处理返回
        if (operaResponse.getCode() == 200) {
            dayStatisticsBean = operaResponse.getData();
        } else {
            log.warn("获取订单相关的统计数据 调用order rpc服务 错误!");
        }

        log.info("OrdersRpcService#queryOrdersOverviewStatistic 调用order rpc服务 返回:{}",
                JSONUtil.toJsonString(dayStatisticsBean));

        return dayStatisticsBean;
    }

    /**
     * 获取商户订单相关的整体运营数据
     *  1.获取订单支付总额
     *  3.(已支付)订单总量
     *  4.(已支付)下单人数
     *
     * @return
     */
    public DayStatisticsBean queryMerchantOrdersOverallStatistic(Integer merchantId) {
        // 返回值
        DayStatisticsBean dayStatisticsBean = null;

        // 执行rpc调用
        log.info("获取商户订单相关的整体运营数据 调用order rpc服务 入参 merchantId:{}", merchantId);
        OperaResponse<DayStatisticsBean> operaResponse = orderServiceClient.merchantStatistics(merchantId);
        log.info("获取商户订单相关的整体运营数据 调用order rpc服务 返回:{}", JSONUtil.toJsonString(operaResponse));

        // 处理返回
        if (operaResponse.getCode() == 200) {
            dayStatisticsBean = operaResponse.getData();
        } else {
            log.warn("获取商户订单相关的整体运营数据 调用order rpc服务 错误!");
        }

        log.info("OrdersRpcService#queryMerchantOrdersOverallStatistic 调用order rpc服务 返回:{}",
                JSONUtil.toJsonString(dayStatisticsBean));

        return dayStatisticsBean;
    }



    /**
     * 按商户维度统计订单支付总额
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
//    public List<MerchantPaymentBean> statisticOrdersAmountByMerchant(String startDateTime, String endDateTime) {
//        // 返回值
//        List<MerchantPaymentBean> merchantPaymentBeanList = new ArrayList<>();
//
//        // 执行rpc调用
//        log.info("按商户统计订单支付总额 调用order rpc服务 入参:startDateTime{}, endDateTime:{}",
//                startDateTime, endDateTime);
//        OperaResult operaResult = orderServiceClient.paymentMerchantCount(startDateTime, endDateTime);
//        log.info("按商户统计订单支付总额 调用order rpc服务 返回:{}", JSONUtil.toJsonString(operaResult));
//
//        // 处理返回
//        if (operaResult.getCode() == 200) {
//            List<MerchantPaymentBean> _merchantPaymentBeanList
//                    = (List<MerchantPaymentBean>) operaResult.getData().get("result");
//
//            // 转
//            merchantPaymentBeanList = JSON.parseArray(JSON.toJSONString(_merchantPaymentBeanList), MerchantPaymentBean.class);
//        } else {
//            log.warn("按商户统计订单支付总额 调用order rpc服务 错误!");
//        }
//
//        log.info("OrdersRpcService#statisticOrdersAmountByMerchant 调用equity rpc服务 返回:{}",
//                JSONUtil.toJsonString(merchantPaymentBeanList));
//
//        return merchantPaymentBeanList;
//    }
}