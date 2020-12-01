package com.fengchao.statistics.controller;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.vo.AppOverviewResVo;
import com.fengchao.statistics.bean.vo.CategoryOverviewResVo;
import com.fengchao.statistics.bean.vo.OverviewResVo;
import com.fengchao.statistics.service.AppOverviewService;
import com.fengchao.statistics.service.CategoryOverviewService;
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
 * 类别概览统计分析
 *
 * @author zp
 */
@RestController
@RequestMapping(value = "/overview/ap", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class AppOverviewController {

    @Autowired
    private AppOverviewService appOverviewService;

    /**
     * 根据时间范围获取按照app维度的统计结果
     *
     * @param startDate
     * @param endDate
     * @param operaResponse
     * @return
     */
    @GetMapping("sum")
    private OperaResponse overview(@RequestHeader("renter") String renterHeader, String startDate, String endDate, OperaResponse operaResponse) {
        log.debug("根据时间范围获取按照app维度的统计结果, 入参 startDate:{}, endDate:{}, renterHander: {}", startDate, endDate, renterHeader);

        try {
            List<AppOverviewResVo> categoryOverviewResVoList =
                    appOverviewService.fetchStatisticDailyResult(startDate, endDate, renterHeader);
            operaResponse.setData(categoryOverviewResVoList);
        } catch (Exception e) {
            log.info("根据时间范围获取按照app维度的统计结果 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("根据时间范围获取按照app维度的统计结果异常");
            operaResponse.setData(null);
        }

        log.info("根据时间范围获取按照app维度的统计结果 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }

}
