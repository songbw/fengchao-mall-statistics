package com.fengchao.statistics.feign.hystric;

import com.fengchao.statistics.bean.OperaResult;
import com.fengchao.statistics.feign.SsoServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SsoServiceClientFallbackFactory implements FallbackFactory<SsoServiceClient> {

    @Override
    public SsoServiceClient create(Throwable throwable) {
        return new SsoServiceClient() {
            @Override
            public OperaResult queryAllUsercount() {
                return HystrixDefaultFallback.defaultFallback(throwable);
            }
        };
    }


}
