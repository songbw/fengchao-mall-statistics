package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.PeriodOverview;
import com.fengchao.statistics.model.PeriodOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PeriodOverviewMapper {
    long countByExample(PeriodOverviewExample example);

    int deleteByExample(PeriodOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PeriodOverview record);

    int insertSelective(PeriodOverview record);

    List<PeriodOverview> selectByExample(PeriodOverviewExample example);

    PeriodOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PeriodOverview record, @Param("example") PeriodOverviewExample example);

    int updateByExample(@Param("record") PeriodOverview record, @Param("example") PeriodOverviewExample example);

    int updateByPrimaryKeySelective(PeriodOverview record);

    int updateByPrimaryKey(PeriodOverview record);
}