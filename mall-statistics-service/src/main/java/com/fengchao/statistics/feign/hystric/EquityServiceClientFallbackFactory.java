package com.fengchao.statistics.feign.hystric;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.feign.EquityServiceClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class EquityServiceClientFallbackFactory implements FallbackFactory<EquityServiceClient> {

    @Override
    public EquityServiceClient create(Throwable throwable) {
        return new EquityServiceClient() {
            @Override
            public OperaResponse queryPromotionByIdList(List<Integer> idList) {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }

            @Override
            public OperaResponse queryAllPromotionTypeList() {
                return HystrixDefaultFallback.fallbackResponse(throwable);
            }
        };
    }

}

