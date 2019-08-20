package com.fengchao.statistics.controller;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.vo.MOverallResVo;
import com.fengchao.statistics.bean.vo.MUserStatisticResVo;
import com.fengchao.statistics.bean.vo.MerchantCityRangeStatisticResVo;
import com.fengchao.statistics.service.MerchantStatisticService;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 商户相关的统计查询入口
 *
 * @author tom
 */
@RestController
@RequestMapping(value = "/merchant/statistic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class MerchantRangeStatisticController {

    @Autowired
    private MerchantStatisticService merchantStatisticService;

    /**
     * 商户整体运营数据统计
     *
     * @param merchantId
     * @return
     */
    @GetMapping("/overview")
    public OperaResponse overview(@RequestParam("merchantId") Long merchantId) {
        OperaResponse operaResponse = new OperaResponse();

        log.info("查询商户整体运营数据, 入参 merchantId:{}", merchantId);

        try {
            if (merchantId == null || merchantId <= 0) {
                throw new Exception("入参 merchantId不合法");
            }

            MOverallResVo mOverallResVo = merchantStatisticService.fetchOverAll(merchantId);
            operaResponse.setData(mOverallResVo);
        } catch (Exception e) {
            log.info("查询商户整体运营数据 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("查询商户整体运营数据异常:" + e.getMessage());
            operaResponse.setData(null);
        }

        log.info("查询商户整体运营数据 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }

    /**
     * 商户订单总额变化趋势
     *
     * @param startDate yyyy-MM-dd
     * @param endDate yyyy-MM-dd
     * @param merchantId
     * @return
     */
    @GetMapping("/order/amount")
    public OperaResponse orderAmountTrend(@RequestParam(value = "startDate") String startDate,
                                          @RequestParam(value = "endDate") String endDate,
                                          @RequestParam(value = "merchantId") Integer merchantId) {
        OperaResponse operaResponse = new OperaResponse();

        log.info("根据时间范围获取按照商户-城市(天)维度的统计结果, 入参 startDate:{}, endDate:{}, merchantId:{}",
                startDate, endDate, merchantId);

        try {
            if (merchantId == null || merchantId <= 0) {
                throw new Exception("入参 merchantId不合法");
            }

            List<MerchantCityRangeStatisticResVo> resultList =
                    merchantStatisticService.fetchOrderAmountTrend(startDate, endDate, merchantId);
            operaResponse.setData(resultList);
        } catch (Exception e) {
            log.info("根据时间范围获取按商户-城市(天)维度的统计结果 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("根据时间范围获取按照商户-城市(天)维度的统计结果异常:" + e.getMessage());
            operaResponse.setData(null);
        }

        log.info("根据时间范围获取按照商户-城市(天)维度的统计结果 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }

    /**
     * 商户用户数变化趋势
     *
     * @param startDate yyyy-MM-dd
     * @param endDate yyyy-MM-dd
     * @param merchantId
     * @return
     */
    @GetMapping("/user/count")
    public OperaResponse merchantUserTrend(@RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate,
                                           @RequestParam("merchantId") Integer merchantId) {
        OperaResponse operaResponse = new OperaResponse();

        log.info("根据时间范围获取商户的用户变化趋势 入参 startDate:{}, endDate:{}, merchantId:{}",
                startDate, endDate, merchantId);

        try {
            if (merchantId == null || merchantId <= 0) {
                throw new Exception("入参 merchantId不合法");
            }

            List<MUserStatisticResVo> resultList = merchantStatisticService.fetchUserTrend(startDate, endDate, merchantId);
            operaResponse.setData(resultList);
        } catch (Exception e) {
            log.info("根据时间范围获取商户的用户变化趋势 异常:{}", e.getMessage(), e);

            operaResponse.setCode(500);
            operaResponse.setMsg("根据时间范围获取商户的用户变化趋势异常:" + e.getMessage());
            operaResponse.setData(null);
        }

        log.info("根据时间范围获取商户的用户变化趋势 返回:{}", JSONUtil.toJsonString(operaResponse));

        return operaResponse;
    }
}
