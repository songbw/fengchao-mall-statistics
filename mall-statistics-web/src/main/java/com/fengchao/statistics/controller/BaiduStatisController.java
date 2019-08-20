package com.fengchao.statistics.controller;

import com.fengchao.statistics.bean.OperaResult;
import com.fengchao.statistics.bean.QueryBean;
import com.fengchao.statistics.bean.QueryUrlStatisBean;
import com.fengchao.statistics.exception.StatisticsException;
import com.fengchao.statistics.service.BaiduStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 百度页面统计分析
 * @author zp
 */
@RestController
@RequestMapping(value = "/baidu_statis", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BaiduStatisController {

    @Autowired
    private BaiduStatisService service;

    @GetMapping("/total_pv_uv")
    private OperaResult totalPvandUv(OperaResult result) {
        try{
            result.getData().put("data", service.getTotalPVandUv()) ;
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new StatisticsException(801001, "请检查参数是否符合要求");
        }
    }

    @PostMapping("/query_urls")
    private OperaResult queryAllUrls(@RequestBody QueryBean queryBean, OperaResult result) {
        try{
            result.getData().put("data", service.queryAllUrls(queryBean)) ;
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new StatisticsException(801001, "请检查参数是否符合要求");
        }
    }

    @PostMapping("/query_urls_statis")
    private OperaResult queryStatisticsData(@RequestBody QueryUrlStatisBean queryBean, OperaResult result) {
        try{
            result.getData().put("data", service.queryStatisticsData(queryBean)) ;
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new StatisticsException(801001, "请检查参数是否符合要求");
        }
    }



}
