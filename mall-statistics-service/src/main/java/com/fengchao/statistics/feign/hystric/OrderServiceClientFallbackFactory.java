package com.fengchao.statistics.feign.hystric;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.OperaResult;
import com.fengchao.statistics.feign.OrderServiceClient;
import com.fengchao.statistics.rpc.extmodel.DayStatisticsBean;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceClientFallbackFactory implements FallbackFactory<OrderServiceClient> {

    @Override
    public OrderServiceClient create(Throwable throwable) {

        return new OrderServiceClient() {
            @Override
            public OperaResponse<DayStatisticsBean> statistics(String renterId) {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }

            @Override
            public OperaResponse<DayStatisticsBean> merchantStatistics(Integer merchantId) {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }

            @Override
            public OperaResult paymentCount(String start, String end) {
                return HystrixDefaultFallback.defaultFallback(throwable);
            }

            @Override
            public OperaResult paymentPromotionCount(String start, String end) {
                return HystrixDefaultFallback.defaultFallback(throwable);
            }

            @Override
            public OperaResponse<List<OrderDetailBean>> queryPayedOrderDetailList(String start, String end) {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }
        };
    }


}
