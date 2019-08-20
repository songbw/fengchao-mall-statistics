package com.fengchao.statistics.dao;

import com.fengchao.statistics.constants.IStatusEnum;
import com.fengchao.statistics.constants.StatisticPeriodTypeEnum;
import com.fengchao.statistics.mapper.MStatisticUserMapper;
import com.fengchao.statistics.model.MStatisticUser;
import com.fengchao.statistics.model.MStatisticUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author tom
 * @Date 19-7-25 下午5:17
 */
@Component
public class MStatisticUserDao {

    private MStatisticUserMapper mStatisticUserMapper;

    @Autowired
    public MStatisticUserDao(MStatisticUserMapper mStatisticUserMapper) {
        this.mStatisticUserMapper = mStatisticUserMapper;
    }

    /**
     * 新增
     *
     * @param mStatisticUser
     * @return
     */
    public Long insertMStatisticUser(MStatisticUser mStatisticUser) {
        int count = mStatisticUserMapper.insertSelective(mStatisticUser);
        return mStatisticUser.getId();
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
        MStatisticUserExample mStatisticUserExample = new MStatisticUserExample();

        MStatisticUserExample.Criteria criteria = mStatisticUserExample.createCriteria();
        criteria.andPeriodTypeEqualTo(period);
        criteria.andStatisticStartTimeEqualTo(statisticStartDate);
        criteria.andStatisticEndTimeEqualTo(statisticEndDate);

        int count = mStatisticUserMapper.deleteByExample(mStatisticUserExample);

        return count;
    }

    /**
     * 根据时间范围获取daily型的统计数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<MStatisticUser> selectDailyStatisticByDateRange(Date startDate, Date endDate, Integer merchantId) {
        MStatisticUserExample mStatisticUserExample = new MStatisticUserExample();

        MStatisticUserExample.Criteria criteria = mStatisticUserExample.createCriteria();
        criteria.andIstatusEqualTo(IStatusEnum.VALID.getValue().shortValue());

        criteria.andPeriodTypeEqualTo(StatisticPeriodTypeEnum.DAY.getValue().shortValue());
        criteria.andStatisticStartTimeBetween(startDate, endDate);
        criteria.andMerchantIdEqualTo(merchantId);

        List<MStatisticUser> mStatisticUserList =
                mStatisticUserMapper.selectByExample(mStatisticUserExample);

        return mStatisticUserList;
    }
}
