package com.fengchao.statistics.controller;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.service.PromotionOverviewService;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 活动概览统计分析
 * @author zp
 */
@RestController
@RequestMapping(value = "/overview/promotion", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class PromotionOverviewController {

    @Autowired
    private PromotionOverviewService promotionOverviewService;

    @GetMapping("list")
    private OperaResponse overview(String startDate, String endDate, OperaResponse operaResponse) {
        log.info("根据时间范围获取按照活动维度的统计结果, 入参 startDate:{}, endDate:{}", startDate, endDate);

        try {
            List<Map<String, Object>> result =
                    promotionOverviewService.fetchStatisticDailyResult(startDate, endDate);
            operaResponse.setData(result);
        } catch (Exception e) {
            log.info("根据时间范围获取按活动类维度的统计结果 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("根据时间范围获取按照活动维度的统计结果异常");
            operaResponse.setData(null);
        }

        log.info("根据时间范围获取按照活动维度的统计结果 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }
}
