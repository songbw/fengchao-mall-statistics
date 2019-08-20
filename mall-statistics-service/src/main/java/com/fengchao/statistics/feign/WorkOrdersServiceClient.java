package com.fengchao.statistics.feign;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.feign.hystric.WorkOrderServiceClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "workorders", fallbackFactory = WorkOrderServiceClientFallbackFactory.class)
public interface WorkOrdersServiceClient {

    /**
     * 获取退货人数
     *
     * @return
     */
    @RequestMapping(value = "/work_orders/refunds", method = RequestMethod.GET)
    OperaResponse refundOrdersCount();

    /**
     * 根据时间范围获取退货信息列表
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    @RequestMapping(value = "/work_orders/refunds/list", method = RequestMethod.GET)
    OperaResponse queryRefundInfoList(@RequestParam("timeStart") String startDateTime,
                                      @RequestParam("timeEnd") String endDateTime);

    /**
     * 根据商户id查询退货人数
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "/work_orders/refund/user/count", method = RequestMethod.GET)
    OperaResponse<Integer> queryRefundUserCountByMerchantId(@RequestParam("merchantId") Long merchantId);

}
