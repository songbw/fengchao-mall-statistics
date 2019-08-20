package com.fengchao.statistics.rpc;

import com.alibaba.fastjson.JSON;
import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.feign.ProductServiceClient;
import com.fengchao.statistics.rpc.extmodel.CategoryQueryBean;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author tom
 * @Date 19-7-26 上午11:25
 */
@Service
@Slf4j
public class ProductRpcService {

    private ProductServiceClient productServiceClient;

    @Autowired
    public ProductRpcService(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    /**
     * 根据categoryId集合查询品类列表
     *
     * @param categoryIdList
     */
    public List<CategoryQueryBean> queryCategorysByCategoryIdList(List<Integer> categoryIdList) {
        // 返回值
        List<CategoryQueryBean> categoryQueryBeanList = new ArrayList<>();

        // 执行rpc调用
        log.info("根据categoryId集合查询品类列表 调用product rpc服务 入参:{}", JSONUtil.toJsonString(categoryIdList));
        OperaResponse<List<CategoryQueryBean>> operaResponse = productServiceClient.queryCategorysByCategoryIdList(categoryIdList);
        log.info("根据categoryId集合查询品类列表 调用product rpc服务 返回:{}", JSONUtil.toJsonString(operaResponse));

        // 处理返回
        if (operaResponse.getCode() == 200) {
            categoryQueryBeanList = operaResponse.getData();
        } else {
            log.warn("查询已支付的子订单 调用product rpc服务 错误!");
        }

        log.info("ProductRpcService#queryCategorysByCategoryIdList 调用product rpc服务 返回:{}",
                JSONUtil.toJsonString(categoryQueryBeanList));

        return categoryQueryBeanList;
    }





}
