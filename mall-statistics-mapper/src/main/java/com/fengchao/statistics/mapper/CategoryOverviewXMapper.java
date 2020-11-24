package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.CategoryOverview;

import java.util.HashMap;
import java.util.List;

public interface CategoryOverviewXMapper {
    List<CategoryOverview> selectAllSum(HashMap map) ;
}