package com.fengchao.statistics.feign;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.OperaResult;
import com.fengchao.statistics.feign.hystric.OrderServiceClientFallbackFactory;
import com.fengchao.statistics.rpc.extmodel.DayStatisticsBean;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "order", url = "${rpc.feign.client.order.url:}", fallbackFactory = OrderServiceClientFallbackFactory.class)
public interface OrderServiceClient {

    /**
     * 获取平台整体运营的统计数据
     *
     * @return
     */
    @RequestMapping(value = "/order/statistics", method = RequestMethod.GET)
    OperaResponse<DayStatisticsBean> statistics();

    /**
     * 获取商户整体运营的统计数据
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "/order/merchant/statistics", method = RequestMethod.GET)
    OperaResponse<DayStatisticsBean> merchantStatistics(@RequestParam("merchantId") Integer merchantId);

    /**
     * 获取已支付的子订单集合
     *
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/order/orderdetail/payed/list", method = RequestMethod.GET)
    OperaResponse<List<OrderDetailBean>> queryPayedOrderDetailList(@RequestParam("start") String start, @RequestParam("end") String end);

    @Deprecated
    @RequestMapping(value = "/order/payment/count", method = RequestMethod.GET)
    OperaResult paymentCount(@RequestParam("start") String start, @RequestParam("end") String end);

    @Deprecated
    @RequestMapping(value = "/order/payment/promotion/count", method = RequestMethod.GET)
    OperaResult paymentPromotionCount(@RequestParam("start") String start, @RequestParam("end") String end);




}
