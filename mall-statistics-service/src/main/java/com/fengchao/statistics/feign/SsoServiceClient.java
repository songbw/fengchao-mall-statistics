package com.fengchao.statistics.feign;

import com.fengchao.statistics.bean.OperaResult;
import com.fengchao.statistics.feign.hystric.SsoServiceClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sso", fallbackFactory = SsoServiceClientFallbackFactory.class)
public interface SsoServiceClient {

    /**
     * 查询所有用户数
     *
     * @return
     */
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    OperaResult queryAllUsercount();

}
