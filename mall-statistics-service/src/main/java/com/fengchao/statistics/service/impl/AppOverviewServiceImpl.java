package com.fengchao.statistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.fengchao.statistics.bean.vo.AppOverviewResVo;
import com.fengchao.statistics.bean.vo.CategoryOverviewResVo;
import com.fengchao.statistics.constants.StatisticConstants;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.dao.AppOverviewDao;
import com.fengchao.statistics.model.AppOverview;
import com.fengchao.statistics.model.CategoryOverview;
import com.fengchao.statistics.rpc.VendorsRpcService;
import com.fengchao.statistics.rpc.extmodel.CategoryQueryBean;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import com.fengchao.statistics.service.AppOverviewService;
import com.fengchao.statistics.utils.DateUtil;
import com.fengchao.statistics.utils.FengchaoMailUtil;
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

/**
 * @author songbw
 * @date 2020/12/1 15:38
 */
@Service
@Slf4j
public class AppOverviewServiceImpl implements AppOverviewService {

    @Autowired
    private VendorsRpcService vendorsRpcService;
    @Autowired
    private AppOverviewDao appOverviewDao ;


    @Override
    public void doDailyStatistic(List<OrderDetailBean> orderDetailBeanList, String startDateTime, String endDateTime, Date statisticDate) throws Exception {
        log.info("按照appId(天)维度统计订单详情总金额数据; 统计时间范围：{} - {}, 开始...", startDateTime, endDateTime);

        try {
            // 1. 根据APP ID维度将订单详情分组
            Map<String, List<OrderDetailBean>> orderDetailBeanListMap = new HashMap<>();
            List<Integer> firstCategorys = new ArrayList<>();
            List<String> appIds = orderDetailBeanList.stream().map(orderDetailBean -> {
                String appId = orderDetailBean.getSubOrderId().substring(0, 2) ;
                return appId;
            }).collect(Collectors.toList());
            JSONObject jsonObject = vendorsRpcService.queryAppIdAndRenterIdMap(appIds) ;
            for (OrderDetailBean orderDetailBean : orderDetailBeanList) { // 遍历订单详情
                // 处理品类信息
                String subOrderId = orderDetailBean.getSubOrderId() ;
                String appId = subOrderId.substring(0, 2) ;
                orderDetailBean.setAppId(appId);
                String renterId = jsonObject.getString(appId) ;
                if (StringUtils.isNotBlank(renterId)) {

                    List<OrderDetailBean> _list = orderDetailBeanListMap.get(renterId);
                    if (_list == null) {
                        _list = new ArrayList<>();
                        orderDetailBeanListMap.put(renterId, _list);
                    }
                    _list.add(orderDetailBean);
                }
            }

            log.info("按照appId(天)维度统计订单详情总金额数据; 根据一级品类维度将订单详情分组结果:{}", JSONUtil.toJsonString(orderDetailBeanListMap));
            List<AppOverview> appOverviewList = new ArrayList<>();
            orderDetailBeanListMap.forEach((k, v) -> {
                List<OrderDetailBean> _orderDetailBeanList = v;
                // 计算总金额
                BigDecimal totalPrice = new BigDecimal(0);
                for (OrderDetailBean orderDetailBean : _orderDetailBeanList) {
                    BigDecimal _count = new BigDecimal(orderDetailBean.getNum());
                    BigDecimal _tmpPrice = new BigDecimal(orderDetailBean.getSaleAmount()).multiply(_count);
                    totalPrice = totalPrice.add(_tmpPrice);
                }
                AppOverview appOverview = new AppOverview();
                appOverview.setAppId(_orderDetailBeanList.get(0).getAppId());
                appOverview.setOrderAmount(totalPrice.multiply(new BigDecimal(100)).longValue());
                appOverview.setStatisticsDate(statisticDate);
                appOverview.setStatisticStartTime(DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                appOverview.setStatisticEndTime(DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                appOverview.setPeriodType(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
                appOverview.setRenterId(k);
                appOverviewList.add(appOverview) ;
            });

            log.info("按照appId(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 统计结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(appOverviewList));

            // 4. 插入统计数据
            // 4.1 首先按照“统计时间”和“统计类型”从数据库获取是否有已统计过的数据; 如果有，则删除
            int count = appOverviewDao.deleteByPeriodTypeAndStatisticDate(StatisticPeriodTypeEnum.DAY.getValue().shortValue(),
                    DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS),
                    DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));

            log.info("按照appId(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 删除数据条数:{}",
                    startDateTime, endDateTime, count);

            // 4.2 执行插入
            for (AppOverview appOverview : appOverviewList) {
                appOverviewDao.insertOverview(appOverview);
            }

            log.info("按照appId(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 执行完成!");
        } catch (Exception e) {
            log.error("按照appId(天)维度统计订单详情总金额数据; 统计时间范围：{} - {}, 异常:{}",
                    startDateTime, endDateTime, e.getMessage(), e);

            Cat.logEvent(StatisticConstants.DAILY_STATISTIC_EXCEPTION_TYPE, StatisticConstants.CATEGORY, Event.SUCCESS, "traceId=" + MDC.get("X-B3-TraceId"));

            FengchaoMailUtil.send("统计执行异常", "按照appId(天)维度统计订单详情总金额数据 执行异常:" + e.getMessage());
        }
    }

    @Override
    public List<AppOverviewResVo> fetchStatisticDailyResult(String startDate, String endDate, String renterHeader) throws Exception {
        // 1. 查询数据库
        Date _startDate = DateUtil.parseDateTime(startDate + " 00:00:00", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        Date _endDate = DateUtil.parseDateTime(endDate + " 23:59:59", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        log.debug("根据时间范围获取daily型的品类维度统计数据 日期范围: {} - {}", _startDate, _endDate);

        List<AppOverview> appOverviewList =
                appOverviewDao.selectDailyStatisticByDateRange(_startDate, _endDate, renterHeader);
        log.debug("根据时间范围获取daily型的品类维度统计数据 数据库返回: {}", JSONUtil.toJsonString(appOverviewList));

        if (CollectionUtils.isEmpty(appOverviewList)) {
            return Collections.emptyList();
        }

        // 2. 将获取到的数据按照app分组
        Map<String, List<AppOverview>> appOverviewListMap = new HashMap<>();
        for (AppOverview appOverview : appOverviewList) {
            String appId = appOverview.getAppId(); // 一级品类code

            List<AppOverview> _appOverviewList = appOverviewListMap.get(appId);
            if (_appOverviewList == null) {
                _appOverviewList = new ArrayList<>();
                appOverviewListMap.put(appId, _appOverviewList);
            }

            _appOverviewList.add(appOverview);
        }

        log.info("根据时间范围获取daily型的app维度统计数据 按照app分组Map<String, List<AppOverview>>:{}",
                JSONUtil.toJsonString(appOverviewListMap));

        // 3. 组装统计数据 AppOverviewResVo
        List<AppOverviewResVo> appOverviewResVoList = new ArrayList<>();
        Set<String> keySet = appOverviewListMap.keySet();
        for (String key : keySet) {
            List<AppOverview> _appOverviewList = appOverviewListMap.get(key);

            Long totalAmount = 0L; // 单位：分
            for (AppOverview appOverview : _appOverviewList) {
                totalAmount = totalAmount + appOverview.getOrderAmount();
            }

            AppOverviewResVo appOverviewResVo = new AppOverviewResVo();
            appOverviewResVo.setAppId(key);
            appOverviewResVo.setOrderAmount(new BigDecimal(totalAmount).divide(new BigDecimal(100)).toString());

            appOverviewResVoList.add(appOverviewResVo);
        }

        log.info("根据时间范围获取daily型的app维度统计数据 获取统计数据List<AppOverview>:{}",
                JSONUtil.toJsonString(appOverviewResVoList));


        return appOverviewResVoList;
    }
}
