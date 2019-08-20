package com.fengchao.statistics.service.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.fengchao.statistics.bean.PromotionTypeResDto;
import com.fengchao.statistics.constants.StatisticConstants;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.dao.PromotionOverviewDao;
import com.fengchao.statistics.model.PromotionOverview;
import com.fengchao.statistics.rpc.EquityRpcService;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import com.fengchao.statistics.rpc.extmodel.PromotionBean;
import com.fengchao.statistics.service.PromotionOverviewService;
import com.fengchao.statistics.utils.DateUtil;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PromotionOverviewServiceImpl implements PromotionOverviewService {

    @Autowired
    private EquityRpcService equityRpcService;

    @Autowired
    private PromotionOverviewDao promotionOverviewDao;

    @Override
    public void doDailyStatistic(List<OrderDetailBean> orderDetailBeanList, String startDateTime,
                                 String endDateTime, Date statisticDate) throws Exception {
        log.info("按照活动promotion(天)维度统计订单详情总数量数据; 统计时间范围：{} - {} 开始...", startDateTime, endDateTime);

        try {
            // 0. 获取活动类别信息
            // 获取所有活动类型，准备插入空数据
            List<PromotionTypeResDto> promotionTypeResDtoList = equityRpcService.queryAllPromotionTypeList();
            // 准备空数据， 以'活动类别'维度生成空统计数据的map
            Map<String, List<OrderDetailBean>> orderDetailBeansByPromotionTypeMap = new HashMap<>(); // !!
            orderDetailBeansByPromotionTypeMap.put("其他", new ArrayList<>());
            for (PromotionTypeResDto promotionTypeResDto : promotionTypeResDtoList) {
                orderDetailBeansByPromotionTypeMap.put(promotionTypeResDto.getTypeName(), new ArrayList<>());
            }

            // 1. 获取活动信息
            // 获取所有订单详情的活动id集合
            Set<Integer> promotionIdSet =
                    orderDetailBeanList.stream().map(o -> o.getPromotionId()).collect(Collectors.toSet());
            // 获取活动信息
            List<PromotionBean> promotionBeanList = equityRpcService.queryPromotionByIdList(new ArrayList<>(promotionIdSet));
            // 转map key:promotionId  value:PromotionBean
            Map<Integer, PromotionBean> promotionBeanMap = promotionBeanList.stream()
                    .collect(Collectors.toMap(p -> p.getId().intValue(), p -> p));


            // 2. 根据'活动类别'维度将订单详情分组 活动类型 1:秒杀 2:优选 3:普通
            for (OrderDetailBean orderDetailBean : orderDetailBeanList) {
                // 从订单中获取活动id
                Integer promotionId = orderDetailBean.getPromotionId();
                if (promotionId == null || promotionId == 0) { // 无活动的订单
                    promotionId = -1;
                }

                // 获取该订单的活动类型
                String promotionTypeName = "其他";
                PromotionBean promotionBean = promotionBeanMap.get(promotionId);
                if (promotionBean != null && StringUtils.isNotBlank(promotionBean.getTypeName())) { // 活动类型 1:秒杀 2:优选 3:普通
                    promotionTypeName = promotionBean.getTypeName();
                }

                List<OrderDetailBean> _orderDetailBeanList = orderDetailBeansByPromotionTypeMap.get(promotionTypeName);
                if (_orderDetailBeanList == null) {
                    _orderDetailBeanList = new ArrayList<>();
                    orderDetailBeansByPromotionTypeMap.put(promotionTypeName, _orderDetailBeanList);
                }
                _orderDetailBeanList.add(orderDetailBean);
            }
            log.info("按照活动promotion(天)维度统计订单详情总数量数据; 统计时间范围：{} - {} 根据'活动类别'维度将订单详情分组结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(orderDetailBeansByPromotionTypeMap));

            // 3. 获取统计数据
            List<PromotionOverview> promotionOverviewList = new ArrayList<>(); // 统计数据
            Set<String> promotionTypeSet = orderDetailBeansByPromotionTypeMap.keySet();
            for (String promotionTypeName : promotionTypeSet) { // 遍历
                List<OrderDetailBean> _orderDetailBeanList = orderDetailBeansByPromotionTypeMap.get(promotionTypeName);

                Long orderAmout = 0L; // 统计订单数量
                for (OrderDetailBean orderDetailBean : _orderDetailBeanList) {
                    Float _tmpPrice = (orderDetailBean.getSaleAmount() == null ? 0L : orderDetailBean.getSaleAmount());

                    orderAmout = orderAmout + new BigDecimal(_tmpPrice).multiply(new BigDecimal(100)).longValue();
                }

                // 组装统计数据
                PromotionOverview promotionOverview = new PromotionOverview();

                promotionOverview.setPromotionType(promotionTypeName);
                promotionOverview.setOrderAmount(orderAmout);

                promotionOverview.setStatisticsDate(statisticDate);
                promotionOverview.setStatisticStartTime(DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                promotionOverview.setStatisticEndTime(DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                promotionOverview.setPeriodType(StatisticPeriodTypeEnum.DAY.getValue().shortValue());

                promotionOverviewList.add(promotionOverview); //
            }


            log.info("按照活动promotion(天)维度统计订单详情总数量数据; 统计时间范围：{} - {} 统计结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(promotionOverviewList));

            // 4. 插入统计数据
            // 4.1 首先按照“统计时间”和“统计类型”从数据库获取是否有已统计过的数据; 如果有，则删除
            int count = promotionOverviewDao.deleteByPeriodTypeAndStatisticDate(
                    StatisticPeriodTypeEnum.DAY.getValue().shortValue(),
                    DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS),
                    DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
            log.info("按照活动promotion(天)维度统计订单详情总数量数据; 统计时间范围：{} - {} 删除数据条数:{}",
                    startDateTime, endDateTime, count);

            // 4.2 执行插入
            for (PromotionOverview promotionOverview : promotionOverviewList) {
                promotionOverviewDao.insertCategoryOverview(promotionOverview);
            }

            log.info("按照活动promotion(天)维度统计订单详情总数量数据; 统计时间范围：{} - {} 执行完成!", startDateTime, endDateTime);
        } catch (Exception e) {
            log.error("按照活动promotion(天)维度统计订单详情数量额数据; 统计时间范围：{} - {} 异常:{}",
                    startDateTime, endDateTime, e.getMessage(), e);

            Cat.logEvent(StatisticConstants.DAILY_STATISTIC_EXCEPTION_TYPE, StatisticConstants.PROMOTION, Event.SUCCESS, "traceId=" + MDC.get("X-B3-TraceId"));
        }
    }

    @Override
    public List<Map<String, Object>> fetchStatisticDailyResult(String startDate, String endDate) throws Exception {
        // 1. 查询数据库
        Date _startDate = DateUtil.parseDateTime(startDate + " 00:00:00", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        Date _endDate = DateUtil.parseDateTime(endDate + " 23:59:59", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        log.info("根据时间范围获取daily型的活动维度统计数据 日期范围: {} - {}", _startDate, _endDate);
        List<PromotionOverview> promotionOverviewList =
                promotionOverviewDao.selectDailyStatisticByDateRange(_startDate, _endDate);
        log.info("根据时间范围获取daily型的活动维度统计数据 数据库返回: {}", JSONUtil.toJsonString(promotionOverviewList));

        if (CollectionUtils.isEmpty(promotionOverviewList)) {
            return Collections.emptyList();
        }

        // 2. 统计数据
        // 2.1 按照日期维度分组
        Map<String, List<PromotionOverview>> dateRangeMap = new HashMap<>(); // 按照日期维度分组
        for (PromotionOverview promotionOverview : promotionOverviewList) {
            String _date = DateUtil.dateTimeFormat(
                    promotionOverview.getStatisticStartTime(), DateUtil.DATE_YYYY_MM_DD); // 活动name

            List<PromotionOverview> dateRangeList = dateRangeMap.get(_date);
            if (dateRangeList == null) {
                dateRangeList = new ArrayList<>();
                dateRangeMap.put(_date, dateRangeList);
            }

            dateRangeList.add(promotionOverview);
        }

        log.info("根据时间范围获取daily型的活动维度统计数据 按照日期维度分组Map<String, List<PromotionOverview>>:{}",
                JSONUtil.toJsonString(dateRangeMap));

        // 2.2 将数据转换成前端需要的格式：{[date:'2019-09-09', '秒杀':8, '优选':9 ...] ...}
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> dateSet = dateRangeMap.keySet();
        for (String date : dateSet) {
            Map<String, Object> _map = new HashMap<>();
            _map.put("date", date);

            List<PromotionOverview> _promotionOverviewList = dateRangeMap.get(date);
            for (PromotionOverview promotionOverview : _promotionOverviewList) {
                String _orderAmount = new BigDecimal(promotionOverview.getOrderAmount()).divide(new BigDecimal(100)).toString();
                _map.put(promotionOverview.getPromotionType(), _orderAmount);
            }

            result.add(_map);
        }

        log.info("根据时间范围获取daily型的活动维度统计数据 获取统计数据 List<Map<String, Object>>:{}",
                JSONUtil.toJsonString(result));

        return result;
    }

    //====================================== private ======================================

    /**
//     * @param promotionOverview
//     * @return
//     */
//    private PromotionOverviewResVo convertToPromotionOverviewResVo(PromotionOverview promotionOverview) {
//        PromotionOverviewResVo promotionOverviewResVo = new PromotionOverviewResVo();
//
//        private String date; // '2019/6/1',
//
//
//        String promotionTypeName = promotionOverview.getPromotionType();
//
//        private Integer secKill = 0;
//        private Integer premium = 0;
//        private Integer normal = 0;
//        private Integer others = 0;
//
//        return promotionOverviewResVo;
//    }


}
