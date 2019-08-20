package com.fengchao.statistics.service;

import com.fengchao.statistics.bean.vo.MOverallResVo;
import com.fengchao.statistics.bean.vo.MUserStatisticResVo;
import com.fengchao.statistics.bean.vo.MerchantCityRangeStatisticResVo;
import com.fengchao.statistics.rpc.extmodel.OrderDetailBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author tom
 * @Date 19-7-27 下午4:20
 */
public interface MerchantStatisticService {

    /**
     * 每天 统计各个商户的整体运营数据，目前包括:
     * 订单支付总额, 订单总量, 下单人数, 退货人数
     *
     * @param payedOrderDetailBeanList 需要统计的原始数据-已支付的订单详情
     * @param startDateTime
     * @param endDateTime
     * @param statisticDate            统计执行日期
     * @throws Exception
     */
    @Deprecated
    void statisticOverall(List<OrderDetailBean> payedOrderDetailBeanList,
                          String startDateTime,
                          String endDateTime, Date statisticDate) throws Exception;

    /**
     * 每天 按照商户-城市的维度，统计订单支付总额
     *
     * @param payedOrderDetailBeanList 需要统计的原始数据-已支付的订单详情
     * @param startDateTime
     * @param endDateTime
     * @param statisticDate            统计执行日期
     * @throws Exception
     */
    void statisticDailyOrderAmountByCity(List<OrderDetailBean> payedOrderDetailBeanList,
                                         String startDateTime,
                                         String endDateTime, Date statisticDate) throws Exception;

    /**
     * 每天 按照商户维度统计用户数相关数据, 目前包括:
     * 下单人数， 退货人数
     *
     * @param payedOrderDetailBeanList
     * @param startDateTime
     * @param endDateTime
     * @param statisticDate
     * @throws Exception
     */
    void statisticDailyUserCount(List<OrderDetailBean> payedOrderDetailBeanList,
                                 String startDateTime,
                                 String endDateTime, Date statisticDate) throws Exception;

    /**
     * 商户整体运营数据
     *
     * @param merchantId
     * @return
     * @throws Exception
     */
    MOverallResVo fetchOverAll(Long merchantId) throws Exception;

    /**
     * 订单支付总额变化趋势
     *
     * @param startDate  yyyy-MM-dd
     * @param endDate    yyyy-MM-dd
     * @param merchantId
     * @return
     * @throws Exception
     */
    List<MerchantCityRangeStatisticResVo> fetchOrderAmountTrend(String startDate,
                                                                             String endDate,
                                                                             Integer merchantId) throws Exception;

    /**
     * 根据时间范围获取商户的用户变化趋势
     *
     * @param startDate
     * @param endDate
     * @param merchantId
     * @return
     * @throws Exception
     */
    List<MUserStatisticResVo> fetchUserTrend(String startDate,
                                                    String endDate,
                                                    Integer merchantId) throws Exception;
}
