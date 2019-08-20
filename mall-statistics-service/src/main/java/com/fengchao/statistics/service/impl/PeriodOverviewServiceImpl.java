package com.fengchao.statistics.service.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.fengchao.statistics.bean.vo.PeriodOverviewResVo;
import com.fengchao.statistics.constants.StatisticConstants;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.dao.PeriodOverviewDao;
import com.fengchao.statistics.mapper.PeriodOverviewMapper;
import com.fengchao.statistics.model.PeriodOverview;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import com.fengchao.statistics.service.PeriodOverviewService;
import com.fengchao.statistics.utils.DateUtil;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
public class PeriodOverviewServiceImpl implements PeriodOverviewService {

    @Autowired
    private PeriodOverviewMapper mapper;

    @Autowired
    private PeriodOverviewDao periodOverviewDao;

    @Override
    public void doStatistic(List<OrderDetailBean> orderDetailBeanList, String startDateTime,
                            String endDateTime, Date statisticDate) throws Exception {
        log.info("按照时间段period(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 开始...", startDateTime, endDateTime);
        try {
            // 1. 创建统计数据
            PeriodOverview periodOverview = new PeriodOverview();

            periodOverview.setEarlyMorning(0L);
            periodOverview.setMorning(0L);
            periodOverview.setNoon(0L);
            periodOverview.setAfternoon(0L);
            periodOverview.setNight(0L);
            periodOverview.setLateAtNight(0L);


            // 2. 将获取的订单按照时间段分组 - 并形成统计数据
            for (OrderDetailBean orderDetailBean : orderDetailBeanList) {
                // 订单金额
                Long saleAmount = 0L; // 单位：分
                if (orderDetailBean.getSaleAmount() != null) {
                    saleAmount = new BigDecimal(orderDetailBean.getSaleAmount()).multiply(new BigDecimal(100)).longValue();
                }

                //
                String orderCreateTime = DateUtil.dateTimeFormat(
                        orderDetailBean.getPaymentAt() == null ? new Date() : orderDetailBean.getPaymentAt(), DateUtil.TIME_HH_mm_ss);

                TimeRangeEnum timeRangeEnum = TimeRangeEnum.checkInRange(orderCreateTime, DateUtil.TIME_HH_mm_ss);
                switch (timeRangeEnum) {
                    case EARLYMORNINGRANGE:  // 凌晨
                        periodOverview.setEarlyMorning(periodOverview.getEarlyMorning() + saleAmount);
                        break;
                    case MORNING: // 上午
                        periodOverview.setMorning(periodOverview.getMorning() + saleAmount);
                        break;
                    case NOON:// 中午
                        periodOverview.setNoon(periodOverview.getNoon() + saleAmount);
                        break;
                    case AFTERNOON: // 下午
                        periodOverview.setAfternoon(periodOverview.getAfternoon() + saleAmount);
                        break;
                    case NIGHT: // 晚上
                        periodOverview.setNight(periodOverview.getNight() + saleAmount);
                        break;
                    case LATEATNIGHT: // 深夜
                        periodOverview.setLateAtNight(periodOverview.getLateAtNight() + saleAmount);
                        break;
                }
            }

            // 统计时间
            periodOverview.setStatisticsDate(statisticDate);
            periodOverview.setStatisticStartTime(DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
            periodOverview.setStatisticEndTime(DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
            periodOverview.setPeriodType(StatisticPeriodTypeEnum.DAY.getValue().shortValue());

            log.info("按照时间段period(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 统计结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(periodOverview));

            // 4. 插入统计数据
            // 4.1 首先按照“统计时间”和“统计类型”从数据库获取是否有已统计过的数据; 如果有，则删除
            int count = periodOverviewDao.deleteByPeriodTypeAndStatisticDate(
                    StatisticPeriodTypeEnum.DAY.getValue().shortValue(),
                    DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS),
                    DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
            log.info("按照时间段period(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 删除数据条数:{}",
                    startDateTime, endDateTime, count);

            // 4.2 执行插入
            periodOverviewDao.insertPeriodOverview(periodOverview);

            log.info("按照时间段period(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 执行完成!", startDateTime, endDateTime);
        } catch (Exception e) {
            log.error("按照时间段period(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 异常",
                    startDateTime, endDateTime, e.getMessage(), e);

            Cat.logEvent(StatisticConstants.DAILY_STATISTIC_EXCEPTION_TYPE, StatisticConstants.PERIOD, Event.SUCCESS, "traceId=" + MDC.get("X-B3-TraceId"));
        }

    }

    @Override
    public List<PeriodOverviewResVo> fetchStatisticDailyResult(String startDate, String endDate) throws Exception {
        // 1. 查询数据库
        Date _startDate = DateUtil.parseDateTime(startDate + " 00:00:00", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        Date _endDate = DateUtil.parseDateTime(endDate + " 23:59:59", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        log.info("根据时间范围获取daily型的时间段维度统计数据 日期范围: {} - {}", _startDate, _endDate);
        List<PeriodOverview> periodOverviewList =
                periodOverviewDao.selectDailyStatisticByDateRange(_startDate, _endDate);
        log.info("根据时间范围获取daily型的时间段维度统计数据 数据库返回: {}", JSONUtil.toJsonString(periodOverviewList));

        if (CollectionUtils.isEmpty(periodOverviewList)) {
            return Collections.emptyList();
        }

        // 根据start时间排序
        periodOverviewList.sort(Comparator.comparing(PeriodOverview::getId));

        // 2. 转统计数据
        List<PeriodOverviewResVo> periodOverviewResVoList = new ArrayList<>();
        for (PeriodOverview periodOverview : periodOverviewList) {
            PeriodOverviewResVo periodOverviewResVo = new PeriodOverviewResVo();

            BigDecimal divided = new BigDecimal(100);
            periodOverviewResVo.setEarlyMorning(new BigDecimal(periodOverview.getEarlyMorning())
                    .divide(divided).toString()); // 凌晨
            periodOverviewResVo.setMorning(new BigDecimal(periodOverview.getMorning())
                    .divide(divided).toString()); // 上午
            periodOverviewResVo.setNoon(new BigDecimal(periodOverview.getNoon())
                    .divide(divided).toString()); // 中午
            periodOverviewResVo.setAfternoon(new BigDecimal(periodOverview.getAfternoon())
                    .divide(divided).toString()); // 下午
            periodOverviewResVo.setNight(new BigDecimal(periodOverview.getNight())
                    .divide(divided).toString()); // 晚上
            periodOverviewResVo.setLateAtNight(new BigDecimal(periodOverview.getLateAtNight())
                    .divide(divided).toString()); // 深夜
            periodOverviewResVo.setStatisticsDate(DateUtil.
                    dateTimeFormat(periodOverview.getStatisticStartTime(), DateUtil.DATE_YYYY_MM_DD)); // 数据时间

            periodOverviewResVoList.add(periodOverviewResVo);
        }

        // 将统计数据按照时间排序
        periodOverviewResVoList.sort(Comparator.comparing(PeriodOverviewResVo::getStatisticsDate));

        log.info("根据时间范围获取daily型的时间段维度统计数据 获取统计数据 List<PeriodOverviewResVo>:{}",
                JSONUtil.toJsonString(periodOverviewResVoList));

        return periodOverviewResVoList;
    }

    // ====================================== private ==================================

    /**
     * 时间范围
     */
    private enum TimeRangeEnum {

        EARLYMORNINGRANGE("02:00:00", "05:59:59"), // 凌晨
        MORNING("06:00:00", "09:59:59"), // 上午
        NOON("10:00:00", "13:59:59"), // 中午
        AFTERNOON("14:00:00", "17:59:59"), // 下午
        NIGHT("18:00:00", "21:59:59"), // 晚上
        LATEATNIGHT("22:00:00", "01:59:59"); // 深夜

        LocalTime startTime;

        LocalTime endTime;

        TimeRangeEnum(String startTime, String endTime) {
            this.startTime = DateUtil.convertToLocalTime(startTime, DateUtil.TIME_HH_mm_ss);
            this.endTime = DateUtil.convertToLocalTime(endTime, DateUtil.TIME_HH_mm_ss);
        }

        /**
         * 判断一个时间点是否在一个时间范围内
         *
         * @param timePiont
         * @return true 在该时间范围内， fasle 不在该时间范围内
         */
        protected static TimeRangeEnum checkInRange(String timePiont, String format) {
            LocalTime point = DateUtil.convertToLocalTime(timePiont, format);

            if (point.compareTo(EARLYMORNINGRANGE.getStartTime()) >= 0 &&
                    point.compareTo(EARLYMORNINGRANGE.getEndTime()) <= 0) {
                return EARLYMORNINGRANGE;
            } else if (point.compareTo(MORNING.getStartTime()) >= 0 &&
                    point.compareTo(MORNING.getEndTime()) <= 0) {
                return MORNING;
            } else if (point.compareTo(NOON.getStartTime()) >= 0 &&
                    point.compareTo(NOON.getEndTime()) <= 0) {
                return NOON;
            } else if (point.compareTo(AFTERNOON.getStartTime()) >= 0 &&
                    point.compareTo(AFTERNOON.getEndTime()) <= 0) {
                return AFTERNOON;
            } else if (point.compareTo(NIGHT.getStartTime()) >= 0 &&
                    point.compareTo(NIGHT.getEndTime()) <= 0) {
                return NIGHT;
            } else if (point.compareTo(LATEATNIGHT.getStartTime()) >= 0 &&
                    point.compareTo(LATEATNIGHT.getEndTime()) <= 0) {
                return LATEATNIGHT;
            } else {
                return null;
            }
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }
    }


}
