package com.fengchao.statistics.feign.hystric;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.feign.WorkOrdersServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class WorkOrderServiceClientFallbackFactory implements FallbackFactory<WorkOrdersServiceClient> {

    @Override
    public WorkOrdersServiceClient create(Throwable throwable) {
        return new WorkOrdersServiceClient() {
            @Override
            public OperaResponse refundOrdersCount() {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }

            @Override
            public OperaResponse queryRefundInfoList(String startDateTime, String endDateTime) {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }

            @Override
            public OperaResponse queryRefundUserCountByMerchantId(Long merchantId) {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }
        };
    }


}
