package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.PromotionOverview;
import com.fengchao.statistics.model.PromotionOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PromotionOverviewMapper {
    long countByExample(PromotionOverviewExample example);

    int deleteByExample(PromotionOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PromotionOverview record);

    int insertSelective(PromotionOverview record);

    List<PromotionOverview> selectByExample(PromotionOverviewExample example);

    PromotionOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PromotionOverview record, @Param("example") PromotionOverviewExample example);

    int updateByExample(@Param("record") PromotionOverview record, @Param("example") PromotionOverviewExample example);

    int updateByPrimaryKeySelective(PromotionOverview record);

    int updateByPrimaryKey(PromotionOverview record);
}