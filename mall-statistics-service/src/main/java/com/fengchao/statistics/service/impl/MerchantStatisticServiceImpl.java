package com.fengchao.statistics.service.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.fengchao.statistics.bean.vo.MOverallResVo;
import com.fengchao.statistics.bean.vo.MUserStatisticResVo;
import com.fengchao.statistics.bean.vo.MerchantCityRangeStatisticResVo;
import com.fengchao.statistics.constants.StatisticConstants;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.dao.MCityOrderAmountDao;
import com.fengchao.statistics.dao.MOverviewDao;
import com.fengchao.statistics.dao.MStatisticUserDao;
import com.fengchao.statistics.model.MCityOrderamount;
import com.fengchao.statistics.model.MOverview;
import com.fengchao.statistics.model.MStatisticUser;
import com.fengchao.statistics.rpc.OrdersRpcService;
import com.fengchao.statistics.rpc.VendorsRpcService;
import com.fengchao.statistics.rpc.WorkOrdersRpcService;
import com.fengchao.statistics.rpc.extmodel.DayStatisticsBean;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;
import com.fengchao.statistics.rpc.extmodel.SysCompany;
import com.fengchao.statistics.rpc.extmodel.WorkOrder;
import com.fengchao.statistics.service.MerchantStatisticService;
import com.fengchao.statistics.utils.DateUtil;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author tom
 * @Date 19-7-27 下午4:23
 */
@Service
@Slf4j
public class MerchantStatisticServiceImpl implements MerchantStatisticService {

    @Autowired
    private VendorsRpcService vendorsRpcService;

    @Autowired
    private WorkOrdersRpcService workOrdersRpcService;

    @Autowired
    private MOverviewDao mOverviewDao;

    @Autowired
    private MCityOrderAmountDao mCityOrderAmountDao;

    @Autowired
    private OrdersRpcService ordersRpcService;

    @Autowired
    private MStatisticUserDao mStatisticUserDao;

    @Override
    @Deprecated
    public void statisticOverall(List<OrderDetailBean> payedOrderDetailBeanList,
                                 String startDateTime, String endDateTime, Date statisticDate) throws Exception {
        log.info("按天统计各个商户的整体运营数据; 统计时间范围：{} - {} 开始...", startDateTime, endDateTime);

        try {
            // 1. 根据商户id维度将订单详情分组
            Map<Integer, List<OrderDetailBean>> orderDetailBeansByMerchantMap = divideOrderDetailByMerchantId(payedOrderDetailBeanList);

            log.info("按天统计各个商户的整体运营数据 统计时间范围:{} - {}, 根据商户id维度将订单详情分组结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(orderDetailBeansByMerchantMap));

            // 2. 获取商户名称
            Set<Integer> merchantIdSet = orderDetailBeansByMerchantMap.keySet(); // 商户id集合
            List<SysCompany> sysCompanyList = vendorsRpcService.queryMerchantByIdList(new ArrayList<>(merchantIdSet));
            // 转map key:merchantId  value:SysUser
            Map<Integer, SysCompany> sysCompanyMap = sysCompanyList.stream()
                    .collect(Collectors.toMap(u -> u.getId().intValue(), u -> u));

            // 3. 组装统计数据 :
            // 3.1 退货信息
            List<WorkOrder> workOrderList = workOrdersRpcService.queryRefundInfoList(startDateTime, endDateTime);
            // 转map key:merchantId, value:退货单数
            Map<Long, Integer> refundMap = new HashMap<>();
            for (WorkOrder workOrder : workOrderList) {
                Integer refundCount = refundMap.get(workOrder.getMerchantId());
                if (refundCount == null) {
                    refundCount = 0;
                }

                refundCount = refundCount + 1;
                refundMap.put(workOrder.getMerchantId(), refundCount);
            }
            log.info("按天统计各个商户的整体运营数据 统计时间范围：{} - {}, 获取的退货单数信息Map<Long, Integer>:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(refundMap));


            // 3.2 订单 支付总额, 订单总量, 下单人数, 退货单数
            List<MOverview> mOverviewList = new ArrayList<>();
            for (Integer merchantId : merchantIdSet) { // 遍历以商户为维度的数据map
                // 某商户下的订单详情集合
                List<OrderDetailBean> _orderDetailBeanList = orderDetailBeansByMerchantMap.get(merchantId);

                Set<String> orderTradeNoSet = new HashSet<>(); // 主订单tradeNo集合-用于订单总量计算
                Set<String> openIdSet = new HashSet<>(); // 下单人id的集合-用于计算 下单人数
                BigDecimal orderAmountB = new BigDecimal(0); // 订单支付总金额

                for (OrderDetailBean _orderDetailBean : _orderDetailBeanList) {
                    // 拼装主订单tradeNo集合
                    if (_orderDetailBean.getTradeNo() != null) {
                        orderTradeNoSet.add(_orderDetailBean.getTradeNo());
                    }

                    // 拼装下单人id的集合
                    if (_orderDetailBean.getOpenId() != null) {
                        openIdSet.add(_orderDetailBean.getOpenId());
                    }

                    // 订单支付总额
                    Float saleAmount = _orderDetailBean.getSaleAmount();
                    if (saleAmount != null) {
                        orderAmountB = orderAmountB.add(new BigDecimal(saleAmount));
                    }
                }

                // 组装统计数据
                MOverview mOverview = new MOverview();
                mOverview.setMerchantId(merchantId);
                mOverview.setMerchantCode("");
                mOverview.setMerchantName(sysCompanyMap.get(merchantId) == null ?
                        "/" : sysCompanyMap.get(merchantId).getName());
                mOverview.setOrderAmount(orderAmountB.multiply(new BigDecimal(100)).longValue());
                mOverview.setOrderCount(orderTradeNoSet.size());
                mOverview.setOrderUserCount(openIdSet.size());
                mOverview.setRefundOrderCount(refundMap.get(merchantId) == null ? 0 : refundMap.get(merchantId)); // 退货单数
                mOverview.setStatisticStartTime(DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                mOverview.setStatisticEndTime(DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                mOverview.setPeriodType(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
                mOverview.setStatisticsDate(statisticDate);

                mOverviewList.add(mOverview);
            }

            log.info("按天统计各个商户的整体运营数据 统计时间范围：{} - {} 获取的统计数据是:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(mOverviewList));

            // 4. 统计数据入库
            // 4.1 首先按照“统计时间”和“统计类型”从数据库获取是否有已统计过的数据; 如果有，则删除
            int count = mOverviewDao.deleteByPeriodTypeAndStatisticDate(
                    StatisticPeriodTypeEnum.DAY.getValue().shortValue(),
                    DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS),
                    DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));

            log.info("按天统计各个商户的整体运营数据; 统计时间范围：{} - {} 删除数据条数:{}",
                    startDateTime, endDateTime, count);

            // 4.2 执行插入
            for (MOverview mOverview : mOverviewList) {
                mOverviewDao.insertMOverview(mOverview);
            }
        } catch (Exception e) {
            log.error("按天统计各个商户的整体运营数据; 统计时间范围：{} - {} 异常:{}",
                    startDateTime, endDateTime, e.getMessage(), e);
        }
    }

    @Override
    public void statisticDailyOrderAmountByCity(List<OrderDetailBean> orderDetailBeanList,
                                                String startDateTime,
                                                String endDateTime, Date statisticDate) throws Exception {
        log.info("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围: {} - {}, 开始...", startDateTime, endDateTime);

        try {
            if (CollectionUtils.isEmpty(orderDetailBeanList)) {
                log.info("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围: {} - {} 统计数据为空 执行完成!");
                return;
            }

            // 1. 根据商户id维度将订单详情分类
            Map<Integer, List<OrderDetailBean>> orderDetailBeansByMerchantMap = divideOrderDetailByMerchantId(orderDetailBeanList);
            log.info("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围：{} - {}, 根据商户id维度将订单详情分组结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(orderDetailBeansByMerchantMap));

            // 2. 获取商户名称
            Set<Integer> merchantIdSet = orderDetailBeansByMerchantMap.keySet(); // 商户id集合
            List<SysCompany> sysCompanyList = vendorsRpcService.queryMerchantByIdList(new ArrayList<>(merchantIdSet));
            // 转map key:merchantId  value:SysUser
            Map<Integer, SysCompany> sysCompanyMap = sysCompanyList.stream()
                    .collect(Collectors.toMap(u -> u.getId().intValue(), u -> u));

            // 3. 获取统计数据
            // // 统计数据
            List<MCityOrderamount> mCityOrderamoutList = new ArrayList<>();
            for (Integer merchantId : merchantIdSet) { // 遍历以商户为维度的数据map
                // 以商户为维度的数据列表
                List<OrderDetailBean> _orderDetailBeanList = orderDetailBeansByMerchantMap.get(merchantId);

                // 3.1 将“以商户为维度的数据列表”继续以cityId维度分组
                Map<String, List<OrderDetailBean>> cityRangeOrderDetailMap = new HashMap<>();
                for (OrderDetailBean orderDetailBean : _orderDetailBeanList) {
                    String cityId = orderDetailBean.getCityId();
                    List<OrderDetailBean> cityRangeOrderDetailList = cityRangeOrderDetailMap.get(cityId);
                    if (cityRangeOrderDetailList == null) {
                        cityRangeOrderDetailList = new ArrayList<>();
                        cityRangeOrderDetailMap.put(cityId, cityRangeOrderDetailList);
                    }
                    cityRangeOrderDetailList.add(orderDetailBean);
                }
                log.info("按照商户-城市(天)维度统计订单详情总金额数据; merchantId:{} 以cityId维度分组结果:{}",
                        merchantId, JSONUtil.toJsonString(cityRangeOrderDetailMap));

                // 3.2 组装统计数据
                Set<String> cityIdSet = cityRangeOrderDetailMap.keySet();
                for (String cityId : cityIdSet) { // 遍历 cityRangeOrderDetailMap
                    List<OrderDetailBean> cityRangeOrderDetailList = cityRangeOrderDetailMap.get(cityId);

                    String cityName = ""; // 城市名称
                    BigDecimal orderAmount = new BigDecimal(0);
                    for (OrderDetailBean orderDetailBean : cityRangeOrderDetailList) {
                        BigDecimal _tmpPrice = new BigDecimal(orderDetailBean.getSaleAmount());
                        orderAmount = orderAmount.add(_tmpPrice);
                        cityName = orderDetailBean.getCityName();
                    }

                    // 组装数据
                    MCityOrderamount mCityOrderamount = new MCityOrderamount();
                    mCityOrderamount.setMerchantId(merchantId);
                    mCityOrderamount.setMerchantCode("");
                    mCityOrderamount.setMerchantName(sysCompanyMap.get(merchantId) == null ?
                            "/" : sysCompanyMap.get(merchantId).getName());
                    mCityOrderamount.setCityId(cityId);
                    mCityOrderamount.setCityName(cityName);
                    mCityOrderamount.setStatisticsDate(statisticDate);
                    mCityOrderamount.setStatisticStartTime(DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                    mCityOrderamount.setStatisticEndTime(DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                    mCityOrderamount.setPeriodType(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
                    mCityOrderamount.setOrderAmount(orderAmount.multiply(new BigDecimal(100)).longValue());

                    mCityOrderamoutList.add(mCityOrderamount);
                }
            } // 组装统计数据end

            log.info("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 统计结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(mCityOrderamoutList));

            // 4. 插入统计数据
            // 4.1 首先按照“统计时间”和“统计类型”从数据库获取是否有已统计过的数据; 如果有，则删除
            int count = mCityOrderAmountDao.deleteByPeriodTypeAndStatisticDate(
                    StatisticPeriodTypeEnum.DAY.getValue().shortValue(),
                    DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS),
                    DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
            log.info("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 删除数据数量:{}",
                    startDateTime, endDateTime, count);

            // 4.2 执行插入
            for (MCityOrderamount mCityOrderamount : mCityOrderamoutList) {
                mCityOrderAmountDao.insertMCityOrderAmount(mCityOrderamount);
            }

            log.info("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 执行完成!");
        } catch (Exception e) {
            log.error("按照商户-城市(天)维度统计订单详情总金额数据; 统计时间范围：{} - {} 异常:{}",
                    startDateTime, endDateTime, e.getMessage(), e);

            Cat.logEvent(StatisticConstants.DAILY_STATISTIC_EXCEPTION_TYPE, StatisticConstants.M_ORDERAMOUNT_TREND, Event.SUCCESS, "traceId=" + MDC.get("X-B3-TraceId"));
        }
    }

    @Override
    public void statisticDailyUserCount(List<OrderDetailBean> payedOrderDetailBeanList, String startDateTime,
                                        String endDateTime, Date statisticDate) throws Exception {
        log.info("按照商户维度统计用户数相关数据; 统计时间范围：{} - {}, 开始...", startDateTime, endDateTime);

        try {
            if (CollectionUtils.isEmpty(payedOrderDetailBeanList)) {
                log.info("按照商户维度统计用户数相关数据; 统计时间范围: {} - {} 统计数据为空 执行完成!");
                return;
            }

            // 1. 根据商户id维度将订单详情分类
            Map<Integer, List<OrderDetailBean>> orderDetailBeansByMerchantMap = divideOrderDetailByMerchantId(payedOrderDetailBeanList);
            log.info("按照商户维度统计用户数相关数据; 统计时间范围：{} - {}, 根据商户id维度将订单详情分组结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(orderDetailBeansByMerchantMap));

            // 2. 获取商户名称
            Set<Integer> merchantIdSet = orderDetailBeansByMerchantMap.keySet(); // 商户id集合
            List<SysCompany> sysCompanyList = vendorsRpcService.queryMerchantByIdList(new ArrayList<>(merchantIdSet));
            // 转map key:merchantId  value:SysUser
            Map<Integer, SysCompany> sysCompanyMap = sysCompanyList.stream()
                    .collect(Collectors.toMap(u -> u.getId().intValue(), u -> u));

            // 3. 获取商户的退货信息
            List<WorkOrder> workOrderList = workOrdersRpcService.queryRefundInfoList(startDateTime, endDateTime);
            // 从退货信息中聚合出各个商户的退货人数 key:merchantId value:用户id的set集合
            Map<Long, Set<String>> refundUserIdsMap = new HashMap<>();
            for (WorkOrder workOrder : workOrderList) {
                Long _merchantId = workOrder.getMerchantId();
                Set<String> refundUserIdSet = refundUserIdsMap.get(_merchantId);
                if (refundUserIdSet == null) {
                    refundUserIdSet = new HashSet<>();
                }

                refundUserIdSet.add(workOrder.getReceiverId());
            }
            log.info("按照商户维度统计用户数相关数据; 统计时间范围：{} - {}, 从退货信息中聚合出各个商户的退货人数信息:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(refundUserIdsMap));


            // 4. 获取统计数据
            List<MStatisticUser> mStatisticUserList = new ArrayList<>();
            // 遍历　orderDetailBeansByMerchantMap
            for (Integer merchantId : merchantIdSet) {
                // 获取该商户的已支付的子订单
                List<OrderDetailBean> orderDetailBeanList = orderDetailBeansByMerchantMap.get(merchantId);

                // a.遍历该商户的子订单获取下单人数
                Set<String> openIdSet = orderDetailBeanList.stream().map(o -> o.getOpenId()).collect(Collectors.toSet());

                // b.获取该商户的退货人数
                int refudnUserCount = 0;
                Set<String> refundUserIdSet = refundUserIdsMap.get(merchantId);
                if (refundUserIdSet != null) {
                    refudnUserCount = refundUserIdSet.size();
                }

                // 组装统计数据
                MStatisticUser mStatisticUser = new MStatisticUser();
                mStatisticUser.setMerchantId(merchantId);
                mStatisticUser.setMerchantCode("");
                mStatisticUser.setMerchantName(sysCompanyMap.get(merchantId) == null ? "" : sysCompanyMap.get(merchantId).getName());
                mStatisticUser.setOrderUserCount(openIdSet.size()); // 下单人数
                mStatisticUser.setRefundUserCount(refudnUserCount); // 退货人数
                mStatisticUser.setStatisticsDate(statisticDate);
                mStatisticUser.setStatisticStartTime(DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                mStatisticUser.setStatisticEndTime(DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
                mStatisticUser.setPeriodType(StatisticPeriodTypeEnum.DAY.getValue().shortValue());

                mStatisticUserList.add(mStatisticUser);
            }

            log.info("按照商户维度统计用户数相关数据; 统计时间范围：{} - {} 统计结果:{}",
                    startDateTime, endDateTime, JSONUtil.toJsonString(mStatisticUserList));

            // 5. 插入统计数据
            // 5.1 首先按照“统计时间”和“统计类型”从数据库获取是否有已统计过的数据; 如果有，则删除
            int count = mStatisticUserDao.deleteByPeriodTypeAndStatisticDate(
                    StatisticPeriodTypeEnum.DAY.getValue().shortValue(),
                    DateUtil.parseDateTime(startDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS),
                    DateUtil.parseDateTime(endDateTime, DateUtil.DATE_YYYY_MM_DD_HH_MM_SS));
            log.info("按照商户维度统计用户数相关数据; 统计时间范围：{} - {} 删除数据数量:{}",
                    startDateTime, endDateTime, count);

            // 5.2 执行插入
            for (MStatisticUser mStatisticUser : mStatisticUserList) {
                mStatisticUserDao.insertMStatisticUser(mStatisticUser);
            }

            log.info("按照商户维度统计用户数相关数据; 统计时间范围：{} - {} 执行完成!");
        } catch (Exception e) {
            log.error("按照商户维度统计用户数相关数据; 统计时间范围：{} - {} 异常:{}",
                    startDateTime, endDateTime, e.getMessage(), e);

            Cat.logEvent(StatisticConstants.DAILY_STATISTIC_EXCEPTION_TYPE, StatisticConstants.M_USER_TREND, Event.SUCCESS, "traceId=" + MDC.get("X-B3-TraceId"));
        }
    }

    @Override
    public MOverallResVo fetchOverAll(Long merchantId) throws Exception {
        // 1. 查询订单相关的整体运营数据
        // a.获取订单支付总额 b.(已支付)订单总量 c.(已支付)下单人数
        DayStatisticsBean dayStatisticsBean = ordersRpcService.queryMerchantOrdersOverallStatistic(merchantId.intValue());

        // 2. 查询该商户的退货人数
        Integer refundUserCount = workOrdersRpcService.queryRefundUserCountByMerchantId(merchantId);

        // 3. 组装返回数据
        MOverallResVo mOverallResVo = new MOverallResVo();
        if (dayStatisticsBean != null) {
            mOverallResVo.setOrderAmount(new BigDecimal(dayStatisticsBean.getOrderPaymentAmount()).toString()); // 订单总额
            mOverallResVo.setOrderCount(dayStatisticsBean.getOrderCount()); // 订单总量
            mOverallResVo.setOrderUserCount(dayStatisticsBean.getOrderPeopleNum()); // 下单人数
        } else {
            mOverallResVo.setOrderAmount("0"); // 订单总额
            mOverallResVo.setOrderCount(0); // 订单总量
            mOverallResVo.setOrderUserCount(0); // 下单人数
        }

        mOverallResVo.setRefundUserCount(refundUserCount); // 退货人数

        log.info("查询商户整体运营数据 merchantId:{} 获取的统计数据为:{}", merchantId, JSONUtil.toJsonString(mOverallResVo));

        return mOverallResVo;
    }

    @Override
    public List<MerchantCityRangeStatisticResVo> fetchOrderAmountTrend(String startDate,
                                                                                    String endDate,
                                                                                    Integer merchantId) throws Exception {
        // 1. 查询数据库
        Date _startDate = DateUtil.parseDateTime(startDate + " 00:00:00", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        Date _endDate = DateUtil.parseDateTime(endDate + " 23:59:59", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        log.info("根据时间范围获取daily型的商户-城市(天)维度统计数据 日期范围: {} - {}, 商户id:{}", _startDate, _endDate, merchantId);
        List<MCityOrderamount> mCityOrderamountList =
                mCityOrderAmountDao.selectDailyStatisticByDateRange(_startDate, _endDate, merchantId);
        log.info("根据时间范围获取daily型的商户-城市(天)维度统计数据 数据库返回: {}", JSONUtil.toJsonString(mCityOrderamountList));

        // 2. 统计数据
        // 将获取到的数据按照'城市name'聚合， 并转换成 MerchantCityRangeStatisticResVo
        Map<String, MerchantCityRangeStatisticResVo> cityRangeMap = new HashMap<>();
        for (MCityOrderamount mCityOrderamount : mCityOrderamountList) {
            String cityName = mCityOrderamount.getCityName();
            String cityId = mCityOrderamount.getCityId();
            Long orderAmount = mCityOrderamount.getOrderAmount(); // 单位 分

            MerchantCityRangeStatisticResVo cityRangeResVo = cityRangeMap.get(cityName);
            if (cityRangeResVo == null) {
                cityRangeResVo = new MerchantCityRangeStatisticResVo();
                cityRangeResVo.setCityId(cityId);
                cityRangeResVo.setCityName(cityName);
                cityRangeResVo.setOrderAmount(new BigDecimal(orderAmount).divide(new BigDecimal(100)).toString()); // 单位：元

                cityRangeMap.put(cityName, cityRangeResVo);
            } else {
                cityRangeResVo.setOrderAmount(new BigDecimal(cityRangeResVo.getOrderAmount()).
                        add(new BigDecimal(orderAmount).divide(new BigDecimal(100))).toString()); // 单位：元
            }
        }
        log.info("根据时间范围获取daily型的商户-城市(天)维度统计数据 按照城市分组结果:{}", JSONUtil.toJsonString(cityRangeMap));

        // 转list
        List<MerchantCityRangeStatisticResVo> resultList = new ArrayList<>(cityRangeMap.values());

        log.info("根据时间范围获取daily型的商户-城市(天)维度统计数据 获取统计数据 List<MerchantCityRangeStatisticResVo>:{}",
                JSONUtil.toJsonString(resultList));

        return resultList;
    }

    @Override
    public List<MUserStatisticResVo> fetchUserTrend(String startDate,
                                                           String endDate,
                                                           Integer merchantId) throws Exception {
        // 1. 查询数据库
        Date _startDate = DateUtil.parseDateTime(startDate + " 00:00:00", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        Date _endDate = DateUtil.parseDateTime(endDate + " 23:59:59", DateUtil.DATE_YYYY_MM_DD_HH_MM_SS);
        log.info("根据时间范围获取商户的用户变化趋势 日期范围: {} - {}, 商户id:{}", _startDate, _endDate, merchantId);
        List<MStatisticUser> mStatisticUserList =
                mStatisticUserDao.selectDailyStatisticByDateRange(_startDate, _endDate, merchantId);
        log.info("根据时间范围获取商户的用户变化趋势 数据库返回: {}", JSONUtil.toJsonString(mStatisticUserList));

//        if (CollectionUtils.isEmpty(mStatisticUserList)) {
//            return Collections.EMPTY_LIST;
//        }

        // 转map key：日期yyyy-MM-dd value: MStatisticUser
        Map<String, MStatisticUser> mStatisticUserMap = new HashMap<>();
        for (MStatisticUser mStatisticUser : mStatisticUserList) {
            String _date = DateUtil.dateTimeFormat(mStatisticUser.getStatisticStartTime(), DateUtil.DATE_YYYY_MM_DD);
            mStatisticUserMap.put(_date, mStatisticUser);
        }
        log.info("根据时间范围获取商户的用户变化趋势 按日期分组结果:{}", JSONUtil.toJsonString(mStatisticUserMap));

        // 2. 统计数据
        // 补充后的数据
        Map<String, MUserStatisticResVo> mUserStatisticResVoMap = new TreeMap<>(); // key:yyyy-MM-dd value:MUserStatisticResVo
        String currentDate = startDate; // 当前时间 yyyy-MM-dd
        while (DateUtil.compareDate(currentDate, DateUtil.DATE_YYYY_MM_DD, endDate, DateUtil.DATE_YYYY_MM_DD) <= 0) {
            MUserStatisticResVo mUserStatisticResVo = null; // 该天的统计数据

            MStatisticUser mStatisticUser = mStatisticUserMap.get(currentDate); // yyyy-MM-dd
            if (mStatisticUser == null) {
                mUserStatisticResVo = new MUserStatisticResVo(0, 0, currentDate);
            } else {
                mUserStatisticResVo = convertToMUserStatisticResVo(mStatisticUser);
            }

            mUserStatisticResVoMap.put(currentDate, mUserStatisticResVo);

            currentDate = DateUtil.plusDayWithDate(currentDate, DateUtil.DATE_YYYY_MM_DD, 1, DateUtil.DATE_YYYY_MM_DD);
        }

        log.info("根据时间范围获取商户的用户变化趋势 获取统计数据Map<String, MUserStatisticResVo>:{}",
                JSONUtil.toJsonString(mUserStatisticResVoMap));

        //
        List<MUserStatisticResVo> resultList = new ArrayList<>(mUserStatisticResVoMap.values());

        log.info("根据时间范围获取商户的用户变化趋势 获取统计数据返回List<MUserStatisticResVo>:{}", JSONUtil.toJsonString(resultList));

        return resultList;
    }


    // ======================================== private ==========================================

    /**
     * @param mStatisticUser
     * @return
     */
    private MUserStatisticResVo convertToMUserStatisticResVo(MStatisticUser mStatisticUser) {
        MUserStatisticResVo mUserStatisticResVo = new MUserStatisticResVo();
        mUserStatisticResVo.setOrderUserCount(mStatisticUser.getOrderUserCount());
        mUserStatisticResVo.setRefundUserCount(mStatisticUser.getRefundUserCount());
        mUserStatisticResVo.setStatisticDate(DateUtil.
                dateTimeFormat(mStatisticUser.getStatisticStartTime(), DateUtil.DATE_YYYY_MM_DD)); // yyyy-MM-dd

        return mUserStatisticResVo;
    }

    /**
     * @param mCityOrderamount
     * @return
     */
    private MerchantCityRangeStatisticResVo convertToMerchantCityRangeStatisticResVo(MCityOrderamount mCityOrderamount) {
        MerchantCityRangeStatisticResVo merchantCityRangeStatisticResVo = new MerchantCityRangeStatisticResVo();

        merchantCityRangeStatisticResVo.setCityId(mCityOrderamount.getCityId());
        merchantCityRangeStatisticResVo.setCityName(mCityOrderamount.getCityName());
        merchantCityRangeStatisticResVo.setOrderAmount(new BigDecimal(mCityOrderamount.getOrderAmount())
                .divide(new BigDecimal(100)).toString()); // 单位：元

        return merchantCityRangeStatisticResVo;
    }

    /**
     * 将订单按照商户订单分组
     *
     * @param orderDetailBeanList 订单详情列表
     * @return
     */
    private Map<Integer, List<OrderDetailBean>> divideOrderDetailByMerchantId(List<OrderDetailBean> orderDetailBeanList) {
        Map<Integer, List<OrderDetailBean>> orderDetailBeansByMerchantMap = new HashMap<>();
        for (OrderDetailBean orderDetailBean : orderDetailBeanList) {
            Integer merchantId = orderDetailBean.getMerchantId(); // 商户id
            List<OrderDetailBean> _orderDetailBeanList = orderDetailBeansByMerchantMap.get(merchantId);
            if (_orderDetailBeanList == null) {
                _orderDetailBeanList = new ArrayList<>();
                orderDetailBeansByMerchantMap.put(merchantId, _orderDetailBeanList);
            }
            _orderDetailBeanList.add(orderDetailBean);
        }

        return orderDetailBeansByMerchantMap;
    }
}
