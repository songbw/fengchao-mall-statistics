package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.MOverviewMapper;
import com.fengchao.statistics.model.MOverview;
import com.fengchao.statistics.model.MOverviewExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-25 下午5:17
 */
@Component
public class MOverviewDao {

    private MOverviewMapper mOverviewMapper;

    @Autowired
    public MOverviewDao(MOverviewMapper mOverviewMapper) {
        this.mOverviewMapper = mOverviewMapper;
    }

    /**
     * 新增
     *
     * @param mOverview
     * @return
     */
    public Long insertMOverview(MOverview mOverview) {
        int count = mOverviewMapper.insertSelective(mOverview);
        return mOverview.getId();
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
        MOverviewExample mOverviewExample = new MOverviewExample();

        MOverviewExample.Criteria criteria = mOverviewExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = mOverviewMapper.deleteByExample(mOverviewExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<MOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate, Integer merchantId) {
        MOverviewExample mOverviewExample = new MOverviewExample();

        MOverviewExample.Criteria criteria = mOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);
        criteria.andMerchantIdEqualTo(merchantId);

        List<MOverview> mOverviewList =
                mOverviewMapper.selectByExample(mOverviewExample);

        return mOverviewList;
    }
}
