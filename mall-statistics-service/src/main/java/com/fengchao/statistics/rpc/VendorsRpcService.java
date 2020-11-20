package com.fengchao.statistics.rpc;

import com.alibaba.fastjson.JSONObject;
import com.fengchao.statistics.feign.VendorsServiceClient;
import com.fengchao.statistics.rpc.extmodel.ResultObject;
import com.fengchao.statistics.rpc.extmodel.SysCompany;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author tom
 * @Date 19-7-27 上午10:34
 */
@Service
@Slf4j
public class VendorsRpcService {

    private VendorsServiceClient vendorsServiceClient;

    @Autowired
    public VendorsRpcService(VendorsServiceClient vendorsServiceClient) {
        this.vendorsServiceClient = vendorsServiceClient;
    }

    /**
     * 根据id集合查询商户信息
     *
     * @param merchantIdList
     * @return
     */
    public List<SysCompany> queryMerchantByIdList(List<Integer> merchantIdList) {
        // 返回值
        List<SysCompany> sysCompanyList = new ArrayList<>();

        // 执行rpc调用
        log.info("根据id集合查询商户信息 调用vendors rpc服务 入参:{}", JSONUtil.toJsonString(merchantIdList));

        // 将merchantIdList转成Long型
        List<Long> idList = merchantIdList.stream().map(m -> m.longValue()).collect(Collectors.toList());
        ResultObject<List<SysCompany>> resultObject = vendorsServiceClient.queryMerchantByIdList(idList);
        log.info("根据id集合查询商户信息 调用vendors rpc服务 返回:{}", JSONUtil.toJsonString(resultObject));

        // 处理返回
        if (resultObject.getCode() == 200) {
            sysCompanyList = resultObject.getData();
        } else {
            log.warn("根据id集合查询商户信息 调用vendors rpc服务 错误!");
        }

        log.info("VendorsRpcService#queryMerchantByIdList 调用vendors rpc服务 返回:{}",
                JSONUtil.toJsonString(sysCompanyList));

        return sysCompanyList;
    }

    public List<String> queryAppIdList(String renterId){
        List<String> renterCompanyList = new ArrayList<>();

        ResultObject<List<String>> response = vendorsServiceClient.queryAppIdList(renterId) ;

        log.debug("vendor 服务 queryAppIdList 入参renterId： {},  返回值：{}",renterId, JSONUtil.toJsonString(response));
        if (response.getCode() == 200) {
            renterCompanyList = response.getData() ;
        } else {
            log.warn("查询所有的商户信息 调用vendors rpc服务 错误!");
        }
        return renterCompanyList;

    }

    public List<Integer> queryRenterMerhantList(String renterId){
        List<Integer> renterCompanyList = new ArrayList<>();

        ResultObject<List<Integer>> response = vendorsServiceClient.queryRenterMerchantList(renterId) ;

        log.debug("vendor 服务 queryRenterMerhantList 入参renterId： {},  返回值：{}",renterId, JSONUtil.toJsonString(response));
        if (response.getCode() == 200) {
            renterCompanyList = response.getData() ;
        } else {
            log.warn("查询所有的商户信息 调用vendors rpc服务 错误!");
        }
        return renterCompanyList;

    }

    public JSONObject queryAppIdAndRenterIdMap(List<String> appIds){
        JSONObject jsonObject = new JSONObject();

        ResultObject<JSONObject> response = vendorsServiceClient.queryAppIdAndRenterId(appIds) ;

        log.debug("vendor 服务 queryAppIdAndRenterIdMap 入参appIds： {},  返回值：{}",appIds, JSONUtil.toJsonString(response));
        if (response.getCode() == 200) {
            jsonObject = response.getData() ;
        } else {
            log.warn("查询所有的商户信息 调用vendors rpc服务 错误!");
        }
        return jsonObject;

    }


}
