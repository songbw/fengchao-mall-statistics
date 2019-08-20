package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.Overview;

import java.util.HashMap;

public interface OverviewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Overview record);

    int insertSelective(Overview record);

    Overview selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Overview record);

    int updateByPrimaryKey(Overview record);

    Overview selectSum() ;
}