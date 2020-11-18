package com.fengchao.statistics.controller;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.vo.OverviewResVo;
import com.fengchao.statistics.service.OverviewService;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 概览统计分析
 *
 * @author zp
 */
@RestController
@RequestMapping(value = "/overview", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class OverviewController {

    @Autowired
    private OverviewService overviewService;

    /**
     * 获取总揽统计数据
     *
     * 订单支付总额（元） 用户总数（人） 订单总量（单） 下单人数（人） 退货单数（单） 客单价（元） 订单均价（元）
     *
     * @param operaResponse
     * @return
     */
    @GetMapping("sum")
    private OperaResponse overview(@RequestHeader("renter") String renterHeader, OperaResponse operaResponse) {
        log.debug("获取总揽统计数据 入参:{}", renterHeader);

        try {
            OverviewResVo overviewResVo = overviewService.fetchOverviewStatistic(renterHeader);
            operaResponse.setData(overviewResVo);
        } catch (Exception e) {
            log.error("获取总揽统计数据 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("获取总揽统计数据异常");
        }

        log.info("获取总揽统计数据 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }


}
