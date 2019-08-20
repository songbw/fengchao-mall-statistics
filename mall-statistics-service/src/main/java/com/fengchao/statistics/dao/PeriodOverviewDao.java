package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.PeriodOverviewMapper;
import com.fengchao.statistics.model.PeriodOverview;
import com.fengchao.statistics.model.PeriodOverviewExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-25 下午5:17
 */
@Component
public class PeriodOverviewDao {

    private PeriodOverviewMapper periodOverviewMapper;

    @Autowired
    public PeriodOverviewDao(PeriodOverviewMapper periodOverviewMapper) {
        this.periodOverviewMapper = periodOverviewMapper;
    }

    /**
     * 新增
     *
     * @param periodOverview
     * @return
     */
    public Long insertPeriodOverview(PeriodOverview periodOverview) {
        periodOverviewMapper.insertSelective(periodOverview);
        return periodOverview.getId();
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
        PeriodOverviewExample periodOverviewExample = new PeriodOverviewExample();

        PeriodOverviewExample.Criteria criteria = periodOverviewExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = periodOverviewMapper.deleteByExample(periodOverviewExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<PeriodOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate) {
        PeriodOverviewExample periodOverviewExample = new PeriodOverviewExample();

        PeriodOverviewExample.Criteria criteria = periodOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);

        List<PeriodOverview> periodOverviewList =
                periodOverviewMapper.selectByExample(periodOverviewExample);

        return periodOverviewList;
    }
}
