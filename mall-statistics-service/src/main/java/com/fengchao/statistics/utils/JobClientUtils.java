package com.fengchao.statistics.utils;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import org.springframework.stereotype.Component;

@Component
public class JobClientUtils {

    /**
     * 定时统计订单任务
     */
    public static void orderStatisticsTrigger(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("order_statistics_trigger_23H");
        job.setParam("type", "orderStatistics");
        job.setTaskTrackerNodeGroup("statistics_trade_TaskTracker");
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // 当任务队列中存在这个任务的时候，是否替换更新
        job.setCronExpression("0 0 23 * * ?");   // 每天23点执行
        jobClient.submitJob(job);
    }
}
