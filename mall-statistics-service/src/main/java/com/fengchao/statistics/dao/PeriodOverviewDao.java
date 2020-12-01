package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.BaiduStatisMapper;
import com.fengchao.statistics.mapper.PeriodOverviewMapper;
import com.fengchao.statistics.model.CategoryOverview;
import com.fengchao.statistics.model.PeriodOverview;
import com.fengchao.statistics.model.PeriodOverviewExample;
import com.fengchao.statistics.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-25 下午5:17
 */
@Slf4j
@Component
public class PeriodOverviewDao {

    private PeriodOverviewMapper periodOverviewMapper;
    private BaiduStatisMapper baiduStatisMapper;

    @Autowired
    public PeriodOverviewDao(PeriodOverviewMapper periodOverviewMapper, BaiduStatisMapper baiduStatisMapper) {
        this.periodOverviewMapper = periodOverviewMapper;
        this.baiduStatisMapper = baiduStatisMapper;
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
    public List<PeriodOverview> selectDailyStatisticByDateRange(Date startDate, Date endDate, String renterId) {
        PeriodOverviewExample periodOverviewExample = new PeriodOverviewExample();

        PeriodOverviewExample.Criteria criteria = periodOverviewExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);


        List<PeriodOverview> periodOverviewList = new ArrayList<>();
        if (StringUtils.isNotBlank(renterId) && !"0".equals(renterId)) {
            criteria.andRenterIdEqualTo(renterId);
            periodOverviewList =
                    periodOverviewMapper.selectByExample(periodOverviewExample);
        } else {
            HashMap map = new HashMap();
            map.put("startDate", startDate) ;
            map.put("endDate", endDate) ;
            List<HashMap<String, Object>> categoryOverviewXList = baiduStatisMapper.selectPeriodSum(map) ;
            log.info("baiduStatisMapper.selectPeriodSum is {}", JSONUtil.toJsonString(categoryOverviewXList));
            if (categoryOverviewXList != null && categoryOverviewXList.size() > 0 && categoryOverviewXList.get(0) != null) {
                for (HashMap<String, Object> hashMap: categoryOverviewXList) {
                    PeriodOverview overview = new PeriodOverview() ;
                    overview.setLateAtNight(Long.valueOf((String) hashMap.get("lateAtNight")));
                    overview.setEarlyMorning(Long.valueOf((String) hashMap.get("earlyMorning")));
                    overview.setMorning(Long.valueOf((String) hashMap.get("morning")));
                    overview.setNoon(Long.valueOf((String) hashMap.get("noon")));
                    overview.setAfternoon(Long.valueOf((String) hashMap.get("afternoon")));
                    overview.setNight(Long.valueOf((String) hashMap.get("night")));
                    periodOverviewList.add(overview) ;
                }
            }

        }

        return periodOverviewList;
    }
}
