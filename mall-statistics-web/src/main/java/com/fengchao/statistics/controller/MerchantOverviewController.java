package com.fengchao.statistics.controller;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.vo.MerchantOverviewResVo;
import com.fengchao.statistics.service.MerchantOverviewService;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商户概览统计分析
 *
 * @author zp
 */
@RestController
@RequestMapping(value = "/overview/merchant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class MerchantOverviewController {

    @Autowired
    private MerchantOverviewService merchantOverviewService;

    @GetMapping("sum")
    private OperaResponse overview(@RequestHeader("renter") String renterHeader, String startDate, String endDate, OperaResponse operaResponse) {
        log.info("根据时间范围获取按照商户维度的统计结果, 入参 startDate:{}, endDate:{}", startDate, endDate);

        try {
            List<MerchantOverviewResVo> merchantOverviewResVoList =
                    merchantOverviewService.fetchStatisticDailyResult(renterHeader, startDate, endDate);
            operaResponse.setData(merchantOverviewResVoList);
        } catch (Exception e) {
            log.info("根据时间范围获取按商户类维度的统计结果 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("根据时间范围获取按照商户维度的统计结果异常");
            operaResponse.setData(null);
        }

        log.info("根据时间范围获取按照商户维度的统计结果 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }


}
