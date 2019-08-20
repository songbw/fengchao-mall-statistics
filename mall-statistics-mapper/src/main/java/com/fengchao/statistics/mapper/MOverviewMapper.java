package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.MOverview;
import com.fengchao.statistics.model.MOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MOverviewMapper {
    long countByExample(MOverviewExample example);

    int deleteByExample(MOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MOverview record);

    int insertSelective(MOverview record);

    List<MOverview> selectByExample(MOverviewExample example);

    MOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MOverview record, @Param("example") MOverviewExample example);

    int updateByExample(@Param("record") MOverview record, @Param("example") MOverviewExample example);

    int updateByPrimaryKeySelective(MOverview record);

    int updateByPrimaryKey(MOverview record);
}