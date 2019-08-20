package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.MerchantOverviewMapper;
import com.fengchao.statistics.model.MerchantOverview;
import com.fengchao.statistics.model.MerchantOverviewExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-26 下午6:41
 */
@Component
public class MerchantOverviewDao {

    private MerchantOverviewMapper merchantOverviewMapper;

    @Autowired
    public MerchantOverviewDao(MerchantOverviewMapper merchantOverviewMapper) {
        this.merchantOverviewMapper = merchantOverviewMapper;
    }

    /**
     * 新增
     *
     * @param merchantOverview
     * @return
     */
    public Long insertMerchantOverview(MerchantOverview merchantOverview) {
        int count = merchantOverviewMapper.insertSelective(merchantOverview);
        return merchantOverview.getId();
    }

    /**
     * 根据统计周期类型 和 统计开始/结束时间 删除 统计数据
     *
     * @param period
     * @param statisticStartDate
     * @param statisticEndDate
     * @return
     */
    public int deleteByPeriodTypeAndStatisticDate(Short period,
                                                  Date statisticStartDate, Date statisticEndDate) {
        MerchantOverviewExample merchantOverviewExample = new MerchantOverviewExample();

        MerchantOverviewExample.Criteria criteria = merchantOverviewExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = merchantOverviewMapper.deleteByExample(merchantOverviewExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<MerchantOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate) {
        MerchantOverviewExample merchantOverviewExample = new MerchantOverviewExample();

        MerchantOverviewExample.Criteria criteria = merchantOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);

        List<MerchantOverview> merchantOverviewList =
                merchantOverviewMapper.selectByExample(merchantOverviewExample);

        return merchantOverviewList;
    }
}
