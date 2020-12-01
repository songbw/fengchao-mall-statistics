package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.AppOverviewMapper;
import com.fengchao.statistics.mapper.BaiduStatisMapper;
import com.fengchao.statistics.model.AppOverview;
import com.fengchao.statistics.model.AppOverviewExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-25 下午5:17
 */
@Component
public class AppOverviewDao {

    private AppOverviewMapper appOverviewMapper;
    private BaiduStatisMapper baiduStatisMapper ;

    @Autowired
    public AppOverviewDao(AppOverviewMapper appOverviewMapper, BaiduStatisMapper baiduStatisMapper) {
        this.appOverviewMapper = appOverviewMapper;
        this.baiduStatisMapper = baiduStatisMapper;
    }

    /**
     * 新增
     *
     * @param appOverview
     * @return
     */
    public Long insertOverview(AppOverview appOverview) {
        int count = appOverviewMapper.insertSelective(appOverview);
        return appOverview.getId();
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
        AppOverviewExample appOverviewExample = new AppOverviewExample();

        AppOverviewExample.Criteria criteria = appOverviewExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = appOverviewMapper.deleteByExample(appOverviewExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<AppOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate, String renterId) {
        AppOverviewExample appOverviewExample = new AppOverviewExample();

        AppOverviewExample.Criteria criteria = appOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);
        if (StringUtils.isNotBlank(renterId) && !"0".equals(renterId)) {
            criteria.andRenterIdEqualTo(renterId);
        }
        List<AppOverview> appOverviewList =
                appOverviewMapper.selectByExample(appOverviewExample);
        return appOverviewList;
    }
}
