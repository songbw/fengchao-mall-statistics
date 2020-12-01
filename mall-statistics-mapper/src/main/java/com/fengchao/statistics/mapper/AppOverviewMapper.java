package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.AppOverview;
import com.fengchao.statistics.model.AppOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppOverviewMapper {
    long countByExample(AppOverviewExample example);

    int deleteByExample(AppOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppOverview record);

    int insertSelective(AppOverview record);

    List<AppOverview> selectByExample(AppOverviewExample example);

    AppOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppOverview record, @Param("example") AppOverviewExample example);

    int updateByExample(@Param("record") AppOverview record, @Param("example") AppOverviewExample example);

    int updateByPrimaryKeySelective(AppOverview record);

    int updateByPrimaryKey(AppOverview record);
}