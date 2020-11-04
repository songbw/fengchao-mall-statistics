package com.fengchao.statistics.rpc;

import com.fengchao.statistics.bean.OperaResult;
import com.fengchao.statistics.feign.SsoServiceClient;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author tom
 * @Date 19-7-25 下午2:29
 */
@Service
@Slf4j
public class SsoRpcService {

    private SsoServiceClient ssoServiceClient;

    @Autowired
    public SsoRpcService(SsoServiceClient ssoServiceClient) {
        this.ssoServiceClient = ssoServiceClient;
    }

    /**
     * 查询退货人数
     *
     * @return
     */
    public int queryAllUsercount(String renterId) {
        // 返回值
        int userCount = 0;

        // 执行rpc调用
        log.info("查询所有用户数 调用sso rpc服务 入参:无");
        OperaResult operaResult = ssoServiceClient.queryAllUsercount(renterId);
        log.info("查询所有用户数 调用sso rpc服务 返回:{}", JSONUtil.toJsonString(operaResult));

        // 处理返回
        if (operaResult.getCode() == 200) {
            userCount = (int) operaResult.getData().get("count");
        } else {
            log.warn("查询所有用户数 调用sso rpc服务 错误!");
        }

        log.info("SsoRpcService#queryAllUsercount 调用sso rpc服务 返回:{}", userCount);

        return userCount;
    }


}