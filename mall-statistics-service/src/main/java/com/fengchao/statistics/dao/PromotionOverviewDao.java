package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.PromotionOverviewMapper;
import com.fengchao.statistics.model.PromotionOverview;
import com.fengchao.statistics.model.PromotionOverviewExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-26 下午6:41
 */
@Component
public class PromotionOverviewDao {

    private PromotionOverviewMapper promotionOverviewMapper;

    @Autowired
    public PromotionOverviewDao(PromotionOverviewMapper promotionOverviewMapper) {
        this.promotionOverviewMapper = promotionOverviewMapper;
    }

    /**
     * 新增
     *
     * @param promotionOverview
     * @return
     */
    public Long insertCategoryOverview(PromotionOverview promotionOverview) {
        int count = promotionOverviewMapper.insertSelective(promotionOverview);
        return promotionOverview.getId();
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
        PromotionOverviewExample promotionOverviewExample = new PromotionOverviewExample();

        PromotionOverviewExample.Criteria criteria = promotionOverviewExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = promotionOverviewMapper.deleteByExample(promotionOverviewExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<PromotionOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate) {
        PromotionOverviewExample promotionOverviewExample = new PromotionOverviewExample();

        PromotionOverviewExample.Criteria criteria = promotionOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);

        List<PromotionOverview> promotionOverviewList =
                promotionOverviewMapper.selectByExample(promotionOverviewExample);

        return promotionOverviewList;
    }
}
