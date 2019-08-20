package com.fengchao.statistics.jobClient;

import brave.propagation.CurrentTraceContext;
import brave.propagation.TraceContext;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.fengchao.statistics.constants.StatisticConstants;
import com.fengchao.statistics.utils.JSONUtil;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.spring.boot.annotation.JobRunner4TaskTracker;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import static com.fengchao.statistics.constants.StatisticConstants.*;

/**
 * @author Robert HG (254963746@qq.com) on 4/9/16.
 */
@JobRunner4TaskTracker
@Slf4j
public class JobRunnerDispatcher implements JobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRunnerDispatcher.class);

    private static final ConcurrentHashMap<String, JobRunner> JOB_RUNNER_MAP = new ConcurrentHashMap<String, JobRunner>();

    static {
        JOB_RUNNER_MAP.put("orderStatistics", new OrderStatisticsRunner());
    }

    @Override
    public Result run(JobContext jobContext) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("统计任务执行时间");

        Transaction transaction = Cat.newTransaction(DAILY_STATISTIC_TYPE, ORDER_DAILY_STATISTIC);

        try {
            // sleuth 自定义trace begin TODO : 目前MDC不设置会导致当前线程不打印traceid
            CurrentTraceContext currentTraceContext = CurrentTraceContext.Default.create();
            currentTraceContext.newScope(TraceContext.newBuilder().traceId(new Date().getTime()).spanId(1L).build());
            String traceId = currentTraceContext.get().traceIdString();
            MDC.put("X-B3-TraceId", traceId);
            // sleuth 自定义trace begin

            log.info("接收到统计执行任务:{}", JSONUtil.toJsonString(jobContext));

            Job job = jobContext.getJob();
            String type = job.getParam("type");

            if (type != null && !"".equals(type)) {
                OrderStatisticsRunner orderStatisticsRunner = (OrderStatisticsRunner) JOB_RUNNER_MAP.get(type);

                // 执行!!!!
                Result result = orderStatisticsRunner.run(jobContext);

                transaction.setStatus(Transaction.SUCCESS);
                transaction.addData("traceId", MDC.get("X-B3-TraceId"));
                return result;
            } else {
                LOGGER.warn("统计执行任务 type is null.");
                throw new Exception("统计执行任务 type is null.");
            }
        } catch (Throwable e) {
            log.info("统计执行任务执行异常:{}", e.getMessage(), e);

            Cat.logEvent(DAILY_STATISTIC_EXCEPTION_TYPE, StatisticConstants.ORDER_DAILY_STATISTIC,
                    Event.SUCCESS, "traceId=" + MDC.get("X-B3-TraceId") + ";duration=" + stopWatch.getTotalTimeSeconds());

            transaction.setStatus(e);
            transaction.addData("traceId", MDC.get("X-B3-TraceId"));

            return new Result(Action.EXECUTE_SUCCESS, "统计执行任务执行异常");
        } finally {
            stopWatch.stop();

            transaction.addData("duration", stopWatch.getTotalTimeSeconds());
            transaction.complete();
        }

    }
}
