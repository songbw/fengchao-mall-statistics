package com.fengchao.statistics.rpc;

import com.fengchao.statistics.bean.OperaResponse;
import com.fengchao.statistics.feign.WorkOrdersServiceClient;
import com.fengchao.statistics.rpc.extmodel.WorkOrder;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author tom
 * @Date 19-7-25 下午2:29
 */
@Service
@Slf4j
public class WorkOrdersRpcService {

    private WorkOrdersServiceClient workOrdersServiceClient;

    @Autowired
    public WorkOrdersRpcService(WorkOrdersServiceClient workOrdersServiceClient) {
        this.workOrdersServiceClient = workOrdersServiceClient;
    }

    /**
     * 查询退货单数
     *
     * @return
     */
    public int queryRefundOrdersCount() {
        // 返回值
        int refundOrdersCount = 0;

        // 执行rpc调用
        log.info("查询退货人数 调用workorders rpc服务 入参:无");
        OperaResponse operaResponse = workOrdersServiceClient.refundOrdersCount();
        log.info("查询退货人数 调用workorders rpc服务 返回:{}", JSONUtil.toJsonString(operaResponse));

        // 处理返回
        if (operaResponse.getCode() == 200) {
            Map _resultMap = (Map) operaResponse.getData();
            refundOrdersCount = (int) _resultMap.get("count");
        } else {
            log.warn("查询已支付的子订单 调用workorders rpc服务 错误!");
        }

        log.info("WorkOrdersRpcService#queryRefundOrdersCount 调用workorders rpc服务 返回:{}",
                JSONUtil.toJsonString(refundOrdersCount));

        return refundOrdersCount;
    }

    /**
     * 根据时间范围获取退货信息列表
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public List<WorkOrder> queryRefundInfoList(String startDateTime, String endDateTime) {
        // 返回值
        List<WorkOrder> workOrderList = new ArrayList<>();

        // 执行rpc调用
        log.info("根据时间范围获取退货信息列表 调用workorders rpc服务 入参:无");
        OperaResponse<List<WorkOrder>> operaResponse =
                workOrdersServiceClient.queryRefundInfoList(startDateTime, endDateTime);
        log.info("根据时间范围获取退货信息列表 调用workorders rpc服务 返回:{}", JSONUtil.toJsonString(operaResponse));

        // 处理返回
        if (operaResponse.getCode() == 200) {
            workOrderList = operaResponse.getData();
        } else {
            log.warn("根据时间范围获取退货信息列表 调用workorders rpc服务 错误!");
        }

        log.info("WorkOrdersRpcService#queryRefundInfoList 调用workorders rpc服务 返回:{}",
                JSONUtil.toJsonString(workOrderList));

        return workOrderList;
    }

    /**
     * 根据商户id查询退货人数
     *
     * @param merchantId
     * @return
     */
    public Integer queryRefundUserCountByMerchantId(Long merchantId) {
        // 返回值
        Integer refundUserCount = 0;

        // 执行rpc调用
        log.info("根据商户id查询退货人数 调用workorders rpc服务 入参 merchantId:{}", merchantId);
        OperaResponse<Integer> operaResponse = workOrdersServiceClient.queryRefundUserCountByMerchantId(merchantId);
        log.info("根据商户id查询退货人数 调用workorders rpc服务 返回:{}", JSONUtil.toJsonString(operaResponse));

        // 处理返回
        if (operaResponse.getCode() == 200) {
            refundUserCount = operaResponse.getData();
        } else {
            log.warn("根据商户id查询退货人数 调用workorders rpc服务 错误!");
        }

        log.info("WorkOrdersRpcService#queryRefundUserCountByMerchantId 调用workorders rpc服务 返回:{}",
                JSONUtil.toJsonString(refundUserCount));

        return refundUserCount;
    }

}