package com.fengchao.statistics.feign.hystric;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.OperaResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author tom
 * @Date 19-7-19 下午6:22
 */
@Slf4j
public class HystrixDefaultFallback {

    public static OperaResult defaultFallback(Throwable cause) {
        log.error("降级异常:{}", cause.getMessage(), cause);

        OperaResult result = new OperaResult();
        result.setCode(500);
        result.setMsg("rpc 查询失败降级");

        return result;
    }

    public static OperaResponse fallbackResponse(Throwable cause) {
        log.error("降级异常:{}", cause.getMessage(), cause);

        OperaResponse result = new OperaResponse();
        result.setCode(500);
        result.setMsg("rpc 查询失败降级");

        return result;
    }
}
