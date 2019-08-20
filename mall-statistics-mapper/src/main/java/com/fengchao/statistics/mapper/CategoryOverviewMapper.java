package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.CategoryOverview;
import com.fengchao.statistics.model.CategoryOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryOverviewMapper {
    long countByExample(CategoryOverviewExample example);

    int deleteByExample(CategoryOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CategoryOverview record);

    int insertSelective(CategoryOverview record);

    List<CategoryOverview> selectByExample(CategoryOverviewExample example);

    CategoryOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CategoryOverview record, @Param("example") CategoryOverviewExample example);

    int updateByExample(@Param("record") CategoryOverview record, @Param("example") CategoryOverviewExample example);

    int updateByPrimaryKeySelective(CategoryOverview record);

    int updateByPrimaryKey(CategoryOverview record);
}