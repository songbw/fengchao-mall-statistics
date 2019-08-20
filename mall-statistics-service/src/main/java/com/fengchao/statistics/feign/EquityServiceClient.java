package com.fengchao.statistics.feign;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.bean.PromotionTypeResDto;
import com.fengchao.statistics.feign.hystric.EquityServiceClientFallbackFactory;
import com.fengchao.statistics.rpc.extmodel.PromotionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "equity", url = "${rpc.feign.client.equity.url:}", fallbackFactory = EquityServiceClientFallbackFactory.class)
public interface EquityServiceClient {

    /**
     * 根据id集合查询活动信息
     *
     * @param idList
     * @return
     */
    @RequestMapping(value = "/promotion/findByIdList", method = RequestMethod.GET)
    OperaResponse<List<PromotionBean>> queryPromotionByIdList(@RequestParam("idList") List<Integer> idList);

    /**
     * 查询所有的活动类型
     *
     * @return
     */
    @RequestMapping(value = "/promotion/type/queryAllPromotionTypes", method = RequestMethod.GET)
    OperaResponse<List<PromotionTypeResDto>> queryAllPromotionTypeList();

}
