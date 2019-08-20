package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.CategoryOverviewMapper;
import com.fengchao.statistics.model.CategoryOverview;
import com.fengchao.statistics.model.CategoryOverviewExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-25 下午5:17
 */
@Component
public class CategoryOverviewDao {

    private CategoryOverviewMapper categoryOverviewMapper;

    @Autowired
    public CategoryOverviewDao(CategoryOverviewMapper categoryOverviewMapper) {
        this.categoryOverviewMapper = categoryOverviewMapper;
    }

    /**
     * 新增
     *
     * @param categoryOverview
     * @return
     */
    public Long insertCategoryOverview(CategoryOverview categoryOverview) {
        int count = categoryOverviewMapper.insertSelective(categoryOverview);
        return categoryOverview.getId();
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
        CategoryOverviewExample categoryOverviewExample = new CategoryOverviewExample();

        CategoryOverviewExample.Criteria criteria = categoryOverviewExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = categoryOverviewMapper.deleteByExample(categoryOverviewExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<CategoryOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate) {
        CategoryOverviewExample categoryOverviewExample = new CategoryOverviewExample();

        CategoryOverviewExample.Criteria criteria = categoryOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);

        List<CategoryOverview> categoryOverviewList =
                categoryOverviewMapper.selectByExample(categoryOverviewExample);

        return categoryOverviewList;
    }
}
