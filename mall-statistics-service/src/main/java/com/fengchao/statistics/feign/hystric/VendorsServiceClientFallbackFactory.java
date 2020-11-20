package com.fengchao.statistics.feign.hystric;

import com.alibaba.fastjson.JSONObject;
import com.fengchao.statistics.feign.VendorsServiceClient;
import com.fengchao.statistics.rpc.extmodel.ResultObject;
import com.fengchao.statistics.rpc.extmodel.SysCompany;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author tom
 * @Date 19-7-27 上午10:32
 */
@Component
@Slf4j
public class VendorsServiceClientFallbackFactory implements FallbackFactory<VendorsServiceClient> {

    @Override
    public VendorsServiceClient create(Throwable throwable) {
        return new VendorsServiceClient() {
            @Override
            public ResultObject<List<SysCompany>> queryMerchantByIdList(List<Long> idList) {
                log.info("根据id集合查询商户信息 调用vendors rpc服务 降级:{}", throwable.getMessage(), throwable);

                ResultObject resultObject = new ResultObject<>(500, "vendors 服务降级", null);
                return resultObject;
            }

            @Override
            public ResultObject<List<String>> queryAppIdList(String renterId) {
                log.info("根据renterId集合查询appId列表信息 调用vendors rpc服务 降级:{}", throwable.getMessage(), throwable);
                ResultObject resultObject = new ResultObject<>(500, "vendors 服务降级", null);
                return resultObject;
            }

            @Override
            public ResultObject<List<Integer>> queryRenterMerchantList(String renterId) {
                log.info("根据renterId集合查询merchantId 列表信息 调用vendors rpc服务 降级:{}", throwable.getMessage(), throwable);
                ResultObject resultObject = new ResultObject<>(500, "vendors 服务降级", null);
                return resultObject;
            }

            @Override
            public ResultObject<JSONObject> queryAppIdAndRenterId(List<String> appIds) {
                log.info("根据appIds集合查询appId renterId 列表信息 调用vendors rpc服务 降级:{}", throwable.getMessage(), throwable);
                ResultObject resultObject = new ResultObject<>(500, "vendors 服务降级", null);
                return resultObject;
            }
        };
    }

}
